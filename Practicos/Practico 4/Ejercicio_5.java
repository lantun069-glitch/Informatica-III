/*
Ejercicio 5 – Palíndromo con Pila y Cola
Un palíndromo es una palabra o frase que se lee igual en ambos sentidos (ej: "radar").
Implemente un programa que determine si una palabra es palíndromo usando:
● Una pila para recorrer de derecha a izquierda.
● Una cola para recorrer de izquierda a derecha.
 */

import java.util.Scanner;

public class Ejercicio_5 {

    static class Pila {
        private char[] elementos;
        private int tope;
        
        public Pila(int capacidad) {
            elementos = new char[capacidad];
            tope = -1;
        }
        
        public void push(char elemento) {
            tope++;
            elementos[tope] = elemento;
        }
        
        public char pop() {
            char elemento = elementos[tope];
            tope--;
            return elemento;
        }
        
        public boolean estaVacia() {
            return tope == -1;
        }
    }

    static class Cola {
        private char[] elementos;
        private int frente;
        private int fin;
        private int tamano;
        
        public Cola(int capacidad) {
            elementos = new char[capacidad];
            frente = 0;
            fin = -1;
            tamano = 0;
        }
        
        public void encolar(char elemento) {
            fin++;
            elementos[fin] = elemento;
            tamano++;
        }
        
        public char desencolar() {
            char elemento = elementos[frente];
            frente++;
            tamano--;
            return elemento;
        }
        
        public boolean estaVacia() {
            return tamano == 0;
        }
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        
        System.out.print("Ingrese una palabra para verificar si es palindromo: ");
        String palabra = entrada.nextLine();
        
        // Convertir a minusculas para comparacion
        palabra = palabra.toLowerCase();
        
        // Crear pila y cola con el tamano de la palabra
        Pila pila = new Pila(palabra.length());
        Cola cola = new Cola(palabra.length());
        
        // Llenar la pila y la cola con los caracteres
        for (int i = 0; i < palabra.length(); i++) {
            char caracter = palabra.charAt(i);
            pila.push(caracter);
            cola.encolar(caracter);
        }
        
        // Verificar si es palindromo
        boolean esPalindromo = true;
        
        while (!pila.estaVacia() && !cola.estaVacia()) {
            char caracterPila = pila.pop();
            char caracterCola = cola.desencolar();
            
            if (caracterPila != caracterCola) {
                esPalindromo = false;
                break;
            }
        }
        
        // Mostrar resultado
        if (esPalindromo) {
            System.out.println("La palabra \"" + palabra + "\" ES un palindromo");
        } else {
            System.out.println("La palabra \"" + palabra + "\" NO es un palindromo");
        }
        
        entrada.close();
    }
}