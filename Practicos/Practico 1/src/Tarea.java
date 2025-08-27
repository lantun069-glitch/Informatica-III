public class Tarea {
    private String descripcion;
    private String estado;
public Tarea(String descripcion) {
    this.descripcion = descripcion;
    this.estado = "pendiente";
}

public Tarea(String descripcion, String estado) {
    this.descripcion = descripcion;
    this.estado = estado;
}

public String getDescripcion() {
    return descripcion;
}

public String getEstado() {
    return estado;
}

public void setEstado(String estado) {
    this.estado = estado;
}

@Override
public String toString() {
    return descripcion + " [" + estado + "]";
}
}