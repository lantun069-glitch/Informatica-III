package edu.informatica3.lucas_antun.practico03;

public class Pedido {
    private final int id;
    private String nombreCliente;
    private double precio;
    private int tiempoPreparacion;
    private String descripcion;
    private EstadoPedido estado;

    public enum EstadoPedido {
        PENDIENTE("pendiente"),
        EN_PREPARACION("en preparacion"),
        COMPLETADO("completado");
        private final String descripcion;
        EstadoPedido(String descripcion) { this.descripcion = descripcion; }
        public String getDescripcion() { return descripcion; }
        public static EstadoPedido fromString(String estado) {
            if (estado == null) return PENDIENTE;
            for (EstadoPedido e : values()) {
                if (e.descripcion.equalsIgnoreCase(estado.trim())) return e;
            }
            return PENDIENTE;
        }
    }

    public Pedido(int id, String nombreCliente, double precio, int tiempoPreparacion, String descripcion) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser positivo");
            if (nombreCliente == null || nombreCliente.trim().isEmpty()) throw new IllegalArgumentException("Nombre vacio");
            if (precio < 0) throw new IllegalArgumentException("Precio negativo");
            if (tiempoPreparacion <= 0) throw new IllegalArgumentException("Tiempo no valido");
            if (descripcion == null || descripcion.trim().isEmpty()) throw new IllegalArgumentException("Descripcion vacia");
            this.id = id;
            this.nombreCliente = nombreCliente.trim();
            this.precio = precio;
            this.tiempoPreparacion = tiempoPreparacion;
            this.descripcion = descripcion.trim();
            this.estado = EstadoPedido.PENDIENTE;
        }

        public int getId() { return id; }
        public String getNombreCliente() { return nombreCliente; }
        public double getPrecio() { return precio; }
        public int getTiempoPreparacion() { return tiempoPreparacion; }
        public String getDescripcion() { return descripcion; }
        public EstadoPedido getEstado() { return estado; }
        public String getEstadoString() { return estado.getDescripcion(); }

        public void setNombreCliente(String nombreCliente) {
            if (nombreCliente == null || nombreCliente.trim().isEmpty()) throw new IllegalArgumentException("Nombre vacio");
            this.nombreCliente = nombreCliente.trim();
        }
        public void setPrecio(double precio) {
            if (precio < 0) throw new IllegalArgumentException("Precio negativo");
            this.precio = precio;
        }
        public void setTiempoPreparacion(int tiempoPreparacion) {
            if (tiempoPreparacion <= 0) throw new IllegalArgumentException("Tiempo no valido");
            this.tiempoPreparacion = tiempoPreparacion;
        }
        public void setDescripcion(String descripcion) {
            if (descripcion == null || descripcion.trim().isEmpty()) throw new IllegalArgumentException("Descripcion vacia");
            this.descripcion = descripcion.trim();
        }
        public void setEstado(EstadoPedido estado) {
            this.estado = estado != null ? estado : EstadoPedido.PENDIENTE;
        }
        public void setEstado(String estado) {
            this.estado = EstadoPedido.fromString(estado);
        }

        public boolean estaPendiente() { return estado == EstadoPedido.PENDIENTE; }
        public boolean estaEnPreparacion() { return estado == EstadoPedido.EN_PREPARACION; }
        public boolean estaCompletado() { return estado == EstadoPedido.COMPLETADO; }
        public void marcarEnPreparacion() { this.estado = EstadoPedido.EN_PREPARACION; }
        public void marcarCompletado() { this.estado = EstadoPedido.COMPLETADO; }
        public int calcularTiempoTotal(int margenMinutos) { return tiempoPreparacion + Math.max(0, margenMinutos); }

        @Override
        public String toString() {
            return String.format("Pedido #%d - %s | Cliente: %s | Precio: $%.2f | Tiempo: %d min | Estado: %s", 
                id, descripcion, nombreCliente, precio, tiempoPreparacion, estado.getDescripcion());
        }

        public String toStringDetallado() {
            return String.format(
                "PEDIDO #%d\nCliente: %s\nDescripcion: %s\nPrecio: $%.2f\nTiempo: %d minutos\nEstado: %s",
                id, nombreCliente, descripcion, precio, tiempoPreparacion, estado.getDescripcion()
            );
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Pedido pedido = (Pedido) obj;
            return id == pedido.id;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(id);
        }
    }