package com.petstore.data.model;

import javax.persistence.*;


@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(nullable = false)
    private String name;

    private Integer age;
    private String color;


    private Gender petSex;

    @Column(unique = true)
    private String breed;

}
