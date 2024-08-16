package com.wba;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import groovyjarjarantlr4.v4.codegen.model.ArgAction;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ComplexArrayAgain {
    public static void main(String[] args) throws IOException {
        ArrayList<String> keys = new ArrayList<>();
        ArrayList<Object> values = new ArrayList<>();
        File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\complexJsonArray.json");
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String,Object> payload = objectMapper.readValue(file, new TypeReference<HashMap<String,Object>>() {});
        printAllKeyValues(payload,keys,values);
        System.out.println(keys);
        System.out.println(values);
    }

    private static void printAllKeyValues(HashMap<String, Object> payload,ArrayList<String> keyss,ArrayList<Object> values) {
        payload.forEach((key,value)->{
            if(value instanceof LinkedHashMap)
                printAllKeyValues((LinkedHashMap)value,keyss,values);
            else if(value instanceof ArrayList){
                ((ArrayList<?>) value).forEach(keys->{
                    if(keys instanceof LinkedHashMap)
                        printAllKeyValues((LinkedHashMap)keys,keyss,values);
                });
            }else{
                values.add(value);
            }
            keyss.add(key);
        });
    }


}
