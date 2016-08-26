/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks.followme.principal.compras.compras.ingresar;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author diego
 */
public class Producto {
    private SimpleIntegerProperty cod = new SimpleIntegerProperty();
    private SimpleStringProperty det = new SimpleStringProperty();
    private SimpleIntegerProperty cant = new SimpleIntegerProperty();
    private SimpleStringProperty prec = new SimpleStringProperty();
    private SimpleStringProperty iva = new SimpleStringProperty();
    
    public Producto(){
//        this.cod = new SimpleIntegerProperty(0);
//        this.det = new SimpleStringProperty("hola");
//        this.cant = new SimpleIntegerProperty(1);
//        this.prec = new SimpleIntegerProperty(2);
//        this.iva = new SimpleStringProperty("chao");
    }
    
    public Producto(int cod, String det, int cant, String prec, String iva){
        this.cod.set(cod);
        this.det.set(det);
        this.cant.set(cant);
        this.prec.set(prec);
        this.iva.set(iva);
    }

    public int getCod() {
        return cod.get();
    }

    public void setCod(int cod) {
        this.cod.set(cod);
    }

    public String getDet() {
        return det.get();
    }

    public void setDet(String det) {
        this.det.set(det);
    }

    public int getCant() {
        return cant.get();
    }

    public void setCant(int cant) {
        this.cant.set(cant);
    }

    public String getPrec() {
        return prec.get();
    }

    public void setPrec(String prec) {
        this.prec.set(prec);
    }

    public String getIva() {
        return iva.get();
    }

    public void setIva(String iva) {
        this.iva.set(iva);
    }
    
}
