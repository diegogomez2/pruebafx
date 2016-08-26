/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks.followme.principal.operacionesyservicios.clientes.modificarClientes;

import com.airhacks.followme.principal.operacionesyservicios.clientes.Cliente;
import com.airhacks.followme.principal.operacionesyservicios.clientes.ClientesModel;
import com.airhacks.followme.principal.modelos.RegionesModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author diego
 */
public class ModificarClientesPresenter implements Initializable {

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
    Button aceptar;
    @FXML
    Object[][] regiones;
    @Inject
    Cliente cliente;
    Alert alert;
    RegionesModel regModel = RegionesModel.getInstance();
    ClientesModel clientModel = ClientesModel.getInstance();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarRegiones();
        cargarDatos();
        reg.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int region = (int)newValue;
                cargarCiudades(region);
                cargarComunas(region);
            }
        });
    }    
    
    public void modificar() {
        if(!camposVacios()){
            String rutS = rut.getText();
            String[] rut_dv = rut.getText().split("-");
            if(verificarRut(rutS)){
                String[] data = new String[]{rut_dv[0], rut_dv[1], con.getText(), rzn.getText(), gir.getText(), 
                    cor.getText(), tel.getText(), cel.getText(), dir.getText(), reg.getSelectionModel().getSelectedItem().toString(),
                    ciu.getSelectionModel().getSelectedItem().toString(), com.getSelectionModel().getSelectedItem().toString(), 
                    obs.getText()};
                String[] rut_new = cliente.getRut().split("-");
                clientModel.modificarCliente(data, Integer.parseInt(rut_new[0]));
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText("Operación exitosa");
                alert.setContentText("El cliente ha sido modificado con éxito");
                alert.showAndWait();
                Stage rutStage = (Stage) rut.getScene().getWindow();
                rutStage.close();
            }else{
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Rut inválido");
                alert.setContentText("Debe ingresar un rut válido");
                alert.showAndWait();
            }
        }else{
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText("Campos vacíos");
            alert.setContentText("Debe ingresar los campos obligatorios");
            alert.showAndWait();
        }
    }

    public void cerrar() {
        Stage rutStage = (Stage) rut.getScene().getWindow();
        rutStage.close();
    }
    
    public void cargarRegiones() {
        regiones = regModel.listarRegiones();
        ObservableList<String> listaRegiones = FXCollections.observableArrayList();
        for (Object[] region1 : regiones) {
            listaRegiones.add(region1[1].toString());
        }
        reg.setItems(listaRegiones);
    }

    public void cargarCiudades(int region) {
        if(region != -1){
            int regi = Integer.parseInt(regiones[region][0].toString());
            Object[] ciudades = regModel.listaComunas(regi);
            ObservableList<String> listaCiudades = FXCollections.observableArrayList();
            for (Object ciu1 : ciudades) {
                listaCiudades.add(ciu1.toString());
            }
            ciu.setItems(listaCiudades);
        }
    }

    public void cargarComunas(int region) {
        if(region != -1){
            int regi = Integer.parseInt(regiones[region][0].toString());
            Object[] comunas = regModel.listaComunas(regi);
            ObservableList<String> listaComunas = FXCollections.observableArrayList();
            for (Object com1 : comunas) {
                listaComunas.add(com1.toString());
            }
            com.setItems(listaComunas);
        }
    }
    
    public boolean camposVacios(){
        boolean flag = false;
        if(rut.getText().trim().compareTo("") == 0){
            rut.getStyleClass().add("error");
            flag = true;
        }
        if(rzn.getText().trim().compareTo("") == 0){
            rzn.getStyleClass().add("error");
            flag = true;
        }
        if(gir.getText().trim().compareTo("") == 0){
            gir.getStyleClass().add("error");
            flag = true;
        }
        if(dir.getText().trim().compareTo("") == 0){
            dir.getStyleClass().add("error");
            flag = true;
        }
        if(reg.getSelectionModel().getSelectedIndex() == -1){
            reg.getStyleClass().add("error");
            flag = true;
        }
        if(ciu.getSelectionModel().getSelectedIndex() == -1){
            ciu.getStyleClass().add("error");
            flag = true;
        }if(com.getSelectionModel().getSelectedIndex() == -1){
            com.getStyleClass().add("error");
            flag = true;
        }
        return flag;
    }
    
    public void cargarDatos(){
        String[] rut_dv = cliente.getRut().split("-");
        Object[] data = clientModel.obtenerClientePorRut(rut_dv[0]);
        rut.setText(data[0].toString());
        con.setText(data[1].toString());
        rzn.setText(data[2].toString());
        gir.setText(data[3].toString());
        cor.setText(data[4].toString());
        tel.setText(data[5].toString());
        cel.setText(data[6].toString());
        dir.setText(data[7].toString());
        reg.getSelectionModel().select(data[8].toString());
        int region = reg.getSelectionModel().getSelectedIndex();
        cargarCiudades(region);
        cargarComunas(region);
        ciu.getSelectionModel().select(data[9].toString().toUpperCase());
        com.getSelectionModel().select(data[10].toString().toUpperCase());
        obs.setText(data[11].toString());
    }
    
    public boolean verificarRut(String rut) {
        if (rut.compareTo("") != 0) {
            String[] rut_dv = rut.split("-");
            if (rut_dv.length == 2) {
                try {
                    int num = Integer.parseInt(rut_dv[0]);
                    char dv = rut_dv[1].charAt(0);
                    return validarRut(num, dv);
                } catch (Exception e) {
                    System.out.println(e);
                }
            } 
        } 
        return false;
    }
    
    public static boolean validarRut(int rut, char dv) {
        int m = 0, s = 1;
        for (; rut != 0; rut /= 10) {
            s = (s + rut % 10 * (9 - m++ % 6)) % 11;
        }
        return dv == (char) (s != 0 ? s + 47 : 107);
    }
}
