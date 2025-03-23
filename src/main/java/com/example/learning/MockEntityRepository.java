package com.example.learning;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MockEntityRepository extends JpaRepository<MockEntity, Long> {
}
