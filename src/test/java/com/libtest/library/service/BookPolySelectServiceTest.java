package com.libtest.library.service;

import com.libtest.library.entity.Author;
import com.libtest.library.entity.Book;
import com.libtest.library.exception.IllegalNumberException;
import com.libtest.library.exception.PageNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toUnmodifiableList;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookPolySelectServiceTest {

    final List<String> THE_SAME_AUTHORS_LIST_FOR_CHECKING = Stream.generate(() -> "Leo Tolstoy").limit(3).collect(toUnmodifiableList());

    final int BOOKS_COUNT_ON_PAGE_LESS_AND_EQUALS = 20;

    final int CURRENT_YEAR = LocalDate.now().getYear();

    @Autowired
    BookPolySelectService bookPolySelectService;

    @Test
    void findBookByAuthorNameInParamAndNullableOtherParams() {

        List<Book> books = bookPolySelectService.findBy("Tolstoy", null, null);

        List<String> authorsNameToCheck = books.stream()
                .flatMap(book -> book.getAuthors().stream())
                .map(Author::getName)
                .collect(toUnmodifiableList());

        assertIterableEquals(authorsNameToCheck, THE_SAME_AUTHORS_LIST_FOR_CHECKING);

    }

    @DisplayName(value = "Should return count of book satisfies the condition in the method. Query executes in JPQL.")
    @Test
    void findByMultiSetWithTitle() {

        final int actualBooksCount = 29;

        List<Book> dbData = bookPolySelectService.findByMultiSelect("The", 1000, 1603, 0L);

        assertEquals(dbData.size(), actualBooksCount);

        dbData.forEach(System.out::println);
    }

    @DisplayName(value = "Should return count of book satisfies the condition in the method. Query executes in JPQL.")
    @Test
    void findByMultiSetCommonly() {

        final int actualBooksCount = 98;

        List<Book> dbData = bookPolySelectService.findByMultiSelect(null, 1000, 1603, 0L);

        assertEquals(dbData.size(), actualBooksCount);

        dbData.forEach(System.out::println);
    }

    @DisplayName(value = "Throws IllegalNumberException when books' author count is negative")
    @Test
    void negativeNumbersInput() {

        assertThrows(IllegalNumberException.class, () -> bookPolySelectService.findByMultiSelect("Man", -1000, CURRENT_YEAR, -10L));

    }

    @DisplayName(value = "Should return all books if all parameters are nullable")
    @Test
    void nullsSettingInMethod() {

        List<Book> bookList = bookPolySelectService.findByMultiSelect(null, null, null, null);

        List<Book> allBooksInRepo = bookPolySelectService.findAll();

        assertFalse(bookList.isEmpty());

        assertEquals(bookList.size(), allBooksInRepo.size());
    }

    @Test
    void unitsCountOnPageNumberSet() {

        List<Book> entityOnPage = bookPolySelectService.findOnPage(1);

        assertTrue(entityOnPage.size() <= BOOKS_COUNT_ON_PAGE_LESS_AND_EQUALS);

    }

    @DisplayName(value = "Throws PageNumberException when page number is negative")
    @Test
    void NegativeOrZeroNumberPageSet() {

        int negativeNumber = -100;

        assertThrows(PageNumberException.class, () -> bookPolySelectService.findOnPage(negativeNumber));

        assertThrows(PageNumberException.class, () -> bookPolySelectService.findOnPage(0));
    }

    @DisplayName(value = "Returning content should be equal to the content on the first page.")
    @Test
    void nullPageNumberSet() {

        int firstPageNum = 1;

        final List<Book> contentOnFirstPage = bookPolySelectService.findOnPage(firstPageNum);

        final List<Book> nullPageInputContent = bookPolySelectService.findOnPage(null);

        assertIterableEquals(nullPageInputContent, contentOnFirstPage);

    }
}
