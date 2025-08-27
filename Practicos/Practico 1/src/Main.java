import java.util.Scanner;
/**

Clase principal con el menu para gestionar tareas
*/
public class Main {
public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);
GestorTareas gestor = new GestorTareas();
// Intentar cargar tareas al iniciar
 gestor.cargarTareas();
 
 boolean ejecutar = true;
 
 while (ejecutar) {
     mostrarMenu();
     System.out.print("Seleccione una opcion: ");
     
     int opcion = 0;
     try {
         opcion = Integer.parseInt(scanner.nextLine());
     } catch (NumberFormatException e) {
         System.out.println("Por favor, ingrese un numero valido.");
         continue;
     }
     
     switch (opcion) {
         case 1: // Agregar tarea
             System.out.print("Ingrese la descripcion de la tarea: ");
             String descripcion = scanner.nextLine();
             if (gestor.agregarTarea(descripcion)) {
                 System.out.println("Tarea agregada correctamente.");
             } else {
                 System.out.println("Error: La descripcion no puede estar vacia.");
             }
             break;
             
         case 2: // Listar tareas
             gestor.listarTareas();
             break;
             
         case 3: // Marcar tarea como completada
             if (gestor.obtenerNumeroTareas() == 0) {
                 System.out.println("No hay tareas para marcar como completadas.");
                 break;
             }
             
             gestor.listarTareas();
             System.out.print("Ingrese el numero de la tarea a marcar como completada: ");
             
             try {
                 int indice = Integer.parseInt(scanner.nextLine());
                 if (gestor.marcarComoCompletada(indice)) {
                     System.out.println("Tarea marcada como completada.");
                 } else {
                     System.out.println("Error: Numero de tarea invalido.");
                 }
             } catch (NumberFormatException e) {
                 System.out.println("Por favor, ingrese un numero valido.");
             }
             break;
             
         case 4: // Eliminar tareas completadas
             int eliminadas = gestor.eliminarTareasCompletadas();
             if (eliminadas > 0) {
                 System.out.println("Se eliminaron " + eliminadas + " tareas completadas.");
             } else {
                 System.out.println("No hay tareas completadas para eliminar.");
             }
             break;
             
         case 5: // Guardar tareas
             if (gestor.guardarTareas()) {
                 System.out.println("Tareas guardadas correctamente.");
             } else {
                 System.out.println("Error al guardar las tareas.");
             }
             break;
             
         case 6: // Cargar tareas
             if (gestor.cargarTareas()) {
                 System.out.println("Tareas cargadas correctamente.");
             } else {
                 System.out.println("No se encontro el archivo de tareas.");
             }
             break;
             
         case 0: // Salir
             System.out.println("Desea guardar las tareas antes de salir? (S/N)");
             String respuesta = scanner.nextLine().toUpperCase();
             if (respuesta.equals("S")) {
                 gestor.guardarTareas();
             }
             System.out.println("Hasta pronto!");
             ejecutar = false;
             break;
             
         default:
             System.out.println("Opcion no valida. Intente nuevamente.");
     }
     
     if (ejecutar) {
         System.out.println("\nPresione Enter para continuar...");
         scanner.nextLine();
     }
 }
 
 scanner.close();
}
/**

Muestra el menu de opciones
*/
private static void mostrarMenu() {
System.out.println("\n=== GESTOR DE TAREAS PERSONALES ===");
System.out.println("1. Agregar tarea");
System.out.println("2. Listar tareas");
System.out.println("3. Marcar tarea como completada");
System.out.println("4. Eliminar tareas completadas");
System.out.println("5. Guardar tareas en archivo");
System.out.println("6. Cargar tareas desde archivo");
System.out.println("0. Salir");
System.out.println("===================================");
}
}
