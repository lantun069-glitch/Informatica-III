package edu.informatica3.lucas_antun.practico_integrador.estructuras;

import edu.informatica3.lucas_antun.practico_integrador.modelo.*;
import edu.informatica3.lucas_antun.practico_integrador.nodos.NodoAVL;
import edu.informatica3.lucas_antun.practico_integrador.utils.Lista;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * AgendaMedico - Implementación con Árbol AVL
 * 
 * EJERCICIO 2: Agenda por médico con inserción/borrado y "siguiente disponible"
 * 
 * Esta clase mantiene los turnos de un médico ordenados por fecha/hora usando
 * un Árbol AVL (Adelson-Velsky and Landis). El AVL es un árbol binario de búsqueda
 * auto-balanceado que garantiza altura O(log n), permitiendo operaciones eficientes.
 * 
 * ¿POR QUÉ USAMOS AVL Y NO OTRA ESTRUCTURA?
 * 
 * 1. Red-Black Tree vs AVL:
 *    - AVL es más balanceado (factor de balance máximo = 1)
 *    - Red-Black permite desbalance mayor (altura hasta 2*log n)
 *    - Para BÚSQUEDAS frecuentes (como "siguiente turno"), AVL es superior
 *    - AVL: más búsquedas, menos inserciones/borrados
 *    - Red-Black: más inserciones/borrados, menos búsquedas
 *    - En este sistema, buscamos MÁS que insertamos → AVL es mejor opción
 * 
 * 2. Heap vs AVL:
 *    - Heap solo mantiene el mínimo/máximo accesible en O(1)
 *    - AVL mantiene TODO el árbol ordenado
 *    - Necesitamos buscar "siguiente turno >= t" → requiere orden total → AVL
 * 
 * 3. BST simple vs AVL:
 *    - BST puede degradarse a O(n) con inserciones ordenadas
 *    - AVL auto-balancea → garantiza O(log n) siempre
 * 
 * COMPLEJIDADES OBJETIVO (todas cumplidas):
 * - insert: O(log n)
 * - remove: O(log n)
 * - siguiente(t): O(log n)
 * 
 * @author Lucas Santiago Said Antun
 * @author Federico Fernández
 * @version 1.0
 */
public class AgendaMedico {
    
    // Raíz del árbol AVL
    private NodoAVL raiz;
    
    // Médico dueño de esta agenda
    private final Medico medico;
    
    // Contador de turnos
    private int tamanio;
    
    /**
     * Constructor
     * @param medico Médico dueño de esta agenda
     */
    public AgendaMedico(Medico medico) {
        this.medico = medico;
        this.raiz = null;
        this.tamanio = 0;
    }
    
    // ==================== OPERACIONES PÚBLICAS ====================
    
    /**
     * Agenda un nuevo turno
     * 
     * Complejidad: O(log n)
     * - Búsqueda del lugar de inserción: O(log n)
     * - Verificación de solapamiento: O(log n)
     * - Rotaciones de balanceo: O(1)
     * 
     * @param turno Turno a agendar
     * @return true si se agendó exitosamente, false si hay conflicto
     */
    public boolean agendar(Turno turno) {
        // Verificar que el turno no se solape con ningún turno existente
        if (tieneSolapamiento(raiz, turno)) {
            return false; // Prohibir "double booking"
        }
        
        raiz = insertarRecursivo(raiz, turno);
        tamanio++;
        return true;
    }
    
    /**
     * Cancela un turno por ID
     * 
     * Complejidad: O(log n)
     * - Búsqueda del nodo: O(log n)
     * - Eliminación: O(log n)
     * - Rotaciones de rebalanceo: O(1)
     * 
     * @param idTurno ID del turno a cancelar
     * @return true si se canceló, false si no se encontró
     */
    public boolean cancelar(String idTurno) {
        int tamanioAntes = tamanio;
        raiz = eliminarRecursivo(raiz, idTurno);
        return tamanio < tamanioAntes;
    }
    
    /**
     * Busca el siguiente turno a partir de una fecha/hora
     * 
     * Complejidad: O(log n)
     * Recorre solo una rama del árbol desde la raíz hasta encontrar
     * el turno con fecha >= t más cercana
     * 
     * @param t Fecha/hora de referencia
     * @return Optional con el turno encontrado, o empty si no hay
     */
    public Optional<Turno> siguiente(LocalDateTime t) {
        return Optional.ofNullable(buscarSiguiente(raiz, t, null));
    }
    
    /**
     * Busca el primer hueco libre de al menos duracionMin minutos
     * 
     * EJERCICIO 3: Búsqueda de hueco libre de X minutos
     * 
     * Complejidad: O(log n + k) donde k = cantidad de turnos revisados
     * 
     * Algoritmo:
     * 1. Obtener todos los turnos ordenados (in-order traversal)
     * 2. Buscar gaps entre turnos consecutivos
     * 3. Si no hay gap suficiente hoy, continuar mañana
     * 
     * @param t0 Fecha/hora desde donde buscar
     * @param durMin Duración mínima requerida en minutos
     * @return Optional con la fecha/hora del hueco, o empty si no hay
     */
    public Optional<LocalDateTime> primerHueco(LocalDateTime t0, int durMin) {
        Lista<Turno> turnos = obtenerTurnosOrdenados();
        
        // Horario laboral: 8:00 a 18:00
        int horaInicio = 8;
        int horaFin = 18;
        
        LocalDateTime actual = t0;
        int diasBuscados = 0;
        int maxDiasBuscar = 30; // Buscar hasta 30 días adelante
        
        while (diasBuscados < maxDiasBuscar) {
            // Ajustar al inicio del horario laboral si es necesario
            if (actual.getHour() < horaInicio) {
                actual = actual.withHour(horaInicio).withMinute(0);
            }
            
            LocalDateTime finDia = actual.withHour(horaFin).withMinute(0);
            
            // Buscar huecos en el día actual
            LocalDateTime cursor = actual;
            
            for (int i = 0; i < turnos.size(); i++) {
                Turno turno = turnos.get(i);
                // Solo considerar turnos del día actual
                if (turno.getFechaHora().toLocalDate().equals(actual.toLocalDate())) {
                    // ¿Hay hueco entre cursor y este turno?
                    if (turno.getFechaHora().isAfter(cursor)) {
                        long minutosDisponibles = java.time.Duration.between(
                            cursor, turno.getFechaHora()).toMinutes();
                        
                        if (minutosDisponibles >= durMin) {
                            return Optional.of(cursor); // ¡Hueco encontrado!
                        }
                    }
                    
                    // Mover cursor al final de este turno
                    if (turno.getFechaHoraFin().isAfter(cursor)) {
                        cursor = turno.getFechaHoraFin();
                    }
                }
            }
            
            // Verificar hueco al final del día
            if (cursor.isBefore(finDia)) {
                long minutosDisponibles = java.time.Duration.between(cursor, finDia).toMinutes();
                if (minutosDisponibles >= durMin) {
                    return Optional.of(cursor);
                }
            }
            
            // Pasar al día siguiente
            actual = actual.plusDays(1).withHour(horaInicio).withMinute(0);
            diasBuscados++;
        }
        
        return Optional.empty(); // No se encontró hueco
    }
    
    /**
     * Obtiene todos los turnos ordenados por fecha (in-order traversal)
     * 
     * @return Lista de turnos ordenados
     */
    public Lista<Turno> obtenerTurnosOrdenados() {
        Lista<Turno> lista = new Lista<>();
        inOrderTraversal(raiz, lista);
        return lista;
    }
    
    /**
     * Obtiene la cantidad de turnos agendados
     * @return Cantidad de turnos
     */
    public int size() {
        return tamanio;
    }
    
    /**
     * Obtiene el médico dueño de esta agenda
     * @return Médico
     */
    public Medico getMedico() {
        return medico;
    }
    
    // ==================== OPERACIONES PRIVADAS DEL AVL ====================
    
    /**
     * Inserta un turno en el árbol de forma recursiva
     * y mantiene el balance AVL
     */
    private NodoAVL insertarRecursivo(NodoAVL nodo, Turno turno) {
        // Caso base: insertar aquí
        if (nodo == null) {
            return new NodoAVL(turno);
        }
        
        // Comparar fechas para decidir izquierda o derecha
        int comparacion = turno.getFechaHora().compareTo(nodo.turno.getFechaHora());
        
        if (comparacion < 0) {
            // Insertar en subárbol izquierdo
            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, turno);
        } else {
            // Insertar en subárbol derecho (permite fechas iguales)
            nodo.derecho = insertarRecursivo(nodo.derecho, turno);
        }
        
        // Actualizar altura de este nodo
        nodo.altura = 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));
        
        // Obtener factor de balance para verificar si se desbalanceó
        int balance = obtenerBalance(nodo);
        
        // Si se desbalanceó, hay 4 casos posibles:
        
        // Caso 1: Izquierda-Izquierda (rotación simple derecha)
        if (balance > 1 && turno.getFechaHora().compareTo(nodo.izquierdo.turno.getFechaHora()) < 0) {
            return rotarDerecha(nodo);
        }
        
        // Caso 2: Derecha-Derecha (rotación simple izquierda)
        if (balance < -1 && turno.getFechaHora().compareTo(nodo.derecho.turno.getFechaHora()) >= 0) {
            return rotarIzquierda(nodo);
        }
        
        // Caso 3: Izquierda-Derecha (rotación doble: izq-der)
        if (balance > 1 && turno.getFechaHora().compareTo(nodo.izquierdo.turno.getFechaHora()) >= 0) {
            nodo.izquierdo = rotarIzquierda(nodo.izquierdo);
            return rotarDerecha(nodo);
        }
        
        // Caso 4: Derecha-Izquierda (rotación doble: der-izq)
        if (balance < -1 && turno.getFechaHora().compareTo(nodo.derecho.turno.getFechaHora()) < 0) {
            nodo.derecho = rotarDerecha(nodo.derecho);
            return rotarIzquierda(nodo);
        }
        
        // Nodo ya está balanceado
        return nodo;
    }
    
    /**
     * Elimina un turno por ID
     */
    private NodoAVL eliminarRecursivo(NodoAVL nodo, String idTurno) {
        if (nodo == null) {
            return null;
        }
        
        // Buscar el nodo a eliminar
        if (idTurno.equals(nodo.turno.getId())) {
            // ¡Encontrado! Eliminar este nodo
            
            // Caso 1: Nodo hoja o con un solo hijo
            if (nodo.izquierdo == null) {
                tamanio--;
                return nodo.derecho;
            } else if (nodo.derecho == null) {
                tamanio--;
                return nodo.izquierdo;
            }
            
            // Caso 2: Nodo con dos hijos
            // Encontrar el sucesor in-order (mínimo del subárbol derecho)
            NodoAVL sucesor = encontrarMinimo(nodo.derecho);
            nodo.turno = sucesor.turno;
            nodo.derecho = eliminarRecursivo(nodo.derecho, sucesor.turno.getId());
            
        } else {
            // Continuar buscando
            nodo.izquierdo = eliminarRecursivo(nodo.izquierdo, idTurno);
            nodo.derecho = eliminarRecursivo(nodo.derecho, idTurno);
        }
        
        // Actualizar altura
        nodo.altura = 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));
        
        // Rebalancear si es necesario
        int balance = obtenerBalance(nodo);
        
        // Rotaciones (4 casos, similar a inserción)
        if (balance > 1 && obtenerBalance(nodo.izquierdo) >= 0) {
            return rotarDerecha(nodo);
        }
        
        if (balance > 1 && obtenerBalance(nodo.izquierdo) < 0) {
            nodo.izquierdo = rotarIzquierda(nodo.izquierdo);
            return rotarDerecha(nodo);
        }
        
        if (balance < -1 && obtenerBalance(nodo.derecho) <= 0) {
            return rotarIzquierda(nodo);
        }
        
        if (balance < -1 && obtenerBalance(nodo.derecho) > 0) {
            nodo.derecho = rotarDerecha(nodo.derecho);
            return rotarIzquierda(nodo);
        }
        
        return nodo;
    }
    
    /**
     * Verifica si hay solapamiento con turnos existentes
     */
    private boolean tieneSolapamiento(NodoAVL nodo, Turno nuevoTurno) {
        if (nodo == null) {
            return false;
        }
        
        // Verificar solapamiento con este nodo
        if (nodo.turno.seSolapaCon(nuevoTurno)) {
            return true;
        }
        
        // Buscar en ambos subárboles (puede haber solapamiento en cualquiera)
        return tieneSolapamiento(nodo.izquierdo, nuevoTurno) || 
               tieneSolapamiento(nodo.derecho, nuevoTurno);
    }
    
    /**
     * Busca el siguiente turno >= t
     */
    private Turno buscarSiguiente(NodoAVL nodo, LocalDateTime t, Turno mejorHastaAhora) {
        if (nodo == null) {
            return mejorHastaAhora;
        }
        
        // Si este turno es >= t, podría ser candidato
        if (nodo.turno.getFechaHora().isAfter(t) || nodo.turno.getFechaHora().isEqual(t)) {
            // Actualizar mejor candidato
            if (mejorHastaAhora == null || 
                nodo.turno.getFechaHora().isBefore(mejorHastaAhora.getFechaHora())) {
                mejorHastaAhora = nodo.turno;
            }
            // Buscar en subárbol izquierdo por si hay algo más cercano
            return buscarSiguiente(nodo.izquierdo, t, mejorHastaAhora);
        } else {
            // Este turno es < t, buscar en subárbol derecho
            return buscarSiguiente(nodo.derecho, t, mejorHastaAhora);
        }
    }
    
    /**
     * Recorrido in-order para obtener turnos ordenados
     */
    private void inOrderTraversal(NodoAVL nodo, Lista<Turno> lista) {
        if (nodo != null) {
            inOrderTraversal(nodo.izquierdo, lista);
            lista.add(nodo.turno);
            inOrderTraversal(nodo.derecho, lista);
        }
    }
    
    /**
     * Encuentra el nodo con valor mínimo (más a la izquierda)
     */
    private NodoAVL encontrarMinimo(NodoAVL nodo) {
        while (nodo.izquierdo != null) {
            nodo = nodo.izquierdo;
        }
        return nodo;
    }
    
    // ==================== OPERACIONES DE BALANCEO AVL ====================
    
    /**
     * Obtiene la altura de un nodo
     * La altura de null es 0
     */
    private int altura(NodoAVL nodo) {
        return nodo == null ? 0 : nodo.altura;
    }
    
    /**
     * Obtiene el factor de balance de un nodo
     * Balance = altura(izquierdo) - altura(derecho)
     * 
     * En AVL, el balance debe estar en [-1, 0, 1]
     * Si balance > 1: subárbol izquierdo es más alto (desbalance izquierdo)
     * Si balance < -1: subárbol derecho es más alto (desbalance derecho)
     */
    private int obtenerBalance(NodoAVL nodo) {
        return nodo == null ? 0 : altura(nodo.izquierdo) - altura(nodo.derecho);
    }
    
    /**
     * Rotación simple a la derecha
     * 
     *       y                    x
     *      / \                  / \
     *     x   C    -->         A   y
     *    / \                      / \
     *   A   B                    B   C
     */
    private NodoAVL rotarDerecha(NodoAVL y) {
        NodoAVL x = y.izquierdo;
        NodoAVL B = x.derecho;
        
        // Realizar rotación
        x.derecho = y;
        y.izquierdo = B;
        
        // Actualizar alturas
        y.altura = 1 + Math.max(altura(y.izquierdo), altura(y.derecho));
        x.altura = 1 + Math.max(altura(x.izquierdo), altura(x.derecho));
        
        return x; // Nueva raíz
    }
    
    /**
     * Rotación simple a la izquierda
     * 
     *     x                        y
     *    / \                      / \
     *   A   y        -->         x   C
     *      / \                  / \
     *     B   C                A   B
     */
    private NodoAVL rotarIzquierda(NodoAVL x) {
        NodoAVL y = x.derecho;
        NodoAVL B = y.izquierdo;
        
        // Realizar rotación
        y.izquierdo = x;
        x.derecho = B;
        
        // Actualizar alturas
        x.altura = 1 + Math.max(altura(x.izquierdo), altura(x.derecho));
        y.altura = 1 + Math.max(altura(y.izquierdo), altura(y.derecho));
        
        return y; // Nueva raíz
    }
}
