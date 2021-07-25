package com.libtest.library.service;

import com.libtest.library.entity.Book;
import com.libtest.library.exception.IllegalNumberException;
import com.libtest.library.exception.PageNumberException;
import com.libtest.library.repo.BookRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static java.util.Objects.isNull;

@Service
@Transactional(readOnly = true)
public class BookPolySelectServiceImpl implements BookPolySelectService {

    private final BookRepository bookRepository;

    public BookPolySelectServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {

        return bookRepository.findAll();
    }

    @Override
    public List<Book> findBy(
            String parameter,
            Integer year,
            Integer authorCount) {

        return bookRepository.findByTitleOrAuthorNameAndYear(parameter, year);
    }

    @Override
    public List<Book> findByMultiSelect(
            String title,
            Integer from,
            Integer until,
            Long count) {

        if (isNull(title)) title = "";

        if (isNull(from)) from = LocalDate.MIN.getYear();
        if (isNull(until)) until = LocalDate.now().getYear();
        if (isNull(count)) count = 0L;

        if (count < 0) throw new IllegalNumberException("Input number in query '" + count + "' is negative.");

        return bookRepository.findTitlesByTitleAndYearsBetweenAndAuthorCountMoreThan(title, from, until, count);

    }

    @Override
    public List<Book> findOnPage(Integer page) {

        if (isNull(page)) page = 1;

        if (page <= 0) throw new PageNumberException("Page number must be positive and greater than zero");

        return bookRepository.findAll(PageRequest.of(page - 1, 20)).getContent();
    }
}
