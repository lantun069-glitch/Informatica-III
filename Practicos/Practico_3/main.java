package Practicos.Practico_3;
import java.util.Scanner;


public class main {
    static Scanner sc = new Scanner(System.in);
    static pizzeria pizzeria = new pizzeria();
    static tiempoordenamiento tiempos = new tiempoordenamiento();
    
    public static void main(String[] args) {
        System.out.println("sistema de gestion de pizzeria\n");
        
        int opcion;
        do {
            menu();
            opcion = sc.nextInt();
            sc.nextLine();
            
            switch(opcion) {
                case 1:
                    agregarpedido();
                    break;
                case 2:
                    eliminarpedido();
                    break;
                case 3:
                    actualizarpedido();
                    break;
                case 4:
                    cambiarestado();
                    break;
                case 5:
                    pizzeria.mostrarpedidos();
                    break;
                case 6:
                    menuordenar();
                    break;
                case 7:
                    menutiempos();
                    break;
                case 8:
                    pizzeria.cargarpedidosprueba();
                    System.out.println("pedidos de prueba cargados");
                    break;
                case 9:
                    System.out.println("saliendo...");
                    break;
                default:
                    System.out.println("opcion invalida");
            }
        } while(opcion != 9);
    }
    
    static void menu() {
        System.out.println("\nmenu:");
        System.out.println("1. agregar pedido");
        System.out.println("2. eliminar pedido");
        System.out.println("3. actualizar pedido");
        System.out.println("4. cambiar estado");
        System.out.println("5. mostrar pedidos");
        System.out.println("6. ordenar pedidos");
        System.out.println("7. comparar tiempos");
        System.out.println("8. cargar datos de prueba");
        System.out.println("9. salir");
        System.out.print("opcion: ");
    }
    
    static void agregarpedido() {
        System.out.print("nombre cliente: ");
        String nombre = sc.nextLine();
        
        System.out.print("precio: ");
        double precio = sc.nextDouble();
        
        System.out.print("tiempo (minutos): ");
        int tiempo = sc.nextInt();
        sc.nextLine();
        
        System.out.print("descripcion: ");
        String desc = sc.nextLine();
        
        pizzeria.agregarpedido(nombre, precio, tiempo, desc);
    }
    
    static void eliminarpedido() {
        System.out.print("id del pedido: ");
        int id = sc.nextInt();
        pizzeria.eliminarpedido(id);
    }
    
    static void actualizarpedido() {
        System.out.print("id del pedido: ");
        int id = sc.nextInt();
        
        System.out.print("nuevo precio: ");
        double precio = sc.nextDouble();
        
        System.out.print("nuevo tiempo: ");
        int tiempo = sc.nextInt();
        
        pizzeria.actualizarpedido(id, precio, tiempo);
    }
    
    static void cambiarestado() {
        System.out.print("id del pedido: ");
        int id = sc.nextInt();
        sc.nextLine();
        
        System.out.println("1. pendiente");
        System.out.println("2. en preparacion");
        System.out.println("3. completado");
        System.out.print("opcion: ");
        int op = sc.nextInt();
        
        String estado = "";
        if (op == 1) estado = "pendiente";
        else if (op == 2) estado = "en preparacion";
        else if (op == 3) estado = "completado";
        
        pizzeria.cambiarestado(id, estado);
    }
    
    static void menuordenar() {
        System.out.println("\nordenar por:");
        System.out.println("1. tiempo (insercion)");
        System.out.println("2. precio (shellsort)");
        System.out.println("3. nombre (quicksort)");
        System.out.print("opcion: ");
        
        int op = sc.nextInt();
        
        if (op == 1) {
            pizzeria.ordenarportiempo();
            pizzeria.mostrarpedidos();
        }
        else if (op == 2) {
            pizzeria.ordenarporprecio();
            pizzeria.mostrarpedidos();
        }
        else if (op == 3) {
            pizzeria.ordenarpornombre();
            pizzeria.mostrarpedidos();
        }
    }
    
    static void menutiempos() {
        System.out.println("\ncantidad de pedidos para probar:");
        System.out.println("1. 100 pedidos");
        System.out.println("2. 1000 pedidos");
        System.out.println("3. 10000 pedidos");
        System.out.print("opcion: ");
        
        int op = sc.nextInt();
        
        if (op == 1) tiempos.medirtiempos(100);
        else if (op == 2) tiempos.medirtiempos(1000);
        else if (op == 3) tiempos.medirtiempos(10000);
    }
}