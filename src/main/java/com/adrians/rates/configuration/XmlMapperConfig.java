package com.adrians.rates.configuration;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XmlMapperConfig {
    @Bean
    public XmlMapper xmlMapper() {
        return XmlMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
    }
}
