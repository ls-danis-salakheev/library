package com.libtest.library.controller.v1;

import com.libtest.library.entity.Book;
import com.libtest.library.service.BookPolySelectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/v1/books")
@RestController
@RequiredArgsConstructor
public class BookControllerV1 {

    private final BookPolySelectService bookPolySelectService;

    @GetMapping
    public ResponseEntity<List<Book>> findOnPage(@RequestParam(value = "page", required = false) Integer page) {

        List<Book> booksOnPage = bookPolySelectService.findOnPage(page);

        return ResponseEntity.ok().body(booksOnPage);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> findByParameters(
            @RequestParam("title") String title,
            @RequestParam("from") Integer from,
            @RequestParam("until") Integer until,
            @RequestParam("count") Long count) {

        return ResponseEntity.ok().body(bookPolySelectService.findByMultiSelect(title, from, until, count));

    }
}
