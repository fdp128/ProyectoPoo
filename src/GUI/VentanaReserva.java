/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import proyectosegundoparcial.Ciudad;
import proyectosegundoparcial.Cliente;
import proyectosegundoparcial.Habitacion;
import proyectosegundoparcial.Hotel;
import proyectosegundoparcial.ProyectoSegundoParcial;
import proyectosegundoparcial.Reservas;

/**
 *
 * @author Pc
 */
 //Creamos la clase Ventana reserva que permite realizar una reserva
public class VentanaReserva {
    private Hotel hotel;
    private VBox root = new VBox();
    private Label lblTitulo;
    private Label lblFecha = new Label("Fecha(dd/mm/aa): ");
    private TextField txtFecha = new TextField();
    private HBox panelFecha = new HBox();
    private Label lblHabitacion = new Label("Habitacion: ");
    private ComboBox<Habitacion> habitaciones = new ComboBox<>();
    private ComboBox<String> tarifas = new ComboBox<>();
    private Label lblTarifa = new Label("Tarifas de la Habitacion: ");
    private Label lblPrecio = new Label("");
    private HBox panelHabitacion = new HBox();
    private Label lblCliente = new Label("Cliente: ");
    private ComboBox<Cliente> clientes = new ComboBox<>();
    private HBox panelCliente = new HBox();
    private Button bttReserva = new Button("RESERVAR"); 
    // Creamos constructor
    public VentanaReserva(Hotel h){
       hotel = h;
       lblTitulo = new Label("RESERVA DE HOTEL: "+hotel.getNombreHotel());
       organizarControles();
       llenarComboBoxs();
       bttReserva.setOnAction(e -> botonReserva());
    }
    //Organizamos los elementos de la ventana
    public void organizarControles(){
       root.setSpacing(15);
       root.setAlignment(Pos.TOP_CENTER);
       lblTitulo.setAlignment(Pos.TOP_CENTER);
       bttReserva.setAlignment(Pos.CENTER);
       panelFecha.setSpacing(10);
       panelHabitacion.setSpacing(50);
       panelCliente.setSpacing(70);
       panelFecha.getChildren().addAll(lblFecha,txtFecha);
       panelHabitacion.getChildren().addAll(lblHabitacion,habitaciones,lblTarifa,tarifas,lblPrecio);
       panelCliente.getChildren().addAll(lblCliente,clientes);
       lblTitulo.setAlignment(Pos.TOP_CENTER);
       root.getChildren().addAll(lblTitulo,panelFecha,panelHabitacion,panelCliente,bttReserva);
    }
    // llenamos el combo box de habitaciones y clientes
    public void llenarComboBoxs(){
        habitaciones.getItems().addAll(hotel.getHabitaciones());
        clientes.getItems().addAll(ProyectoSegundoParcial.clientes);
        habitaciones.setOnAction(e -> comboBoxHabitaciones(habitaciones.getValue()));
    }
    //Reliza la reserva de un hotel no existente y lo guarda en una lista
    public void botonReserva(){
        if(txtFecha.getText().equals("")||habitaciones.getValue()==null||clientes.getValue()==null||tarifas.getValue()==null){
            Stage error = new Stage();
            Scene s = new Scene(new Label("                Llene todo los datos"),200,40);
            error.setTitle("Error ingreso de datos");
            error.setScene(s);
            error.show();
        }else if(txtFecha.getText().split("/").length!=3||Integer.parseInt(txtFecha.getText().split("/")[0])>31||Integer.parseInt(txtFecha.getText().split("/")[1])>12||Integer.parseInt(txtFecha.getText().split("/")[2])<2020){
            Stage error2 = new Stage();
            Scene s = new Scene(new Label("            La fecha ingresada es incorrecta"),250,40);
            error2.setTitle("Error ingreso de datos");
            error2.setScene(s);
            error2.show();       
        }else{
            boolean reservaExistente = false;
            double precio = 0;
            if(tarifas.getValue().equals("Tarifa Sencilla")){
                precio = habitaciones.getValue().getTarifaSencilla();
            }
            if(tarifas.getValue().equals("Tarifa Doble")){
                precio = habitaciones.getValue().getTarifaDoble();
            }
            if(tarifas.getValue().equals("Tarifa Triple")){
                precio = habitaciones.getValue().getTarifaTriple();
            }
            Reservas reserva = new Reservas(hotel,txtFecha.getText(),habitaciones.getValue(),clientes.getValue(),precio);
            for (Reservas r:ProyectoSegundoParcial.reservas){
                if (reserva.equals(r)){
                    Stage error3 = new Stage();
                    Scene s = new Scene(new Label("          Esta Reserva ya fue Realizada"),250,40);
                    error3.setTitle("Reserva Existente");
                    error3.setScene(s);
                    error3.show();
                    reservaExistente = true;
                }
            }
            if (!(reservaExistente)){
                ProyectoSegundoParcial.reservas.add(reserva); 
                txtFecha.setText("");
                habitaciones.setValue(null);
                clientes.setValue(null);
            }
        }    
    } 
    //Evento de habitaciones llena el combobox de tarifas 
    public void comboBoxHabitaciones(Habitacion h){
        lblPrecio.setText("");
        tarifas.getItems().clear();
        if(h.getTarifaSencilla()!=0){
            tarifas.getItems().add("Tarifa Sencilla");
        }
        if(h.getTarifaDoble()!=0){
            tarifas.getItems().add("Tarifa Doble");
        }
        if(h.getTarifaTriple()!=0){
            tarifas.getItems().add("Tarifa Triple");
        }
        tarifas.setOnAction(e -> comboBoxTarifas(h,tarifas.getValue()));
    }
    //Evento de tarifas muestra el precio de la tarifa elejida
    public void comboBoxTarifas(Habitacion h,String tarifa){
        lblPrecio.setText("");
        if(tarifa.equals("Tarifa Sencilla")){
            lblPrecio.setText(h.getTarifaSencilla()+" $");
        }
        if(tarifa.equals("Tarifa Doble")){
            lblPrecio.setText(h.getTarifaDoble()+" $");
        }
        if(tarifa.equals("Tarifa Triple")){
            lblPrecio.setText(h.getTarifaTriple()+" $");
        }
    }
    //getter del root
    public VBox getRoot() {
        return root;
    }
    
    
}
