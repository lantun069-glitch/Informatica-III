package edu.informatica3.lucas_antun.practico_integrador;

import java.time.LocalDateTime;

public class Turno {
    private String id;
    private String dniPaciente;
    private String matriculaMedico;
    private LocalDateTime fechaHora;
    private int duracionMin;
    private String motivo;
    
    public Turno(String id, String dniPaciente, String matriculaMedico, 
                 LocalDateTime fechaHora, int duracionMin, String motivo) {
        this.id = id;
        this.dniPaciente = dniPaciente;
        this.matriculaMedico = matriculaMedico;
        this.fechaHora = fechaHora;
        this.duracionMin = duracionMin;
        this.motivo = motivo;
    }
    
    public String getId() {
        return id;
    }
    
    public String getDniPaciente() {
        return dniPaciente;
    }
    
    public String getMatriculaMedico() {
        return matriculaMedico;
    }
    
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    
    public int getDuracionMin() {
        return duracionMin;
    }
    
    public String getMotivo() {
        return motivo;
    }
    
    @Override
    public String toString() {
        return "Turno{id='" + id + "', dniPaciente='" + dniPaciente + 
               "', matriculaMedico='" + matriculaMedico + "', fechaHora=" + 
               fechaHora + ", duracionMin=" + duracionMin + ", motivo='" + 
               motivo + "'}";
    }
}