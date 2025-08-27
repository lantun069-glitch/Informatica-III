/*
Ejercicio 6 – Palíndromo
Cree una función recursiva que determine si una cadena es un palíndromo.
Ejemplo: "neuquen" → true, "informatica" → false.
 */
public class Ejercicio_6 {

    static boolean palindromo(String s) {
        // Caso base: si la cadena esta vacía o tiene un solo caracter
        if (s.length() <= 1) {
            return true;
        }
        // Vemos ambas puntas de la cadena
        if (s.charAt(0) != s.charAt(s.length() - 1)) {
            return false;
        }
        // Caso recursivo
        return palindromo(s.substring(1, s.length() - 1));
    }

    public static void main(String[] args) {
        String palabra1 = "neuquen";
        String palabra2 = "12321";
        String palabra3 = "test";
        System.out.println("La palabra/numero \"" + palabra1 + "\" es un palindromo? " + palindromo(palabra1));
        System.out.println("La palabra/numero \"" + palabra2 + "\" es un palindromo? " + palindromo(palabra2));
        System.out.println("La palabra/numero \"" + palabra3 + "\" es un palindromo? " + palindromo(palabra3));
    }

}
