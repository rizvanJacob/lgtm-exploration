package com.example.learning;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class MockEntityController {
    private final Random random = new Random();
    private final MockEntityService service;

    public MockEntityController(MockEntityService service, ScheduledExecutorService executor) {
        this.service = service;
        executor.scheduleAtFixedRate(this::simulateCrud, 0, 100, TimeUnit.MILLISECONDS);
    }

    public void simulateCrud() {
        var entities = service.findAll();
        if (entities.isEmpty()) {
            service.createNew("First Entity");
            return;
        }
        int option = random.nextInt(4);
        long idToGet = entities.get(random.nextInt(entities.size()))
                .getId();
        try {
            switch (option) {
                case 0 -> service.createNew("New Entity");
                case 1 -> service.updateById(idToGet, "Updated Entity");
                case 2 -> service.findById(idToGet);
                case 3 -> service.deleteById(idToGet);
            }
        } catch (NoSuchElementException ex) {
            log.error("Failed to perform CRUD operation ", ex);
        }
    }
}
