import java.io.*;
import java.net.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Luciano
 */
public class Servicentro {
    
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
        System.out.print("Eliga una operación]: ");
        String mensaje = "poto";

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
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Servicentro();
    }
    
}
