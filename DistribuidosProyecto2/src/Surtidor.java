
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.sql.SQLException;
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
public class Surtidor extends Application{
    
    public static String nombre;
    static final String HOST = "localhost";
    static final int PUERTO = 5001;
    public static boolean ocupado = false;
    public static int valor93=123;
    public static int valor95=234;
    public static int valor97=345;
    public static int valorDiesel=546;
    public static int valorKerosene=567;
    
    login l = new login();
    
    public Surtidor() throws InterruptedException{
        
        
        
    }
    
    public void conectar(){
        
            try{
            Socket skCliente = new Socket(HOST, PUERTO);
            InputStream aux = skCliente.getInputStream();
            DataInputStream flujo = new DataInputStream( aux );
            DataOutputStream dOut = new DataOutputStream(skCliente.getOutputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            //Stage stage = new Stage();
          //  launch();
            //this.start(stage);
            System.out.print("Eliga una operación: ");
            String mensaje = reader.readLine();

            dOut.writeUTF(mensaje);
            System.out.println( flujo.readUTF() );
            skCliente.close();
        } catch(Exception e ) {
            System.out.println( e.getMessage() );
        }
    }
    
    public void actualizarPrecios() throws SQLException
    {
        this.valor93= l.obtenerPrecio("valor93");;//preguntar a base de datos
        this.valor95= l.obtenerPrecio("valor95");;//preguntar a base de datos
        this.valor97= l.obtenerPrecio("valor97");;//preguntar a base de datos
        this.valorDiesel=l.obtenerPrecio("valorDiesel");;//preguntar a base de datos  
        this.valorKerosene=l.obtenerPrecio("valorKerosene");;//preguntar a base de datos
    }
    
    public static void generarCarga(int cantidad, boolean litros, String tipo) throws InterruptedException
    {
        int  valorActual=0; //= consulta bd;
        switch (tipo)
        {
            case "1":   //93
                valorActual=this.valor93; //= consulta bd;
                break;
            case "2":   //95
                valorActual=Surtidor.valor95; //= consulta bd;
                break;
            case "3":   //97
                valorActual=Surtidor.valor97; //= consulta bd;
                break;
            case "4":   //Diesel
                valorActual=Surtidor.valorDiesel; //= consulta bd;
                break;
            case "5":   //Kerosene
                valorActual=Surtidor.valorKerosene; //= consulta bd;
                break;
                    
        }        
        
        Surtidor.ocupado=true;
        if (litros) {
            sleep(cantidad);
        }
        else{
            cantidad = cantidad/valorActual;
            sleep(cantidad);
        }
        int total=cantidad*valorActual;
        Surtidor.ocupado=false;
        try{
            Socket skCliente = new Socket(HOST, PUERTO);
            InputStream aux = skCliente.getInputStream();
            DataInputStream flujo = new DataInputStream( aux );
            DataOutputStream dOut = new DataOutputStream(skCliente.getOutputStream());
            
            String mensaje = "generarCarga"+" "+cantidad+" "+litros+" "+ Surtidor.nombre +" "+total; //Instruccion + litros de carga + nombre surtidor

            dOut.writeUTF(mensaje);
            System.out.println( flujo.readUTF() );
            skCliente.close();
        } catch(Exception e ) {
            System.out.println( e.getMessage() );
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        launch();
    }

    public static int getValor93() {
        return valor93;
    }

    public static void setValor93(int valor93) {
        Surtidor.valor93 = valor93;
    }

    public static int getValor95() {
        return valor95;
    }

    public static void setValor95(int valor95) {
        Surtidor.valor95 = valor95;
    }

    public static int getValor97() {
        return valor97;
    }

    public static void setValor97(int valor97) {
        Surtidor.valor97 = valor97;
    }

    public static int getValorDiesel() {
        return valorDiesel;
    }

    public static void setValorDiesel(int valorDiesel) {
        Surtidor.valorDiesel = valorDiesel;
    }

    public static int getValorKerosene() {
        return valorKerosene;
    }

    public static void setValorKerosene(int valorKerosene) {
        Surtidor.valorKerosene = valorKerosene;
    }
    
    
    

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("V1_FXML.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
