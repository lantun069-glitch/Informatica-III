package src.edu.informatica3.lucas_antun.practico02;

public class EjerciciosRecursividad {

    // Ejercicio 1: Conteo de digitos
    public static int contarDigitos(int numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("El numero debe ser positivo");
        }
        if (numero < 10) {
            return 1;
        }
        return 1 + contarDigitos(numero / 10);
    }

    // Ejercicio 2: Calculo de factorial
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("El numero debe ser no negativo");
        }
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    // Ejercicio 3: Suma de digitos
    public static int sumarDigitos(int numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("El numero debe ser positivo");
        }
        if (numero < 10) {
            return numero;
        }
        return (numero % 10) + sumarDigitos(numero / 10);
    }

    // Ejercicio 4: Inversion de numero
    public static int invertirNumero(int numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("El numero debe ser positivo");
        }
        return invertirNumeroRecursivo(numero, 0);
    }

    private static int invertirNumeroRecursivo(int numero, int invertido) {
        if (numero == 0) {
            return invertido;
        }
        return invertirNumeroRecursivo(numero / 10, invertido * 10 + numero % 10);
    }

    // Ejercicio 5: Validacion de palindromo
    public static boolean esPalindromo(int numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("El numero debe ser positivo");
        }
        return numero == invertirNumero(numero);
    }

    // Ejercicio 6: Secuencia de Fibonacci
    public static long fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("El indice debe ser no negativo");
        }
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // Ejercicio 7: Calculo de potencia
    public static long potencia(int base, int exponente) {
        if (exponente < 0) {
            throw new IllegalArgumentException("El exponente debe ser no negativo");
        }
        if (exponente == 0) {
            return 1;
        }
        return base * potencia(base, exponente - 1);
    }

    // Ejercicio 8: Maximo comun divisor (MCD)
    public static int calcularMCD(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        if (b == 0) {
            return a;
        }
        return calcularMCD(b, a % b);
    }

    public static void main(String[] args) {
        System.out.println("=== EJERCICIOS DE RECURSIVIDAD ===");
        
        // Demostracion ejercicio 1
        System.out.println("\n1. Conteo de digitos:");
        System.out.println("contarDigitos(12345) = " + contarDigitos(12345));
        System.out.println("contarDigitos(7) = " + contarDigitos(7));
        
        // Demostracion ejercicio 2
        System.out.println("\n2. Factorial:");
        System.out.println("factorial(5) = " + factorial(5));
        System.out.println("factorial(0) = " + factorial(0));
        
        // Demostracion ejercicio 3
        System.out.println("\n3. Suma de digitos:");
        System.out.println("sumarDigitos(12345) = " + sumarDigitos(12345));
        System.out.println("sumarDigitos(999) = " + sumarDigitos(999));
        
        // Demostracion ejercicio 4
        System.out.println("\n4. Inversion de numero:");
        System.out.println("invertirNumero(12345) = " + invertirNumero(12345));
        System.out.println("invertirNumero(100) = " + invertirNumero(100));
        
        // Demostracion ejercicio 5
        System.out.println("\n5. Palindromo:");
        System.out.println("esPalindromo(12321) = " + esPalindromo(12321));
        System.out.println("esPalindromo(12345) = " + esPalindromo(12345));
        
        // Demostracion ejercicio 6
        System.out.println("\n6. Fibonacci:");
        System.out.print("Primeros 10 terminos: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(fibonacci(i) + " ");
        }
        System.out.println();
        
        // Demostracion ejercicio 7
        System.out.println("\n7. Potencia:");
        System.out.println("potencia(2, 3) = " + potencia(2, 3));
        System.out.println("potencia(5, 0) = " + potencia(5, 0));
        
        // Demostracion ejercicio 8
        System.out.println("\n8. MCD:");
        System.out.println("calcularMCD(48, 18) = " + calcularMCD(48, 18));
        System.out.println("calcularMCD(17, 13) = " + calcularMCD(17, 13));
        
        System.out.println("\n=== FIN DE DEMOSTRACIONES ===");
    }
}
