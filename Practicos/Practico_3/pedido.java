package Practicos.Practico_3;

public class pedido {
    private int id;
    private String nombrecliente;
    private double precio;
    private int tiempo;
    private String descripcion;
    private String estado;
    
    public pedido(int id, String nombrecliente, double precio, int tiempo, String descripcion) {
        this.id = id;
        this.nombrecliente = nombrecliente;
        this.precio = precio;
        this.tiempo = tiempo;
        this.descripcion = descripcion;
        this.estado = "pendiente";
    }
    
    public int getid() {
        return id;
    }
    
    public String getnombrecliente() {
        return nombrecliente;
    }
    
    public double getprecio() {
        return precio;
    }
    
    public int gettiempo() {
        return tiempo;
    }
    
    public String getdescripcion() {
        return descripcion;
    }
    
    public String getestado() {
        return estado;
    }
    
    public void setprecio(double precio) {
        this.precio = precio;
    }
    
    public void settiempo(int tiempo) {
        this.tiempo = tiempo;
    }
    
    public void setestado(String estado) {
        this.estado = estado;
    }
    
    public String toString() {
        return "id: " + id + " - cliente: " + nombrecliente + " - $" + precio + " - " + tiempo + " min - " + estado;
    }
}