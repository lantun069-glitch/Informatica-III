package edu.informatica3.lucas_antun.practico_integrador.nodos;

import edu.informatica3.lucas_antun.practico_integrador.modelo.Paciente;

/**
 * Nodo de una lista enlazada para resolución de colisiones en HashMap.
 * 
 * Estructura:
 * - dni: Clave (DNI del paciente)
 * - paciente: Valor (datos del paciente)
 * - siguiente: Referencia al siguiente nodo en la cadena
 * 
 * Se utiliza encadenamiento (chaining) para manejar colisiones:
 * Cuando dos claves tienen el mismo hash, se almacenan en la misma posición
 * de la tabla formando una lista enlazada.
 * 
 * @author Lucas Santiago Said Antun
 * @author Federico Fernández
 */
public class NodoHashMap {
    public String dni;
    public Paciente paciente;
    public NodoHashMap siguiente;
    
    /**
     * Constructor de nodo.
     * 
     * @param dni DNI del paciente (clave)
     * @param paciente Datos del paciente (valor)
     */
    public NodoHashMap(String dni, Paciente paciente) {
        this.dni = dni;
        this.paciente = paciente;
        this.siguiente = null;
    }
}
