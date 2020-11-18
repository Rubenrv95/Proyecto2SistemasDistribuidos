
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
            int opcion=1;
            while (opcion!=0) {
                System.out.println("asdda");
                Socket skCliente = skServidor.accept(); // Crea objeto
               // System.out.println("Sirvo al cliente " + numCli);
                OutputStream aux = skCliente.getOutputStream();
                DataOutputStream flujo= new DataOutputStream( aux );
                DataInputStream dIn = new DataInputStream(skCliente.getInputStream());
                flujo.writeUTF( "consulta resivida: "+dIn.readUTF());
                System.out.println("asd");
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
