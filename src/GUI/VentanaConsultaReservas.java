/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import proyectosegundoparcial.Cliente;
import proyectosegundoparcial.Hotel;
import proyectosegundoparcial.ProyectoSegundoParcial;
import proyectosegundoparcial.Reservas;

/**
 *
 * @author Pc
 */
 //Creamos la clase Consulta reservas que permite consultar las reservas hecha
public class VentanaConsultaReservas {
     private VBox root = new VBox();
     private Label lblTitulo = new Label("CONSULTA DE RESERVAS");
     private Label lblMetodo = new Label("Metodo de busqueda: ");
     private ComboBox metodos = new ComboBox();
     private HBox panelBusqueda = new HBox();
     private Label lblFecha = new Label("Ingrese Fecha: ");
     private TextField txtFecha = new TextField();
     private Button bttFecha = new Button("Buscar");
     private ComboBox hoteles = new ComboBox();
     private Label lblHotel = new Label("Elija un Hotel: ");
     private ComboBox clientes = new ComboBox();
     private Label lblCliente = new Label("Elija un Cliente: ");
     private Pane panelReservas = new Pane();
     private Pane panelBusqueda1 = new Pane();
    //Creamos el constructor
    public VentanaConsultaReservas() {
        organizarControles();
        llenarComboBox();
    }
     
     
     //Organizamos los elementos de la ventana
     public void organizarControles(){
       root.setSpacing(15);
       root.setAlignment(Pos.TOP_CENTER);
       panelBusqueda.getChildren().addAll(lblMetodo,metodos,panelBusqueda1);
       panelBusqueda.setSpacing(10);
       root.getChildren().addAll(lblTitulo,panelBusqueda,panelReservas);
    }
    // Llenamos el combo box de metodos y definimos su evento
    public void llenarComboBox(){
        metodos.getItems().addAll("Fecha","Hotel","Cliente");
        metodos.setOnAction(e ->comboBoxMetodos((String)metodos.getValue()));
    }
    //Genera un buscador dependiendo el metodo seleccionado
    public void comboBoxMetodos(String metodo){
        panelReservas.getChildren().clear();
        panelBusqueda1.getChildren().clear();
        if(metodo.equals("Fecha")){
            HBox panel = new HBox(lblFecha,txtFecha,bttFecha);
            panel.setSpacing(10);
            panelBusqueda1.getChildren().add(panel);
            bttFecha.setOnAction(e -> botonFecha(txtFecha.getText()));
        }
        if(metodo.equals("Hotel")){
            HBox panel = new HBox(lblHotel,hoteles);
            hoteles.getItems().addAll(ProyectoSegundoParcial.mHoteles.values());
            panel.setSpacing(10);
            panelBusqueda1.getChildren().add(panel);
            hoteles.setOnAction(e -> comboBoxHoteles((Hotel) hoteles.getValue()));
        }
        if(metodo.equals("Cliente")){
            HBox panel = new HBox(lblCliente,clientes);
            clientes.getItems().addAll(ProyectoSegundoParcial.clientes);
            panel.setSpacing(10);
            panelBusqueda1.getChildren().add(panel);
            clientes.setOnAction(e -> comboBoxClientes((Cliente) clientes.getValue()));
        }
    }
    // Busca por Fecha
    public void botonFecha(String fecha){
        panelReservas.getChildren().clear();
        VBox panelR = new VBox();
        for (Reservas r:ProyectoSegundoParcial.reservas){
            if(r.getFechas().equals(fecha)){
                VBox panelI = new VBox();
                panelR.setSpacing(10);
                Label lblNombreH = new Label("Reserva del Hotel: "+r.getHotel());
                Label lblNombreC = new Label("Reservado por: "+r.getCliente());
                Label lblFechaR = new Label("Reservado para el: "+r.getFechas());
                Label lblNombreHa = new Label("Habitacion tipo: "+r.getHabitacion());
                Label lblPrecio = new Label("Por el precio de: "+r.getPrecio());
                panelI.getChildren().addAll(lblNombreH,lblNombreC,lblFechaR,lblNombreHa,lblPrecio);
                panelR.getChildren().add(panelI);
            }
        }
        panelReservas.getChildren().addAll(panelR);
    }
    //Busca por Hoteles
    public void comboBoxHoteles(Hotel h){
        panelReservas.getChildren().clear();
        VBox panelR = new VBox();
        panelR.setSpacing(50);
        for (Reservas r:ProyectoSegundoParcial.reservas){
            if(r.getHotel().equals(h)){
                VBox panelI = new VBox();
                panelR.setSpacing(10);
                Label lblNombreH = new Label("Reserva del Hotel: "+r.getHotel());
                Label lblNombreC = new Label("Reservado por: "+r.getCliente());
                Label lblFechaR = new Label("Reservado para el: "+r.getFechas());
                Label lblNombreHa = new Label("Habitacion tipo: "+r.getHabitacion());
                Label lblPrecio = new Label("Por el precio de: "+r.getPrecio());
                panelI.getChildren().addAll(lblNombreH,lblNombreC,lblFechaR,lblNombreHa,lblPrecio);
                panelR.getChildren().add(panelI);
            }
        }
        panelReservas.getChildren().addAll(panelR);
        
    }
    //Busca por clientes
    public void comboBoxClientes(Cliente c){
       panelReservas.getChildren().clear();
        VBox panelR = new VBox();
        panelR.setSpacing(50);
        for (Reservas r:ProyectoSegundoParcial.reservas){
            if(r.getCliente().equals(c)){
                VBox panelI = new VBox();
                panelR.setSpacing(10);
                Label lblNombreH = new Label("Reserva del Hotel: "+r.getHotel());
                Label lblNombreC = new Label("Reservado por: "+r.getCliente());
                Label lblFechaR = new Label("Reservado para el: "+r.getFechas());
                Label lblNombreHa = new Label("Habitacion tipo: "+r.getHabitacion());
                Label lblPrecio = new Label("Por el precio de: "+r.getPrecio());
                panelI.getChildren().addAll(lblNombreH,lblNombreC,lblFechaR,lblNombreHa,lblPrecio);
                panelR.getChildren().add(panelI);
            }
        }
        panelReservas.getChildren().addAll(panelR);
    }
    //getter del root
    public VBox getRoot() {
        return root;
    }
    
}
