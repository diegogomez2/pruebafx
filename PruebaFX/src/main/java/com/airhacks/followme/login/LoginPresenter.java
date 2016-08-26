package com.airhacks.followme.login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.airhacks.followme.principal.PrincipalView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author diego
 */
public class LoginPresenter implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML TextField txtUsuario;
    @FXML PasswordField txtPassword;
    @FXML VBox vbox;
    Label texto = new Label();
    
    Stage login;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(new Runnable() {
        @Override
        public void run() {
            txtUsuario.requestFocus();
        }
    });
    }    
    
    public void ingresar(){
        if(!camposVacios()){
            UsuariosModel modelo = UsuariosModel.getInstance();
            int result = modelo.verificarLogin(txtUsuario.getText(), txtPassword.getText());
            switch(result){
                case 1:
                        Stage logStage = (Stage) txtPassword.getScene().getWindow();
                        PrincipalView prinApp = new PrincipalView();
                        Stage stage = new Stage();
                        Scene scene = new Scene(prinApp.getView(), 1024, 640);
                        stage.setTitle("Menú GST");
            //            final String uri = getClass().getResource("app.css").toExternalForm();
            //            scene.getStylesheets().add(uri);
                        stage.setScene(scene);
                        stage.show();
                        logStage.hide();
                        break;
                    case 2:
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("La conexión a la base de datos falló");
                        alert.setContentText("Intente ingresar de nuevo");
                        alert.showAndWait();
                        break;
                    case 0:
                        texto.setText("Usuario y/o contraseña inválidos");
                        vbox.getChildren().remove(texto);
                        vbox.getChildren().add(texto);
                        texto.getStyleClass().add("error");
                        break;
                    default:
                        break;
            }
        }else{
            texto.setText("Debe ingresar usuario y contraseña");
            vbox.getChildren().remove(texto);
            vbox.getChildren().add(texto);
            texto.getStyleClass().add("error");
        }
    }
    
    public void ingresarEnter(KeyEvent key){
        if(key.getCode() == KeyCode.ENTER){
            if(!camposVacios()){
                UsuariosModel modelo = UsuariosModel.getInstance();
                int result = modelo.verificarLogin(txtUsuario.getText(), txtPassword.getText());
                switch(result){
                    case 1:
                        Stage logStage = (Stage) txtPassword.getScene().getWindow();
                        PrincipalView prinApp = new PrincipalView();
                        Stage stage = new Stage();
                        Scene scene = new Scene(prinApp.getView(), 1024, 640);
                        stage.setTitle("Menú GST");
            //            final String uri = getClass().getResource("app.css").toExternalForm();
            //            scene.getStylesheets().add(uri);
                        stage.setScene(scene);
                        stage.show();
                        logStage.hide();
                        break;
                    case 2:
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("La conexión a la base de datos falló");
                        alert.setContentText("Intente ingresar de nuevo");
                        alert.showAndWait();
                        break;
                    case 0:
                        texto.setText("Usuario y/o contraseña inválidos");
                        vbox.getChildren().remove(texto);
                        vbox.getChildren().add(texto);
                        texto.getStyleClass().add("error");
                        break;
                    default:
                        break;
                }
            }else{
                texto.setText("Debe ingresar usuario y contraseña");
                vbox.getChildren().remove(texto);
                vbox.getChildren().add(texto);
                texto.getStyleClass().add("error");
            }
        }
    }
    
    public boolean camposVacios(){
        boolean flag = false;
        if(txtUsuario.getText().trim().compareTo("") == 0){
            txtUsuario.getStyleClass().add("error");
            flag = true;
        }else{
            txtUsuario.getStyleClass().remove("error");
        }
        if(txtPassword.getText().trim().compareTo("") == 0){
            txtPassword.getStyleClass().add("error");
            flag = true;
        }else{
            txtPassword.getStyleClass().remove("error");
        }
        return flag;
    }
}
