package edu.informatica3.lucas_antun.practico06;

import java.util.*;

/**
 * Clase de demostracion para el Arbol Rojo-Negro.
 * 
 * Esta clase proporciona ejercicios practicos que demuestran todas las
 * funcionalidades del arbol Rojo-Negro, incluyendo:
 * 
 *   Insercion secuencial y aleatoria
 *   Verificacion de propiedades del arbol
 *   Analisis de rendimiento y rotaciones
 *   Consultas por rango
 *   Navegacion entre nodos (predecesor/sucesor)
 * 
 * 
 * @author Lucas Santiago Said Antun & Federico Fernandez
 * @version 1.0
 * @since 2025-01-01
 */
public class DemostracionRojoNegro {
    
    /**
     * Punto de entrada principal para ejecutar todas las demostraciones.
     */
    public static void main(String[] args) {
        System.out.println("=".repeat(80));
        System.out.println("       PRACTICO 6: DEMOSTRACIONES DE ARBOL ROJO-NEGRO");
        System.out.println("                  Lucas Santiago Said Antun");
        System.out.println("=".repeat(80));
        
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            mostrarMenu();
            System.out.print("Selecciona una opcion (0-10): ");
            
            try {
                int opcion = scanner.nextInt();
                
                switch (opcion) {
                    case 0 -> {
                        System.out.println("\nGracias por usar la demostracion!");
                        scanner.close();
                        return;
                    }
                    case 1 -> ejercicio1InsercionBasica();
                    case 2 -> ejercicio2VerificacionPropiedades();
                    case 3 -> ejercicio3InsercionSecuencial();
                    case 4 -> ejercicio4InsercionAleatoria();
                    case 5 -> ejercicio5ConsultaPorRango();
                    case 6 -> ejercicio6NavigacionNodos();
                    case 7 -> ejercicio7ComparacionRendimiento();
                    case 8 -> ejercicio8EscenarioComplejo();
                    case 9 -> ejercicio9VisualizacionArbol();
                    case 10 -> ejercicio10EstresTest();
                    default -> System.out.println(" Opcion invalida. Intenta nuevamente.");
                }
                
                if (opcion >= 1 && opcion <= 10) {
                    System.out.println("\nPresiona Enter para continuar...");
                    scanner.nextLine(); // Consumir el salto de linea
                    scanner.nextLine(); // Esperar al usuario
                }
                
            } catch (InputMismatchException e) {
                System.out.println(" Por favor ingresa un numero valido.");
                scanner.nextLine(); // Limpiar buffer
            } catch (Exception e) {
                System.out.printf(" Error inesperado: %s%n", e.getMessage());
                scanner.nextLine(); // Limpiar buffer
            }
        }
    }
    
    /**
     * Muestra el menu principal de opciones.
     */
    private static void mostrarMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                    MENU DE DEMOSTRACIONES");
        System.out.println("=".repeat(60));
        System.out.println(" 1.  Insercion basica y busqueda");
        System.out.println(" 2.  Verificacion de propiedades RB");
        System.out.println(" 3.  Insercion secuencial (1-20)");
        System.out.println(" 4.  Insercion aleatoria y analisis");
        System.out.println(" 5.  Consulta por rango");
        System.out.println(" 6.  Navegacion (predecesor/sucesor)");
        System.out.println(" 7.  Comparacion de rendimiento");
        System.out.println(" 8.  Escenario complejo");
        System.out.println(" 9.  Visualizacion del arbol");
        System.out.println("10.  Test de estres (1000 elementos)");
        System.out.println(" 0.  Salir");
        System.out.println("=".repeat(60));
    }
    
    /**
     * Ejercicio 1: Demostracion basica de insercion y busqueda.
     */
    private static void ejercicio1InsercionBasica() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("            EJERCICIO 1: INSERCION BASICA");
        System.out.println("=".repeat(60));
        
        ArbolRojoNegro<Integer, String> arbol = new ArbolRojoNegro<>();
        
        System.out.println(" Insertando elementos basicos: 10, 5, 15, 3, 7, 12, 18");
        
        arbol.insertar(10, "Diez");
        arbol.insertar(5, "Cinco");
        arbol.insertar(15, "Quince");
        arbol.insertar(3, "Tres");
        arbol.insertar(7, "Siete");
        arbol.insertar(12, "Doce");
        arbol.insertar(18, "Dieciocho");
        
        System.out.printf(" Se insertaron %d elementos%n", arbol.tamano());
        System.out.printf(" Rotaciones realizadas: %d%n", arbol.getContadorRotaciones());
        
        System.out.println("\n Pruebas de busqueda:");
        int[] clavesTest = {10, 5, 15, 2, 20, 7};
        for (int clave : clavesTest) {
            boolean encontrado = arbol.contiene(clave);
            String valor = arbol.obtenerValor(clave);
            System.out.printf("   Clave %d: %s %s%n", 
                clave, 
                encontrado ? " Encontrada" : " No encontrada",
                valor != null ? ("-> " + valor) : "");
        }
        
        System.out.println("\n Recorrido in-orden:");
        System.out.println("   " + arbol.recorridoInOrden());
        
        arbol.mostrarEstadisticas();
    }
    
    /**
     * Ejercicio 2: Verificacion exhaustiva de las propiedades del arbol Rojo-Negro.
     */
    private static void ejercicio2VerificacionPropiedades() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("         EJERCICIO 2: VERIFICACION DE PROPIEDADES");
        System.out.println("=".repeat(60));
        
        ArbolRojoNegro<Integer, String> arbol = new ArbolRojoNegro<>();
        
        System.out.println(" Insertando secuencia que puede causar rotaciones: 1, 2, 3, 4, 5, 6, 7");
        
        for (int i = 1; i <= 7; i++) {
            arbol.insertar(i, "Valor" + i);
            System.out.printf("   Despues de insertar %d: rotaciones = %d%n", 
                i, arbol.getContadorRotaciones());
        }
        
        System.out.println("\n Verificando propiedades del arbol Rojo-Negro:");
        
        // Propiedad 1: Todo nodo es rojo o negro (implicito por la implementacion)
        System.out.println("    Propiedad 1: Todo nodo es rojo o negro (garantizado por implementacion)");
        
        // Propiedad 2: La raiz es negra
        boolean raizNegra = arbol.raizEsNegra();
        System.out.printf("   %s Propiedad 2: La raiz es negra%n", 
            raizNegra ? "" : "");
        
        // Propiedad 4: No hay nodos rojos consecutivos
        boolean sinRojosConsecutivos = arbol.sinNodosRojosConsecutivos();
        System.out.printf("   %s Propiedad 4: Sin nodos rojos consecutivos%n", 
            sinRojosConsecutivos ? "" : "");
        
        // Propiedad 5: Misma altura negra en todos los caminos
        int alturaNegra = arbol.alturaNegra();
        if (alturaNegra != -1) {
            System.out.printf("    Propiedad 5: Altura negra consistente = %d%n", alturaNegra);
        } else {
            System.out.println("    Propiedad 5: Altura negra inconsistente");
        }
        
        boolean esValido = raizNegra && sinRojosConsecutivos && (alturaNegra != -1);
        System.out.printf("%n RESULTADO: El arbol %s un arbol Rojo-Negro valido%n", 
            esValido ? "ES" : "NO ES");
        
        arbol.mostrarEstadisticas();
    }
    
    /**
     * Ejercicio 3: Insercion secuencial y analisis de rotaciones.
     */
    private static void ejercicio3InsercionSecuencial() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("          EJERCICIO 3: INSERCION SECUENCIAL");
        System.out.println("=".repeat(60));
        
        ArbolRojoNegro<Integer, String> arbol = new ArbolRojoNegro<>();
        
        System.out.println(" Insertando numeros del 1 al 20 secuencialmente:");
        
        List<Integer> rotacionesPorInsercion = new ArrayList<>();
        
        for (int i = 1; i <= 20; i++) {
            int rotacionesAntes = arbol.getContadorRotaciones();
            arbol.insertar(i, "Num" + i);
            int rotacionesDespues = arbol.getContadorRotaciones();
            
            int nuevasRotaciones = rotacionesDespues - rotacionesAntes;
            rotacionesPorInsercion.add(nuevasRotaciones);
            
            if (nuevasRotaciones > 0) {
                System.out.printf("   %d: +%d rotaciones (total: %d)%n", 
                    i, nuevasRotaciones, rotacionesDespues);
            }
        }
        
        System.out.printf("%n Analisis de rotaciones:%n");
        System.out.printf("   Total de rotaciones: %d%n", arbol.getContadorRotaciones());
        System.out.printf("   Promedio por insercion: %.2f%n", 
            arbol.getContadorRotaciones() / 20.0);
        
        long insercionesConRotacion = rotacionesPorInsercion.stream()
            .mapToLong(Integer::longValue)
            .filter(r -> r > 0)
            .count();
        
        System.out.printf("   Inserciones que causaron rotaciones: %d/20 (%.1f%%)%n", 
            insercionesConRotacion, (insercionesConRotacion / 20.0) * 100);
        
        System.out.println("\n Recorrido final (primeros 10):");
        List<Integer> elementos = arbol.recorridoInOrden();
        System.out.println("   " + elementos.subList(0, Math.min(10, elementos.size())));
        
        arbol.mostrarEstadisticas();
    }
    
    /**
     * Ejercicio 4: Insercion aleatoria y comparacion con insercion secuencial.
     */
    private static void ejercicio4InsercionAleatoria() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("          EJERCICIO 4: INSERCION ALEATORIA");
        System.out.println("=".repeat(60));
        
        // Crear dos arboles para comparar
        ArbolRojoNegro<Integer, String> arbolAleatorio = new ArbolRojoNegro<>();
        ArbolRojoNegro<Integer, String> arbolSecuencial = new ArbolRojoNegro<>();
        
        // Generar numeros aleatorios unicos
        List<Integer> numerosAleatorios = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            numerosAleatorios.add(i);
        }
        Collections.shuffle(numerosAleatorios, new Random(42)); // Semilla fija para reproducibilidad
        
        System.out.println(" Insertando numeros en orden aleatorio:");
        System.out.println("   Secuencia: " + numerosAleatorios);
        
        // Insercion aleatoria
        for (int num : numerosAleatorios) {
            arbolAleatorio.insertar(num, "Val" + num);
        }
        
        // Insercion secuencial (1 a 15)
        for (int i = 1; i <= 15; i++) {
            arbolSecuencial.insertar(i, "Val" + i);
        }
        
        System.out.println("\n Comparacion de rendimiento:");
        System.out.printf("%-20s | %-15s | %-15s%n", "Metrica", "Aleatorio", "Secuencial");
        System.out.println("-".repeat(55));
        System.out.printf("%-20s | %-15d | %-15d%n", 
            "Rotaciones", arbolAleatorio.getContadorRotaciones(), arbolSecuencial.getContadorRotaciones());
        System.out.printf("%-20s | %-15d | %-15d%n", 
            "Altura negra", arbolAleatorio.alturaNegra(), arbolSecuencial.alturaNegra());
        
        // Verificar que ambos tienen los mismos elementos
        List<Integer> elementosAleatorio = arbolAleatorio.recorridoInOrden();
        List<Integer> elementosSecuencial = arbolSecuencial.recorridoInOrden();
        
        boolean mismoContenido = elementosAleatorio.equals(elementosSecuencial);
        System.out.printf("%-20s | %-31s%n", 
            "Mismo contenido", mismoContenido ? " SI" : " NO");
        
        System.out.println("\n Observacion: El orden de insercion afecta el numero de rotaciones,");
        System.out.println("   pero ambos arboles mantienen las propiedades Rojo-Negro.");
        
        arbolAleatorio.mostrarEstadisticas();
    }
    
    /**
     * Ejercicio 5: Demostracion de consultas por rango.
     */
    private static void ejercicio5ConsultaPorRango() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("            EJERCICIO 5: CONSULTA POR RANGO");
        System.out.println("=".repeat(60));
        
        ArbolRojoNegro<Integer, String> arbol = new ArbolRojoNegro<>();
        
        // Insertar numeros no consecutivos para hacer mas interesante el ejemplo
        int[] numeros = {50, 25, 75, 10, 35, 60, 90, 5, 15, 30, 40, 55, 65, 80, 95};
        
        System.out.println(" Insertando numeros no consecutivos:");
        System.out.println("   " + Arrays.toString(numeros));
        
        for (int num : numeros) {
            arbol.insertar(num, "Valor" + num);
        }
        
        System.out.println("\n Todos los elementos (in-orden):");
        System.out.println("   " + arbol.recorridoInOrden());
        
        // Realizar varias consultas por rango
        int[][] rangos = {
            {20, 40},    // Rango pequeno
            {45, 70},    // Rango medio
            {1, 100},    // Rango completo
            {25, 25},    // Rango de un solo elemento
            {100, 200}   // Rango fuera del arbol
        };
        
        System.out.println("\n Consultas por rango:");
        for (int[] rango : rangos) {
            List<Integer> resultado = arbol.consultaRango(rango[0], rango[1]);
            System.out.printf("   Rango [%d, %d]: %s%n", 
                rango[0], rango[1], 
                resultado.isEmpty() ? "🚫 Vacio" : resultado.toString());
        }
        
        System.out.println("\n Nota: Las consultas por rango son eficientes en arboles Rojo-Negro");
        System.out.println("   debido a su estructura balanceada (O(log n + k) donde k = elementos en rango)");
        
        arbol.mostrarEstadisticas();
    }
    
    /**
     * Ejercicio 6: Demostracion de navegacion entre nodos (predecesor/sucesor).
     */
    private static void ejercicio6NavigacionNodos() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("           EJERCICIO 6: NAVEGACION ENTRE NODOS");
        System.out.println("=".repeat(60));
        
        ArbolRojoNegro<Integer, String> arbol = new ArbolRojoNegro<>();
        
        // Insertar algunos numeros
        int[] numeros = {20, 10, 30, 5, 15, 25, 35};
        
        System.out.println(" Creando arbol con elementos:");
        System.out.println("   " + Arrays.toString(numeros));
        
        for (int num : numeros) {
            arbol.insertar(num, "V" + num);
        }
        
        System.out.println("\n Orden in-orden: " + arbol.recorridoInOrden());
        
        // Probar navegacion para varios nodos
        int[] nodosTest = {5, 15, 20, 30, 35};
        
        System.out.println("\n🧭 Navegacion (predecesor ← nodo → sucesor):");
        for (int clave : nodosTest) {
            var nodo = arbol.buscar(clave);
            if (!nodo.esNIL()) {
                var predecesor = arbol.predecesor(nodo);
                var sucesor = arbol.sucesor(nodo);
                
                String predStr = predecesor.esNIL() ? "NULL" : predecesor.getClave().toString();
                String sucStr = sucesor.esNIL() ? "NULL" : sucesor.getClave().toString();
                
                System.out.printf("   %s ← [%d] → %s%n", predStr, clave, sucStr);
            }
        }
        
        System.out.println("\n Recorrido usando sucesores (desde el minimo):");
        var minimo = arbol.buscar(Collections.min(arbol.recorridoInOrden()));
        List<Integer> recorridoSucesores = new ArrayList<>();
        
        var actual = minimo;
        while (!actual.esNIL()) {
            recorridoSucesores.add(actual.getClave());
            actual = arbol.sucesor(actual);
        }
        
        System.out.println("   " + recorridoSucesores);
        
        System.out.println("\n La navegacion por predecesor/sucesor es util para:");
        System.out.println("   • Recorridos iterativos eficientes");
        System.out.println("   • Implementacion de iteradores");
        System.out.println("   • Busqueda del siguiente/anterior elemento");
        
        arbol.mostrarEstadisticas();
    }
    
    /**
     * Ejercicio 7: Comparacion de rendimiento entre diferentes tamanos.
     */
    private static void ejercicio7ComparacionRendimiento() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("         EJERCICIO 7: COMPARACION DE RENDIMIENTO");
        System.out.println("=".repeat(60));
        
        int[] tamanos = {10, 50, 100, 200};
        
        System.out.println(" Analizando rendimiento para diferentes tamanos:");
        System.out.printf("%-10s | %-12s | %-12s | %-15s%n", 
            "Tamano", "Rotaciones", "Altura Negra", "Rot/Elemento");
        System.out.println("-".repeat(55));
        
        for (int tamano : tamanos) {
            ArbolRojoNegro<Integer, String> arbol = new ArbolRojoNegro<>();
            
            // Insercion secuencial (peor caso para arboles no balanceados)
            for (int i = 1; i <= tamano; i++) {
                arbol.insertar(i, "Val" + i);
            }
            
            double rotacionesPorElemento = (double) arbol.getContadorRotaciones() / tamano;
            
            System.out.printf("%-10d | %-12d | %-12d | %-15.2f%n", 
                tamano, 
                arbol.getContadorRotaciones(), 
                arbol.alturaNegra(),
                rotacionesPorElemento);
        }
        
        System.out.println("\n Analisis teorico:");
        System.out.println("   • Altura maxima de arbol RB: 2·log₂(n+1)");
        System.out.println("   • Busqueda: O(log n) garantizado");
        System.out.println("   • Insercion: O(log n) amortizado");
        System.out.println("   • Rotaciones por insercion: O(1) amortizado");
        
        System.out.println("\n Conclusion: Los arboles Rojo-Negro mantienen un rendimiento");
        System.out.println("   consistente incluso con inserciones secuenciales (peor caso).");
    }
    
    /**
     * Ejercicio 8: Escenario complejo con multiples operaciones.
     */
    private static void ejercicio8EscenarioComplejo() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("           EJERCICIO 8: ESCENARIO COMPLEJO");
        System.out.println("=".repeat(60));
        
        ArbolRojoNegro<String, Integer> diccionario = new ArbolRojoNegro<>();
        
        // Simular un diccionario de frecuencias de palabras
        String[] palabras = {
            "java", "arbol", "rojo", "negro", "algoritmo", "estructura",
            "datos", "balanceado", "rotacion", "insercion", "busqueda",
            "nodo", "raiz", "hoja", "navegacion"
        };
        
        System.out.println(" Construyendo diccionario de frecuencias de palabras:");
        System.out.println("   Palabras: " + Arrays.toString(palabras));
        
        // Insertar palabras con frecuencias aleatorias
        Random random = new Random(42);
        for (String palabra : palabras) {
            int frecuencia = random.nextInt(50) + 1; // 1-50
            diccionario.insertar(palabra, frecuencia);
            System.out.printf("   '%s': %d veces%n", palabra, frecuencia);
        }
        
        System.out.printf("%n Diccionario creado con %d palabras%n", diccionario.tamano());
        System.out.printf(" Rotaciones realizadas: %d%n", diccionario.getContadorRotaciones());
        
        // Consultas especificas
        System.out.println("\n Consultas especificas:");
        String[] consultasTest = {"java", "python", "arbol", "xyz"};
        for (String consulta : consultasTest) {
            Integer frecuencia = diccionario.obtenerValor(consulta);
            if (frecuencia != null) {
                System.out.printf("   '%s': aparece %d veces%n", consulta, frecuencia);
            } else {
                System.out.printf("   '%s':  no encontrada%n", consulta);
            }
        }
        
        // Consulta por rango alfabetico
        System.out.println("\n Palabras en rango alfabetico [a-m]:");
        List<String> palabrasAM = diccionario.consultaRango("a", "m");
        System.out.println("   " + palabrasAM);
        
        // Estadisticas del diccionario
        System.out.println("\n Estadisticas del diccionario:");
        List<String> todasPalabras = diccionario.recorridoInOrden();
        System.out.printf("   Primera palabra: '%s'%n", todasPalabras.get(0));
        System.out.printf("   Ultima palabra: '%s'%n", todasPalabras.get(todasPalabras.size() - 1));
        
        diccionario.mostrarEstadisticas();
        
        System.out.println("\n Este escenario demuestra el uso practico de arboles RB");
        System.out.println("   en aplicaciones reales como diccionarios y bases de datos.");
    }
    
    /**
     * Ejercicio 9: Visualizacion detallada de la estructura del arbol.
     */
    private static void ejercicio9VisualizacionArbol() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("          EJERCICIO 9: VISUALIZACION DEL ARBOL");
        System.out.println("=".repeat(60));
        
        ArbolRojoNegro<Integer, String> arbol = new ArbolRojoNegro<>();
        
        // Crear un arbol pequeno pero interesante
        int[] secuencia = {50, 25, 75, 10, 35, 60, 90, 5, 15, 30, 40};
        
        System.out.println(" Construyendo arbol paso a paso:");
        System.out.println("   Secuencia: " + Arrays.toString(secuencia));
        
        for (int i = 0; i < secuencia.length; i++) {
            int num = secuencia[i];
            int rotacionesAntes = arbol.getContadorRotaciones();
            
            arbol.insertar(num, "V" + num);
            
            int nuevasRotaciones = arbol.getContadorRotaciones() - rotacionesAntes;
            
            System.out.printf("%nPaso %d: Insertando %d", i + 1, num);
            if (nuevasRotaciones > 0) {
                System.out.printf(" (causo %d rotaciones)", nuevasRotaciones);
            }
            System.out.println();
            
            // Mostrar arbol cada 3 inserciones o al final
            if ((i + 1) % 4 == 0 || i == secuencia.length - 1) {
                System.out.printf("%n Estado del arbol (elementos: %d):%n", arbol.tamano());
                arbol.mostrarArbol();
            }
        }
        
        System.out.println("\n Propiedades finales del arbol:");
        System.out.printf("    Raiz negra: %s%n", arbol.raizEsNegra());
        System.out.printf("    Sin rojos consecutivos: %s%n", arbol.sinNodosRojosConsecutivos());
        System.out.printf("    Altura negra: %d%n", arbol.alturaNegra());
        
        arbol.mostrarEstadisticas();
        
        System.out.println("\n La visualizacion ayuda a entender como las rotaciones");
        System.out.println("   mantienen el arbol balanceado y las propiedades RB.");
    }
    
    /**
     * Ejercicio 10: Test de estres con muchos elementos.
     */
    private static void ejercicio10EstresTest() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("           EJERCICIO 10: TEST DE ESTRES");
        System.out.println("=".repeat(60));
        
        final int NUM_ELEMENTOS = 1000;
        
        System.out.printf(" Insertando %d elementos aleatorios...%n", NUM_ELEMENTOS);
        
        ArbolRojoNegro<Integer, String> arbol = new ArbolRojoNegro<>();
        
        // Generar numeros aleatorios unicos
        Set<Integer> numerosUnicos = new HashSet<>();
        Random random = new Random(12345); // Semilla fija
        
        while (numerosUnicos.size() < NUM_ELEMENTOS) {
            numerosUnicos.add(random.nextInt(NUM_ELEMENTOS * 2));
        }
        
        List<Integer> numeros = new ArrayList<>(numerosUnicos);
        Collections.shuffle(numeros, random);
        
        // Medir tiempo de insercion
        long tiempoInicio = System.nanoTime();
        
        for (int i = 0; i < numeros.size(); i++) {
            int num = numeros.get(i);
            arbol.insertar(num, "Val" + num);
            
            // Progreso cada 100 elementos
            if ((i + 1) % 100 == 0) {
                System.out.printf("   Progreso: %d/%d elementos insertados%n", i + 1, NUM_ELEMENTOS);
            }
        }
        
        long tiempoFin = System.nanoTime();
        double tiempoMs = (tiempoFin - tiempoInicio) / 1_000_000.0;
        
        System.out.printf("%n Insercion completada en %.2f ms%n", tiempoMs);
        
        // Verificar integridad del arbol
        System.out.println("\n Verificando integridad del arbol grande:");
        
        boolean integridadOK = true;
        
        // Verificar tamano
        if (arbol.tamano() != NUM_ELEMENTOS) {
            System.out.printf("    Tamano incorrecto: esperado %d, actual %d%n", 
                NUM_ELEMENTOS, arbol.tamano());
            integridadOK = false;
        } else {
            System.out.printf("    Tamano correcto: %d elementos%n", arbol.tamano());
        }
        
        // Verificar propiedades RB
        boolean raizNegra = arbol.raizEsNegra();
        boolean sinRojos = arbol.sinNodosRojosConsecutivos();
        int alturaNegra = arbol.alturaNegra();
        
        System.out.printf("   %s Raiz negra%n", raizNegra ? "" : "");
        System.out.printf("   %s Sin nodos rojos consecutivos%n", sinRojos ? "" : "");
        System.out.printf("   %s Altura negra consistente: %d%n", 
            (alturaNegra != -1) ? "" : "", alturaNegra);
        
        integridadOK = integridadOK && raizNegra && sinRojos && (alturaNegra != -1);
        
        // Test de busqueda en arbol grande
        System.out.println("\n Test de busqueda (100 elementos aleatorios):");
        long tiempoInicioB = System.nanoTime();
        
        int encontrados = 0;
        for (int i = 0; i < 100; i++) {
            int claveBuscar = numeros.get(random.nextInt(numeros.size()));
            if (arbol.contiene(claveBuscar)) {
                encontrados++;
            }
        }
        
        long tiempoFinB = System.nanoTime();
        double tiempoBusquedaMs = (tiempoFinB - tiempoInicioB) / 1_000_000.0;
        
        System.out.printf("   Encontrados: %d/100 en %.2f ms%n", encontrados, tiempoBusquedaMs);
        System.out.printf("   Tiempo promedio por busqueda: %.4f ms%n", tiempoBusquedaMs / 100);
        
        // Estadisticas finales
        arbol.mostrarEstadisticas();
        
        System.out.printf("%n Analisis de eficiencia:%n");
        System.out.printf("   Rotaciones/elemento: %.3f%n", 
            (double) arbol.getContadorRotaciones() / NUM_ELEMENTOS);
        System.out.printf("   Tiempo/insercion: %.4f ms%n", tiempoMs / NUM_ELEMENTOS);
        System.out.printf("   Altura teorica maxima: %.1f%n", 2 * Math.log(NUM_ELEMENTOS + 1) / Math.log(2));
        
        System.out.printf("%n RESULTADO DEL TEST DE ESTRES: %s%n", 
            integridadOK ? " EXITOSO - El arbol mantiene todas sus propiedades" 
                         : " FALLIDO - Se detectaron problemas de integridad");
    }
}