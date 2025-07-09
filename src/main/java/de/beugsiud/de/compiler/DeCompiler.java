package de.beugsiud.de.compiler;

import de.beugsiud.de.exceptions.DecompileException;
import de.beugsiud.de.interfaces.DecompileStrategy;
import de.beugsiud.de.standarts.DefaultDecompileStrategy;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DeCompiler {
    private String fileName;
    private final Map<String, String> contentMap;
    private DecompileStrategy strategy;

    public DeCompiler(String fileName) {
        this.fileName = fileName;
        this.contentMap = new HashMap<>();
        this.strategy = new DefaultDecompileStrategy();
    }

    public void setStrategy(DecompileStrategy strategy) {
        this.strategy = strategy;
    }

    public void decompile() throws IOException {
        System.out.println("Decompiling file: " + fileName);
        try {
            readFile();
            processContent();
        } catch (IOException e) {
            throw new DecompileException("Error during decompilation", e);
        }
    }

    private void readFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                strategy.processLine(line, contentMap);
            }
        }
    }
    public void displayValueForKey(String key) {
        if (contentMap.containsKey(key)) {
            strategy.displaySingleValue(key, contentMap.get(key));
        } else {
            System.out.println("Key '" + key + "' not found.");
        }
    }


    private void processContent() {
        strategy.processContent(contentMap);
    }

    public Map<String, String> getDecompiledContent() {
        return new HashMap<>(contentMap);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

