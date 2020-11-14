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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import proyectosegundoparcial.Habitacion;
import proyectosegundoparcial.Hotel;
import proyectosegundoparcial.ProyectoSegundoParcial;
import proyectosegundoparcial.Servicio;
/**
 *
 * @author Pc
 */
 //Creamos la clase Busqueda or nombre que permite buscar un hotel por nombre
public class BusquedaNombreHotel {
    private VBox root = new VBox();
    private HBox panelBusqueda = new HBox();
    private TextField txtEntradas = new TextField();
    private Label lblBusqueda = new Label("Busqueda por Nombre de Hotel: ");
    private VBox panelHoteles = new VBox();
    private Button bttBuscar = new Button("BUSCAR"); 
    private Label lblServicio = new Label("Busqueda por Servicio: ");
    private ComboBox servicios = new ComboBox();
    //Creamos el contrcutor.
    public BusquedaNombreHotel(){
        organizarControles();
        llenarPanelHoteles();
    }
    //Organizamo los elementos de la ventana
    public void organizarControles(){
       panelHoteles.setSpacing(35);
       panelBusqueda.setSpacing(5);
       panelBusqueda.setAlignment(Pos.TOP_CENTER);
       root.setSpacing(20);
       root.setAlignment(Pos.TOP_CENTER);
       panelBusqueda.getChildren().addAll(lblBusqueda,txtEntradas,bttBuscar,lblServicio,servicios);
       root.getChildren().addAll(panelBusqueda,panelHoteles);
    }
    // Llenamos el panel hoteles al dar click en servicios
    public void llenarPanelHoteles(){
        bttBuscar.setOnAction(e->botonBuscar());
        servicios.getItems().addAll(ProyectoSegundoParcial.servicios);
        servicios.setOnAction(e -> comboBoxServicios((String)servicios.getValue()));
    }
    //Creamos el metodo de boton mapa que recibe de parametro un hotel
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
    //Creamos el metodo donde creamos las imagenes y la informacion y boton de mapa del hotel
    private void botonBuscar(){
        panelHoteles.getChildren().clear();
        for (Hotel h:ProyectoSegundoParcial.mHoteles.values()){
            int resultado = h.getNombreHotel().indexOf(txtEntradas.getText());
            if(resultado != -1) {
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
                ImageView i = new ImageView(h.getFotoHotel());
                i.setFitHeight(120);
                i.setFitWidth(120);
                Thread t1= new Thread(new Hilo1(i,h.getFotoHotel()));
                t1.start();
                paneli.setSpacing(30);
                panelh.setSpacing(20);
                botones.setSpacing(10);
                panelDireccion.getChildren().addAll(lblDireccion,lblDireccionInfo);
                botones.getChildren().addAll(informacion,mapa,reserva);
                paneli.getChildren().addAll(lblNombre,panelDireccion,botones);
                panelh.getChildren().addAll(i,paneli);
                panelHoteles.getChildren().add(panelh);
            }
        }
    }
    //Presentamos la infomracion desdripcion del hotel
    public void presentarBotonInfo(Hotel h){
        Stage s = new Stage();
        s.setTitle(h.getNombreHotel());
        Scene sc = new Scene(new VentanaHotel(h).getRoot(),300,450);
        s.setScene(sc);
        s.show();
    }
    // llenamos los servicios que recibe de parametro un String Servicio
    public void comboBoxServicios(String servicio){
        panelHoteles.getChildren().clear();
        for (Servicio s:ProyectoSegundoParcial.mServicios.values()){
            if(servicio.equals(s.getCatalogo())){
                for(Hotel h:ProyectoSegundoParcial.mHoteles.values()){
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
                    ImageView i = new ImageView(h.getFotoHotel());
                    i.setFitHeight(120);
                    i.setFitWidth(120);
                    paneli.setSpacing(30);
                    panelh.setSpacing(20);
                    botones.setSpacing(10);
                    panelDireccion.getChildren().addAll(lblDireccion,lblDireccionInfo);
                    botones.getChildren().addAll(informacion,mapa,reserva);
                    paneli.getChildren().addAll(lblNombre,panelDireccion,botones);
                    panelh.getChildren().addAll(i,paneli);
                    panelHoteles.getChildren().add(panelh);
                }
            }      
        }  
    }
    // Creamos el metodo boton Reserva que recibe un hotel
    public void botonReserva(Hotel h){
        Stage s = new Stage();
        s.setTitle(h.getNombreHotel());
        Scene sc = new Scene(new VentanaReserva(h).getRoot(),700,200);
        s.setScene(sc);
        s.show();
    }
  //getter del root 
    public VBox getRoot() {
        return root;
    }
    //Creamos una clae hilo para ejecutar las imagenes sin demora
    class Hilo1 implements Runnable{
        private ImageView imag;
        private String urlImag;

        public Hilo1(ImageView imag,String urlImag) {
            this.imag = imag;
            this.urlImag=urlImag;
        }
    
   //Sobreescribimos el metodo del run para que se cambie las imagenes
        @Override
        public void run() {
            imag.setImage(new Image(urlImag));
        }

    
}
    
    
}
