package com.example.demo.model.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by abdoli on 2/27/2020.
 */

@Entity
@Table
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToMany(mappedBy ="roles" )
    private Set<User> users;

    public Roles() {
    }

    public Roles(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
