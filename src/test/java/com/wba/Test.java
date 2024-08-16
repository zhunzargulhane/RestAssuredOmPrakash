package com.wba;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import sun.awt.image.ImageWatched;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Test {
    static List<Object> keys = new ArrayList<>();
    static List<Object> values = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<LinkedHashMap<String, Object>> pairs = objectMapper.readValue(new File("src\\main\\resources\\FetchKeyvaluePairs.json"),
                new TypeReference<List<LinkedHashMap<String, Object>>>() {
                });
        for (LinkedHashMap<String, Object> linkedHashMap : pairs) {
            printKeys(linkedHashMap);
        }
        System.out.println(keys);
        System.out.println(values);
    }

    private static void printKeys(LinkedHashMap<String, Object> linkedHashMap) {
        linkedHashMap.forEach((key, value) -> {
            if (value instanceof LinkedHashMap) {
                printKeys((LinkedHashMap) value);
            } else if (value instanceof ArrayList) {
                ((ArrayList<?>) value).forEach(val -> {
                    if (val instanceof LinkedHashMap)
                        printKeys((LinkedHashMap<String, Object>) val);
                    else
                        values.add(val);
                });
            } else {
                values.add(value);
            }
            keys.add(key);
        });

    }
}
