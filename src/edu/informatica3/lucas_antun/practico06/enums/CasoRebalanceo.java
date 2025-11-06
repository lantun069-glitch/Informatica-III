package edu.informatica3.lucas_antun.practico06.enums;

/**
 * Enumeracion para clasificar los diferentes casos que pueden surgir durante
 * las operaciones de rebalanceo en un arbol Rojo-Negro.
 * 
 * Durante la insercion en un arbol Rojo-Negro, pueden surgir violaciones
 * de las propiedades que requieren correccion mediante recoloreo y rotaciones.
 * Esta enumeracion ayuda a identificar y manejar cada caso especifico.
 * 
 * Casos de rebalanceo:
 * 
 *   TIO_ROJO: El tio del nodo es rojo (recoloreo)
 *   LL: Violacion Left-Left (rotacion derecha)
 *   RR: Violacion Right-Right (rotacion izquierda)
 *   LR: Violacion Left-Right (rotacion doble)
 *   RL: Violacion Right-Left (rotacion doble)
 * 
 * 
 * @author Lucas Santiago Said Antun
 * @author Federico Fernández
 * @version 1.0
 * @since 2025-01-01
 */
public enum CasoRebalanceo {
    
    /**
     * Caso donde el tio del nodo insertado es rojo.
     * 
     * Solucion: Recoloreo simple
     * 
     *   Padre → Negro
     *   Tio → Negro
     *   Abuelo → Rojo
     *   Continuar verificacion desde el abuelo
     * 
     */
    TIO_ROJO("Tio Rojo", "Recoloreo: padre y tio → negro, abuelo → rojo"),
    
    /**
     * Caso Left-Left: violacion en linea izquierda.
     * 
     * Configuracion:
     * 
     *   Nodo insertado es hijo izquierdo
     *   Su padre es hijo izquierdo del abuelo
     *   El tio es negro
     * 
     * 
     * Solucion: Rotacion simple derecha en el abuelo
     */
    LL("Left-Left", "Rotacion simple derecha + intercambio de colores"),
    
    /**
     * Caso Right-Right: violacion en linea derecha.
     * 
     * Configuracion:
     * 
     *   Nodo insertado es hijo derecho
     *   Su padre es hijo derecho del abuelo
     *   El tio es negro
     * 
     * 
     * Solucion: Rotacion simple izquierda en el abuelo
     */
    RR("Right-Right", "Rotacion simple izquierda + intercambio de colores"),
    
    /**
     * Caso Left-Right: violacion angular izquierda-derecha.
     * 
     * Configuracion:
     * 
     *   Nodo insertado es hijo derecho
     *   Su padre es hijo izquierdo del abuelo
     *   El tio es negro
     * 
     * 
     * Solucion: Rotacion doble (izq en padre + der en abuelo)
     */
    LR("Left-Right", "Rotacion doble: izquierda en padre, derecha en abuelo"),
    
    /**
     * Caso Right-Left: violacion angular derecha-izquierda.
     * 
     * Configuracion:
     * 
     *   Nodo insertado es hijo izquierdo
     *   Su padre es hijo derecho del abuelo
     *   El tio es negro
     * 
     * 
     * Solucion: Rotacion doble (der en padre + izq en abuelo)
     */
    RL("Right-Left", "Rotacion doble: derecha en padre, izquierda en abuelo");
    
    /** Nombre descriptivo del caso. */
    private final String nombre;
    
    /** Descripcion de la solucion para este caso. */
    private final String solucion;
    
    /**
     * Constructor para casos de rebalanceo.
     * 
     * @param nombre nombre descriptivo del caso
     * @param solucion descripcion de la solucion
     */
    CasoRebalanceo(String nombre, String solucion) {
        this.nombre = nombre;
        this.solucion = solucion;
    }
    
    /**
     * Obtiene el nombre descriptivo del caso.
     * 
     * @return el nombre del caso
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Obtiene la descripcion de la solucion para este caso.
     * 
     * @return la descripcion de la solucion
     */
    public String getSolucion() {
        return solucion;
    }
    
    /**
     * Verifica si este caso requiere rotaciones.
     * 
     * @return true si requiere rotaciones, false si solo recoloreo
     */
    public boolean requiereRotacion() {
        return this != TIO_ROJO;
    }
    
    /**
     * Verifica si este caso requiere rotacion doble.
     * 
     * @return true si requiere rotacion doble, false en caso contrario
     */
    public boolean esRotacionDoble() {
        return this == LR || this == RL;
    }
    
    /**
     * Verifica si este caso es simetrico (casos RR y RL).
     * 
     * @return true si el caso esta en la rama derecha, false si esta en la izquierda
     */
    public boolean esRamaDerecha() {
        return this == RR || this == RL;
    }
    
    @Override
    public String toString() {
        return String.format("%s: %s", nombre, solucion);
    }
    
    /**
     * Retorna una descripcion detallada del caso con ejemplo.
     * 
     * @return descripcion detallada del caso
     */
    public String descripcionDetallada() {
        return switch (this) {
            case TIO_ROJO -> String.format(
                "%s%n" +
                "  └─ Configuracion: Padre y tio son rojos%n" +
                "  └─ Accion: Cambiar colores (padre→negro, tio→negro, abuelo→rojo)%n" +
                "  └─ Efecto: Empuja el problema hacia arriba del arbol",
                nombre);
                
            case LL -> String.format(
                "%s%n" +
                "  └─ Configuracion: Insercion en subarbol izq del hijo izq%n" +
                "  └─ Accion: Rotacion simple derecha en abuelo%n" +
                "  └─ Efecto: Padre se convierte en nueva raiz del subarbol",
                nombre);
                
            case RR -> String.format(
                "%s%n" +
                "  └─ Configuracion: Insercion en subarbol der del hijo der%n" +
                "  └─ Accion: Rotacion simple izquierda en abuelo%n" +
                "  └─ Efecto: Padre se convierte en nueva raiz del subarbol",
                nombre);
                
            case LR -> String.format(
                "%s%n" +
                "  └─ Configuracion: Insercion en subarbol der del hijo izq%n" +
                "  └─ Accion: Rotacion izq en padre, luego rotacion der en abuelo%n" +
                "  └─ Efecto: Nodo insertado se convierte en nueva raiz del subarbol",
                nombre);
                
            case RL -> String.format(
                "%s%n" +
                "  └─ Configuracion: Insercion en subarbol izq del hijo der%n" +
                "  └─ Accion: Rotacion der en padre, luego rotacion izq en abuelo%n" +
                "  └─ Efecto: Nodo insertado se convierte en nueva raiz del subarbol",
                nombre);
        };
    }
}