package edu.informatica3.lucas_antun.practico_integrador.modelo;

/**
 * Clase Paciente - Representa un paciente en el sistema de gestión de turnos médicos
 * 
 * Esta clase modela la información básica de un paciente del sistema.
 * Se utiliza como parte del modelo de dominio según las especificaciones del ejercicio 1.
 * 
 * @author Lucas Santiago Said Antun
 * @author Federico Fernández
 * @version 1.0
 */
public class Paciente {
    // DNI del paciente - identificador único
    private String dni;
    
    // Nombre completo del paciente
    private String nombre;
    
    /**
     * Constructor de Paciente
     * 
     * @param dni DNI del paciente (no puede ser null ni vacío)
     * @param nombre Nombre completo del paciente (no puede ser null ni vacío)
     * @throws IllegalArgumentException si algún parámetro es inválido
     */
    public Paciente(String dni, String nombre) {
        // Validación: el DNI no puede estar vacío
        if (dni == null || dni.trim().isEmpty()) {
            throw new IllegalArgumentException("DNI no puede estar vacío");
        }
        
        // Validación: el nombre no puede estar vacío
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre no puede estar vacío");
        }
        
        // Asignamos valores limpiando espacios en blanco
        this.dni = dni.trim();
        this.nombre = nombre.trim();
    }
    
    /**
     * Obtiene el DNI del paciente
     * @return DNI del paciente
     */
    public String getDni() {
        return dni;
    }
    
    /**
     * Obtiene el nombre del paciente
     * @return Nombre completo del paciente
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Representación en String del paciente
     * @return String con formato legible
     */
    @Override
    public String toString() {
        return "Paciente{dni='" + dni + "', nombre='" + nombre + "'}";
    }
    
    /**
     * Compara dos pacientes por su DNI
     * Dos pacientes son iguales si tienen el mismo DNI
     * 
     * @param o Objeto a comparar
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return dni.equals(paciente.dni);
    }
    
    /**
     * Calcula el hash code basado en el DNI
     * Necesario para usar Paciente en HashMaps y HashSets
     * 
     * @return hash code del DNI
     */
    @Override
    public int hashCode() {
        return dni.hashCode();
    }
}