/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosegundoparcial;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Pc
 */
 //Creamos clase habitacion
public class Habitacion implements Serializable{
    private String tipoHabitacion;
    private double tarifaSencilla;
    private double tarifaDoble;
    private double tarifaTriple ;
    //Creamos constructor
    public Habitacion(String tipoHabitacion, double tarifaSencilla, double tarifaDoble, double tarifaTriple) {
        this.tipoHabitacion = tipoHabitacion;
        this.tarifaSencilla = tarifaSencilla;
        this.tarifaDoble = tarifaDoble;
        this.tarifaTriple = tarifaTriple;
    }
    //getter de tipo habitacion
    public String getTipoHabitacion() {
        return tipoHabitacion;
    }
    //getter de tarifa sencilla
    public double getTarifaSencilla() {
        return tarifaSencilla;
    }
   //getter de tarifa doble
    public double getTarifaDoble() {
        return tarifaDoble;
    }
    // getter de tarifa triple
    public double getTarifaTriple() {
        return tarifaTriple;
    }

    
     //Sobreescribimos el metodo ToString para retornar tipo habitacion
    @Override
    public String toString() {
        return tipoHabitacion;
    }
    //Sobreescribimos el metodo hashCode
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.tipoHabitacion);
        return hash;
    }
   //Sobreescribimos el metodo equals que retonar un Boolean
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
        final Habitacion other = (Habitacion) obj;
        if (!Objects.equals(this.tipoHabitacion, other.tipoHabitacion)) {
            return false;
        }
        return true;
    }
    
    
    
}
