package com.valiaho.Domain.ErrorCodes;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by akivv on 15.2.2016.
 */
@Component
public class ErrorCodes {
    public Map<Integer, String> mapOfErrorCodes = new HashMap<>();

    public ErrorCodes() {
        //These could be given from the classpath as environment
        //variables
        mapOfErrorCodes.put(404, "Requested resource has no mapping");
    }
    public String findErrorCodeString(Integer i) {
        if (mapOfErrorCodes.containsKey(i)) {
            return mapOfErrorCodes.get(i);
        }
        return null;
    }
}
