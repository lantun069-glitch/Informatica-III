package edu.informatica3.lucas_antun.practico_integrador;

public class Paciente {
    private String dni;
    private String nombre;
    
    public Paciente(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }
    
    public String getDni() {
        return dni;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    @Override
    public String toString() {
        return "Paciente{dni='" + dni + "', nombre='" + nombre + "'}";
    }
}