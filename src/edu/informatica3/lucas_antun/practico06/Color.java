package edu.informatica3.lucas_antun.practico06;

/**
 * Colores para nodos de arbol Rojo-Negro.
 */
public enum Color {
    
    /** Color rojo */
    ROJO,
    
    /** Color negro */
    NEGRO;
    
    /**
     * Retorna el color opuesto.
     * 
     * @return NEGRO si este color es ROJO, ROJO si este color es NEGRO
     */
    public Color opuesto() {
        return this == ROJO ? NEGRO : ROJO;
    }
    
    /**
     * Verifica si este color es rojo.
     * 
     * @return true si el color es rojo, false en caso contrario
     */
    public boolean esRojo() {
        return this == ROJO;
    }
    
    /**
     * Verifica si este color es negro.
     * 
     * @return true si el color es negro, false en caso contrario
     */
    public boolean esNegro() {
        return this == NEGRO;
    }
    
    /**
     * Retorna una representacion de caracter del color.
     * 
     * @return 'R' para rojo, 'N' para negro
     */
    public char toChar() {
        return this == ROJO ? 'R' : 'N';
    }
    
    /**
     * Retorna una representacion en cadena corta del color.
     * 
     * @return "R" para rojo, "N" para negro
     */
    public String toShortString() {
        return String.valueOf(toChar());
    }
    
    @Override
    public String toString() {
        return this == ROJO ? "Rojo" : "Negro";
    }
}