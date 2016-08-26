package com.airhacks.followme.principal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * FXML Controller class
 *
 * @author diego
 */
public class PrincipalPresenter implements Initializable {
    
    @FXML TabPane tabPanel;
    @FXML MenuItem menuOperaciones;
    @FXML MenuItem menuCompras;
    @FXML Tab tabCompras;
    @FXML Tab tabProveedores;
    @FXML Tab tabClientes;
    @FXML Tab tabGruas;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void irVistaCompras(){
        tabPanel.getTabs().clear();
        tabPanel.getTabs().add(tabProveedores);
        tabPanel.getTabs().add(tabCompras);
    }
    
    public void irVistaOperaciones(){
        tabPanel.getTabs().clear();
        tabPanel.getTabs().add(tabCompras);
        tabPanel.getTabs().add(tabGruas);
    }
    
}
