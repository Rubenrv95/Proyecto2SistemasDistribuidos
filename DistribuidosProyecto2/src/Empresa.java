
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
public class Empresa {

  //  static final String HOST = "empresa";
    static final int PUERTO=5000;
    
    public Empresa () {
        try {
            ServerSocket skServidor = new ServerSocket( PUERTO );
            System.out.println("Escucho el puerto " + PUERTO );
            Servicentro.main(new String[0]);
            //Process p = Runtime.getRuntime().exec("javac Servicentro.java");
            //Process p2 = Runtime.getRuntime().exec("java Servicentro");
            int opcion=1;
            while (opcion!=0) {
                System.out.println("Esperando cliente");
                Socket skCliente = skServidor.accept(); // Crea objeto
               // System.out.println("Sirvo al cliente " + numCli);
                OutputStream aux = skCliente.getOutputStream();
                DataOutputStream flujo= new DataOutputStream( aux );
                DataInputStream dIn = new DataInputStream(skCliente.getInputStream());
                flujo.writeUTF( "consulta resivida: "+dIn.readUTF());
                skCliente.close();
        }
        System.out.println("Demasiados clientes por hoy");
        } catch( Exception e ) {
            System.out.println( e.getMessage() );
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Empresa();
    }
    
}
