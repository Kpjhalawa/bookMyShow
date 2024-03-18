package com.example.demo.model.classes;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Movie extends BaseModel{
    private String name;
    private String langauge;
    private String rating;
}








