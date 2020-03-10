package com.example.demo.entity;

import javax.persistence.*;

/**
 * Created by abdoli on 3/2/2020.
 */
@Table
@Entity
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String province;
    private String city;
    private String street;
    private String pelak;

    public Adress() {
    }

    public Adress(String province, String city, String street, String pelak) {
        this.province = province;
        this.city = city;
        this.street = street;
        this.pelak = pelak;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPelak() {
        return pelak;
    }

    public void setPelak(String pelak) {
        this.pelak = pelak;
    }
}
