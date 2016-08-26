/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks.followme.principal.compras.compras;

import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author diego
 */
public class Compra {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty tipo = new SimpleStringProperty();
    private SimpleIntegerProperty folReal = new SimpleIntegerProperty();
    private SimpleIntegerProperty folInt = new SimpleIntegerProperty();
    private SimpleStringProperty rut = new SimpleStringProperty();
    private SimpleStringProperty rzn = new SimpleStringProperty();
    private SimpleStringProperty fecVen = new SimpleStringProperty();
    private SimpleStringProperty est = new SimpleStringProperty();

    public Compra() {
    }

    public Compra(List<String> data) {
        this.id = new SimpleIntegerProperty(Integer.parseInt(data.get(0)));
        this.tipo = new SimpleStringProperty(data.get(1));
        this.folReal = new SimpleIntegerProperty(Integer.parseInt(data.get(2)));
        this.folInt = new SimpleIntegerProperty(Integer.parseInt(data.get(3)));
        this.rut = new SimpleStringProperty(data.get(4));
        this.rzn = new SimpleStringProperty(data.get(5));
        this.fecVen = new SimpleStringProperty(data.get(6));
        this.est = new SimpleStringProperty(data.get(7));
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getTipo() {
        return tipo.get();
    }

    public void setTipo(String tipo) {
        this.tipo.set(tipo);
    }

    public int getFolReal() {
        return folReal.get();
    }

    public void setFolReal(int folReal) {
        this.folReal.set(folReal);
    }

    public int getFolInt() {
        return folInt.get();
    }

    public void setFolInt(int folInt) {
        this.folInt.set(folInt);
    }

    public String getRut() {
        return rut.get();
    }

    public void setRut(String rut) {
        this.rut.set(rut);
    }

    public String getRzn() {
        return rzn.get();
    }

    public void setRzn(String rzn) {
        this.rzn.set(rzn);
    }

    public String getFecVen() {
        return fecVen.get();
    }

    public void setFecVen(String fecVen) {
        this.fecVen.set(fecVen);
    }

    public String getEst() {
        return est.get();
    }

    public void setEst(String est) {
        this.est.set(est);
    }    
}
