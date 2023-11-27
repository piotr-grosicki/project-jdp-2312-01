package com.kodilla.ecommercee.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String value;

    public Product() {
    }

    public long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public Product(String value) {
        this.value = value;
    }
}
