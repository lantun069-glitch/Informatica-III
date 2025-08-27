/*
Ejercicio 3 – Suma de elementos de un arrayeglo
Implemente una función recursiva que calcule la suma de todos los elementos de un arrayeglo
de enteros.
Ejemplo: [2, 4, 6, 8] → 20.
👉 Extienda la solución para que además devuelva el promedio usando únicamente
recursión.
 */
public class Ejercicio_3 {
    int suma(int[] array, int indice) {
        if (indice == array.length) {
            return 0;
        }
        return array[indice] + suma(array, indice + 1);
    }

    double promedio(int[] array, int indice, int suma) {
        if (indice == array.length) {
            return (double) suma / array.length;
        }
        return promedio(array, indice + 1, suma + array[indice]);
    }

    public static void main(String[] args) {
        Ejercicio_3 ejercicio = new Ejercicio_3();
        int[] arreglo = {10, 10, 10, 10, 30, 40};
        int suma = ejercicio.suma(arreglo, 0);
        double promedio = ejercicio.promedio(arreglo, 0, 0);
        System.out.println("Suma: " + suma);
        System.out.println("Promedio: " + promedio);
    }
}
