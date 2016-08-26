package com.airhacks.followme.principal.operacionesyservicios.clientes;

import com.airhacks.followme.principal.operacionesyservicios.clientes.detalleClientes.DetalleClientesView;
import com.airhacks.followme.principal.operacionesyservicios.clientes.ingresarClientes.IngresarClientesView;
import com.airhacks.followme.principal.operacionesyservicios.clientes.modificarClientes.ModificarClientesView;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
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
public class ClientesPresenter implements Initializable {

    @FXML TextField filtro;
    @FXML TableView tablaClientes;
    @FXML AnchorPane vista;
    @Inject Cliente cliente;
    ClientesModel modelo = ClientesModel.getInstance();
    FilteredList<Cliente> filteredData ;
     private ObservableList<Cliente> clientData = FXCollections.observableArrayList();
     Alert alert;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filteredData = new FilteredList<>(clientData, c -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        filtro.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(c -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if ((c.getRut().toLowerCase().contains(lowerCaseFilter)) || (c.getRzn().toLowerCase().contains(lowerCaseFilter)) 
                        ||(c.getCon().toLowerCase().contains(lowerCaseFilter)) ||(c.getDir().toLowerCase().contains(lowerCaseFilter))
                        || (c.getTel().toLowerCase().contains(lowerCaseFilter))) return true;
                        return false;
        });
        });
        
        tablaClientes.setRowFactory(tv -> {
        TableRow<Cliente> row = new TableRow();
        row.setOnMouseClicked(e -> {
        if(e.getClickCount() == 2 && (!row.isEmpty())){
            Cliente rowData = row.getItem();
            detalle(rowData.getRut());
        }});
        return row;
        });
        loadData();
    }    
    
    public void loadData(){
        List<List<String>> data = modelo.listarClientes();
        ObservableList<Cliente> dataClientes = cargarClientes(data);
//        ObservableList<Cliente> dataClientes = modelo.listarClientes();
        clientData = dataClientes;
        filteredData = new FilteredList<>(clientData, p -> true);
        SortedList<Cliente> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tablaClientes.comparatorProperty());
        definirColumnas();
        //tablaClientes.setItems(dataClientes);
        tablaClientes.setItems(sortedData);
    }
    
    public ObservableList<Cliente> cargarClientes(List<List<String>> data){
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        Iterator<List<String>> it = data.iterator();
        while(it.hasNext()){
            clientes.add(new Cliente(it.next()));
        }
        return clientes;
    }
    
    public void definirColumnas(){
        //tablaClientes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
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
        tablaClientes.getColumns().setAll(rutCol, rznCol, telCol, dirCol, conCol);
    }
    
    public void agregar() throws IOException{
        IngresarClientesView in = new IngresarClientesView();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        Scene scene = new Scene(in.getView());
        stage.setTitle("Ingresar cliente");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
        loadData();
    }
    
    public void detalle(String rut){
        cliente.setRut(rut);
        DetalleClientesView det = new DetalleClientesView();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        Scene scene = new Scene(det.getView());
        stage.setTitle("Detalle cliente");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
    }
    
    public void eliminar(){
        Cliente rowClient = (Cliente)tablaClientes.getSelectionModel().getSelectedItem();
        if(rowClient == null){
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un cliente para ser eliminado");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmar");
            alert.setHeaderText("Se eliminará al cliente\nRut: " + rowClient.getRut() 
                    + "\nRazón social: " + rowClient.getRzn());
            alert.setContentText("Realizar operación");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
               String[] rut_dv = rowClient.getRut().split("-");
                modelo.eliminarCliente(rut_dv[0]);
                loadData();
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("Cliente eliminado correctamente");
                alert.showAndWait();
            }
        }
    }
    
    public void modificar(){
        Cliente rowClient = (Cliente)tablaClientes.getSelectionModel().getSelectedItem();
        if(rowClient == null){
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText(null);
            alert.setContentText("Debe seleccionar un cliente para ser modificado");
            alert.showAndWait();
        }else{
            String[] rut_dv = rowClient.getRut().split("-");
            cliente.setRut(rowClient.getRut());
            ModificarClientesView mod = new ModificarClientesView();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            Scene scene = new Scene(mod.getView());
            stage.setTitle("Modificar cliente");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();
            loadData();
        }
    }
}
