import java.io.*;
import java.util.ArrayList;
/**
Clase que gestiona las tareas personales
*/
public class GestorTareas {
private ArrayList<Tarea> tareas;
private final String ARCHIVO_TAREAS = "tareas.txt";
public GestorTareas() {
tareas = new ArrayList<>();
}
/**

Agrega una nueva tarea
*/
public boolean agregarTarea(String descripcion) {
if (descripcion == null || descripcion.trim().isEmpty()) {
return false;
}
tareas.add(new Tarea(descripcion));
return true;
}

/**

Lista todas las tareas
*/
public boolean listarTareas() {
if (tareas.isEmpty()) {
System.out.println("No hay tareas registradas.");
return false;
}
System.out.println("\n=== LISTA DE TAREAS ===");
for (int i = 0; i < tareas.size(); i++) {
System.out.println((i + 1) + ". " + tareas.get(i));
}
System.out.println("======================\n");
return true;
}

/**

Marca una tarea como completada
*/
public boolean marcarComoCompletada(int indice) {
if (indice < 1 || indice > tareas.size()) {
return false;
}
tareas.get(indice - 1).setEstado("completada");
return true;
}

/**

Elimina todas las tareas completadas
*/
public int eliminarTareasCompletadas() {
int contador = 0;
for (int i = tareas.size() - 1; i >= 0; i--) {
if (tareas.get(i).getEstado().equals("completada")) {
tareas.remove(i);
contador++;
}
}
return contador;
}

/**

Guarda las tareas en un archivo
*/
public boolean guardarTareas() {
try {
FileWriter fw = new FileWriter(ARCHIVO_TAREAS);
PrintWriter pw = new PrintWriter(fw);
for (Tarea tarea : tareas) {
     pw.println(tarea.getDescripcion() + "|" + tarea.getEstado());
 }
 
 pw.close();
 return true;
} catch (Exception e) {
System.out.println("Error al guardar las tareas");
return false;
}
}

/**

Carga las tareas desde un archivo
*/
public boolean cargarTareas() {
File archivo = new File(ARCHIVO_TAREAS);
if (!archivo.exists()) {
return false;
}
try {
FileReader fr = new FileReader(archivo);
BufferedReader br = new BufferedReader(fr);
tareas.clear();
 String linea;
 while ((linea = br.readLine()) != null) {
     String[] partes = linea.split("\\|");
     if (partes.length == 2) {
         tareas.add(new Tarea(partes[0], partes[1]));
     }
 }
 
 br.close();
 return true;
} catch (Exception e) {
System.out.println("Error al cargar las tareas");
return false;
}
}

/**

Obtiene el numero de tareas
*/
public int obtenerNumeroTareas() {
return tareas.size();
}
}


