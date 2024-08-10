package com.java.ivtmg.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
    @Id
    private Long id;
    
    private String name;
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ivtmg_id", nullable = false)
    @JsonIgnore
    private Ivtmg ivtmg;

    // Constructors
    public Product() {
    }

    public Product(String name, double price, Ivtmg ivtmg) {
        this.name = name;
        this.price = price;
        this.ivtmg = ivtmg;
    }

    // Getters and setters
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Ivtmg getIvtmg() {
        return ivtmg;
    }

    public void setIvtmg(Ivtmg ivtmg) {
        this.ivtmg = ivtmg;
    }
}
