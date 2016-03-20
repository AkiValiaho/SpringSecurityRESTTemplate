package com.valiaho.Utils;

import com.couchbase.client.java.document.json.JsonObject;
import com.google.gson.Gson;

public class JsonObjectBuilder {

    private Object objectToBuildJsonFrom;


    JsonObject buildJsonObject() {
        Gson gson = new Gson();
        return JsonObject.fromJson(gson.toJson((objectToBuildJsonFrom)));
    }

    public void setObject(Object objectToBuildDocumentFrom) {
        this.objectToBuildJsonFrom = objectToBuildDocumentFrom;
    }
}