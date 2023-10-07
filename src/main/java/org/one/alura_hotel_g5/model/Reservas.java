package org.one.alura_hotel_g5.model;

import java.time.LocalDate;

public class Reservas {
    private Integer id;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private double costo;
    private FormaPago formaPago;
    private boolean isCheckedIn;
    private boolean isCheckedOut;
    private Integer numeroHabitacion;
    private Habitacion tipoHabitacion;
}
