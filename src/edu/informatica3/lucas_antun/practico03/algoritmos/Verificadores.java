package edu.informatica3.lucas_antun.practico03.algoritmos;
import edu.informatica3.lucas_antun.practico03.modelo.*;
import java.util.Comparator;
import java.util.List;
/**
 * Verificadores para comprobar si una lista de Pedidos está ordenada.
 */
public class Verificadores {
    public static boolean estaOrdenadoPorTiempo(List<Pedido> pedidos) {
        return estaOrdenado(pedidos, Comparadores.POR_TIEMPO);
    }
    
    public static boolean estaOrdenadoPorPrecio(List<Pedido> pedidos) {
        return estaOrdenado(pedidos, Comparadores.POR_PRECIO);
    }
    
    public static boolean estaOrdenadoPorNombre(List<Pedido> pedidos) {
        return estaOrdenado(pedidos, Comparadores.POR_NOMBRE);
    }
    
    private static boolean estaOrdenado(List<Pedido> pedidos, Comparator<Pedido> comparador) {
        if (pedidos == null || pedidos.size() <= 1) return true;
        for (int i = 1; i < pedidos.size(); i++) {
            if (comparador.compare(pedidos.get(i - 1), pedidos.get(i)) > 0) return false;
        }
        return true;
    }
}
