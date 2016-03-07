package com.valiaho.Controller;

import com.valiaho.Domain.Product;
import com.valiaho.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by akivv on 7.3.2016.
 */
@RestController
@ComponentScan(basePackages = "com.valiaho.Service")
public class ProductRepositoryController {
    @Autowired
    ProductService productService;

    @RequestMapping(value = "/productRepository", method = RequestMethod.GET)
    public List<Product> productsCurrentlyInRepository() {
        return productService.findAll();
    }

    @RequestMapping(value = "/productRepository", method = RequestMethod.POST)
    public void createProductToRepository(@RequestBody Product product) {
        productService.save(product);
    }

    @RequestMapping(value = "/productRepository/delete", method = RequestMethod.POST)
    public void deleteProductFromRepository(@RequestBody Product product) {
        productService.deleteByname(product.getName());
    }
}
