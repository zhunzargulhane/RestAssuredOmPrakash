package com.wba;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Assignment4 {
    List<String> nameFromLanguage = new ArrayList<>();
    List<String> urlFromLanguage = new ArrayList<>();
    List<String> namesName = new ArrayList<>();
    @Test
    public void collectNames() throws IOException {
        File file = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\assignment4.json");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonObject = objectMapper.readTree(file).toPrettyString();
        JsonPath jsonPath = new JsonPath(jsonObject);
        nameFromLanguage.add(jsonPath.get("names.language.name"));
        urlFromLanguage.add(jsonPath.get("names.language.url"));
        namesName.add(jsonPath.get("names.name"));
        System.out.println(nameFromLanguage);
        System.out.println(urlFromLanguage);
        System.out.println(namesName);
    }
}
