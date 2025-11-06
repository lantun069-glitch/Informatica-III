package edu.informatica3.lucas_antun.practico_integrador.modelo;

import java.time.LocalDateTime;

/**
 * Clase que representa un quirófano en el planificador.
 * 
 * Atributos:
 * - id: Identificador único del quirófano (ej: "Q1", "Q2")
 * - disponibleDesde: Momento desde el cual el quirófano está libre
 * 
 * Se utiliza en un Min-Heap ordenado por disponibleDesde para:
 * - Asignar cirugías al quirófano que se libere primero
 * - Implementar algoritmo greedy de asignación óptima
 * - Minimizar tiempos de espera
 * 
 * @author Lucas Santiago Said Antun
 * @author Federico Fernández
 */
public class Quirofano {
    public String id;
    public LocalDateTime disponibleDesde;
    
    /**
     * Constructor de quirófano.
     * 
     * @param id Identificador del quirófano
     * @param disponibleDesde Momento desde el cual está disponible
     */
    public Quirofano(String id, LocalDateTime disponibleDesde) {
        this.id = id;
        this.disponibleDesde = disponibleDesde;
    }
}
