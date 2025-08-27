/*
Ejercicio 4 – Máximo común divisor (MCD)
Implemente de manera recursiva el algoritmo de Euclides para calcular el MCD de dos
números enteros positivos.
Ejemplo: MCD(48, 18) = 6.
 */

public class Ejercicio_4 {

    static int mcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return mcd(b, a % b);
    }

    public static void main(String[] args) {
        int a = 12, b = 6;
        System.out.println("MCD(" + a + ", " + b + ") = " + mcd(a, b));
    }

}
