package edu.informatica3.lucas_antun.practico03;

import java.util.Scanner;
import java.util.List;

/**
 * Aplicacion principal del sistema de pizzeria.
 */
public class MainPizzeria {
    
    private final Scanner scanner;
    private final SistemaPizzeria pizzeria;
    private final MedidorTiempos medidorTiempos;
    
    /**
     * Constructor de la aplicacion.
     */
    public MainPizzeria() {
        this.scanner = new Scanner(System.in);
        this.pizzeria = new SistemaPizzeria();
        this.medidorTiempos = new MedidorTiempos();
    }
    
    /**
     * Punto de entrada principal de la aplicacion.
     * 
     * @param args argumentos de linea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        MainPizzeria app = new MainPizzeria();
        app.ejecutar();
    }
    
    /**
     * Ejecuta el bucle principal de la aplicacion.
     */
    public void ejecutar() {
        System.out.println("SISTEMA DE GESTION DE PIZZERIA");
        
        boolean continuar = true;
        while (continuar) {
            mostrarMenuPrincipal();
            int opcion = leerOpcion();
            continuar = procesarOpcion(opcion);
            
            if (continuar) {
                esperarEnter();
            }
        }
        
        System.out.println("Hasta pronto!");
        scanner.close();
    }
    
    private void mostrarBienvenida() {
        // Removed verbose welcome message
    }
    
    private void mostrarMenuPrincipal() {
        System.out.println("\nMENU PRINCIPAL");
        System.out.println("1. Agregar pedido");
        System.out.println("2. Eliminar pedido");
        System.out.println("3. Actualizar pedido");
        System.out.println("4. Cambiar estado de pedido");
        System.out.println("5. Mostrar todos los pedidos");
        System.out.println("6. Buscar pedidos");
        System.out.println("7. Mostrar estadisticas");
        System.out.println("8. Ordenar pedidos");
        System.out.println("9. Comparar tiempos de algoritmos");
        System.out.println("10. Cargar datos de prueba");
        System.out.println("11. Limpiar todos los pedidos");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opcion: ");
    }
    
    private int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private boolean procesarOpcion(int opcion) {
        switch (opcion) {
            case 1: agregarPedido(); break;
            case 2: eliminarPedido(); break;
            case 3: actualizarPedido(); break;
            case 4: cambiarEstadoPedido(); break;
            case 5: pizzeria.mostrarPedidos(); break;
            case 6: menuBusquedas(); break;
            case 7: pizzeria.mostrarEstadisticas(); break;
            case 8: menuOrdenamiento(); break;
            case 9: menuAnalisisRendimiento(); break;
            case 10: pizzeria.cargarPedidosPrueba(); break;
            case 11: confirmarLimpiezaTotal(); break;
            case 0: return false;
            default: System.out.println(" Opcion no valida. Intente nuevamente.");
        }
        return true;
    }
    
    private void agregarPedido() {
        System.out.println("\n AGREGAR NUEVO PEDIDO");
        System.out.println("─".repeat(25));
        
        try {
            System.out.print("Nombre del cliente: ");
            String nombre = scanner.nextLine().trim();
            
            System.out.print(" Precio del pedido: $");
            double precio = Double.parseDouble(scanner.nextLine().trim());
            
            System.out.print(" Tiempo de preparacion (minutos): ");
            int tiempo = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print(" Descripcion del pedido: ");
            String descripcion = scanner.nextLine().trim();
            
            pizzeria.agregarPedido(nombre, precio, tiempo, descripcion);
            
        } catch (NumberFormatException e) {
            System.out.println(" Error: Formato numerico invalido");
        } catch (IllegalArgumentException e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }
    
    private void eliminarPedido() {
        if (pizzeria.estaVacio()) {
            System.out.println("\n No hay pedidos para eliminar");
            return;
        }
        
        System.out.println("\n ELIMINAR PEDIDO");
        System.out.println("─".repeat(18));
        
        pizzeria.mostrarPedidos();
        System.out.print("\n Ingrese el ID del pedido a eliminar: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            pizzeria.eliminarPedido(id);
        } catch (NumberFormatException e) {
            System.out.println(" Error: ID invalido");
        }
    }
    
    private void actualizarPedido() {
        if (pizzeria.estaVacio()) {
            System.out.println("\n No hay pedidos para actualizar");
            return;
        }
        
        System.out.println("\n ACTUALIZAR PEDIDO");
        System.out.println("─".repeat(20));
        
        pizzeria.mostrarPedidos();
        
        try {
            System.out.print("\n ID del pedido a actualizar: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print(" Nuevo precio: $");
            double precio = Double.parseDouble(scanner.nextLine().trim());
            
            System.out.print(" Nuevo tiempo (minutos): ");
            int tiempo = Integer.parseInt(scanner.nextLine().trim());
            
            pizzeria.actualizarPedido(id, precio, tiempo);
            
        } catch (NumberFormatException e) {
            System.out.println(" Error: Formato numerico invalido");
        }
    }
    
    private void cambiarEstadoPedido() {
        if (pizzeria.estaVacio()) {
            System.out.println("\n No hay pedidos para cambiar estado");
            return;
        }
        
        System.out.println("\n CAMBIAR ESTADO DE PEDIDO");
        System.out.println("─".repeat(30));
        
        pizzeria.mostrarPedidos();
        
        try {
            System.out.print("\n ID del pedido: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.println("\n Estados disponibles:");
            System.out.println("1. Pendiente");
            System.out.println("2. En preparacion");
            System.out.println("3. Completado");
            System.out.print("\n Seleccione el nuevo estado: ");
            
            int opcionEstado = Integer.parseInt(scanner.nextLine().trim());
            
            String nuevoEstado = switch (opcionEstado) {
                case 1 -> "pendiente";
                case 2 -> "en preparacion";
                case 3 -> "completado";
                default -> null;
            };
            
            if (nuevoEstado != null) {
                pizzeria.cambiarEstado(id, nuevoEstado);
            } else {
                System.out.println(" Opcion de estado invalida");
            }
            
        } catch (NumberFormatException e) {
            System.out.println(" Error: Entrada invalida");
        }
    }
    
    private void menuBusquedas() {
        System.out.println("\n MENU DE BUSQUEDAS");
        System.out.println("─".repeat(22));
        System.out.println("1. Buscar por cliente");
        System.out.println("2. Buscar por estado");
        System.out.println("3. Buscar por rango de precio");
        System.out.println("4. Mostrar pedido especifico");
        System.out.print("\n Seleccione tipo de busqueda: ");
        
        int opcion = leerOpcion();
        
        switch (opcion) {
            case 1 -> buscarPorCliente();
            case 2 -> buscarPorEstado();
            case 3 -> buscarPorRangoPrecio();
            case 4 -> mostrarPedidoEspecifico();
            default -> System.out.println(" Opcion no valida");
        }
    }
    
    private void buscarPorCliente() {
        System.out.print("\nIngrese nombre del cliente (busqueda parcial): ");
        String nombre = scanner.nextLine().trim();
        
        List<Pedido> resultados = pizzeria.buscarPorCliente(nombre);
        mostrarResultadosBusqueda(resultados, "cliente '" + nombre + "'");
    }
    
    private void buscarPorEstado() {
        System.out.println("\n Estados disponibles:");
        System.out.println("1. Pendiente");
        System.out.println("2. En preparacion");
        System.out.println("3. Completado");
        System.out.print("\n Seleccione estado: ");
        
        int opcion = leerOpcion();
        Pedido.EstadoPedido estado = switch (opcion) {
            case 1 -> Pedido.EstadoPedido.PENDIENTE;
            case 2 -> Pedido.EstadoPedido.EN_PREPARACION;
            case 3 -> Pedido.EstadoPedido.COMPLETADO;
            default -> null;
        };
        
        if (estado != null) {
            List<Pedido> resultados = pizzeria.buscarPorEstado(estado);
            mostrarResultadosBusqueda(resultados, "estado '" + estado.getDescripcion() + "'");
        } else {
            System.out.println(" Estado invalido");
        }
    }
    
    private void buscarPorRangoPrecio() {
        try {
            System.out.print("\n Precio minimo: $");
            double min = Double.parseDouble(scanner.nextLine().trim());
            
            System.out.print(" Precio maximo: $");
            double max = Double.parseDouble(scanner.nextLine().trim());
            
            List<Pedido> resultados = pizzeria.buscarPorRangoPrecio(min, max);
            mostrarResultadosBusqueda(resultados, String.format("rango de precio $%.2f - $%.2f", min, max));
            
        } catch (NumberFormatException e) {
            System.out.println(" Error: Formato de precio invalido");
        }
    }
    
    private void mostrarPedidoEspecifico() {
        try {
            System.out.print("\n ID del pedido: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            pizzeria.mostrarPedidoDetallado(id);
        } catch (NumberFormatException e) {
            System.out.println(" Error: ID invalido");
        }
    }
    
    private void mostrarResultadosBusqueda(List<Pedido> resultados, String criterio) {
        System.out.printf("\n Resultados de busqueda por %s:%n", criterio);
        
        if (resultados.isEmpty()) {
            System.out.println(" No se encontraron pedidos que coincidan con el criterio");
        } else {
            System.out.println("─".repeat(50));
            for (int i = 0; i < resultados.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, resultados.get(i));
            }
            System.out.printf("\n Se encontraron %d pedido(s)%n", resultados.size());
        }
    }
    
    private void menuOrdenamiento() {
        if (pizzeria.estaVacio()) {
            System.out.println("\n No hay pedidos para ordenar");
            return;
        }
        
        System.out.println("\n MENU DE ORDENAMIENTO");
        System.out.println("─".repeat(25));
        System.out.println("1. Ordenar por tiempo (Insertion Sort)");
        System.out.println("2. Ordenar por precio (Shell Sort)");
        System.out.println("3. Ordenar por nombre (Quick Sort)");
        System.out.println("4. Ordenar por estado y tiempo");
        System.out.println("5. Ordenar por precio descendente y tiempo");
        System.out.print("\n Seleccione algoritmo: ");
        
        int opcion = leerOpcion();
        
        switch (opcion) {
            case 1:
                pizzeria.ordenarPorTiempo();
                pizzeria.mostrarPedidos();
                break;
            case 2:
                pizzeria.ordenarPorPrecio();
                pizzeria.mostrarPedidos();
                break;
            case 3:
                pizzeria.ordenarPorNombre();
                pizzeria.mostrarPedidos();
                break;
            case 4:
                pizzeria.ordenarConComparador(
                    AlgoritmosOrdenamiento.Comparadores.POR_ESTADO_Y_TIEMPO,
                    "estado y tiempo"
                );
                pizzeria.mostrarPedidos();
                break;
            case 5:
                pizzeria.ordenarConComparador(
                    AlgoritmosOrdenamiento.Comparadores.POR_PRECIO_DESC_Y_TIEMPO,
                    "precio descendente y tiempo"
                );
                pizzeria.mostrarPedidos();
                break;
            default:
                System.out.println(" Opcion no valida");
        }
    }
    
    private void menuAnalisisRendimiento() {
        System.out.println("\n ANALISIS DE RENDIMIENTO");
        System.out.println("─".repeat(30));
        System.out.println("1. Comparar con 100 pedidos");
        System.out.println("2. Comparar con 1,000 pedidos");
        System.out.println("3. Comparar con 10,000 pedidos");
        System.out.println("4. Analisis personalizado");
        System.out.print("\\n Seleccione opcion: ");
        
        int opcion = leerOpcion();
        
        switch (opcion) {
            case 1 -> medidorTiempos.compararAlgoritmos(100);
            case 2 -> medidorTiempos.compararAlgoritmos(1000);
            case 3 -> medidorTiempos.compararAlgoritmos(10000);
            case 4 -> analisisPersonalizado();
            default -> System.out.println(" Opcion no valida");
        }
    }
    
    private void analisisPersonalizado() {
        try {
            System.out.print("\n Numero de pedidos para el analisis: ");
            int cantidad = Integer.parseInt(scanner.nextLine().trim());
            
            if (cantidad <= 0) {
                System.out.println(" La cantidad debe ser positiva");
                return;
            }
            
            medidorTiempos.compararAlgoritmos(cantidad);
            
        } catch (NumberFormatException e) {
            System.out.println(" Error: Cantidad invalida");
        }
    }
    
    private void confirmarLimpiezaTotal() {
        if (pizzeria.estaVacio()) {
            System.out.println("\n No hay pedidos para limpiar");
            return;
        }
        
        System.out.println("\n CONFIRMACION DE LIMPIEZA");
        System.out.println("─".repeat(30));
        System.out.printf("Esta a punto de eliminar %d pedido(s).%n", pizzeria.obtenerTotalPedidos());
        System.out.print("\nEsta seguro? (S/N): ");
        
        String respuesta = scanner.nextLine().trim().toUpperCase();
        
        if ("S".equals(respuesta) || "SI".equals(respuesta) || "SI".equals(respuesta)) {
            pizzeria.limpiarTodos();
        } else {
            System.out.println(" Operacion cancelada");
        }
    }
    
    // despedirse eliminado
    
    private void esperarEnter() {
        System.out.println("\n Presione Enter para continuar...");
        scanner.nextLine();
        limpiarPantalla();
    }
    
    private void limpiarPantalla() {
        for (int i = 0; i < 2; i++) {
            System.out.println();
        }
    }
}