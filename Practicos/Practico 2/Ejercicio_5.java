/*
Ejercicio 5 – Conversión binaria
Escriba un método recursivo que reciba un número entero positivo y devuelva su
representación en binario (como string).
Ejemplo: 13 → "1101".
 */
import java.util.Scanner;
public class Ejercicio_5 {
    static String numeroabinario(int n) {
        if (n == 0) {
            return "0";
        }
        return numeroabinario(n / 2) + (n % 2); 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el numero a convertir: ");
        int numero = scanner.nextInt();
        System.out.println("El numero ingresado en decimal es: " + numero + " y en binario es: " + numeroabinario(numero));
    }
}