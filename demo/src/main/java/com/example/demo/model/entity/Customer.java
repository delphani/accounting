package com.example.demo.model.entity;

import javax.persistence.*;

/**
 * Created by abdoli on 3/2/2020.
 */
@Entity
@Table
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String family;
    private String natinalcode;
    private int age;

    @OneToOne
    private Adress adress;
    @OneToOne
    private Branche branche;

    public Customer() {
    }

    public Customer(String name, String family, String natinalcode, int age, Adress adress, Branche branche) {
        this.name = name;
        this.family = family;
        this.natinalcode = natinalcode;
        this.age = age;
        this.adress = adress;
        this.branche = branche;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getNatinalcode() {
        return natinalcode;
    }

    public void setNatinalcode(String natinalcode) {
        this.natinalcode = natinalcode;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public Branche getBranche() {
        return branche;
    }

    public void setBranche(Branche branche) {
        this.branche = branche;
    }
}
