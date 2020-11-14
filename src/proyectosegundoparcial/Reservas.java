/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosegundoparcial;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Pc
 */
 //Creamos clase reserva
public class Reservas implements Serializable{
    private Hotel hotel;
    private String fecha;
    private Habitacion habitacion;
    private Cliente cliente;
    private double precio;
    //Creamos constructor
    public Reservas(Hotel hotel, String fecha, Habitacion habitacion, Cliente cliente,double precio) {
        this.hotel = hotel;
        this.fecha = fecha;
        this.habitacion = habitacion;
        this.cliente = cliente;
        this.precio = precio;
    }
    //Sobreescribimos el metodo hashCode
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
    //Sobreescribimos el metodo equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reservas other = (Reservas) obj;
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.hotel, other.hotel)) {
            return false;
        }
        if (!Objects.equals(this.habitacion, other.habitacion)) {
            return false;
        }
        return true;
    }
    //getter hotel
    public Hotel getHotel() {
        return hotel;
    }
     // getter Fechas
    public String getFechas() {
        return fecha;
    }
   // getter habitacion
    public Habitacion getHabitacion() {
        return habitacion;
    }
    // getter Cliente
    public Cliente getCliente() {
        return cliente;
    } 
    // getter Precio
    public double getPrecio() {
        return precio;
    }
     
    //Sobreescribimos el metodo ToString para retonar el hotel , fecha , habitacion y cliente
    @Override
    public String toString() {
        return "Reservas{" + "hotel=" + hotel + ", fecha=" + fecha + ", habitacion=" + habitacion + ", cliente=" + cliente + '}';
    }
    
    
}
