package com.taskflow.example.factory;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class AppSeeder {
    private final TagSeeder tagSeeder;

    public AppSeeder(TagSeeder tagSeeder) {
        this.tagSeeder = tagSeeder;
    }

    @PostConstruct
    public void init(){
        tagSeeder.seed();
    }
}
