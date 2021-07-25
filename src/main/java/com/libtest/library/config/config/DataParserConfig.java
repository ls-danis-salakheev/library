package com.libtest.library.config.config;

import com.fasterxml.jackson.databind.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataParserConfig {

    @Bean("dataObjectMapper")
    public ObjectMapper dataObjectMapper() {

        return new ObjectMapper();
    }

}
