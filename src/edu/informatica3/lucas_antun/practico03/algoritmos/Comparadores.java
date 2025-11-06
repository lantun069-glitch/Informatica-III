package edu.informatica3.lucas_antun.practico03.algoritmos;
import edu.informatica3.lucas_antun.practico03.modelo.*;
import java.util.Comparator;
/**
 * Comparadores para distintos criterios de ordenamiento de Pedidos.
 */
public class Comparadores {
    public static final Comparator<Pedido> POR_TIEMPO = Comparator.comparingInt(Pedido::getTiempoPreparacion);
    public static final Comparator<Pedido> POR_PRECIO = Comparator.comparingDouble(Pedido::getPrecio);
    public static final Comparator<Pedido> POR_NOMBRE = Comparator.comparing(p -> p.getNombreCliente().toLowerCase());
    public static final Comparator<Pedido> POR_ID = Comparator.comparingInt(Pedido::getId);
    public static final Comparator<Pedido> POR_ESTADO = Comparator.comparing(Pedido::getEstado);
    public static final Comparator<Pedido> POR_ESTADO_Y_TIEMPO = POR_ESTADO.thenComparing(POR_TIEMPO);
    public static final Comparator<Pedido> POR_PRECIO_DESC_Y_TIEMPO = POR_PRECIO.reversed().thenComparing(POR_TIEMPO);
}
