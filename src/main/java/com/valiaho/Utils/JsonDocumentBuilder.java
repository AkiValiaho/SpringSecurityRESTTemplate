package com.valiaho.Utils;

import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.repository.annotation.Id;
import org.springframework.stereotype.Component;

/**
 * Created by akivv on 20.3.2016.
 */
@Component
public class JsonDocumentBuilder {
    private final JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
    private Object objectToBuildDocumentFrom;

    public void setObject(Object object) {
        this.objectToBuildDocumentFrom = object;
    }

    public JsonDocument build() throws IllegalAccessException {
        //Implement builder from object details
        String id = AnnotationTool.findPropertyWithAnnotation(objectToBuildDocumentFrom, Id.class);
        final JsonObject jsonObject = jsonObjectBuilder.buildJsonObject();
        JsonDocument jsonDocument = JsonDocument.create(id, jsonObject);
        return jsonDocument;
    }

    private JsonObject buildJsonObject() {
        jsonObjectBuilder.setObject(objectToBuildDocumentFrom);
        return jsonObjectBuilder.buildJsonObject();
    }
}
