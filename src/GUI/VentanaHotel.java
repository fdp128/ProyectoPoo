/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import proyectosegundoparcial.Hotel;

/**
 *
 * Creamos la clase Ventana Hotel donde se imprimira la informacion de descripcion y titulo de los hoteles
 */
public class VentanaHotel {
    private Hotel hotel;
    private VBox root = new VBox();
    private Label lblTitulo;
    private TextArea lblDescripcion = new TextArea();
    private FlowPane Descripcion=new FlowPane(Orientation.VERTICAL); 
    //Creamos el contrcutor de Ventana Hotel que recibe un hotel
    public VentanaHotel(Hotel h){
       
       hotel = h;
       lblTitulo = new Label(hotel.getNombreHotel());
       
       lblDescripcion =new TextArea(separador(hotel.getDescripcionHotel()));
       lblDescripcion.setWrapText(false);
       organizarControles();
    }
    // Alineamos y colocamos los paneles
    public void organizarControles(){
       Descripcion.setColumnHalignment(HPos.LEFT);
       Descripcion.setPrefWrapLength(100);
       Descripcion.setHgap(15);
       Descripcion.setMaxWidth(500);

      Descripcion.getChildren().addAll(lblTitulo,lblDescripcion);
      root.getChildren().addAll(Descripcion);
      
       
       
    }

    
//hacemos los getter de los root y Descripcion
    public FlowPane getDescripcion() {
        return Descripcion;
    }

    public VBox getRoot() {
        return root;
    }
    //Creamos el metodo para seprar la cadena String descripcion con el punto final de las oraciones y rotarnamos la division
     public String separador(String str){
       String[] separado=str.split(".");
       String datos = null;
       for(String dato:separado){
           String datoNuevo=dato + "\n";
           datos.concat(datoNuevo);
       }
       
    
       return datos;
    
     }
}