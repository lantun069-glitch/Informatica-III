package edu.informatica3.lucas_antun.practico01;

/**
 * Representa una tarea en el sistema.
 */
public class Tarea {
    
    private final String descripcion;
    private String estado;
    
    public static final String ESTADO_PENDIENTE = "pendiente";
    public static final String ESTADO_COMPLETADA = "completada";
    
    public Tarea(String descripcion) {
        this(descripcion, ESTADO_PENDIENTE);
    }
    
    public Tarea(String descripcion, String estado) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("Descripcion no puede ser vacia");
        }
        this.descripcion = descripcion.trim();
        this.estado = estado != null ? estado : ESTADO_PENDIENTE;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado != null ? estado : ESTADO_PENDIENTE;
    }
    
    public boolean estaCompletada() {
        return ESTADO_COMPLETADA.equals(estado);
    }
    
    public boolean estaPendiente() {
        return ESTADO_PENDIENTE.equals(estado);
    }
    
    public void marcarComoCompletada() {
        this.estado = ESTADO_COMPLETADA;
    }
    
    public void marcarComoPendiente() {
        this.estado = ESTADO_PENDIENTE;
    }
    
    @Override
    public String toString() {
        return String.format("%s [%s]", descripcion, estado);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Tarea tarea = (Tarea) obj;
        return descripcion.equals(tarea.descripcion) && 
               estado.equals(tarea.estado);
    }
    
    @Override
    public int hashCode() {
        return java.util.Objects.hash(descripcion, estado);
    }
}
