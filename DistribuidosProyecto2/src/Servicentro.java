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
    
    int valor93;
    int valor95;
    int valor97;
    int valorDiesel;
    int valorKerosene;
    
    static final String HOST = "localhost";
    static final int PUERTO=5000;
    
    public Servicentro()
    {
        try{
        Socket skCliente = new Socket(HOST, PUERTO);
        InputStream aux = skCliente.getInputStream();
        DataInputStream flujo = new DataInputStream( aux );
        DataOutputStream dOut = new DataOutputStream(skCliente.getOutputStream());

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //Stage stage = new Stage();
        launch();
        //this.start(stage);
        System.out.print("Eliga una operación: ");
        String mensaje = "test";

        dOut.writeUTF(mensaje);
        System.out.println( flujo.readUTF() );
        skCliente.close();
        } catch(Exception e ) {
            System.out.println( e.getMessage() );
        }
    }
    
    public void generarReporte()
    {
        
    }
    
    public void calcularFactorUtilidad()
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
