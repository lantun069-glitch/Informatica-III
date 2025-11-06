package edu.informatica3.lucas_antun.practico_integrador.utils;

/**
 * Implementación propia de una Pila (Stack) genérica usando arreglos dinámicos.
 * 
 * Estructura de datos LIFO (Last In, First Out) - El último en entrar es el primero en salir.
 * 
 * Operaciones principales:
 * - push(T): Agregar elemento al tope - O(1) amortizado
 * - pop(): Remover y retornar elemento del tope - O(1)
 * - peek(): Consultar elemento del tope sin remover - O(1)
 * - isEmpty(): Verificar si está vacía - O(1)
 * - size(): Obtener cantidad de elementos - O(1)
 * 
 * @param <T> Tipo de elementos almacenados en la pila
 * @author Lucas Santiago Said Antun
 * @author Federico Fernández
 */
public class Pila<T> {
    
    private static final int CAPACIDAD_INICIAL = 10;
    private T[] elementos;
    private int tope;
    
    /**
     * Constructor por defecto.
     * Crea una pila con capacidad inicial de 10 elementos.
     */
    @SuppressWarnings("unchecked")
    public Pila() {
        this.elementos = (T[]) new Object[CAPACIDAD_INICIAL];
        this.tope = -1;
    }
    
    /**
     * Constructor con capacidad inicial especificada.
     * 
     * @param capacidadInicial Capacidad inicial del arreglo interno
     */
    @SuppressWarnings("unchecked")
    public Pila(int capacidadInicial) {
        if (capacidadInicial <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a 0");
        }
        this.elementos = (T[]) new Object[capacidadInicial];
        this.tope = -1;
    }
    
    /**
     * Agrega un elemento al tope de la pila.
     * Si la pila está llena, se duplica su capacidad automáticamente.
     * 
     * Complejidad: O(1) amortizado (O(n) cuando se redimensiona)
     * 
     * @param elemento Elemento a agregar
     */
    public void push(T elemento) {
        if (tope == elementos.length - 1) {
            redimensionar();
        }
        elementos[++tope] = elemento;
    }
    
    /**
     * Remueve y retorna el elemento del tope de la pila.
     * 
     * Complejidad: O(1)
     * 
     * @return Elemento del tope
     * @throws IllegalStateException si la pila está vacía
     */
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía");
        }
        T elemento = elementos[tope];
        elementos[tope--] = null; // Evitar memory leak
        return elemento;
    }
    
    /**
     * Retorna el elemento del tope sin removerlo.
     * 
     * Complejidad: O(1)
     * 
     * @return Elemento del tope
     * @throws IllegalStateException si la pila está vacía
     */
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía");
        }
        return elementos[tope];
    }
    
    /**
     * Verifica si la pila está vacía.
     * 
     * Complejidad: O(1)
     * 
     * @return true si está vacía, false en caso contrario
     */
    public boolean isEmpty() {
        return tope == -1;
    }
    
    /**
     * Retorna la cantidad de elementos en la pila.
     * 
     * Complejidad: O(1)
     * 
     * @return Cantidad de elementos
     */
    public int size() {
        return tope + 1;
    }
    
    /**
     * Vacía completamente la pila.
     * 
     * Complejidad: O(n)
     */
    public void clear() {
        for (int i = 0; i <= tope; i++) {
            elementos[i] = null;
        }
        tope = -1;
    }
    
    /**
     * Obtiene el elemento en la posición especificada (0 = tope).
     * Usado para iterar sobre la pila sin modificarla.
     * 
     * Complejidad: O(1)
     * 
     * @param index Índice desde el tope (0 = tope, 1 = siguiente, etc.)
     * @return Elemento en la posición especificada
     * @throws IndexOutOfBoundsException si el índice es inválido
     */
    public T get(int index) {
        if (index < 0 || index > tope) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }
        return elementos[tope - index];
    }
    
    /**
     * Redimensiona el arreglo interno cuando está lleno.
     * Duplica la capacidad actual.
     * 
     * Complejidad: O(n)
     */
    @SuppressWarnings("unchecked")
    private void redimensionar() {
        int nuevaCapacidad = elementos.length * 2;
        T[] nuevoArreglo = (T[]) new Object[nuevaCapacidad];
        
        for (int i = 0; i <= tope; i++) {
            nuevoArreglo[i] = elementos[i];
        }
        
        elementos = nuevoArreglo;
    }
    
    /**
     * Representación en String de la pila.
     * Muestra los elementos desde el tope hasta el fondo.
     * 
     * @return String representando la pila
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "Pila[]";
        }
        
        StringBuilder sb = new StringBuilder("Pila[tope -> ");
        for (int i = tope; i >= 0; i--) {
            sb.append(elementos[i]);
            if (i > 0) {
                sb.append(", ");
            }
        }
        sb.append(" <- fondo]");
        return sb.toString();
    }
}
