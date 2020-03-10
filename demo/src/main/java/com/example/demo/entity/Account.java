package com.example.demo.entity;

import javax.persistence.*;

/**
 * Created by abdoli on 3/2/2020.
 */
@Entity
@Table
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToOne
    private Customer customer;
    @OneToOne
    private Branche branche;
    private String balance;

    public Account() {
    }

    public Account(String name, Customer customer, Branche branche, String balance) {
        this.name = name;
        this.customer = customer;
        this.branche = branche;
        this.balance = balance;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Branche getBranche() {
        return branche;
    }

    public void setBranche(Branche branche) {
        this.branche = branche;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
