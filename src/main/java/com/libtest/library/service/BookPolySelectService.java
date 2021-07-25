package com.libtest.library.service;

import com.libtest.library.entity.Book;

import java.util.List;

public interface BookPolySelectService {

    List<Book> findAll();

    List<Book> findBy(String parameter, Integer year, Integer authorCount);

    List<Book> findByMultiSelect(String title, Integer from, Integer untill, Long count);

    List<Book> findOnPage(Integer page);
}
