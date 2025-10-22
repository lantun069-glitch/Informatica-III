package edu.informatica3.lucas_antun.practico_integrador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;

public class SistemaGestionTurnos {
    private ArrayList<Paciente> pacientes;
    private ArrayList<Medico> medicos;
    private ArrayList<Turno> turnos;
    private HashSet<String> idsTurnos;
    
    public SistemaGestionTurnos() {
        this.pacientes = new ArrayList<>();
        this.medicos = new ArrayList<>();
        this.turnos = new ArrayList<>();
        this.idsTurnos = new HashSet<>();
    }
    
    public void cargarDatos() {
        System.out.println("===========================================");
        System.out.println("SISTEMA DE GESTION DE TURNOS MEDICOS");
        System.out.println("===========================================");
        System.out.println("Cargando datos iniciales...");
        
        int pacientesCargados = cargarPacientes("pacientes.csv");
        System.out.println("> Leyendo pacientes.csv ... [OK] (" + pacientesCargados + " registros)");
        
        int medicosCargados = cargarMedicos("medicos.csv");
        System.out.println("> Leyendo medicos.csv ...... [OK] (" + medicosCargados + " registros)");
        
        int[] resultadoTurnos = cargarTurnos("turnos.csv");
        System.out.println("> Leyendo turnos.csv ....... [OK] (" + resultadoTurnos[0] + " registros)");
        
        System.out.println("> Validando datos ...");
        if (resultadoTurnos[1] > 0) {
            System.out.println("  - " + resultadoTurnos[1] + " turnos rechazados (fecha pasada)");
        }
        if (resultadoTurnos[2] > 0) {
            System.out.println("  - " + resultadoTurnos[2] + " turno(s) duplicado(s) por ID");
        }
        if (resultadoTurnos[3] > 0) {
            System.out.println("  - " + resultadoTurnos[3] + " turno(s) rechazado(s) (paciente/medico no existe)");
        }
        if (resultadoTurnos[4] > 0) {
            System.out.println("  - " + resultadoTurnos[4] + " turno(s) rechazado(s) (duracion invalida)");
        }
        
        System.out.println("> Estructuras internas inicializadas correctamente.");
        System.out.println();
    }
    
    private int cargarPacientes(String archivo) {
        int contador = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea = br.readLine();
            
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 2) {
                    String dni = datos[0].trim();
                    String nombre = datos[1].trim();
                    pacientes.add(new Paciente(dni, nombre));
                    contador++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer archivo: " + archivo);
        }
        return contador;
    }
    
    private int cargarMedicos(String archivo) {
        int contador = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea = br.readLine();
            
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 3) {
                    String matricula = datos[0].trim();
                    String nombre = datos[1].trim();
                    String especialidad = datos[2].trim();
                    medicos.add(new Medico(matricula, nombre, especialidad));
                    contador++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer archivo: " + archivo);
        }
        return contador;
    }
    
    private int[] cargarTurnos(String archivo) {
        int totalLeidos = 0;
        int fechaPasada = 0;
        int duplicados = 0;
        int noExiste = 0;
        int duracionInvalida = 0;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime ahora = LocalDateTime.now();
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea = br.readLine();
            
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 6) {
                    totalLeidos++;
                    String id = datos[0].trim();
                    String dniPaciente = datos[1].trim();
                    String matriculaMedico = datos[2].trim();
                    String fechaHoraStr = datos[3].trim();
                    int duracionMin = Integer.parseInt(datos[4].trim());
                    String motivo = datos[5].trim();
                    
                    if (idsTurnos.contains(id)) {
                        duplicados++;
                        continue;
                    }
                    
                    if (duracionMin <= 0) {
                        duracionInvalida++;
                        continue;
                    }
                    
                    if (!existePaciente(dniPaciente) || !existeMedico(matriculaMedico)) {
                        noExiste++;
                        continue;
                    }
                    
                    LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr, formatter);
                    
                    if (fechaHora.isBefore(ahora)) {
                        fechaPasada++;
                        continue;
                    }
                    
                    turnos.add(new Turno(id, dniPaciente, matriculaMedico, fechaHora, duracionMin, motivo));
                    idsTurnos.add(id);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer archivo: " + archivo);
        }
        
        return new int[]{totalLeidos, fechaPasada, duplicados, noExiste, duracionInvalida};
    }
    
    private boolean existePaciente(String dni) {
        for (Paciente p : pacientes) {
            if (p.getDni().equals(dni)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean existeMedico(String matricula) {
        for (Medico m : medicos) {
            if (m.getMatricula().equals(matricula)) {
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<Paciente> getPacientes() {
        return pacientes;
    }
    
    public ArrayList<Medico> getMedicos() {
        return medicos;
    }
    
    public ArrayList<Turno> getTurnos() {
        return turnos;
    }
    
    public static void main(String[] args) {
        SistemaGestionTurnos sistema = new SistemaGestionTurnos();
        sistema.cargarDatos();
    }
}