package edu.informatica3.lucas_antun.practico_integrador.estructuras;

import edu.informatica3.lucas_antun.practico_integrador.modelo.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * PlanificadorQuirofano - Asignación de quirófanos y Top-K médicos ocupados
 * 
 * EJERCICIO 10: Planificador de quirófano con asignación y top-K cuellos de botella
 * 
 * Este sistema maneja dos problemas simultáneamente:
 * 
 * 1. ASIGNACIÓN DE QUIRÓFANOS:
 *    - Asignar cada cirugía al primer quirófano libre que cumpla el deadline
 *    - Usar Min-Heap de quirófanos ordenados por tiempo de liberación
 * 
 * 2. TOP-K MÉDICOS MÁS OCUPADOS:
 *    - Mantener en tiempo real los K médicos con más minutos bloqueados
 *    - Usar Max-Heap de tamaño limitado K (o manejar todos y extraer top-K)
 * 
 * ¿POR QUÉ MIN-HEAP PARA QUIRÓFANOS?
 * 
 * Queremos asignar al quirófano que se libere primero (el "más disponible").
 * Min-Heap nos da el quirófano con menor tiempo de finalización en O(1).
 * 
 * ALTERNATIVAS:
 * 1. Iterar array de quirófanos y buscar el mínimo: O(Q)
 * 2. Mantener lista ordenada: O(Q) para mantener orden
 * 3. MIN-HEAP: O(log Q) para actualizar, O(1) para consultar mínimo ✓
 * 
 * ¿POR QUÉ RASTREAR TOP-K?
 * 
 * En el contexto médico, identificar a los médicos más ocupados es clave para:
 * - Planificación de recursos
 * - Detectar cuellos de botella
 * - Balanceo de carga
 * 
 * ESTRATEGIA PARA TOP-K:
 * - Mantener un mapa: Matrícula -> MinutosBloqueados
 * - Al consultar top-K, ordenar y tomar los K mayores
 * - Complejidad: O(M log M) donde M = cantidad de médicos (generalmente pequeño)
 * 
 * COMPLEJIDAD POR EVENTO (procesar cirugía):
 * - Extraer quirófano libre: O(log Q)
 * - Actualizar quirófano: O(log Q)
 * - Actualizar contador médico: O(1)
 * - Total: O(log Q) por cirugía ✓ (cumple requisito O(log Q + log K))
 * 
 * @author Lucas Santiago Said Antun
 * @author Federico Fernández
 * @version 1.0
 */
public class PlanificadorQuirofano {
    
    // Min-Heap de quirófanos ordenados por disponibleDesde
    // El quirófano que se libere primero está en la raíz
    private PriorityQueue<Quirofano> quirofanos;
    
    // Mapa: Matrícula -> Minutos totales bloqueados
    // Para calcular top-K médicos más ocupados
    private Map<String, Integer> minutosPorMedico;
    
    // Cantidad de quirófanos disponibles
    private int cantidadQuirofanos;
    
    /**
     * Constructor
     * 
     * @param cantidadQuirofanos Cantidad de quirófanos disponibles
     */
    public PlanificadorQuirofano(int cantidadQuirofanos) {
        if (cantidadQuirofanos <= 0) {
            throw new IllegalArgumentException("Debe haber al menos un quirófano");
        }
        
        this.cantidadQuirofanos = cantidadQuirofanos;
        this.minutosPorMedico = new HashMap<>();
        
        // Inicializar min-heap con comparador por disponibleDesde
        this.quirofanos = new PriorityQueue<>((q1, q2) -> 
            q1.disponibleDesde.compareTo(q2.disponibleDesde)
        );
        
        // Crear quirófanos inicialmente todos disponibles "ahora"
        LocalDateTime ahora = LocalDateTime.now();
        for (int i = 1; i <= cantidadQuirofanos; i++) {
            quirofanos.add(new Quirofano("Q" + i, ahora));
        }
    }
    
    /**
     * Procesa una solicitud de cirugía
     * 
     * Asigna la cirugía al primer quirófano libre que pueda cumplir el deadline.
     * Actualiza el contador de minutos bloqueados del médico.
     * 
     * Complejidad: O(log Q) donde Q = cantidad de quirófanos
     * - Extraer mínimo del heap: O(log Q)
     * - Actualizar y re-insertar: O(log Q)
     * 
     * @param solicitud Solicitud de cirugía a procesar
     * @return true si se asignó exitosamente, false si no hay quirófano disponible
     */
    public boolean procesar(SolicitudCirugia solicitud) {
        // Extraer el quirófano que se libera primero (raíz del min-heap)
        Quirofano quirofano = quirofanos.poll();
        
        if (quirofano == null) {
            return false; // No hay quirófanos (no debería pasar)
        }
        
        // Verificar si el quirófano puede completar la cirugía antes del deadline
        LocalDateTime inicioReal = quirofano.disponibleDesde.isAfter(LocalDateTime.now()) 
                                   ? quirofano.disponibleDesde 
                                   : LocalDateTime.now();
        
        LocalDateTime finCirugia = inicioReal.plusMinutes(solicitud.getDuracionMin());
        
        if (finCirugia.isAfter(solicitud.getDeadline())) {
            // No se puede cumplir el deadline
            quirofanos.add(quirofano); // Devolver al heap
            return false;
        }
        
        // Asignar cirugía al quirófano
        System.out.println("✓ Cirugía " + solicitud.getId() + " asignada a " + quirofano.id);
        System.out.println("  Médico: " + solicitud.getMatriculaMedico());
        System.out.println("  Inicio: " + inicioReal);
        System.out.println("  Fin: " + finCirugia);
        
        // Actualizar disponibilidad del quirófano
        quirofano.disponibleDesde = finCirugia;
        
        // Re-insertar quirófano en el heap con nueva disponibilidad
        quirofanos.add(quirofano);
        
        // Actualizar minutos bloqueados del médico
        String matricula = solicitud.getMatriculaMedico();
        minutosPorMedico.put(matricula, 
            minutosPorMedico.getOrDefault(matricula, 0) + solicitud.getDuracionMin());
        
        return true;
    }
    
    /**
     * Obtiene los K médicos con más minutos bloqueados
     * 
     * Estrategia:
     * 1. Convertir mapa a lista de pares (médico, minutos)
     * 2. Ordenar por minutos descendente
     * 3. Tomar los K primeros
     * 
     * Complejidad: O(M log M) donde M = cantidad de médicos
     * 
     * NOTA: Para sistemas con muchos médicos, se podría usar un Max-Heap
     * de tamaño limitado K para mantener complejidad O(M log K). Pero como
     * en un hospital hay pocos médicos (decenas, no miles), O(M log M) es
     * aceptable y más simple.
     * 
     * @param K Cantidad de médicos a retornar
     * @return Lista de Strings con formato "Dr. X - Y hs"
     */
    public List<String> topKMedicosBloqueados(int K) {
        // Convertir mapa a lista de pares
        List<Map.Entry<String, Integer>> lista = new ArrayList<>(minutosPorMedico.entrySet());
        
        // Ordenar por minutos descendente
        lista.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        
        // Tomar los K primeros
        List<String> resultado = new ArrayList<>();
        int limite = Math.min(K, lista.size());
        
        for (int i = 0; i < limite; i++) {
            Map.Entry<String, Integer> entry = lista.get(i);
            String matricula = entry.getKey();
            int minutos = entry.getValue();
            double horas = minutos / 60.0;
            
            resultado.add(String.format("%s - %.1f hs", matricula, horas));
        }
        
        return resultado;
    }
    
    /**
     * Obtiene la cantidad de quirófanos
     * 
     * @return Cantidad de quirófanos
     */
    public int getCantidadQuirofanos() {
        return cantidadQuirofanos;
    }
    
    /**
     * Obtiene el mapa completo de minutos por médico
     * Para estadísticas y depuración
     * 
     * @return Mapa matrícula -> minutos
     */
    public Map<String, Integer> getMinutosPorMedico() {
        return new HashMap<>(minutosPorMedico);
    }
    
    /**
     * Reinicia el planificador
     * Útil para empezar una nueva semana/mes
     */
    public void reiniciar() {
        quirofanos.clear();
        minutosPorMedico.clear();
        
        LocalDateTime ahora = LocalDateTime.now();
        for (int i = 1; i <= cantidadQuirofanos; i++) {
            quirofanos.add(new Quirofano("Q" + i, ahora));
        }
    }
    
    /**
     * Representación en String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PlanificadorQuirofano{\n");
        sb.append("  Quirófanos: ").append(cantidadQuirofanos).append("\n");
        sb.append("  Médicos registrados: ").append(minutosPorMedico.size()).append("\n");
        sb.append("  Minutos por médico:\n");
        
        for (Map.Entry<String, Integer> entry : minutosPorMedico.entrySet()) {
            sb.append("    ").append(entry.getKey()).append(": ")
              .append(entry.getValue()).append(" min\n");
        }
        
        sb.append("}");
        return sb.toString();
    }
}
