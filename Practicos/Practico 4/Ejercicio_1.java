/*
Ejercicio 1 – Implementación de Pila
Implemente una clase PilaArreglo en Java utilizando un arreglo.
Métodos: push(int dato), pop(), top(), isEmpty(), isFull().
Pruebe apilando los enteros 10, 20, 30, 40 y desapilando dos.
*/

public class Ejercicio_1 {
    public static class PilaArreglo {
        private int[] elementos;
        private int tope;
        private int capacidad;

        public PilaArreglo(int capacidad) {
            this.capacidad = capacidad;
            elementos = new int[capacidad];
            tope = -1;
        }

        public boolean isEmpty() {
            return tope == -1;
        }

        public boolean isFull() {
            return tope == capacidad - 1;
        }

        // Adicionales
        public boolean estaVacia() {
            return isEmpty();
        }

        public boolean estaLlena() {
            return isFull();
        }

        public void push(int dato) {
            if (!isFull()) {
                elementos[++tope] = dato;
            } else {
                System.out.println("Pila llena");
            }
        }

        public int pop() {
            if (!isEmpty()) {
                return elementos[tope--];
            } else {
                System.out.println("Pila vacía");
                return -1; // Valor por defecto
            }
        }

        public int top() {
            if (!isEmpty()) {
                return elementos[tope];
            } else {
                System.out.println("Pila vacía");
                return -1; // Valor por defecto
            }
        }
    }

    public static void main(String[] args) {
        PilaArreglo pila = new PilaArreglo(5);
        
        pila.push(10);
        pila.push(20);
        pila.push(30);
        pila.push(40);
        
        System.out.println("Elemento en la cima: " + pila.top());
        
        // Desapilando dos elementos
        System.out.println("Desapilando: " + pila.pop());
        System.out.println("Desapilando: " + pila.pop());
        
        System.out.println("Elemento en la cima después de desapilar dos: " + pila.top());
        
        System.out.println("Esta vacia? " + pila.isEmpty());
        System.out.println("Esta llena? " + pila.isFull());
    }
}