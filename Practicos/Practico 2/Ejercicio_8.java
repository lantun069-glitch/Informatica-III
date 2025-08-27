/*
Ejercicio 8 – Buscar en un arreglo
Implemente un método recursivo que determine si un número se encuentra dentro de un
arreglo de enteros.
Ejemplo: [3, 5, 7, 9], buscar 7 → true.
 */

public class Ejercicio_8 {
    static boolean buscar(int[] arr, int objetivo, int indice) {
        // Si ya revisamos todos los elementos, no esta
        if (indice >= arr.length) {
            return false;
        }

        // Si lo encontramos
        if (arr[indice] == objetivo) {
            return true;
        }
        
        // Seguimos buscando
        return buscar(arr, objetivo, indice + 1);
    }

    public static void main(String[] args) {
        int[] arreglo = {3, 5, 7, 9};
        int numeroaencontrar = 4; 
        
        boolean encontrado = buscar(arreglo, numeroaencontrar, 0);
        
        if (encontrado) {
            System.out.println("El numero " + numeroaencontrar + " esta en el array");
        } else {
            System.out.println("El numero " + numeroaencontrar + " no se encuentra en el array");
        }
    }
}
