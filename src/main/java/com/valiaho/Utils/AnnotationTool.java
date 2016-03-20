package com.valiaho.Utils;

import com.couchbase.client.java.repository.annotation.Id;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by akivv on 20.3.2016.
 */
public class AnnotationTool {
    public static String findPropertyWithAnnotation(Object objectToBuildDocumentFrom, Class<Id> idClass) throws IllegalAccessException {
        for (Field field : objectToBuildDocumentFrom.getClass().getDeclaredFields()) {
            final Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
            for (Annotation annotation : declaredAnnotations) {
                if (annotation.annotationType().equals(idClass)) {
                    field.setAccessible(true);
                    return String.valueOf(field.get(objectToBuildDocumentFrom));
                }
            }
        }
        return null;
    }

}
