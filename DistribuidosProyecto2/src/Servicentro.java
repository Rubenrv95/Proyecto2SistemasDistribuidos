import java.io.*;
import java.net.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Luciano
 */
public class Servicentro extends Application {
    
    
    static final String HOSTsrv = "192.168.1.126"; //empresa
    static final int PUERTOsrv = 5000;
    static final String nombre = "nombre";
    
    public Servicentro()
    {
        iniciarListener();
    }
    
    public void generarReporte()
    {
        
    }
    
    public void calcularFactorUtilidad()
    {
        
    }
    

    private void iniciarListener() {
        Thread hilo;
        hilo = new ServicentroCliente(this.nombre);
        hilo.start();
       
    }
       

    
    public void ingresarCarga(String mensaje)
    {
        
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("V1_FXML.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        new Servicentro();
    }


    
}
