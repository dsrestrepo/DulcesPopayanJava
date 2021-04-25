package edu.unicauca.appsmoviles.dulces_popayan_java2;

public class Tienda {

    private String nombre;
    private String Id;
    private String dulces;
    private String descripcion;
    private String url;

    public Tienda() {
    }

    public Tienda(String nombre, String Id, String dulces, String descripcion, String url) {
        this.nombre = nombre;
        this.Id = Id;
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

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
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
