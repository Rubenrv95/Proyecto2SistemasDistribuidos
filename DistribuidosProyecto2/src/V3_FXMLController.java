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
        Empresa.actualizarPrecios(Integer.parseInt(this.precioProveedor1.getText()), Integer.parseInt(this.precioProveedor2.getText()), Integer.parseInt(this.precioProveedor3.getText()), Integer.parseInt(this.precioProveedor4.getText()), Integer.parseInt(this.precioProveedor5.getText()));
        this.precioFinal1= this.precioProveedor1;
        //= this.precioFinal1.getText();              
    }
    
    @FXML
    private void actualizarBencina95(ActionEvent event) {
        Empresa.actualizarPrecios(Integer.parseInt(this.precioProveedor1.getText()), Integer.parseInt(this.precioProveedor2.getText()), Integer.parseInt(this.precioProveedor3.getText()), Integer.parseInt(this.precioProveedor4.getText()), Integer.parseInt(this.precioProveedor5.getText()));
        this.precioFinal2= this.precioProveedor1;
        //= this.precioFinal2.getText();   
    }
    
    @FXML
    private void actualizarBencina97(ActionEvent event) {
        Empresa.actualizarPrecios(Integer.parseInt(this.precioProveedor1.getText()), Integer.parseInt(this.precioProveedor2.getText()), Integer.parseInt(this.precioProveedor3.getText()), Integer.parseInt(this.precioProveedor4.getText()), Integer.parseInt(this.precioProveedor5.getText()));
        this.precioFinal3= this.precioProveedor1;
        //= this.precioFinal3.getText();   
    }
    
    @FXML
    private void actualizarDiesel(ActionEvent event) {
        Empresa.actualizarPrecios(Integer.parseInt(this.precioProveedor1.getText()), Integer.parseInt(this.precioProveedor2.getText()), Integer.parseInt(this.precioProveedor3.getText()), Integer.parseInt(this.precioProveedor4.getText()), Integer.parseInt(this.precioProveedor5.getText()));
        this.precioFinal4= this.precioProveedor1;
        //= this.precioFinal4.getText();   
    }
    
    @FXML
    private void actualizarKerosene(ActionEvent event) {
        Empresa.actualizarPrecios(Integer.parseInt(this.precioProveedor1.getText()), Integer.parseInt(this.precioProveedor2.getText()), Integer.parseInt(this.precioProveedor3.getText()), Integer.parseInt(this.precioProveedor4.getText()), Integer.parseInt(this.precioProveedor5.getText()));
        this.precioFinal5= this.precioProveedor1;
        //= this.precioFinal5.getText();   
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
