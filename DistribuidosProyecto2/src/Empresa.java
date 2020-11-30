
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import javafx.application.Application;
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
public class Empresa extends Application {

  //  static final String HOST = "empresa";
    static final int PUERTO=5000;
    static final int PUERTOsrv=5001;

    static ArrayList<String> hosts = new ArrayList<>();
    
    public Empresa () {
        hosts.add("192.168.1.126");
        //actualizarPrecios();
    }
    
    public static void actualizarPrecios(int noventaYTres, int noventaYCinco, int noventaYSiete, int diesel, int kerosene)
    {
        for (int i = 0; i < hosts.size(); i++) {
        try{
            Socket skCliente = new Socket(hosts.get(i), PUERTOsrv);
            InputStream aux = skCliente.getInputStream();
            DataInputStream flujo = new DataInputStream( aux );
            DataOutputStream dOut = new DataOutputStream(skCliente.getOutputStream());
            String mensaje = "actualizarPrecios "+noventaYTres+" "+noventaYCinco+" "+noventaYSiete+" "+diesel+" "+kerosene;
            dOut.writeUTF(mensaje);
            System.out.println( flujo.readUTF() );
            skCliente.close();
        } catch(Exception e ) {
            System.out.println( e.getMessage() );
        }
    }

    
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("V3_FXML.fxml"));        
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
        new Empresa();
    }
    
}
