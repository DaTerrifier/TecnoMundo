package com.utp.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "Producto")
public class ProductoTecnologia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prod")
    private int idProd; // Cambiado a camelCase

    @Column(name = "nombre", nullable = false, length = 600)
    private String nombre;

    @Column(name = "precio", nullable = false)
    private double precio;

    @Column(name = "imagen", length = 200)
    private String imagen;

    public ProductoTecnologia() {
        super();
    }

    // Constructor
    public ProductoTecnologia(int idProd, String nombre, double precio, String imagen) {
        super();
        this.idProd = idProd;
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
    }

    // Getters y Setters
    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
