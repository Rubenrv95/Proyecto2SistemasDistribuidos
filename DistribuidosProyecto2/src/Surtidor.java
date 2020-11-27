
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.Thread.sleep;
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
public class Surtidor {
    
    public String nombre;
    static final String HOST = "localhost";
    static final int PUERTO = 5001;
    public boolean ocupado = false;
    public int valor93;
    public int valor95;
    public int valor97;
    public int valorDiesel;
    public int valorKerosene;
    
    login l = new login();
    
    public Surtidor() throws InterruptedException{
        
        generarCarga(1, true, "Bencina");
        
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
    
    public void generarCarga(int cantidad, boolean litros, String tipo) throws InterruptedException
    {
        int valorActual=0; //= consulta bd;
        switch (tipo)
        {
            case "1":   //93
                valorActual=this.valor93; //= consulta bd;
                
                break;
            case "2":   //95
                valorActual=this.valor95; //= consulta bd;
                break;
            case "3":   //97
                valorActual=this.valor97; //= consulta bd;
                break;
            case "4":   //Diesel
                valorActual=this.valorDiesel; //= consulta bd;
                break;
            case "5":   //Kerosene
                valorActual=this.valorKerosene; //= consulta bd;
                break;
                    
        }        
        
        this.ocupado=true;
        if (litros) {
            sleep(cantidad);
        }
        else{
            cantidad = cantidad/valorActual;
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
