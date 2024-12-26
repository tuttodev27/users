package com.microservices.users.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue
    UUID id;
    @Column(name = "name", nullable = false)
    String name;
    @Column(name="email", nullable = false)
    String email;
    @Column(name="password", nullable = false)
    String password;
    @Column (name="created", nullable = false)
    LocalDateTime created;
    @Column (name = "modified", nullable = false)
    LocalDateTime modified;
    @Column (name = "lastLogin")
    LocalDateTime lastLogin;
    @Column(name = "token")
     @JsonIgnore
    String token;
    boolean isActive;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Phone> phones;


}
