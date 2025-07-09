package de.beugsiud.de.interfaces;

// Optional: Observer für Decompilation-Events
interface DecompileObserver {
    void onLineProcessed(String line);

    void onDecompileComplete();
}
