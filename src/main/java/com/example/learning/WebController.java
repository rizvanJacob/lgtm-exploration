package com.example.learning;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RequestMapping("/wait")
@RestController
@Slf4j
public class WebController {
    private static final float badRequestRate = 0.01f,
            internalServerErrorRate = 0.001f;
    private static final int maxWaitTimeMillis = 5000;
    private final Random random = new Random();

    @GetMapping("/{durationMillis}")
    public ResponseEntity<String> simulateGetById(@PathVariable int durationMillis) {
        log.info("Simulating get entity by ID");
        return wait(durationMillis);
    }

    @GetMapping()
    public ResponseEntity<String> simulateIndex() {
        log.info("Simulating get all entities");
        return wait(random.nextInt(maxWaitTimeMillis * 4 / 5, maxWaitTimeMillis));
    }
    @PostMapping()
    public ResponseEntity<String> simulatePostRequest() {
        log.info("Simulating create new entity");
        return wait(random.nextInt(maxWaitTimeMillis / 5, maxWaitTimeMillis * 2 / 5));
    }

    @PutMapping("/{durationMillis}")
    public ResponseEntity<String> simulateUpdateById(@PathVariable int durationMillis) {
        log.info("Simulating update entity by ID");
        return wait(durationMillis);
    }

    @DeleteMapping("/{durationMillis}")
    public ResponseEntity<String> simulateDeleteById(@PathVariable int durationMillis) {
        log.info("Simulating delete entity by ID");
        return wait(durationMillis);
    }

    private ResponseEntity<String> wait(int durationMillis) {
        var randomFloat = random.nextFloat();
        if (randomFloat < internalServerErrorRate) {
            return ResponseEntity.internalServerError()
                    .body("Simulated Internal Server Error");
        }
        if (randomFloat < badRequestRate) {
            return ResponseEntity.badRequest()
                    .body("Simulated Bad Request");
        }

        var waitDurationMillis = Math.min(durationMillis, maxWaitTimeMillis);
        try {
            Thread.sleep(waitDurationMillis);
            return ResponseEntity.ok(String.format("Completed request in %f seconds", (float) waitDurationMillis / 1000));
        } catch (InterruptedException e) {
            return ResponseEntity.badRequest().body("Thread was interrupted while awaiting request completion");
        }
    }
}
