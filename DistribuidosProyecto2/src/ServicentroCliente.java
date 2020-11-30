
//import static Servicentro.HOSTsrv;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;


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
    public String nombre;
    
    private static int valor93;
    private static int valor95;
    private static int valor97;
    private static int valorDiesel;
    private static int valorKerosene;
    
    private static int utilidad93=50;
    private static int utilidad95=50;
    private static int utilidad97=50;
    private static int utilidadDiesel=50;
    private static int utilidadKerosene=50;
    
    login l = new login();

    public ServicentroCliente(String nombre) throws SQLException {       
        this.nombre = nombre;
         l.crearServicentro(nombre);
        actualizarPrecios();

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
                if (mensaje.endsWith("consultarValores")) {
                    flujo.writeUTF(Integer.toString(valor93+(valor93*utilidad93/100))+" "+Integer.toString(valor95+(valor95*utilidad95/100))+" "+Integer.toString(valor97+(valor97*utilidad97/100))+" "+Integer.toString(valorDiesel+(valorDiesel*utilidadDiesel/100))+" "+Integer.toString(valorKerosene+(valorKerosene*utilidadKerosene/100)));
                }
                else
                    flujo.writeUTF( "consulta recibida: "+mensaje);
                skCliente.close();
                identificadorInstruccion(mensaje.split(" "));
                
        }
        System.out.println("Demasiados clientes por hoy");
        } catch( Exception e ) {
            System.out.println( e.getMessage() );
        }      
    }
    
    
    
    
    public void identificadorInstruccion(String[] mensaje) throws SQLException
    {
        System.out.println("ads");
        switch (mensaje[0])
        {
            case "generarCarga":
                
                String[] destiny = new String[5];
                destiny[0] = mensaje[0];
                destiny[1] = mensaje[1];
                destiny[2] = mensaje[2];
                destiny[3] = mensaje[3];
                destiny[4] = this.nombre;
                
                
                l.generarCarga(destiny);
                
                //Guardar info en BDD, mensaje[1] cantidadLitros, mensaje[2] nombreSurtidor, mensaje[3] Monto a pagar, mensaje [4] nobmreServicentro
                
                break;
                
            case "actualizarPrecios":

                l.actualizarPreciosEnBDD(mensaje);
                //Guardar info en BDD, mensaje[1] 93, mensaje[2] 95, mensaje[3] 97, mensaje[4] Diesel, mensaje[5] kerosene
                break;
                
            case "crearSurtidor":
                
                String[] destino = new String[3];
                destino[0] = mensaje[0];
                destino[1] = mensaje[1];
                destino[2] = this.nombre;
                l.crearSurtidor(destino);
                break;
        }              
    }
    
    public static void actualizarStatic() throws SQLException
    {
        Surtidor.actualizarPrecios();
    }
    
    public void actualizarPrecios() throws SQLException
    {
        this.valor93= l.obtenerPrecio("valor93");
        this.valor95= l.obtenerPrecio("valor95");
        this.valor97= l.obtenerPrecio("valor97");
        this.valorDiesel=l.obtenerPrecio("valorDiesel"); 
        this.valorKerosene=l.obtenerPrecio("valorKerosene");
    }

    public static int getValor93() {
        return valor93;
    }

    public static int getValor95() {
        return valor95;
    }

    public static int getValor97() {
        return valor97;
    }

    public static int getValorDiesel() {
        return valorDiesel;
    }

    public static int getValorKerosene() {
        return valorKerosene;
    }

    public static int getUtilidad93() {
        return utilidad93;
    }

    public static void setUtilidad93(int utilidad93) {
        ServicentroCliente.utilidad93 = utilidad93;
    }

    public static int getUtilidad95() {
        return utilidad95;
    }

    public static void setUtilidad95(int utilidad95) {
        ServicentroCliente.utilidad95 = utilidad95;
    }

    public static int getUtilidad97() {
        return utilidad97;
    }

    public static void setUtilidad97(int utilidad97) {
        ServicentroCliente.utilidad97 = utilidad97;
    }

    public static int getUtilidadDiesel() {
        return utilidadDiesel;
    }

    public static void setUtilidadDiesel(int utilidadDiesel) {
        ServicentroCliente.utilidadDiesel = utilidadDiesel;
    }

    public static int getUtilidadKerosene() {
        return utilidadKerosene;
    }

    public static void setUtilidadKerosene(int utilidadKerosene) {
        ServicentroCliente.utilidadKerosene = utilidadKerosene;
    }
    
    
    
    
}
