package com.samm.trippytravel.repository;

import com.samm.trippytravel.data.model.ERole;
import com.samm.trippytravel.data.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
