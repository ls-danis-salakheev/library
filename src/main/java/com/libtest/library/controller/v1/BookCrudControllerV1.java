package com.libtest.library.controller.v1;

import com.libtest.library.entity2.Book;
import com.libtest.library.service.crud.EntityCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/books")
@RequiredArgsConstructor
public class BookCrudControllerV1 {

    private final EntityCrudService<Book> bookCrudService;

    @GetMapping("{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {

        return ResponseEntity.ok(bookCrudService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> saveOne(@RequestBody Book book) {

        bookCrudService.create(book);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {

        Book res = bookCrudService.update(id, book);

        return ResponseEntity.ok(res);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {

        bookCrudService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
