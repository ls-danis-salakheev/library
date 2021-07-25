package com.libtest.library.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookControllerV1Test {

    String LOCAL_HOST = "http://localhost:8080/v1/books";

    final HttpClient build = HttpClient.newBuilder().build();

    @Test
    void select() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(LOCAL_HOST + "?page=1")).build();

        var send = build
                .send(request, HttpResponse.BodyHandlers.ofFile(Path.of("src", "test", "resources", "tested", "1", "firstPage.json")));


        assertEquals(send.statusCode(), HttpStatus.OK.value());

        final File downloaded = send.body().toFile();
        assertTrue(downloaded.exists());

        downloaded.delete();

    }
}