package com.hotel.gestionhotel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "habitaciones")
public class Habitacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String numeroHabitacion;
    
    @Column(nullable = false, length = 50)
    private String tipo;
    
    @Column(nullable = false)
    private Double precioPorNoche;
    
    @Column(nullable = false)
    private Integer capacidad;
    
    @Column(nullable = false)
    private Boolean disponible = true;
    
    @Column(length = 500)
    private String descripcion;
    
    public Habitacion() {
    }
    
    public Habitacion(String numeroHabitacion, String tipo, Double precioPorNoche, Integer capacidad, String descripcion) {
        this.numeroHabitacion = numeroHabitacion;
        this.tipo = tipo;
        this.precioPorNoche = precioPorNoche;
        this.capacidad = capacidad;
        this.descripcion = descripcion;
        this.disponible = true;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNumeroHabitacion() {
        return numeroHabitacion;
    }
    
    public void setNumeroHabitacion(String numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public Double getPrecioPorNoche() {
        return precioPorNoche;
    }
    
    public void setPrecioPorNoche(Double precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }
    
    public Integer getCapacidad() {
        return capacidad;
    }
    
    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
    
    public Boolean getDisponible() {
        return disponible;
    }
    
    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString() {
        return "Habitacion{" +
                "id=" + id +
                ", numeroHabitacion='" + numeroHabitacion + '\'' +
                ", tipo='" + tipo + '\'' +
                ", precioPorNoche=" + precioPorNoche +
                ", capacidad=" + capacidad +
                ", disponible=" + disponible +
                '}';
    }
}
