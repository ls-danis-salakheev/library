package com.libtest.library.dataUtil;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.libtest.library.dto.BookDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TestingDataParser implements DataParser<BookDto> {

    @Qualifier("dataObjectMapper")
    private final ObjectMapper objectMapper;

    @Override
    public List<BookDto> parseFrom(String root, String resource) {
        File file = dataFile(root, resource);
        try {
            return objectMapper.readValue(file, new TypeReference<>() {
            });
        } catch (IOException e) {
            log.error("Cannot do marshalling from json to pojo with " + BookDto.class + ".");
            throw new RuntimeException(e);
        }
    }

    private File dataFile(String root, String resourceNodes) {

        String[] res = resourceNodes.split(",");

        return Paths.get(root, res).toFile();
    }


}

