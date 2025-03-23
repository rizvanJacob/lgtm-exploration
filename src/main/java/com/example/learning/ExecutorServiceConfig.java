package com.example.learning;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class ExecutorServiceConfig {
    @Bean
    public ScheduledExecutorService createExecutor(){
        return Executors.newScheduledThreadPool(1);
    }
}
