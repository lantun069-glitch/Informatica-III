
package src.edu.informatica3.lucas_antun.practico03;

import java.util.*;

/**
 * Medidor de tiempos para algoritmos de ordenamiento.
 */
public class MedidorTiempos {
    private final Random random;
    private static final String[] NOMBRES_CLIENTES = {
        "Ana Garcia", "Carlos Lopez", "Maria Rodriguez", "Luis Martin", "Elena Sanchez",
        "Diego Torres", "Sofia Ruiz", "Roberto Silva", "Carmen Jimenez", "Pablo Moreno",
        "Laura Alvarez", "Javier Romero", "Cristina Vargas", "Manuel Herrera", "Isabel Castro",
        "Fernando Ortiz", "Patricia Ramos", "Andres Delgado", "Lucia Guerrero", "Raul Medina"
    };
    private static final String[] DESCRIPCIONES_PEDIDOS = {
        "Pizza Margarita", "Pizza Pepperoni", "Pizza Hawaiana", "Pizza Cuatro Quesos",
        "Pizza Vegetariana", "Pizza Suprema", "Pizza Napolitana", "Pizza Barbacoa",
        "Pizza Mexicana", "Pizza Carbonara", "Pizza Prosciutto", "Pizza Funghi",
        "Pizza Marinara", "Pizza Diavola", "Pizza Capricciosa", "Pizza Quattro Stagioni"
    };

    public MedidorTiempos() {
        this.random = new Random();
    }

    /** Compara el rendimiento de algoritmos de ordenamiento. */
    public void compararAlgoritmos(int cantidadPedidos) {
        System.out.printf("\n ANALISIS DE RENDIMIENTO - %,d PEDIDOS%n", cantidadPedidos);
        System.out.println("=".repeat(60));
        List<Pedido> datosOriginales = generarPedidosAleatorios(cantidadPedidos);
        System.out.printf(" %,d pedidos generados exitosamente%n%n", cantidadPedidos);
        ResultadoMedicion resultadoInsercion = medirInsertionSort(new ArrayList<>(datosOriginales));
        ResultadoMedicion resultadoShell = medirShellSort(new ArrayList<>(datosOriginales));
        ResultadoMedicion resultadoQuick = medirQuickSort(new ArrayList<>(datosOriginales));
        ResultadoMedicion resultadoJavaSort = medirJavaSort(new ArrayList<>(datosOriginales));
        mostrarResultadosComparativos(cantidadPedidos, resultadoInsercion, resultadoShell, resultadoQuick, resultadoJavaSort);
        analizarEficiencia(cantidadPedidos, resultadoInsercion, resultadoShell, resultadoQuick, resultadoJavaSort);
    }

    private List<Pedido> generarPedidosAleatorios(int cantidad) {
        List<Pedido> pedidos = new ArrayList<>(cantidad);
        for (int i = 1; i <= cantidad; i++) {
            String cliente = NOMBRES_CLIENTES[random.nextInt(NOMBRES_CLIENTES.length)];
            String descripcion = DESCRIPCIONES_PEDIDOS[random.nextInt(DESCRIPCIONES_PEDIDOS.length)];
            if (random.nextBoolean()) cliente += " " + (random.nextInt(99) + 1);
            double precio = 10.0 + (random.nextDouble() * 40.0);
            int tiempo = 10 + random.nextInt(51);
            try {
                Pedido pedido = new Pedido(i, cliente, precio, tiempo, descripcion);
                pedidos.add(pedido);
            } catch (IllegalArgumentException e) {
                Pedido pedido = new Pedido(i, "Cliente " + i, 20.0, 20, "Pedido " + i);
                pedidos.add(pedido);
            }
        }
        return pedidos;
    }

    private ResultadoMedicion medirInsertionSort(List<Pedido> pedidos) {
        long inicio = System.nanoTime();
        AlgoritmosOrdenamiento.ordenarPorTiempoInsercion(pedidos);
        long fin = System.nanoTime();
        long tiempoNanos = fin - inicio;
        boolean estaOrdenado = AlgoritmosOrdenamiento.Verificadores.estaOrdenadoPorTiempo(pedidos);
        return new ResultadoMedicion("Insertion Sort", tiempoNanos, estaOrdenado);
    }

    private ResultadoMedicion medirShellSort(List<Pedido> pedidos) {
        long inicio = System.nanoTime();
        AlgoritmosOrdenamiento.ordenarPorPrecioShellSort(pedidos);
        long fin = System.nanoTime();
        long tiempoNanos = fin - inicio;
        boolean estaOrdenado = AlgoritmosOrdenamiento.Verificadores.estaOrdenadoPorPrecio(pedidos);
        return new ResultadoMedicion("Shell Sort", tiempoNanos, estaOrdenado);
    }

    private ResultadoMedicion medirQuickSort(List<Pedido> pedidos) {
        long inicio = System.nanoTime();
        AlgoritmosOrdenamiento.ordenarPorNombreQuickSort(pedidos);
        long fin = System.nanoTime();
        long tiempoNanos = fin - inicio;
        boolean estaOrdenado = AlgoritmosOrdenamiento.Verificadores.estaOrdenadoPorNombre(pedidos);
        return new ResultadoMedicion("Quick Sort", tiempoNanos, estaOrdenado);
    }

    private ResultadoMedicion medirJavaSort(List<Pedido> pedidos) {
        long inicio = System.nanoTime();
        AlgoritmosOrdenamiento.ordenarConComparador(pedidos, AlgoritmosOrdenamiento.Comparadores.POR_ID);
        long fin = System.nanoTime();
        long tiempoNanos = fin - inicio;
        boolean estaOrdenado = estaOrdenadoPorId(pedidos);
        return new ResultadoMedicion("Java Collections.sort()", tiempoNanos, estaOrdenado);
    }

    private boolean estaOrdenadoPorId(List<Pedido> pedidos) {
        for (int i = 1; i < pedidos.size(); i++) {
            if (pedidos.get(i - 1).getId() > pedidos.get(i).getId()) return false;
        }
        return true;
    }

    private void mostrarResultadosComparativos(int cantidadPedidos, ResultadoMedicion... resultados) {
        System.out.println("\n RESULTADOS COMPARATIVOS");
        System.out.println("=".repeat(80));
        System.out.printf("%-25s %-15s %-15s %-10s%n", "ALGORITMO", "TIEMPO (ms)", "TIEMPO (ns)", "CORRECTO");
        System.out.println("-".repeat(80));
        for (ResultadoMedicion resultado : resultados) {
            double tiempoMs = resultado.tiempoNanos / 1_000_000.0;
            String correcto = resultado.estaOrdenado ? " SI" : " NO";
            System.out.printf("%-25s %12.3f ms %12,d ns %-10s%n",
                resultado.nombreAlgoritmo,
                tiempoMs,
                resultado.tiempoNanos,
                correcto);
        }
        System.out.println("=".repeat(80));
        ResultadoMedicion masRapido = Arrays.stream(resultados)
            .min(Comparator.comparingLong(r -> r.tiempoNanos))
            .orElse(null);
        if (masRapido != null) {
            System.out.printf("ALGORITMO MAS RAPIDO: %s (%.3f ms)%n", masRapido.nombreAlgoritmo, masRapido.tiempoNanos / 1_000_000.0);
        }
    }

    private void analizarEficiencia(int cantidadPedidos, ResultadoMedicion... resultados) {
        System.out.println("\n ANALISIS DE EFICIENCIA");
        System.out.println("=".repeat(60));
        for (ResultadoMedicion resultado : resultados) {
            double tiempoMs = resultado.tiempoNanos / 1_000_000.0;
            double tiempoPorElemento = tiempoMs / cantidadPedidos;
            System.out.printf(" %s:%n", resultado.nombreAlgoritmo);
            System.out.printf("   - Tiempo total: %.3f ms%n", tiempoMs);
            System.out.printf("   - Tiempo por elemento: %.6f ms%n", tiempoPorElemento);
            System.out.printf("   - Elementos por segundo: %,.0f%n", 1000.0 / tiempoPorElemento);
            String complejidad = obtenerComplejidadTeorica(resultado.nombreAlgoritmo);
            System.out.printf("   - Complejidad teorica: %s%n", complejidad);
            System.out.println();
        }
        mostrarRecomendaciones(cantidadPedidos, resultados);
    }

    private String obtenerComplejidadTeorica(String algoritmo) {
        return switch (algoritmo) {
            case "Insertion Sort" -> "O(n^2) promedio, O(n) mejor caso";
            case "Shell Sort" -> "O(n^1.5) promedio, O(n log^2n) peor caso";
            case "Quick Sort" -> "O(n log n) promedio, O(n^2) peor caso";
            case "Java Collections.sort()" -> "O(n log n) garantizado (TimSort)";
            default -> "Desconocida";
        };
    }

    private void mostrarRecomendaciones(int cantidadPedidos, ResultadoMedicion... resultados) {
        System.out.println(" RECOMENDACIONES");
        System.out.println("-".repeat(40));
        if (cantidadPedidos < 100) {
            System.out.println("- Para listas pequenas (< 100): Insertion Sort es eficiente");
            System.out.println("- Shell Sort ofrece buen balance para listas medianas");
        } else if (cantidadPedidos < 1000) {
            System.out.println("- Para listas medianas (100-1000): Shell Sort o Quick Sort");
            System.out.println("- Java Collections.sort() es siempre confiable");
        } else {
            System.out.println("- Para listas grandes (> 1000): Quick Sort o Java Collections.sort()");
            System.out.println("- Evitar Insertion Sort en listas grandes");
        }
        System.out.println("\n CONSIDERACIONES:");
        System.out.println("- Los tiempos pueden variar segun el hardware y estado del sistema");
        System.out.println("- Para datos parcialmente ordenados, Insertion Sort puede ser eficiente");
        System.out.println("- Java Collections.sort() usa TimSort optimizado para casos reales");
        System.out.println("- Quick Sort puede degradarse a O(n^2) con datos especificos");
    }

    private static class ResultadoMedicion {
        final String nombreAlgoritmo;
        final long tiempoNanos;
        final boolean estaOrdenado;
        ResultadoMedicion(String nombreAlgoritmo, long tiempoNanos, boolean estaOrdenado) {
            this.nombreAlgoritmo = nombreAlgoritmo;
            this.tiempoNanos = tiempoNanos;
            this.estaOrdenado = estaOrdenado;
        }
    }
}