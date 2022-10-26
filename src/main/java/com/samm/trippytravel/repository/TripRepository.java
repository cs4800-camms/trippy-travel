package com.samm.trippytravel.repository;

import com.samm.trippytravel.data.domain.Trip;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends MongoRepository<Trip, Long> {
    @Query(value="{'_id' : ?0}", delete = true)
    Trip deleteById(String id);
}