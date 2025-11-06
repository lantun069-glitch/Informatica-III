package edu.informatica3.lucas_antun.practico04.main;

public class PilaArregloDemo {
    private int[] datos;
    private int tope;

    public PilaArregloDemo(int capacidad) {
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
