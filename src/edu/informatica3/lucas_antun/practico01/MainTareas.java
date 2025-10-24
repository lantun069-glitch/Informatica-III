package edu.informatica3.lucas_antun.practico01;

import java.util.Scanner;

/**
 * Sistema de gestion de tareas personales.
 */
public class MainTareas {
    
    private final Scanner scanner;
    private final GestorTareas gestor;
    
    public MainTareas() {
        this.scanner = new Scanner(System.in);
        this.gestor = new GestorTareas();
    }
    
    public MainTareas(GestorTareas gestor) {
        this.scanner = new Scanner(System.in);
        this.gestor = gestor != null ? gestor : new GestorTareas();
    }
    
    public static void main(String[] args) {
        MainTareas app = new MainTareas();
        app.ejecutar();
    }
    
    public void ejecutar() {
        mostrarBienvenida();
        
        if (gestor.cargarTareas()) {
            System.out.println("Tareas cargadas desde archivo.");
        }
        
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = leerOpcion();
            continuar = procesarOpcion(opcion);
            
            if (continuar) {
                esperarEnter();
            }
        }
        
        despedirse();
        scanner.close();
    }
    
    private void mostrarBienvenida() {
        System.out.println("GESTOR DE TAREAS PERSONALES");
    }
    
    private void mostrarMenu() {
        System.out.println("\nMENU PRINCIPAL");
        System.out.println("1. Agregar tarea");
        System.out.println("2. Listar tareas");
        System.out.println("3. Marcar tarea como completada");
        System.out.println("4. Eliminar tareas completadas");
        System.out.println("5. Guardar tareas en archivo");
        System.out.println("6. Cargar tareas desde archivo");
        System.out.println("7. Mostrar estadisticas");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opcion: ");
    }
    
    private int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private boolean procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                agregarTarea();
                break;
            case 2:
                listarTareas();
                break;
            case 3:
                marcarTareaCompletada();
                break;
            case 4:
                eliminarTareasCompletadas();
                break;
            case 5:
                guardarTareas();
                break;
            case 6:
                cargarTareas();
                break;
            case 7:
                mostrarEstadisticas();
                break;
            case 0:
                return manejarSalida();
            default:
                System.out.println("Opcion no valida. Intente nuevamente.");
        }
        return true;
    }
    
    private void agregarTarea() {
        System.out.print("\nIngrese la descripcion de la tarea: ");
        String descripcion = scanner.nextLine().trim();
        
        if (gestor.agregarTarea(descripcion)) {
            System.out.println("Tarea agregada correctamente.");
        } else {
            System.out.println("Error: La descripcion no puede estar vacia.");
        }
    }
    
    private void listarTareas() {
        System.out.println();
        gestor.listarTareas();
    }
    
    private void marcarTareaCompletada() {
        if (gestor.estaVacio()) {
            System.out.println("\nNo hay tareas para marcar como completadas.");
            return;
        }
        
        gestor.listarTareas();
        System.out.print("Ingrese el numero de la tarea a marcar como completada: ");
        
        try {
            int indice = Integer.parseInt(scanner.nextLine().trim());
            if (gestor.marcarComoCompletada(indice)) {
                System.out.println("Tarea marcada como completada.");
            } else {
                System.out.println("Error: Numero de tarea invalido.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Por favor, ingrese un numero valido.");
        }
    }
    
    private void eliminarTareasCompletadas() {
        int eliminadas = gestor.eliminarTareasCompletadas();
        if (eliminadas > 0) {
            System.out.printf("Se eliminaron %d tarea(s) completada(s).\n", eliminadas);
        } else {
            System.out.println("No hay tareas completadas para eliminar.");
        }
    }
    
    private void guardarTareas() {
        if (gestor.guardarTareas()) {
            System.out.printf("Tareas guardadas correctamente en '%s'.\n", 
                gestor.obtenerArchivoTareas());
        } else {
            System.out.println("Error al guardar las tareas.");
        }
    }
    
    private void cargarTareas() {
        if (gestor.cargarTareas()) {
            System.out.printf("Tareas cargadas correctamente desde '%s'.\n", 
                gestor.obtenerArchivoTareas());
        } else {
            System.out.printf("No se encontro el archivo '%s' o error al cargar.\n", 
                gestor.obtenerArchivoTareas());
        }
    }
    
    private void mostrarEstadisticas() {
        System.out.println("\nESTADISTICAS");
        System.out.printf("Total de tareas: %d\n", gestor.obtenerNumeroTareas());
        System.out.printf("Pendientes: %d\n", gestor.contarTareasPendientes());
        System.out.printf("Completadas: %d\n", gestor.contarTareasCompletadas());
        
        if (gestor.obtenerNumeroTareas() > 0) {
            double porcentaje = (gestor.contarTareasCompletadas() * 100.0) / gestor.obtenerNumeroTareas();
            System.out.printf("Progreso: %.1f%% completado\n", porcentaje);
        }
        
        System.out.printf("Archivo: %s\n", gestor.obtenerArchivoTareas());
    }
    
    private boolean manejarSalida() {
        System.out.println("Desea guardar las tareas antes de salir? (S/N): ");
        String respuesta = scanner.nextLine().trim().toUpperCase();
        
        if ("S".equals(respuesta) || "SI".equals(respuesta)) {
            if (gestor.guardarTareas()) {
                System.out.println("Tareas guardadas correctamente.");
            } else {
                System.out.println("Error al guardar las tareas.");
            }
        }
        
        return false;
    }
    
    private void despedirse() {
        System.out.println("Hasta pronto!");
    }
    
    private void esperarEnter() {
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();
        System.out.println();
    }
}
