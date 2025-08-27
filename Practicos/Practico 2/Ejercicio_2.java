/**
Ejercicio 2 – Invertir una cadena
Escriba un método recursivo que reciba una cadena y devuelva la misma cadena invertida.
Ejemplo: "recursivo" → "ovisrucer".
 */
public class Ejercicio_2 {
    
    public static String invertirCadena(String str) {
        if (str.isEmpty()) {
            return str;
        }
        return str.charAt(str.length() - 1) + invertirCadena(str.substring(0, str.length() - 1));
    }
    
    public static void main(String[] args) {
        System.out.println("Hola -> " + invertirCadena("Hola"));
        System.out.println("Java -> " + invertirCadena("Java"));
        System.out.println("Informatica -> " + invertirCadena("Informatica"));
    }
}