package com.wba;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Assignment3Practice {
    List<String> keys = new ArrayList<>();
    List<Object> values = new ArrayList<>();

    @Test
    public void collectKeyValues() throws IOException {
        File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\assignment3.json");
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> treeMap = objectMapper.readValue(file, new TypeReference<List<Map<String, Object>>>() {
        });
        for (Map<String, Object> eachMap : treeMap) {
            eachJson(eachMap);
        }
        System.out.println(keys);
        System.out.println(values);
    }

    public void eachJson(Map<String, Object> eachJson) {
        eachJson.forEach((key, value) -> {
            if (value instanceof LinkedHashMap) {
                eachJson((LinkedHashMap) value);
            } else {
                values.add(value);
            }
            keys.add(key);
        });
    }

    @Test
    public void printKeysValuesComplexJsonArray() throws IOException {
        File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\complexJsonArray.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> treeMap = objectMapper.readValue(file, new TypeReference<Map<String, Object>>() {
        });
        getKeyValue(treeMap);
        System.out.println(keys);
        System.out.println(values);
    }

    public void getKeyValue(Map<String, Object> treeMap) {
        treeMap.forEach((key,value)->{
            if(value instanceof LinkedHashMap){
                getKeyValue((LinkedHashMap)value);
            }else if(value instanceof ArrayList){
                ((ArrayList<?>) value).forEach(keys->{
                    if(keys instanceof LinkedHashMap){
                        getKeyValue((LinkedHashMap)keys);
                    }
                });
            }else{
                values.add(value);
            }
            keys.add(key);
        });
    }
}


