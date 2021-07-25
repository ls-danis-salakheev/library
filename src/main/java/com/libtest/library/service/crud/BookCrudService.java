package com.libtest.library.service.crud;

import com.libtest.library.entity.Book;
import com.libtest.library.exception.EntityNotFoundException;
import com.libtest.library.exception.EntityPersistenceException;
import com.libtest.library.repo.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class BookCrudService implements EntityCrudService<Book> {

    private final BookRepository bookRepository;

    @Transactional
    @Override
    public void create(Book entity) {
        bookRepository.save(entity);
    }

    @Transactional
    @Override
    public Book update(Long id, Book toUpdate) {

        Book resultFromDb = bookRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find the entity with id " + id));

        BeanUtils.copyProperties(toUpdate, resultFromDb);

        return bookRepository
                .save(resultFromDb);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository
                .findById(id)
                .orElseThrow(() -> new EntityPersistenceException("Not found by id " + id));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {

        bookRepository.deleteById(id);
    }
}
