package com.microservices.users.domain.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "phones")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false)
    @NotNull
    @Pattern(regexp = "\\d+", message = "Number must contain only digits")
    private String number;

    @Column(name = "countryCode", nullable = false)
    @NotNull
    @Pattern(regexp = "\\d+", message = "Country code must contain only digits")
    private String countryCode;

    @Column(name = "cityCode", nullable = false)
    @NotNull
    @Pattern(regexp = "\\d+", message = "City code must contain only digits")
    private String cityCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
