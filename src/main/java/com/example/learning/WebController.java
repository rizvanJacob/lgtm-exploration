package com.example.learning;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    private final int maxWaitDurationSeconds = 10;

    @GetMapping("/wait/:waitDurationSeconds")
    public ResponseEntity<String> simulateRequest(@RequestParam int waitDurationSeconds) {
        var waitDurationMillis = Math.min(waitDurationSeconds, maxWaitDurationSeconds) * 1000;
        try {
            Thread.sleep(waitDurationMillis);
            return ResponseEntity.ok(String.format("Completed request in %d seconds", waitDurationMillis / 1000));
        } catch (InterruptedException e) {
            return ResponseEntity.badRequest()
                    .body("Could not complete request!");
        }
    }
}
