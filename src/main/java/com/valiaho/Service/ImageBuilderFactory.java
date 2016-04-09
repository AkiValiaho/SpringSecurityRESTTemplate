package com.valiaho.Service;

import com.valiaho.Domain.Builders.PersistImageObjectBuilder;

/**
 * Created by akivv on 8.4.2016.
 */
public class ImageBuilderFactory {
    public static PersistImageObjectBuilder buildPersistImageObjectBuilder() {
        return new PersistImageObjectBuilder();
    }
}
