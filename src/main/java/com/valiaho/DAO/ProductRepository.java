package com.valiaho.DAO;

import com.sun.org.apache.xpath.internal.operations.String;
import com.valiaho.Domain.Product;
import org.springframework.stereotype.Repository;

/**
 * Created by akivv on 9.4.2016.
 */
@Repository
public interface ProductRepository extends BaseRepository<Product, String> {

}
