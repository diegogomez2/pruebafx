package com.airhacks.followme.principal.compras.compras.ingresar;

import com.airhacks.followme.principal.AutoCompleteComboBoxListener;
import com.airhacks.followme.principal.compras.proveedores.ProveedoresModel;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import static javafx.scene.control.cell.TextFieldTableCell.forTableColumn;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;
import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author diego
 */
public class IngresarComprasPresenter implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML TableView tablaCuotas;
    @FXML TableView tablaDetalle;
    @FXML ComboBox rut;
    @FXML TextField gir;
    @FXML TextField rzn;
    @FXML TextField dir;
    @FXML TextField con;
    @FXML TextField ban;
    @FXML TextField numTC;
    @FXML TextField asun;
    @FXML Slider cuotas;
    @FXML ComboBox tipo;
    @FXML DatePicker fecIn;
    @FXML DatePicker fecPago;
    @FXML ComboBox form;
    @FXML ComboBox med;
    //@FXML Spinner cuotas, cantidad;
    @FXML ScrollPane scrollPane;
    @FXML GridPane grid;
    @Inject Producto producto;
    ProveedoresModel proveedores = ProveedoresModel.getInstance();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rut.setItems(proveedores.obtenerRutProveedores());
        tablaCuotas.managedProperty().bind(tablaCuotas.visibleProperty());
        tablaCuotas.setVisible(false);
        fecIn.setValue(LocalDate.now());
        hideOtrosPagos();
        ObservableList<String> tipos = FXCollections.observableArrayList("Factura de ventas y servicios", 
                "Factura de ventas y servicios electrónica", "Factura exenta", "Factura exenta electrónica",
                "Nota de crédito", "Nota de créditp electrónica", "Nota de débito", "Nota de débito electrónica" );
        ObservableList<String> formas = FXCollections.observableArrayList("Al día", "30 días", "60 días", "90 días", 
                "Otros pagos");
        ObservableList<String> medios = FXCollections.observableArrayList("Efectivo", "Transferencia", "Cheque", 
                "Tarjeta de crédito");
        tipo.setItems(tipos);
        form.setItems(formas);
        med.setItems(medios);
        new AutoCompleteComboBoxListener<>(rut);
        rut.valueProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                List<String> data = proveedores.obtenerProveedorPorRut(newValue.toString());
                if(!data.isEmpty()){
                    con.setText(data.get(1));
                    rzn.setText(data.get(2));
                    gir.setText(data.get(3));
                    dir.setText(data.get(6));
                    form.setValue(data.get(11));
                    med.setValue(data.get(12));  
                }
            }
        });
        form.valueProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(newValue.toString().compareTo("Otros pagos") == 0){
                    showOtrosPagos();
                }else{
                    hideOtrosPagos();
                }
            }
        });
        
        showDet();        
    }
    public class AutoShowComboBoxHelper {
    public AutoShowComboBoxHelper(final ComboBox<String> comboBox, final Callback<String, String> textBuilder) {
        final ObservableList<String> items = FXCollections.observableArrayList(comboBox.getItems());

        comboBox.getEditor().textProperty().addListener((ov, o, n) -> {
            if (n.equals(comboBox.getSelectionModel().getSelectedItem())) {
                return;
            }

            comboBox.hide();
            final FilteredList<String> filtered = items.filtered(s -> textBuilder.call(s).toLowerCase().contains(n.toLowerCase()));
            if (filtered.isEmpty()) {
                comboBox.getItems().setAll(items);
            } else {
                comboBox.getItems().setAll(filtered);
                comboBox.show();
            }
        });
    }
}
    public void agregar(){
        showCQ();

    }
    
    public void cancelar(){
//        grid.getChildren().add(tablaCheques);
        tablaCuotas.setVisible(true);
        scrollPane.requestLayout();
        grid.setGridLinesVisible(true);
    }
    
    public void hideOtrosPagos(){
        ban.setDisable(true);
        asun.setDisable(true);
        numTC.setDisable(true);
        cuotas.setDisable(true);
        tablaCuotas.setVisible(false);
    }
    
    public void showOtrosPagos(){
        ban.setDisable(false);
        asun.setDisable(false);
        numTC.setDisable(false);
        cuotas.setDisable(false);
        tablaCuotas.setVisible(true);
    }
    
    public void showCQ(){
        TableColumn numCol = new TableColumn("Cheque");
        numCol.setCellValueFactory(new PropertyValueFactory<>("num"));
        TableColumn folCol = new TableColumn("Folio");
        folCol.setCellValueFactory(new PropertyValueFactory<>("fol"));
        TableColumn fecVCol = new TableColumn("Fecha de vencimiento");
        fecVCol.setCellValueFactory(new PropertyValueFactory<>("fecVen"));
        TableColumn monCol = new TableColumn("Monto");
        monCol.setCellValueFactory(new PropertyValueFactory<>("mon"));
        TableColumn estCol = new TableColumn("Estado");
        estCol.setCellValueFactory(new PropertyValueFactory<>("est"));
        tablaCuotas.getColumns().setAll(numCol, folCol, fecVCol, monCol, estCol);
        tablaCuotas.setVisible(true);
    }
    
    public void showDet(){
        TableColumn codCol = new TableColumn("Codigo");
        codCol.setCellValueFactory(new PropertyValueFactory<>("cod"));
        TableColumn detCol = new TableColumn("Detalle");
        detCol.setCellValueFactory(new PropertyValueFactory<>("det"));
        TableColumn cantCol = new TableColumn("Cantidad");
        cantCol.setCellValueFactory(new PropertyValueFactory<>("cant"));
        TableColumn precCol = new TableColumn("Precio unitario");
        precCol.setCellValueFactory(new PropertyValueFactory<>("prec"));
        precCol.setCellFactory(getCustomCellFactory());
        //precCol.setCellFactory(tc -> new CurrencyCell<>());
        TableColumn ivaCol = new TableColumn("Iva");
        ivaCol.setCellValueFactory(new PropertyValueFactory<>("iva"));
        tablaDetalle.getColumns().setAll(codCol, detCol, cantCol, precCol, ivaCol);
        tablaDetalle.setVisible(true);
    }
    
    public void addRow(){
        ObservableList<Producto> productos = FXCollections.observableArrayList();
        productos.add(new Producto());
        tablaDetalle.getItems().add(new Producto());
    }
    
    public void remRow(){
        tablaDetalle.getItems().remove(tablaDetalle.getItems().size() -1);
    }
    
    private Callback<TableColumn<Producto, String>, TableCell<Producto, String>> getCustomCellFactory() {
        return new Callback<TableColumn<Producto, String>, TableCell<Producto, String>>() {

            @Override
            public TableCell<Producto, String> call(TableColumn<Producto, String> param) {
                TableCell<Producto, String> cell = new TableCell<Producto, String>() {

                    @Override
                    public void updateItem(final String item, boolean empty) {
                        if (item != null) {
                            setText("$ "+item);
                        }
                    }
                };
                return cell;
            }
        };
    }
    
    
    
}
