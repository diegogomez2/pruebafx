/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks.followme.principal.compras.proveedores;

import com.airhacks.followme.principal.compras.proveedores.detalle.DetalleProveedoresView;
import com.airhacks.followme.principal.compras.proveedores.ingresar.IngresarProveedoresView;
import com.airhacks.followme.principal.compras.proveedores.modificar.ModificarProveedoresView;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author diego
 */
public class ProveedoresPresenter implements Initializable {

    @FXML TextField filtro;
    @FXML TableView tablaProveedores;
    //@FXML TextField rut;
    //@FXML AnchorPane vista;
    @Inject Proveedor proveedor;
    ProveedoresModel modelo = ProveedoresModel.getInstance();
    FilteredList<Proveedor> filteredData ;
     private ObservableList<Proveedor> provData = FXCollections.observableArrayList();
     Alert alert;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filteredData = new FilteredList<>(provData, p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
         filtro.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(p -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if ((p.getRut().toLowerCase().contains(lowerCaseFilter)) || (p.getRzn().toLowerCase().contains(lowerCaseFilter)) 
                        ||(p.getCon().toLowerCase().contains(lowerCaseFilter)) ||(p.getDir().toLowerCase().contains(lowerCaseFilter))
                        || (p.getTel().toLowerCase().contains(lowerCaseFilter))) return true;
                        return false;
        });
        });
        
        tablaProveedores.setRowFactory(tv -> {
        TableRow<Proveedor> row = new TableRow();
        row.setOnMouseClicked(e -> {
        if(e.getClickCount() == 2 && (!row.isEmpty())){
            Proveedor rowData = row.getItem();
            detalle(rowData.getRut());
        }});
        return row;
        });
        loadData();
    }    
    
    public void loadData(){
        List<List<String>> data = modelo.listarProveedores();
        ObservableList<Proveedor> dataProveedores = cargarProveedores(data);
        provData = dataProveedores;
        filteredData = new FilteredList<>(provData, p -> true);
        SortedList<Proveedor> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tablaProveedores.comparatorProperty());
        definirColumnas();
        //tablaClientes.setItems(dataClientes);
        tablaProveedores.setItems(sortedData);
    }
    
    public ObservableList<Proveedor> cargarProveedores(List<List<String>> data){
        ObservableList<Proveedor> proveedores = FXCollections.observableArrayList();
        Iterator<List<String>> it = data.iterator();
        while(it.hasNext()){
            proveedores.add(new Proveedor(it.next()));
        }
        return proveedores;
    }
    
    public void definirColumnas(){
        tablaProveedores.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn rutCol = new TableColumn("Rut");
        rutCol.setCellValueFactory(new PropertyValueFactory<>("rut"));
        TableColumn rznCol = new TableColumn("Razón Social");
        rznCol.setCellValueFactory(new PropertyValueFactory<>("rzn"));
        TableColumn telCol = new TableColumn("Teléfono");
        telCol.setCellValueFactory(new PropertyValueFactory<>("tel"));
        TableColumn dirCol = new TableColumn("Dirección");
        dirCol.setCellValueFactory(new PropertyValueFactory<>("dir"));
        TableColumn conCol = new TableColumn("Contacto");
        conCol.setCellValueFactory(new PropertyValueFactory<>("con"));
        tablaProveedores.getColumns().setAll(rutCol, rznCol, telCol, dirCol, conCol);
    }
    
    public void detalle(String rut){
        proveedor.setRut(rut);
        DetalleProveedoresView det = new DetalleProveedoresView();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(det.getView());
        stage.setTitle("Detalle proveedor");
        stage.setScene(scene);
        stage.showAndWait();
    }
    
    public void agregar() throws IOException{
        IngresarProveedoresView in = new IngresarProveedoresView();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(in.getView());
        stage.setTitle("Ingresar proveedor");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
        loadData();
    }
    
    
    public void eliminar(){
        Proveedor rowClient = (Proveedor)tablaProveedores.getSelectionModel().getSelectedItem();
        if(rowClient == null){
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un proveedor para ser eliminado");
            alert.showAndWait();
        }else{
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmar");
            alert.setHeaderText("Se eliminará al proveedor\nRut: " + rowClient.getRut() 
                    + "\nRazón social: " + rowClient.getRzn());
            alert.setContentText("Realizar operación");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
               String[] rut_dv = rowClient.getRut().split("-");
                modelo.eliminarProveedor(rut_dv[0]);
                loadData();
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("Proveedor eliminado correctamente");
                alert.showAndWait();
            }
        }
    }
//    
    public void modificar(){
        Proveedor rowClient = (Proveedor)tablaProveedores.getSelectionModel().getSelectedItem();
        if(rowClient == null){
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un proveedor para ser modificado");
            alert.showAndWait();
        }else{
            String[] rut_dv = rowClient.getRut().split("-");
            proveedor.setRut(rowClient.getRut());
            ModificarProveedoresView mod = new ModificarProveedoresView();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(mod.getView());
            stage.setTitle("Modificar proveedor");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();
            loadData();
        }
    }
}
