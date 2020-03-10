package com.example.demo.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by abdoli on 2/27/2020.
 */

@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    @Transient
    private String passwordConfirm;
    @ManyToMany
    private Set<Roles> roles ;

    public User() {
    }

    public User(String username, String password, String passwordConfirm, Set<Roles> roles) {
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }
}
