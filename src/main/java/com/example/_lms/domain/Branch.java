package com.example._lms.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@Entity
//@NoArgsConstructor
@Table(name = "branch")
public class Branch {

    @Id
    @SequenceGenerator(
            name = "branch_sequence",
            sequenceName = "branch_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "branch_sequence"
    )
    private Long id;

//    @NotNull(message = "branch name must not be null")
    private String name;

//    @NotNull(message = "branch code must not be null")
    private String code;

//    @NotNull(message = "street must not be null")
    private String street;

//    @NotNull(message = "zip_code must not be null")
    private String zip_code;

//    @NotNull(message = "zip_code must not be null")
    private String contact;

    @Transient
    private Country countryObject;
    private Long country;

    @Transient
    private State stateObject;
    private Long state;

    @Transient
    private City cityObject;
    private Long city;

    public Branch(String name, String code, String street, String zip_code, String contact, Country countryObject, Long country, State stateObject, Long state, City cityObject, Long city) {
        this.name = name;
        this.code = code;
        this.street = street;
        this.zip_code = zip_code;
        this.contact = contact;
        this.countryObject = countryObject;
        this.country = country;
        this.stateObject = stateObject;
        this.state = state;
        this.cityObject = cityObject;
        this.city = city;
    }
}
