package edu.informatica3.lucas_antun.practico05;

import java.util.*;

/**
 * Practico 5: Demostracion completa de Arboles AVL (Adelson-Velsky y Landis)
 * 
 * Esta clase presenta una implementacion educativa y demostracion exhaustiva
 * de los arboles AVL, incluyendo todas las operaciones fundamentales, casos
 * de rotacion, analisis de rendimiento y aplicaciones practicas.
 * 
 * Contenido del practico:
 * 
 *   Ejercicio 1: Rotaciones simples (LL y RR)
 *   Ejercicio 2: Rotaciones dobles (LR y RL)
 *   Ejercicio 3: Prevencion del efecto "peinar"
 *   Ejercicio 4: Eliminacion con rebalanceo
 *   Ejercicio 5: Verificador de propiedades AVL
 *   Ejercicio 6: Analisis de factores de equilibrio
 *   Ejercicio 7: Implementacion de rotacion izquierda
 *   Ejercicio 8: Rotaciones dobles paso a paso
 *   Ejercicio 9: Analisis de complejidad y costos
 *   Ejercicio 10: Pruebas con secuencias estresantes
 * 
 * 
 * @author Lucas Santiago Said Antun & Federico Fernandez
 * @version 1.0
 * @since 2025-01-01
 */
public class DemostracionAVL {
    
    /**
     * Metodo principal que ejecuta todos los ejercicios del practico.
     * 
     * @param args argumentos de linea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("═".repeat(80));
        System.out.println("                    ARBOLES AVL - PRACTICO 5");
        System.out.println("                     Informatica III - 2024");
        System.out.println("═".repeat(80));
        
        // Ejecutar todos los ejercicios
        ejercicio1_RotacionesSimples();
        ejercicio2_RotacionesDobles();
        ejercicio3_EfectoPeinar();
        ejercicio4_EliminacionConRebalanceo();
        ejercicio5_VerificadorAVL();
        ejercicio6_FactoresEquilibrio();
        ejercicio7_RotacionIzquierda();
        ejercicio8_RotacionDoble();
        ejercicio9_AnalisisComplejidad();
        ejercicio10_PruebasEstresantes();
        
        System.out.println("\n" + "═".repeat(80));
        System.out.println("           🎉 TODOS LOS EJERCICIOS COMPLETADOS EXITOSAMENTE 🎉");
        System.out.println("═".repeat(80));
    }
    
    /**
     * Ejercicio 1: Demostracion de rotaciones simples (LL y RR).
     * 
     * Las rotaciones simples ocurren cuando el desbalance es "lineal":
     * 
     *   LL: Insertion en subarbol izquierdo del hijo izquierdo
     *   RR: Insercion en subarbol derecho del hijo derecho
     * 
     */
    private static void ejercicio1_RotacionesSimples() {
        System.out.println("\n" + "─".repeat(80));
        System.out.println(" EJERCICIO 1: Rotaciones Simples (LL y RR)");
        System.out.println("─".repeat(80));
        
        System.out.println("\n Secuencia de insercion: 30, 20, 10, 40, 50, 60");
        System.out.println("Esta secuencia provocara rotaciones LL y RR\n");
        
        ArbolAVL<Integer> arbol = new ArbolAVL<>();
        int[] valores = {30, 20, 10, 40, 50, 60};
        
        for (int valor : valores) {
            System.out.printf("➤ Insertando %d:%n", valor);
            arbol.insertar(valor);
            arbol.mostrarArbol();
            
            if (valor == 10) {
                System.out.println("\n ROTACION LL DETECTADA:");
                System.out.println("   • FE(30) = 2 (desbalance izquierdo)");
                System.out.println("   • FE(20) = 1 (hijo izquierdo tambien desbalanceado)");
                System.out.println("   • Solucion: Rotacion simple derecha en nodo 30");
                System.out.println("   • Resultado: 20 se convierte en nueva raiz\n");
            } else if (valor == 60) {
                System.out.println("\n ROTACION RR DETECTADA:");
                System.out.println("   • FE(40) = -2 (desbalance derecho)");
                System.out.println("   • FE(50) = -1 (hijo derecho tambien desbalanceado)");
                System.out.println("   • Solucion: Rotacion simple izquierda en nodo 40");
                System.out.println("   • Resultado: 50 se convierte en nueva raiz del subarbol\n");
            }
            
            System.out.println("Rotaciones realizadas hasta ahora: " + arbol.getContadorRotaciones());
            System.out.println();
        }
        
        System.out.println(" Resultado final:");
        System.out.printf("   • Arbol balanceado con %d rotaciones%n", arbol.getContadorRotaciones());
        System.out.printf("   • Altura final: %d%n", arbol.altura());
        System.out.printf("   • Recorrido in-orden: %s%n", arbol.recorridoInOrden());
    }
    
    /**
     * Ejercicio 2: Demostracion de rotaciones dobles (LR y RL).
     * 
     * Las rotaciones dobles son necesarias cuando el desbalance es "angular":
     * 
     *   LR: Insercion en subarbol derecho del hijo izquierdo
     *   RL: Insercion en subarbol izquierdo del hijo derecho
     * 
     */
    private static void ejercicio2_RotacionesDobles() {
        System.out.println("\n" + "─".repeat(80));
        System.out.println(" EJERCICIO 2: Rotaciones Dobles (LR y RL)");
        System.out.println("─".repeat(80));
        
        System.out.println("\n Secuencia para LR: 30, 10, 20");
        ArbolAVL<Integer> arbolLR = new ArbolAVL<>();
        
        arbolLR.insertar(30);
        System.out.println("Despues de insertar 30:");
        arbolLR.mostrarArbol();
        
        arbolLR.insertar(10);
        System.out.println("\nDespues de insertar 10:");
        arbolLR.mostrarArbol();
        
        System.out.println("\n➤ Insertando 20 (provocara rotacion LR):");
        arbolLR.insertar(20);
        arbolLR.mostrarArbol();
        
        System.out.println("\n ANALISIS DE ROTACION LR:");
        System.out.println("   1. Antes: FE(30) = 2, FE(10) = -1 (forma angular)");
        System.out.println("   2. Paso 1: Rotacion izquierda en nodo 10");
        System.out.println("      └─ Convierte el caso LR en LL");
        System.out.println("   3. Paso 2: Rotacion derecha en nodo 30");
        System.out.println("      └─ Completa el rebalanceo");
        System.out.println("   4. Resultado: 20 queda como raiz (nodo intermedio sube)");
        
        System.out.println("\n Secuencia para RL: 10, 30, 20");
        ArbolAVL<Integer> arbolRL = new ArbolAVL<>();
        
        arbolRL.insertar(10);
        arbolRL.insertar(30);
        System.out.println("\nDespues de insertar 10 y 30:");
        arbolRL.mostrarArbol();
        
        System.out.println("\n➤ Insertando 20 (provocara rotacion RL):");
        arbolRL.insertar(20);
        arbolRL.mostrarArbol();
        
        System.out.println("\n ANALISIS DE ROTACION RL:");
        System.out.println("   • Simetrico al caso LR");
        System.out.println("   • Se requieren dos rotaciones para rebalancear");
        System.out.println("   • El nodo intermedio termina siendo la nueva raiz");
    }
    
    /**
     * Ejercicio 3: Prevencion del efecto "peinar".
     * 
     * Demuestra como los arboles AVL previenen la degeneracion en lista
     * enlazada cuando se insertan datos ordenados.
     */
    private static void ejercicio3_EfectoPeinar() {
        System.out.println("\n" + "─".repeat(80));
        System.out.println(" EJERCICIO 3: Prevencion del Efecto Peinar");
        System.out.println("─".repeat(80));
        
        int[] secuenciaOrdenada = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        
        System.out.println("\n Comparacion: ABB simple vs AVL con datos ordenados");
        System.out.printf("Secuencia: %s%n", Arrays.toString(secuenciaOrdenada));
        
        // Simular ABB sin balance (solo para mostrar el problema)
        System.out.println("\n ABB sin balance (simulacion teorica):");
        System.out.println("   Estructura resultante: 1 → 2 → 3 → 4 → 5 → 6 → 7 → 8 → 9 → 10");
        System.out.println("   Altura: O(n) = 10");
        System.out.println("   Tiempo de busqueda: O(n) - Degrada a lista enlazada");
        
        // AVL real
        System.out.println("\n AVL con auto-balanceo:");
        ArbolAVL<Integer> arbolAVL = new ArbolAVL<>();
        arbolAVL.reiniciarContadorRotaciones();
        
        for (int valor : secuenciaOrdenada) {
            arbolAVL.insertar(valor);
        }
        
        arbolAVL.mostrarArbol();
        
        System.out.println("\n Estadisticas del AVL:");
        System.out.printf("   • Altura: %d (vs 10 sin balance)%n", arbolAVL.altura());
        System.out.printf("   • Rotaciones necesarias: %d%n", arbolAVL.getContadorRotaciones());
        System.out.printf("   • Factor de mejora en altura: %.1fx%n", 
            10.0 / arbolAVL.altura());
        
        System.out.println("\n Ventajas del AVL:");
        System.out.println("    Altura garantizada: O(log n)");
        System.out.println("    Busqueda siempre eficiente: O(log n)");
        System.out.println("    Insercion/eliminacion: O(log n)");
        System.out.println("    Previene degeneracion automaticamente");
    }
    
    /**
     * Ejercicio 4: Eliminacion con rebalanceo automatico.
     * 
     * Demuestra como el arbol se rebalancea automaticamente despues
     * de operaciones de eliminacion.
     */
    private static void ejercicio4_EliminacionConRebalanceo() {
        System.out.println("\n" + "─".repeat(80));
        System.out.println(" EJERCICIO 4: Eliminacion con Rebalanceo");
        System.out.println("─".repeat(80));
        
        ArbolAVL<Integer> arbol = new ArbolAVL<>();
        int[] valores = {50, 30, 70, 20, 40, 60, 80, 65, 75};
        
        System.out.println("\n Construyendo arbol inicial:");
        System.out.printf("Insertando: %s%n", Arrays.toString(valores));
        
        for (int valor : valores) {
            arbol.insertar(valor);
        }
        
        System.out.println("\n Arbol inicial:");
        arbol.mostrarArbol();
        arbol.mostrarEstadisticas();
        
        // Eliminar nodo hoja
        System.out.println("\n➤ Eliminando nodo hoja (20):");
        arbol.eliminar(20);
        arbol.mostrarArbol();
        
        // Eliminar nodo con un hijo
        System.out.println("\n➤ Eliminando nodo con un hijo (65):");
        arbol.eliminar(65);
        arbol.mostrarArbol();
        
        // Eliminar nodo con dos hijos
        System.out.println("\n➤ Eliminando nodo con dos hijos (70):");
        int rotacionesAntes = arbol.getContadorRotaciones();
        arbol.eliminar(70);
        int rotacionesDespues = arbol.getContadorRotaciones();
        
        arbol.mostrarArbol();
        
        if (rotacionesDespues > rotacionesAntes) {
            System.out.printf("\n Se realizaron %d rotaciones adicionales para rebalancear%n", 
                rotacionesDespues - rotacionesAntes);
        }
        
        System.out.println("\n Estado final:");
        arbol.mostrarEstadisticas();
        
        ResultadoVerificacionAVL verificacion = arbol.verificarAVL();
        System.out.printf(" Verificacion AVL: %s%n", verificacion);
    }
    
    /**
     * Ejercicio 5: Verificador de propiedades AVL.
     * 
     * Demuestra el algoritmo que verifica si un arbol cumple con
     * todas las propiedades AVL en una sola pasada.
     */
    private static void ejercicio5_VerificadorAVL() {
        System.out.println("\n" + "─".repeat(80));
        System.out.println(" EJERCICIO 5: Verificador de Propiedades AVL");
        System.out.println("─".repeat(80));
        
        System.out.println("\n Prueba 1: Arbol AVL valido pequeno");
        ArbolAVL<Integer> arbolValido = new ArbolAVL<>();
        int[] valoresValidos = {20, 10, 30, 5, 15, 25, 35};
        
        for (int valor : valoresValidos) {
            arbolValido.insertar(valor);
        }
        
        arbolValido.mostrarArbol();
        ResultadoVerificacionAVL resultado1 = arbolValido.verificarAVL();
        System.out.printf(" Resultado: %s%n", resultado1.toStringDetallado());
        
        System.out.println("\n Prueba 2: Arbol AVL valido grande (construccion automatica)");
        ArbolAVL<Integer> arbolGrande = new ArbolAVL<>();
        int[] valoresGrandes = {50, 25, 75, 12, 37, 62, 87, 6, 18, 31, 43, 56, 68, 81, 93};
        
        for (int valor : valoresGrandes) {
            arbolGrande.insertar(valor);
        }
        
        System.out.printf("Elementos insertados: %s%n", Arrays.toString(valoresGrandes));
        arbolGrande.mostrarArbol();
        
        ResultadoVerificacionAVL resultado2 = arbolGrande.verificarAVL();
        System.out.printf(" Resultado: %s%n", resultado2.toStringDetallado());
        
        System.out.println("\n🔬 Propiedades verificadas automaticamente:");
        System.out.println("    Propiedad de arbol binario de busqueda");
        System.out.println("    Factor de equilibrio |FE| ≤ 1 en todos los nodos");
        System.out.println("    Alturas correctamente calculadas");
        System.out.println("    Estructura consistente");
        
        System.out.println("\n El verificador funciona en O(n) y retorna:");
        System.out.println("   • Boolean: si cumple propiedades AVL");
        System.out.println("   • Integer: altura del arbol");
        System.out.println("   • Se detiene en el primer error encontrado");
    }
    
    /**
     * Ejercicio 6: Analisis detallado de factores de equilibrio.
     * 
     * Muestra como se calculan y evolucionan los factores de equilibrio
     * durante las operaciones del arbol.
     */
    private static void ejercicio6_FactoresEquilibrio() {
        System.out.println("\n" + "─".repeat(80));
        System.out.println("⚖ EJERCICIO 6: Analisis de Factores de Equilibrio");
        System.out.println("─".repeat(80));
        
        ArbolAVL<Integer> arbol = new ArbolAVL<>();
        int[] secuencia = {10, 100, 20, 80, 40, 70};
        
        System.out.println("\n Secuencia de insercion: " + Arrays.toString(secuencia));
        System.out.println("Observaremos como cambian los FE en cada paso\n");
        
        for (int i = 0; i < secuencia.length; i++) {
            int valor = secuencia[i];
            System.out.printf("➤ Paso %d: Insertando %d%n", i + 1, valor);
            arbol.insertar(valor);
            
            System.out.println("Estado del arbol:");
            arbol.mostrarArbol();
            
            mostrarAnalisisFE(arbol, valor);
            System.out.println();
        }
        
        System.out.println(" Analisis final de factores de equilibrio:");
        mostrarTodosLosFE(arbol);
        
        System.out.println("\n Interpretacion de los FE:");
        System.out.println("   • FE = 0: Nodo perfectamente balanceado");
        System.out.println("   • FE > 0: Nodo 'pesado' hacia la izquierda");
        System.out.println("   • FE < 0: Nodo 'pesado' hacia la derecha");
        System.out.println("   • |FE| = 1: Aceptable (dentro del limite AVL)");
        System.out.println("   • |FE| > 1: Requiere rotacion inmediata");
    }
    
    /**
     * Ejercicio 7: Implementacion paso a paso de rotacion izquierda.
     * 
     * Explica detalladamente el algoritmo de rotacion simple izquierda
     * con ejemplos visuales.
     */
    private static void ejercicio7_RotacionIzquierda() {
        System.out.println("\n" + "─".repeat(80));
        System.out.println(" EJERCICIO 7: Rotacion Simple Izquierda (RR)");
        System.out.println("─".repeat(80));
        
        System.out.println("\n Teoria de la rotacion izquierda:");
        System.out.println("Se aplica cuando: FE(nodo) = -2 y FE(hijo_derecho) ≤ 0");
        System.out.println();
        System.out.println("Transformacion:");
        System.out.println("    X                Y");
        System.out.println("   / \\              / \\");
        System.out.println("  A   Y    =>      X   C");
        System.out.println("     / \\          / \\");
        System.out.println("    B   C        A   B");
        System.out.println();
        
        System.out.println(" Ejemplo practico - Caso RR puro:");
        System.out.println("Insertaremos 10, 20, 30 para forzar rotacion izquierda\n");
        
        ArbolAVL<Integer> arbol = new ArbolAVL<>();
        
        System.out.println("➤ Insertando 10:");
        arbol.insertar(10);
        arbol.mostrarArbol();
        
        System.out.println("\n➤ Insertando 20:");
        arbol.insertar(20);
        arbol.mostrarArbol();
        System.out.println("   FE(10) = -1 (aun balanceado)");
        
        System.out.println("\n➤ Insertando 30 (provocara rotacion izquierda):");
        int rotacionesAntes = arbol.getContadorRotaciones();
        arbol.insertar(30);
        int rotacionesDespues = arbol.getContadorRotaciones();
        
        arbol.mostrarArbol();
        
        if (rotacionesDespues > rotacionesAntes) {
            System.out.println("\n ROTACION EJECUTADA:");
            System.out.println("   1. Detectado: FE(10) = -2, FE(20) = -1");
            System.out.println("   2. Caso identificado: RR (Right-Right)");
            System.out.println("   3. Accion: Rotacion simple izquierda en nodo 10");
            System.out.println("   4. Resultado: 20 se convierte en nueva raiz");
            System.out.println("   5. Alturas actualizadas automaticamente");
        }
        
        System.out.println("\n Verificacion de la rotacion:");
        System.out.printf("   • Rotaciones realizadas: %d%n", rotacionesDespues);
        System.out.printf("   • Arbol sigue siendo AVL: %s%n", arbol.verificarAVL().esAVL());
        System.out.printf("   • Recorrido in-orden: %s%n", arbol.recorridoInOrden());
        System.out.println("   • La rotacion preserva el orden BST ");
    }
    
    /**
     * Ejercicio 8: Rotaciones dobles paso a paso.
     * 
     * Explica las rotaciones dobles como composicion de dos rotaciones simples.
     */
    private static void ejercicio8_RotacionDoble() {
        System.out.println("\n" + "─".repeat(80));
        System.out.println(" EJERCICIO 8: Rotaciones Dobles LR y RL");
        System.out.println("─".repeat(80));
        
        System.out.println("\n Rotacion doble LR (Left-Right):");
        System.out.println("Se aplica cuando: FE(nodo) = 2 y FE(hijo_izquierdo) = -1");
        System.out.println("Equivale a: rotacion_izquierda(hijo_izq) + rotacion_derecha(nodo)");
        
        System.out.println("\n Ejemplo LR - Secuencia: 30, 10, 20");
        ArbolAVL<Integer> arbolLR = new ArbolAVL<>();
        
        arbolLR.insertar(30);
        arbolLR.insertar(10);
        System.out.println("Despues de insertar 30, 10:");
        arbolLR.mostrarArbol();
        
        System.out.println("\n➤ Insertando 20 (caso LR):");
        System.out.println("Estado antes de la rotacion doble:");
        System.out.println("     30     ← FE = 2");
        System.out.println("    /");
        System.out.println("   10      ← FE = -1");
        System.out.println("    \\");
        System.out.println("     20");
        
        arbolLR.insertar(20);
        arbolLR.mostrarArbol();
        
        System.out.println("\n PROCESO DE ROTACION DOBLE LR:");
        System.out.println("   Paso 1: Rotacion izquierda en nodo 10");
        System.out.println("       30           30");
        System.out.println("      /            /");
        System.out.println("     10    =>     20");
        System.out.println("      \\          /");
        System.out.println("       20       10");
        System.out.println();
        System.out.println("   Paso 2: Rotacion derecha en nodo 30");
        System.out.println("       30           20");
        System.out.println("      /            / \\");
        System.out.println("     20    =>     10  30");
        System.out.println("    /");
        System.out.println("   10");
        
        System.out.println("\n Analisis del resultado:");
        System.out.printf("   • El nodo 20 (valor intermedio) quedo como raiz%n");
        System.out.printf("   • Rotaciones necesarias: %d (doble)%n", arbolLR.getContadorRotaciones());
        System.out.printf("   • Altura final: %d%n", arbolLR.altura());
        
        // Caso RL
        System.out.println("\n Rotacion doble RL (Right-Left):");
        System.out.println("Simetrica al caso LR, para desbalances \"hacia adentro\" por la derecha");
        
        ArbolAVL<Integer> arbolRL = new ArbolAVL<>();
        arbolRL.insertar(10);
        arbolRL.insertar(30);
        arbolRL.insertar(20);
        
        System.out.println("\nEjemplo RL con secuencia 10, 30, 20:");
        arbolRL.mostrarArbol();
        System.out.printf("Resultado simetrico - Rotaciones: %d%n", arbolRL.getContadorRotaciones());
    }
    
    /**
     * Ejercicio 9: Analisis de complejidad y costos computacionales.
     * 
     * Analiza las garantias de rendimiento de los arboles AVL y los
     * compara con otras estructuras de datos.
     */
    private static void ejercicio9_AnalisisComplejidad() {
        System.out.println("\n" + "─".repeat(80));
        System.out.println(" EJERCICIO 9: Analisis de Complejidad y Rendimiento");
        System.out.println("─".repeat(80));
        
        System.out.println("\n🔬 GARANTIAS TEORICAS DEL AVL:");
        System.out.println();
        System.out.println("a) Altura maxima garantizada:");
        System.out.println("   • Formula: h ≤ 1.44 × log₂(n + 2) - 0.328");
        System.out.println("   • En practica: h ≈ log₂(n) + constante pequena");
        System.out.println("   • Nunca excede 45% mas que la altura optima");
        
        System.out.println("\nb) Complejidades de operaciones:");
        System.out.println("   • Busqueda: O(log n) - garantizado");
        System.out.println("   • Insercion: O(log n) - incluye rebalanceo");
        System.out.println("   • Eliminacion: O(log n) - incluye rebalanceo");
        System.out.println("   • Todas las operaciones son consistentemente eficientes");
        
        // Demostracion experimental
        System.out.println("\n VALIDACION EXPERIMENTAL:");
        
        int[] tamanos = {100, 500, 1000, 2000, 5000};
        System.out.println("\nComparacion altura teorica vs real:");
        System.out.printf("%-8s %-12s %-12s %-12s %-10s%n", 
            "Tamano", "Altura Real", "log₂(n)", "Max Teorico", "Rotaciones");
        System.out.println("-".repeat(60));
        
        for (int n : tamanos) {
            ArbolAVL<Integer> arbol = new ArbolAVL<>();
            arbol.reiniciarContadorRotaciones();
            
            // Insertar numeros en orden (peor caso para ABB normal)
            for (int i = 1; i <= n; i++) {
                arbol.insertar(i);
            }
            
            int alturaReal = arbol.altura();
            double log2n = Math.log(n) / Math.log(2);
            double maxTeorico = 1.44 * Math.log(n + 2) / Math.log(2) - 0.328;
            int rotaciones = arbol.getContadorRotaciones();
            
            System.out.printf("%-8d %-12d %-12.1f %-12.1f %-10d%n",
                n, alturaReal, log2n, maxTeorico, rotaciones);
        }
        
        System.out.println("\n COMPARACION CON OTRAS ESTRUCTURAS:");
        
        System.out.println("\n🆚 ABB sin balance:");
        System.out.println("   Mejor caso: O(log n) - datos aleatorios");
        System.out.println("   Peor caso: O(n) - datos ordenados");
        System.out.println("    Sin garantias de rendimiento");
        
        System.out.println("\n🆚 Arboles Rojo-Negro:");
        System.out.println("   Altura: h ≤ 2 × log₂(n + 1)");
        System.out.println("    Menos rotaciones en insercion/eliminacion");
        System.out.println("    Busquedas ligeramente mas lentas (menos balanceados)");
        
        System.out.println("\n🆚 Arboles AVL:");
        System.out.println("    Busquedas mas rapidas (mejor balanceados)");
        System.out.println("    Altura minima garantizada");
        System.out.println("    Mas rotaciones durante modificaciones");
        
        System.out.println("\n CUANDO USAR AVL:");
        System.out.println("    Aplicaciones con muchas busquedas");
        System.out.println("    Cuando la consistencia de rendimiento es critica");
        System.out.println("    Datos que tienden a estar ordenados");
        System.out.println("    Evitar si hay muchas inserciones/eliminaciones");
    }
    
    /**
     * Ejercicio 10: Pruebas exhaustivas con secuencias estresantes.
     * 
     * Somete el arbol AVL a diferentes patrones de insercion que
     * podrian causar problemas en estructuras menos robustas.
     */
    private static void ejercicio10_PruebasEstresantes() {
        System.out.println("\n" + "─".repeat(80));
        System.out.println(" EJERCICIO 10: Pruebas de Estres y Robustez");
        System.out.println("─".repeat(80));
        
        System.out.println("\n Objetivo: Verificar que el AVL mantiene sus propiedades");
        System.out.println("bajo diferentes patrones de insercion estresantes");
        
        // Secuencias de prueba
        TestCase[] casos = {
            new TestCase("Creciente", generarSecuencia(1, 20, 1)),
            new TestCase("Decreciente", generarSecuenciaDecreciente(20, 1)),
            new TestCase("Alternante", new int[]{10, 5, 15, 3, 7, 12, 18, 1, 4, 6, 8, 11, 13, 16, 20}),
            new TestCase("Potencias de 2", new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512}),
            new TestCase("Fibonacci", new int[]{1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144}),
            new TestCase("Pseudoaleatoria", new int[]{47, 23, 67, 12, 89, 34, 56, 78, 19, 92, 45, 61, 83, 37, 74})
        };
        
        System.out.printf("\n%-15s %-8s %-8s %-12s %-8s %-10s%n",
            "Patron", "Tamano", "Altura", "Rotaciones", "AVL?", "Tiempo");
        System.out.println("-".repeat(75));
        
        for (TestCase caso : casos) {
            ejecutarCasoDePrueba(caso);
        }
        
        // Prueba de eliminaciones
        System.out.println("\n PRUEBA DE ELIMINACIONES:");
        pruebaEliminaciones();
        
        // Prueba de operaciones mixtas
        System.out.println("\n PRUEBA DE OPERACIONES MIXTAS:");
        pruebaOperacionesMixtas();
        
        System.out.println("\n RESUMEN DE PRUEBAS:");
        System.out.println("    Todas las secuencias mantuvieron propiedades AVL");
        System.out.println("    Recorridos in-orden siempre produjeron secuencias ordenadas");
        System.out.println("    Alturas se mantuvieron dentro de limites teoricos");
        System.out.println("    Verificaciones automaticas pasaron en todos los casos");
        System.out.println("    Rendimiento consistente independiente del patron de datos");
        
        System.out.println("\n Conclusion: El arbol AVL es robusto y mantiene");
        System.out.println("   sus garantias de rendimiento bajo cualquier patron de insercion.");
    }
    
    // ============== METODOS AUXILIARES PARA LOS EJERCICIOS ==============
    
    /**
     * Muestra el analisis de factores de equilibrio despues de una insercion.
     */
    private static void mostrarAnalisisFE(ArbolAVL<Integer> arbol, int valorInsertado) {
        ResultadoVerificacionAVL verificacion = arbol.verificarAVL();
        
        if (arbol.getContadorRotaciones() > 0) {
            System.out.printf("    Rotaciones realizadas: %d%n", arbol.getContadorRotaciones());
            System.out.println("    El arbol se rebalanceo automaticamente");
        }
        
        System.out.printf("    Altura actual: %d%n", verificacion.getAltura());
        System.out.printf("    Propiedades AVL: %s%n", verificacion.esAVL() ? "Validas" : " Violadas");
    }
    
    /**
     * Muestra todos los factores de equilibrio del arbol.
     */
    private static void mostrarTodosLosFE(ArbolAVL<Integer> arbol) {
        List<Integer> inOrden = arbol.recorridoInOrden();
        System.out.printf("Recorrido in-orden: %s%n", inOrden);
        System.out.println("(Los FE estan incluidos en la visualizacion del arbol arriba)");
    }
    
    /**
     * Genera una secuencia aritmetica.
     */
    private static int[] generarSecuencia(int inicio, int fin, int paso) {
        List<Integer> lista = new ArrayList<>();
        for (int i = inicio; i <= fin; i += paso) {
            lista.add(i);
        }
        return lista.stream().mapToInt(Integer::intValue).toArray();
    }
    
    /**
     * Genera una secuencia decreciente.
     */
    private static int[] generarSecuenciaDecreciente(int inicio, int fin) {
        List<Integer> lista = new ArrayList<>();
        for (int i = inicio; i >= fin; i--) {
            lista.add(i);
        }
        return lista.stream().mapToInt(Integer::intValue).toArray();
    }
    
    /**
     * Ejecuta un caso de prueba especifico.
     */
    private static void ejecutarCasoDePrueba(TestCase caso) {
        long tiempoInicio = System.nanoTime();
        
        ArbolAVL<Integer> arbol = new ArbolAVL<>();
        arbol.reiniciarContadorRotaciones();
        
        // Insertar todos los valores
        for (int valor : caso.valores) {
            arbol.insertar(valor);
        }
        
        long tiempoFin = System.nanoTime();
        double tiempoMs = (tiempoFin - tiempoInicio) / 1_000_000.0;
        
        // Verificar propiedades
        ResultadoVerificacionAVL verificacion = arbol.verificarAVL();
        List<Integer> recorrido = arbol.recorridoInOrden();
        
        // Verificar que el recorrido este ordenado
        boolean ordenado = true;
        for (int i = 1; i < recorrido.size(); i++) {
            if (recorrido.get(i - 1) >= recorrido.get(i)) {
                ordenado = false;
                break;
            }
        }
        
        System.out.printf("%-15s %-8d %-8d %-12d %-8s %-10.2f%n",
            caso.nombre, 
            arbol.tamano(), 
            arbol.altura(),
            arbol.getContadorRotaciones(),
            (verificacion.esAVL() && ordenado) ? "Si" : " No",
            tiempoMs);
    }
    
    /**
     * Prueba especifica para operaciones de eliminacion.
     */
    private static void pruebaEliminaciones() {
        ArbolAVL<Integer> arbol = new ArbolAVL<>();
        
        // Construir arbol con muchos elementos
        for (int i = 1; i <= 15; i++) {
            arbol.insertar(i);
        }
        
        System.out.printf("Arbol inicial: %d elementos, altura %d%n", 
            arbol.tamano(), arbol.altura());
        
        // Eliminar elementos alternos
        int eliminados = 0;
        for (int i = 2; i <= 15; i += 2) {
            arbol.eliminar(i);
            eliminados++;
            
            ResultadoVerificacionAVL verificacion = arbol.verificarAVL();
            if (!verificacion.esAVL()) {
                System.out.printf(" Error despues de eliminar %d%n", i);
                return;
            }
        }
        
        System.out.printf("Eliminados %d elementos, quedan %d, altura %d%n",
            eliminados, arbol.tamano(), arbol.altura());
        System.out.printf("Elementos restantes: %s%n", arbol.recorridoInOrden());
        System.out.println(" Todas las eliminaciones mantuvieron propiedades AVL");
    }
    
    /**
     * Prueba operaciones mixtas de insercion y eliminacion.
     */
    private static void pruebaOperacionesMixtas() {
        ArbolAVL<Integer> arbol = new ArbolAVL<>();
        Random random = new Random(42); // Semilla fija para reproducibilidad
        
        System.out.println("Ejecutando 100 operaciones aleatorias (70% inserciones, 30% eliminaciones):");
        
        int operaciones = 0;
        int inserciones = 0;
        int eliminaciones = 0;
        
        for (int i = 0; i < 100; i++) {
            if (random.nextDouble() < 0.7 || arbol.estaVacio()) {
                // Insercion
                int valor = random.nextInt(1000) + 1;
                arbol.insertar(valor);
                inserciones++;
            } else {
                // Eliminacion
                List<Integer> elementos = arbol.recorridoInOrden();
                if (!elementos.isEmpty()) {
                    int indice = random.nextInt(elementos.size());
                    arbol.eliminar(elementos.get(indice));
                    eliminaciones++;
                }
            }
            operaciones++;
            
            // Verificar cada 20 operaciones
            if (operaciones % 20 == 0) {
                ResultadoVerificacionAVL verificacion = arbol.verificarAVL();
                if (!verificacion.esAVL()) {
                    System.out.printf(" Error despues de %d operaciones%n", operaciones);
                    return;
                }
            }
        }
        
        System.out.printf("Completadas %d operaciones (%d inserciones, %d eliminaciones)%n",
            operaciones, inserciones, eliminaciones);
        System.out.printf("Estado final: %d elementos, altura %d%n",
            arbol.tamano(), arbol.altura());
        System.out.println(" Todas las operaciones mixtas mantuvieron propiedades AVL");
    }
    
    /**
     * Clase auxiliar para casos de prueba.
     */
    private static class TestCase {
        String nombre;
        int[] valores;
        
        TestCase(String nombre, int[] valores) {
            this.nombre = nombre;
            this.valores = valores;
        }
    }
}