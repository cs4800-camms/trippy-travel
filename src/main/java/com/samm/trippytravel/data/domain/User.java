package com.samm.trippytravel.data.domain;

import com.samm.trippytravel.data.model.Role;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Value
@Builder
@Document("users")
public class User {
    @Id
    String _id;
    String firstName;
    String lastName;
    String username;
    @Email
    String email;
    String password;
    @DBRef
    Set<Role> roles;

    public static User getUsers(User user) {
        return User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }
}
