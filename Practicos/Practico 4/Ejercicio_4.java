/*
Ejercicio 4 – Simulación de Turnos con Cola
Implemente un programa que utilice ColaArreglo para simular una fila de espera en un
banco.
● Los clientes llegan en el orden: Ana, Luis, Marta, Pedro.
● Se atienden los dos primeros clientes.
📌 Mostrar la cola antes y después de atender.
 */

public class Ejercicio_4 {
    
    static class ColaArreglo {
        private String[] cola;
        private int frente;
        private int atras;
        private int tamano;
        
        public ColaArreglo(int capacidad) {
            cola = new String[capacidad];
            frente = 0;
            atras = -1;
            tamano = 0;
        }
        
        public void encolar(String cliente) {
            if (tamano < cola.length) {
                atras++;
                cola[atras] = cliente;
                tamano++;
            }
        }
        
        public String desencolar() {
            if (tamano > 0) {
                String cliente = cola[frente];
                frente++;
                tamano--;
                return cliente;
            }
            return null;
        }
        
        public void mostrar() {
            if (tamano == 0) {
                System.out.println("Cola vacía");
                return;
            }
            
            System.out.print("Cola: ");
            for (int i = frente; i <= atras; i++) {
                System.out.print(cola[i] + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        ColaArreglo banco = new ColaArreglo(10);
        
        // Llegan los clientes
        banco.encolar("Ana");
        banco.encolar("Luis");
        banco.encolar("Marta");
        banco.encolar("Pedro");
        
        System.out.println("ANTES de atender:");
        banco.mostrar();
        
        // Se atienden los dos primeros
        System.out.println("\nAtendiendo a " + banco.desencolar());
        System.out.println("Atendiendo a " + banco.desencolar());
        
        System.out.println("\nDESPUES de atender:");
        banco.mostrar();
    }
}