package com.valiaho.Domain;

import com.couchbase.client.java.repository.annotation.Id;

/**
 * Created by akivv on 7.3.2016.
 */
public class Product {
    @Id
    private Long id;
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
