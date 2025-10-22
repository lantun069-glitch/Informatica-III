package src.edu.informatica3.lucas_antun.practico01;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Gestor de tareas personales.
 */
public class GestorTareas {
    
    private final List<Tarea> tareas;
    private final String archivoTareas;
    private static final String SEPARADOR_ARCHIVO = "|";
    
    public GestorTareas() {
        this("tareas.txt");
    }
    
    public GestorTareas(String archivoTareas) {
        this.tareas = new ArrayList<>();
        this.archivoTareas = archivoTareas != null ? archivoTareas : "tareas.txt";
    }
    
    public boolean agregarTarea(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            return false;
        }
        
        try {
            Tarea nuevaTarea = new Tarea(descripcion);
            tareas.add(nuevaTarea);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    
    public boolean agregarTarea(Tarea tarea) {
        if (tarea == null) {
            return false;
        }
        tareas.add(tarea);
        return true;
    }
    
    public boolean listarTareas() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas registradas.");
            return false;
        }
        
        System.out.println("\nLISTA DE TAREAS");
        for (int i = 0; i < tareas.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, tareas.get(i));
        }
        return true;
    }
    
    public List<Tarea> obtenerTareas() {
        return new ArrayList<>(tareas);
    }
    
    public Optional<Tarea> obtenerTarea(int indice) {
        if (indice < 1 || indice > tareas.size()) {
            return Optional.empty();
        }
        return Optional.of(tareas.get(indice - 1));
    }
    
    public boolean marcarComoCompletada(int indice) {
        Optional<Tarea> tarea = obtenerTarea(indice);
        if (tarea.isPresent()) {
            tarea.get().marcarComoCompletada();
            return true;
        }
        return false;
    }
    
    public int eliminarTareasCompletadas() {
        int contador = 0;
        for (int i = tareas.size() - 1; i >= 0; i--) {
            if (tareas.get(i).estaCompletada()) {
                tareas.remove(i);
                contador++;
            }
        }
        return contador;
    }
    
    public boolean eliminarTarea(int indice) {
        if (indice < 1 || indice > tareas.size()) {
            return false;
        }
        tareas.remove(indice - 1);
        return true;
    }
    
    public long contarTareasPendientes() {
        return tareas.stream().filter(Tarea::estaPendiente).count();
    }
    
    public long contarTareasCompletadas() {
        return tareas.stream().filter(Tarea::estaCompletada).count();
    }
    
    public boolean guardarTareas() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivoTareas))) {
            for (Tarea tarea : tareas) {
                writer.printf("%s%s%s%n", 
                    tarea.getDescripcion(), 
                    SEPARADOR_ARCHIVO, 
                    tarea.getEstado());
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error al guardar las tareas: " + e.getMessage());
            return false;
        }
    }
    
    public boolean cargarTareas() {
        File archivo = new File(archivoTareas);
        if (!archivo.exists()) {
            return false;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            tareas.clear();
            String linea;
            
            while ((linea = reader.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }
                
                String[] partes = linea.split("\\" + SEPARADOR_ARCHIVO, 2);
                if (partes.length == 2) {
                    try {
                        Tarea tarea = new Tarea(partes[0], partes[1]);
                        tareas.add(tarea);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Linea ignorada por formato invalido: " + linea);
                    }
                }
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error al cargar las tareas: " + e.getMessage());
            return false;
        }
    }
    
    public int obtenerNumeroTareas() {
        return tareas.size();
    }
    
    public boolean estaVacio() {
        return tareas.isEmpty();
    }
    
    public void limpiarTareas() {
        tareas.clear();
    }
    
    public String obtenerArchivoTareas() {
        return archivoTareas;
    }
    
    @Override
    public String toString() {
        return String.format("GestorTareas{tareas=%d, pendientes=%d, completadas=%d, archivo='%s'}", 
            obtenerNumeroTareas(), 
            contarTareasPendientes(), 
            contarTareasCompletadas(), 
            archivoTareas);
    }
}
