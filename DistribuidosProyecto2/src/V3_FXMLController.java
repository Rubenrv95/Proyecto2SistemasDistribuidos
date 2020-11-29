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

/**
 *
 * @author F. Pino
 */
public class V3_FXMLController implements Initializable {
    @FXML
    private TextField precioProveedor1;    
    @FXML
    private TextField porentajeUtilidad1;    
    @FXML
    private TextField precioFinal1;
    
    @FXML
    private TextField precioProveedor2;    
    @FXML
    private TextField porentajeUtilidad2;    
    @FXML
    private TextField precioFinal2;
    
    @FXML
    private TextField precioProveedor3;    
    @FXML
    private TextField porentajeUtilidad3;    
    @FXML
    private TextField precioFinal3;    
    
    @FXML
    private TextField precioProveedor4;    
    @FXML
    private TextField porentajeUtilidad4;    
    @FXML
    private TextField precioFinal4;    

    @FXML
    private TextField precioProveedor5;    
    @FXML
    private TextField porentajeUtilidad5;    
    @FXML
    private TextField precioFinal5;    

    
    @FXML
    private void actualizarBencina93(ActionEvent event) {
        //= this.precioProveedor1.getText();
        //= this.porentajeUtilidad1.getText();
        //= this.precioFinal1.getText();              
    }
    
    @FXML
    private void actualizarBencina95(ActionEvent event) {
        //= this.precioProveedor2.getText();
        //= this.porentajeUtilidad2.getText();
        //= this.precioFinal2.getText();   
    }
    
    @FXML
    private void actualizarBencina97(ActionEvent event) {
        //= this.precioProveedor3.getText();
        //= this.porentajeUtilidad3.getText();
        //= this.precioFinal3.getText();   
    }
    
    @FXML
    private void actualizarDiesel(ActionEvent event) {
        //= this.precioProveedor4.getText();
        //= this.porentajeUtilidad4.getText();
        //= this.precioFinal4.getText();   
    }
    
    @FXML
    private void actualizarKerosene(ActionEvent event) {
        //= this.precioProveedor5.getText();
        //= this.porentajeUtilidad5.getText();
        //= this.precioFinal5.getText();   
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
