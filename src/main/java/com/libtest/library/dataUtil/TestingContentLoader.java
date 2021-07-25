package com.libtest.library.dataUtil;

import com.libtest.library.dto.BookDto;
import com.libtest.library.entity2.Author;
import com.libtest.library.entity2.Book;
import com.libtest.library.repo.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Profile("tester")
@RequiredArgsConstructor
public class TestingContentLoader implements ApplicationRunner {

    @Value("${testing.data.path}")
    private String pathToData;

    @Value("${testing.data.root.path}")
    private String root;

    private final DataParser<BookDto> dataParser;

    private final BookRepository repository;


    @SneakyThrows
    @Override
    public void run(ApplicationArguments args) {

        final List<BookDto> bookDtos = dataParser.parseFrom(root, pathToData);

        Set<Book> booksToDb = new HashSet<>(bookDtos.size() + 1);

        for (BookDto aBook : bookDtos) {

            final Author author = new Author(aBook.getAuthors(), LocalDate.now());
            List<Author> authorList = List.of(author);

            Book bookEntity = new Book(aBook.getTitle(), aBook.getYear(), aBook.getPages(), authorList);
            author.setBook(bookEntity);

            booksToDb.add(bookEntity);
        }

        repository.saveAll(booksToDb);
    }
}
