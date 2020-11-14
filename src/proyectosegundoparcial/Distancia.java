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
 //Creamos clase distancia
public class Distancia implements Comparable<Distancia>{
    private double distancia;
    private Hotel h;
    //Creamos constructor
    public Distancia(double distancia, Hotel h) {
        this.distancia = distancia;
        this.h = h;
    }
    //getter de distancia retorna un double
    public double getDistancia() {
        return distancia;
    }
     // getter de hotel
    public Hotel getH() {
        return h;
    }
    //Sobreescribimos el metodo compareTo para el orden de las distancia de coleccion map
    @Override
    public int compareTo(Distancia o) {
        if (distancia < o.distancia) {
            return -1;
        }
        if (distancia > o.distancia) {
            return 1;
        }
        return 0;
    }
    
    
}
