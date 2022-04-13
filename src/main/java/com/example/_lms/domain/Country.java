package com.example._lms.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "country")
public class Country{

    @Id
    @SequenceGenerator(
            name = "country_sequence",
            sequenceName = "country_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "country_sequence"
    )
    private Long id;

//    @NotNull(message = "country name must not be null")
    private String name;


 /*   @OneToMany(mappedBy = "country",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<City> cities= new HashSet<>();

   */

//    @OneToMany(mappedBy="country")
//    private List<Branch> branches;

//private Set<State> states = new HashSet<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

/*    @OneToMany(mappedBy = "country",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    public Set<State> getStates() {
        return states;
    }

    public void setStates(Set<State> states) {
        this.states = states;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
