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
    
    static String myIP;
    static final String HOSTsrv = "192.168.1.126"; //empresa
    static final int PUERTOsrv = 5000;
    static final String nombre = "nombre";
    
    public Servicentro() throws IOException
    {
        iniciarListener();
    }
    
    public void conseguirIP() throws MalformedURLException, IOException
    {
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
        String ip = in.readLine(); //you get the IP as a String
        System.out.println(ip);
        myIP = ip;
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
        Parent root = FXMLLoader.load(getClass().getResource("V2_FXML.fxml"));        
        Scene scene = new Scene(root);       
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        launch();
        
    }


    
}
