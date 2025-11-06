package edu.informatica3.lucas_antun.practico04.estructuras;

/**
 * Implementacion de una Pila usando un arreglo.
 */
public class PilaArreglo<T> {
    
    /** Arreglo para elementos */
    private final Object[] elementos;
    
    /** Indice del tope (-1 si vacia) */
    private int tope;
    
    /** Capacidad maxima */
    private final int capacidad;
    
    /**
     * Constructor que crea una pila con capacidad especificada.
     */
    public PilaArreglo(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("Capacidad debe ser positiva");
        }
        
        this.capacidad = capacidad;
        this.elementos = new Object[capacidad];
        this.tope = -1;
    }
    
    /**
     * Agrega un elemento al tope de la pila.
     */
    public boolean push(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("Elemento no puede ser null");
        }
        if (isFull()) {
            throw new IllegalStateException("Pila llena");
        }
        
        elementos[++tope] = elemento;
        return true;
    }
    
    /** Remueve y retorna el elemento del tope. */
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Pila vacia");
        }
        
        T elemento = (T) elementos[tope];
        elementos[tope] = null; // Limpiar referencia para GC
        tope--;
        return elemento;
    }
    
    /**
     * Retorna el elemento del tope sin removerlo.
     * 
     * @return el elemento en el tope de la pila
     * @throws IllegalStateException si la pila esta vacia
     */
    @SuppressWarnings("unchecked")
    public T top() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila esta vacia");
        }
        
        return (T) elementos[tope];
    }
    
    /**
     * Sinonimo de top() para compatibilidad.
     * 
     * @return el elemento en el tope de la pila
     */
    public T peek() {
        return top();
    }
    
    /**
     * Verifica si la pila esta vacia.
     * 
     * @return true si la pila no tiene elementos
     */
    public boolean isEmpty() {
        return tope == -1;
    }
    
    /**
     * Verifica si la pila esta llena.
     * 
     * @return true si la pila alcanzo su capacidad maxima
     */
    public boolean isFull() {
        return tope == capacidad - 1;
    }
    
    /**
     * Obtiene el numero actual de elementos en la pila.
     * 
     * @return el tamano actual de la pila
     */
    public int size() {
        return tope + 1;
    }
    
    /**
     * Obtiene la capacidad maxima de la pila.
     * 
     * @return la capacidad maxima
     */
    public int getCapacidad() {
        return capacidad;
    }
    
    /**
     * Limpia todos los elementos de la pila.
     */
    public void clear() {
        // Limpiar referencias para el garbage collector
        for (int i = 0; i <= tope; i++) {
            elementos[i] = null;
        }
        tope = -1;
    }
    
    /**
     * Busca un elemento en la pila y retorna su posicion desde el tope.
     * 
     * @param elemento el elemento a buscar
     * @return la posicion desde el tope (0 = tope, 1 = segundo, etc.) o -1 si no se encuentra
     */
    public int search(T elemento) {
        if (elemento == null) {
            return -1;
        }
        
        for (int i = tope; i >= 0; i--) {
            if (elemento.equals(elementos[i])) {
                return tope - i;
            }
        }
        return -1;
    }
    
    /**
     * Convierte la pila a un arreglo (del tope hacia la base).
     * 
     * @return arreglo con los elementos de la pila
     */
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        Object[] resultado = new Object[size()];
        for (int i = 0; i < size(); i++) {
            resultado[i] = elementos[tope - i];
        }
        return (T[]) resultado;
    }
    
    /**
     * Representacion en cadena de la pila.
     * Formato: [tope -> base]: elemento1, elemento2, ...
     * 
     * @return representacion en cadena de la pila
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "Pila vacia []";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("Pila [tope -> base]: ");
        
        for (int i = tope; i >= 0; i--) {
            sb.append(elementos[i]);
            if (i > 0) {
                sb.append(", ");
            }
        }
        
        sb.append(String.format(" (tamano: %d/%d)", size(), capacidad));
        return sb.toString();
    }
    
    /**
     * Representacion detallada para debugging.
     * 
     * @return informacion detallada de la pila
     */
    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("PilaArreglo{capacidad=%d, tamano=%d, tope=%d}%n", 
            capacidad, size(), tope));
        
        if (isEmpty()) {
            sb.append("  [vacia]");
        } else {
            sb.append("  Contenido (tope -> base):%n");
            for (int i = tope; i >= 0; i--) {
                String marcador = (i == tope) ? " <- TOPE" : "";
                sb.append(String.format("    [%d]: %s%s%n", i, elementos[i], marcador));
            }
        }
        
        return sb.toString();
    }
    
    /**
     * Crea una copia de la pila actual.
     * 
     * @return nueva pila con los mismos elementos
     */
    public PilaArreglo<T> clone() {
        PilaArreglo<T> copia = new PilaArreglo<>(capacidad);
        
        // Copiar todos los elementos
        for (int i = 0; i <= tope; i++) {
            copia.elementos[i] = elementos[i];
        }
        copia.tope = this.tope;
        
        return copia;
    }
    
    /**
     * Compara dos pilas para verificar si son iguales.
     * 
     * @param obj objeto a comparar
     * @return true si las pilas tienen los mismos elementos en el mismo orden
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        PilaArreglo<?> otra = (PilaArreglo<?>) obj;
        
        // Verificar tamanos
        if (this.size() != otra.size()) return false;
        
        // Comparar elementos
        for (int i = 0; i <= tope; i++) {
            if (!java.util.Objects.equals(elementos[i], otra.elementos[i])) {
                return false;
            }
        }
        
        return true;
    }
    
    @Override
    public int hashCode() {
        int result = 1;
        for (int i = 0; i <= tope; i++) {
            result = 31 * result + (elementos[i] != null ? elementos[i].hashCode() : 0);
        }
        return result;
    }
}