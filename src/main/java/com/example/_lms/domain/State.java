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
@Table(name = "state")
public class State {
    @Id
    @SequenceGenerator(
            name = "grieve_sequence",
            sequenceName = "grieve_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "grieve_sequence"
    )
    private Long id;

//    @NotNull(message = "state name must not be null")
    private String name;

    @Transient
    private Country countryObject;
    private long country;

}
