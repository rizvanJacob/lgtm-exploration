package com.example.learning;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class MockEntityService {
    private final MockEntityRepository repository;
    @Transactional
    public MockEntity createNew(String text){
        log.info("Simulating create entity: {}", text);
        var entity = new MockEntity();
        entity.setText(text);
        return repository.save(entity);
    }
    @Transactional
    public MockEntity findById(Long id){
        log.info("Simulating find entity by id ({})", id);
        return repository.findById(id)
                .orElseThrow();
    }
    @Transactional
    public List<MockEntity> findAll(){
        log.info("Simulating find all entities");
        return repository.findAll();
    }
    @Transactional
    public MockEntity updateById(Long id, String text){
        log.info("Simulating update entity by id ({}): {}", id, text);
        var entity = repository.findById(id)
                .orElseThrow();
        entity.setText(text);
        return entity;
    }
    @Transactional
    public void deleteById(Long id){
        log.info("Simulating delete entity by id ({})", id);
        repository.deleteById(id);
    }
}
