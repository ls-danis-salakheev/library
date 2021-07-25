package com.libtest.library.repo;


import com.libtest.library.entity2.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.List;

@NamedQueries(
        @NamedQuery(name = "Collec.fid", query = "")
)
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(
            name = "Deleting operation by title, year and pages",
            value = "delete from books where title ilike '%:title%' and year = :year and pages = :pages",
            nativeQuery = true
    )
    void deleteByTitleAndYearAndPages(
            @Param("title") String title,
            @Param("year") Integer year,
            @Param("pages") Integer pages);

    @Query(name = "Selection two tables by left outer join type with query parameters",
            value =
                    "SELECT b FROM Book b LEFT JOIN FETCH b.authors a " +
                    "WHERE b.title LIKE %:parameter% OR a.name LIKE %:parameter% OR b.year = :year")
    List<Book> findByTitleOrAuthorNameAndYear(
            @Param("parameter") String title,
            @Param("year") Integer year);


    @Query(
            value = "SELECT * from books b where " +
                    "b.title in (select b.title from books b " +
                    "left join authors a on b.id = a.book_id where b.title " +
                    "LIKE %:title% AND b.year between :from and :to GROUP BY b.title having count(a.name) > :num)",
            nativeQuery = true
    )
    List<Book> findTitlesByTitleAndYearsBetweenAndAuthorCountMoreThanNative(@Param("title") String title,
                                                                            @Param("from") Integer from,
                                                                            @Param("to") Integer untill,
                                                                            @Param("num") Integer count);

    @Query(
            value = "SELECT b from Book b where\n" +
                    "b.title in (select b.title from Book b\n" +
                    "join b.authors a where b.title\n" +
                    "LIKE %:title% OR a.name LIKE %:title% AND b.year between :from and :to GROUP BY b.title having count(a.name) > :num)"
    )
    List<Book> findTitlesByTitleAndYearsBetweenAndAuthorCountMoreThan(@Param("title") String title,
                                                                      @Param("from") Integer from,
                                                                      @Param("to") Integer until,
                                                                      @Param("num") Long count);


}
