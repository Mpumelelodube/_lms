package com.example._lms.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "sender")
public class Sender{

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

//    @NotNull(message = "sender_number must not be null")
    private String sender_number;

//    @NotNull(message = "sender_name must not be null")
    private String name;

//    @NotNull(message = "sender_address must not be null")
    private String address;

//    @NotNull(message = "sender_phone_number must not be null")
    private String phone_number;

//    @NotNull(message = "sender_email must not be null")
    private String email;

    /*@OneToMany(mappedBy = "receiver")*/
   /* private Set<Parcel> parcels = new HashSet<>();*/

}
