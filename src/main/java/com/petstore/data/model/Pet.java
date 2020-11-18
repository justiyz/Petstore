package com.petstore.data.model;



import lombok.Data;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Data
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(nullable = false)
    private String name;

    private Integer age;

    private String color;

    @Column
    private String breed;

    @Enumerated(EnumType.STRING)
    private Gender petSex;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private Store store;

}
