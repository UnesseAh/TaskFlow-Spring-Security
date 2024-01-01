package com.taskflow.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class AppRole {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
//    @ManyToMany(mappedBy = "roles")
//    private List<User> users;

}
