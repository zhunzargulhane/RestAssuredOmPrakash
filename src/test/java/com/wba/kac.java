package com.wba;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wba.pojo.workspace.WorkspaceNestedPOJO;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class kac {
    public static void main(String[] args) throws IOException {
        String fileName = "";
        InputStream inputStream1 = kac.class.getClassLoader().getResourceAsStream(fileName);
        ObjectMapper objectMapper1 = new ObjectMapper();
        WorkspaceNestedPOJO workspacePOJO = objectMapper1.readValue(new File(fileName),WorkspaceNestedPOJO.class);
        workspacePOJO.getName();
    }
}
