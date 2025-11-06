package edu.informatica3.lucas_antun.practico05.utils;

/**
 * Clase auxiliar para encapsular el resultado de operaciones de verificacion
 * en arboles AVL.
 * 
 * Esta clase se utiliza para retornar multiples valores desde metodos
 * de verificacion, especificamente si un arbol cumple con las propiedades
 * AVL y cual es su altura.
 * 
 * Es util para algoritmos que necesitan verificar la validez de un
 * arbol AVL en una sola pasada, calculando simultaneamente la altura
 * y verificando las propiedades de balance.
 * 
 * @author Lucas Santiago Said Antun
 * @author Federico Fernández
 * @version 1.0
 * @since 2025-01-01
 */
public class ResultadoVerificacionAVL {
    
    /** Indica si el arbol verificado cumple con las propiedades AVL. */
    private final boolean esAVL;
    
    /** La altura del arbol verificado. */
    private final int altura;
    
    /**
     * Construye un nuevo resultado de verificacion AVL.
     * 
     * @param esAVL true si el arbol es un AVL valido, false en caso contrario
     * @param altura la altura del arbol (0 para arboles vacios)
     * 
     * @throws IllegalArgumentException si la altura es negativa
     * 
     * @example
     * <pre>
     * // Arbol valido con altura 3
     * ResultadoVerificacionAVL resultado = new ResultadoVerificacionAVL(true, 3);
     * 
     * // Arbol invalido
     * ResultadoVerificacionAVL resultado = new ResultadoVerificacionAVL(false, 0);
     * </pre>
     */
    public ResultadoVerificacionAVL(boolean esAVL, int altura) {
        if (altura < 0) {
            throw new IllegalArgumentException("La altura no puede ser negativa");
        }
        
        this.esAVL = esAVL;
        this.altura = altura;
    }
    
    /**
     * Indica si el arbol verificado es un AVL valido.
     * 
     * Un arbol es AVL valido si:
     * 
     *   Es un arbol binario de busqueda valido
     *   Para cada nodo, |altura_izq - altura_der| ≤ 1
     *   Todos los subarboles tambien son AVL validos
     * 
     * 
     * @return true si es AVL valido, false en caso contrario
     */
    public boolean esAVL() {
        return esAVL;
    }
    
    /**
     * Obtiene la altura del arbol verificado.
     * 
     * La altura es el numero de niveles en el arbol, donde:
     * 
     *   Un arbol vacio tiene altura 0
     *   Un arbol con solo la raiz tiene altura 1
     *   La altura aumenta en 1 por cada nivel adicional
     * 
     * 
     * @return la altura del arbol (≥ 0)
     */
    public int getAltura() {
        return altura;
    }
    
    /**
     * Crea un resultado que indica un arbol AVL valido.
     * 
     * @param altura la altura del arbol valido
     * @return un resultado indicando arbol AVL valido con la altura especificada
     * @throws IllegalArgumentException si la altura es negativa
     */
    public static ResultadoVerificacionAVL valido(int altura) {
        return new ResultadoVerificacionAVL(true, altura);
    }
    
    /**
     * Crea un resultado que indica un arbol no AVL.
     * 
     * La altura se establece en 0 por convencion cuando el arbol no es valido.
     * 
     * @return un resultado indicando arbol no AVL
     */
    public static ResultadoVerificacionAVL invalido() {
        return new ResultadoVerificacionAVL(false, 0);
    }
    
    /**
     * Crea un resultado para un arbol vacio.
     * 
     * Un arbol vacio se considera AVL valido con altura 0.
     * 
     * @return un resultado para arbol vacio
     */
    public static ResultadoVerificacionAVL vacio() {
        return new ResultadoVerificacionAVL(true, 0);
    }
    
    /**
     * Compara este resultado con otro objeto para determinar igualdad.
     * 
     * Dos resultados son iguales si tienen el mismo estado AVL y la misma altura.
     * 
     * @param obj el objeto a comparar
     * @return true si los objetos son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        ResultadoVerificacionAVL that = (ResultadoVerificacionAVL) obj;
        return esAVL == that.esAVL && altura == that.altura;
    }
    
    /**
     * Retorna el codigo hash de este resultado.
     * 
     * @return el codigo hash basado en el estado AVL y la altura
     */
    @Override
    public int hashCode() {
        int result = Boolean.hashCode(esAVL);
        result = 31 * result + altura;
        return result;
    }
    
    /**
     * Retorna una representacion en cadena de este resultado.
     * 
     * @return representacion en cadena del resultado
     * 
     * @example
     * <pre>
     * ResultadoVerificacionAVL resultado = ResultadoVerificacionAVL.valido(3);
     * System.out.println(resultado); // "AVL: Si, Altura: 3"
     * 
     * ResultadoVerificacionAVL invalido = ResultadoVerificacionAVL.invalido();
     * System.out.println(invalido); // "AVL: No, Altura: 0"
     * </pre>
     */
    @Override
    public String toString() {
        return String.format("AVL: %s, Altura: %d", 
            esAVL ? "Si" : "No", altura);
    }
    
    /**
     * Retorna una representacion detallada en cadena de este resultado.
     * 
     * @return representacion detallada del resultado
     */
    public String toStringDetallado() {
        if (altura == 0) {
            return esAVL ? "Arbol vacio (AVL valido)" : "Arbol invalido";
        } else {
            return String.format("Arbol %s con altura %d", 
                esAVL ? "AVL valido" : "no AVL", altura);
        }
    }
}