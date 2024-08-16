package com.wba;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FetchAllKeyValues {
    static ArrayList<String> keys = new ArrayList<>();
    static ArrayList<Object> values = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        List<HashMap<String, Object>> jsonPayload = objectMapper.readValue(new File("src\\main\\resources\\FetchKeyvaluePairs.json"), new TypeReference<List<HashMap<String, Object>>>() {
        });
        for (Map<String, Object> objectMap : jsonPayload) {
            printAllKeyValuePair(objectMap);
        }
        System.out.println(keys);
        System.out.println(values);
    }

    private static void printAllKeyValuePair(Map<String, Object> jsonPayload) {
        jsonPayload.forEach((key,value)->{
            if(value instanceof LinkedHashMap){
                printAllKeyValuePair((LinkedHashMap) value);
            }else if(value instanceof ArrayList){
                ((ArrayList<?>) value).forEach(eachKey->{
                    if(eachKey instanceof LinkedHashMap)
                        printAllKeyValuePair((LinkedHashMap)eachKey);
                    else if(eachKey instanceof Integer)
                        values.add(eachKey);
                });
            }else{
                values.add(value);
            }
            keys.add(key);
        });
    }
}
