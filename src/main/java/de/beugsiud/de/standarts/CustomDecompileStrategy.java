package de.beugsiud.de.standarts;

import de.beugsiud.de.interfaces.DecompileStrategy;

import java.util.Map;

// Beispiel für eine spezielle Decompile-Strategie
public class CustomDecompileStrategy implements DecompileStrategy {
    @Override
    public void processLine(String line, Map<String, String> contentMap) {
        // Implementieren Sie hier Ihre spezielle Logik
    }

    @Override
    public void displaySingleValue(String key, String value) {

    }

    @Override
    public void processContent(Map<String, String> contentMap) {
        // Implementieren Sie hier Ihre spezielle Ausgabe-Logik
    }
}
