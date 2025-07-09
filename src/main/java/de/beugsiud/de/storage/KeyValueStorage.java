package de.beugsiud.de.storage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class KeyValueStorage {
    private Map<String, String> storage;
    
    public KeyValueStorage() {
        this.storage = new HashMap<>();
    }
    
    public void loadFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    storage.put(parts[0].trim(), parts[1].trim());
                }
            }
        }
    }
    
    public String getValue(String key) {
        return storage.get(key);
    }
    
    public void setValue(String key, String value) {
        storage.put(key, value);
    }
    
    public void saveToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, String> entry : storage.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
        }
    }
    
    public Map<String, String> getAllEntries() {
        return new HashMap<>(storage);
    }
}