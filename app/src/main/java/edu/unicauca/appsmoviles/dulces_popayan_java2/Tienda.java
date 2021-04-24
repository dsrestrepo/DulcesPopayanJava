package edu.unicauca.appsmoviles.dulces_popayan_java2;

public class Tienda {

    private String nombre;
    private int Id;
    private String dulces;
    private String descripcion;

    public Tienda() {
    }

    public Tienda(String nombre, int id, String dulces, String descripcion) {
        this.nombre = nombre;
        Id = id;
        this.dulces = dulces;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDulces() {
        return dulces;
    }

    public void setDulces(String dulces) {
        this.dulces = dulces;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
