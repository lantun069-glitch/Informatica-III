/*
Ejercicio 2 – Implementación de Cola
Implemente una clase ColaArreglo en Java utilizando un arreglo.
Métodos: enqueue(int dato), dequeue(), top(), isEmpty(), isFull().
Pruebe encolando los enteros 1, 2, 3, 4 y desencolando uno.
 */

public class Ejercicio_2 {
    public static class ColaArreglo {
    private int[] arreglo;
    private int frente;
    private int fin;
    private int tamano;
    private int capacidad;
    
    // Constructor
    public ColaArreglo(int capacidad) {
        this.capacidad = capacidad;
        this.arreglo = new int[capacidad];
        this.frente = 0;
        this.fin = -1;
        this.tamano = 0;
    }
    
    // Metodo para agregar elemento al final de la cola
    public void enqueue(int dato) {
        if (isFull()) {
            System.out.println("La cola esta llena. No se puede agregar: " + dato);
            return;
        }
        fin = (fin + 1) % capacidad;
        arreglo[fin] = dato;
        tamano++;
    }
    
    // Metodo para quitar y retornar elemento del frente
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("La cola esta vacia");
            return -1;
        }
        int dato = arreglo[frente];
        frente = (frente + 1) % capacidad;
        tamano--;
        return dato;
    }
    
    // para ver el elemento del frente 
    public int top() {
        if (isEmpty()) {
            System.out.println("La cola esta vacia");
            return -1;
        }
        return arreglo[frente];
    }
    
    public boolean isEmpty() {
        return tamano == 0;
    }
    
    public boolean isFull() {
        return tamano == capacidad;
    }
    
    // Metodo principal para pruebas
    public static void main(String[] args) {
        ColaArreglo cola = new ColaArreglo(5);
        
        // Encolando los enteros 1, 2, 3, 4
        cola.enqueue(1);
        cola.enqueue(2);
        cola.enqueue(3);
        cola.enqueue(4);
        
        System.out.println("Elemento en el frente: " + cola.top());
        
        // Desencolando uno
        int elementoDesencolado = cola.dequeue();
        System.out.println("Elemento desencolado: " + elementoDesencolado);
        
        System.out.println("Nuevo elemento en el frente: " + cola.top());
        
        // Verificaciones adicionales
        System.out.println("Cola vacia? " + cola.isEmpty());
        System.out.println("Cola llena? " + cola.isFull());
    }
}
}
