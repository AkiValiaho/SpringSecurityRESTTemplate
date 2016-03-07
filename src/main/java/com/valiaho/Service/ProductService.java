package com.valiaho.Service;

import com.valiaho.Domain.Product;
import com.valiaho.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by akivv on 7.3.2016.
 */
@Service
@Transactional
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public void save(Product product) {
        final Optional<Product> one = findOne(product.getName());
        if (one.isPresent()) {
            //Don't do anything to product repository
            //Could be used for updating with appropriate logic
        } else {
            productRepository.save(product);

        }
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }

    public List<Product> findAll() {
        final List<Product> all = productRepository.findAll();
        return all;
    }

    public Optional<Product> findOne(String username) {
        return productRepository.findByname(username);
    }

    public void deleteByname(String name) {
        productRepository.deleteByname(name);
    }
}
