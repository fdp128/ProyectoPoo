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
 //Creamos la clase cliente
public class Cliente implements Serializable{
    private String cedula;
    private String nombre;
    private String direccion;
    private String correo;   
    //Creamos el constructor
    public Cliente(String cedula, String nombre, String direccion, String correo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
    }
    //Sobreescribimos el metodo ToString para retornar el nombre
    @Override
    public String toString() {
        return nombre;
    }
    //Sobreescribimos el hasdCode
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.cedula);
        return hash;
    }
     //Sobreescribimos el equals 
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.cedula, other.cedula)) {
            return false;
        }
        return true;
    }
    
    
}
