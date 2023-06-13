package com.epam.mjc.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class FileReader {

    private Map<String, String> parseData(String data) {
        Map<String, String> keyValuePairs = new HashMap<>();
        String[] lines = data.split("\n");
        for (String line : lines) {
            String[] parts = line.trim().split(":");
            if (parts.length == 2) {
                keyValuePairs.put(parts[0].trim(), parts[1].trim());
            }
        }
        return keyValuePairs;
    }

    public String getString(File file) {
        StringBuilder data = new StringBuilder();

        try (FileInputStream fileInputStream = new FileInputStream(file.getPath())) {
            int c;
            while((c = fileInputStream.read()) != -1){
                data.append((char)c);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return new String(data);
    }

    public Profile getDataFromFile(File file) {
        String data = getString(file);
        Map<String, String> keyValuePairs = parseData(data);
        String name = keyValuePairs.get("Name");
        int age = Integer.parseInt(keyValuePairs.get("Age"));
        String email = keyValuePairs.get("Email");
        Long phone = Long.parseLong(keyValuePairs.get("Age"));
        return new Profile(name, age, email, phone);
    }
}