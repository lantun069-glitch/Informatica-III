package edu.informatica3.lucas_antun.practico_integrador;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PlanificadorRecordatorios - Implementación con Min-Heap
 * 
 * EJERCICIO 5: Recordatorios y llamados con planificador por prioridad temporal
 * 
 * Esta clase mantiene recordatorios ordenados por fecha usando un Min-Heap (montículo mínimo).
 * El recordatorio con fecha más próxima siempre está en la raíz del heap.
 * 
 * ¿POR QUÉ USAMOS MIN-HEAP?
 * 
 * 1. Necesitamos acceso rápido al recordatorio MÁS PRÓXIMO en fecha
 * 2. Los recordatorios se procesan en orden cronológico
 * 3. Min-Heap mantiene el mínimo (fecha más cercana) en la raíz
 * 
 * COMPARACIÓN CON OTRAS ESTRUCTURAS:
 * 
 * 1. Array Ordenado:
 *    - Inserción: O(n) - necesita mantener orden
 *    - Obtener mínimo: O(1)
 *    - Eliminar mínimo: O(n) - necesita desplazar elementos
 *    - Reprogramar: O(n)
 * 
 * 2. Lista Enlazada Ordenada:
 *    - Inserción: O(n) - búsqueda de posición
 *    - Obtener mínimo: O(1)
 *    - Eliminar mínimo: O(1)
 *    - Reprogramar: O(n)
 * 
 * 3. BST/AVL:
 *    - Inserción: O(log n)
 *    - Obtener mínimo: O(log n) - necesita bajar a la izquierda
 *    - Eliminar mínimo: O(log n)
 *    - Reprogramar: O(log n)
 *    - Más complejo de implementar que heap
 * 
 * 4. MIN-HEAP (nuestra elección):
 *    - Inserción: O(log n) - bubble up
 *    - Obtener mínimo: O(1) - siempre en raíz
 *    - Eliminar mínimo: O(log n) - bubble down
 *    - Reprogramar: O(log n)
 *    - Implementación más simple que AVL
 *    - Ideal cuando solo importa el mínimo (no necesitamos recorrido ordenado completo)
 * 
 * REPRESENTACIÓN DEL HEAP:
 * Usamos un ArrayList para almacenar el heap de forma compacta.
 * Para un nodo en posición i:
 * - Padre está en: (i-1)/2
 * - Hijo izquierdo está en: 2*i + 1
 * - Hijo derecho está en: 2*i + 2
 * 
 * COMPLEJIDADES:
 * - programar(): O(log n)
 * - proximo(): O(log n) - extrae y elimina
 * - reprogramar(): O(log n) - buscar + reposicionar
 * - size(): O(1)
 * 
 * @author Lucas Antun
 * @version 1.0
 */
public class PlanificadorRecordatorios {
    
    // Heap implementado con ArrayList
    // heap.get(0) siempre es el recordatorio con fecha más próxima
    private List<Recordatorio> heap;
    
    // Mapa para búsqueda rápida de recordatorios por ID
    // Necesario para la operación reprogramar()
    private Map<String, Integer> posiciones; // ID -> índice en heap
    
    /**
     * Constructor
     * Inicializa el heap vacío
     */
    public PlanificadorRecordatorios() {
        this.heap = new ArrayList<>();
        this.posiciones = new HashMap<>();
    }
    
    /**
     * Programa un nuevo recordatorio
     * 
     * Complejidad: O(log n)
     * - Agregar al final: O(1)
     * - Bubble up: O(log n)
     * 
     * @param recordatorio Recordatorio a programar
     */
    public void programar(Recordatorio recordatorio) {
        // Agregar al final del heap
        heap.add(recordatorio);
        int index = heap.size() - 1;
        posiciones.put(recordatorio.getId(), index);
        
        // Subir el elemento hasta su posición correcta (bubble up)
        bubbleUp(index);
    }
    
    /**
     * Obtiene y elimina el recordatorio más próximo
     * 
     * Complejidad: O(log n)
     * - Acceso a raíz: O(1)
     * - Reemplazar con último: O(1)
     * - Bubble down: O(log n)
     * 
     * @return Recordatorio con fecha más próxima, null si el heap está vacío
     */
    public Recordatorio proximo() {
        if (heap.isEmpty()) {
            return null;
        }
        
        // El mínimo siempre está en la raíz
        Recordatorio min = heap.get(0);
        posiciones.remove(min.getId());
        
        // Reemplazar raíz con el último elemento
        Recordatorio ultimo = heap.remove(heap.size() - 1);
        
        if (!heap.isEmpty()) {
            heap.set(0, ultimo);
            posiciones.put(ultimo.getId(), 0);
            // Bajar el elemento hasta su posición correcta (bubble down)
            bubbleDown(0);
        }
        
        return min;
    }
    
    /**
     * Reprograma un recordatorio a una nueva fecha
     * 
     * Complejidad: O(log n)
     * - Búsqueda por ID: O(1) gracias al mapa
     * - Actualizar y reposicionar: O(log n)
     * 
     * @param id ID del recordatorio a reprogramar
     * @param nuevaFecha Nueva fecha y hora
     * @return true si se reprogramó, false si no se encontró el ID
     */
    public boolean reprogramar(String id, LocalDateTime nuevaFecha) {
        Integer index = posiciones.get(id);
        if (index == null || index >= heap.size()) {
            return false; // No encontrado
        }
        
        Recordatorio recordatorio = heap.get(index);
        LocalDateTime fechaAnterior = recordatorio.getFecha();
        recordatorio.setFecha(nuevaFecha);
        
        // Determinar si hay que subir o bajar en el heap
        if (nuevaFecha.isBefore(fechaAnterior)) {
            // La nueva fecha es más temprana -> subir (mayor prioridad)
            bubbleUp(index);
        } else {
            // La nueva fecha es más tardía -> bajar (menor prioridad)
            bubbleDown(index);
        }
        
        return true;
    }
    
    /**
     * Obtiene la cantidad de recordatorios programados
     * 
     * @return Tamaño del heap
     */
    public int size() {
        return heap.size();
    }
    
    /**
     * Verifica si el planificador está vacío
     * 
     * @return true si está vacío, false en caso contrario
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }
    
    // ==================== OPERACIONES INTERNAS DEL HEAP ====================
    
    /**
     * Bubble Up: Sube un elemento en el heap hasta su posición correcta
     * 
     * Se usa después de insertar un elemento al final.
     * Compara el elemento con su padre y los intercambia si es necesario.
     * 
     * Complejidad: O(log n) - en el peor caso sube hasta la raíz
     * 
     * @param index Índice del elemento a subir
     */
    private void bubbleUp(int index) {
        while (index > 0) {
            int indicePadre = (index - 1) / 2;
            
            // Si el elemento es mayor o igual que su padre, está en posición correcta
            if (compararFechas(heap.get(index), heap.get(indicePadre)) >= 0) {
                break;
            }
            
            // Intercambiar con el padre
            intercambiar(index, indicePadre);
            index = indicePadre;
        }
    }
    
    /**
     * Bubble Down: Baja un elemento en el heap hasta su posición correcta
     * 
     * Se usa después de eliminar la raíz y reemplazarla con el último elemento.
     * Compara el elemento con sus hijos y lo intercambia con el menor si es necesario.
     * 
     * Complejidad: O(log n) - en el peor caso baja hasta las hojas
     * 
     * @param index Índice del elemento a bajar
     */
    private void bubbleDown(int index) {
        int size = heap.size();
        
        while (true) {
            int indiceIzq = 2 * index + 1;
            int indiceDer = 2 * index + 2;
            int indiceMenor = index;
            
            // Comparar con hijo izquierdo
            if (indiceIzq < size && 
                compararFechas(heap.get(indiceIzq), heap.get(indiceMenor)) < 0) {
                indiceMenor = indiceIzq;
            }
            
            // Comparar con hijo derecho
            if (indiceDer < size && 
                compararFechas(heap.get(indiceDer), heap.get(indiceMenor)) < 0) {
                indiceMenor = indiceDer;
            }
            
            // Si el elemento es menor o igual que sus hijos, está en posición correcta
            if (indiceMenor == index) {
                break;
            }
            
            // Intercambiar con el hijo menor
            intercambiar(index, indiceMenor);
            index = indiceMenor;
        }
    }
    
    /**
     * Intercambia dos elementos en el heap y actualiza el mapa de posiciones
     * 
     * @param i Índice del primer elemento
     * @param j Índice del segundo elemento
     */
    private void intercambiar(int i, int j) {
        Recordatorio temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
        
        // Actualizar mapa de posiciones
        posiciones.put(heap.get(i).getId(), i);
        posiciones.put(heap.get(j).getId(), j);
    }
    
    /**
     * Compara dos recordatorios por fecha
     * 
     * @param r1 Primer recordatorio
     * @param r2 Segundo recordatorio
     * @return < 0 si r1 < r2, 0 si son iguales, > 0 si r1 > r2
     */
    private int compararFechas(Recordatorio r1, Recordatorio r2) {
        return r1.getFecha().compareTo(r2.getFecha());
    }
    
    /**
     * Representación en String para depuración
     * Muestra el heap en forma de array
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MinHeap{\n");
        sb.append("  Tamaño: ").append(heap.size()).append("\n");
        sb.append("  Elementos (orden de heap): [\n");
        
        for (int i = 0; i < heap.size(); i++) {
            Recordatorio r = heap.get(i);
            sb.append("    [").append(i).append("] ")
              .append(r.getId()).append(" -> ")
              .append(r.getFecha())
              .append("\n");
        }
        
        sb.append("  ]\n}");
        return sb.toString();
    }
}
