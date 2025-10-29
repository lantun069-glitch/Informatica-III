package edu.informatica3.lucas_antun.practico04;

public class DemostracionEstructuras {

    // ==========================
    // Ejercicio 1 – Pila con Arreglo
    // ==========================
    static class PilaArreglo {
        private int[] datos;
        private int tope;

        public PilaArreglo(int capacidad) {
            datos = new int[capacidad];
            tope = -1;
        }

        public boolean isEmpty() {
            return tope == -1;
        }

        public boolean isFull() {
            return tope == datos.length - 1;
        }

        public void push(int dato) {
            if (isFull()) {
                System.out.println("La pila está llena");
                throw new IllegalStateException("Pila llena");
            }
            datos[++tope] = dato;
        }

        public int pop() {
            if (isEmpty()) {
                System.out.println("La pila está vacía");
                throw new IllegalStateException("Pila vacía");
            }
            return datos[tope--];
        }

        public int top() {
            if (isEmpty()) {
                System.out.println("La pila está vacía");
                throw new IllegalStateException("Pila vacía");
            }
            return datos[tope];
        }

        public void mostrar() {
            System.out.print("[ ");
            for (int i = 0; i <= tope; i++) {
                System.out.print(datos[i] + " ");
            }
            System.out.println("]");
        }
    }

    // ==========================
    // Ejercicio 2 – Cola con Arreglo
    // ==========================
    /**
     * Cola circular para enteros con comportamiento de sobrescritura.
     *
     * Comportamiento:
     * - Tiene una capacidad fija (tamaño del arreglo).
     * - `enqueue` inserta siempre en la posición `fin` siguiente.
     * - Si la cola está llena, `enqueue` sobrescribe la entrada más antigua
     *   (avanzando `frente`) en lugar de lanzar una excepción.
     * - Cuando se sobrescribe, se registra un mensaje indicando qué
     *   elemento fue descartado y cuál lo reemplaza (útil en demos).
     *
     * Este comportamiento simula una cola circular en sistemas donde las
     * llamadas/entradas más recientes deben conservarse y las más antiguas
     * pueden descartarse automáticamente.
     */
    static class ColaArreglo {
        private int[] datos;
        private int frente, fin, cantidad;

        public ColaArreglo(int capacidad) {
            datos = new int[capacidad];
            frente = 0;
            fin = -1;
            cantidad = 0;
        }

        public boolean isEmpty() {
            return cantidad == 0;
        }

        public boolean isFull() {
            return cantidad == datos.length;
        }

        public void enqueue(int dato) {
            // Avanzamos la posición de fin donde se insertará el nuevo elemento
            fin = (fin + 1) % datos.length;
            if (isFull()) {
                // Si la cola está llena, sobrescribimos la llamada más antigua.
                // Guardamos el valor que será descartado para el log.
                int sobrescrito = datos[frente];
                // Avanzamos 'frente' descartando el elemento antiguo.
                frente = (frente + 1) % datos.length;
                datos[fin] = dato;
                // 'cantidad' permanece igual (capacidad máxima)
                System.out.printf("Sobrescribiendo llamada %d por %d%n", sobrescrito, dato);
            } else {
                datos[fin] = dato;
                cantidad++;
            }
        }

        public int dequeue() {
            if (isEmpty()) {
                System.out.println("La cola está vacía");
                throw new IllegalStateException("Cola vacía");
            }
            int valor = datos[frente];
            frente = (frente + 1) % datos.length;
            cantidad--;
            return valor;
        }

        public int top() {
            if (isEmpty()) {
                System.out.println("La cola está vacía");
                throw new IllegalStateException("Cola vacía");
            }
            return datos[frente];
        }

        public void mostrar() {
            System.out.print("[ ");
            for (int i = 0; i < cantidad; i++) {
                if (i > 0) System.out.print(", ");
                System.out.print(datos[(frente + i) % datos.length]);
            }
            System.out.println(" ]");
        }
    }

    /**
     * Variante de cola que almacena Strings (para demostraciones con nombres).
     */
    static class ColaArregloString {
        private String[] datos;
        private int frente, fin, cantidad;

        public ColaArregloString(int capacidad) {
            datos = new String[capacidad];
            frente = 0;
            fin = -1;
            cantidad = 0;
        }

        public boolean isEmpty() { return cantidad == 0; }
        public boolean isFull() { return cantidad == datos.length; }

        public void enqueue(String dato) {
            if (isFull()) {
                System.out.println("La cola está llena");
                throw new IllegalStateException("Cola llena");
            }
            fin = (fin + 1) % datos.length;
            datos[fin] = dato;
            cantidad++;
        }

        public String dequeue() {
            if (isEmpty()) {
                System.out.println("La cola está vacía");
                throw new IllegalStateException("Cola vacía");
            }
            String valor = datos[frente];
            frente = (frente + 1) % datos.length;
            cantidad--;
            return valor;
        }

        public String top() {
            if (isEmpty()) {
                System.out.println("La cola está vacía");
                throw new IllegalStateException("Cola vacía");
            }
            return datos[frente];
        }

        public void mostrar() {
            System.out.print("[ ");
            for (int i = 0; i < cantidad; i++) {
                if (i > 0) System.out.print(", ");
                System.out.print(datos[(frente + i) % datos.length]);
            }
            System.out.println(" ]");
        }
    }

    // ==========================
    // MAIN – Demostraciones
    // ==========================
    public static void main(String[] args) {

        System.out.println("=== PRACTICO 4 – PILAS Y COLAS ===\n");

        // ===== Ejercicio 1 =====
        System.out.println("1. Pila con arreglo:");
        PilaArreglo pila = new PilaArreglo(5);
        pila.push(10);
        pila.push(20);
        pila.push(30);
        pila.push(40);
        System.out.print("Pila actual: ");
        pila.mostrar();
        pila.pop();
        pila.pop();
        System.out.print("Pila después de desapilar 2: ");
        pila.mostrar();
        System.out.println();

        // ===== Ejercicio 2 =====
        System.out.println("2. Cola con arreglo:");
        ColaArreglo cola = new ColaArreglo(5);
        cola.enqueue(1);
        cola.enqueue(2);
        cola.enqueue(3);
        cola.enqueue(4);
        System.out.print("Cola actual: ");
        cola.mostrar();
        cola.dequeue();
        System.out.print("Cola después de desencolar 1: ");
        cola.mostrar();
        System.out.println();

        // ===== Ejercicio 3 =====
        System.out.println("3. Invertir cadena con pila:");
        String texto = "Hola";
        PilaArreglo pilaCadena = new PilaArreglo(texto.length());
        for (char c : texto.toCharArray()) pilaCadena.push(c);
        String invertida = "";
        while (!pilaCadena.isEmpty()) invertida += (char) pilaCadena.pop();
        System.out.println("Original: " + texto);
        System.out.println("Invertida: " + invertida);
        System.out.println();

    // ===== Ejercicio 4 =====
    System.out.println("4. Simulación de turnos con cola:");
    ColaArregloString clientes = new ColaArregloString(10);
    clientes.enqueue("Ana");
    clientes.enqueue("Luis");
    clientes.enqueue("Marta");
    clientes.enqueue("Pedro");
    System.out.print("Cola antes de atender: ");
    clientes.mostrar();
    clientes.dequeue(); // Ana atendida
    clientes.dequeue(); // Luis atendido
    System.out.print("Cola después de atender: ");
    clientes.mostrar();
    System.out.println();

        // ===== Ejercicio 5 =====
        System.out.println("5. Palíndromo con pila y cola:");
        String palabra = "radar";
        PilaArreglo p = new PilaArreglo(palabra.length());
        ColaArreglo c = new ColaArreglo(palabra.length());
        for (char ch : palabra.toCharArray()) {
            p.push(ch);
            c.enqueue(ch);
        }
        boolean palindromo = true;
        while (!p.isEmpty() && !c.isEmpty()) {
            if (p.pop() != c.dequeue()) {
                palindromo = false;
                break;
            }
        }
        System.out.println("¿'" + palabra + "' es palíndromo? " + palindromo);
        System.out.println();

        // ===== Ejercicio 6 =====
        System.out.println("6. Deshacer / Rehacer con pilas:");
        PilaArreglo deshacer = new PilaArreglo(10);
        PilaArreglo rehacer = new PilaArreglo(10);

        deshacer.push('A');
        deshacer.push('B');
        deshacer.push('C');
        deshacer.push('D');
        deshacer.push('E');
        System.out.print("Pila de deshacer: ");
        deshacer.mostrar();

        System.out.println("Deshacer 2 acciones...");
        rehacer.push(deshacer.pop());
        rehacer.push(deshacer.pop());
        System.out.print("Pila deshacer: ");
        deshacer.mostrar();
        System.out.print("Pila rehacer: ");
        rehacer.mostrar();

        System.out.println("Rehacer 1 acción...");
        deshacer.push(rehacer.pop());
        System.out.print("Pila deshacer: ");
        deshacer.mostrar();
        System.out.print("Pila rehacer: ");
        rehacer.mostrar();
        System.out.println();

    // ===== Ejercicio 7 =====
    System.out.println("7. Simulación de impresora con cola:");
    ColaArregloString impresora = new ColaArregloString(10);
    // Encolar 5 documentos con nombres Doc1..Doc5
    for (int i = 1; i <= 5; i++) impresora.enqueue("Doc" + i);
    System.out.print("Cola de impresión: ");
    impresora.mostrar();
    System.out.println("Imprimiendo 3 documentos...");
    // Simular impresión de 3 documentos (desencolar 3)
    for (int i = 0; i < 3; i++) impresora.dequeue();
    System.out.print("Cola después de imprimir: ");
    impresora.mostrar();
    System.out.println();

        // ===== Ejercicio 8 =====
        System.out.println("8. Cola circular para llamadas:");
        ColaArreglo llamadas = new ColaArreglo(5);
        for (int i = 1; i <= 8; i++) {
            // Simulación: llegan 8 llamadas a una cola con capacidad 5.
            // Llamamos a `enqueue(i)` directamente para demostrar que la
            // cola maneja la sobrescritura internamente (si está llena)
            // y registra qué llamada antigua fue reemplazada por la nueva.
            llamadas.enqueue(i);
        }
        System.out.print("Cola final (últimas 5 llamadas): ");
        llamadas.mostrar();

        System.out.println("\n=== FIN DEL PRACTICO ===");
    }
}