package edu.informatica3.lucas_antun.practico03.modelo;

public enum EstadoPedido {
    PENDIENTE("pendiente"),
    EN_PREPARACION("en preparacion"),
    COMPLETADO("completado");
    
    private final String descripcion;
    
    EstadoPedido(String descripcion) { 
        this.descripcion = descripcion; 
    }
    
    public String getDescripcion() { 
        return descripcion; 
    }
    
    public static EstadoPedido fromString(String estado) {
        if (estado == null) return PENDIENTE;
        for (EstadoPedido e : values()) {
            if (e.descripcion.equalsIgnoreCase(estado.trim())) return e;
        }
        return PENDIENTE;
    }
}
