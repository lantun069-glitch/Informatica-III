package edu.informatica3.lucas_antun.practico06;

/**
 * Representa un nodo en un Arbol Rojo-Negro.
 * 
 * Un nodo Rojo-Negro es un nodo de arbol binario extendido que mantiene
 * informacion adicional de color y referencias a padres para facilitar
 * las operaciones de rotacion y rebalanceo.
 * 
 * Caracteristicas del nodo:
 * 
 *   Almacena una clave y un valor (estructura de diccionario)
 *   Mantiene un color (rojo o negro)
 *   Referencias a padre, hijo izquierdo e hijo derecho
 *   Soporte para nodo centinela NIL
 * 
 * 
 * @param <K> tipo de la clave, debe ser comparable
 * @param <V> tipo del valor asociado
 * @author Lucas Santiago Said Antun
 * @version 1.0
 * @since 2025-01-01
 */
public class NodoRojoNegro<K extends Comparable<K>, V> {
    
    /** La clave almacenada en este nodo. */
    private K clave;
    
    /** El valor asociado a la clave. */
    private V valor;
    
    /** El color del nodo (rojo o negro). */
    private Color color;
    
    /** Referencia al nodo padre. */
    private NodoRojoNegro<K, V> padre;
    
    /** Referencia al hijo izquierdo. */
    private NodoRojoNegro<K, V> izquierda;
    
    /** Referencia al hijo derecho. */
    private NodoRojoNegro<K, V> derecha;
    
    /**
     * Construye un nodo Rojo-Negro completo con clave y valor.
     * 
     * @param clave la clave del nodo
     * @param valor el valor asociado a la clave
     * @param color el color inicial del nodo
     * @param nodoNil el nodo centinela NIL a usar para hijos nulos
     * @throws IllegalArgumentException si la clave es null
     */
    public NodoRojoNegro(K clave, V valor, Color color, NodoRojoNegro<K, V> nodoNil) {
        if (clave == null) {
            throw new IllegalArgumentException("La clave no puede ser null");
        }
        if (color == null) {
            throw new IllegalArgumentException("El color no puede ser null");
        }
        
        this.clave = clave;
        this.valor = valor;
        this.color = color;
        this.padre = nodoNil;
        this.izquierda = nodoNil;
        this.derecha = nodoNil;
    }
    
    /**
     * Construye un nodo centinela NIL con color negro.
     * 
     * Los nodos NIL son nodos especiales que representan hojas nulas
     * en el arbol Rojo-Negro. Siempre son negros por definicion.
     */
    public NodoRojoNegro() {
        this.clave = null;
        this.valor = null;
        this.color = Color.NEGRO;
        this.padre = null;
        this.izquierda = null;
        this.derecha = null;
    }
    
    // ============== GETTERS Y SETTERS ==============
    
    /**
     * Obtiene la clave del nodo.
     * 
     * @return la clave del nodo, o null si es nodo NIL
     */
    public K getClave() {
        return clave;
    }
    
    /**
     * Establece la clave del nodo.
     * 
     * @param clave la nueva clave
     * @throws IllegalArgumentException si la clave es null en nodo no-NIL
     */
    public void setClave(K clave) {
        if (clave == null && !esNIL()) {
            throw new IllegalArgumentException("La clave no puede ser null en nodos normales");
        }
        this.clave = clave;
    }
    
    /**
     * Obtiene el valor asociado a la clave.
     * 
     * @return el valor del nodo, o null si es nodo NIL
     */
    public V getValor() {
        return valor;
    }
    
    /**
     * Establece el valor del nodo.
     * 
     * @param valor el nuevo valor
     */
    public void setValor(V valor) {
        this.valor = valor;
    }
    
    /**
     * Obtiene el color del nodo.
     * 
     * @return el color del nodo
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * Establece el color del nodo.
     * 
     * @param color el nuevo color
     * @throws IllegalArgumentException si el color es null
     */
    public void setColor(Color color) {
        if (color == null) {
            throw new IllegalArgumentException("El color no puede ser null");
        }
        this.color = color;
    }
    
    /**
     * Obtiene el nodo padre.
     * 
     * @return el nodo padre
     */
    public NodoRojoNegro<K, V> getPadre() {
        return padre;
    }
    
    /**
     * Establece el nodo padre.
     * 
     * @param padre el nuevo nodo padre
     */
    public void setPadre(NodoRojoNegro<K, V> padre) {
        this.padre = padre;
    }
    
    /**
     * Obtiene el hijo izquierdo.
     * 
     * @return el hijo izquierdo
     */
    public NodoRojoNegro<K, V> getIzquierda() {
        return izquierda;
    }
    
    /**
     * Establece el hijo izquierdo.
     * 
     * @param izquierda el nuevo hijo izquierdo
     */
    public void setIzquierda(NodoRojoNegro<K, V> izquierda) {
        this.izquierda = izquierda;
    }
    
    /**
     * Obtiene el hijo derecho.
     * 
     * @return el hijo derecho
     */
    public NodoRojoNegro<K, V> getDerecha() {
        return derecha;
    }
    
    /**
     * Establece el hijo derecho.
     * 
     * @param derecha el nuevo hijo derecho
     */
    public void setDerecha(NodoRojoNegro<K, V> derecha) {
        this.derecha = derecha;
    }
    
    // ============== METODOS DE CONSULTA ==============
    
    /**
     * Verifica si este nodo es el nodo centinela NIL.
     * 
     * @return true si es nodo NIL, false en caso contrario
     */
    public boolean esNIL() {
        return clave == null;
    }
    
    /**
     * Verifica si este nodo es rojo.
     * 
     * @return true si el nodo es rojo, false en caso contrario
     */
    public boolean esRojo() {
        return color == Color.ROJO;
    }
    
    /**
     * Verifica si este nodo es negro.
     * 
     * @return true si el nodo es negro, false en caso contrario
     */
    public boolean esNegro() {
        return color == Color.NEGRO;
    }
    
    /**
     * Verifica si este nodo es una hoja (ambos hijos son NIL).
     * 
     * @return true si es hoja, false en caso contrario
     */
    public boolean esHoja() {
        return izquierda.esNIL() && derecha.esNIL();
    }
    
    /**
     * Verifica si este nodo tiene exactamente un hijo no-NIL.
     * 
     * @return true si tiene exactamente un hijo, false en caso contrario
     */
    public boolean tieneUnHijo() {
        return izquierda.esNIL() != derecha.esNIL();
    }
    
    /**
     * Verifica si este nodo tiene ambos hijos no-NIL.
     * 
     * @return true si tiene ambos hijos, false en caso contrario
     */
    public boolean tieneAmbosHijos() {
        return !izquierda.esNIL() && !derecha.esNIL();
    }
    
    /**
     * Verifica si este nodo es hijo izquierdo de su padre.
     * 
     * @return true si es hijo izquierdo, false en caso contrario
     */
    public boolean esHijoIzquierdo() {
        return padre != null && padre.izquierda == this;
    }
    
    /**
     * Verifica si este nodo es hijo derecho de su padre.
     * 
     * @return true si es hijo derecho, false en caso contrario
     */
    public boolean esHijoDerecho() {
        return padre != null && padre.derecha == this;
    }
    
    /**
     * Verifica si este nodo es la raiz del arbol.
     * 
     * @return true si es raiz, false en caso contrario
     */
    public boolean esRaiz() {
        return padre == null || padre.esNIL();
    }
    
    // ============== METODOS DE NAVEGACION ==============
    
    /**
     * Obtiene el abuelo de este nodo (padre del padre).
     * 
     * @return el nodo abuelo, o NIL si no existe
     */
    public NodoRojoNegro<K, V> getAbuelo() {
        if (padre != null && !padre.esNIL()) {
            return padre.getPadre();
        }
        return padre != null ? padre : this; // Retorna NIL apropiado
    }
    
    /**
     * Obtiene el tio de este nodo (hermano del padre).
     * 
     * @return el nodo tio, o NIL si no existe
     */
    public NodoRojoNegro<K, V> getTio() {
        NodoRojoNegro<K, V> abuelo = getAbuelo();
        if (abuelo == null || abuelo.esNIL()) {
            return abuelo != null ? abuelo : padre; // Retorna NIL apropiado
        }
        
        if (padre.esHijoIzquierdo()) {
            return abuelo.getDerecha();
        } else {
            return abuelo.getIzquierda();
        }
    }
    
    /**
     * Obtiene el hermano de este nodo.
     * 
     * @return el nodo hermano, o NIL si no existe o este es raiz
     */
    public NodoRojoNegro<K, V> getHermano() {
        if (padre == null || padre.esNIL()) {
            return padre != null ? padre : this; // Retorna NIL apropiado
        }
        
        if (esHijoIzquierdo()) {
            return padre.getDerecha();
        } else {
            return padre.getIzquierda();
        }
    }
    
    // ============== OPERACIONES DE COMPARACION ==============
    
    /**
     * Compara la clave de este nodo con otra clave.
     * 
     * @param otraClave la clave a comparar
     * @return valor negativo si menor, 0 si igual, positivo si mayor
     * @throws IllegalStateException si este nodo es NIL
     * @throws IllegalArgumentException si la otra clave es null
     */
    public int compararClave(K otraClave) {
        if (esNIL()) {
            throw new IllegalStateException("No se puede comparar clave de nodo NIL");
        }
        if (otraClave == null) {
            throw new IllegalArgumentException("La clave a comparar no puede ser null");
        }
        
        return clave.compareTo(otraClave);
    }
    
    /**
     * Verifica si este nodo tiene la clave especificada.
     * 
     * @param claveBuscada la clave a verificar
     * @return true si las claves son iguales, false en caso contrario
     */
    public boolean tieneClave(K claveBuscada) {
        if (esNIL() || claveBuscada == null) {
            return false;
        }
        return clave.equals(claveBuscada);
    }
    
    // ============== METODOS DE UTILIDAD ==============
    
    /**
     * Intercambia el color de este nodo con otro nodo.
     * 
     * @param otroNodo el nodo con el cual intercambiar colores
     * @throws IllegalArgumentException si el otro nodo es null
     */
    public void intercambiarColores(NodoRojoNegro<K, V> otroNodo) {
        if (otroNodo == null) {
            throw new IllegalArgumentException("El otro nodo no puede ser null");
        }
        
        Color colorTemporal = this.color;
        this.color = otroNodo.color;
        otroNodo.color = colorTemporal;
    }
    
    /**
     * Crea una copia superficial de este nodo.
     * 
     * La copia incluye clave, valor y color, pero no las referencias
     * a otros nodos (padre, hijos).
     * 
     * @param nodoNil el nodo NIL a usar para inicializar referencias
     * @return una copia del nodo
     */
    public NodoRojoNegro<K, V> clonar(NodoRojoNegro<K, V> nodoNil) {
        if (esNIL()) {
            return new NodoRojoNegro<>();
        }
        
        return new NodoRojoNegro<>(clave, valor, color, nodoNil);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        @SuppressWarnings("unchecked")
        NodoRojoNegro<K, V> otroNodo = (NodoRojoNegro<K, V>) obj;
        
        // Dos nodos NIL son iguales
        if (this.esNIL() && otroNodo.esNIL()) {
            return true;
        }
        
        // Un nodo NIL y uno normal no son iguales
        if (this.esNIL() || otroNodo.esNIL()) {
            return false;
        }
        
        // Comparar por clave y color
        return clave.equals(otroNodo.clave) && color == otroNodo.color;
    }
    
    @Override
    public int hashCode() {
        if (esNIL()) {
            return Color.NEGRO.hashCode(); // Todos los NIL tienen el mismo hash
        }
        
        int resultado = clave.hashCode();
        resultado = 31 * resultado + color.hashCode();
        return resultado;
    }
    
    @Override
    public String toString() {
        if (esNIL()) {
            return "NIL";
        }
        
        return String.format("%s(%s)", clave, color.toShortString());
    }
    
    /**
     * Retorna una representacion detallada del nodo.
     * 
     * @return cadena con informacion completa del nodo
     */
    public String toStringDetallado() {
        if (esNIL()) {
            return "Nodo NIL (Negro)";
        }
        
        return String.format("Nodo[clave=%s, valor=%s, color=%s, padre=%s, izq=%s, der=%s]",
            clave,
            valor,
            color,
            padre != null && !padre.esNIL() ? padre.getClave() : "NIL",
            !izquierda.esNIL() ? izquierda.getClave() : "NIL",
            !derecha.esNIL() ? derecha.getClave() : "NIL");
    }
    
    /**
     * Retorna una representacion compacta para visualizacion de arbol.
     * 
     * @return cadena compacta del nodo
     */
    public String toStringCompacto() {
        if (esNIL()) {
            return "⬛"; // Simbolo para NIL
        }
        
        String simboloColor = esRojo() ? "" : "";
        return String.format("%s%s", clave, simboloColor);
    }
}