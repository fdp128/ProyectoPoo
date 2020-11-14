/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import generadorhtmlmapbox.GeneradorHTMLMapBox;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import proyectosegundoparcial.Hotel;
import proyectosegundoparcial.ProyectoSegundoParcial;
import generadorhtmlmapbox.Ubicacion;
import geocoding.GeoCoding;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.stage.Stage;
import proyectosegundoparcial.Distancia;

/**
 *
 * @author Pc
 */
 //Creamos la clase busqueda por ubicacion que permite buscar por una ubicacion cercana
public class BusquedaUbicacion {
    private List<Distancia> distancias = new ArrayList<>();
    private VBox root = new VBox();
    private HBox panelBusqueda = new HBox();
    private TextField txtEntradas = new TextField();
    private Label lblBusqueda = new Label("Busqueda por  proximidad a lugar tur�stico: ");
    private VBox panelHoteles = new VBox();
    private Button bttBuscar = new Button("BUSCAR"); 
    //Creamos el constructor
    public BusquedaUbicacion()throws IOException{
        organizarControles();
        llenarPanelHoteles();
    }
    //Organizamos los elementos de la ventan
    public void organizarControles(){
       panelHoteles.setSpacing(35);
       panelBusqueda.setSpacing(10);
       panelBusqueda.setAlignment(Pos.TOP_CENTER);
       root.setSpacing(20);
       root.setAlignment(Pos.TOP_CENTER);
       panelBusqueda.getChildren().addAll(lblBusqueda,txtEntradas,bttBuscar);
       root.getChildren().addAll(panelBusqueda,panelHoteles);
    }
    //Definimos el evento del boton buscar
     public void llenarPanelHoteles(){
        bttBuscar.setOnAction(e->botonBuscar());
    }
     //Buscamos los 5 hoteles mas cercanos a la ubicacion ingresada
     private void botonBuscar(){
        panelHoteles.getChildren().clear();
        GeoCoding a = new GeoCoding("pk.eyJ1IjoiamVmbGphcmEiLCJhIjoiY2s2NWQzMWMwMDl6ajNvbzQ2dzd6cTd6cyJ9.ktqF2mqUnVQnLw0SwQMq8A");
        Ubicacion u = a.consultar(txtEntradas.getText());
        for (Hotel h:ProyectoSegundoParcial.mHoteles.values()){
            if(h.getLatitud()!= null | h.getLongitud()!= null){
                double latitudFinal = Double.parseDouble(h.getLatitud());
                double latitudInicial = u.getLatitud();
                double cateto1 = latitudFinal-latitudInicial;
                double longitudFinal = Double.parseDouble(h.getLongitud());
                double longitudInicial = u.getLongitud();
                double cateto2 = longitudFinal-longitudInicial;
                double distancia = Math.sqrt(cateto1*cateto1 + cateto2*cateto2);
                distancias.add(new Distancia(distancia,h));
            }
        }
        Distancia[] miarray = new Distancia[distancias.size()];
        miarray = distancias.toArray(miarray);
        Arrays.sort(miarray);
        List<Hotel> hoteles = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            hoteles.add(miarray[i].getH());
        }//Protegemos el posible error de IO Exception de generar una HTMLMapBox
        try {
            GeneradorHTMLMapBox b = new GeneradorHTMLMapBox("pk.eyJ1IjoiamVmbGphcmEiLCJhIjoiY2s2NXdiY2RtMTAwNzNvbzgyZWN5Y2FyOCJ9.iNdss6wcv0oAM_aZ-9Ch8A",-0.207371,-78.496579);
            b.anadirMarcador(u.getLongitud(),u.getLatitud() ,u.getDescripcion());
            for(Hotel h: hoteles){
                b.anadirMarcador(Double.parseDouble(h.getLongitud()),Double.parseDouble(h.getLatitud()), h.getNombreHotel());
                HBox panelh = new HBox();
                VBox paneli = new VBox();
                HBox botones = new HBox();
                Label lblNombre = new Label(h.getNombreHotel());
                Label lblDireccion = new Label("Direccion: ");
                Label lblDireccionInfo = new Label(h.getDireccionHotel());
                HBox panelDireccion = new HBox();
                Button informacion = new Button("+ Informacion");
                Button reserva = new Button("+ Reservar");
                reserva.setOnAction(e -> botonReserva(h));
                informacion.setOnAction(e -> presentarBotonInfo(h));
                Button mapa = new Button("Ver mapa");
                mapa.setOnAction(e->botonMapa(h));
                ImageView i = new ImageView(h.getFotoHotel());
                i.setFitHeight(120);
                i.setFitWidth(120);
                paneli.setSpacing(30);
                panelh.setSpacing(20);
                panelDireccion.getChildren().addAll(lblDireccion,lblDireccionInfo);
                botones.setSpacing(10);
                botones.getChildren().addAll(informacion,mapa,reserva);
                paneli.getChildren().addAll(lblNombre,panelDireccion,botones);
                panelh.getChildren().addAll(i,paneli);
                panelHoteles.getChildren().add(panelh);
            }
             b.grabarHTML("src/HTML/prueba.html");
             Desktop.getDesktop().browse(Paths.get("src/HTML/prueba.html").toAbsolutePath().toUri());
        } catch (IOException ex) {
            Logger.getLogger(BusquedaUbicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    //Evento boton mapa abre una venta en Google de la ubicacion del hotel
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
    //Evento boton info abre una ventana con la informacion del hotel
    public void presentarBotonInfo(Hotel h){
        Stage s = new Stage();
        s.setTitle(h.getNombreHotel());
        Scene sc = new Scene(new VentanaHotel(h).getRoot(),300,450);
        s.setScene(sc);
        s.show();
    }
    //Evento boton reserva hotel abre una venta donde se permite reservar el hotel
    public void botonReserva(Hotel h){
        Stage s = new Stage();
        s.setTitle(h.getNombreHotel());
        Scene sc = new Scene(new VentanaReserva(h).getRoot(),700,200);
        s.setScene(sc);
        s.show();
    }
    //Getter del root
    public VBox getRoot() {
        return root;
    }
    
    
}
