/*
Ejercicio 8 – Cola Circular para Gestión de Llamadas
Implemente una cola circular para gestionar llamadas en un call center.
● La cola tiene capacidad máxima de 5 llamadas.
● Cuando llega una nueva llamada y la cola está llena, sobrescribe la más antigua.
📌 Simule la llegada de 8 llamadas y muestre el estado final de la cola.
 */

public class Ejercicio_8 {
    
    static class ColaCircular {
        private String[] llamadas;
        private int capacidad;
        private int frente;
        private int siguiente;
        private int cantidad;
        
        public ColaCircular(int capacidad) {
            this.capacidad = capacidad;
            this.llamadas = new String[capacidad];
            this.frente = 0;
            this.siguiente = 0;
            this.cantidad = 0;
        }
        
        public void agregarLlamada(String llamada) {
            if (cantidad < capacidad) {
                // Todavia hay espacio en la cola
                llamadas[siguiente] = llamada;
                siguiente = (siguiente + 1) % capacidad;
                cantidad++;
                System.out.println("Llamada agregada: " + llamada);
            } else {
                // Cola llena, sobrescribir la mas antigua
                System.out.println("Cola llena. Sobrescribiendo llamada mas antigua: " + llamadas[frente]);
                llamadas[siguiente] = llamada;
                siguiente = (siguiente + 1) % capacidad;
                frente = (frente + 1) % capacidad;
                System.out.println("Llamada agregada: " + llamada);
            }
        }
        
        public void mostrarEstado() {
            System.out.println("\n=== Estado Final de la Cola ===");
            if (cantidad == 0) {
                System.out.println("La cola esta vacia");
                return;
            }
            
            System.out.println("Llamadas en la cola (de mas antigua a mas reciente):");
            int indice = frente;
            for (int i = 0; i < cantidad; i++) {
                System.out.println((i + 1) + ". " + llamadas[indice]);
                indice = (indice + 1) % capacidad;
            }
            System.out.println("Total de llamadas en cola: " + cantidad);
        }
    }
    
    public static void main(String[] args) {
        // Crear cola circular con capacidad de 5 llamadas
        ColaCircular cola = new ColaCircular(5);
        
        System.out.println("=== Simulacion de Call Center ===");
        System.out.println("Capacidad maxima de la cola: 5 llamadas\n");
        
        // Simular llegada de 8 llamadas
        String[] nuevasLlamadas = {
            "Llamada-001 Cliente Juan",
            "Llamada-002 Cliente Maria",
            "Llamada-003 Cliente Pedro",
            "Llamada-004 Cliente Ana",
            "Llamada-005 Cliente Luis",
            "Llamada-006 Cliente Sofia",
            "Llamada-007 Cliente Carlos",
            "Llamada-008 Cliente Elena"
        };
        
        for (String llamada : nuevasLlamadas) {
            cola.agregarLlamada(llamada);
        }
        
        // Mostrar estado final de la cola
        cola.mostrarEstado();
    }
}
