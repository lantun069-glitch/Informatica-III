package edu.informatica3.lucas_antun.practico_integrador.utils;

/**
 * Implementación propia de una Lista dinámica genérica (similar a ArrayList).
 * 
 * Estructura de datos secuencial con tamaño dinámico basada en arreglos.
 * 
 * Operaciones principales:
 * - add(T): Agregar elemento al final - O(1) amortizado
 * - add(int, T): Insertar en posición específica - O(n)
 * - get(int): Obtener elemento por índice - O(1)
 * - remove(int): Remover elemento por índice - O(n)
 * - size(): Obtener cantidad de elementos - O(1)
 * - isEmpty(): Verificar si está vacía - O(1)
 * 
 * @param <T> Tipo de elementos almacenados en la lista
 * @author Lucas Santiago Said Antun
 * @author Federico Fernández
 */
public class Lista<T> {
    
    private static final int CAPACIDAD_INICIAL = 10;
    private T[] elementos;
    private int tamanio;
    
    /**
     * Constructor por defecto.
     * Crea una lista con capacidad inicial de 10 elementos.
     */
    @SuppressWarnings("unchecked")
    public Lista() {
        this.elementos = (T[]) new Object[CAPACIDAD_INICIAL];
        this.tamanio = 0;
    }
    
    /**
     * Constructor con capacidad inicial especificada.
     * 
     * @param capacidadInicial Capacidad inicial del arreglo interno
     */
    @SuppressWarnings("unchecked")
    public Lista(int capacidadInicial) {
        if (capacidadInicial <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a 0");
        }
        this.elementos = (T[]) new Object[capacidadInicial];
        this.tamanio = 0;
    }
    
    /**
     * Agrega un elemento al final de la lista.
     * Si la lista está llena, se duplica su capacidad automáticamente.
     * 
     * Complejidad: O(1) amortizado
     * 
     * @param elemento Elemento a agregar
     */
    public void add(T elemento) {
        if (tamanio == elementos.length) {
            redimensionar();
        }
        elementos[tamanio++] = elemento;
    }
    
    /**
     * Inserta un elemento en la posición especificada.
     * Los elementos posteriores se desplazan hacia la derecha.
     * 
     * Complejidad: O(n)
     * 
     * @param index Índice donde insertar (0 <= index <= size)
     * @param elemento Elemento a insertar
     * @throws IndexOutOfBoundsException si el índice es inválido
     */
    public void add(int index, T elemento) {
        if (index < 0 || index > tamanio) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }
        
        if (tamanio == elementos.length) {
            redimensionar();
        }
        
        // Desplazar elementos hacia la derecha
        for (int i = tamanio; i > index; i--) {
            elementos[i] = elementos[i - 1];
        }
        
        elementos[index] = elemento;
        tamanio++;
    }
    
    /**
     * Obtiene el elemento en la posición especificada.
     * 
     * Complejidad: O(1)
     * 
     * @param index Índice del elemento (0 <= index < size)
     * @return Elemento en la posición especificada
     * @throws IndexOutOfBoundsException si el índice es inválido
     */
    public T get(int index) {
        if (index < 0 || index >= tamanio) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }
        return elementos[index];
    }
    
    /**
     * Modifica el elemento en la posición especificada.
     * 
     * Complejidad: O(1)
     * 
     * @param index Índice del elemento a modificar
     * @param elemento Nuevo elemento
     * @return Elemento anterior en esa posición
     * @throws IndexOutOfBoundsException si el índice es inválido
     */
    public T set(int index, T elemento) {
        if (index < 0 || index >= tamanio) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }
        T anterior = elementos[index];
        elementos[index] = elemento;
        return anterior;
    }
    
    /**
     * Remueve el elemento en la posición especificada.
     * Los elementos posteriores se desplazan hacia la izquierda.
     * 
     * Complejidad: O(n)
     * 
     * @param index Índice del elemento a remover
     * @return Elemento removido
     * @throws IndexOutOfBoundsException si el índice es inválido
     */
    public T remove(int index) {
        if (index < 0 || index >= tamanio) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }
        
        T elementoRemovido = elementos[index];
        
        // Desplazar elementos hacia la izquierda
        for (int i = index; i < tamanio - 1; i++) {
            elementos[i] = elementos[i + 1];
        }
        
        elementos[--tamanio] = null; // Evitar memory leak
        return elementoRemovido;
    }
    
    /**
     * Remueve la primera ocurrencia del elemento especificado.
     * 
     * Complejidad: O(n)
     * 
     * @param elemento Elemento a remover
     * @return true si se removió, false si no se encontró
     */
    public boolean remove(T elemento) {
        for (int i = 0; i < tamanio; i++) {
            if ((elemento == null && elementos[i] == null) || 
                (elemento != null && elemento.equals(elementos[i]))) {
                remove(i);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Verifica si la lista contiene el elemento especificado.
     * 
     * Complejidad: O(n)
     * 
     * @param elemento Elemento a buscar
     * @return true si está presente, false en caso contrario
     */
    public boolean contains(T elemento) {
        return indexOf(elemento) != -1;
    }
    
    /**
     * Retorna el índice de la primera ocurrencia del elemento.
     * 
     * Complejidad: O(n)
     * 
     * @param elemento Elemento a buscar
     * @return Índice del elemento, o -1 si no se encuentra
     */
    public int indexOf(T elemento) {
        for (int i = 0; i < tamanio; i++) {
            if ((elemento == null && elementos[i] == null) || 
                (elemento != null && elemento.equals(elementos[i]))) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Retorna la cantidad de elementos en la lista.
     * 
     * Complejidad: O(1)
     * 
     * @return Cantidad de elementos
     */
    public int size() {
        return tamanio;
    }
    
    /**
     * Verifica si la lista está vacía.
     * 
     * Complejidad: O(1)
     * 
     * @return true si está vacía, false en caso contrario
     */
    public boolean isEmpty() {
        return tamanio == 0;
    }
    
    /**
     * Vacía completamente la lista.
     * 
     * Complejidad: O(n)
     */
    public void clear() {
        for (int i = 0; i < tamanio; i++) {
            elementos[i] = null;
        }
        tamanio = 0;
    }
    
    /**
     * Convierte la lista a un arreglo.
     * 
     * Complejidad: O(n)
     * 
     * @return Arreglo con los elementos de la lista
     */
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] arreglo = (T[]) new Object[tamanio];
        for (int i = 0; i < tamanio; i++) {
            arreglo[i] = elementos[i];
        }
        return arreglo;
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
        
        for (int i = 0; i < tamanio; i++) {
            nuevoArreglo[i] = elementos[i];
        }
        
        elementos = nuevoArreglo;
    }
    
    /**
     * Representación en String de la lista.
     * 
     * @return String representando la lista
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < tamanio; i++) {
            sb.append(elementos[i]);
            if (i < tamanio - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
