
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.Thread.sleep;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Luciano
 */
public class Surtidor {
    
    public String nombre;
    static final String HOST = "localhost";
    static final int PUERTO = 5001;
    public boolean ocupado = false;
    public int valor;
    
    public Surtidor() throws InterruptedException{
        
        generarCarga(1, true);
        
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
    
    public void generarCarga(int cantidad, boolean litros) throws InterruptedException
    {
        int valorActual=714; //= consulta bd;
        
        this.ocupado=true;
        if (litros) {
            sleep(cantidad);
        }
        else{
            cantidad = cantidad/this.valor;
            sleep(cantidad);
        }
        int total=cantidad*valorActual;
        this.ocupado=false;
        try{
            Socket skCliente = new Socket(HOST, PUERTO);
            InputStream aux = skCliente.getInputStream();
            DataInputStream flujo = new DataInputStream( aux );
            DataOutputStream dOut = new DataOutputStream(skCliente.getOutputStream());
            
            String mensaje = "generarCarga"+" "+cantidad+" "+litros+" "+nombre+" "+total; //Instruccion + litros de carga + nombre surtidor

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
        new Surtidor();
    }
    
}
