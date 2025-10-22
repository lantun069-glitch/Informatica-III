
package src.edu.informatica3.lucas_antun.practico03;

import java.util.List;
import java.util.Comparator;

/**
 * Algoritmos de ordenamiento para pedidos.
 * Implementa Insertion Sort, Shell Sort y Quick Sort.
 */
public class AlgoritmosOrdenamiento {

    /** Ordena por tiempo de preparacion usando Insertion Sort. */
    public static void ordenarPorTiempoInsercion(List<Pedido> pedidos) {
        if (pedidos == null) throw new IllegalArgumentException("Lista nula");
        if (pedidos.size() <= 1) return;
        for (int i = 1; i < pedidos.size(); i++) {
            Pedido actual = pedidos.get(i);
            int j = i - 1;
            while (j >= 0 && pedidos.get(j).getTiempoPreparacion() > actual.getTiempoPreparacion()) {
                pedidos.set(j + 1, pedidos.get(j));
                j--;
            }
            pedidos.set(j + 1, actual);
        }
    }

    /** Ordena por precio usando Shell Sort. */
    public static void ordenarPorPrecioShellSort(List<Pedido> pedidos) {
        if (pedidos == null) throw new IllegalArgumentException("Lista nula");
        if (pedidos.size() <= 1) return;
        int n = pedidos.size();
        int intervalo = 1;
        while (intervalo < n / 3) intervalo = intervalo * 3 + 1;
        while (intervalo > 0) {
            for (int i = intervalo; i < n; i++) {
                Pedido actual = pedidos.get(i);
                int j = i;
                while (j >= intervalo && pedidos.get(j - intervalo).getPrecio() > actual.getPrecio()) {
                    pedidos.set(j, pedidos.get(j - intervalo));
                    j -= intervalo;
                }
                pedidos.set(j, actual);
            }
            intervalo = (intervalo - 1) / 3;
        }
    }

    /** Ordena por nombre de cliente usando Quick Sort. */
    public static void ordenarPorNombreQuickSort(List<Pedido> pedidos) {
        if (pedidos == null) throw new IllegalArgumentException("Lista nula");
        if (pedidos.size() <= 1) return;
        quickSortRecursivo(pedidos, 0, pedidos.size() - 1);
    }

    private static void quickSortRecursivo(List<Pedido> pedidos, int inicio, int fin) {
        if (inicio < fin) {
            int pivote = particionar(pedidos, inicio, fin);
            quickSortRecursivo(pedidos, inicio, pivote - 1);
            quickSortRecursivo(pedidos, pivote + 1, fin);
        }
    }

    private static int particionar(List<Pedido> pedidos, int inicio, int fin) {
        String pivote = pedidos.get(fin).getNombreCliente().toLowerCase();
        int i = inicio - 1;
        for (int j = inicio; j < fin; j++) {
            if (pedidos.get(j).getNombreCliente().toLowerCase().compareTo(pivote) <= 0) {
                i++;
                intercambiar(pedidos, i, j);
            }
        }
        intercambiar(pedidos, i + 1, fin);
        return i + 1;
    }

    private static void intercambiar(List<Pedido> pedidos, int i, int j) {
        Pedido temp = pedidos.get(i);
        pedidos.set(i, pedidos.get(j));
        pedidos.set(j, temp);
    }

    /** Ordena usando un comparador especifico. */
    public static void ordenarConComparador(List<Pedido> pedidos, Comparator<Pedido> comparador) {
        if (pedidos == null || comparador == null) throw new IllegalArgumentException("Argumento nulo");
        pedidos.sort(comparador);
    }

    /** Comparadores para distintos criterios. */
    public static class Comparadores {
        public static final Comparator<Pedido> POR_TIEMPO = Comparator.comparingInt(Pedido::getTiempoPreparacion);
        public static final Comparator<Pedido> POR_PRECIO = Comparator.comparingDouble(Pedido::getPrecio);
        public static final Comparator<Pedido> POR_NOMBRE = Comparator.comparing(p -> p.getNombreCliente().toLowerCase());
        public static final Comparator<Pedido> POR_ID = Comparator.comparingInt(Pedido::getId);
        public static final Comparator<Pedido> POR_ESTADO = Comparator.comparing(Pedido::getEstado);
        public static final Comparator<Pedido> POR_ESTADO_Y_TIEMPO = POR_ESTADO.thenComparing(POR_TIEMPO);
        public static final Comparator<Pedido> POR_PRECIO_DESC_Y_TIEMPO = POR_PRECIO.reversed().thenComparing(POR_TIEMPO);
    }

    /** Verificadores para comprobar si la lista esta ordenada. */
    public static class Verificadores {
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
}