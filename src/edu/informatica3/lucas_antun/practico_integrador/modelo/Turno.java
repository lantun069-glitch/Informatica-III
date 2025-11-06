package edu.informatica3.lucas_antun.practico_integrador.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase Turno - Representa una cita médica en el sistema
 * 
 * Esta clase modela un turno médico con toda la información necesaria:
 * - Identificador único
 * - Paciente y médico involucrados
 * - Fecha, hora y duración
 * - Motivo de la consulta
 * 
 * Se utiliza como parte del modelo de dominio según las especificaciones del ejercicio 1.
 * Incluye lógica para detectar solapamientos de turnos (ejercicio 2).
 * 
 * @author Lucas Santiago Said Antun
 * @author Federico Fernández
 * @version 1.0
 */
public class Turno {
    // Identificador único del turno
    private final String id;
    
    // DNI del paciente que tiene el turno
    private final String dniPaciente;
    
    // Matrícula del médico que atenderá
    private final String matriculaMedico;
    
    // Fecha y hora de inicio del turno
    private LocalDateTime fechaHora;
    
    // Duración del turno en minutos
    private final int duracionMin;
    
    // Motivo de la consulta
    private final String motivo;
    
    /**
     * Constructor de Turno
     * 
     * @param id Identificador único del turno (no puede ser null ni vacío)
     * @param dniPaciente DNI del paciente
     * @param matriculaMedico Matrícula del médico
     * @param fechaHora Fecha y hora del turno (no puede ser null)
     * @param duracionMin Duración en minutos (debe ser > 0)
     * @param motivo Motivo de la consulta
     * @throws IllegalArgumentException si los parámetros son inválidos
     */
    public Turno(String id, String dniPaciente, String matriculaMedico, 
                 LocalDateTime fechaHora, int duracionMin, String motivo) {
        // Validación: el ID no puede estar vacío
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID no puede estar vacío");
        }
        
        // Validación: la duración debe ser positiva (ejercicio 1)
        if (duracionMin <= 0) {
            throw new IllegalArgumentException("Duración debe ser mayor a 0");
        }
        
        // Validación: la fecha no puede ser nula
        if (fechaHora == null) {
            throw new IllegalArgumentException("Fecha y hora no pueden ser nulas");
        }
        
        this.id = id.trim();
        this.dniPaciente = dniPaciente;
        this.matriculaMedico = matriculaMedico;
        this.fechaHora = fechaHora;
        this.duracionMin = duracionMin;
        this.motivo = motivo != null ? motivo.trim() : "";
    }
    
    /**
     * Obtiene el ID del turno
     * @return ID único del turno
     */
    public String getId() {
        return id;
    }
    
    /**
     * Obtiene el DNI del paciente
     * @return DNI del paciente
     */
    public String getDniPaciente() {
        return dniPaciente;
    }
    
    /**
     * Obtiene la matrícula del médico
     * @return Matrícula del médico
     */
    public String getMatriculaMedico() {
        return matriculaMedico;
    }
    
    /**
     * Obtiene la fecha y hora de inicio del turno
     * @return Fecha y hora de inicio
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    
    /**
     * Calcula y obtiene la fecha y hora de fin del turno
     * Se obtiene sumando la duración al inicio
     * 
     * @return Fecha y hora de finalización del turno
     */
    public LocalDateTime getFechaHoraFin() {
        return fechaHora.plusMinutes(duracionMin);
    }
    
    /**
     * Obtiene la duración del turno en minutos
     * @return Duración en minutos
     */
    public int getDuracionMin() {
        return duracionMin;
    }
    
    /**
     * Obtiene el motivo de la consulta
     * @return Motivo de la consulta
     */
    public String getMotivo() {
        return motivo;
    }
    
    /**
     * Modifica la fecha y hora del turno
     * Usado en reprogramaciones (ejercicio 9)
     * 
     * @param fechaHora Nueva fecha y hora
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    /**
     * Verifica si este turno se solapa con otro turno
     * 
     * Dos turnos se solapan si sus intervalos de tiempo se cruzan.
     * Se usa para evitar "double booking" (ejercicio 2).
     * 
     * Lógica: Los turnos NO se solapan si:
     * - Este turno termina antes o justo cuando empieza el otro, O
     * - Este turno empieza después o justo cuando termina el otro
     * 
     * Si ninguna de estas condiciones se cumple, entonces SÍ se solapan.
     * 
     * @param otro El otro turno a comparar
     * @return true si hay solapamiento, false en caso contrario
     */
    public boolean seSolapaCon(Turno otro) {
        LocalDateTime esteFin = this.getFechaHoraFin();
        LocalDateTime otroFin = otro.getFechaHoraFin();
        
        // Los turnos se solapan si no se cumple que uno termine antes de que empiece el otro
        return !(this.fechaHora.isAfter(otroFin) || this.fechaHora.isEqual(otroFin) ||
                 esteFin.isBefore(otro.fechaHora) || esteFin.isEqual(otro.fechaHora));
    }
    
    /**
     * Formatea la fecha y hora en formato legible: dd/MM HH:mm
     * Usado para mostrar en reportes y menús
     * 
     * @return String con formato "22/10 09:30"
     */
    public String formatearFechaHora() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM HH:mm");
        return fechaHora.format(formatter);
    }
    
    /**
     * Representación en String del turno
     * @return String con formato legible
     */
    @Override
    public String toString() {
        return String.format("Turno{id='%s', paciente='%s', medico='%s', fecha=%s, dur=%dmin, motivo='%s'}",
                id, dniPaciente, matriculaMedico, formatearFechaHora(), duracionMin, motivo);
    }
    
    /**
     * Compara dos turnos por su ID
     * Dos turnos son iguales si tienen el mismo ID (ejercicio 1: rechazar duplicados)
     * 
     * @param o Objeto a comparar
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turno turno = (Turno) o;
        return id.equals(turno.id);
    }
    
    /**
     * Calcula el hash code basado en el ID
     * Necesario para usar Turno en HashMaps y HashSets
     * 
     * @return hash code del ID
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}