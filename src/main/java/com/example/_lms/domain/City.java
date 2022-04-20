package com.example._lms.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "city")
public class City {
    @Id
    @SequenceGenerator(
            name = "city_sequence",
            sequenceName = "city_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "city_sequence"
    )
    private Long id;

//    @NotNull(message = "city name must not be null")
    private String name;

    @Transient
    private Country countryObject;
    private Long country;

    public City(String name, Country countryObject, Long country) {
        this.name = name;
        this.countryObject = countryObject;
        this.country = country;
    }
}
