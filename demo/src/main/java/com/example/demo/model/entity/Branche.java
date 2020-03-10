package com.example.demo.model.entity;

import javax.persistence.*;

/**
 * Created by abdoli on 3/2/2020.
 */

@Entity
@Table
public class Branche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String branchcode;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Adress adress;

    public Branche() {
    }

    public Branche(Long id, String name, String branchcode, Adress adress) {
        this.id = id;
        this.name = name;
        this.branchcode = branchcode;
        this.adress = adress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranchcode() {
        return branchcode;
    }

    public void setBranchcode(String branchcode) {
        this.branchcode = branchcode;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }
}
