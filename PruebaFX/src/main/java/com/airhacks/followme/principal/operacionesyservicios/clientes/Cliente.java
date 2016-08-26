/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks.followme.principal.operacionesyservicios.clientes;

import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javax.annotation.PostConstruct;

/**
 *
 * @author diego
 */
public class Cliente {
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

    public Cliente() {
    }

    
//    public Cliente(Object[] data) {
//        this.rut = new SimpleStringProperty(data[0].toString());
//        this.con = new SimpleStringProperty(data[4].toString());
//        this.rzn = new SimpleStringProperty(data[1].toString());
//        this.gir = new SimpleStringProperty();
//        this.cor = new SimpleStringProperty();
//        this.tel = new SimpleStringProperty(data[3].toString());
//        this.cel = new SimpleStringProperty();
//        this.dir = new SimpleStringProperty(data[2].toString());
//        this.reg = new SimpleStringProperty();
//        this.ciu = new SimpleStringProperty();
//        this.com = new SimpleStringProperty();
//        this.obs = new SimpleStringProperty();
//    }

    public Cliente(List<String> data) {
        this.rut = new SimpleStringProperty(data.get(0));
        this.con = new SimpleStringProperty(data.get(4));
        this.rzn = new SimpleStringProperty(data.get(1));
        this.gir = new SimpleStringProperty();
        this.cor = new SimpleStringProperty();
        this.tel = new SimpleStringProperty(data.get(3));
        this.cel = new SimpleStringProperty();
        this.dir = new SimpleStringProperty(data.get(2));
        this.reg = new SimpleStringProperty();
        this.ciu = new SimpleStringProperty();
        this.com = new SimpleStringProperty();
        this.obs = new SimpleStringProperty();
    }
    
    public String getRut() {
        return rut.get();
    }
    
    public int getRutInt() {
        return Integer.parseInt(rut.get());
    }
    
    public String getCon() {
        return con.get();
    }

    public String getRzn() {
        return rzn.get();
    }

    public String getGir(){
        return gir.get();
    }
    
    public String getCor() {
        return cor.get();
    }
    
    public String getTel() {
        return tel.get();
    }

    public String getCel() {
        return cel.get();
    }
    
    public String getDir() {
        return dir.get();
    }
    
    public String getReg() {
        return reg.get();
    }

    public String getCiu() {
        return ciu.get();
    }

    public String getCom() {
        return com.get();
    }

    public String getObs() {
        return obs.get();
    }
    
    public void setRut(String rut) {
        this.rut.set(rut);
    }

    public void setCon(String con) {
        this.con.set(con);
    }
    
    public void setRzn(String rzn) {
        this.rzn.set(rzn);
    }

    public void setGir(String gir) {
        this.gir.set(gir);
    }
    
    public void setCor(String cor) {
        this.cor.set(cor);
    }
    
    public void setTel(String tel) {
        this.tel.set(tel);
    }
    
    public void setCel(String cel) {
        this.cel.set(cel);
    }

    public void setDir(String dir) {
        this.dir.set(dir);
    }
    
    public void setReg(String reg) {
        this.reg.set(reg);
    }

    public void setCiu(String ciu) {
        this.ciu.set(ciu);
    }
    
    public void setCom(String com) {
        this.com.set(com);
    }
    
    public void setObs(String obs) {
        this.obs.set(obs);
    }
    
    public void setData(String rut, String con, String rzn, String gir, String cor, String tel, String cel, 
            String dir, String reg, String ciu, String com, String obs){
        this.rut.set(rut);
        this.con.set(con);
        this.rzn.set(rzn);
        this.gir.set(gir);
        this.cor.set(cor);
        this.tel.set(tel);
        this.cel.set(cel);
        this.dir.set(dir);
        this.reg.set(reg);
        this.ciu.set(ciu);
        this.com.set(com);
        this.obs.set(obs);
    }
    
    public void setData(Object[] data){
        this.rut.set(data[0].toString());
        this.con.set(data[1].toString());
        this.rzn.set(data[2].toString());
        this.gir.set(data[3].toString());
        this.cor.set(data[4].toString());
        this.tel.set(data[5].toString());
        this.cel.set(data[6].toString());
        this.dir.set(data[7].toString());
        this.reg.set(data[8].toString());
        this.ciu.set(data[9].toString());
        this.com.set(data[10].toString());
        this.obs.set(data[11].toString());
    }
}
