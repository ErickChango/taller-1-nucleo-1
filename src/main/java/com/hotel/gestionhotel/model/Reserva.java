package com.hotel.gestionhotel.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "reservas")
public class Reserva {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "huesped_id", nullable = false)
    private Huesped huesped;
    
    @ManyToOne
    @JoinColumn(name = "habitacion_id", nullable = false)
    private Habitacion habitacion;
    
    @Column(nullable = false)
    private LocalDate fechaEntrada;
    
    @Column(nullable = false)
    private LocalDate fechaSalida;
    
    @Column(nullable = false)
    private Integer numeroPersonas;
    
    @Column(nullable = false)
    private Double montoTotal;
    
    @Column(nullable = false, length = 20)
    private String estado;
    
    @Column(length = 500)
    private String observaciones;
    
    @Column(nullable = false)
    private LocalDate fechaReserva;
    
    public Reserva() {
        this.fechaReserva = LocalDate.now();
        this.estado = "Pendiente";
    }
    
    public Reserva(Huesped huesped, Habitacion habitacion, LocalDate fechaEntrada, LocalDate fechaSalida, Integer numeroPersonas, String observaciones) {
        this.huesped = huesped;
        this.habitacion = habitacion;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.numeroPersonas = numeroPersonas;
        this.observaciones = observaciones;
        this.fechaReserva = LocalDate.now();
        this.estado = "Pendiente";
        this.montoTotal = calcularMontoTotal();
    }
    
    public Double calcularMontoTotal() {
        if (fechaEntrada != null && fechaSalida != null && habitacion != null) {
            long dias = ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);
            return dias * habitacion.getPrecioPorNoche();
        }
        return 0.0;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Huesped getHuesped() {
        return huesped;
    }
    
    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }
    
    public Habitacion getHabitacion() {
        return habitacion;
    }
    
    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }
    
    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }
    
    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
        this.montoTotal = calcularMontoTotal();
    }
    
    public LocalDate getFechaSalida() {
        return fechaSalida;
    }
    
    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
        this.montoTotal = calcularMontoTotal();
    }
    
    public Integer getNumeroPersonas() {
        return numeroPersonas;
    }
    
    public void setNumeroPersonas(Integer numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }
    
    public Double getMontoTotal() {
        return montoTotal;
    }
    
    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getObservaciones() {
        return observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public LocalDate getFechaReserva() {
        return fechaReserva;
    }
    
    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
    
    public long getNumeroDias() {
        if (fechaEntrada != null && fechaSalida != null) {
            return ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);
        }
        return 0;
    }
    
    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", huesped=" + huesped.getNombreCompleto() +
                ", habitacion=" + habitacion.getNumeroHabitacion() +
                ", fechaEntrada=" + fechaEntrada +
                ", fechaSalida=" + fechaSalida +
                ", montoTotal=" + montoTotal +
                ", estado='" + estado + '\'' +
                '}';
    }
}
