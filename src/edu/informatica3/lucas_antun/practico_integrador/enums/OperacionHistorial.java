package edu.informatica3.lucas_antun.practico_integrador.enums;

import edu.informatica3.lucas_antun.practico_integrador.modelo.Turno;
import java.time.LocalDateTime;

/**
 * Clase que representa una operación realizada en el historial del sistema.
 * Utilizada para implementar funcionalidad de Deshacer (Undo).
 * 
 * Tipos de operaciones:
 * - AGREGAR: Se agregó un nuevo turno
 * - CANCELAR: Se canceló un turno existente
 * - REPROGRAMAR: Se cambió la fecha de un turno
 * 
 * @author Lucas Santiago Said Antun
 * @author Federico Fernández
 */
public class OperacionHistorial {
    
    public TipoOperacion tipo;
    public Turno turno;
    public LocalDateTime fechaAnterior; // Solo para REPROGRAMAR
    public String descripcion;
    
    /**
     * Constructor para operaciones AGREGAR y CANCELAR
     * 
     * @param tipo Tipo de operación (AGREGAR o CANCELAR)
     * @param turno Turno afectado
     * @param descripcion Descripción de la operación
     */
    public OperacionHistorial(TipoOperacion tipo, Turno turno, String descripcion) {
        this.tipo = tipo;
        this.turno = turno;
        this.descripcion = descripcion;
        this.fechaAnterior = null;
    }
    
    /**
     * Constructor para operación REPROGRAMAR
     * 
     * @param tipo Tipo de operación (debe ser REPROGRAMAR)
     * @param turno Turno reprogramado
     * @param fechaAnterior Fecha anterior del turno antes de reprogramar
     * @param descripcion Descripción de la operación
     */
    public OperacionHistorial(TipoOperacion tipo, Turno turno, LocalDateTime fechaAnterior, String descripcion) {
        this.tipo = tipo;
        this.turno = turno;
        this.fechaAnterior = fechaAnterior;
        this.descripcion = descripcion;
    }
}
