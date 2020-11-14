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
 //Creamos clase servicio
public class Servicio {
    private String secuencia;
    private boolean activo;
    private String catalogo;
    //Creamos constructor
    public Servicio(String secuencia, String activo) {
        this.secuencia = secuencia;
        this.activo = activo.equals("activo");
    }
    //getter de sencuencia
    public String getSecuencia() {
        return secuencia;
    }
    //getter de activo
    public boolean isActivo() {
        return activo;
    }
     //getter de catalogo
    public String getCatalogo() {
        return catalogo;
    }
    //setter de catalogo que recibe como parametro un String 
    public void setCatalogo(String catalogo) {
        this.catalogo = catalogo;
    }
    
    

    
    
    
    //Sobreescribimos el metodo ToString para retonar el String actividad
    @Override
    public String toString() {
        String act;
        if (activo){
            act = "Activo";    
        }else{
            act = "Inactivo";
        }
        return act;
    }   
}
