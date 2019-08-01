package com.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.models.factory.TileBoardFactory;

@Configuration
public class ControllerConfig {
    @Bean
    public TileBoardFactory tileBoardFactory() {
        return new TileBoardFactory();
    }

}
