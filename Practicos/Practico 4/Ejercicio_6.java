/*
Ejercicio 6 – Deshacer/Rehacer con Pila
Implemente un programa que simule un editor de texto.
● Cada acción del usuario (escribir, borrar, copiar) se guarda en una pila de deshacer.
● Cuando el usuario presiona Deshacer, se pasa la acción a una pila de rehacer.
📌 Simule al menos 5 acciones y muestre cómo cambian las pilas al deshacer y
rehacer.
 */
import java.util.Stack;

public class Ejercicio_6 {
    private String Texto;
    private Stack<String> PilaDeshacer;
    private Stack<String> PilaRehacer;
    
    public Ejercicio_6() {
        Texto = "";
        PilaDeshacer = new Stack<>();
        PilaRehacer = new Stack<>();
    }
    
    public void escribir(String contenido) {
        PilaDeshacer.push(Texto);
        Texto = Texto + contenido;
        PilaRehacer.clear();
        System.out.println("Escribir: " + contenido);
        mostrarEstado();
    }
    
    public void borrar() {
        if (Texto.length() > 0) {
            PilaDeshacer.push(Texto);
            Texto = Texto.substring(0, Texto.length() - 1);
            PilaRehacer.clear();
            System.out.println("Borrar ultimo caracter");
            mostrarEstado();
        }
    }
    
    public void copiar() {
        PilaDeshacer.push(Texto);
        Texto = Texto + Texto;
        PilaRehacer.clear();
        System.out.println("Copiar todo el texto");
        mostrarEstado();
    }
    
    public void deshacer() {
        if (!PilaDeshacer.isEmpty()) {
            PilaRehacer.push(Texto);
            Texto = PilaDeshacer.pop();
            System.out.println("Deshacer");
            mostrarEstado();
        }
    }
    
    public void rehacer() {
        if (!PilaRehacer.isEmpty()) {
            PilaDeshacer.push(Texto);
            Texto = PilaRehacer.pop();
            System.out.println("Rehacer");
            mostrarEstado();
        }
    }
    
    public void mostrarEstado() {
        System.out.println("Texto: " + Texto);
        System.out.print("Pila Deshacer: ");
        if (PilaDeshacer.isEmpty()) {
            System.out.println("vacia");
        } else {
            System.out.println(PilaDeshacer);
        }
        System.out.print("Pila Rehacer: ");
        if (PilaRehacer.isEmpty()) {
            System.out.println("vacia");
        } else {
            System.out.println(PilaRehacer);
        }
        System.out.println("------------------------");
    }
    
    public static void main(String[] args) {
        Ejercicio_6 editor = new Ejercicio_6();
        
        System.out.println("EDITOR DE TEXTO - DESHACER/REHACER");
        System.out.println("===================================");
        System.out.println();
        
        // Accion 1
        editor.escribir("Hola");
        
        // Accion 2
        editor.escribir(" mundo");
        
        // Accion 3
        editor.borrar();
        
        // Accion 4
        editor.escribir("!");
        
        // Accion 5
        editor.copiar();
        
        System.out.println();
        System.out.println("PROBANDO DESHACER");
        System.out.println("=================");
        editor.deshacer();
        editor.deshacer();
        
        System.out.println();
        System.out.println("PROBANDO REHACER");
        System.out.println("================");
        editor.rehacer();
        editor.rehacer();
        
        System.out.println();
        System.out.println("DESHACER Y NUEVA ACCION");
        System.out.println("=======================");
        editor.deshacer();
        editor.escribir(" Java");
    }
}
