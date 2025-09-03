/*
Ejercicio 3 – Invertir una Cadena con Pila
Usando la clase PilaArreglo, escriba un programa que reciba una cadena y la invierta.
Ejemplo: "Hola" → "aloH".
*/

import java.util.Scanner;

class PilaArreglo {
    private char[] elementos;
    private int tope;
    
    public PilaArreglo(int tamano) {
        elementos = new char[tamano];
        tope = -1;
    }
    
    public void push(char c) {
        tope++;
        elementos[tope] = c;
    }
    
    public char pop() {
        char temp = elementos[tope];
        tope--;
        return temp;
    }
    
    public boolean isEmpty() {
        return tope == -1;
    }
}

public class Ejercicio_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Ingrese una cadena: ");
        String cadena = sc.nextLine();
        
        PilaArreglo pila = new PilaArreglo(cadena.length());
        
        for (int i = 0; i < cadena.length(); i++) {
            pila.push(cadena.charAt(i));
        }
        
        String resultado = "";
        while (!pila.isEmpty()) {
            resultado = resultado + pila.pop();
        }
        
        System.out.println("Cadena invertida: " + resultado);
    }
}
