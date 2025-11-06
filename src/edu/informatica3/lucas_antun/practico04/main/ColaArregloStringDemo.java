package edu.informatica3.lucas_antun.practico04.main;

/**
 * Variante de cola que almacena Strings.
 */
public class ColaArregloStringDemo {
    private String[] datos;
    private int frente, fin, cantidad;

    public ColaArregloStringDemo(int capacidad) {
        datos = new String[capacidad];
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

    public void enqueue(String dato) {
        fin = (fin + 1) % datos.length;
        if (isFull()) {
            String sobrescrito = datos[frente];
            frente = (frente + 1) % datos.length;
            datos[fin] = dato;
            System.out.printf("Sobrescribiendo usuario \"%s\" por \"%s\"%n", sobrescrito, dato);
        } else {
            datos[fin] = dato;
            cantidad++;
        }
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
            System.out.printf("\"%s\"", datos[(frente + i) % datos.length]);
        }
        System.out.println(" ]");
    }
}
