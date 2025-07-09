package de.beugsiud.de.standarts;
import de.beugsiud.de.interfaces.DecompileStrategy;

import java.util.*;

public class DefaultDecompileStrategy implements DecompileStrategy {
    private static final int MIN_COLUMN_WIDTH = 10;
    private final List<String> keyOrder = new ArrayList<>();

    @Override
    public void processLine(String line, Map<String, String> contentMap) {
        int indent = 0;
        while (indent < line.length() && line.charAt(indent) == ' ') {
            indent++;
        }

        String trimmedLine = line.trim();
        String[] parts = trimmedLine.split(":", 2);

        if (parts.length == 2) {
            String key = parts[0].trim();
            String value = parts[1].trim();
            String displayKey = " ".repeat(indent) + key;

            if (!contentMap.containsKey(key)) {
                keyOrder.add(key);
            }
            contentMap.put(key, value);
            contentMap.put("__display_" + key, displayKey);
        }
    }

    @Override
    public void processContent(Map<String, String> contentMap) {
        if (contentMap.isEmpty()) {
            System.out.println("Keine Daten zum Anzeigen verfügbar.");
            return;
        }

        int maxKeyLength = Math.max(
                keyOrder.stream()
                        .map(k -> {
                            String displayKey = contentMap.get("__display_" + k);
                            return displayKey != null ? displayKey.length() : k.length();
                        })
                        .mapToInt(Integer::intValue)
                        .max()
                        .orElse(MIN_COLUMN_WIDTH),
                MIN_COLUMN_WIDTH
        );

        int maxValueLength = Math.max(
                keyOrder.stream()
                        .map(contentMap::get)
                        .mapToInt(String::length)
                        .max()
                        .orElse(MIN_COLUMN_WIDTH),
                MIN_COLUMN_WIDTH
        );

        printFormattedTable(contentMap, maxKeyLength, maxValueLength);
    }

    private void printFormattedTable(Map<String, String> data, int keyLength, int valueLength) {
        String horizontalLine = "+" + "-".repeat(keyLength + 2) +
                "+" + "-".repeat(valueLength + 2) + "+";

        // Header
        System.out.println(horizontalLine);
        System.out.printf("| %-" + keyLength + "s | %-" + valueLength + "s |%n",
                "KEY", "VALUE");
        System.out.println(horizontalLine);

        // Einträge in der ursprünglichen Reihenfolge ausgeben
        keyOrder.forEach(key -> {
            String displayKey = data.get("__display_" + key);
            String value = data.get(key);
            System.out.printf("| %-" + keyLength + "s | %-" + valueLength + "s |%n",
                    displayKey != null ? displayKey : key,
                    value);
        });

        System.out.println(horizontalLine);
    }

    @Override
    public void displaySingleValue(String key, String value) {
        int keyLength = Math.max(key.length(), MIN_COLUMN_WIDTH);
        int valueLength = Math.max(value.length(), MIN_COLUMN_WIDTH);

        String horizontalLine = "+" + "-".repeat(keyLength + 2) +
                "+" + "-".repeat(valueLength + 2) + "+";

        System.out.println(horizontalLine);
        System.out.printf("| %-" + keyLength + "s | %-" + valueLength + "s |%n",
                "KEY", "VALUE");
        System.out.println(horizontalLine);
        System.out.printf("| %-" + keyLength + "s | %-" + valueLength + "s |%n",
                key, value);
        System.out.println(horizontalLine);
    }
}