package edu.unicauca.appsmoviles.dulces_popayan_java2;

public class Tienda {

    private String nombre;
    private int Id;
    private String dulces;
    private String descripcion;
    private String url;

    public Tienda() {
    }

    public Tienda(String nombre, int id, String dulces, String descripcion, String url) {
        this.nombre = nombre;
        Id = id;
        this.url = url;
        this.dulces = dulces;
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
