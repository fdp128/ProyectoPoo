/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosegundoparcial;

/**
 *
 * @author Pc
 */
 //Creamos exepcion de faltadatos
public class FaltaDatos extends Exception{
     //Sobreescribimos el metodo ToString para retornar Falta informacion
    @Override
    public String toString() {
        return "Falta de Informacion";
    }
    
    
}
