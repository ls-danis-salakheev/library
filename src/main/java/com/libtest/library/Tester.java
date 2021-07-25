package com.libtest.library;

import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Tester {

    @SneakyThrows
    public static void main(String[] args) {

        final HttpClient build = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

        final HttpRequest request = HttpRequest
                .newBuilder(
                        new URI("https://en.wikipedia.org/w/api.php?action=query&prop=revisions&rvprop=content&format=json&titles=Michael%20Jackson")).build();
        var send = build.send(request, HttpResponse.BodyHandlers.ofString());

    }

}
