package com.airhacks.followme.principal.operacionesyservicios.gruas;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author diego
 */
public class Grua {
    private SimpleStringProperty pat = new SimpleStringProperty();
    private SimpleStringProperty des= new SimpleStringProperty();
    private SimpleStringProperty mod= new SimpleStringProperty();
    private SimpleStringProperty pes= new SimpleStringProperty();
    private SimpleStringProperty tneum= new SimpleStringProperty();
    private SimpleStringProperty tneum2= new SimpleStringProperty();
    private SimpleStringProperty ncha= new SimpleStringProperty();
    private SimpleStringProperty tcom= new SimpleStringProperty();
    private SimpleStringProperty obs= new SimpleStringProperty();
    private SimpleStringProperty ton= new SimpleStringProperty();
    private SimpleStringProperty horo= new SimpleStringProperty();
    private SimpleStringProperty fin= new SimpleStringProperty();
    private SimpleStringProperty ndel= new SimpleStringProperty();
    private SimpleStringProperty ntra= new SimpleStringProperty();
    private SimpleStringProperty nmo= new SimpleStringProperty();
    private SimpleStringProperty nse= new SimpleStringProperty();
    private SimpleStringProperty frt= new SimpleStringProperty();
    private SimpleStringProperty fum= new SimpleStringProperty();
    private SimpleStringProperty kmhum= new SimpleStringProperty();
    public Grua(){}
    public Grua(Object[] data){
        this.pat = new SimpleStringProperty();
    this.des= new SimpleStringProperty(data[0].toString());
    this.mod= new SimpleStringProperty(data[1].toString());
    this.pes= new SimpleStringProperty(data[0].toString());
    this.tneum= new SimpleStringProperty(data[3].toString());
    this.tneum2= new SimpleStringProperty(data[4].toString());
    this.ncha= new SimpleStringProperty(data[5].toString());
    this.tcom= new SimpleStringProperty(data[6].toString());
    this.obs= new SimpleStringProperty(data[7].toString());
    this.ton= new SimpleStringProperty(data[8].toString());
    this.horo= new SimpleStringProperty(data[9].toString());
    this.fin= new SimpleStringProperty(data[10].toString());
    this.ndel= new SimpleStringProperty(data[11].toString());
    this.ntra= new SimpleStringProperty(data[12].toString());
    this.nmo= new SimpleStringProperty(data[13].toString());
    this.nse= new SimpleStringProperty(data[14].toString());
    this.frt= new SimpleStringProperty(data[15].toString());
    this.fum= new SimpleStringProperty(data[16].toString());
    this.kmhum= new SimpleStringProperty(data[17].toString());
    }
}
