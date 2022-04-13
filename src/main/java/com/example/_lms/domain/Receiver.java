package com.example._lms.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "receiver")
public class Receiver{
    @Id
    @SequenceGenerator(
            name = "receiver_sequence",
            sequenceName = "receiver_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "receiver_sequence"
    )
    private Long id;

//    @NotNull(message = "receiver_number must not be null")
    private String receiver_number;

//    @NotNull(message = "receiver_name must not be null")
    private String name;

//    @NotNull(message = "receiver_address must not be null")
    private String address;

//    @NotNull(message = "receiver_phone_number must not be null")
    private String phone_number;

//    @NotNull(message = "receiver_email must not be null")
    private String email;

    public String getReceiver_number() {
        return receiver_number;
    }

    public void setReceiver_number(String receiver_number) {
        this.receiver_number = receiver_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*@OneToMany(mappedBy = "receiver")*/
    /*private Set<Parcel> parcels = new HashSet<>();*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
