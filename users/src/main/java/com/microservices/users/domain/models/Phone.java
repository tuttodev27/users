
package com.microservices.users.domain.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "number", nullable = false)
    String number;

    @Column(name = "country_code", nullable = false)
    String countryCode;

    @Column(name = "city_code", nullable = false)
    String cityCode;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;
}

