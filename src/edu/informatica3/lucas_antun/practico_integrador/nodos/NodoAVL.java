package edu.informatica3.lucas_antun.practico_integrador.nodos;

import edu.informatica3.lucas_antun.practico_integrador.modelo.Turno;

/**
 * Nodo de un Árbol AVL para la Agenda Médica.
 * 
 * Estructura:
 * - turno: Información del turno almacenado
 * - izquierdo: Subárbol izquierdo (turnos con fechas anteriores)
 * - derecho: Subárbol derecho (turnos con fechas posteriores)
 * - altura: Altura del subárbol con raíz en este nodo
 * 
 * La altura se utiliza para:
 * - Calcular el factor de balance
 * - Determinar si se requieren rotaciones
 * - Mantener la propiedad AVL (|balance| <= 1)
 * 
 * @author Lucas Santiago Said Antun
 * @author Federico Fernández
 */
public class NodoAVL {
    public Turno turno;
    public NodoAVL izquierdo;
    public NodoAVL derecho;
    public int altura;  // Altura del subárbol con raíz en este nodo
    
    /**
     * Constructor de nodo hoja.
     * La altura de un nodo hoja es 1 (cuenta el nodo mismo).
     * 
     * @param turno Turno a almacenar en el nodo
     */
    public NodoAVL(Turno turno) {
        this.turno = turno;
        this.altura = 1; // Nuevo nodo se inserta como hoja (altura 1)
        this.izquierdo = null;
        this.derecho = null;
    }
}
