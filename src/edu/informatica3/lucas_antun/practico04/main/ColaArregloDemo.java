package edu.informatica3.lucas_antun.practico04.main;

/**
 * Cola circular para enteros con comportamiento de sobrescritura.
 */
public class ColaArregloDemo {
    private int[] datos;
    private int frente, fin, cantidad;

    public ColaArregloDemo(int capacidad) {
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
        fin = (fin + 1) % datos.length;
        if (isFull()) {
            int sobrescrito = datos[frente];
            frente = (frente + 1) % datos.length;
            datos[fin] = dato;
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
