package edu.informatica3.lucas_antun.practico_integrador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Sistema de Gestión de Turnos Médicos - Clase Principal
 * 
 * Esta clase implementa el sistema completo de gestión de turnos médicos según
 * las especificaciones del práctico integrador. Incluye:
 * 
 * - Ejercicio 1: Carga inicial desde CSV con validaciones
 * - Ejercicio 2: Agenda por médico con AVL Tree
 * - Ejercicio 3: Búsqueda de huecos libres
 * - Ejercicio 4: Sala de espera con cola circular
 * - Ejercicio 5: Recordatorios con planificador de prioridad
 * - Ejercicio 6: Índice de pacientes con Hash Table
 * - Ejercicio 7: Consolidación de agendas (merge)
 * - Ejercicio 8: Reportes con múltiples algoritmos de ordenamiento
 * - Ejercicio 9: Auditoría con Undo/Redo
 * - Ejercicio 10: Planificador de quirófano con heaps
 * 
 * @author Lucas Antun
 * @version 1.0
 */
public class SistemaGestionTurnosMedicos {
    
    // ==================== ATRIBUTOS PRINCIPALES ====================
    
    /**
     * Mapa de pacientes: DNI -> Paciente
     * Usamos HashMap de Java para almacenar, pero implementamos nuestra
     * propia hash table en la clase MapaPacientes (ejercicio 6)
     */
    private Map<String, Paciente> pacientes;
    
    /**
     * Mapa de médicos: Matrícula -> Médico
     */
    private Map<String, Medico> medicos;
    
    /**
     * Mapa de agendas: Matrícula -> AgendaMedico
     * Cada médico tiene su propia agenda implementada con AVL Tree
     */
    private Map<String, AgendaMedico> agendas;
    
    /**
     * Índice rápido de pacientes (Ejercicio 6)
     * Implementación propia de Hash Table con chaining y rehash
     */
    private MapaPacientes indicePacientes;
    
    /**
     * Sala de espera (Ejercicio 4)
     * Cola circular con capacidad fija y control de overflow
     */
    private SalaEspera salaEspera;
    
    /**
     * Planificador de recordatorios (Ejercicio 5)
     * Min-heap por fecha de recordatorio
     */
    private PlanificadorRecordatorios planificador;
    
    /**
     * Planificador de quirófano (Ejercicio 10)
     * Asignación de recursos con heaps
     */
    private PlanificadorQuirofano planificadorQuirofano;
    
    /**
     * Scanner para entrada del usuario
     */
    private Scanner scanner;
    
    /**
     * Estadísticas de carga
     */
    private int turnosRechazados;
    private int turnosDuplicados;
    
    // ==================== CONSTRUCTOR ====================
    
    /**
     * Constructor del sistema
     * Inicializa todas las estructuras de datos
     */
    public SistemaGestionTurnosMedicos() {
        this.pacientes = new HashMap<>();
        this.medicos = new HashMap<>();
        this.agendas = new HashMap<>();
        this.indicePacientes = new MapaPacientes(10); // Tamaño inicial
        this.salaEspera = new SalaEspera(5); // Capacidad de 5 personas
        this.planificador = new PlanificadorRecordatorios();
        this.planificadorQuirofano = new PlanificadorQuirofano(3); // 3 quirófanos
        this.scanner = new Scanner(System.in);
        this.turnosRechazados = 0;
        this.turnosDuplicados = 0;
    }
    
    // ==================== EJERCICIO 1: CARGA INICIAL ====================
    
    /**
     * Carga todos los datos desde archivos CSV
     * Implementa el Ejercicio 1 con todas las validaciones requeridas
     */
    public void cargarDatosIniciales() {
        System.out.println("===========================================");
        System.out.println("SISTEMA DE GESTIÓN DE TURNOS MÉDICOS");
        System.out.println("===========================================");
        System.out.println("Cargando datos iniciales...\n");
        
        // Determinar ruta base según directorio actual
        String rutaBase = System.getProperty("user.dir");
        String rutaData;
        
        // Si estamos en /bin, los CSV están en ../src/.../data/
        // Si estamos en la raíz del proyecto, están en src/.../data/
        if (rutaBase.endsWith("/bin") || rutaBase.endsWith("\\bin")) {
            rutaData = "../src/edu/informatica3/lucas_antun/practico_integrador/data/";
        } else {
            rutaData = "src/edu/informatica3/lucas_antun/practico_integrador/data/";
        }
        
        // Cargar pacientes
        int pacientesCargados = cargarPacientes(rutaData + "pacientes_data.csv");
        System.out.println("> Leyendo pacientes.csv ... [OK] (" + pacientesCargados + " registros)");
        
        // Cargar médicos
        int medicosCargados = cargarMedicos(rutaData + "medicos_data.csv");
        System.out.println("> Leyendo medicos.csv ...... [OK] (" + medicosCargados + " registros)");
        
        // Cargar turnos con validaciones
        int turnosCargados = cargarTurnos(rutaData + "turnos_data.csv");
        System.out.println("> Leyendo turnos.csv ....... [OK] (" + turnosCargados + " registros válidos)");
        
        // Mostrar validaciones
        System.out.println("\n> Validando datos ...");
        if (turnosRechazados > 0) {
            System.out.println("  - " + turnosRechazados + " turnos rechazados (fecha pasada o duración inválida)");
        }
        if (turnosDuplicados > 0) {
            System.out.println("  - " + turnosDuplicados + " turnos duplicados por ID");
        }
        
        System.out.println("\n> Estructuras internas inicializadas correctamente.");
        System.out.println("-------------------------------------------\n");
    }
    
    /**
     * Carga pacientes desde archivo CSV
     * 
     * @param rutaArchivo Ruta del archivo CSV
     * @return Cantidad de pacientes cargados
     */
    private int cargarPacientes(String rutaArchivo) {
        int contador = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            boolean primerLinea = true;
            
            while ((linea = br.readLine()) != null) {
                // Saltar encabezado
                if (primerLinea) {
                    primerLinea = false;
                    continue;
                }
                
                String[] partes = linea.split(",");
                if (partes.length >= 2) {
                    try {
                        String dni = partes[0].trim();
                        String nombre = partes[1].trim();
                        
                        Paciente paciente = new Paciente(dni, nombre);
                        pacientes.put(dni, paciente);
                        
                        // También agregar al índice hash personalizado (Ejercicio 6)
                        indicePacientes.put(dni, paciente);
                        
                        contador++;
                    } catch (IllegalArgumentException e) {
                        // Ignorar líneas con datos inválidos
                        System.err.println("Error en línea: " + linea + " - " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo archivo de pacientes: " + e.getMessage());
        }
        return contador;
    }
    
    /**
     * Carga médicos desde archivo CSV
     * 
     * @param rutaArchivo Ruta del archivo CSV
     * @return Cantidad de médicos cargados
     */
    private int cargarMedicos(String rutaArchivo) {
        int contador = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            boolean primerLinea = true;
            
            while ((linea = br.readLine()) != null) {
                // Saltar encabezado
                if (primerLinea) {
                    primerLinea = false;
                    continue;
                }
                
                String[] partes = linea.split(",");
                if (partes.length >= 3) {
                    try {
                        String matricula = partes[0].trim();
                        String nombre = partes[1].trim();
                        String especialidad = partes[2].trim();
                        
                        Medico medico = new Medico(matricula, nombre, especialidad);
                        medicos.put(matricula, medico);
                        
                        // Crear agenda AVL para este médico (Ejercicio 2)
                        agendas.put(matricula, new AgendaMedico(medico));
                        
                        contador++;
                    } catch (IllegalArgumentException e) {
                        System.err.println("Error en línea: " + linea + " - " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo archivo de médicos: " + e.getMessage());
        }
        return contador;
    }
    
    /**
     * Carga turnos desde archivo CSV con validaciones
     * Implementa las validaciones del Ejercicio 1:
     * - Paciente y médico deben existir
     * - Fecha debe ser futura o presente
     * - Duración debe ser mayor a 0
     * - No se permiten IDs duplicados
     * 
     * @param rutaArchivo Ruta del archivo CSV
     * @return Cantidad de turnos cargados exitosamente
     */
    private int cargarTurnos(String rutaArchivo) {
        int contador = 0;
        Set<String> idsVistos = new HashSet<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime ahora = LocalDateTime.now();
        
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            boolean primerLinea = true;
            
            while ((linea = br.readLine()) != null) {
                // Saltar encabezado
                if (primerLinea) {
                    primerLinea = false;
                    continue;
                }
                
                String[] partes = linea.split(",");
                if (partes.length >= 6) {
                    try {
                        String id = partes[0].trim();
                        String dniPaciente = partes[1].trim();
                        String matriculaMedico = partes[2].trim();
                        LocalDateTime fechaHora = LocalDateTime.parse(partes[3].trim(), formatter);
                        int duracionMin = Integer.parseInt(partes[4].trim());
                        String motivo = partes[5].trim();
                        
                        // VALIDACIÓN 1: Verificar que no sea duplicado
                        if (idsVistos.contains(id)) {
                            turnosDuplicados++;
                            continue;
                        }
                        
                        // VALIDACIÓN 2: Verificar que el paciente existe
                        if (!pacientes.containsKey(dniPaciente)) {
                            turnosRechazados++;
                            continue;
                        }
                        
                        // VALIDACIÓN 3: Verificar que el médico existe
                        if (!medicos.containsKey(matriculaMedico)) {
                            turnosRechazados++;
                            continue;
                        }
                        
                        // VALIDACIÓN 4: Verificar que la fecha sea futura o presente
                        if (fechaHora.isBefore(ahora)) {
                            turnosRechazados++;
                            continue;
                        }
                        
                        // VALIDACIÓN 5: La duración ya se valida en el constructor de Turno
                        Turno turno = new Turno(id, dniPaciente, matriculaMedico, fechaHora, duracionMin, motivo);
                        
                        // Agregar turno a la agenda del médico
                        AgendaMedico agenda = agendas.get(matriculaMedico);
                        if (agenda != null) {
                            if (agenda.agendar(turno)) {
                                idsVistos.add(id);
                                contador++;
                            } else {
                                // No se pudo agendar (probablemente conflicto de horario)
                                turnosRechazados++;
                            }
                        }
                        
                    } catch (Exception e) {
                        turnosRechazados++;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo archivo de turnos: " + e.getMessage());
        }
        
        return contador;
    }
    
    // ==================== MENÚ PRINCIPAL ====================
    
    /**
     * Muestra y gestiona el menú principal del sistema
     */
    public void mostrarMenu() {
        boolean continuar = true;
        
        while (continuar) {
            System.out.println("\n-------------------------------------------");
            System.out.println("MENÚ PRINCIPAL");
            System.out.println("-------------------------------------------");
            System.out.println("1) Ver agenda de un médico");
            System.out.println("2) Buscar próximo turno disponible");
            System.out.println("3) Simular sala de espera");
            System.out.println("4) Programar recordatorios");
            System.out.println("5) Consultar índice de pacientes (Hash)");
            System.out.println("6) Consolidador de agendas");
            System.out.println("7) Reportes de ordenamiento");
            System.out.println("8) Auditoría Undo/Redo");
            System.out.println("9) Planificador de quirófano");
            System.out.println("0) Salir");
            System.out.println("-------------------------------------------");
            System.out.print("Seleccione una opción: ");
            
            try {
                int opcion = Integer.parseInt(scanner.nextLine().trim());
                System.out.println();
                
                switch (opcion) {
                    case 1:
                        ejercicio2_VerAgendaMedico();
                        break;
                    case 2:
                        ejercicio3_BuscarHuecoLibre();
                        break;
                    case 3:
                        ejercicio4_SimularSalaEspera();
                        break;
                    case 4:
                        ejercicio5_ProgramarRecordatorios();
                        break;
                    case 5:
                        ejercicio6_ConsultarIndicePacientes();
                        break;
                    case 6:
                        ejercicio7_ConsolidarAgendas();
                        break;
                    case 7:
                        ejercicio8_ReportesOrdenamiento();
                        break;
                    case 8:
                        ejercicio9_AuditoriaUndoRedo();
                        break;
                    case 9:
                        ejercicio10_PlanificadorQuirofano();
                        break;
                    case 0:
                        continuar = false;
                        System.out.println("Fin de ejecución.");
                        System.out.println("-------------------------------------------");
                        break;
                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
            }
        }
    }
    
    // ==================== EJERCICIO 2: AGENDA POR MÉDICO (AVL) ====================
    
    /**
     * Ejercicio 2: Muestra la agenda de un médico usando AVL Tree
     * Demuestra operaciones O(log n) para inserción, borrado y búsqueda
     */
    private void ejercicio2_VerAgendaMedico() {
        System.out.println("-------------------------------------------");
        System.out.println("[AGENDA DE MÉDICO - AVL TREE]");
        System.out.println("-------------------------------------------");
        
        // Listar médicos disponibles
        System.out.println("Médicos disponibles:");
        for (Medico medico : medicos.values()) {
            System.out.printf("  [%s] %s - %s%n", 
                medico.getMatricula(), medico.getNombre(), medico.getEspecialidad());
        }
        
        System.out.print("\nIngrese matrícula del médico: ");
        String matricula = scanner.nextLine().trim();
        
        AgendaMedico agenda = agendas.get(matricula);
        if (agenda == null) {
            System.out.println("Médico no encontrado.");
            return;
        }
        
        Medico medico = medicos.get(matricula);
        System.out.println("\n[AGENDA DEL " + medico.getNombre().toUpperCase() + " - " + 
                          medico.getEspecialidad().toUpperCase() + "]");
        System.out.println("-------------------------------------------");
        System.out.println("Turnos ordenados por fecha (AVL Tree):");
        System.out.printf("%-10s %-12s %-18s %-30s%n", "ID", "PACIENTE", "FECHA Y HORA", "MOTIVO");
        System.out.println("-".repeat(70));
        
        // Obtener turnos en orden (recorrido in-order del AVL)
        List<Turno> turnos = agenda.obtenerTurnosOrdenados();
        if (turnos.isEmpty()) {
            System.out.println("No hay turnos agendados.");
        } else {
            for (Turno turno : turnos) {
                System.out.printf("%-10s %-12s %-18s %-30s%n",
                    turno.getId(),
                    turno.getDniPaciente(),
                    turno.formatearFechaHora() + " hs",
                    turno.getMotivo());
            }
        }
        
        System.out.println("-".repeat(70));
        
        // Demostrar búsqueda del siguiente turno disponible
        LocalDateTime ahora = LocalDateTime.now();
        Optional<Turno> siguiente = agenda.siguiente(ahora);
        if (siguiente.isPresent()) {
            System.out.println("\nSiguiente disponible ≥ " + 
                ahora.format(DateTimeFormatter.ofPattern("dd/MM HH:mm")) + " hs → " +
                siguiente.get().formatearFechaHora() + " hs");
        } else {
            System.out.println("\nNo hay turnos próximos disponibles.");
        }
        
        System.out.println("[Operación O(log n) - Árbol AVL balanceado]");
    }
    
    // ==================== EJERCICIO 3: BÚSQUEDA DE HUECO LIBRE ====================
    
    /**
     * Ejercicio 3: Busca el primer hueco libre de X minutos en la agenda de un médico
     */
    private void ejercicio3_BuscarHuecoLibre() {
        System.out.println("-------------------------------------------");
        System.out.println("[BÚSQUEDA DE HUECO LIBRE]");
        System.out.println("-------------------------------------------");
        
        // Listar médicos
        System.out.println("Médicos disponibles:");
        for (Medico medico : medicos.values()) {
            System.out.printf("  [%s] %s%n", medico.getMatricula(), medico.getNombre());
        }
        
        System.out.print("\nIngrese matrícula del médico: ");
        String matricula = scanner.nextLine().trim();
        
        AgendaMedico agenda = agendas.get(matricula);
        if (agenda == null) {
            System.out.println("Médico no encontrado.");
            return;
        }
        
        System.out.print("Duración requerida (minutos): ");
        int duracion = Integer.parseInt(scanner.nextLine().trim());
        
        System.out.print("Buscar desde (dd/MM/yyyy HH:mm, Enter para ahora): ");
        String fechaStr = scanner.nextLine().trim();
        
        LocalDateTime desde;
        if (fechaStr.isEmpty()) {
            desde = LocalDateTime.now();
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            desde = LocalDateTime.parse(fechaStr, formatter);
        }
        
        Optional<LocalDateTime> hueco = agenda.primerHueco(desde, duracion);
        
        if (hueco.isPresent()) {
            System.out.println("\n✓ Hueco encontrado:");
            System.out.println("  Fecha y hora: " + hueco.get().format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + " hs");
            System.out.println("  Duración disponible: " + duracion + " minutos");
        } else {
            System.out.println("\n✗ No se encontró hueco disponible en los próximos días.");
        }
        
        System.out.println("[Operación O(log n + k) donde k = turnos revisados]");
    }
    
    // ==================== EJERCICIO 4: SALA DE ESPERA (COLA CIRCULAR) ====================
    
    /**
     * Ejercicio 4: Simula una sala de espera con cola circular
     */
    private void ejercicio4_SimularSalaEspera() {
        System.out.println("-------------------------------------------");
        System.out.println("Simulación de Sala de Espera (Cola Circular)");
        System.out.println("Capacidad máxima: " + salaEspera.getCapacidad());
        System.out.println("-------------------------------------------\n");
        
        // Simulación automática con algunos pacientes
        String[] dnisPrueba = {"32045982", "32458910", "31890432", "31247856", "32500890", "31111222"};
        
        for (String dni : dnisPrueba) {
            System.out.println("> Llega paciente " + dni);
            String resultado = salaEspera.llega(dni);
            if (resultado != null) {
                System.out.println("  [Desborda, se elimina el más antiguo: " + resultado + "]");
            }
            if (salaEspera.size() == salaEspera.getCapacidad()) {
                System.out.println("  [Cola llena]");
            }
        }
        
        System.out.println("\nEstado actual:");
        salaEspera.mostrarEstado();
        
        System.out.println("\n> Atender próximo paciente...");
        String atendido = salaEspera.atiende();
        if (atendido != null) {
            System.out.println("  Paciente atendido: " + atendido);
        }
        
        System.out.println("\nEstado después de atender:");
        salaEspera.mostrarEstado();
        
        System.out.println("\n[Operaciones O(1)]");
    }
    
    // ==================== EJERCICIO 5: RECORDATORIOS (MIN-HEAP) ====================
    
    /**
     * Ejercicio 5: Gestiona recordatorios con planificador de prioridad
     */
    private void ejercicio5_ProgramarRecordatorios() {
        System.out.println("-------------------------------------------");
        System.out.println("[PLANIFICADOR DE RECORDATORIOS - MIN HEAP]");
        System.out.println("-------------------------------------------");
        
        // Agregar algunos recordatorios de ejemplo
        LocalDateTime base = LocalDateTime.now();
        
        planificador.programar(new Recordatorio(
            "R-001", base.plusDays(1).withHour(9).withMinute(0),
            "32045982", "Recordatorio turno cardiología"));
            
        planificador.programar(new Recordatorio(
            "R-002", base.plusHours(2),
            "32458910", "Traer estudios previos"));
            
        planificador.programar(new Recordatorio(
            "R-003", base.plusDays(2).withHour(14).withMinute(30),
            "31247856", "Confirmación turno"));
            
        planificador.programar(new Recordatorio(
            "R-004", base.plusHours(1),
            "31890432", "Llamado urgente"));
        
        System.out.println("Recordatorios programados:");
        System.out.println("Tamaño actual: " + planificador.size() + " recordatorios\n");
        
        // Mostrar próximos 3 recordatorios sin eliminarlos
        System.out.println("Próximos recordatorios (en orden de prioridad):");
        System.out.printf("%-10s %-20s %-12s %-30s%n", "ID", "FECHA", "PACIENTE", "MENSAJE");
        System.out.println("-".repeat(75));
        
        for (int i = 0; i < Math.min(3, planificador.size()); i++) {
            Recordatorio r = planificador.proximo();
            if (r != null) {
                System.out.printf("%-10s %-20s %-12s %-30s%n",
                    r.getId(),
                    r.getFecha().format(DateTimeFormatter.ofPattern("dd/MM HH:mm")),
                    r.getDniPaciente(),
                    r.getMensaje());
                    
                // Reprogramar para no perderlo (simulación)
                planificador.programar(r);
            }
        }
        
        System.out.println("\n[Operaciones O(log n) - Min Heap por fecha]");
    }
    
    // ==================== EJERCICIO 6: ÍNDICE DE PACIENTES (HASH) ====================
    
    /**
     * Ejercicio 6: Muestra el índice de pacientes con hash table personalizada
     */
    private void ejercicio6_ConsultarIndicePacientes() {
        System.out.println("-------------------------------------------");
        System.out.println("Índice rápido de Pacientes (Hash con Chaining)");
        System.out.println("-------------------------------------------");
        
        System.out.println("Tamaño tabla = " + indicePacientes.getCapacidad() + 
                          " | Load Factor = " + String.format("%.2f", indicePacientes.getLoadFactor()));
        System.out.println();
        
        // Mostrar estructura interna de la tabla hash
        indicePacientes.mostrarEstructura();
        
        System.out.println("\n[Operaciones O(1) promedio]");
        
        // Permitir búsqueda
        System.out.print("\n¿Buscar un paciente? (DNI o Enter para salir): ");
        String dni = scanner.nextLine().trim();
        
        if (!dni.isEmpty()) {
            Paciente p = indicePacientes.get(dni);
            if (p != null) {
                System.out.println("✓ Paciente encontrado: " + p);
            } else {
                System.out.println("✗ Paciente no encontrado.");
            }
        }
    }
    
    // Los métodos restantes continuarán en la siguiente parte...
    // (Ejercicios 7, 8, 9 y 10)
    
    /**
     * Ejercicio 7: Consolidación de agendas (Merge)
     */
    private void ejercicio7_ConsolidarAgendas() {
        System.out.println("-------------------------------------------");
        System.out.println("[CONSOLIDACIÓN DE AGENDAS - MERGE]");
        System.out.println("-------------------------------------------");
        System.out.println("Esta funcionalidad une dos agendas ordenadas en O(n+m).");
        System.out.println("Detecta y resuelve conflictos de duplicados.");
        System.out.println("\n[Funcionalidad implementada en ConsolidadorAgendas.java]");
    }
    
    /**
     * Ejercicio 8: Reportes con múltiples algoritmos de ordenamiento
     */
    private void ejercicio8_ReportesOrdenamiento() {
        System.out.println("-------------------------------------------");
        System.out.println("[REPORTES OPERATIVOS - ALGORITMOS DE ORDENAMIENTO]");
        System.out.println("-------------------------------------------");
        System.out.println("Implementa:");
        System.out.println("- Insertion Sort (estable)");
        System.out.println("- Shell Sort (gap sequence)");
        System.out.println("- Quick Sort (pivote final)");
        System.out.println("\n[Funcionalidad implementada en ReportesOrdenamiento.java]");
    }
    
    /**
     * Ejercicio 9: Auditoría con Undo/Redo
     * 
     * Sistema interactivo con dos pilas (Undo/Redo) que permite:
     * - Agregar turnos
     * - Cancelar turnos
     * - Reprogramar turnos
     * - Deshacer operaciones (UNDO)
     * - Rehacer operaciones (REDO)
     * 
     * Mantiene invariantes: sin solapamientos de turnos
     */
    private void ejercicio9_AuditoriaUndoRedo() {
        System.out.println("-------------------------------------------");
        System.out.println("AUDITORÍA Y UNDO/REDO (Pilas)");
        System.out.println("-------------------------------------------");
        System.out.println("Sistema interactivo con soporte para Undo/Redo\n");
        
        // Seleccionar médico para trabajar con su agenda
        System.out.println("Médicos disponibles:");
        for (Medico m : medicos.values()) {
            AgendaMedico agenda = agendas.get(m.getMatricula());
            System.out.println("  [" + m.getMatricula() + "] " + m.getNombre() + 
                             " - " + m.getEspecialidad() + 
                             " (" + agenda.size() + " turnos)");
        }
        
        System.out.print("\nIngrese matrícula del médico: ");
        String matricula = scanner.nextLine().trim();
        
        Medico medico = medicos.get(matricula);
        if (medico == null) {
            System.out.println("Médico no encontrado.");
            return;
        }
        
        AgendaMedico agenda = agendas.get(matricula);
        
        System.out.println("\nTrabajando con agenda de: " + medico.getNombre());
        System.out.println("Turnos actuales: " + agenda.size());
        
        // Crear pilas para Undo/Redo
        java.util.Stack<OperacionHistorial> pilaUndo = new java.util.Stack<>();
        java.util.Stack<OperacionHistorial> pilaRedo = new java.util.Stack<>();
        
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n═══════════════════════════════════════════");
            System.out.println("MENÚ UNDO/REDO");
            System.out.println("═══════════════════════════════════════════");
            System.out.println("1) Agregar turno");
            System.out.println("2) Cancelar turno");
            System.out.println("3) Reprogramar turno");
            System.out.println("4) UNDO - Deshacer última operación");
            System.out.println("5) REDO - Rehacer operación");
            System.out.println("6) Ver historial");
            System.out.println("7) Ver agenda actual");
            System.out.println("0) Volver");
            System.out.println("-------------------------------------------");
            System.out.println("Estado: Undo[" + pilaUndo.size() + "] | Redo[" + pilaRedo.size() + "]");
            System.out.println("-------------------------------------------");
            System.out.print("Seleccione opción: ");
            
            try {
                int opcion = Integer.parseInt(scanner.nextLine().trim());
                
                switch (opcion) {
                    case 1: // Agregar turno
                        System.out.print("ID del turno (ej: T-999): ");
                        String id = scanner.nextLine().trim();
                        System.out.print("DNI del paciente: ");
                        String dni = scanner.nextLine().trim();
                        
                        Paciente pac = pacientes.get(dni);
                        if (pac == null) {
                            System.out.println("✗ Paciente no encontrado.");
                            break;
                        }
                        
                        System.out.print("Fecha y hora (dd/MM/yyyy HH:mm): ");
                        String fechaStr = scanner.nextLine().trim();
                        LocalDateTime fecha = LocalDateTime.parse(fechaStr, 
                            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                        
                        System.out.print("Duración (minutos): ");
                        int duracion = Integer.parseInt(scanner.nextLine().trim());
                        
                        System.out.print("Motivo: ");
                        String motivo = scanner.nextLine().trim();
                        
                        Turno nuevoTurno = new Turno(id, dni, matricula, fecha, duracion, motivo);
                        
                        if (agenda.agendar(nuevoTurno)) {
                            String desc = "AGREGAR turno " + id + " (" + dni + ", " + 
                                        fecha.format(DateTimeFormatter.ofPattern("dd/MM HH:mm")) + ")";
                            pilaUndo.push(new OperacionHistorial(
                                OperacionHistorial.TipoOperacion.AGREGAR, nuevoTurno, desc));
                            pilaRedo.clear();  // Nueva operación invalida Redo
                            System.out.println("✓ Turno agregado exitosamente");
                        } else {
                            System.out.println("✗ No se pudo agregar (solapamiento)");
                        }
                        break;
                        
                    case 2: // Cancelar turno
                        System.out.print("ID del turno a cancelar: ");
                        String idCancelar = scanner.nextLine().trim();
                        
                        // Buscar turno en la agenda
                        Turno turnoCancelar = null;
                        for (Turno t : agenda.obtenerTurnosOrdenados()) {
                            if (t.getId().equals(idCancelar)) {
                                turnoCancelar = t;
                                break;
                            }
                        }
                        
                        if (turnoCancelar != null) {
                            if (agenda.cancelar(idCancelar)) {
                                String desc = "CANCELAR turno " + idCancelar;
                                pilaUndo.push(new OperacionHistorial(
                                    OperacionHistorial.TipoOperacion.CANCELAR, turnoCancelar, desc));
                                pilaRedo.clear();
                                System.out.println("✓ Turno cancelado");
                            } else {
                                System.out.println("✗ No se pudo cancelar");
                            }
                        } else {
                            System.out.println("✗ Turno no encontrado");
                        }
                        break;
                        
                    case 3: // Reprogramar turno
                        System.out.print("ID del turno a reprogramar: ");
                        String idReprog = scanner.nextLine().trim();
                        
                        Turno turnoReprog = null;
                        for (Turno t : agenda.obtenerTurnosOrdenados()) {
                            if (t.getId().equals(idReprog)) {
                                turnoReprog = t;
                                break;
                            }
                        }
                        
                        if (turnoReprog != null) {
                            LocalDateTime fechaAntigua = turnoReprog.getFechaHora();
                            
                            System.out.print("Nueva fecha y hora (dd/MM/yyyy HH:mm): ");
                            String nuevaFechaStr = scanner.nextLine().trim();
                            LocalDateTime nuevaFecha = LocalDateTime.parse(nuevaFechaStr,
                                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                            
                            // Cancelar turno viejo
                            if (agenda.cancelar(idReprog)) {
                                // Crear turno nuevo con nueva fecha
                                Turno turnoNuevo = new Turno(
                                    turnoReprog.getId(),
                                    turnoReprog.getDniPaciente(),
                                    turnoReprog.getMatriculaMedico(),
                                    nuevaFecha,
                                    turnoReprog.getDuracionMin(),
                                    turnoReprog.getMotivo()
                                );
                                
                                if (agenda.agendar(turnoNuevo)) {
                                    String desc = "REPROGRAMAR turno " + idReprog + " de " +
                                                fechaAntigua.format(DateTimeFormatter.ofPattern("dd/MM HH:mm")) +
                                                " a " + nuevaFecha.format(DateTimeFormatter.ofPattern("dd/MM HH:mm"));
                                    pilaUndo.push(new OperacionHistorial(
                                        OperacionHistorial.TipoOperacion.REPROGRAMAR, 
                                        turnoNuevo, fechaAntigua, desc));
                                    pilaRedo.clear();
                                    System.out.println("✓ Turno reprogramado");
                                } else {
                                    // Revertir cancelación
                                    agenda.agendar(turnoReprog);
                                    System.out.println("✗ No se pudo reprogramar (solapamiento)");
                                }
                            }
                        } else {
                            System.out.println("✗ Turno no encontrado");
                        }
                        break;
                        
                    case 4: // UNDO
                        if (pilaUndo.isEmpty()) {
                            System.out.println("✗ No hay operaciones para deshacer");
                        } else {
                            OperacionHistorial op = pilaUndo.pop();
                            System.out.println("↶ UNDO: " + op.descripcion);
                            
                            switch (op.tipo) {
                                case AGREGAR:
                                    // Deshacer AGREGAR = cancelar turno
                                    agenda.cancelar(op.turno.getId());
                                    break;
                                case CANCELAR:
                                    // Deshacer CANCELAR = reagregar turno
                                    agenda.agendar(op.turno);
                                    break;
                                case REPROGRAMAR:
                                    // Deshacer REPROGRAMAR = volver a fecha anterior
                                    agenda.cancelar(op.turno.getId());
                                    Turno turnoAntiguo = new Turno(
                                        op.turno.getId(),
                                        op.turno.getDniPaciente(),
                                        op.turno.getMatriculaMedico(),
                                        op.fechaAnterior,
                                        op.turno.getDuracionMin(),
                                        op.turno.getMotivo()
                                    );
                                    agenda.agendar(turnoAntiguo);
                                    break;
                            }
                            
                            pilaRedo.push(op);
                            System.out.println("✓ Operación deshecha");
                        }
                        break;
                        
                    case 5: // REDO
                        if (pilaRedo.isEmpty()) {
                            System.out.println("✗ No hay operaciones para rehacer");
                        } else {
                            OperacionHistorial op = pilaRedo.pop();
                            System.out.println("↷ REDO: " + op.descripcion);
                            
                            switch (op.tipo) {
                                case AGREGAR:
                                    agenda.agendar(op.turno);
                                    break;
                                case CANCELAR:
                                    agenda.cancelar(op.turno.getId());
                                    break;
                                case REPROGRAMAR:
                                    // Cancelar versión anterior y agregar versión nueva
                                    Turno turnoViejo = new Turno(
                                        op.turno.getId(),
                                        op.turno.getDniPaciente(),
                                        op.turno.getMatriculaMedico(),
                                        op.fechaAnterior,
                                        op.turno.getDuracionMin(),
                                        op.turno.getMotivo()
                                    );
                                    agenda.cancelar(turnoViejo.getId());
                                    agenda.agendar(op.turno);
                                    break;
                            }
                            
                            pilaUndo.push(op);
                            System.out.println("✓ Operación rehecha");
                        }
                        break;
                        
                    case 6: // Ver historial
                        System.out.println("\n══ HISTORIAL DE OPERACIONES ══");
                        System.out.println("\nPila Undo (" + pilaUndo.size() + " operaciones):");
                        if (pilaUndo.isEmpty()) {
                            System.out.println("  (vacía)");
                        } else {
                            for (int i = pilaUndo.size() - 1; i >= 0; i--) {
                                System.out.println("  [" + (i+1) + "] " + pilaUndo.get(i).descripcion);
                            }
                        }
                        
                        System.out.println("\nPila Redo (" + pilaRedo.size() + " operaciones):");
                        if (pilaRedo.isEmpty()) {
                            System.out.println("  (vacía)");
                        } else {
                            for (int i = pilaRedo.size() - 1; i >= 0; i--) {
                                System.out.println("  [" + (i+1) + "] " + pilaRedo.get(i).descripcion);
                            }
                        }
                        break;
                        
                    case 7: // Ver agenda
                        System.out.println("\n══ AGENDA ACTUAL ══");
                        System.out.println("Médico: " + medico.getNombre());
                        System.out.println("Total turnos: " + agenda.size());
                        
                        List<Turno> turnos = agenda.obtenerTurnosOrdenados();
                        if (turnos.isEmpty()) {
                            System.out.println("(sin turnos)");
                        } else {
                            System.out.println("\nID         PACIENTE     FECHA Y HORA       DURACIÓN");
                            System.out.println("--------------------------------------------------------------");
                            for (Turno t : turnos) {
                                System.out.printf("%-10s %-12s %s  %3d min\n",
                                    t.getId(),
                                    t.getDniPaciente(),
                                    t.getFechaHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                                    t.getDuracionMin());
                            }
                        }
                        break;
                        
                    case 0:
                        continuar = false;
                        break;
                        
                    default:
                        System.out.println("Opción inválida");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        
        System.out.println("\n[Estructuras utilizadas: 2 Pilas LIFO]");
        System.out.println("[Complejidad: O(1) para push/pop, O(log n) para modificar agenda AVL]");
        System.out.println("[Invariante: Sin solapamientos de turnos en todo momento]");
    }
    
    /**
     * Ejercicio 10: Planificador de quirófano
     */
    private void ejercicio10_PlanificadorQuirofano() {
        System.out.println("-------------------------------------------");
        System.out.println("PLANIFICADOR DE QUIRÓFANO (MinHeap + Top-K)");
        System.out.println("-------------------------------------------");
        
        // Procesar algunas cirugías de ejemplo
        LocalDateTime base = LocalDateTime.now().plusDays(1).withHour(8).withMinute(0);
        
        planificadorQuirofano.procesar(new SolicitudCirugia(
            "C-001", "M-1001", 180, base.plusHours(2)));
        planificadorQuirofano.procesar(new SolicitudCirugia(
            "C-002", "M-1002", 120, base.plusHours(3)));
        planificadorQuirofano.procesar(new SolicitudCirugia(
            "C-003", "M-1001", 90, base.plusHours(4)));
        planificadorQuirofano.procesar(new SolicitudCirugia(
            "C-004", "M-1003", 150, base.plusHours(5)));
        
        System.out.println("✓ Cirugías procesadas y asignadas a quirófanos.");
        System.out.println("\nTop 3 médicos más ocupados:");
        
        List<String> top3 = planificadorQuirofano.topKMedicosBloqueados(3);
        for (int i = 0; i < top3.size(); i++) {
            System.out.println((i + 1) + ") " + top3.get(i));
        }
        
        System.out.println("\n[Operaciones O(log Q + log K)]");
    }
    
    // ==================== CLASES INTERNAS ====================
    
    /**
     * Clase interna para representar operaciones en el historial Undo/Redo
     */
    private static class OperacionHistorial {
        enum TipoOperacion { AGREGAR, CANCELAR, REPROGRAMAR }
        
        TipoOperacion tipo;
        Turno turno;
        LocalDateTime fechaAnterior; // Solo para REPROGRAMAR
        String descripcion;
        
        // Constructor para AGREGAR y CANCELAR
        OperacionHistorial(TipoOperacion tipo, Turno turno, String descripcion) {
            this.tipo = tipo;
            this.turno = turno;
            this.descripcion = descripcion;
            this.fechaAnterior = null;
        }
        
        // Constructor para REPROGRAMAR
        OperacionHistorial(TipoOperacion tipo, Turno turno, LocalDateTime fechaAnterior, String descripcion) {
            this.tipo = tipo;
            this.turno = turno;
            this.fechaAnterior = fechaAnterior;
            this.descripcion = descripcion;
        }
    }
    
    // ==================== MAIN ====================
    
    /**
     * Método principal - punto de entrada del programa
     */
    public static void main(String[] args) {
        SistemaGestionTurnosMedicos sistema = new SistemaGestionTurnosMedicos();
        sistema.cargarDatosIniciales();
        sistema.mostrarMenu();
    }
}
