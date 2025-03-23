package com.example.learning;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Random;
import java.util.concurrent.*;

@Component
@Slf4j
public class LoggingSimulator {
    private static final float errorLogRate = 0.01f,
    warnLogRate = 0.03f,
    infoLogRate = 0.3f;
    private final Random random = new Random();
    private final ScheduledExecutorService executor;
    public LoggingSimulator(){
        this.executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(this::simulateLog, 0, 100, TimeUnit.MILLISECONDS);
    }
    private void simulateLog(){
        var randomFloat = random.nextFloat();
        if (randomFloat < errorLogRate) {
            log.error("Simulated error log at {}", Instant.now());
        } else if (randomFloat < warnLogRate) {
            log.warn("Simulated warn log at {}", Instant.now());
        } else if (randomFloat < infoLogRate){
            log.info("Simulated info log at {}", Instant.now());
        }
    }
}
