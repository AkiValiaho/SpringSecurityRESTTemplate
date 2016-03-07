package com.valiaho.Repository;

import com.valiaho.Domain.Product;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by akivv on 7.3.2016.
 */
@Repository
public interface ProductRepository extends BaseRepository<Product,Long> {
    public Optional<Product> findByname(String name);
    public void deleteByname(String name);
}
