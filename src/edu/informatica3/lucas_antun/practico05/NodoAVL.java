package edu.informatica3.lucas_antun.practico05;

/**
 * Nodo de un Arbol AVL.
 */
public class NodoAVL<T extends Comparable<T>> {
    
    /** El valor almacenado en este nodo. */
    private T valor;
    
    /** La altura de este nodo en el arbol (hojas tienen altura 1). */
    private int altura;
    
    /** Referencia al hijo izquierdo del nodo. */
    private NodoAVL<T> izquierda;
    
    /** Referencia al hijo derecho del nodo. */
    private NodoAVL<T> derecha;
    
    /**
     * Construye un nuevo nodo AVL con el valor especificado.
     * 
     * El nodo se inicializa como hoja (altura = 1) y sin hijos.
     * 
     * @param valor el valor a almacenar en el nodo
     * @throws IllegalArgumentException si el valor es null
     * 
     * @example
     * <pre>
     * // Crear un nodo con valor entero
     * NodoAVL&lt;Integer&gt; nodo = new NodoAVL&lt;&gt;(42);
     * 
     * // Crear un nodo con cadena
     * NodoAVL&lt;String&gt; nodoTexto = new NodoAVL&lt;&gt;("Hola");
     * </pre>
     */
    public NodoAVL(T valor) {
        if (valor == null) {
            throw new IllegalArgumentException("El valor del nodo no puede ser null");
        }
        
        this.valor = valor;
        this.altura = 1;
        this.izquierda = null;
        this.derecha = null;
    }
    
    /**
     * Obtiene el valor almacenado en este nodo.
     * 
     * @return el valor del nodo
     */
    public T getValor() {
        return valor;
    }
    
    /**
     * Establece el valor de este nodo.
     * 
     * @param valor el nuevo valor del nodo
     * @throws IllegalArgumentException si el valor es null
     */
    public void setValor(T valor) {
        if (valor == null) {
            throw new IllegalArgumentException("El valor del nodo no puede ser null");
        }
        this.valor = valor;
    }
    
    /**
     * Obtiene la altura de este nodo.
     * 
     * La altura de un nodo es la longitud del camino mas largo
     * desde el nodo hasta una hoja. Las hojas tienen altura 1.
     * 
     * @return la altura del nodo (≥ 1)
     */
    public int getAltura() {
        return altura;
    }
    
    /**
     * Establece la altura de este nodo.
     * 
     * @param altura la nueva altura del nodo
     * @throws IllegalArgumentException si la altura es menor a 1
     */
    public void setAltura(int altura) {
        if (altura < 1) {
            throw new IllegalArgumentException("La altura debe ser al menos 1");
        }
        this.altura = altura;
    }
    
    /**
     * Obtiene el hijo izquierdo de este nodo.
     * 
     * @return el nodo hijo izquierdo, o null si no existe
     */
    public NodoAVL<T> getIzquierda() {
        return izquierda;
    }
    
    /**
     * Establece el hijo izquierdo de este nodo.
     * 
     * @param izquierda el nuevo hijo izquierdo
     */
    public void setIzquierda(NodoAVL<T> izquierda) {
        this.izquierda = izquierda;
    }
    
    /**
     * Obtiene el hijo derecho de este nodo.
     * 
     * @return el nodo hijo derecho, o null si no existe
     */
    public NodoAVL<T> getDerecha() {
        return derecha;
    }
    
    /**
     * Establece el hijo derecho de este nodo.
     * 
     * @param derecha el nuevo hijo derecho
     */
    public void setDerecha(NodoAVL<T> derecha) {
        this.derecha = derecha;
    }
    
    /**
     * Verifica si este nodo es una hoja (no tiene hijos).
     * 
     * @return true si el nodo es una hoja, false en caso contrario
     */
    public boolean esHoja() {
        return izquierda == null && derecha == null;
    }
    
    /**
     * Verifica si este nodo tiene exactamente un hijo.
     * 
     * @return true si el nodo tiene exactamente un hijo, false en caso contrario
     */
    public boolean tieneUnHijo() {
        return (izquierda == null) != (derecha == null);
    }
    
    /**
     * Verifica si este nodo tiene ambos hijos.
     * 
     * @return true si el nodo tiene ambos hijos, false en caso contrario
     */
    public boolean tieneAmbosHijos() {
        return izquierda != null && derecha != null;
    }
    
    /**
     * Calcula y retorna el factor de equilibrio de este nodo.
     * 
     * El factor de equilibrio es la diferencia entre la altura
     * del subarbol izquierdo y la altura del subarbol derecho.
     * 
     * Interpretacion:
     * 
     *   FE > 0: nodo "pesado" hacia la izquierda
     *   FE < 0: nodo "pesado" hacia la derecha
     *   FE = 0: nodo perfectamente balanceado
     *   |FE| > 1: nodo desbalanceado (requiere rotacion)
     * 
     * 
     * @return el factor de equilibrio (altura_izq - altura_der)
     */
    public int calcularFactorEquilibrio() {
        int alturaIzq = (izquierda != null) ? izquierda.getAltura() : 0;
        int alturaDer = (derecha != null) ? derecha.getAltura() : 0;
        return alturaIzq - alturaDer;
    }
    
    /**
     * Actualiza automaticamente la altura de este nodo basandose
     * en las alturas de sus hijos.
     * 
     * La altura se calcula como: 1 + max(altura_izq, altura_der)
     */
    public void actualizarAltura() {
        int alturaIzq = (izquierda != null) ? izquierda.getAltura() : 0;
        int alturaDer = (derecha != null) ? derecha.getAltura() : 0;
        this.altura = 1 + Math.max(alturaIzq, alturaDer);
    }
    
    /**
     * Compara este nodo con otro objeto para determinar igualdad.
     * 
     * Dos nodos son iguales si tienen el mismo valor.
     * 
     * @param obj el objeto a comparar
     * @return true si los objetos son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        @SuppressWarnings("unchecked")
        NodoAVL<T> otroNodo = (NodoAVL<T>) obj;
        return valor.equals(otroNodo.valor);
    }
    
    /**
     * Retorna el codigo hash de este nodo.
     * 
     * @return el codigo hash basado en el valor del nodo
     */
    @Override
    public int hashCode() {
        return valor.hashCode();
    }
    
    /**
     * Retorna una representacion en cadena de este nodo.
     * 
     * El formato incluye el valor, altura y factor de equilibrio.
     * 
     * @return representacion en cadena del nodo
     * 
     * @example
     * <pre>
     * NodoAVL&lt;Integer&gt; nodo = new NodoAVL&lt;&gt;(42);
     * System.out.println(nodo); // "42(h:1, FE:0)"
     * </pre>
     */
    @Override
    public String toString() {
        return String.format("%s(h:%d, FE:%d)", 
            valor, altura, calcularFactorEquilibrio());
    }
    
    /**
     * Crea una representacion detallada del nodo con informacion
     * sobre sus hijos.
     * 
     * @return cadena con informacion completa del nodo
     */
    public String toStringDetallado() {
        return String.format("Nodo[valor=%s, altura=%d, FE=%d, izq=%s, der=%s]",
            valor, altura, calcularFactorEquilibrio(),
            izquierda != null ? izquierda.getValor() : "null",
            derecha != null ? derecha.getValor() : "null");
    }
}