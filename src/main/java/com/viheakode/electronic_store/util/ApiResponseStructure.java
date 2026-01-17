package com.viheakode.electronic_store.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class ApiResponseStructure {

    public static ResponseEntity<Object> response(boolean success, int status, String msg, Object data, String path, HttpStatus httpStatus){
        Map<String, Object> stringMap = new HashMap<>();
        stringMap.put("timestamp", Instant.now());
        stringMap.put("success", success);
        stringMap.put("status", status);
        stringMap.put("data", data);
        stringMap.put("message", msg);
        stringMap.put("path", path);
        return new ResponseEntity<>(stringMap, httpStatus);
    }
}
