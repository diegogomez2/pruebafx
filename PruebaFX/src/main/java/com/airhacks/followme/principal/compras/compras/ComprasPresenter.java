/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks.followme.principal.compras.compras;

import com.airhacks.followme.principal.compras.compras.ingresar.IngresarComprasView;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author diego
 */
public class ComprasPresenter implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML TextField filtro;
    @FXML TableView tablaCompras;
    @FXML AnchorPane vista;
    @Inject Compra compra;
    ComprasModel modelo = ComprasModel.getInstance();
    FilteredList<Compra> filteredData ;
     private ObservableList<Compra> compraData = FXCollections.observableArrayList();
     Alert alert;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filteredData = new FilteredList<>(compraData, c -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        filtro.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(c -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                return (String.valueOf(c.getId()).contains(lowerCaseFilter)) || (c.getTipo().toLowerCase().contains(lowerCaseFilter)) 
                        || (String.valueOf(c.getFolReal()).contains(lowerCaseFilter)) || (String.valueOf(c.getFolInt()).contains(lowerCaseFilter))
                        || (c.getRut().toLowerCase().contains(lowerCaseFilter)) || (c.getRzn().toLowerCase().contains(lowerCaseFilter))
                        || (c.getFecVen().toLowerCase().contains(lowerCaseFilter)) || (c.getEst().toLowerCase().contains(lowerCaseFilter));
        });
        });
        
        tablaCompras.setRowFactory(tv -> {
        TableRow<Compra> row = new TableRow();
        row.setOnMouseClicked(e -> {
        if(e.getClickCount() == 2 && (!row.isEmpty())){
            Compra rowData = row.getItem();
            detalle(rowData.getId());
        }});
        return row;
        });
        loadData();
    }    
    
    public void loadData(){
        List<List<String>> data = modelo.listarCompras();
        ObservableList<Compra> dataCompras = cargarCompras(data);
        compraData = dataCompras;
        filteredData = new FilteredList<>(compraData, p -> true);
        SortedList<Compra> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tablaCompras.comparatorProperty());
        definirColumnas();
        tablaCompras.setItems(sortedData);
    }
    
    public ObservableList<Compra> cargarCompras(List<List<String>> data){
        ObservableList<Compra> compras = FXCollections.observableArrayList();
        Iterator<List<String>> it = data.iterator();
        while(it.hasNext()){
            compras.add(new Compra(it.next()));
        }
        return compras;
    }
    
    public void agregar() throws IOException{
        IngresarComprasView in = new IngresarComprasView();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        Scene scene = new Scene(in.getView());
        stage.setTitle("Ingresar compra");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
        loadData();
    }
    
    public void definirColumnas(){
        tablaCompras.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn idCol = new TableColumn("Id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn tipoCol = new TableColumn("Tipo");
        tipoCol.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        TableColumn folCol = new TableColumn("Folio");
        TableColumn folRCol = new TableColumn("Real");
        folRCol.setCellValueFactory(new PropertyValueFactory<>("folReal"));
        TableColumn folICol = new TableColumn("Interno");
        folICol.setCellValueFactory(new PropertyValueFactory<>("folInt"));
        folCol.getColumns().addAll(folRCol, folICol);
        TableColumn rutCol = new TableColumn("Rut");
        rutCol.setCellValueFactory(new PropertyValueFactory<>("rut"));
        TableColumn rznCol = new TableColumn("Razón Social");
        rznCol.setCellValueFactory(new PropertyValueFactory<>("rzn"));
        TableColumn fecVCol = new TableColumn("Fecha de vencimiento");
        fecVCol.setCellValueFactory(new PropertyValueFactory<>("fecVen"));
        TableColumn estCol = new TableColumn("Estado");
        estCol.setCellValueFactory(new PropertyValueFactory<>("est"));
        tablaCompras.getColumns().setAll(idCol, tipoCol, folCol, rutCol, rznCol, fecVCol, estCol);
    }
    
    public void detalle(int id){
        compra.setId(id);
        System.out.println("ID: "+id);
//        DetalleClientesView det = new DetalleClientesView();
//        Stage stage = new Stage();
//        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.initStyle(StageStyle.UTILITY);
//        Scene scene = new Scene(det.getView());
//        stage.setTitle("Detalle cliente");
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.showAndWait();
    }
}
