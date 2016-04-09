package com.valiaho.Service;

import com.valiaho.DAO.ProductRepository;
import com.valiaho.Domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by akivv on 7.3.2016.
 */
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public void save(Product product) {
        final Optional<Product> one = findOne(product.getName());
        if (one.isPresent()) {
            //Don't do anything to product repository
            //Could be used for updating with appropriate logic
        } else {

        }
    }

    public void delete(Product product) {

    }

    public List<Product> findAll() {
        return null;
    }

    public Optional<Product> findOne(String username) {
        return null;
    }

    public void deleteByname(String name) {

    }
}
