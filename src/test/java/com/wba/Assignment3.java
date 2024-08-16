package com.wba;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import com.fasterxml.jackson.databind.node.TextNode;
import com.jayway.jsonpath.JsonPath;
import com.sun.javafx.collections.MappingChange;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;
import sun.awt.image.ImageWatched;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Assignment3 {
    public static List<String> keys = new ArrayList<>();
    public static List<Object> values = new ArrayList<>();
    @Test
    public void collectKeysAndValues() throws IOException {
       /* File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\assignment3.json");
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> array = objectMapper.readValue(file, new TypeReference<List<Map<String, Object>>>() {
        });
        for(Map<String,Object> treeMap: array){
            find(treeMap);
        }
        System.out.println(keys);
        System.out.println(values);*/
        File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\assignment3.json");
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> array = mapper.readValue(file, new TypeReference<List<Map<String, Object>>>(){});
        for (Map<String, Object> treeMap : array) {
            find(treeMap);
        }
        System.out.println(keys);
        System.out.println(values);
    }

    public static void find(Map<String,Object> treeMap){
       treeMap.forEach((k,v)->{
           if(v instanceof LinkedHashMap){
               find((LinkedHashMap) v);
           }else{
               values.add(v);
           }
           keys.add(k);
       });

    }
}
