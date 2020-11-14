/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosegundoparcial;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pc
 */
 //Creamos clase ciudad
public class Ciudad {
    private List<Hotel> hoteles = new ArrayList<>();
    private String idciudad;
    private String idprovincia;
    private String nombre;  
    //Creamos constructor
    public Ciudad(String nombre) {
        this.nombre = nombre;
    }
    //getter idciudad
    public String getIdciudad() {
        return idciudad;
    }
    //getter idprovincia
    public String getIdprovincia() {
        return idprovincia;
    }
    //getter nombre
    public String getNombre() {
        return nombre;
    }
    //getter lista hoteles
    public List<Hotel> getHoteles() {
        return hoteles;
    }
    
    
    
    //sobreescribimos el metodo ToString para retornar el nombre
    @Override
    public String toString() {
        return nombre;
    }
    
}
