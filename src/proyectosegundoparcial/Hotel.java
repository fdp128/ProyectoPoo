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
 //Creamos clase hotel
public class Hotel implements Serializable{
    private List<Habitacion> habitaciones = new ArrayList<>();
    public static List<Servicio> servicios = new ArrayList<>();
    private String nombreHotel;
    private String descripcionHotel;
    private String tarjetaHotel;
    private String ubicacionHotel;
    private String direccionHotel;
    private String webHotel;
    private String clasificacionHotel;
    private String fotoHotel;
    private String latitud;
    private String longitud;
    //Creamos constructor
    public Hotel(String nombreHotel, String descripcionHotel, String tarjetaHotel, String ubicacionHotel, String direccionHotel, String webHotel, String clasificacionHotel, String fotoHotel) {
        this.nombreHotel = nombreHotel;
        this.descripcionHotel = descripcionHotel;
        this.tarjetaHotel = tarjetaHotel;
        this.ubicacionHotel = ubicacionHotel;
        this.direccionHotel = direccionHotel;
        this.webHotel = webHotel;
        this.clasificacionHotel = clasificacionHotel;
        this.fotoHotel = fotoHotel;
    }    
        
    //Creamos constructor 2
    public Hotel(String nombreHotel, String descripcionHotel, String tarjetaHotel, String ubicacionHotel, String direccionHotel, String webHotel, String clasificacionHotel, String fotoHotel, String latitud, String longitud) {
        this.nombreHotel = nombreHotel;
        this.descripcionHotel = descripcionHotel;
        this.tarjetaHotel = tarjetaHotel;
        this.ubicacionHotel = ubicacionHotel;
        this.direccionHotel = direccionHotel;
        this.webHotel = webHotel;
        this.clasificacionHotel = clasificacionHotel;
        this.fotoHotel = fotoHotel;
        this.latitud = latitud;
        this.longitud = longitud;
    }
    //getter nombre hotel
    public String getNombreHotel() {
        return nombreHotel;
    }
    //getter Descripcion Hotel
    public String getDescripcionHotel() {
        return descripcionHotel;
    }
    //getter tarjeta hotel
    public String getTarjetaHotel() {
        return tarjetaHotel;
    }
    //getter ubicacion hotel
    public String getUbicacionHotel() {
        return ubicacionHotel;
    }
    // getter direccion Hotel
    public String getDireccionHotel() {
        return direccionHotel;
    }
    // getter de web hotel
    public String getWebHotel() {
        return webHotel;
    }
    //getter de clasificacion hotel
    public String getClasificacionHotel() {
        return clasificacionHotel;
    }
    //getter de foto hotel
    public String getFotoHotel() {
        return fotoHotel;
    }
    // getter latitud
    public String getLatitud() {
        return latitud;
    }
    // getter longitud
    public String getLongitud() {
        return longitud;
    }
    // getter de lista habitaciones
    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }
    // getter de lista servicios
    public static List<Servicio> getServicios() {
        return servicios;
    }
    
    
    
    
    
    //Sobreescribimos el metodo ToString que retorna el nombre hotel
    @Override
    public String toString() {
        return nombreHotel;
    }
    //Sobreescribimos el metodo hashCode
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.nombreHotel);
        return hash;
    }
    //Sobreescribimos el metodo equeals
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
        final Hotel other = (Hotel) obj;
        if (!Objects.equals(this.nombreHotel, other.nombreHotel)) {
            return false;
        }
        return true;
    }
    
    
    
}
