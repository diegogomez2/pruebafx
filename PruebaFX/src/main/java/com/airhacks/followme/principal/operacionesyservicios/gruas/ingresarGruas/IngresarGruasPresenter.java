/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks.followme.principal.operacionesyservicios.gruas.ingresarGruas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author diego
 */
public class IngresarGruasPresenter implements Initializable {

    @FXML ToggleGroup tneum;
    @FXML ToggleGroup tneum2;
    @FXML MenuButton mbtn;
    String tipon = "Tubular" , tipon2 = "Simple";
    String sep = " | ";
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        System.out.println(tneum.getSelectedToggle());
//        System.out.println(tneum.getToggles());
//        tneum.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
//        @Override
//        public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1){
//               if (t != null) System.out.println("t : " + ((RadioMenuItem)t.getToggleGroup().getSelectedToggle()).getText());
//               if (t1 != null) System.out.println("t1 : "+ ((RadioMenuItem)t1.getToggleGroup().getSelectedToggle()).getText());
////            if(t != null) System.out.println("TOGGLE " + ((RadioMenuItem)t.getToggleGroup().getSelectedToggle()).getText());
////            else{
////            RadioMenuItem chk = (RadioMenuItem)t1.getToggleGroup().getSelectedToggle();
////            tipon = chk.getText();
////            mbtn.setText(tipon+sep+tipon2);
////                
////            }
//            }});
//        tneum2.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
//        @Override
//        public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1){
////            System.out.println(((RadioMenuItem)t1.getToggleGroup().getSelectedToggle()).getText());
////            tipon2 = ((RadioMenuItem)t1.getToggleGroup().getSelectedToggle()).getText();
//            mbtn.setText(tipon+sep+tipon2);
//            }});
////        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
////        @Override
////        public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
////
////            RadioButton chk = (RadioButton)t1.getToggleGroup().getSelectedToggle(); // Cast object to radio button
////            System.out.println("Selected Radio Button - "+chk.getText());
//
//        }
    }    
    
    public void setMbtn(){
        tipon = ((RadioMenuItem)tneum.getSelectedToggle()).getText();
        tipon2 = ((RadioMenuItem)tneum2.getSelectedToggle()).getText();
        mbtn.setText(tipon + sep + tipon2);
    }
}
