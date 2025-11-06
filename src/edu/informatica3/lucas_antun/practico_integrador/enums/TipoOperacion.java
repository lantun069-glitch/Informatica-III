package edu.informatica3.lucas_antun.practico_integrador.enums;

/**
 * Tipos de operaciones que se pueden realizar en el historial.
 * Utilizado por el sistema de Undo/Redo.
 * 
 * Tipos:
 * - AGREGAR: Se agregó un nuevo turno al sistema
 * - CANCELAR: Se canceló un turno existente
 * - REPROGRAMAR: Se cambió la fecha de un turno
 * 
 * @author Lucas Santiago Said Antun
 * @author Federico Fernández
 */
public enum TipoOperacion { 
    AGREGAR,      // Se agregó un turno
    CANCELAR,     // Se canceló un turno
    REPROGRAMAR   // Se reprogramó un turno
}
