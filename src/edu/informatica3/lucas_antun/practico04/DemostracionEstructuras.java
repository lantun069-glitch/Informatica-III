package edu.informatica3.lucas_antun.practico04;

/**
 * Demostracion de estructuras de datos (Pilas y Colas).
 */
public class DemostracionEstructuras {
    
    /**
     * Metodo principal que ejecuta todas las demostraciones.
     * 
     * @param args argumentos de linea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("ESTRUCTURAS DE DATOS - PILAS Y COLAS");
        demostrarPila();
        demostrarAplicacionesPila();
        System.out.println("DEMOSTRACIONES COMPLETADAS");
    }
    
    /**
     * Demuestra las operaciones basicas de la Pila.
     */
    private static void demostrarPila() {
        System.out.println("\nDEMOSTRACION DE PILA CON ARREGLO");
        
        // Crear una pila de enteros con capacidad 5
        PilaArreglo<Integer> pila = new PilaArreglo<>(5);
        
        System.out.println("Pila creada con capacidad 5:");
        System.out.println(pila);
        System.out.printf("Esta vacia? %s%n", pila.isEmpty() ? "Si" : "No");
        System.out.printf("Esta llena? %s%n", pila.isFull() ? "Si" : "No");
        
        // Agregar elementos
        System.out.println("\nAgregando elementos 10, 20, 30, 40:");
        try {
            pila.push(10);
            System.out.println("Push 10: " + pila);
            
            pila.push(20);
            System.out.println("Push 20: " + pila);
            
            pila.push(30);
            System.out.println("Push 30: " + pila);
            
            pila.push(40);
            System.out.println("Push 40: " + pila);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        // Verificar tope
        System.out.printf("\nElemento en el tope: %d%n", pila.top());
        System.out.printf("Tamano actual: %d%n", pila.size());
        
        // Desapilar elementos
        System.out.println("\nDesapilando dos elementos:");
        try {
            System.out.printf("Pop: %d → %s%n", pila.pop(), pila);
            System.out.printf("Pop: %d → %s%n", pila.pop(), pila);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        // Busqueda
        System.out.println("\nBuscando elementos:");
        System.out.printf("Posicion del 20 desde el tope: %d%n", pila.search(20));
        System.out.printf("Posicion del 10 desde el tope: %d%n", pila.search(10));
        System.out.printf("Posicion del 99 (no existe): %d%n", pila.search(99));
        
        // Llenar la pila
        System.out.println("\nLlenando la pila hasta el limite:");
        try {
            pila.push(50);
            System.out.println("Push 50: " + pila);
            pila.push(60);
            System.out.println("Push 60: " + pila);
            pila.push(70);
            System.out.println("Push 70: " + pila);
            
            // Intentar agregar uno mas (deberia fallar)
            pila.push(80);
            
        } catch (IllegalStateException e) {
            System.out.println("Error esperado: " + e.getMessage());
        }
        
        System.out.printf("Estado final - Esta llena? %s%n", pila.isFull() ? "Si" : "No");
    }
    
    /**
     * Demuestra aplicaciones practicas de las pilas.
     */
    private static void demostrarAplicacionesPila() {
        System.out.println("\nAPLICACIONES PRACTICAS DE PILAS");
        demostrarParentesisBalanceados();
        demostrarConversionExpresiones();
        demostrarEvaluacionPostfija();
        demostrarHistorialNavegacion();
    }
    
    /**
     * Demuestra la verificacion de parentesis balanceados usando una pila.
     */
    private static void demostrarParentesisBalanceados() {
        System.out.println("\n Aplicacion 1: Verificacion de Parentesis Balanceados");
        System.out.println("-".repeat(55));
        
        String[] expresiones = {
            "((()))",
            "()()())",
            "(()",
            "(()())",
            ")((",
            ""
        };
        
        for (String expr : expresiones) {
            boolean balanceado = verificarParentesisBalanceados(expr);
            System.out.printf("'%s' → %s%n", 
                expr.isEmpty() ? "(vacio)" : expr, 
                balanceado ? " Balanceado" : " No balanceado");
        }
    }
    
    /**
     * Verifica si los parentesis en una expresion estan balanceados.
     * 
     * @param expresion la expresion a verificar
     * @return true si estan balanceados, false en caso contrario
     */
    private static boolean verificarParentesisBalanceados(String expresion) {
        if (expresion.isEmpty()) {
            return true; // Cadena vacia esta balanceada
        }
        
        PilaArreglo<Character> pila = new PilaArreglo<>(expresion.length());
        
        for (char c : expresion.toCharArray()) {
            if (c == '(') {
                try {
                    pila.push(c);
                } catch (IllegalStateException e) {
                    return false; // Demasiados parentesis de apertura
                }
            } else if (c == ')') {
                if (pila.isEmpty()) {
                    return false; // Parentesis de cierre sin apertura
                }
                pila.pop();
            }
        }
        
        return pila.isEmpty(); // Debe estar vacia para estar balanceado
    }
    
    /**
     * Demuestra la conversion de expresiones infijas a postfijas.
     */
    private static void demostrarConversionExpresiones() {
        System.out.println("\n Aplicacion 2: Conversion Infija a Postfija");
        System.out.println("-".repeat(45));
        
        String[] expresiones = {
            "a+b",
            "a+b*c",
            "(a+b)*c",
            "a*(b+c)",
            "a+b*c+d"
        };
        
        for (String expr : expresiones) {
            String postfija = convertirInfijaAPostfija(expr);
            System.out.printf("Infija: %-12s → Postfija: %s%n", expr, postfija);
        }
    }
    
    /**
     * Convierte una expresion infija simple a postfija.
     * Simplificado para operadores +, *, (, )
     * 
     * @param infija expresion en notacion infija
     * @return expresion en notacion postfija
     */
    private static String convertirInfijaAPostfija(String infija) {
        PilaArreglo<Character> pila = new PilaArreglo<>(infija.length());
        StringBuilder resultado = new StringBuilder();
        
        for (char c : infija.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                // Operando: agregar directamente al resultado
                resultado.append(c);
            } else if (c == '(') {
                // Parentesis de apertura: push a la pila
                pila.push(c);
            } else if (c == ')') {
                // Parentesis de cierre: pop hasta encontrar '('
                while (!pila.isEmpty() && pila.top() != '(') {
                    resultado.append(pila.pop());
                }
                if (!pila.isEmpty()) {
                    pila.pop(); // Remover '('
                }
            } else if (esOperador(c)) {
                // Operador: pop operadores de mayor o igual precedencia
                while (!pila.isEmpty() && 
                       precedencia(pila.top()) >= precedencia(c)) {
                    resultado.append(pila.pop());
                }
                pila.push(c);
            }
        }
        
        // Pop operadores restantes
        while (!pila.isEmpty()) {
            resultado.append(pila.pop());
        }
        
        return resultado.toString();
    }
    
    /**
     * Verifica si un caracter es un operador.
     */
    private static boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
    
    /**
     * Retorna la precedencia de un operador.
     */
    private static int precedencia(char operador) {
        return switch (operador) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> -1;
        };
    }
    
    /**
     * Demuestra la evaluacion de expresiones postfijas.
     */
    private static void demostrarEvaluacionPostfija() {
        System.out.println("\n Aplicacion 3: Evaluacion de Expresiones Postfijas");
        System.out.println("-".repeat(50));
        
        String[] expresiones = {
            "23+",      // (2+3) = 5
            "23*4+",    // (2*3)+4 = 10
            "234*+",    // 2+(3*4) = 14
            "15+26*+",  // (1+5)+(2*6) = 18
        };
        
        for (String expr : expresiones) {
            try {
                int resultado = evaluarPostfija(expr);
                System.out.printf("Postfija: %s → Resultado: %d%n", expr, resultado);
            } catch (Exception e) {
                System.out.printf("Postfija: %s → Error: %s%n", expr, e.getMessage());
            }
        }
    }
    
    /**
     * Evalua una expresion postfija simple.
     * 
     * @param postfija expresion postfija con digitos unicos
     * @return resultado de la evaluacion
     */
    private static int evaluarPostfija(String postfija) {
        PilaArreglo<Integer> pila = new PilaArreglo<>(postfija.length());
        
        for (char c : postfija.toCharArray()) {
            if (Character.isDigit(c)) {
                // Operando: push a la pila
                pila.push(Character.getNumericValue(c));
            } else if (esOperador(c)) {
                // Operador: pop dos operandos, operar y push resultado
                if (pila.size() < 2) {
                    throw new IllegalArgumentException("Expresion invalida: faltan operandos");
                }
                
                int operando2 = pila.pop();
                int operando1 = pila.pop();
                int resultado = switch (c) {
                    case '+' -> operando1 + operando2;
                    case '-' -> operando1 - operando2;
                    case '*' -> operando1 * operando2;
                    case '/' -> {
                        if (operando2 == 0) {
                            throw new ArithmeticException("Division por cero");
                        }
                        yield operando1 / operando2;
                    }
                    default -> throw new IllegalArgumentException("Operador desconocido: " + c);
                };
                
                pila.push(resultado);
            }
        }
        
        if (pila.size() != 1) {
            throw new IllegalArgumentException("Expresion invalida: resultado ambiguo");
        }
        
        return pila.pop();
    }
    
    /**
     * Demuestra un historial de navegacion usando una pila.
     */
    private static void demostrarHistorialNavegacion() {
        System.out.println("\n Aplicacion 4: Historial de Navegacion Web");
        System.out.println("-".repeat(42));
        
        HistorialNavegacion historial = new HistorialNavegacion(10);
        
        // Simular navegacion
        System.out.println("Navegando por diferentes paginas:");
        historial.visitarPagina("google.com");
        historial.visitarPagina("youtube.com");
        historial.visitarPagina("github.com");
        historial.visitarPagina("stackoverflow.com");
        
        System.out.println("\nHistorial actual:");
        historial.mostrarHistorial();
        
        System.out.println("\nRetrocediendo 2 paginas:");
        historial.retroceder();
        historial.retroceder();
        
        System.out.println("\nHistorial despues de retroceder:");
        historial.mostrarHistorial();
        
        System.out.println("\nVisitando nueva pagina:");
        historial.visitarPagina("reddit.com");
        historial.mostrarHistorial();
    }
    
    /**
     * Clase auxiliar para demostrar historial de navegacion con pila.
     */
    private static class HistorialNavegacion {
        private final PilaArreglo<String> historial;
        private String paginaActual;
        
        public HistorialNavegacion(int capacidad) {
            this.historial = new PilaArreglo<>(capacidad);
            this.paginaActual = null;
        }
        
        public void visitarPagina(String url) {
            if (paginaActual != null) {
                try {
                    historial.push(paginaActual);
                } catch (IllegalStateException e) {
                    // Si el historial esta lleno, remover la pagina mas antigua
                    // En una implementacion real, usariamos una estructura diferente
                    System.out.println("Historial lleno, pagina mas antigua eliminada");
                }
            }
            paginaActual = url;
            System.out.printf(" Visitando: %s%n", url);
        }
        
        public String retroceder() {
            if (historial.isEmpty()) {
                System.out.println(" No hay paginas anteriores");
                return paginaActual;
            }
            
            String paginaAnterior = historial.pop();
            System.out.printf(" Retrocediendo a: %s%n", paginaAnterior);
            paginaActual = paginaAnterior;
            return paginaActual;
        }
        
        public void mostrarHistorial() {
            System.out.printf(" Pagina actual: %s%n", 
                paginaActual != null ? paginaActual : "Ninguna");
            
            if (!historial.isEmpty()) {
                System.out.println(" Historial: " + historial);
            } else {
                System.out.println(" Historial: vacio");
            }
        }
    }
}