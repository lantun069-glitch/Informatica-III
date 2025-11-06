package edu.informatica3.lucas_antun.practico06.estructuras;

import edu.informatica3.lucas_antun.practico06.enums.*;
import edu.informatica3.lucas_antun.practico06.nodos.*;
import java.util.*;

/**
 * Implementacion de Arbol Rojo-Negro.
 */
public class ArbolRojoNegro<K extends Comparable<K>, V> {
    
    /** Nodo centinela NIL compartido por todo el arbol. */
    private final NodoRojoNegro<K, V> NIL;
    
    /** Nodo raiz del arbol. */
    private NodoRojoNegro<K, V> raiz;
    
    /** Contador de rotaciones para analisis de rendimiento. */
    private int contadorRotaciones;
    
    /** Numero de elementos en el arbol. */
    private int tamano;
    
    /**
     * Construye un arbol Rojo-Negro vacio.
     */
    public ArbolRojoNegro() {
        this.NIL = new NodoRojoNegro<>(); // Nodo NIL siempre negro
        this.raiz = NIL;
        this.contadorRotaciones = 0;
        this.tamano = 0;
    }
    
    // ============== OPERACIONES PUBLICAS ==============
    
    /**
     * Inserta un nuevo par clave-valor en el arbol.
     * 
     * @param clave la clave a insertar
     * @param valor el valor asociado
     * @throws IllegalArgumentException si la clave es null
     */
    public void insertar(K clave, V valor) {
        if (clave == null) {
            throw new IllegalArgumentException("La clave no puede ser null");
        }
        
        NodoRojoNegro<K, V> nuevoNodo = insertarBST(clave, valor);
        if (nuevoNodo != null) {
            arreglarInsercion(nuevoNodo);
        }
    }
    
    /**
     * Busca un nodo por su clave.
     * 
     * @param clave la clave a buscar
     * @return el nodo encontrado o NIL si no existe
     */
    public NodoRojoNegro<K, V> buscar(K clave) {
        if (clave == null) {
            return NIL;
        }
        
        NodoRojoNegro<K, V> actual = raiz;
        while (!actual.esNIL()) {
            int comparacion = clave.compareTo(actual.getClave());
            if (comparacion == 0) {
                return actual;
            } else if (comparacion < 0) {
                actual = actual.getIzquierda();
            } else {
                actual = actual.getDerecha();
            }
        }
        return NIL;
    }
    
    /**
     * Verifica si el arbol contiene una clave.
     * 
     * @param clave la clave a verificar
     * @return true si existe, false en caso contrario
     */
    public boolean contiene(K clave) {
        return !buscar(clave).esNIL();
    }
    
    /**
     * Obtiene el valor asociado a una clave.
     * 
     * @param clave la clave cuyo valor se busca
     * @return el valor asociado o null si no existe
     */
    public V obtenerValor(K clave) {
        NodoRojoNegro<K, V> nodo = buscar(clave);
        return nodo.esNIL() ? null : nodo.getValor();
    }
    
    // ============== OPERACIONES DE INFORMACION ==============
    
    /**
     * @return el numero de elementos en el arbol
     */
    public int tamano() {
        return tamano;
    }
    
    /**
     * @return true si el arbol esta vacio
     */
    public boolean estaVacio() {
        return raiz.esNIL();
    }
    
    /**
     * @return el numero de rotaciones realizadas
     */
    public int getContadorRotaciones() {
        return contadorRotaciones;
    }
    
    /**
     * Reinicia el contador de rotaciones.
     */
    public void reiniciarContadorRotaciones() {
        this.contadorRotaciones = 0;
    }
    
    // ============== RECORRIDOS ==============
    
    /**
     * Realiza un recorrido in-orden del arbol.
     * 
     * @return lista de claves en orden ascendente
     */
    public List<K> recorridoInOrden() {
        List<K> resultado = new ArrayList<>();
        recorridoInOrdenRecursivo(raiz, resultado);
        return resultado;
    }
    
    private void recorridoInOrdenRecursivo(NodoRojoNegro<K, V> nodo, List<K> resultado) {
        if (!nodo.esNIL()) {
            recorridoInOrdenRecursivo(nodo.getIzquierda(), resultado);
            resultado.add(nodo.getClave());
            recorridoInOrdenRecursivo(nodo.getDerecha(), resultado);
        }
    }
    
    // ============== OPERACIONES ESPECIALES ==============
    
    /**
     * Encuentra el sucesor in-orden de un nodo.
     * 
     * @param nodo el nodo cuyo sucesor se busca
     * @return el sucesor o NIL si no existe
     */
    public NodoRojoNegro<K, V> sucesor(NodoRojoNegro<K, V> nodo) {
        if (nodo.esNIL()) {
            return NIL;
        }
        
        // Si tiene hijo derecho, el sucesor es el minimo del subarbol derecho
        if (!nodo.getDerecha().esNIL()) {
            return encontrarMinimo(nodo.getDerecha());
        }
        
        // Si no, buscar el ancestro que sea padre por la izquierda
        NodoRojoNegro<K, V> padre = nodo.getPadre();
        while (!padre.esNIL() && nodo == padre.getDerecha()) {
            nodo = padre;
            padre = padre.getPadre();
        }
        
        return padre;
    }
    
    /**
     * Encuentra el predecesor in-orden de un nodo.
     * 
     * @param nodo el nodo cuyo predecesor se busca
     * @return el predecesor o NIL si no existe
     */
    public NodoRojoNegro<K, V> predecesor(NodoRojoNegro<K, V> nodo) {
        if (nodo.esNIL()) {
            return NIL;
        }
        
        // Si tiene hijo izquierdo, el predecesor es el maximo del subarbol izquierdo
        if (!nodo.getIzquierda().esNIL()) {
            return encontrarMaximo(nodo.getIzquierda());
        }
        
        // Si no, buscar el ancestro que sea padre por la derecha
        NodoRojoNegro<K, V> padre = nodo.getPadre();
        while (!padre.esNIL() && nodo == padre.getIzquierda()) {
            nodo = padre;
            padre = padre.getPadre();
        }
        
        return padre;
    }
    
    /**
     * Realiza una consulta por rango, devolviendo todas las claves
     * en el intervalo [claveInicial, claveFinal].
     * 
     * @param claveInicial limite inferior del rango
     * @param claveFinal limite superior del rango
     * @return lista de claves en el rango especificado
     */
    public List<K> consultaRango(K claveInicial, K claveFinal) {
        List<K> resultado = new ArrayList<>();
        consultaRangoRecursivo(raiz, claveInicial, claveFinal, resultado);
        return resultado;
    }
    
    private void consultaRangoRecursivo(NodoRojoNegro<K, V> nodo, K inicio, K fin, List<K> resultado) {
        if (nodo.esNIL()) {
            return;
        }
        
        // Si el nodo actual es mayor que el inicio, explorar subarbol izquierdo
        if (nodo.getClave().compareTo(inicio) > 0) {
            consultaRangoRecursivo(nodo.getIzquierda(), inicio, fin, resultado);
        }
        
        // Si el nodo esta en el rango, incluirlo
        if (nodo.getClave().compareTo(inicio) >= 0 && nodo.getClave().compareTo(fin) <= 0) {
            resultado.add(nodo.getClave());
        }
        
        // Si el nodo actual es menor que el final, explorar subarbol derecho
        if (nodo.getClave().compareTo(fin) < 0) {
            consultaRangoRecursivo(nodo.getDerecha(), inicio, fin, resultado);
        }
    }
    
    // ============== VERIFICADORES DE PROPIEDADES ==============
    
    /**
     * Verifica si la raiz es negra.
     * 
     * @return true si la raiz es negra o el arbol esta vacio
     */
    public boolean raizEsNegra() {
        return raiz.esNIL() || raiz.esNegro();
    }
    
    /**
     * Verifica que no hay dos nodos rojos consecutivos.
     * 
     * @return true si se cumple la propiedad rojo-rojo
     */
    public boolean sinNodosRojosConsecutivos() {
        return verificarRojoRojo(raiz);
    }
    
    private boolean verificarRojoRojo(NodoRojoNegro<K, V> nodo) {
        if (nodo.esNIL()) {
            return true;
        }
        
        // Si el nodo es rojo, sus hijos deben ser negros
        if (nodo.esRojo()) {
            if (nodo.getIzquierda().esRojo() || nodo.getDerecha().esRojo()) {
                return false;
            }
        }
        
        return verificarRojoRojo(nodo.getIzquierda()) && verificarRojoRojo(nodo.getDerecha());
    }
    
    /**
     * Verifica que todos los caminos tienen la misma altura negra.
     * 
     * @return la altura negra si es valida, -1 si no
     */
    public int alturaNegra() {
        return calcularAlturaNegra(raiz);
    }
    
    private int calcularAlturaNegra(NodoRojoNegro<K, V> nodo) {
        if (nodo.esNIL()) {
            return 0; // Los nodos NIL no cuentan para la altura negra
        }
        
        int alturaIzq = calcularAlturaNegra(nodo.getIzquierda());
        int alturaDer = calcularAlturaNegra(nodo.getDerecha());
        
        // Si algun subarbol es invalido o las alturas no coinciden
        if (alturaIzq == -1 || alturaDer == -1 || alturaIzq != alturaDer) {
            return -1;
        }
        
        // Sumar 1 si el nodo actual es negro
        int incremento = nodo.esNegro() ? 1 : 0;
        return alturaIzq + incremento;
    }
    
    // ============== METODOS PRIVADOS DE IMPLEMENTACION ==============
    
    /**
     * Inserta un nodo usando logica de arbol binario de busqueda estandar.
     */
    private NodoRojoNegro<K, V> insertarBST(K clave, V valor) {
        NodoRojoNegro<K, V> nuevoNodo = new NodoRojoNegro<>(clave, valor, Color.ROJO, NIL);
        NodoRojoNegro<K, V> padre = NIL;
        NodoRojoNegro<K, V> actual = raiz;
        
        // Buscar la posicion de insercion
        while (!actual.esNIL()) {
            padre = actual;
            int comparacion = clave.compareTo(actual.getClave());
            if (comparacion == 0) {
                // Clave ya existe, actualizar valor
                actual.setValor(valor);
                return null; // No se inserto nodo nuevo
            } else if (comparacion < 0) {
                actual = actual.getIzquierda();
            } else {
                actual = actual.getDerecha();
            }
        }
        
        // Establecer el padre del nuevo nodo
        nuevoNodo.setPadre(padre);
        
        // Insertar el nodo en la posicion correcta
        if (padre.esNIL()) {
            raiz = nuevoNodo; // Primer nodo del arbol
        } else if (clave.compareTo(padre.getClave()) < 0) {
            padre.setIzquierda(nuevoNodo);
        } else {
            padre.setDerecha(nuevoNodo);
        }
        
        tamano++;
        return nuevoNodo;
    }
    
    /**
     * Corrige las propiedades del arbol despues de una insercion.
     */
    private void arreglarInsercion(NodoRojoNegro<K, V> nodo) {
        while (!nodo.getPadre().esNIL() && nodo.getPadre().esRojo()) {
            NodoRojoNegro<K, V> padre = nodo.getPadre();
            NodoRojoNegro<K, V> abuelo = padre.getPadre();
            
            if (padre == abuelo.getIzquierda()) {
                // Padre es hijo izquierdo del abuelo
                NodoRojoNegro<K, V> tio = abuelo.getDerecha();
                
                if (tio.esRojo()) {
                    // Caso 1: Tio es rojo -> recoloreo
                    padre.setColor(Color.NEGRO);
                    tio.setColor(Color.NEGRO);
                    abuelo.setColor(Color.ROJO);
                    nodo = abuelo;
                } else {
                    // Tio es negro -> rotaciones
                    if (nodo == padre.getDerecha()) {
                        // Caso 2: LR -> convertir a LL
                        nodo = padre;
                        rotacionIzquierda(nodo);
                        padre = nodo.getPadre(); // Actualizar padre despues de rotacion
                    }
                    // Caso 3: LL -> rotacion derecha
                    padre.setColor(Color.NEGRO);
                    abuelo.setColor(Color.ROJO);
                    rotacionDerecha(abuelo);
                }
            } else {
                // Padre es hijo derecho del abuelo (simetrico)
                NodoRojoNegro<K, V> tio = abuelo.getIzquierda();
                
                if (tio.esRojo()) {
                    // Caso 1: Tio es rojo -> recoloreo
                    padre.setColor(Color.NEGRO);
                    tio.setColor(Color.NEGRO);
                    abuelo.setColor(Color.ROJO);
                    nodo = abuelo;
                } else {
                    // Tio es negro -> rotaciones
                    if (nodo == padre.getIzquierda()) {
                        // Caso 2: RL -> convertir a RR
                        nodo = padre;
                        rotacionDerecha(nodo);
                        padre = nodo.getPadre(); // Actualizar padre despues de rotacion
                    }
                    // Caso 3: RR -> rotacion izquierda
                    padre.setColor(Color.NEGRO);
                    abuelo.setColor(Color.ROJO);
                    rotacionIzquierda(abuelo);
                }
            }
        }
        
        raiz.setColor(Color.NEGRO); // La raiz siempre debe ser negra
    }
    
    /**
     * Rotacion simple izquierda.
     */
    private void rotacionIzquierda(NodoRojoNegro<K, V> x) {
        contadorRotaciones++;
        
        NodoRojoNegro<K, V> y = x.getDerecha();
        x.setDerecha(y.getIzquierda());
        
        if (!y.getIzquierda().esNIL()) {
            y.getIzquierda().setPadre(x);
        }
        
        y.setPadre(x.getPadre());
        
        if (x.getPadre().esNIL()) {
            raiz = y;
        } else if (x == x.getPadre().getIzquierda()) {
            x.getPadre().setIzquierda(y);
        } else {
            x.getPadre().setDerecha(y);
        }
        
        y.setIzquierda(x);
        x.setPadre(y);
    }
    
    /**
     * Rotacion simple derecha.
     */
    private void rotacionDerecha(NodoRojoNegro<K, V> y) {
        contadorRotaciones++;
        
        NodoRojoNegro<K, V> x = y.getIzquierda();
        y.setIzquierda(x.getDerecha());
        
        if (!x.getDerecha().esNIL()) {
            x.getDerecha().setPadre(y);
        }
        
        x.setPadre(y.getPadre());
        
        if (y.getPadre().esNIL()) {
            raiz = x;
        } else if (y == y.getPadre().getIzquierda()) {
            y.getPadre().setIzquierda(x);
        } else {
            y.getPadre().setDerecha(x);
        }
        
        x.setDerecha(y);
        y.setPadre(x);
    }
    
    /**
     * Encuentra el nodo con la clave minima en un subarbol.
     */
    private NodoRojoNegro<K, V> encontrarMinimo(NodoRojoNegro<K, V> nodo) {
        while (!nodo.getIzquierda().esNIL()) {
            nodo = nodo.getIzquierda();
        }
        return nodo;
    }
    
    /**
     * Encuentra el nodo con la clave maxima en un subarbol.
     */
    private NodoRojoNegro<K, V> encontrarMaximo(NodoRojoNegro<K, V> nodo) {
        while (!nodo.getDerecha().esNIL()) {
            nodo = nodo.getDerecha();
        }
        return nodo;
    }
    
    // ============== METODOS DE VISUALIZACION ==============
    
    /**
     * Muestra el arbol en formato visual.
     */
    public void mostrarArbol() {
        if (raiz.esNIL()) {
            System.out.println("Arbol vacio");
            return;
        }
        
        System.out.println("Arbol Rojo-Negro (formato: clave(color)):");
        mostrarArbolRecursivo(raiz, "", true);
    }
    
    private void mostrarArbolRecursivo(NodoRojoNegro<K, V> nodo, String prefijo, boolean esDerecha) {
        if (!nodo.esNIL()) {
            System.out.println(prefijo + 
                (esDerecha ? "└── " : "├── ") + 
                nodo.toString());
            
            String nuevoPrefijo = prefijo + (esDerecha ? "    " : "│   ");
            
            if (!nodo.getIzquierda().esNIL() || !nodo.getDerecha().esNIL()) {
                mostrarArbolRecursivo(nodo.getIzquierda(), nuevoPrefijo, false);
                mostrarArbolRecursivo(nodo.getDerecha(), nuevoPrefijo, true);
            }
        }
    }
    
    /**
     * Muestra estadisticas del arbol.
     */
    public void mostrarEstadisticas() {
        System.out.println("=== Estadisticas del Arbol Rojo-Negro ===");
        System.out.printf("Elementos: %d%n", tamano);
        System.out.printf("Rotaciones realizadas: %d%n", contadorRotaciones);
        System.out.printf("Raiz es negra: %s%n", raizEsNegra());
        System.out.printf("Sin nodos rojos consecutivos: %s%n", sinNodosRojosConsecutivos());
        
        int alturaNegra = alturaNegra();
        if (alturaNegra != -1) {
            System.out.printf("Altura negra: %d%n", alturaNegra);
        } else {
            System.out.println(" Altura negra inconsistente");
        }
        
        System.out.println("========================================");
    }
    
    @Override
    public String toString() {
        return String.format("ArbolRojoNegro[tamano=%d, rotaciones=%d]", tamano, contadorRotaciones);
    }
}