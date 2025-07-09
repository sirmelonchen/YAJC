package de.beugsiud.de;

import de.beugsiud.de.compiler.DeCompiler;
import de.beugsiud.de.exceptions.DecompileException;
import de.beugsiud.de.standarts.CustomDecompileStrategy;

import java.io.IOException;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        try {
            // Standardverwendung
            DeCompiler decompiler = new DeCompiler("test.yajc");
            decompiler.decompile();

            // Mit custom Strategy
            DeCompiler customDecompiler = new DeCompiler("test.yajc");
            customDecompiler.setStrategy(new CustomDecompileStrategy());
            customDecompiler.decompile();

            // Zugriff auf die decompilierten Daten
            Map<String, String> content = customDecompiler.getDecompiledContent();
            decompiler.displayValueForKey("Mail");

        } catch (DecompileException | IOException e) {
            System.err.println("Decompilation failed: " + e.getMessage());
        }
    }

}