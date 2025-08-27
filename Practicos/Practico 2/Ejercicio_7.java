/*
Ejercicio 7 – Números de Fibonacci optimizados
Escriba una función recursiva para calcular el n-ésimo número de Fibonacci.
👉 Discuta con la IA cómo mejorar la eficiencia (por ejemplo, con memoización).
 */
import java.util.Scanner;
public class Ejercicio_7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el valor de n para el cual quiere calcular la sucesion fibonacci: ");
        int n = scanner.nextInt();
        System.out.println("El " + n + "° número de Fibonacci es: " + fibonacci(n));
    }

    static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}