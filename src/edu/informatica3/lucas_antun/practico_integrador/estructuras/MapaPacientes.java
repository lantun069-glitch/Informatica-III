package edu.informatica3.lucas_antun.practico_integrador.estructuras;

import edu.informatica3.lucas_antun.practico_integrador.modelo.*;
import edu.informatica3.lucas_antun.practico_integrador.nodos.NodoHashMap;
import edu.informatica3.lucas_antun.practico_integrador.utils.Lista;

/**
 * MapaPacientes - Implementación de Hash Table con Chaining y Rehashing
 * 
 * EJERCICIO 6: Índice rápido de pacientes (Hash con chaining + rehash)
 * 
 * Esta clase implementa una tabla hash personalizada desde cero, sin usar
 * HashMap de Java. Incluye:
 * - Hash con encadenamiento (chaining) para manejar colisiones
 * - Rehashing automático cuando load factor > 0.75
 * - Función hash bien distribuida
 * 
 * ¿POR QUÉ HASH TABLE?
 * 
 * Necesitamos búsquedas/altas/bajas por DNI en O(1) promedio.
 * 
 * 1. Array Simple:
 *    - Búsqueda: O(n)
 *    - No es viable para muchos pacientes
 * 
 * 2. Árbol BST/AVL:
 *    - Búsqueda: O(log n)
 *    - Mejor que array pero no O(1)
 * 
 * 3. HASH TABLE (nuestra elección):
 *    - Búsqueda promedio: O(1)
 *    - Peor caso: O(n) si todas las claves colisionan (muy raro con buen hash)
 *    - Ideal para búsquedas por clave única (DNI)
 * 
 * MANEJO DE COLISIONES: CHAINING vs OPEN ADDRESSING
 * 
 * 1. Open Addressing (Linear Probing, Quadratic Probing, Double Hashing):
 *    - Almacena todo en el array principal
 *    - Busca la siguiente posición libre cuando hay colisión
 *    - Problema: clustering (agrupamiento)
 *    - Eliminación es compleja (necesita marcadores)
 * 
 * 2. CHAINING (nuestra elección):
 *    - Cada posición del array tiene una lista enlazada
 *    - Múltiples elementos pueden estar en la misma posición
 *    - Más simple de implementar
 *    - Eliminación es directa
 *    - Funciona bien incluso con load factor > 1
 * 
 * FUNCIÓN HASH:
 * Usamos el método polynomial rolling hash para Strings:
 * hash(s) = (s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]) % tableSize
 * 
 * ¿Por qué 31?
 * - Es primo (buena distribución)
 * - Es pequeño (no causa overflow rápido)
 * - Multiplicar por 31 = (x << 5) - x (optimización del compilador)
 * - Es la constante usada por String.hashCode() de Java
 * 
 * REHASHING:
 * Cuando loadFactor = size/capacity > 0.75:
 * - Crear nueva tabla con capacidad * 2
 * - Re-insertar todos los elementos
 * - Actualizar referencias
 * Mantiene operaciones O(1) amortizado
 * 
 * COMPLEJIDADES:
 * - put(): O(1) promedio, O(n) cuando hace rehash
 * - get(): O(1) promedio
 * - remove(): O(1) promedio
 * - containsKey(): O(1) promedio
 * 
 * @author Lucas Santiago Said Antun
 * @author Federico Fernández
 * @version 1.0
 */
public class MapaPacientes {
    
    // Array de buckets (cada bucket es una lista enlazada)
    private NodoHashMap[] tabla;
    
    // Capacidad actual de la tabla
    private int capacidad;
    
    // Cantidad de elementos almacenados
    private int tamanio;
    
    // Factor de carga máximo antes de hacer rehash
    private static final double MAX_LOAD_FACTOR = 0.75;
    
    /**
     * Constructor
     * 
     * @param capacidadInicial Capacidad inicial de la tabla
     */
    public MapaPacientes(int capacidadInicial) {
        if (capacidadInicial <= 0) {
            throw new IllegalArgumentException("Capacidad debe ser mayor a 0");
        }
        this.capacidad = capacidadInicial;
        this.tabla = new NodoHashMap[capacidad];
        this.tamanio = 0;
    }
    
    /**
     * Inserta o actualiza un paciente
     * 
     * Complejidad: O(1) promedio
     * - Calcular hash: O(k) donde k = longitud del DNI (constante pequeña)
     * - Buscar en bucket: O(m) donde m = elementos en el bucket (pequeño si hash es bueno)
     * - Total: O(1) amortizado
     * 
     * @param dni DNI del paciente (clave)
     * @param paciente Paciente a almacenar (valor)
     */
    public void put(String dni, Paciente paciente) {
        // Verificar si necesitamos hacer rehash
        if (getLoadFactor() > MAX_LOAD_FACTOR) {
            rehash();
        }
        
        // Calcular índice del bucket
        int index = hash(dni);
        
        // Buscar si el DNI ya existe en el bucket
        NodoHashMap actual = tabla[index];
        while (actual != null) {
            if (actual.dni.equals(dni)) {
                // DNI ya existe, actualizar paciente
                actual.paciente = paciente;
                return;
            }
            actual = actual.siguiente;
        }
        
        // DNI no existe, agregar nuevo nodo al inicio del bucket
        NodoHashMap nuevoNodo = new NodoHashMap(dni, paciente);
        nuevoNodo.siguiente = tabla[index];
        tabla[index] = nuevoNodo;
        tamanio++;
    }
    
    /**
     * Obtiene un paciente por DNI
     * 
     * Complejidad: O(1) promedio
     * 
     * @param dni DNI del paciente a buscar
     * @return Paciente encontrado, null si no existe
     */
    public Paciente get(String dni) {
        int index = hash(dni);
        
        // Recorrer la lista en el bucket
        NodoHashMap actual = tabla[index];
        while (actual != null) {
            if (actual.dni.equals(dni)) {
                return actual.paciente; // Encontrado
            }
            actual = actual.siguiente;
        }
        
        return null; // No encontrado
    }
    
    /**
     * Elimina un paciente por DNI
     * 
     * Complejidad: O(1) promedio
     * 
     * @param dni DNI del paciente a eliminar
     * @return true si se eliminó, false si no existía
     */
    public boolean remove(String dni) {
        int index = hash(dni);
        
        NodoHashMap actual = tabla[index];
        NodoHashMap anterior = null;
        
        while (actual != null) {
            if (actual.dni.equals(dni)) {
                // Encontrado, eliminar
                if (anterior == null) {
                    // Es el primer nodo del bucket
                    tabla[index] = actual.siguiente;
                } else {
                    // Es un nodo intermedio o final
                    anterior.siguiente = actual.siguiente;
                }
                tamanio--;
                return true;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
        
        return false; // No encontrado
    }
    
    /**
     * Verifica si existe un DNI en el mapa
     * 
     * Complejidad: O(1) promedio
     * 
     * @param dni DNI a verificar
     * @return true si existe, false en caso contrario
     */
    public boolean containsKey(String dni) {
        return get(dni) != null;
    }
    
    /**
     * Obtiene la cantidad de elementos
     * 
     * @return Tamaño del mapa
     */
    public int size() {
        return tamanio;
    }
    
    /**
     * Obtiene todas las claves (DNIs) almacenadas
     * 
     * @return Lista de DNIs
     */
    public Lista<String> keys() {
        Lista<String> claves = new Lista<>();
        
        for (int i = 0; i < capacidad; i++) {
            NodoHashMap actual = tabla[i];
            while (actual != null) {
                claves.add(actual.dni);
                actual = actual.siguiente;
            }
        }
        
        return claves;
    }
    
    /**
     * Obtiene la capacidad actual de la tabla
     * 
     * @return Capacidad
     */
    public int getCapacidad() {
        return capacidad;
    }
    
    /**
     * Calcula el load factor actual
     * 
     * LoadFactor = cantidad de elementos / capacidad de la tabla
     * Indica qué tan llena está la tabla
     * 
     * @return Load factor entre 0.0 y 1.0 (o mayor)
     */
    public double getLoadFactor() {
        return (double) tamanio / capacidad;
    }
    
    // ==================== OPERACIONES INTERNAS ====================
    
    /**
     * Función hash para Strings (DNI)
     * 
     * Implementa polynomial rolling hash con constante 31
     * 
     * Esta función es crucial para el rendimiento de la hash table.
     * Una mala función hash causa muchas colisiones → degrada a O(n).
     * Una buena función hash distribuye uniformemente → mantiene O(1).
     * 
     * @param dni DNI a hashear
     * @return Índice en el array [0, capacidad-1]
     */
    private int hash(String dni) {
        if (dni == null) {
            return 0;
        }
        
        int hash = 0;
        
        // Polynomial rolling hash
        for (int i = 0; i < dni.length(); i++) {
            hash = hash * 31 + dni.charAt(i);
        }
        
        // Asegurar resultado positivo y dentro del rango [0, capacidad-1]
        // Math.abs puede devolver negativo si hash = Integer.MIN_VALUE
        // Usamos & 0x7FFFFFFF para forzar positivo
        return (hash & 0x7FFFFFFF) % capacidad;
    }
    
    /**
     * Rehashing: Redimensiona la tabla cuando el load factor es muy alto
     * 
     * Proceso:
     * 1. Crear nueva tabla con capacidad * 2
     * 2. Re-insertar todos los elementos (recalcular hash para nuevo tamaño)
     * 3. Reemplazar tabla antigua con nueva
     * 
     * Complejidad: O(n) donde n = cantidad de elementos
     * Pero ocurre raramente, entonces put() es O(1) amortizado
     */
    private void rehash() {
        System.out.println("[Rehashing: " + capacidad + " -> " + (capacidad * 2) + "]");
        
        // Guardar referencia a la tabla antigua
        NodoHashMap[] tablaAntigua = tabla;
        int capacidadAntigua = capacidad;
        
        // Crear nueva tabla con el doble de capacidad
        capacidad = capacidad * 2;
        tabla = new NodoHashMap[capacidad];
        tamanio = 0; // Se re-contará al insertar
        
        // Re-insertar todos los elementos
        for (int i = 0; i < capacidadAntigua; i++) {
            NodoHashMap actual = tablaAntigua[i];
            while (actual != null) {
                put(actual.dni, actual.paciente);
                actual = actual.siguiente;
            }
        }
    }
    
    /**
     * Muestra la estructura interna de la hash table
     * Útil para visualizar colisiones y distribución
     */
    public void mostrarEstructura() {
        int bucketsVacios = 0;
        int maxColisiones = 0;
        
        for (int i = 0; i < capacidad; i++) {
            int colisiones = 0;
            NodoHashMap actual = tabla[i];
            
            if (actual == null) {
                bucketsVacios++;
            } else {
                System.out.print("[Bucket " + i + "] → ");
                while (actual != null) {
                    System.out.print("(" + actual.dni + ", \"" + 
                                   actual.paciente.getNombre() + "\")");
                    if (actual.siguiente != null) {
                        System.out.print(" → ");
                        colisiones++;
                    }
                    actual = actual.siguiente;
                }
                System.out.println();
                
                if (colisiones > maxColisiones) {
                    maxColisiones = colisiones;
                }
            }
        }
        
        System.out.println("\nEstadísticas:");
        System.out.println("- Buckets vacíos: " + bucketsVacios + "/" + capacidad);
        System.out.println("- Máxima cadena de colisiones: " + maxColisiones);
        System.out.println("- Load factor: " + String.format("%.2f", getLoadFactor()));
        
        if (getLoadFactor() > MAX_LOAD_FACTOR) {
            System.out.println("- ⚠ Rehash pendiente (load factor > 0.75)");
        }
    }
    
    /**
     * Representación en String
     */
    @Override
    public String toString() {
        return String.format("MapaPacientes{tamaño=%d, capacidad=%d, loadFactor=%.2f}",
                tamanio, capacidad, getLoadFactor());
    }
}
