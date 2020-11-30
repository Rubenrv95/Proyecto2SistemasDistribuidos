/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 *
 * @author F. Pino
 */
public class V2_FXMLController implements Initializable {
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
        ServicentroCliente.setUtilidad93(Integer.parseInt(this.porentajeUtilidad1.getText()));
        this.precioFinal1.setText(Integer.toString(ServicentroCliente.getValor93()+(ServicentroCliente.getValor93()*ServicentroCliente.getUtilidad93()/100)));
        //this.precioFinal1.setText();              
    }
    
    @FXML
    private void actualizarBencina95(ActionEvent event) {
        ServicentroCliente.setUtilidad95(Integer.parseInt(this.porentajeUtilidad2.getText()));
        this.precioFinal2.setText(Integer.toString(ServicentroCliente.getValor95()+(ServicentroCliente.getValor95()*ServicentroCliente.getUtilidad95()/100)));

        //= this.precioProveedor2.getText();
        //= this.precioFinal2.getText();   
    }
    
    @FXML
    private void actualizarBencina97(ActionEvent event) {
        ServicentroCliente.setUtilidad97(Integer.parseInt(this.porentajeUtilidad3.getText()));
        this.precioFinal3.setText(Integer.toString(ServicentroCliente.getValor97()+(ServicentroCliente.getValor97()*ServicentroCliente.getUtilidad97()/100)));
        //= this.precioProveedor3.getText();
        //= this.precioFinal3.getText();   
    }
    
    @FXML
    private void actualizarDiesel(ActionEvent event) {
        ServicentroCliente.setUtilidadDiesel(Integer.parseInt(this.porentajeUtilidad4.getText()));
        this.precioFinal4.setText(Integer.toString(ServicentroCliente.getValorDiesel()+(ServicentroCliente.getValorDiesel()*ServicentroCliente.getUtilidadDiesel()/100)));
        //= this.precioProveedor4.getText();
        //= this.precioFinal4.getText();   
    }
    
    @FXML
    private void actualizarKerosene(ActionEvent event) {
        ServicentroCliente.setUtilidadKerosene(Integer.parseInt(this.porentajeUtilidad5.getText()));
        this.precioFinal5.setText(Integer.toString(ServicentroCliente.getValorKerosene()+(ServicentroCliente.getValorKerosene()*ServicentroCliente.getUtilidadKerosene()/100)));
        //= this.precioProveedor5.getText();
        //= this.precioFinal5.getText();   
    }
    
    public void actualizarValoresProveedor()
    {
        this.precioProveedor1.setText(Integer.toString(ServicentroCliente.getValor93()));
        this.precioProveedor2.setText(Integer.toString(ServicentroCliente.getValor95()));
        this.precioProveedor3.setText(Integer.toString(ServicentroCliente.getValor97()));
        this.precioProveedor4.setText(Integer.toString(ServicentroCliente.getValorDiesel()));
        this.precioProveedor5.setText(Integer.toString(ServicentroCliente.getValorKerosene()));
    }
    
    public void actualizarValoresUtilidad()
    {
        this.precioFinal1.setText(Integer.toString(ServicentroCliente.getValor93()+(ServicentroCliente.getValor93()*ServicentroCliente.getUtilidad93()/100)));
        this.precioFinal2.setText(Integer.toString(ServicentroCliente.getValor95()+(ServicentroCliente.getValor95()*ServicentroCliente.getUtilidad95()/100)));
        this.precioFinal3.setText(Integer.toString(ServicentroCliente.getValor97()+(ServicentroCliente.getValor97()*ServicentroCliente.getUtilidad97()/100)));
        this.precioFinal4.setText(Integer.toString(ServicentroCliente.getValorDiesel()+(ServicentroCliente.getValorDiesel()*ServicentroCliente.getUtilidadDiesel()/100)));
        this.precioFinal5.setText(Integer.toString(ServicentroCliente.getValorKerosene()+(ServicentroCliente.getValorKerosene()*ServicentroCliente.getUtilidadKerosene()/100)));
    }
    
    @FXML
    public void actualizarValores() throws SQLException
    {
        ServicentroCliente.actualizarStatic();
        actualizarValoresProveedor();
        actualizarValoresUtilidad();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualizarValoresProveedor();
        actualizarValoresUtilidad();
    }
    
}
