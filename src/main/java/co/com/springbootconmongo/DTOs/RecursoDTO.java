package co.com.springbootconmongo.DTOs;

import java.util.Objects;

public class RecursoDTO {

    private String id;
    private String nombre;
    private String tipo;
    private String tematica;
    private boolean disponible;
    private String fechaPrestamo;

    public RecursoDTO() {

    }

    public RecursoDTO(String nombre, String tipo, String tematica, boolean disponible, String fechaPrestamo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.tematica = tematica;
        this.disponible = disponible;
        this.fechaPrestamo = fechaPrestamo;
    }

    public RecursoDTO(String id, String nombre, String tipo, String tematica, boolean disponible, String fechaPrestamo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.tematica = tematica;
        this.disponible = disponible;
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecursoDTO that = (RecursoDTO) o;
        return disponible == that.disponible && Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(tipo, that.tipo) && Objects.equals(tematica, that.tematica) && Objects.equals(fechaPrestamo, that.fechaPrestamo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "RecursoDTO{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", tematica='" + tematica + '\'' +
                ", disponible=" + disponible +
                ", fechaPrestamo='" + fechaPrestamo + '\'' +
                '}';
    }
}
