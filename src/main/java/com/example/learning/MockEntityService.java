package com.example.learning;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MockEntityService {
    private final MockEntityRepository repository;
    @Transactional
    public MockEntity createNew(String text){
        var entity = new MockEntity();
        entity.setText(text);
        return repository.save(entity);
    }
    @Transactional
    public MockEntity findById(Long id){
        return repository.findById(id)
                .orElseThrow();
    }
    @Transactional
    public List<MockEntity> findAll(){
        return repository.findAll();
    }
    @Transactional
    public MockEntity updateById(Long id, String text){
        var entity = repository.findById(id)
                .orElseThrow();
        entity.setText(text);
        return entity;
    }
    @Transactional
    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
