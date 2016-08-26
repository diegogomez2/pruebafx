/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks.followme.principal.compras.proveedores;

import java.util.List;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author diego
 */
public class Proveedor {
    private SimpleStringProperty rut = new SimpleStringProperty();
    private SimpleStringProperty con = new SimpleStringProperty();
    private SimpleStringProperty rzn = new SimpleStringProperty();
    private SimpleStringProperty gir = new SimpleStringProperty();
    private SimpleStringProperty cor = new SimpleStringProperty();
    private SimpleStringProperty tel = new SimpleStringProperty();
    private SimpleStringProperty cel = new SimpleStringProperty();
    private SimpleStringProperty dir = new SimpleStringProperty();
    private SimpleStringProperty reg = new SimpleStringProperty();
    private SimpleStringProperty ciu = new SimpleStringProperty();
    private SimpleStringProperty com = new SimpleStringProperty();
    private SimpleStringProperty obs = new SimpleStringProperty();
    private SimpleStringProperty form = new SimpleStringProperty();
    private SimpleStringProperty med = new SimpleStringProperty();
    
    public Proveedor() {
    }

    
    public Proveedor(List<String> data) {
        this.rut = new SimpleStringProperty(data.get(0));
        this.con = new SimpleStringProperty(data.get(4));
        this.rzn = new SimpleStringProperty(data.get(1));
        this.gir = new SimpleStringProperty();
        this.cor = new SimpleStringProperty();
        this.tel = new SimpleStringProperty(data.get(2));
        this.dir = new SimpleStringProperty(data.get(3));
        this.reg = new SimpleStringProperty();
        this.ciu = new SimpleStringProperty();
        this.com = new SimpleStringProperty();
        this.obs = new SimpleStringProperty();
        this.form = new SimpleStringProperty();
        this.med = new SimpleStringProperty();
    }

    public String getRut() {
        return rut.get();
    }

    public void setRut(String rut) {
        this.rut.set(rut);
    }

    public String getCon() {
        return con.get();
    }

    public void setCon(String con) {
        this.con.set(con);
    }

    public String getRzn() {
        return rzn.get();
    }

    public void setRzn(String rzn) {
        this.rzn.set(rzn);
    }

    public String getGir() {
        return gir.get();
    }

    public void setGir(String gir) {
        this.gir.set(gir);
    }

    public String getCor() {
        return cor.get();
    }

    public void setCor(String cor) {
        this.cor.set(cor);
    }

    public String getTel() {
        return tel.get();
    }

    public void setTel(String tel) {
        this.tel.set(tel);
    }

    public String getCel() {
        return cel.get();
    }

    public void setCel(String cel) {
        this.cel.set(cel);
    }

    public String getDir() {
        return dir.get();
    }

    public void setDir(String dir) {
        this.dir.set(dir);
    }

    public String getReg() {
        return reg.get();
    }

    public void setReg(String reg) {
        this.reg.set(reg);
    }

    public String getCiu() {
        return ciu.get();
    }

    public void setCiu(String ciu) {
        this.ciu.set(ciu);
    }

    public String getCom() {
        return com.get();
    }

    public void setCom(String com) {
        this.com.set(com);
    }

    public String getObs() {
        return obs.get();
    }

    public void setObs(String obs) {
        this.obs.set(obs);
    }

    public String getForm() {
        return form.get();
    }

    public void setForm(String form) {
        this.form.set(form);
    }

    public String getMed() {
        return med.get();
    }

    public void setMed(String med) {
        this.med.set(med);
    }    
}
