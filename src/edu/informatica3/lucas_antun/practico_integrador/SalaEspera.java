package edu.informatica3.lucas_antun.practico_integrador;

/**
 * SalaEspera - Implementación con Cola Circular
 * 
 * EJERCICIO 4: Sala de espera con cola circular y "overflow control"
 * 
 * Esta clase implementa una cola circular (circular queue o ring buffer) para
 * gestionar una sala de espera física con capacidad limitada.
 * 
 * ¿POR QUÉ UNA COLA CIRCULAR Y NO UNA COLA NORMAL?
 * 
 * 1. Cola con Array Simple:
 *    - Al eliminar elementos del frente, se desperdicia espacio
 *    - Requeriría desplazar todos los elementos O(n)
 *    - O usar un ArrayList y eliminar el primer elemento O(n)
 * 
 * 2. Cola Circular:
 *    - Reutiliza el espacio del array de forma circular
 *    - TODAS las operaciones son O(1): enqueue, dequeue, peek
 *    - Ideal para buffers de tamaño fijo (nuestra sala tiene capacidad fija)
 * 
 * 3. Linked List:
 *    - Operaciones O(1) también
 *    - Pero usa más memoria (punteros/referencias)
 *    - Para capacidad fija, el array circular es más eficiente
 * 
 * CARACTERÍSTICAS ESPECIALES:
 * - Capacidad fija (no crece dinámicamente)
 * - Overflow control: si está llena, elimina el más antiguo automáticamente
 * - Indices circulares con operación módulo
 * 
 * COMPLEJIDADES (todas O(1)):
 * - llega(): O(1)
 * - atiende(): O(1)
 * - peek(): O(1)
 * - size(): O(1)
 * 
 * @author Lucas Antun
 * @version 1.0
 */
public class SalaEspera {
    
    // Array circular que almacena los DNIs de los pacientes
    private String[] cola;
    
    // Índice del frente de la cola (próximo a atender)
    private int frente;
    
    // Índice del final de la cola (donde se agrega el siguiente)
    private int fin;
    
    // Cantidad actual de pacientes en la cola
    private int cantidad;
    
    // Capacidad máxima de la sala
    private final int capacidad;
    
    /**
     * Constructor
     * 
     * @param capacidad Capacidad máxima de la sala de espera
     */
    public SalaEspera(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("Capacidad debe ser mayor a 0");
        }
        this.capacidad = capacidad;
        this.cola = new String[capacidad];
        this.frente = 0;
        this.fin = 0;
        this.cantidad = 0;
    }
    
    /**
     * Agrega un paciente a la sala de espera
     * 
     * Si la sala está llena, elimina automáticamente al paciente más antiguo
     * (el que está en el frente) para hacer espacio.
     * 
     * Esta política se llama "overflow control" o "ring buffer overwrite".
     * 
     * Complejidad: O(1)
     * 
     * @param dni DNI del paciente que llega
     * @return DNI del paciente eliminado si hubo overflow, null en caso contrario
     */
    public String llega(String dni) {
        String eliminado = null;
        
        // Si la cola está llena, eliminar el más antiguo
        if (cantidad == capacidad) {
            eliminado = cola[frente];
            // Avanzar frente de forma circular
            frente = (frente + 1) % capacidad;
            cantidad--;
        }
        
        // Agregar el nuevo paciente en el final
        cola[fin] = dni;
        fin = (fin + 1) % capacidad; // Avanzar fin de forma circular
        cantidad++;
        
        return eliminado;
    }
    
    /**
     * Atiende al próximo paciente (elimina del frente)
     * 
     * Complejidad: O(1)
     * 
     * @return DNI del paciente atendido, null si la cola está vacía
     */
    public String atiende() {
        if (cantidad == 0) {
            return null; // Cola vacía
        }
        
        String atendido = cola[frente];
        cola[frente] = null; // Limpiar referencia
        frente = (frente + 1) % capacidad; // Avanzar frente
        cantidad--;
        
        return atendido;
    }
    
    /**
     * Consulta el próximo paciente sin eliminarlo
     * 
     * Complejidad: O(1)
     * 
     * @return DNI del próximo paciente, null si la cola está vacía
     */
    public String peek() {
        if (cantidad == 0) {
            return null;
        }
        return cola[frente];
    }
    
    /**
     * Obtiene la cantidad actual de pacientes
     * 
     * Complejidad: O(1)
     * 
     * @return Cantidad de pacientes en la sala
     */
    public int size() {
        return cantidad;
    }
    
    /**
     * Obtiene la capacidad máxima de la sala
     * 
     * @return Capacidad máxima
     */
    public int getCapacidad() {
        return capacidad;
    }
    
    /**
     * Verifica si la sala está vacía
     * 
     * @return true si está vacía, false en caso contrario
     */
    public boolean estaVacia() {
        return cantidad == 0;
    }
    
    /**
     * Verifica si la sala está llena
     * 
     * @return true si está llena, false en caso contrario
     */
    public boolean estaLlena() {
        return cantidad == capacidad;
    }
    
    /**
     * Muestra el estado actual de la cola para depuración
     * Útil para visualizar cómo funciona la cola circular
     */
    public void mostrarEstado() {
        if (cantidad == 0) {
            System.out.println("FRONT → [] ← REAR (vacía)");
            return;
        }
        
        System.out.print("FRONT → [");
        
        // Recorrer la cola de forma circular desde frente hasta fin
        int index = frente;
        for (int i = 0; i < cantidad; i++) {
            System.out.print(cola[index]);
            if (i < cantidad - 1) {
                System.out.print(", ");
            }
            index = (index + 1) % capacidad;
        }
        
        System.out.println("] ← REAR");
        System.out.println("Tamaño actual: " + cantidad + "/" + capacidad);
    }
    
    /**
     * Representación en String para depuración
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SalaEspera{");
        sb.append("capacidad=").append(capacidad);
        sb.append(", cantidad=").append(cantidad);
        sb.append(", frente=").append(frente);
        sb.append(", fin=").append(fin);
        sb.append(", cola=[");
        
        for (int i = 0; i < capacidad; i++) {
            if (cola[i] != null) {
                sb.append(cola[i]);
            } else {
                sb.append("null");
            }
            if (i < capacidad - 1) {
                sb.append(", ");
            }
        }
        
        sb.append("]}");
        return sb.toString();
    }
}
