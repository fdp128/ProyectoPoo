/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosegundoparcial;

import GUI.Busqueda;
import GUI.BusquedaJARA;
import GUI.BusquedaNombreHotel;
import GUI.VentanaClientes;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;

/**
 *
 * @author Pc
 */
 //Creamos clase principal
public class ProyectoSegundoParcial extends Application{
    public static List<Cliente> clientes = new ArrayList<>();
    public static Map<Integer, Ciudad> mCiudades = new HashMap<>();
    public static Map<Integer, Habitacion> mHabitaciones = new HashMap<>(); 
    public static Map<Integer, Hotel> mHoteles = new HashMap<>();
    public static Map<Integer, Provincia> mProvincias = new HashMap<>();
    public static Map<Integer, Servicio> mServicios = new HashMap<>();
    public static List<Reservas> reservas = new ArrayList<>();
    public static List<String> servicios = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
     //Main
    public static void main(String[] args) {
        desSerializar();
        cargarProvincias();
        cargarCiudades();
        cargarHoteles();
        cargarHabitaciones();
        cargarServios();
        cargarCatalogos();
        launch();
        serializar();
    }
    //Carga las ciudades del archivo
    public static void cargarCiudades(){
        try (BufferedReader r = new BufferedReader(new FileReader("src/archivos/ciudades.csv"));){
            String linea;
            r.readLine();
            while ((linea = r.readLine())!= null ){
                String[] a = linea.split("\\|");
                mCiudades.put(Integer.parseInt(a[0]), new Ciudad(a[2]));
                mProvincias.get(Integer.parseInt(a[1])).getCiudades().add(mCiudades.get(Integer.parseInt(a[0])));   
            }
        } catch (FileNotFoundException ex){
            System.err.println("No se encontro el -ciudades.csv- "+ex);
        } catch (IOException ex){
            System.err.println("Error en el metodo cargarCiudades() "+ex);
        }
    }
    //Carga los catalogos
    public static void cargarCatalogos(){
        try (BufferedReader r = new BufferedReader(new FileReader("src/archivos/catalogo.csv"));){
            String linea;
            r.readLine();
            while ((linea = r.readLine())!= null ){
                String[] a = linea.split("\\|");
                servicios.add(a[1]);
                mServicios.get(Integer.parseInt(a[0])).setCatalogo(a[1]);
            }
        } catch (FileNotFoundException ex){
            System.err.println("No se encontro el -ciudades.csv- "+ex);
        } catch (IOException ex){
            System.err.println("Error en el metodo cargarHabitaciones() "+ex);
        }
    }
    //Carga las habitaciones
    public static void cargarHabitaciones(){
        try (BufferedReader r = new BufferedReader(new FileReader("src/archivos/habitaciones.csv"));){
            String linea;
            r.readLine();
            while ((linea = r.readLine())!= null ){
                String[] a = linea.split("\\|");
                double tS = Double.parseDouble(a[3]);
                double tD = Double.parseDouble(a[4]);
                double tT = Double.parseDouble(a[5]);
                mHabitaciones.put(Integer.parseInt(a[0]), new Habitacion(a[2],tS,tD,tT));
                mHoteles.get(Integer.parseInt(a[1])).getHabitaciones().add(mHabitaciones.get(Integer.parseInt(a[0])));
            }
        } catch (FileNotFoundException ex){
            System.err.println("No se encontro el -ciudades.csv- "+ex);
        } catch (IOException ex){
            System.err.println("Error en el metodo cargarHabitaciones() "+ex);
        }
    }
    //Carga los hoteles
    public static void cargarHoteles(){
        try (BufferedReader r = new BufferedReader(new FileReader("src/archivos/hoteles1.csv"));){
            String linea;
            r.readLine();
            while ((linea = r.readLine())!= null ){
                try{
                    String[] a = linea.split("\\|");
                    if (a.length!=12){
                        mHoteles.put(Integer.parseInt(a[0]), new Hotel(a[2],a[3],a[4],a[5],a[6],a[7],a[8],a[9]));
                        mCiudades.get(Integer.parseInt(a[1])).getHoteles().add(mHoteles.get(Integer.parseInt(a[0])));
                        throw new FaltaDatos();  
                    }else{
                        a[10] = a[10].replaceAll(" ", "");
                        a[11] = a[11].replaceAll(" ", "");
                        if(a[10].equals("-")){
                            a[10]="0";
                        }
                        if(a[11].equals("-")){
                            a[11]="0";
                        }
                        mHoteles.put(Integer.parseInt(a[0]), new Hotel(a[2],a[3],a[4],a[5],a[6],a[7],a[8],a[9],a[10],a[11]));
                        mCiudades.get(Integer.parseInt(a[1])).getHoteles().add(mHoteles.get(Integer.parseInt(a[0])));
                    }
                }catch(FaltaDatos ex){
                    System.err.println(ex+" en linea: "+linea);
                }
                
            }
        } catch (FileNotFoundException ex){
            System.err.println("No se encontro el -hoteles1.csv- "+ex);
        } catch (IOException ex){
            System.err.println("Error en el metodo cargarHoteles() "+ex);
        }
    }
    //Carga las provincias
    public static void cargarProvincias(){
        try (BufferedReader r = new BufferedReader(new FileReader("src/archivos/provincias.csv"));){
            String linea;
            r.readLine();
            while ((linea = r.readLine())!= null ){
                String[] a = linea.split("\\|");
                mProvincias.put(Integer.parseInt(a[0]), new Provincia(a[1],a[2],a[3],a[4]));
                
            }
        } catch (FileNotFoundException ex){
            System.err.println("No se encontro el -provincias.csv- "+ex);
        } catch (IOException ex){
            System.err.println("Error en el metodo cargarPovincias() "+ex);
        }
    }
    //Carga los servicios
    public static void cargarServios(){
        try (BufferedReader r = new BufferedReader(new FileReader("src/archivos/servicios.csv"));){
            String linea;
            r.readLine();
            while ((linea = r.readLine())!= null ){
                String[] a = linea.split("\\|");
                mServicios.put(Integer.parseInt(a[2]), new Servicio(a[0],a[3]));
                mHoteles.get(Integer.parseInt(a[1])).getServicios().add(mServicios.get(Integer.parseInt(a[1])));
            }
        } catch (FileNotFoundException ex){
            System.err.println("No se encontro el -servicios-.csv- "+ex);
        } catch (IOException ex){
            System.err.println("Error en el metodo cargarServicios() "+ex);
        }
    }
    //Crea la ventana
    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene s = new Scene(new Busqueda().getRoot(),700,600);
        primaryStage.setTitle("AGENCIA TURISTICA “ CLICK TOURS”");
        primaryStage.setScene(s);
        primaryStage.show();
    }
    //Serializa los clientes y reservas
    public static void serializar(){
        try(ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("src/archivos/reservas.dat"))){
            o.writeObject(reservas);
        }catch(IOException e){
            System.err.println("Error en serializar() "+e);
        }
        try(ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("src/archivos/clientes.dat"))){
            o.writeObject(clientes);
        }catch(IOException e){
            System.err.println("Error en serializar() "+e);
        }
    }
    //deserializa los clientes y reservas
    public static void desSerializar(){
        try(ObjectInputStream i = new ObjectInputStream(new FileInputStream("src/archivos/reservas.dat"))){
            reservas = (ArrayList<Reservas>)i.readObject();
        }catch (FileNotFoundException ex){
            System.err.println("Error en el metodo deserializar() "+ex);
        }catch (IOException ex){
            System.err.println("Error en el metodo deserializar() "+ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProyectoSegundoParcial.class.getName()).log(Level.SEVERE, null, ex);
        }     
        try(ObjectInputStream i = new ObjectInputStream(new FileInputStream("src/archivos/clientes.dat"))){
            clientes = (ArrayList<Cliente>)i.readObject();
        }catch (FileNotFoundException ex){
            System.err.println("Error en el metodo deserializar() "+ex);
        }catch (IOException ex){
            System.err.println("Error en el metodo deserializar() "+ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProyectoSegundoParcial.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
}
