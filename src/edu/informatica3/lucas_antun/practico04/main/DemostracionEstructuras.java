package edu.informatica3.lucas_antun.practico04.main;

import edu.informatica3.lucas_antun.practico04.estructuras.*;

public class DemostracionEstructuras {

    public static void main(String[] args) {

        System.out.println("=== PRACTICO 4 - PILAS Y COLAS ===\n");

        System.out.println("1. Pila con arreglo:");
        PilaArregloDemo pila = new PilaArregloDemo(5);
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

        System.out.println("2. Cola con arreglo:");
        ColaArregloDemo cola = new ColaArregloDemo(5);
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

        System.out.println("3. Invertir cadena con pila:");
        String texto = "Hola";
        PilaArregloDemo pilaCadena = new PilaArregloDemo(texto.length());
        for (char c : texto.toCharArray()) pilaCadena.push(c);
        String invertida = "";
        while (!pilaCadena.isEmpty()) invertida += (char) pilaCadena.pop();
        System.out.println("Original: " + texto);
        System.out.println("Invertida: " + invertida);
        System.out.println();

        System.out.println("4. Simulación de turnos con cola:");
        ColaArregloStringDemo clientes = new ColaArregloStringDemo(10);
        clientes.enqueue("Ana");
        clientes.enqueue("Luis");
        clientes.enqueue("Marta");
        clientes.enqueue("Pedro");
        System.out.print("Cola antes de atender: ");
        clientes.mostrar();
        clientes.dequeue();
        clientes.dequeue();
        System.out.print("Cola después de atender: ");
        clientes.mostrar();
        System.out.println();

        System.out.println("5. Palíndromo con pila y cola:");
        String palabra = "radar";
        PilaArregloDemo p = new PilaArregloDemo(palabra.length());
        ColaArregloDemo c = new ColaArregloDemo(palabra.length());
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

        System.out.println("6. Deshacer / Rehacer con pilas:");
        PilaArregloDemo deshacer = new PilaArregloDemo(10);
        PilaArregloDemo rehacer = new PilaArregloDemo(10);

        deshacer.push('A');
        deshacer.push('B');
        deshacer.push('C');
        deshacer.push('D');
        deshacer.push('E');
        System.out.print("Pila de deshacer: ");
        deshacer.mostrar();

        System.out.println("Deshacer 2 acciones.");
        rehacer.push(deshacer.pop());
        rehacer.push(deshacer.pop());
        System.out.print("Pila deshacer: ");
        deshacer.mostrar();
        System.out.print("Pila rehacer: ");
        rehacer.mostrar();

        System.out.println("Rehacer 1 acción.");
        deshacer.push(rehacer.pop());
        System.out.print("Pila deshacer: ");
        deshacer.mostrar();
        System.out.print("Pila rehacer: ");
        rehacer.mostrar();
        System.out.println();

        System.out.println("7. Simulación de impresora con cola:");
        ColaArregloStringDemo impresora = new ColaArregloStringDemo(10);
        for (int i = 1; i <= 5; i++) impresora.enqueue("Doc" + i);
        System.out.print("Cola de impresión: ");
        impresora.mostrar();
        System.out.println("Imprimiendo 3 documentos.");
        for (int i = 0; i < 3; i++) impresora.dequeue();
        System.out.print("Cola después de imprimir: ");
        impresora.mostrar();
        System.out.println();

        System.out.println("8. Cola circular para llamadas:");
        ColaArregloDemo llamadas = new ColaArregloDemo(5);
        for (int i = 1; i <= 8; i++) {
            llamadas.enqueue(i);
        }
        System.out.print("Cola final (últimas 5 llamadas): ");
        llamadas.mostrar();

        System.out.println("\n=== FIN DEL PRACTICO ===");
    }
}
