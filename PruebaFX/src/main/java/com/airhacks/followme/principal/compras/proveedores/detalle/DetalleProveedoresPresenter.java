/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks.followme.principal.compras.proveedores.detalle;

import com.airhacks.followme.principal.compras.proveedores.Proveedor;
import com.airhacks.followme.principal.compras.proveedores.ProveedoresModel;
import com.airhacks.followme.principal.modelos.RegionesModel;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author diego
 */
public class DetalleProveedoresPresenter implements Initializable {

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
    ComboBox form;
    @FXML
    ComboBox med;
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
    Button ok;
    @Inject 
            Proveedor proveedor;
    
    RegionesModel regModel = RegionesModel.getInstance();
    ProveedoresModel provModel = ProveedoresModel.getInstance();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] rut_dv = proveedor.getRut().split("-");
        List<String> data = provModel.obtenerProveedorPorRut(rut_dv[0]);
        System.out.println(provModel);
        rut.setText(data.get(0));
        con.setText(data.get(1));
        rzn.setText(data.get(2));
        gir.setText(data.get(3));
        cor.setText(data.get(4));
        tel.setText(data.get(5));
        dir.setText(data.get(6));
        reg.setValue(data.get(7));
        ciu.setValue(data.get(8));
        com.setValue(data.get(9));
        obs.setText(data.get(10));
        form.setValue(data.get(11));
        med.setValue(data.get(12));
    }    
    
    public void ok(){
        Stage rutStage = (Stage) rut.getScene().getWindow();
        rutStage.close();
    }
}
