package com.example._lms.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "parcel")
public class Parcel{
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

//    @NotNull(message = "reference_number must not be null")
    private String reference_number;

//    @NotNull(message = "weight must not be null")
    private String weight;

//    @NotNull(message = "height must not be null")
    private String height;

//    @NotNull(message = "length must not be null")
    private String length;

//    @NotNull(message = "width must not be null")
    private String width;

//    @NotNull(message = "price must not be null")
    private String price;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Transient
    private Sender senderObject;
    private Long sender;

    @Transient
    private Receiver receiverObject;
    private Long receiver;

    @Transient
    private Branch fromBranchObject;
    private Long fromBranch;

    @Transient
    private Branch toBranchObject;
    private Long toBranch;

    public Parcel(String reference_number, String weight, String height, String length, String width, String price, Status status, Sender senderObject, Long sender, Receiver receiverObject, Long receiver, Branch fromBranchObject, Long fromBranch, Branch toBranchObject, Long toBranch) {
        this.reference_number = reference_number;
        this.weight = weight;
        this.height = height;
        this.length = length;
        this.width = width;
        this.price = price;
        this.status = status;
        this.senderObject = senderObject;
        this.sender = sender;
        this.receiverObject = receiverObject;
        this.receiver = receiver;
        this.fromBranchObject = fromBranchObject;
        this.fromBranch = fromBranch;
        this.toBranchObject = toBranchObject;
        this.toBranch = toBranch;
    }
}
