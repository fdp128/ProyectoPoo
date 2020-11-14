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
 //Creamos clase provincia
public class Provincia {
    private List<Ciudad> ciudades = new ArrayList<>();
    private String provincia;
    private String descripcion;
    private String region;
    private String web;
    //Creamos constructor
    public Provincia(String provincia, String descripcion, String region, String web) {
        this.provincia = provincia;
        this.descripcion = descripcion;
        this.region = region;
        this.web = web;
    }

    @Override
    public String toString() {
        return provincia;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getRegion() {
        return region;
    }

    public String getWeb() {
        return web;
    }

    public List<Ciudad> getCiudades() {
        return ciudades;
    }
    
    
    
    
    
}
