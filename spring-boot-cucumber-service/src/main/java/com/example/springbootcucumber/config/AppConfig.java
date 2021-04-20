package com.example.springbootcucumber.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.springbootcucumber.service.LoggingService;

@Configuration
public class AppConfig {

    @Bean
    public LoggingService loggingService() {
        return new LoggingService();
    }
}
