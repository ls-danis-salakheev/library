package com.libtest.library.entity2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "books")
@Getter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Setter
    @Column(unique = true, nullable = false)
    private String title;

    @Setter
    private Integer year;

    @Setter
    private Integer pages;

    @JsonIgnore
    @Setter
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    @Fetch(FetchMode.JOIN)
    private List<Author> authors;


    public Book(String title, Integer year, Integer pages, List<Author> authors) {
        this.title = title;
        this.year = year;
        this.pages = pages;
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", year=" + year +
               ", pages=" + pages +
               ", authors=" + authors +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
