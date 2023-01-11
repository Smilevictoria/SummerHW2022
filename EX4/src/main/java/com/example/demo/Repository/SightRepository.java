package com.example.demo.Repository;
import org.springframework.stereotype.Repository;
import com.example.demo.sighter.Sight;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SightRepository extends MongoRepository<Sight, String> {
    List<Sight> findByZoneLike(String zone);
}