package com.samm.trippytravel.repository;

import com.samm.trippytravel.data.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Long> {
    @Query(value="{'email' : ?0}")
    Optional<User> findUserByEmail(String email);

    @Query(value="{'username' : ?0}")
    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    @Query(value="{'_id' : ?0}", delete = true)
    User deleteById(String id);
}
