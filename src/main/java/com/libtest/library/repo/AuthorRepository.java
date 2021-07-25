package com.libtest.library.repo;


import com.libtest.library.entity2.Author;
import com.libtest.library.entity2.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Book> findByNameOrBook_TitleOrBook_Year(String string, String strin, Integer year);

}
