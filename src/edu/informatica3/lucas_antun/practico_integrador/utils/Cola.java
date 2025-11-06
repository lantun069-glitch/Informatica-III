package edu.informatica3.lucas_antun.practico_integrador.utils;

/**
 * Implementación propia de una Cola (Queue) genérica usando arreglos circulares.
 * 
 * Estructura de datos FIFO (First In, First Out) - El primero en entrar es el primero en salir.
 * 
 * Operaciones principales:
 * - enqueue(T): Agregar elemento al final - O(1)
 * - dequeue(): Remover y retornar elemento del frente - O(1)
 * - peek(): Consultar elemento del frente sin remover - O(1)
 * - isEmpty(): Verificar si está vacía - O(1)
 * - isFull(): Verificar si está llena - O(1)
 * - size(): Obtener cantidad de elementos - O(1)
 * 
 * @param <T> Tipo de elementos almacenados en la cola
 * @author Lucas Santiago Said Antun
 * @author Federico Fernández
 */
public class Cola<T> {
    
    private static final int CAPACIDAD_INICIAL = 10;
    private T[] elementos;
    private int frente;
    private int fin;
    private int tamanio;
    private int capacidad;
    
    /**
     * Constructor por defecto.
     * Crea una cola con capacidad inicial de 10 elementos.
     */
    @SuppressWarnings("unchecked")
    public Cola() {
        this.capacidad = CAPACIDAD_INICIAL;
        this.elementos = (T[]) new Object[capacidad];
        this.frente = 0;
        this.fin = -1;
        this.tamanio = 0;
    }
    
    /**
     * Constructor con capacidad especificada.
     * 
     * @param capacidad Capacidad máxima de la cola
     */
    @SuppressWarnings("unchecked")
    public Cola(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a 0");
        }
        this.capacidad = capacidad;
        this.elementos = (T[]) new Object[capacidad];
        this.frente = 0;
        this.fin = -1;
        this.tamanio = 0;
    }
    
    /**
     * Agrega un elemento al final de la cola.
     * Si la cola está llena, se duplica su capacidad automáticamente.
     * 
     * Complejidad: O(1) amortizado
     * 
     * @param elemento Elemento a agregar
     */
    public void enqueue(T elemento) {
        if (isFull()) {
            redimensionar();
        }
        
        fin = (fin + 1) % capacidad;
        elementos[fin] = elemento;
        tamanio++;
    }
    
    /**
     * Remueve y retorna el elemento del frente de la cola.
     * 
     * Complejidad: O(1)
     * 
     * @return Elemento del frente
     * @throws IllegalStateException si la cola está vacía
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía");
        }
        
        T elemento = elementos[frente];
        elementos[frente] = null; // Evitar memory leak
        frente = (frente + 1) % capacidad;
        tamanio--;
        
        return elemento;
    }
    
    /**
     * Retorna el elemento del frente sin removerlo.
     * 
     * Complejidad: O(1)
     * 
     * @return Elemento del frente
     * @throws IllegalStateException si la cola está vacía
     */
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía");
        }
        return elementos[frente];
    }
    
    /**
     * Verifica si la cola está vacía.
     * 
     * Complejidad: O(1)
     * 
     * @return true si está vacía, false en caso contrario
     */
    public boolean isEmpty() {
        return tamanio == 0;
    }
    
    /**
     * Verifica si la cola está llena.
     * 
     * Complejidad: O(1)
     * 
     * @return true si está llena, false en caso contrario
     */
    public boolean isFull() {
        return tamanio == capacidad;
    }
    
    /**
     * Retorna la cantidad de elementos en la cola.
     * 
     * Complejidad: O(1)
     * 
     * @return Cantidad de elementos
     */
    public int size() {
        return tamanio;
    }
    
    /**
     * Retorna la capacidad total de la cola.
     * 
     * Complejidad: O(1)
     * 
     * @return Capacidad de la cola
     */
    public int capacity() {
        return capacidad;
    }
    
    /**
     * Vacía completamente la cola.
     * 
     * Complejidad: O(n)
     */
    public void clear() {
        for (int i = 0; i < capacidad; i++) {
            elementos[i] = null;
        }
        frente = 0;
        fin = -1;
        tamanio = 0;
    }
    
    /**
     * Redimensiona el arreglo interno cuando está lleno.
     * Duplica la capacidad actual.
     * 
     * Complejidad: O(n)
     */
    @SuppressWarnings("unchecked")
    private void redimensionar() {
        int nuevaCapacidad = capacidad * 2;
        T[] nuevoArreglo = (T[]) new Object[nuevaCapacidad];
        
        // Copiar elementos manteniendo el orden
        for (int i = 0; i < tamanio; i++) {
            nuevoArreglo[i] = elementos[(frente + i) % capacidad];
        }
        
        elementos = nuevoArreglo;
        frente = 0;
        fin = tamanio - 1;
        capacidad = nuevaCapacidad;
    }
    
    /**
     * Representación en String de la cola.
     * Muestra los elementos desde el frente hasta el fin.
     * 
     * @return String representando la cola
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "Cola[]";
        }
        
        StringBuilder sb = new StringBuilder("Cola[frente -> ");
        for (int i = 0; i < tamanio; i++) {
            int index = (frente + i) % capacidad;
            sb.append(elementos[index]);
            if (i < tamanio - 1) {
                sb.append(", ");
            }
        }
        sb.append(" <- fin]");
        return sb.toString();
    }
}
