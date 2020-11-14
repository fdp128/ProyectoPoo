/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import proyectosegundoparcial.Ciudad;
import proyectosegundoparcial.Hotel;
import proyectosegundoparcial.Provincia;
import proyectosegundoparcial.ProyectoSegundoParcial;

/**
 *
 * @author CltControl
 */
 
 //Creamos la clase BusquedaJara que nos permitira buscar por Provincia un Hotel
public class BusquedaJARA {
    private VBox root = new VBox();
    private Label lblProvincia = new Label("Provincia: ");
    private ComboBox<Provincia> provincias = new ComboBox<>();
    private HBox panelProvincia = new HBox();
    private Label lblDescripcion = new Label("");
    private Label lblCiudad = new Label("Ciudad: ");
    private ComboBox<Ciudad> ciudades = new ComboBox<>();
    private HBox panelCiudad = new HBox();
    private VBox panelHoteles = new VBox();
    
    //Creamos el constructor
    public BusquedaJARA(){
        organizarControles();
        llenarComboBox();
    }
    // Organizamos la Ventana
    public void organizarControles(){
       root.setSpacing(15);
       root.setAlignment(Pos.TOP_CENTER);
       panelHoteles.setSpacing(35);
       lblDescripcion.setAlignment(Pos.CENTER);
       lblDescripcion.setPrefWidth (675); 
       lblDescripcion.setWrapText (true); 
       panelProvincia.getChildren().addAll(lblProvincia,provincias);
       panelCiudad.getChildren().addAll(lblCiudad,ciudades);
       root.getChildren().addAll(panelProvincia);
    }
    //Llenamos el combo box Provincias y definimos su evento
    public void llenarComboBox(){
        provincias.getItems().addAll(ProyectoSegundoParcial.mProvincias.values());
        provincias.setOnAction(e->mostrarProvincia(provincias.getValue()));
        ciudades.setOnAction(e->comboBoxCiudades(ciudades.getValue()));
    }
    // Llena el combo box de ciudades dependiendo de la provincia
    public void mostrarProvincia(Provincia provincia){
        panelHoteles.getChildren().clear();
        ciudades.getItems().clear();
        lblDescripcion.setText(provincia.getDescripcion());
        ciudades.getItems().addAll(provincia.getCiudades());
        root.getChildren().addAll(lblDescripcion,panelCiudad,panelHoteles);
    }
    //Encuentra todos los hoteles de la ciudad
    public void comboBoxCiudades(Ciudad ciudad){
        panelHoteles.getChildren().clear();
        for (Hotel h:ciudad.getHoteles()){
                HBox panelh = new HBox();
                VBox paneli = new VBox();
                HBox botones = new HBox();
                Label lblNombre = new Label(h.getNombreHotel());
                Label lblDireccion = new Label("Direccion: ");
                Label lblDireccionInfo = new Label(h.getDireccionHotel());
                HBox panelDireccion = new HBox();
                Button informacion = new Button("+ Informacion"); 
                informacion.setOnAction(e -> presentarBotonInfo(h));
                Button reserva = new Button("+ Reservar");
                reserva.setOnAction(e -> botonReserva(h));
                Button mapa = new Button("Ver mapa");
                mapa.setOnAction(e->botonMapa(h));
                ImageView im = new ImageView(h.getFotoHotel());
                im.setFitHeight(120);
                im.setFitWidth(120);
                paneli.setSpacing(30);
                panelh.setSpacing(20);
                botones.setSpacing(10);
                panelDireccion.getChildren().addAll(lblDireccion,lblDireccionInfo);
                botones.getChildren().addAll(informacion,mapa,reserva);
                paneli.getChildren().addAll(lblNombre,panelDireccion,botones);
                panelh.getChildren().addAll(im,paneli);
                panelHoteles.getChildren().add(panelh);
        }
    }
    //Evento boton Info abre una ventana con la informacion del hotel
    public void presentarBotonInfo(Hotel h){
        Stage s = new Stage();
        s.setTitle(h.getNombreHotel());
        Scene sc = new Scene(new VentanaHotel(h).getRoot(),300,450);
        s.setScene(sc);
        s.show();
    }
    //Evento boton mapa abre la ubicacion en Googlemaps del hotel
    private void botonMapa(Hotel h){
        try {
             String query= h.getNombreHotel().replace(" ", "%20");
             URI myURI = new URI("https://www.google.com/maps/search/?api=1&query="+query);
             Desktop.getDesktop().browse(myURI);
     
         } catch (URISyntaxException ex) {
             Logger.getLogger(BusquedaNombreHotel.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(BusquedaNombreHotel.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    //Evento boton reserva abre una ventana para reservar el hotel
    public void botonReserva(Hotel h){
        Stage s = new Stage();
        s.setTitle(h.getNombreHotel());
        Scene sc = new Scene(new VentanaReserva(h).getRoot(),700,200);
        s.setScene(sc);
        s.show();
    }
    //Getter del Root
    public VBox getRoot() {
        return root;
    }
    
    
}
