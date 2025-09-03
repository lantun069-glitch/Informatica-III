package Practicos.Practico_3;
import java.util.*;

public class ordenador {
    
    public void insercion(List<pedido> lista) {
        for (int i = 1; i < lista.size(); i++) {
            pedido aux = lista.get(i);
            int j = i - 1;
            
            while (j >= 0 && lista.get(j).gettiempo() > aux.gettiempo()) {
                lista.set(j + 1, lista.get(j));
                j--;
            }
            lista.set(j + 1, aux);
        }
    }
    
    public void shellsort(List<pedido> lista) {
        int n = lista.size();
        
        for (int gap = n/2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                pedido temp = lista.get(i);
                int j;
                
                for (j = i; j >= gap && lista.get(j - gap).getprecio() > temp.getprecio(); j -= gap) {
                    lista.set(j, lista.get(j - gap));
                }
                lista.set(j, temp);
            }
        }
    }
    
    public void quicksort(List<pedido> lista, int izq, int der) {
        if (izq < der) {
            int pivote = particion(lista, izq, der);
            quicksort(lista, izq, pivote - 1);
            quicksort(lista, pivote + 1, der);
        }
    }
    
    private int particion(List<pedido> lista, int izq, int der) {
        pedido pivote = lista.get(der);
        int i = izq - 1;
        
        for (int j = izq; j < der; j++) {
            if (lista.get(j).getnombrecliente().compareTo(pivote.getnombrecliente()) <= 0) {
                i++;
                pedido temp = lista.get(i);
                lista.set(i, lista.get(j));
                lista.set(j, temp);
            }
        }
        
        pedido temp = lista.get(i + 1);
        lista.set(i + 1, lista.get(der));
        lista.set(der, temp);
        
        return i + 1;
    }
}