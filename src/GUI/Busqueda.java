/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Pc
 */
//Creamos la clase Busqueda que va ser el interfaz de nuestro programa
public class Busqueda {
    private VBox root = new VBox();
    private ScrollPane root1 = new ScrollPane();
    private VBox panelPrincipal = new VBox();
    private Button bttRegistrarCliente = new Button("REGISTRAR CLIENTE");
    private Button bttConsultarReserva = new Button("CONSULTAR RESERVA"); 
    private Button bttBuscarPorProvincia = new Button("BUSCAR POR PROVINCIA"); 
    private Button bttBuscarPorNombre = new Button("BUSCAR POR NOMBRE"); 
    private Button bttBuscarPorUbicacion = new Button("BUSCAR POR PROXIMIDAD A LUGAR"); 
    private HBox buscar = new HBox();
    private HBox panelSuperior = new HBox();
    private VBox panel = new VBox();
    
    //Creamo el cosntructor
    public Busqueda()throws IOException{
        organizarControles();
        botones();
        
    }
    //Organizamos la Ventana
    public void organizarControles(){
       panelPrincipal.setSpacing(10);
       panelPrincipal.setAlignment(Pos.TOP_CENTER);
       buscar.setAlignment(Pos.TOP_CENTER);
       buscar.setSpacing(10);
       panelSuperior.setSpacing(10);
       bttBuscarPorProvincia.setAlignment(Pos.CENTER);
       bttBuscarPorNombre.setAlignment(Pos.CENTER);
       bttBuscarPorUbicacion.setAlignment(Pos.CENTER);
       bttBuscarPorProvincia.setMinSize(225, 30);
       bttBuscarPorNombre.setMinSize(225, 30);
       bttBuscarPorUbicacion.setMinSize(225, 30);
       bttBuscarPorProvincia.setMaxSize(225, 30);
       bttBuscarPorNombre.setMaxSize(225, 30);
       bttBuscarPorUbicacion.setMaxSize(225, 30);
       panelSuperior.setAlignment(Pos.TOP_CENTER);
       panelSuperior.getChildren().addAll(bttRegistrarCliente,bttConsultarReserva );
       buscar.getChildren().addAll(bttBuscarPorProvincia,bttBuscarPorNombre,bttBuscarPorUbicacion);
       panelPrincipal.getChildren().addAll(panelSuperior,buscar,panel);
       root1.setContent(panelPrincipal);
       root1.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
       root1.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
       root1.setPannable(true);
       root.getChildren().addAll(root1);
    }
    
    //Creamos los Eventos de los Botones
    public void botones(){
        bttRegistrarCliente.setOnAction(e->botonRegistrar());
        bttConsultarReserva.setOnAction(e->botonConsultar());
        bttBuscarPorProvincia.setOnAction(e->botonBProvincia());
        bttBuscarPorNombre.setOnAction(e->botonBNombre());
        bttBuscarPorUbicacion.setOnAction(e->{
            try {
                botonBUbicacion();
            } catch (IOException ex) {
                Logger.getLogger(Busqueda.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    //Creamos el Evento del boton consultar que abre una venta para consultar las Reservas Hechas
    public void botonConsultar(){
        panel.getChildren().clear();
        panel.getChildren().add(new VentanaConsultaReservas().getRoot());
    }
    //Creamos el Evento del boton Provincia que permite buscar el hotel por Provincia
    public void botonBProvincia(){
        panel.getChildren().clear();
        panel.getChildren().add(new BusquedaJARA().getRoot());
    }
    // Creamos el evento del boton Nombre que permite buscar el hotel por su nombre o servcio
    public void botonBNombre(){
        panel.getChildren().clear();
        panel.getChildren().add(new BusquedaNombreHotel().getRoot());
    }
    //Creamo el evento del boton Ubicacion que permite buscar el hotel por cercania a una ubicacion
    public void botonBUbicacion() throws IOException {
        panel.getChildren().clear();
        panel.getChildren().add(new BusquedaUbicacion().getRoot());
    }
    // Creamos el evento del boton Registrar Cliente que abre una ventana para crear un cliente
    public void botonRegistrar(){
        panel.getChildren().clear();
        panel.getChildren().add(new VentanaClientes().getRoot());
    }
    // Creamos el getter del Root
    public VBox getRoot() {
        return root;
    }
    
    
}
