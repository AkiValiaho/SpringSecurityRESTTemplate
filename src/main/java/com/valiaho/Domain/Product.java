package com.valiaho.Domain;

import javax.persistence.*;

/**
 * Created by akivv on 7.3.2016.
 */
@Entity
@Table(name = "products")
public class Product {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Column(name = "productName", length = 500)
    private String name;

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
}
