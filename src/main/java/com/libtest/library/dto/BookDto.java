package com.libtest.library.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"author", "pages", "title", "year"})
@JsonIgnoreProperties({"country", "imageLink", "language", "link"})
public class BookDto {

    @JsonProperty("title")
    private String title;

    @JsonProperty("year")
    private Integer year;

    @JsonProperty("pages")
    private Integer pages;

    @JsonProperty("author")
    private String authors;
}
