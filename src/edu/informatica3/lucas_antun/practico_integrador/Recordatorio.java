package edu.informatica3.lucas_antun.practico_integrador;

import java.time.LocalDateTime;

/**
 * Recordatorio - Representa un recordatorio programado
 * 
 * EJERCICIO 5: Recordatorios y llamados con planificador por prioridad temporal
 * 
 * Esta clase representa un recordatorio que debe ser disparado en una fecha específica.
 * Se usa junto con PlanificadorRecordatorios que mantiene los recordatorios ordenados
 * por fecha usando un Min-Heap.
 * 
 * @author Lucas Antun
 * @version 1.0
 */
public class Recordatorio {
    
    // Identificador único del recordatorio
    private final String id;
    
    // Fecha y hora en la que se debe disparar el recordatorio
    private LocalDateTime fecha;
    
    // DNI del paciente destinatario
    private final String dniPaciente;
    
    // Mensaje del recordatorio
    private final String mensaje;
    
    /**
     * Constructor
     * 
     * @param id Identificador único
     * @param fecha Fecha y hora del recordatorio
     * @param dniPaciente DNI del paciente
     * @param mensaje Mensaje del recordatorio
     */
    public Recordatorio(String id, LocalDateTime fecha, String dniPaciente, String mensaje) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID no puede estar vacío");
        }
        if (fecha == null) {
            throw new IllegalArgumentException("Fecha no puede ser nula");
        }
        if (dniPaciente == null || dniPaciente.trim().isEmpty()) {
            throw new IllegalArgumentException("DNI del paciente no puede estar vacío");
        }
        
        this.id = id.trim();
        this.fecha = fecha;
        this.dniPaciente = dniPaciente.trim();
        this.mensaje = mensaje != null ? mensaje.trim() : "";
    }
    
    public String getId() {
        return id;
    }
    
    public LocalDateTime getFecha() {
        return fecha;
    }
    
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    
    public String getDniPaciente() {
        return dniPaciente;
    }
    
    public String getMensaje() {
        return mensaje;
    }
    
    @Override
    public String toString() {
        return String.format("Recordatorio{id='%s', fecha=%s, paciente='%s', mensaje='%s'}",
                id, fecha, dniPaciente, mensaje);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recordatorio that = (Recordatorio) o;
        return id.equals(that.id);
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
