package de.beugsiud.de.exceptions;

// Custom Exception für Decompiler-Fehler
public class DecompileException extends RuntimeException {
    public DecompileException(String message, Throwable cause) {
        super(message, cause);
    }
}
