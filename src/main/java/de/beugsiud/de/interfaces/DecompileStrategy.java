package de.beugsiud.de.interfaces;

import java.util.Map;

// Strategy Pattern für verschiedene Decompile-Methoden
public interface DecompileStrategy {
    void processLine(String line, Map<String, String> contentMap);
    void displaySingleValue(String key, String value);
    void processContent(Map<String, String> contentMap);
}
