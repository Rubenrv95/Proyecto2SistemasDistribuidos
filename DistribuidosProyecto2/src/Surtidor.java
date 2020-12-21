
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    
    public static String nombre="5";
    static final String HOST = "localhost";
    static final int PUERTO = 5001;
    public static boolean ocupado = false;
    public static int valor93;
    public static int valor95;
    public static int valor97;
    public static int valorDiesel;
    public static int valorKerosene;
    
    login l = new login();
    
    public Surtidor() throws InterruptedException{
        
    }
    
    
    
    public static boolean conectar(){
        
            try{
            Socket skCliente = new Socket(HOST, PUERTO);
            InputStream aux = skCliente.getInputStream();
            DataInputStream flujo = new DataInputStream( aux );
            DataOutputStream dOut = new DataOutputStream(skCliente.getOutputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            //Stage stage = new Stage();
          //  launch();
            //this.start(stage);
            String mensaje = "verificarConexión";

            dOut.writeUTF(mensaje);
            System.out.println( flujo.readUTF() );
            skCliente.close();
            return true;
        } catch(Exception e ) {
            System.out.println( e.getMessage() );
        }
        return false;
    }
    
    public static void actualizarPrecios() throws SQLException
    {
        try{
            Socket skCliente = new Socket(HOST, PUERTO);
            InputStream aux = skCliente.getInputStream();
            DataInputStream flujo = new DataInputStream( aux );
            DataOutputStream dOut = new DataOutputStream(skCliente.getOutputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            //Stage stage = new Stage();
          //  launch();
            //this.start(stage);
            //System.out.print("Eliga una operación: ");
            String mensaje = "consultarValores";

            dOut.writeUTF(mensaje);
            
            String respuesta[] = flujo.readUTF().split(" ") ;
            skCliente.close();
            Surtidor.valor93 = Integer.parseInt(respuesta[0]);
            Surtidor.valor95 = Integer.parseInt(respuesta[1]);
            Surtidor.valor97 = Integer.parseInt(respuesta[2]);
            Surtidor.valorDiesel = Integer.parseInt(respuesta[3]);
            Surtidor.valorKerosene = Integer.parseInt(respuesta[4]);
           
        } catch(Exception e ) {
            System.out.println( e.getMessage() );
        }     
    }
    
    public static void generarCarga(int cantidad, boolean litros, String tipo) throws InterruptedException
    {
        if (conectar())
        {
            System.out.println("Primera conexion lograda");
            int  valorActual=0; //= consulta bd;
            switch (tipo)
            {
                case "1":   //93
                    valorActual=Surtidor.valor93; 
                    break;
                case "2":   //95
                    valorActual=Surtidor.valor95; 
                    break;
                case "3":   //97
                    valorActual=Surtidor.valor97; 
                    break;
                case "4":   //Diesel
                    valorActual=Surtidor.valorDiesel; 
                    break;
                case "5":   //Kerosene
                    valorActual=Surtidor.valorKerosene;
                    break;

            }        
            Surtidor.ocupado=true;
            if (litros) {
                sleep(cantidad*1000);
            }
            else{
                cantidad = cantidad/valorActual;
                sleep(cantidad*1000);
            }
            int total=cantidad*valorActual;
            Surtidor.ocupado=false;

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();  

            
            String mensaje = "generarCarga"+" "+cantidad+" "+ Surtidor.nombre +" "+total+" false "+dtf.format(now); //Instruccion + litros de carga + nombre surtidor
            guardarLocal(mensaje);
            enviarCarga(mensaje);   
        }
    

    }
    
    public static void enviarCarga (String mensaje)
    {
        try{
            Socket skCliente = new Socket(HOST, PUERTO);
            InputStream aux = skCliente.getInputStream();
            DataInputStream flujo = new DataInputStream( aux );
            DataOutputStream dOut = new DataOutputStream(skCliente.getOutputStream());
            


            dOut.writeUTF(mensaje);
            System.out.println( flujo.readUTF() );
            skCliente.close();            
            modificarFalse();
            System.out.println("Carga lograda, Envio logrado");
        } catch(Exception e ) {
            System.out.println( e.getMessage() );
            System.out.println("Carga lograda, Envio fallido");
        }
    }
    
        public static void crearEnBD()
    {           
        try{
            Socket skCliente = new Socket(HOST, PUERTO);
            InputStream aux = skCliente.getInputStream();
            DataInputStream flujo = new DataInputStream( aux );
            DataOutputStream dOut = new DataOutputStream(skCliente.getOutputStream());

            String mensaje = "crearSurtidor"+" "+Surtidor.nombre; 
            
            dOut.writeUTF(mensaje);
            System.out.println( flujo.readUTF() );
            skCliente.close();
        } catch(Exception e ) {
            System.out.println( e.getMessage() );
        }
    }
        
    public static void guardarLocal(String mensaje)
    {
        try{
            String verify, putData;
            File file = new File("logSurtidor.txt");
            //file.createNewFile();
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(mensaje+"\n");
            bw.flush();     
            bw.close();
            


        }catch(IOException e){
        e.printStackTrace();
        }
    }
    
    public static String verificarNoEnviado()
    {
        File tmpDir = new File("logSurtidor.txt");
        if (tmpDir.exists())
            
        {
            try {
                BufferedReader reader;
                reader = new BufferedReader(new FileReader("logSurtidor.txt"));
                String line = reader.readLine();
                while (line != null) {
                        System.out.println(line);
                        if(line.toLowerCase().indexOf("false".toLowerCase()) != -1)
                        {
                            reader.close();
                            return line;
                        }
                        line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        
        return "NULO";
    }
    
    public static void modificarFalse() throws IOException, InterruptedException
    {

        
        try{
            String verify, putData;
            File fileOriginal = new File("logSurtidor.txt");
            File fileCopy = new File("logSurtidorCopia.txt");
            fileCopy.createNewFile();
            FileWriter fw = new FileWriter("logSurtidorCopia.txt");
            BufferedWriter bw = new BufferedWriter(fw);
           // bw.flush();
            
            FileReader fr = new FileReader("logSurtidor.txt");
            BufferedReader br = new BufferedReader(fr);

            while( (verify=br.readLine()) != null ){ //***editted
                       //**deleted**verify = br.readLine();**
                if(verify != null){ //***edited
                    putData = verify.replaceAll("false", "true");
                    
                    bw.write(putData+"\n");
                }
                
            }


            bw.close();
            br.close();
            fw.close();
            
            fileOriginal.delete();
            fileCopy.renameTo(fileOriginal);
            


        }catch(IOException e){
        e.printStackTrace();
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

    public static void setNombre(String nombre) {
        Surtidor.nombre = nombre;
    }

    
    

    @Override
    public void start(Stage primaryStage) throws Exception {
        actualizarPrecios();
        String noEnviado;
        noEnviado = verificarNoEnviado();
        if (!noEnviado.equals("NULO"))
        {
            enviarCarga(noEnviado);
            System.out.println(noEnviado);
            
        }
        else
            System.out.println("No hay cargas sin enviar");
        Parent root = FXMLLoader.load(getClass().getResource("V1_1_FXML.fxml"));
        
        Scene scene = new Scene(root);
        primaryStage.setResizable(false);        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
