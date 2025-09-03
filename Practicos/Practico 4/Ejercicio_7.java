/*
Ejercicio 7 – Simulación de Impresora con Cola
Una impresora recibe documentos en orden de llegada.
● Cada documento tiene un número (ej: Doc1, Doc2, Doc3).
● Se procesan en orden usando una cola.
📌 Simule la llegada de 5 documentos y la impresión de 3 de ellos.
 */

public class Ejercicio_7 {
    
    // Clase para representar un nodo de la cola
    static class Nodo {
        String documento;
        Nodo siguiente;
        
        Nodo(String documento) {
            this.documento = documento;
            this.siguiente = null;
        }
    }
    
    // Clase para implementar la cola
    static class Cola {
        private Nodo frente;
        private Nodo ultimo;
        private int tamano;
        
        Cola() {
            this.frente = null;
            this.ultimo = null;
            this.tamano = 0;
        }
        
        // Metodo para agregar documento a la cola
        void encolar(String documento) {
            Nodo nuevoNodo = new Nodo(documento);
            
            if (ultimo == null) {
                frente = nuevoNodo;
                ultimo = nuevoNodo;
            } else {
                ultimo.siguiente = nuevoNodo;
                ultimo = nuevoNodo;
            }
            tamano++;
        }
        
        // Metodo para sacar documento de la cola
        String desencolar() {
            if (frente == null) {
                return null;
            }
            
            String documento = frente.documento;
            frente = frente.siguiente;
            
            if (frente == null) {
                ultimo = null;
            }
            tamano--;
            return documento;
        }
        
        // Metodo para verificar si la cola esta vacia
        boolean estaVacia() {
            return frente == null;
        }
        
        // Metodo para obtener el tamano
        int obtenerTamano() {
            return tamano;
        }
        
        // Metodo para mostrar los documentos en la cola
        String mostrarCola() {
            if (estaVacia()) {
                return "[]";
            }
            
            String resultado = "[";
            Nodo actual = frente;
            while (actual != null) {
                resultado += actual.documento;
                if (actual.siguiente != null) {
                    resultado += ", ";
                }
                actual = actual.siguiente;
            }
            resultado += "]";
            return resultado;
        }
    }
    
    public static void main(String[] args) {
        // Crear cola de impresion
        Cola colaImpresion = new Cola();
        
        // Simular llegada de 5 documentos
        System.out.println("=== LLEGADA DE DOCUMENTOS A LA IMPRESORA ===");
        for (int i = 1; i <= 5; i++) {
            String documento = "Doc" + i;
            colaImpresion.encolar(documento);
            System.out.println("Documento agregado: " + documento);
        }
        
        System.out.println("\nEstado de la cola: " + colaImpresion.mostrarCola());
        System.out.println("Total documentos en cola: " + colaImpresion.obtenerTamano());
        
        // Simular impresion de 3 documentos
        System.out.println("\n=== IMPRIMIENDO DOCUMENTOS ===");
        for (int i = 1; i <= 3; i++) {
            String docImpreso = colaImpresion.desencolar();
            if (docImpreso != null) {
                System.out.println("Imprimiendo: " + docImpreso + " ... Completado");
            }
        }
        
        // Mostrar estado final
        System.out.println("\n=== ESTADO FINAL ===");
        System.out.println("Documentos restantes en cola: " + colaImpresion.mostrarCola());
        System.out.println("Total documentos pendientes: " + colaImpresion.obtenerTamano());
    }
}