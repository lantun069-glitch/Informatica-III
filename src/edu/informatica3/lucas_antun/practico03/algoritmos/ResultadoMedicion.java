package edu.informatica3.lucas_antun.practico03.algoritmos;

public class ResultadoMedicion {
    public final String nombreAlgoritmo;
    public final long tiempoNanos;
    public final boolean estaOrdenado;
    
    public ResultadoMedicion(String nombreAlgoritmo, long tiempoNanos, boolean estaOrdenado) {
        this.nombreAlgoritmo = nombreAlgoritmo;
        this.tiempoNanos = tiempoNanos;
        this.estaOrdenado = estaOrdenado;
    }
}
