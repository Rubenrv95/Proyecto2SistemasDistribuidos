/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 * @author F. Pino
 */
public class V1_FXMLController implements Initializable {
    
    @FXML
    static private Text precioPantalla;
    
    @FXML
    static private Text litrosPantalla;
    
    @FXML
    static private TextField ingresoLitro;
    
    @FXML
    static private TextField ingresoMonto;
       
    @FXML
    static private Text precioBencina93;
    
    @FXML
    static private Text precioBencina95;
    
    @FXML
    static private Text precioBencina97;
    
    @FXML
    static private Text precioDiesel;
    
    @FXML
    static private Text precioKerosene;
    
    private boolean esLitros = false;
    private int cantidad = 0;
    
    @FXML
    private void clickAceptarMonto(ActionEvent event) {
        this.cantidad = Integer.parseInt(V1_FXMLController.ingresoMonto.getText());
        this.esLitros = false;
    }
    
    @FXML
    private void clickAceptarLitros(ActionEvent event) {
        this.cantidad = Integer.parseInt(V1_FXMLController.ingresoLitro.getText());
        this.esLitros = true;
    }

    @FXML
    private void clickBencina93(ActionEvent event) {
        Surtidor.generarCarga(this.cantidad, this.esLitros, "1");
    }
    
    @FXML
    private void clickBencina95(ActionEvent event) {
        Surtidor.generarCarga(this.cantidad, this.esLitros, "2");
    }   
    
    @FXML
    private void clickBencina97(ActionEvent event) {
        Surtidor.generarCarga(this.cantidad, this.esLitros, "3");
    }
    
    @FXML
    private void clickDiesel(ActionEvent event) {
        Surtidor.generarCarga(this.cantidad, this.esLitros, "4");
    }
    
    @FXML
    private void clickKerosene(ActionEvent event) {
        Surtidor.generarCarga(this.cantidad, this.esLitros, "5");
    }     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }        
}
