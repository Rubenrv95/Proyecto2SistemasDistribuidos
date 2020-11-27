
//import static Servicentro.HOSTsrv;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
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
public class ServicentroCliente extends Thread{
    static final int PUERTOclt = 5001;
    static final String HOSTsrv = "192.168.1.126";  

    public ServicentroCliente() {
    }
    

    @Override
    public void run()
    {
        try {
            ServerSocket skServidor = new ServerSocket( PUERTOclt );
            System.out.println("Escucho el puerto " + PUERTOclt );
          //  Servicentro.main(new String[0]);
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
                String mensaje = dIn.readUTF();
                identificadorInstruccion(mensaje.split(" "));
                flujo.writeUTF( "consulta recibida: "+mensaje);
                skCliente.close();
        }
        System.out.println("Demasiados clientes por hoy");
        } catch( Exception e ) {
            System.out.println( e.getMessage() );
        }      
    }
    
        public void identificadorInstruccion(String[] mensaje)
    {
        switch (mensaje[0])
        {
            case "generarCarga":
                //Guardar info en BDD, mensaje[1] litros, mensaje[3] tipo combustible, mensaje[4] Monto a pagar 
                break;
                
            case "actualizarPrecios":
                //Guardar info en BDD, mensaje[1] 93, mensaje[2] 95, mensaje[3] 97, mensaje[4] Diesel, mensaje[5] kerosene
                break;
        }
    }
}
