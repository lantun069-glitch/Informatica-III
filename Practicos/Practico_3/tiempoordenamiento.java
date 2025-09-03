package Practicos.Practico_3;
import java.util.*;

public class tiempoordenamiento {
    
    public void medirtiempos(int cantidad) {
        List<pedido> pedidos = generarpedidos(cantidad);
        
        List<pedido> copia1 = new ArrayList<>(pedidos);
        List<pedido> copia2 = new ArrayList<>(pedidos);
        List<pedido> copia3 = new ArrayList<>(pedidos);
        
        ordenador ord = new ordenador();
        
        long inicio = System.nanoTime();
        ord.insercion(copia1);
        long fin = System.nanoTime();
        long tiempoinsercion = (fin - inicio) / 1000000;
        
        inicio = System.nanoTime();
        ord.shellsort(copia2);
        fin = System.nanoTime();
        long tiemposhell = (fin - inicio) / 1000000;
        
        inicio = System.nanoTime();
        ord.quicksort(copia3, 0, copia3.size() - 1);
        fin = System.nanoTime();
        long tiempoquick = (fin - inicio) / 1000000;
        
        System.out.println("\ntiempos con " + cantidad + " pedidos:");
        System.out.println("insercion: " + tiempoinsercion + " ms");
        System.out.println("shellsort: " + tiemposhell + " ms");
        System.out.println("quicksort: " + tiempoquick + " ms");
    }
    
    private List<pedido> generarpedidos(int cantidad) {
        List<pedido> lista = new ArrayList<>();
        String[] nombres = {"juan", "maria", "pedro", "ana", "luis", "sofia", "carlos", "laura"};
        
        for (int i = 0; i < cantidad; i++) {
            String nombre = nombres[(int)(Math.random() * nombres.length)];
            double precio = 10 + Math.random() * 40;
            int tiempo = 10 + (int)(Math.random() * 50);
            String desc = "pizza " + (i + 1);
            
            lista.add(new pedido(i + 1, nombre, precio, tiempo, desc));
        }
        
        return lista;
    }
}