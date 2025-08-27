/*
Ejercicio 1 – Conteo de dígitos
Escriba una función recursiva que calcule cuántos dígitos tiene un número entero positivo.
Ejemplo: 12345 → 5.
*/

public class Ejercicio_1 {
	public static int contarDigitos(int n) {
		if (n < 10) {
			return 1;
		}
		return 1 + contarDigitos(n / 10);
	}

	public static void main(String[] args) {
		int numero = 12345;
		int resultado = contarDigitos(numero);
		System.out.println("El numero " + numero + " tiene " + resultado + " digitos");
        System.out.println("Otros ejemplos:");
		System.out.println("7 tiene " + contarDigitos(7) + " dígitos");
		System.out.println("999 tiene " + contarDigitos(999) + " dígitos");
	}
}