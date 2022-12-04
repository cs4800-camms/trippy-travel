package com.samm.trippytravel.data.model;

import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Value
@Document("roles")
public class Role {
    @Id
    String _id;
    ERole name;
}
