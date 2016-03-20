package com.valiaho.DAO;

import com.couchbase.client.java.Bucket;
import com.valiaho.Domain.Product;
import com.valiaho.Utils.ConnectionManager;
import com.valiaho.Utils.JsonDocumentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by akivv on 20.3.2016.
 */
@Component
@ComponentScan(basePackages = "com.valiaho.Utils.")
public class ProductServiceDAO {

    private Bucket other;

    @Autowired
    JsonDocumentBuilder jsonDocumentBuilder;
    @PostConstruct
    public void init() {
        other = ConnectionManager.getMainInstance();
    }

    //Persist functionality
    public Product persist(Product product) throws IllegalAccessException {
        jsonDocumentBuilder.setObject(product);
        other.upsert(jsonDocumentBuilder.build());
        return product;
    }
}
