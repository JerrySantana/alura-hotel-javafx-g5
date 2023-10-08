package org.one.alura_hotel_g5.model;

import java.sql.Date;

public class Reservas {
    private Integer id;
    private Date checkIn;
    private Date checkOut;
    private double costo;
    private FormaPago formaPago;
    private Integer numeroHabitacion;
    private Habitacion tipoHabitacion;

    public Reservas(Date checkIn, Date checkOut, double costo, FormaPago formaPago, Integer numeroHabitacion, Habitacion tipoHabitacion) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.costo = costo;
        this.formaPago = formaPago;
        this.numeroHabitacion = numeroHabitacion;
        this.tipoHabitacion = tipoHabitacion;
    }

    public Reservas(Integer id, Date checkIn, Date checkOut, double costo, FormaPago formaPago, Integer numeroHabitacion, Habitacion tipoHabitacion) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.costo = costo;
        this.formaPago = formaPago;
        this.numeroHabitacion = numeroHabitacion;
        this.tipoHabitacion = tipoHabitacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public double getCosto() {
        return costo;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public Integer getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public Habitacion getTipoHabitacion() {
        return tipoHabitacion;
    }
}
