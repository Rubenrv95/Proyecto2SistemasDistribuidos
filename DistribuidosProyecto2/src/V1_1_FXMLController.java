import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class V1_1_FXMLController implements Initializable {
    
    @FXML private AnchorPane ap;
    @FXML private AnchorPane ingresar;
    @FXML private ProgressIndicator cargando;
    @FXML private TextField usuario;
    @FXML private TextField clave; 
    private final String dirWeb = "www.google.com";
    private final int puerto = 80;
    private String usuarioRed;
    private String claveRed;            
    private final Pattern patronUsuario = Pattern.compile("^[a-zA-Z0-9]{4,10}$", Pattern.MULTILINE);
    private final Pattern patronCorreo = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$", Pattern.MULTILINE);  
    
    @FXML
    public void inicioSesion(MouseEvent evento) throws IOException, SQLException{         
        usuario.setStyle("-fx-text-fill: rgba(255,255,255,1); -fx-background-color: rgba(0,0,0,0.6);");
        clave.setStyle("-fx-text-fill: rgba(255,255,255,1); -fx-background-color: rgba(0,0,0,0.6);");      
        Surtidor.setNombre(usuario.getText());
        iniciarSesion();
        Surtidor.crearEnBD();
        
        /*
        if(this.esCompatible(usuario.getText(), clave.getText()))           
            if(true)
                iniciarSesion();  
            else{                  
                System.out.println("Nombre usuario y/o clave incorrecta"+ "Error");                
                clave.clear();                
            }  
        else{            
            System.out.println("Nombre usuario y/o clave no valida"+ "Error");                 
            clave.clear();            
        }   
        */
    }      
    
    @FXML 
    public void clickEnter(KeyEvent event){
        if(event.getKeyCode()==KeyEvent.VK_ENTER){                                    
        }
    }
    
    @FXML
    public void efectoUsuario(Event event){
        usuario.setStyle("-fx-text-fill: black; -fx-background-color: rgba(0,0,0,0.1);");
        clave.setStyle("-fx-text-fill: rgba(255,255,255,1); -fx-background-color: rgba(0,0,0,0.6);");
    }
    
    @FXML
    public void efectoClave(Event event){        
        clave.setStyle("-fx-text-fill: black; -fx-background-color: rgba(0,0,0,0.1);");
        usuario.setStyle("-fx-text-fill: rgba(255,255,255,1); -fx-background-color: rgba(0,0,0,0.6);");
    }
    
    
    private boolean esCompatible(String usuario,String clave){       
        return patronUsuario.matcher(usuario).matches() && patronUsuario.matcher(clave).matches();
    }
        
    @FXML
    private void iniciarSesion() throws IOException{            
            FXMLLoader loader = new FXMLLoader();
            URL location = V1_1_FXMLController.class.getResource("V1_FXML.fxml");
            loader.setLocation(location);
            Stage stage = new Stage();
            stage.setTitle(" Shell | Surtidor");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/logo.png"))); 
            stage.setOpacity(1);                          
            AnchorPane panelControl = loader.load();
            Scene scene = new Scene(panelControl); 
            stage.setScene(scene);            
            //stage.setOpacity(0.95);        
            stage.initOwner(this.ap.getScene().getWindow());            
            stage.setResizable(false);
            ((Stage)this.ap.getScene().getWindow()).close();                 
            stage.centerOnScreen();
            stage.show();                         
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {          
        try{
            Socket s = new Socket(dirWeb, puerto);                               
        } catch (HeadlessException | IOException | SecurityException ex) {
                       
        }                      
    }    
}