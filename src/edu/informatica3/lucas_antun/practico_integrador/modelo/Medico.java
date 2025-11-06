package edu.informatica3.lucas_antun.practico_integrador.modelo;

/**
 * Clase Médico - Representa un médico en el sistema de gestión de turnos
 * 
 * Esta clase modela la información de un médico profesional que atiende pacientes.
 * Cada médico tiene una matrícula única, nombre y especialidad.
 * Se utiliza como parte del modelo de dominio según las especificaciones del ejercicio 1.
 * 
 * @author Lucas Santiago Said Antun
 * @author Federico Fernández
 * @version 1.0
 */
public class Medico {
    // Matrícula profesional - identificador único del médico
    private final String matricula;
    
    // Nombre completo del médico
    private final String nombre;
    
    // Especialidad médica (ej: Cardiología, Pediatría, etc.)
    private final String especialidad;
    
    /**
     * Constructor de Médico
     * 
     * @param matricula Matrícula profesional del médico (no puede ser null ni vacía)
     * @param nombre Nombre completo del médico (no puede ser null ni vacío)
     * @param especialidad Especialidad médica (no puede ser null ni vacía)
     * @throws IllegalArgumentException si algún parámetro es inválido
     */
    public Medico(String matricula, String nombre, String especialidad) {
        // Validación: la matrícula no puede estar vacía
        if (matricula == null || matricula.trim().isEmpty()) {
            throw new IllegalArgumentException("Matrícula no puede estar vacía");
        }
        
        // Validación: el nombre no puede estar vacío
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre no puede estar vacío");
        }
        
        // Validación: la especialidad no puede estar vacía
        if (especialidad == null || especialidad.trim().isEmpty()) {
            throw new IllegalArgumentException("Especialidad no puede estar vacía");
        }
        
        // Asignamos valores limpiando espacios en blanco
        this.matricula = matricula.trim();
        this.nombre = nombre.trim();
        this.especialidad = especialidad.trim();
    }
    
    /**
     * Obtiene la matrícula del médico
     * @return Matrícula profesional
     */
    public String getMatricula() {
        return matricula;
    }
    
    /**
     * Obtiene el nombre del médico
     * @return Nombre completo del médico
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Obtiene la especialidad del médico
     * @return Especialidad médica
     */
    public String getEspecialidad() {
        return especialidad;
    }
    
    /**
     * Representación en String del médico
     * @return String con formato legible
     */
    @Override
    public String toString() {
        return "Medico{matricula='" + matricula + "', nombre='" + nombre + 
               "', especialidad='" + especialidad + "'}";
    }
    
    /**
     * Compara dos médicos por su matrícula
     * Dos médicos son iguales si tienen la misma matrícula
     * 
     * @param o Objeto a comparar
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medico medico = (Medico) o;
        return matricula.equals(medico.matricula);
    }
    
    /**
     * Calcula el hash code basado en la matrícula
     * Necesario para usar Médico en HashMaps y HashSets
     * 
     * @return hash code de la matrícula
     */
    @Override
    public int hashCode() {
        return matricula.hashCode();
    }
}