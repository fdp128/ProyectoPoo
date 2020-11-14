/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import proyectosegundoparcial.Cliente;
import proyectosegundoparcial.ProyectoSegundoParcial;

/**
 *
 * @author Pc
 */
 //Creamos la clase ventana clientes que permite registrar un cliente
public class VentanaClientes {
    
    private VBox root = new VBox();
    private Label lblTitulo = new Label("INSERTE DATOS: ");
    private Label lblCedula = new Label("Cedula: ");
    private TextField txtCedula = new TextField();
    private HBox panelCedula = new HBox();
    private Label lblNombre = new Label("Nombre: ");
    private TextField txtNombre = new TextField();
    private HBox panelNombre = new HBox();
    private Label lblDireccion = new Label("Direccion: ");
    private TextField txtDireccion = new TextField();
    private HBox panelDireccion = new HBox();
    private Label lblCorreo = new Label("Corre: ");
    private TextField txtCorreo = new TextField();
    private HBox panelCorreo = new HBox();
    private Button agregar = new Button("Agregar Cliente"); 
    //creamos constructor
    public VentanaClientes(){
        organizarControles();
        botonAgregar();
        
    }
    //Organizamos la ventana
    public void organizarControles(){
       root.setSpacing(15);
       root.setAlignment(Pos.TOP_CENTER);
       panelCedula.getChildren().addAll(lblCedula,txtCedula);
       panelNombre.getChildren().addAll(lblNombre,txtNombre);
       panelDireccion.getChildren().addAll(lblDireccion,txtDireccion);
       panelCorreo.getChildren().addAll(lblCorreo,txtCorreo);
       root.getChildren().addAll(lblTitulo,panelCedula,panelNombre,panelDireccion,panelCorreo,agregar);
    }
    //Agregamos el cliente a una lista
    public void agregarCliente(){
        String cedula = txtCedula.getText();
        String nombre = txtNombre.getText();
        String direccion = txtDireccion.getText();
        String correo = txtCorreo.getText();
        if(cedula.equals("")|| nombre.equals("") || direccion.equals("") || correo.equals("")){
            Stage error = new Stage();
            Scene s = new Scene(new Label("                Llene todo los datos"),200,40);
            error.setTitle("Error ingreso de datos");
            error.setScene(s);
            error.show();
        }else{
            Cliente c = new Cliente(cedula,nombre,direccion,correo);
            ProyectoSegundoParcial.clientes.add(c);
            txtCedula.clear();  
            txtNombre.clear();
            txtDireccion.clear();
            txtCorreo.clear();
            
        }
    }
    //Se define el evento del boton agregar
    public void botonAgregar(){
        agregar.setOnAction(e->agregarCliente());
    }
    //getter del root
    public VBox getRoot() {
        return root;
    }
    
}
