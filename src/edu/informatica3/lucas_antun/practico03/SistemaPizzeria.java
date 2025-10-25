package edu.informatica3.lucas_antun.practico03;

import java.util.*;

/**
 * Sistema de gestion de pedidos para pizzeria.
 */
public class SistemaPizzeria {
    
    /** Lista de todos los pedidos */
    private final List<Pedido> pedidos;
    
    /** Contador para generar IDs unicos */
    private int contadorId;
    
    /** Mapa para acceso rapido por ID */
    private final Map<Integer, Pedido> indicePorId;
    
    /**
     * Constructor que inicializa el sistema de pizzeria.
     */
    public SistemaPizzeria() {
        this.pedidos = new ArrayList<>();
        this.contadorId = 1;
        this.indicePorId = new HashMap<>();
    }
    
    /**
     * Agrega un nuevo pedido al sistema.
     * 
     * @param nombreCliente nombre del cliente
     * @param precio precio del pedido
     * @param tiempoPreparacion tiempo estimado en minutos
     * @param descripcion descripcion del pedido
     * @return el ID del pedido creado
     * @throws IllegalArgumentException si algun parametro es invalido
     */
    public int agregarPedido(String nombreCliente, double precio, int tiempoPreparacion, String descripcion) {
        Pedido nuevoPedido = new Pedido(contadorId, nombreCliente, precio, tiempoPreparacion, descripcion);
        pedidos.add(nuevoPedido);
        indicePorId.put(contadorId, nuevoPedido);
        
        System.out.printf(" Pedido #%d agregado exitosamente%n", contadorId);
        return contadorId++;
    }
    
    /**
     * Elimina un pedido por su ID.
     * 
     * @param id ID del pedido a eliminar
     * @return true si el pedido fue eliminado, false si no se encontro
     */
    public boolean eliminarPedido(int id) {
        Pedido pedido = indicePorId.get(id);
        if (pedido != null) {
            pedidos.remove(pedido);
            indicePorId.remove(id);
            System.out.printf(" Pedido #%d eliminado exitosamente%n", id);
            return true;
        }
        
        System.out.printf(" No se encontro el pedido #%d%n", id);
        return false;
    }
    
    /**
     * Actualiza el precio y tiempo de preparacion de un pedido.
     * 
     * @param id ID del pedido
     * @param nuevoPrecio nuevo precio
     * @param nuevoTiempo nuevo tiempo de preparacion
     * @return true si se actualizo correctamente, false si no se encontro
     */
    public boolean actualizarPedido(int id, double nuevoPrecio, int nuevoTiempo) {
        Pedido pedido = buscarPorId(id);
        if (pedido != null) {
            try {
                pedido.setPrecio(nuevoPrecio);
                pedido.setTiempoPreparacion(nuevoTiempo);
                System.out.printf(" Pedido #%d actualizado exitosamente%n", id);
                return true;
            } catch (IllegalArgumentException e) {
                System.out.printf(" Error al actualizar pedido #%d: %s%n", id, e.getMessage());
                return false;
            }
        }
        
        System.out.printf(" No se encontro el pedido #%d%n", id);
        return false;
    }
    
    /**
     * Cambia el estado de un pedido.
     * 
     * @param id ID del pedido
     * @param nuevoEstado nuevo estado del pedido
     * @return true si se cambio correctamente, false si no se encontro
     */
    public boolean cambiarEstado(int id, String nuevoEstado) {
        Pedido pedido = buscarPorId(id);
        if (pedido != null) {
            String estadoAnterior = pedido.getEstadoString();
            pedido.setEstado(nuevoEstado);
            System.out.printf(" Estado del pedido #%d cambiado de '%s' a '%s'%n", 
                id, estadoAnterior, pedido.getEstadoString());
            return true;
        }
        
        System.out.printf(" No se encontro el pedido #%d%n", id);
        return false;
    }
    
    /**
     * Cambia el estado de un pedido usando enum.
     * 
     * @param id ID del pedido
     * @param nuevoEstado nuevo estado del pedido
     * @return true si se cambio correctamente, false si no se encontro
     */
    public boolean cambiarEstado(int id, Pedido.EstadoPedido nuevoEstado) {
        Pedido pedido = buscarPorId(id);
        if (pedido != null) {
            Pedido.EstadoPedido estadoAnterior = pedido.getEstado();
            pedido.setEstado(nuevoEstado);
            System.out.printf(" Estado del pedido #%d cambiado de '%s' a '%s'%n", 
                id, estadoAnterior.getDescripcion(), nuevoEstado.getDescripcion());
            return true;
        }
        
        System.out.printf(" No se encontro el pedido #%d%n", id);
        return false;
    }
    
    /**
     * Busca un pedido por su ID.
     * 
     * @param id ID del pedido a buscar
     * @return el pedido encontrado o null si no existe
     */
    public Pedido buscarPorId(int id) {
        return indicePorId.get(id);
    }
    
    /**
     * Busca pedidos por nombre de cliente (busqueda parcial, insensible a mayusculas).
     * 
     * @param nombreCliente nombre o parte del nombre del cliente
     * @return lista de pedidos que coinciden con el criterio
     */
    public List<Pedido> buscarPorCliente(String nombreCliente) {
        if (nombreCliente == null || nombreCliente.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        String nombreBusqueda = nombreCliente.toLowerCase().trim();
        return pedidos.stream()
            .filter(p -> p.getNombreCliente().toLowerCase().contains(nombreBusqueda))
            .collect(java.util.stream.Collectors.toList());
    }
    
    /**
     * Busca pedidos por estado.
     * 
     * @param estado estado a buscar
     * @return lista de pedidos en el estado especificado
     */
    public List<Pedido> buscarPorEstado(Pedido.EstadoPedido estado) {
        return pedidos.stream()
            .filter(p -> p.getEstado() == estado)
            .collect(java.util.stream.Collectors.toList());
    }
    
    /**
     * Busca pedidos en un rango de precios.
     * 
     * @param precioMinimo precio minimo (inclusive)
     * @param precioMaximo precio maximo (inclusive)
     * @return lista de pedidos en el rango de precios
     */
    public List<Pedido> buscarPorRangoPrecio(double precioMinimo, double precioMaximo) {
        return pedidos.stream()
            .filter(p -> p.getPrecio() >= precioMinimo && p.getPrecio() <= precioMaximo)
            .collect(java.util.stream.Collectors.toList());
    }
    
    // Metodos de ordenamiento
    public void ordenarPorTiempo() {
        if (pedidos.isEmpty()) {
            System.out.println(" No hay pedidos para ordenar");
            return;
        }
        
        AlgoritmosOrdenamiento.ordenarPorTiempoInsercion(pedidos);
        System.out.println(" Pedidos ordenados por tiempo de preparacion (Insercion)");
    }
    
    public void ordenarPorPrecio() {
        if (pedidos.isEmpty()) {
            System.out.println(" No hay pedidos para ordenar");
            return;
        }
        
        AlgoritmosOrdenamiento.ordenarPorPrecioShellSort(pedidos);
        System.out.println(" Pedidos ordenados por precio (Shell Sort)");
    }
    
    public void ordenarPorNombre() {
        if (pedidos.isEmpty()) {
            System.out.println(" No hay pedidos para ordenar");
            return;
        }
        
        AlgoritmosOrdenamiento.ordenarPorNombreQuickSort(pedidos);
        System.out.println(" Pedidos ordenados por nombre de cliente (Quick Sort)");
    }
    
    /**
     * Ordena los pedidos usando un comparador personalizado.
     * 
     * @param comparador el comparador a utilizar
     * @param descripcion descripcion del criterio de ordenamiento
     */
    public void ordenarConComparador(Comparator<Pedido> comparador, String descripcion) {
        if (pedidos.isEmpty()) {
            System.out.println(" No hay pedidos para ordenar");
            return;
        }
        
        AlgoritmosOrdenamiento.ordenarConComparador(pedidos, comparador);
        System.out.printf(" Pedidos ordenados por %s%n", descripcion);
    }
    
    /**
     * Muestra todos los pedidos del sistema.
     */
    public void mostrarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println(" No hay pedidos registrados en el sistema");
            return;
        }
        
        System.out.println("\\n" + "=".repeat(80));
        System.out.printf("                    LISTA DE PEDIDOS (%d total)%n", pedidos.size());
        System.out.println("=".repeat(80));
        
        for (int i = 0; i < pedidos.size(); i++) {
            System.out.printf("%3d. %s%n", i + 1, pedidos.get(i));
        }
        
        System.out.println("=".repeat(80));
    }
    
    /**
     * Muestra informacion detallada de un pedido especifico.
     * 
     * @param id ID del pedido a mostrar
     */
    public void mostrarPedidoDetallado(int id) {
        Pedido pedido = buscarPorId(id);
        if (pedido != null) {
            System.out.println("\\n" + pedido.toStringDetallado());
        } else {
            System.out.printf(" No se encontro el pedido #%d%n", id);
        }
    }
    
    /**
     * Genera y muestra estadisticas del sistema.
     */
    public void mostrarEstadisticas() {
        System.out.println("\\n" + "╔".repeat(50));
        System.out.println("                  ESTADISTICAS");
        System.out.println("╚".repeat(50));
        
        System.out.printf(" Total de pedidos: %d%n", pedidos.size());
        
        long pendientes = contarPorEstado(Pedido.EstadoPedido.PENDIENTE);
        long enPreparacion = contarPorEstado(Pedido.EstadoPedido.EN_PREPARACION);
        long completados = contarPorEstado(Pedido.EstadoPedido.COMPLETADO);
        
        System.out.printf(" Pendientes: %d%n", pendientes);
        System.out.printf(" En preparacion: %d%n", enPreparacion);
        System.out.printf(" Completados: %d%n", completados);
        
        if (!pedidos.isEmpty()) {
            double precioPromedio = calcularPrecioPromedio();
            double tiempoPromedio = calcularTiempoPromedio();
            
            System.out.printf(" Precio promedio: $%.2f%n", precioPromedio);
            System.out.printf(" Tiempo promedio: %.1f minutos%n", tiempoPromedio);
            System.out.printf("💵 Ingresos totales: $%.2f%n", calcularIngresosCompletados());
        }
        
        System.out.println("─".repeat(50));
    }
    
    /**
     * Cuenta pedidos por estado.
     */
    public long contarPorEstado(Pedido.EstadoPedido estado) {
        return pedidos.stream().filter(p -> p.getEstado() == estado).count();
    }
    
    /**
     * Calcula el precio promedio de todos los pedidos.
     */
    public double calcularPrecioPromedio() {
        return pedidos.isEmpty() ? 0.0 : 
            pedidos.stream().mapToDouble(Pedido::getPrecio).average().orElse(0.0);
    }
    
    /**
     * Calcula el tiempo promedio de preparacion.
     */
    public double calcularTiempoPromedio() {
        return pedidos.isEmpty() ? 0.0 : 
            pedidos.stream().mapToInt(Pedido::getTiempoPreparacion).average().orElse(0.0);
    }
    
    /**
     * Calcula los ingresos totales de pedidos completados.
     */
    public double calcularIngresosCompletados() {
        return pedidos.stream()
            .filter(Pedido::estaCompletado)
            .mapToDouble(Pedido::getPrecio)
            .sum();
    }
    
    /**
     * Obtiene una copia de la lista de pedidos.
     */
    public List<Pedido> obtenerPedidos() {
        return new ArrayList<>(pedidos);
    }
    
    /**
     * Obtiene el numero total de pedidos.
     */
    public int obtenerTotalPedidos() {
        return pedidos.size();
    }
    
    /**
     * Verifica si el sistema esta vacio.
     */
    public boolean estaVacio() {
        return pedidos.isEmpty();
    }
    
    /**
     * Limpia todos los pedidos del sistema.
     */
    public void limpiarTodos() {
        pedidos.clear();
        indicePorId.clear();
        contadorId = 1;
        System.out.println(" Todos los pedidos han sido eliminados del sistema");
    }
    
    /**
     * Carga pedidos de prueba para demostracion.
     */
    public void cargarPedidosPrueba() {
        System.out.println("\\n Cargando pedidos de prueba...");
        
        agregarPedido("Carlos Mendoza", 25.50, 20, "Pizza Margarita grande");
        agregarPedido("Ana Garcia", 35.00, 35, "Pizza Pepperoni mediana + bebida");
        agregarPedido("Luis Rodriguez", 18.75, 15, "Pizza Hawaiana pequena");
        agregarPedido("Maria Lopez", 42.00, 45, "2 Pizzas medianas especiales");
        agregarPedido("Sofia Martin", 22.50, 25, "Pizza Vegetariana mediana");
        agregarPedido("Diego Torres", 28.00, 30, "Pizza Cuatro Quesos grande");
        agregarPedido("Elena Ruiz", 33.75, 40, "Pizza Suprema mediana");
        agregarPedido("Roberto Silva", 19.25, 18, "Pizza Napolitana pequena");
        
        // Cambiar algunos estados para variedad
        cambiarEstado(2, Pedido.EstadoPedido.EN_PREPARACION);
        cambiarEstado(4, Pedido.EstadoPedido.COMPLETADO);
        cambiarEstado(6, Pedido.EstadoPedido.EN_PREPARACION);
        cambiarEstado(8, Pedido.EstadoPedido.COMPLETADO);
        
        System.out.printf(" Se cargaron %d pedidos de prueba%n", 8);
    }
}