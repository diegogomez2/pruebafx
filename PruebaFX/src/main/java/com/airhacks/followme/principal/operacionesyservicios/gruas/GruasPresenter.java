package com.airhacks.followme.principal.operacionesyservicios.gruas;

import com.airhacks.followme.principal.operacionesyservicios.gruas.ingresarGruas.IngresarGruasView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author diego
 */
public class GruasPresenter implements Initializable {
    
    @FXML ToggleGroup tneum;
    @FXML ToggleGroup tneum2;
    @FXML MenuButton mbtn;
    String tipon = "Tubular" , tipon2 = "Simple";
    String sep = " | ";
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    public void setMBtn(String txt){
        System.out.println(mbtn.getText());
    }
    public void agregar() throws IOException{
        IngresarGruasView in = new IngresarGruasView();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(in.getView());
        stage.setTitle("Ingresar grua");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
//        loadData();
    }
}
