package com.theironyard;

import javax.persistence.*;

/**
 * Created by branden on 3/9/16 at 11:13.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false, unique = true)
    String name;


    public User(String name) {
        this.name = name;
    }

    public User() {
    }
}