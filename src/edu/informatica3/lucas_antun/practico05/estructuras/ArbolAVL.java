package edu.informatica3.lucas_antun.practico05.estructuras;

import edu.informatica3.lucas_antun.practico05.nodos.*;
import edu.informatica3.lucas_antun.practico05.utils.*;
import java.util.*;

/**
 * Implementacion de Arbol AVL.
 */
public class ArbolAVL<T extends Comparable<T>> {
    
    /** Nodo raiz del arbol */
    private NodoAVL<T> raiz;
    
    /** Contador de rotaciones */
    private int contadorRotaciones;
    
    /** Numero de elementos */
    private int tamano;
    
    /**
     * Construye un arbol AVL vacio.
     * 
     * @example
     * <pre>
     * ArbolAVL&lt;Integer&gt; arbol = new ArbolAVL&lt;&gt;();
     * ArbolAVL&lt;String&gt; arbolTexto = new ArbolAVL&lt;&gt;();
     * </pre>
     */
    public ArbolAVL() {
        this.raiz = null;
        this.contadorRotaciones = 0;
        this.tamano = 0;
    }
    
    /**
     * Construye un arbol AVL con los elementos de la coleccion especificada.
     * 
     * @param elementos coleccion de elementos a insertar
     * @throws IllegalArgumentException si la coleccion es null
     */
    public ArbolAVL(Collection<T> elementos) {
        this();
        if (elementos == null) {
            throw new IllegalArgumentException("La coleccion no puede ser null");
        }
        
        for (T elemento : elementos) {
            if (elemento != null) {
                insertar(elemento);
            }
        }
    }
    
    // ============== METODOS PUBLICOS DE OPERACION ==============
    
    /**
     * Inserta un elemento en el arbol AVL.
     * 
     * Si el elemento ya existe, no se realiza ninguna accion.
     * Despues de la insercion, el arbol se rebalancea automaticamente
     * si es necesario mediante rotaciones.
     * 
     * Complejidad: O(log n)
     * 
     * @param valor el valor a insertar
     * @throws IllegalArgumentException si el valor es null
     * @return true si el elemento fue insertado, false si ya existia
     * 
     * @example
     * <pre>
     * ArbolAVL&lt;Integer&gt; arbol = new ArbolAVL&lt;&gt;();
     * arbol.insertar(10); // true
     * arbol.insertar(5);  // true
     * arbol.insertar(10); // false (ya existe)
     * </pre>
     */
    public boolean insertar(T valor) {
        if (valor == null) {
            throw new IllegalArgumentException("No se pueden insertar valores null");
        }
        
        int tamanoAnterior = tamano;
        raiz = insertarRecursivo(raiz, valor);
        return tamano > tamanoAnterior;
    }
    
    /**
     * Elimina un elemento del arbol AVL.
     * 
     * Despues de la eliminacion, el arbol se rebalancea automaticamente
     * si es necesario mediante rotaciones.
     * 
     * Complejidad: O(log n)
     * 
     * @param valor el valor a eliminar
     * @throws IllegalArgumentException si el valor es null
     * @return true si el elemento fue eliminado, false si no existia
     */
    public boolean eliminar(T valor) {
        if (valor == null) {
            throw new IllegalArgumentException("No se pueden eliminar valores null");
        }
        
        int tamanoAnterior = tamano;
        raiz = eliminarRecursivo(raiz, valor);
        return tamano < tamanoAnterior;
    }
    
    /**
     * Busca un elemento en el arbol AVL.
     * 
     * Complejidad: O(log n)
     * 
     * @param valor el valor a buscar
     * @return true si el elemento existe, false en caso contrario
     * @throws IllegalArgumentException si el valor es null
     */
    public boolean contiene(T valor) {
        if (valor == null) {
            throw new IllegalArgumentException("No se pueden buscar valores null");
        }
        
        return buscarRecursivo(raiz, valor) != null;
    }
    
    /**
     * Busca y retorna el nodo que contiene el valor especificado.
     * 
     * @param valor el valor a buscar
     * @return el nodo que contiene el valor, o null si no existe
     * @throws IllegalArgumentException si el valor es null
     */
    public NodoAVL<T> buscar(T valor) {
        if (valor == null) {
            throw new IllegalArgumentException("No se pueden buscar valores null");
        }
        
        return buscarRecursivo(raiz, valor);
    }
    
    // ============== METODOS DE INFORMACION ==============
    
    /**
     * Retorna el numero de elementos en el arbol.
     * 
     * @return el tamano del arbol
     */
    public int tamano() {
        return tamano;
    }
    
    /**
     * Verifica si el arbol esta vacio.
     * 
     * @return true si el arbol esta vacio, false en caso contrario
     */
    public boolean estaVacio() {
        return raiz == null;
    }
    
    /**
     * Obtiene la altura del arbol.
     * 
     * La altura es el numero de niveles en el arbol. Un arbol
     * vacio tiene altura 0, un arbol con solo la raiz tiene altura 1.
     * 
     * @return la altura del arbol
     */
    public int altura() {
        return obtenerAltura(raiz);
    }
    
    /**
     * Obtiene el numero total de rotaciones realizadas.
     * 
     * Este contador se incrementa cada vez que se realiza una
     * rotacion para mantener el balance del arbol. Util para
     * analisis de rendimiento.
     * 
     * @return el numero de rotaciones realizadas
     */
    public int getContadorRotaciones() {
        return contadorRotaciones;
    }
    
    /**
     * Reinicia el contador de rotaciones a cero.
     */
    public void reiniciarContadorRotaciones() {
        this.contadorRotaciones = 0;
    }
    
    // ============== METODOS DE VERIFICACION ==============
    
    /**
     * Verifica si este arbol cumple con todas las propiedades AVL.
     * 
     * Verifica en una sola pasada:
     * 
     *   Propiedad de arbol binario de busqueda
     *   Factor de equilibrio ≤ 1 en todos los nodos
     *   Alturas correctamente calculadas
     * 
     * 
     * @return resultado con informacion de validez y altura
     */
    public ResultadoVerificacionAVL verificarAVL() {
        return verificarAVLRecursivo(raiz);
    }
    
    /**
     * Verifica si es un arbol binario de busqueda valido.
     * 
     * @return true si es ABB valido, false en caso contrario
     */
    public boolean esABBValido() {
        return verificarABB(raiz, null, null);
    }
    
    // ============== METODOS DE RECORRIDO ==============
    
    /**
     * Realiza un recorrido in-orden del arbol.
     * 
     * En un ABB valido, el recorrido in-orden produce
     * los elementos en orden ascendente.
     * 
     * @return lista con los elementos en orden ascendente
     */
    public List<T> recorridoInOrden() {
        List<T> resultado = new ArrayList<>();
        recorridoInOrdenRecursivo(raiz, resultado);
        return resultado;
    }
    
    /**
     * Realiza un recorrido pre-orden del arbol.
     * 
     * @return lista con los elementos en pre-orden
     */
    public List<T> recorridoPreOrden() {
        List<T> resultado = new ArrayList<>();
        recorridoPreOrdenRecursivo(raiz, resultado);
        return resultado;
    }
    
    /**
     * Realiza un recorrido post-orden del arbol.
     * 
     * @return lista con los elementos en post-orden
     */
    public List<T> recorridoPostOrden() {
        List<T> resultado = new ArrayList<>();
        recorridoPostOrdenRecursivo(raiz, resultado);
        return resultado;
    }
    
    /**
     * Realiza un recorrido por niveles del arbol.
     * 
     * @return lista con los elementos por niveles (BFS)
     */
    public List<T> recorridoPorNiveles() {
        List<T> resultado = new ArrayList<>();
        if (raiz == null) return resultado;
        
        Queue<NodoAVL<T>> cola = new LinkedList<>();
        cola.offer(raiz);
        
        while (!cola.isEmpty()) {
            NodoAVL<T> actual = cola.poll();
            resultado.add(actual.getValor());
            
            if (actual.getIzquierda() != null) {
                cola.offer(actual.getIzquierda());
            }
            if (actual.getDerecha() != null) {
                cola.offer(actual.getDerecha());
            }
        }
        
        return resultado;
    }
    
    // ============== METODOS DE BUSQUEDA ESPECIALES ==============
    
    /**
     * Encuentra el valor minimo en el arbol.
     * 
     * @return el valor minimo, o null si el arbol esta vacio
     */
    public T encontrarMinimo() {
        if (raiz == null) return null;
        return encontrarMinimoNodo(raiz).getValor();
    }
    
    /**
     * Encuentra el valor maximo en el arbol.
     * 
     * @return el valor maximo, o null si el arbol esta vacio
     */
    public T encontrarMaximo() {
        if (raiz == null) return null;
        
        NodoAVL<T> actual = raiz;
        while (actual.getDerecha() != null) {
            actual = actual.getDerecha();
        }
        return actual.getValor();
    }
    
    /**
     * Encuentra el sucesor in-orden de un valor.
     * 
     * @param valor el valor cuyo sucesor se busca
     * @return el sucesor in-orden, o null si no existe
     */
    public T encontrarSucesor(T valor) {
        NodoAVL<T> nodo = buscarRecursivo(raiz, valor);
        if (nodo == null) return null;
        
        if (nodo.getDerecha() != null) {
            return encontrarMinimoNodo(nodo.getDerecha()).getValor();
        }
        
        // Buscar ancestor que sea padre por la izquierda
        NodoAVL<T> sucesor = null;
        NodoAVL<T> actual = raiz;
        
        while (actual != null) {
            int comparacion = valor.compareTo(actual.getValor());
            if (comparacion < 0) {
                sucesor = actual;
                actual = actual.getIzquierda();
            } else if (comparacion > 0) {
                actual = actual.getDerecha();
            } else {
                break;
            }
        }
        
        return sucesor != null ? sucesor.getValor() : null;
    }
    
    // ============== METODOS DE UTILIDAD ==============
    
    /**
     * Elimina todos los elementos del arbol.
     */
    public void limpiar() {
        raiz = null;
        tamano = 0;
        // No reiniciamos el contador de rotaciones intencionalmente
    }
    
    /**
     * Convierte el arbol a un arreglo ordenado.
     * 
     * @return arreglo con los elementos en orden ascendente
     */
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        List<T> lista = recorridoInOrden();
        return (T[]) lista.toArray(new Comparable[0]);
    }
    
    // ============== METODOS PRIVADOS DE IMPLEMENTACION ==============
    
    /**
     * Implementacion recursiva de insercion.
     */
    private NodoAVL<T> insertarRecursivo(NodoAVL<T> nodo, T valor) {
        // Caso base: insertar nuevo nodo
        if (nodo == null) {
            tamano++;
            return new NodoAVL<>(valor);
        }
        
        // Insercion recursiva estandar de ABB
        int comparacion = valor.compareTo(nodo.getValor());
        if (comparacion < 0) {
            nodo.setIzquierda(insertarRecursivo(nodo.getIzquierda(), valor));
        } else if (comparacion > 0) {
            nodo.setDerecha(insertarRecursivo(nodo.getDerecha(), valor));
        } else {
            // Valor ya existe, no hacer nada
            return nodo;
        }
        
        // Actualizar altura del nodo actual
        nodo.actualizarAltura();
        
        // Obtener factor de equilibrio y rebalancear si es necesario
        int factorEquilibrio = nodo.calcularFactorEquilibrio();
        
        // Caso Left-Left (LL)
        if (factorEquilibrio > 1 && valor.compareTo(nodo.getIzquierda().getValor()) < 0) {
            return rotacionDerecha(nodo);
        }
        
        // Caso Right-Right (RR)
        if (factorEquilibrio < -1 && valor.compareTo(nodo.getDerecha().getValor()) > 0) {
            return rotacionIzquierda(nodo);
        }
        
        // Caso Left-Right (LR)
        if (factorEquilibrio > 1 && valor.compareTo(nodo.getIzquierda().getValor()) > 0) {
            nodo.setIzquierda(rotacionIzquierda(nodo.getIzquierda()));
            return rotacionDerecha(nodo);
        }
        
        // Caso Right-Left (RL)
        if (factorEquilibrio < -1 && valor.compareTo(nodo.getDerecha().getValor()) < 0) {
            nodo.setDerecha(rotacionDerecha(nodo.getDerecha()));
            return rotacionIzquierda(nodo);
        }
        
        // Nodo esta balanceado
        return nodo;
    }
    
    /**
     * Implementacion recursiva de eliminacion.
     */
    private NodoAVL<T> eliminarRecursivo(NodoAVL<T> nodo, T valor) {
        // Caso base
        if (nodo == null) {
            return null;
        }
        
        int comparacion = valor.compareTo(nodo.getValor());
        if (comparacion < 0) {
            nodo.setIzquierda(eliminarRecursivo(nodo.getIzquierda(), valor));
        } else if (comparacion > 0) {
            nodo.setDerecha(eliminarRecursivo(nodo.getDerecha(), valor));
        } else {
            // Nodo a eliminar encontrado
            tamano--;
            
            // Nodo con un solo hijo o sin hijos
            if (nodo.getIzquierda() == null) {
                return nodo.getDerecha();
            } else if (nodo.getDerecha() == null) {
                return nodo.getIzquierda();
            }
            
            // Nodo con dos hijos: obtener sucesor in-orden
            NodoAVL<T> sucesor = encontrarMinimoNodo(nodo.getDerecha());
            nodo.setValor(sucesor.getValor());
            nodo.setDerecha(eliminarRecursivo(nodo.getDerecha(), sucesor.getValor()));
        }
        
        // Actualizar altura
        nodo.actualizarAltura();
        
        // Rebalancear
        int factorEquilibrio = nodo.calcularFactorEquilibrio();
        
        // Caso Left-Left
        if (factorEquilibrio > 1 && nodo.getIzquierda().calcularFactorEquilibrio() >= 0) {
            return rotacionDerecha(nodo);
        }
        
        // Caso Left-Right
        if (factorEquilibrio > 1 && nodo.getIzquierda().calcularFactorEquilibrio() < 0) {
            nodo.setIzquierda(rotacionIzquierda(nodo.getIzquierda()));
            return rotacionDerecha(nodo);
        }
        
        // Caso Right-Right
        if (factorEquilibrio < -1 && nodo.getDerecha().calcularFactorEquilibrio() <= 0) {
            return rotacionIzquierda(nodo);
        }
        
        // Caso Right-Left
        if (factorEquilibrio < -1 && nodo.getDerecha().calcularFactorEquilibrio() > 0) {
            nodo.setDerecha(rotacionDerecha(nodo.getDerecha()));
            return rotacionIzquierda(nodo);
        }
        
        return nodo;
    }
    
    /**
     * Busqueda recursiva de un valor.
     */
    private NodoAVL<T> buscarRecursivo(NodoAVL<T> nodo, T valor) {
        if (nodo == null) {
            return null;
        }
        
        int comparacion = valor.compareTo(nodo.getValor());
        if (comparacion == 0) {
            return nodo;
        } else if (comparacion < 0) {
            return buscarRecursivo(nodo.getIzquierda(), valor);
        } else {
            return buscarRecursivo(nodo.getDerecha(), valor);
        }
    }
    
    // ============== ROTACIONES ==============
    
    /**
     * Rotacion simple derecha (para casos Left-Left).
     * 
     * <pre>
     *      y                x
     *     / \              / \
     *    x   C    =>      A   y
     *   / \                  / \
     *  A   B                B   C
     * </pre>
     */
    private NodoAVL<T> rotacionDerecha(NodoAVL<T> y) {
        contadorRotaciones++;
        
        NodoAVL<T> x = y.getIzquierda();
        NodoAVL<T> B = x.getDerecha();
        
        // Realizar rotacion
        x.setDerecha(y);
        y.setIzquierda(B);
        
        // Actualizar alturas (orden importante)
        y.actualizarAltura();
        x.actualizarAltura();
        
        return x;
    }
    
    /**
     * Rotacion simple izquierda (para casos Right-Right).
     * 
     * <pre>
     *    x                  y
     *   / \                / \
     *  A   y      =>      x   C
     *     / \            / \
     *    B   C          A   B
     * </pre>
     */
    private NodoAVL<T> rotacionIzquierda(NodoAVL<T> x) {
        contadorRotaciones++;
        
        NodoAVL<T> y = x.getDerecha();
        NodoAVL<T> B = y.getIzquierda();
        
        // Realizar rotacion
        y.setIzquierda(x);
        x.setDerecha(B);
        
        // Actualizar alturas (orden importante)
        x.actualizarAltura();
        y.actualizarAltura();
        
        return y;
    }
    
    // ============== METODOS AUXILIARES ==============
    
    /**
     * Obtiene la altura de un nodo (0 si es null).
     */
    private int obtenerAltura(NodoAVL<T> nodo) {
        return (nodo == null) ? 0 : nodo.getAltura();
    }
    
    /**
     * Encuentra el nodo con el valor minimo en un subarbol.
     */
    private NodoAVL<T> encontrarMinimoNodo(NodoAVL<T> nodo) {
        while (nodo.getIzquierda() != null) {
            nodo = nodo.getIzquierda();
        }
        return nodo;
    }
    
    /**
     * Verificacion recursiva de propiedades AVL.
     */
    private ResultadoVerificacionAVL verificarAVLRecursivo(NodoAVL<T> nodo) {
        if (nodo == null) {
            return ResultadoVerificacionAVL.vacio();
        }
        
        // Verificar subarboles
        ResultadoVerificacionAVL izq = verificarAVLRecursivo(nodo.getIzquierda());
        ResultadoVerificacionAVL der = verificarAVLRecursivo(nodo.getDerecha());
        
        // Si algun subarbol no es AVL, este tampoco lo es
        if (!izq.esAVL() || !der.esAVL()) {
            return ResultadoVerificacionAVL.invalido();
        }
        
        // Verificar factor de equilibrio
        int factorEquilibrio = izq.getAltura() - der.getAltura();
        if (Math.abs(factorEquilibrio) > 1) {
            return ResultadoVerificacionAVL.invalido();
        }
        
        // Verificar altura correcta
        int alturaCalculada = 1 + Math.max(izq.getAltura(), der.getAltura());
        if (nodo.getAltura() != alturaCalculada) {
            return ResultadoVerificacionAVL.invalido();
        }
        
        // Verificar propiedad ABB
        if (!esNodoABBValido(nodo)) {
            return ResultadoVerificacionAVL.invalido();
        }
        
        return ResultadoVerificacionAVL.valido(alturaCalculada);
    }
    
    /**
     * Verifica si un nodo mantiene la propiedad de ABB localmente.
     */
    private boolean esNodoABBValido(NodoAVL<T> nodo) {
        if (nodo.getIzquierda() != null && 
            nodo.getIzquierda().getValor().compareTo(nodo.getValor()) >= 0) {
            return false;
        }
        if (nodo.getDerecha() != null && 
            nodo.getDerecha().getValor().compareTo(nodo.getValor()) <= 0) {
            return false;
        }
        return true;
    }
    
    /**
     * Verifica la propiedad ABB globalmente.
     */
    private boolean verificarABB(NodoAVL<T> nodo, T min, T max) {
        if (nodo == null) return true;
        
        if ((min != null && nodo.getValor().compareTo(min) <= 0) ||
            (max != null && nodo.getValor().compareTo(max) >= 0)) {
            return false;
        }
        
        return verificarABB(nodo.getIzquierda(), min, nodo.getValor()) &&
               verificarABB(nodo.getDerecha(), nodo.getValor(), max);
    }
    
    // ============== METODOS DE RECORRIDO RECURSIVOS ==============
    
    private void recorridoInOrdenRecursivo(NodoAVL<T> nodo, List<T> resultado) {
        if (nodo != null) {
            recorridoInOrdenRecursivo(nodo.getIzquierda(), resultado);
            resultado.add(nodo.getValor());
            recorridoInOrdenRecursivo(nodo.getDerecha(), resultado);
        }
    }
    
    private void recorridoPreOrdenRecursivo(NodoAVL<T> nodo, List<T> resultado) {
        if (nodo != null) {
            resultado.add(nodo.getValor());
            recorridoPreOrdenRecursivo(nodo.getIzquierda(), resultado);
            recorridoPreOrdenRecursivo(nodo.getDerecha(), resultado);
        }
    }
    
    private void recorridoPostOrdenRecursivo(NodoAVL<T> nodo, List<T> resultado) {
        if (nodo != null) {
            recorridoPostOrdenRecursivo(nodo.getIzquierda(), resultado);
            recorridoPostOrdenRecursivo(nodo.getDerecha(), resultado);
            resultado.add(nodo.getValor());
        }
    }
    
    // ============== METODOS DE VISUALIZACION ==============
    
    /**
     * Muestra el arbol en formato visual jerarquico.
     */
    public void mostrarArbol() {
        if (raiz == null) {
            System.out.println("Arbol vacio");
            return;
        }
        
        System.out.println("Arbol AVL (formato: valor(altura, FE)):");
        mostrarArbolRecursivo(raiz, "", true);
    }
    
    private void mostrarArbolRecursivo(NodoAVL<T> nodo, String prefijo, boolean esDerecha) {
        if (nodo != null) {
            System.out.println(prefijo + 
                (esDerecha ? "└── " : "├── ") + 
                nodo.toString());
            
            String nuevoPrefijo = prefijo + (esDerecha ? "    " : "│   ");
            
            if (nodo.getIzquierda() != null || nodo.getDerecha() != null) {
                if (nodo.getIzquierda() != null) {
                    mostrarArbolRecursivo(nodo.getIzquierda(), nuevoPrefijo, false);
                } else {
                    System.out.println(nuevoPrefijo + "├── null");
                }
                
                if (nodo.getDerecha() != null) {
                    mostrarArbolRecursivo(nodo.getDerecha(), nuevoPrefijo, true);
                } else {
                    System.out.println(nuevoPrefijo + "└── null");
                }
            }
        }
    }
    
    /**
     * Muestra estadisticas del arbol.
     */
    public void mostrarEstadisticas() {
        System.out.println("=== Estadisticas del Arbol AVL ===");
        System.out.printf("Elementos: %d%n", tamano);
        System.out.printf("Altura: %d%n", altura());
        System.out.printf("Rotaciones realizadas: %d%n", contadorRotaciones);
        
        if (!estaVacio()) {
            System.out.printf("Valor minimo: %s%n", encontrarMinimo());
            System.out.printf("Valor maximo: %s%n", encontrarMaximo());
            
            ResultadoVerificacionAVL verificacion = verificarAVL();
            System.out.printf("Es AVL valido: %s%n", verificacion.esAVL() ? "Si" : "No");
        }
        
        System.out.println("================================");
    }
    
    @Override
    public String toString() {
        if (estaVacio()) {
            return "ArbolAVL vacio";
        }
        
        return String.format("ArbolAVL[tamano=%d, altura=%d, rotaciones=%d]", 
            tamano, altura(), contadorRotaciones);
    }
}