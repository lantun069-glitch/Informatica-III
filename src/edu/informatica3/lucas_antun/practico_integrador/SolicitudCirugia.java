package edu.informatica3.lucas_antun.practico_integrador;

import java.time.LocalDateTime;

/**
 * SolicitudCirugia - Representa una solicitud de cirugía
 * 
 * EJERCICIO 10: Planificador de quirófano con asignación y top-K
 * 
 * Contiene la información necesaria para asignar un quirófano:
 * - ID único de la solicitud
 * - Matrícula del médico que realizará la cirugía
 * - Duración estimada en minutos
 * - Deadline (fecha límite para realizarla)
 * 
 * @author Lucas Antun
 * @version 1.0
 */
public class SolicitudCirugia {
    
    private final String id;
    private final String matriculaMedico;
    private final int duracionMin;
    private final LocalDateTime deadline;
    
    /**
     * Constructor
     * 
     * @param id Identificador único de la solicitud
     * @param matriculaMedico Matrícula del médico cirujano
     * @param duracionMin Duración estimada en minutos
     * @param deadline Fecha límite para realizar la cirugía
     */
    public SolicitudCirugia(String id, String matriculaMedico, int duracionMin, LocalDateTime deadline) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID no puede estar vacío");
        }
        if (duracionMin <= 0) {
            throw new IllegalArgumentException("Duración debe ser mayor a 0");
        }
        if (deadline == null) {
            throw new IllegalArgumentException("Deadline no puede ser nulo");
        }
        
        this.id = id.trim();
        this.matriculaMedico = matriculaMedico;
        this.duracionMin = duracionMin;
        this.deadline = deadline;
    }
    
    public String getId() {
        return id;
    }
    
    public String getMatriculaMedico() {
        return matriculaMedico;
    }
    
    public int getDuracionMin() {
        return duracionMin;
    }
    
    public LocalDateTime getDeadline() {
        return deadline;
    }
    
    @Override
    public String toString() {
        return String.format("SolicitudCirugia{id='%s', medico='%s', duracion=%dmin, deadline=%s}",
                id, matriculaMedico, duracionMin, deadline);
    }
}
