package com.example.springbootcucumber.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingService {

    public void log() {
        log.info("Something has been logged...");
    }
}
