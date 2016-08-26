/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks.followme.principal.operacionesyservicios.clientes.detalleClientes;

import com.airhacks.followme.principal.operacionesyservicios.clientes.Cliente;
import com.airhacks.followme.principal.operacionesyservicios.clientes.ClientesModel;
import com.airhacks.followme.principal.modelos.RegionesModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;
import javax.inject.Inject;

/**
 *
 * @author diego
 */
public class DetalleClientesPresenter implements Initializable{
    @FXML
    TextField rut;
    @FXML
    TextField con;
    @FXML
    TextField rzn;
    @FXML
    TextField gir;
    @FXML
    TextField cor;
    @FXML
    TextField tel;
    @FXML
    TextField cel;
    @FXML
    TextField dir;
    @FXML
    ComboBox reg;
    @FXML
    ComboBox ciu;
    @FXML
    ComboBox com;
    @FXML
    TextArea obs;
    @FXML
    Button OK;
    @FXML
    TitledPane titledObs;
    @FXML
    TitledPane titledDom;
    @FXML
    TitledPane titledInfo;
    @Inject 
            Cliente cliente;
    
    RegionesModel regModel = RegionesModel.getInstance();
    ClientesModel clientModel = ClientesModel.getInstance();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] rut_dv = cliente.getRut().split("-");
        String[] data = clientModel.obtenerClientePorRut(rut_dv[0]);
        System.out.println(clientModel);
        rut.setText(data[0]);
        con.setText(data[1]);
        rzn.setText(data[2]);
        gir.setText(data[3]);
        cor.setText(data[4]);
        tel.setText(data[5]);
        cel.setText(data[6]);
        dir.setText(data[7]);
        reg.setValue(data[8]);
        ciu.setValue(data[9]);
        com.setValue(data[10]);
        obs.setText(data[11]);
    }
    
    
    public void ok(){
        Stage rutStage = (Stage) rut.getScene().getWindow();
        rutStage.close();
    }
}
