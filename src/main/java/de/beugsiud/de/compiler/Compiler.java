package de.beugsiud.de.compiler;

public class Compiler {
    private static String fileName;

    public Compiler() {
    }
    public Compiler(String fileName) {
        Compiler.fileName = fileName;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        Compiler.fileName = fileName;
    }
    public void compile() {
        System.out.println("Compiling file: " + fileName);
        // Add actual compilation code
    }
}
