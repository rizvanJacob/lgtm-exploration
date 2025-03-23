package com.example.learning;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class MockEntityService {
    private final Random random = new Random();
    private final MockEntityRepository repository;

    @Transactional
    public MockEntity createNew(String text) {
        log.info("Simulating create entity: {}", text);
        simulateLatency(500);
        var entity = new MockEntity();
        entity.setText(text);
        return repository.save(entity);
    }

    @Transactional
    public MockEntity findById(Long id) {
        log.info("Simulating find entity by id ({})", id);
        simulateLatency(200);
        return repository.findById(id)
                .orElseThrow();
    }

    @Transactional
    public List<MockEntity> findAll() {
        log.info("Simulating find all entities");
        simulateLatency(1000);
        return repository.findAll();
    }

    @Transactional
    public MockEntity updateById(Long id, String text) {
        log.info("Simulating update entity by id ({}): {}", id, text);
        simulateLatency(500);
        var entity = repository.findById(id)
                .orElseThrow();
        entity.setText(text);
        return entity;
    }

    @Transactional
    public void deleteById(Long id) {
        log.info("Simulating delete entity by id ({})", id);
        simulateLatency(300);
        repository.deleteById(id);
    }

    private void simulateLatency(int latencyMs) {
        try {
            int minLatency = Math.min(0, latencyMs * 8 / 10);
            int maxLatency = latencyMs * 12 / 10 + 1;
            int distributedLatency = random.nextInt(minLatency, maxLatency);
            log.info("Simulating repository latency of {} ms", distributedLatency);
            Thread.sleep(distributedLatency);
        } catch (InterruptedException ex) {
            log.error("Interrupted when simulating latency", ex);
        }
    }
}
