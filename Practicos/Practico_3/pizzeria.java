package Practicos.Practico_3;
import java.util.*;

public class pizzeria {
    private List<pedido> pedidos;
    private int ultimoid;
    private ordenador ordenador;
    
    public pizzeria() {
        pedidos = new ArrayList<>();
        ultimoid = 1;
        ordenador = new ordenador();
    }
    
    public void agregarpedido(String cliente, double precio, int tiempo, String descripcion) {
        pedido nuevo = new pedido(ultimoid++, cliente, precio, tiempo, descripcion);
        pedidos.add(nuevo);
        System.out.println("pedido agregado con id: " + nuevo.getid());
    }
    
    public void eliminarpedido(int id) {
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getid() == id) {
                pedidos.remove(i);
                System.out.println("pedido eliminado");
                return;
            }
        }
        System.out.println("no se encontro el pedido");
    }
    
    public void actualizarpedido(int id, double nuevoprecio, int nuevotiempo) {
        for (pedido p : pedidos) {
            if (p.getid() == id) {
                p.setprecio(nuevoprecio);
                p.settiempo(nuevotiempo);
                System.out.println("pedido actualizado");
                return;
            }
        }
        System.out.println("no se encontro el pedido");
    }
    
    public void cambiarestado(int id, String estado) {
        for (pedido p : pedidos) {
            if (p.getid() == id) {
                p.setestado(estado);
                System.out.println("estado cambiado a " + estado);
                return;
            }
        }
        System.out.println("no se encontro el pedido");
    }
    
    public void ordenarportiempo() {
        if (pedidos.size() == 0) {
            System.out.println("no hay pedidos");
            return;
        }
        ordenador.insercion(pedidos);
        System.out.println("pedidos ordenados por tiempo");
    }
    
    public void ordenarporprecio() {
        if (pedidos.size() == 0) {
            System.out.println("no hay pedidos");
            return;
        }
        ordenador.shellsort(pedidos);
        System.out.println("pedidos ordenados por precio");
    }
    
    public void ordenarpornombre() {
        if (pedidos.size() == 0) {
            System.out.println("no hay pedidos");
            return;
        }
        ordenador.quicksort(pedidos, 0, pedidos.size() - 1);
        System.out.println("pedidos ordenados por nombre");
    }
    
    public void mostrarpedidos() {
        if (pedidos.size() == 0) {
            System.out.println("no hay pedidos");
            return;
        }
        
        System.out.println("\nlista de pedidos:");
        for (pedido p : pedidos) {
            System.out.println(p);
        }
    }
    
    public void cargarpedidosprueba() {
        agregarpedido("carlos", 25.50, 20, "pizza margarita");
        agregarpedido("ana", 35.00, 35, "pizza pepperoni");
        agregarpedido("luis", 18.75, 15, "pizza hawaiana");
        agregarpedido("maria", 42.00, 45, "2 pizzas medianas");
        agregarpedido("sofia", 22.50, 25, "pizza vegetariana");
    }
}