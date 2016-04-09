package com.valiaho.Tests;

import com.couchbase.client.java.repository.annotation.Id;
import com.valiaho.Domain.Product;
import com.valiaho.Utils.AnnotationTool;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * Created by akivv on 20.3.2016.
 */
public class AnnotationToolTest {

    private AnnotationTool annotation;

    @Before
    public void setUp() throws Exception {
        annotation = new AnnotationTool();
    }

    @Test
    public void testFindPropertyWithAnnotation() throws Exception {
        Product product = new Product();
        product.setId(new Long("123"));
        product.setName("Hello World");
        final String propertyWithAnnotation = AnnotationTool.findPropertyWithAnnotation(product, Id.class);
        assert propertyWithAnnotation != null;
        if (!propertyWithAnnotation.equals("123")) {
            fail("Property is not right");
        }
    }


}