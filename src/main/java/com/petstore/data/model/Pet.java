package com.petstore.data.model;



import lombok.Data;
import lombok.ToString;

import javax.persistence.*;


@Entity //to create a table in the db with the attributes
@Data //to add all fields of the attributes i.e setters, getters, constructors, toString etc.
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

    @Enumerated(EnumType.STRING) //to indicate in the db that this attribute is an ENUM type
    private Gender petSex;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @ToString.Exclude //this tells the jvm not to output the toString method of the class Store.
    private Store store;

}
