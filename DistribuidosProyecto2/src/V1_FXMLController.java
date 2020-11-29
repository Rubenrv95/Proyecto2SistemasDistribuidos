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
    private Text precioPantalla;
    
    @FXML
    private Text litrosPantalla;
    
    @FXML
    public TextField ingresoLitro ;
    
    @FXML
    public TextField ingresoMonto;
       
    @FXML
    private Text precioBencina93;
    
    @FXML
    private Text precioBencina95;
    
    @FXML
    private Text precioBencina97;
    
    @FXML
    private Text precioDiesel;
    
    @FXML
    private Text precioKerosene;
    
    private boolean esLitros = false;
    private int cantidad = 0;

    public V1_FXMLController() {
        
        //  actualizarPrecios();
    }


    
    
    
    @FXML
    private void clickAceptarMonto(ActionEvent event) {
        System.out.println(ingresoMonto.getText());
        String a = ingresoMonto.getText();
        this.cantidad = Integer.parseInt(a);
        this.esLitros = false;
        String b = String.valueOf(this.cantidad);
        this.precioPantalla.setText(b);
    }
    
    @FXML
    private void clickAceptarLitros(ActionEvent event) {
        System.out.println(ingresoLitro.getText());
        String a = ingresoLitro.getText();
        this.cantidad = Integer.parseInt(a);
        this.esLitros = false;
        String b = String.valueOf(this.cantidad);
        this.litrosPantalla.setText(b);
    }

    @FXML
    private void clickBencina93(ActionEvent event) throws InterruptedException {
        this.actualizarTotal(Integer.parseInt(this.precioBencina93.getText()));
        Surtidor.generarCarga(this.cantidad, this.esLitros, "1");
    }
    
    @FXML
    private void clickBencina95(ActionEvent event) throws InterruptedException {
        this.actualizarTotal(Integer.parseInt(this.precioBencina95.getText()));
        Surtidor.generarCarga(this.cantidad, this.esLitros, "2");
    }   
    
    @FXML
    private void clickBencina97(ActionEvent event) throws InterruptedException {
        this.actualizarTotal(Integer.parseInt(this.precioBencina97.getText()));
        Surtidor.generarCarga(this.cantidad, this.esLitros, "3");
    }
    
    @FXML
    private void clickDiesel(ActionEvent event) throws InterruptedException {
        this.actualizarTotal(Integer.parseInt(this.precioDiesel.getText()));        
        Surtidor.generarCarga(this.cantidad, this.esLitros, "4");
    }
    
    @FXML
    private void clickKerosene(ActionEvent event) throws InterruptedException {
        this.actualizarTotal(Integer.parseInt(this.precioKerosene.getText()));
        Surtidor.generarCarga(this.cantidad, this.esLitros, "5");
    }     
    
    @FXML
    private void actualizarPrecios()
    {
        precioBencina93.setText(Integer.toString(Surtidor.getValor93()));
        precioBencina95.setText(Integer.toString(Surtidor.getValor95()));
        precioBencina97.setText(Integer.toString(Surtidor.getValor97()));
        precioDiesel.setText(Integer.toString(Surtidor.getValorDiesel()));
        precioKerosene.setText(Integer.toString(Surtidor.getValorKerosene()));
        
    }
    
    @FXML
    private void actualizarTotal(int valor)
    {
        if (this.esLitros) {
            this.precioPantalla.setText(String.valueOf(this.cantidad * valor));
        }
        else
        {
            this.litrosPantalla.setText(String.valueOf(this.cantidad/valor));
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }        
}
