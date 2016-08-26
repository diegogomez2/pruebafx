/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks.followme.principal.modelos;

import com.airhacks.followme.principal.Connector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author diego
 */
public class RegionesModel {
    private RegionesModel(){}
    public static RegionesModel getInstance(){
        if(regInstance == null){
            regInstance = new RegionesModel();
        }
        return regInstance;
    }
    private static RegionesModel regInstance;
    Connector conector = Connector.getInstance();
    String login = conector.getLogin();
    String password = conector.getPassword();
    String url = conector.getUrl();
    
    Connection conn = null;
    
    public Object[][] listarRegiones(){
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Regiones");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error obtener regiones");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][2];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT nom_reg, id_reg FROM Regiones ORDER BY num_reg");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estid = res.getString("id_reg");
                String estnom = res.getString("nom_reg");
                data[i] = new String[]{estid, estnom};
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println("Error obtener regiones");
            System.out.println(e);
        }
        return data;
    }

    public Object[] listaComunas(int region) {
        int registros = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Comunas INNER JOIN"
                    + " Regiones ON comunas.id_reg = regiones.id_reg WHERE regiones.id_reg = ?");
            pstm.setInt(1, region);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error listar comunas");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[] data = new String[registros];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT nom_com FROM Comunas INNER JOIN regiones ON comunas.id_reg = regiones.id_reg where comunas.id_reg = ? ORDER BY nom_com");
            pstm.setInt(1, region);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estnom = res.getString("nom_com");
                data[i] = estnom;
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println("Error listar comunas");
            System.out.println(e);
        }
        return data;
    }
    
    public Object[] listaCiudades(int region) {
        int registros = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Ciudades INNER JOIN"
                    + " Regiones ON ciudades.id_reg = regiones.id_reg WHERE regiones.id_reg = ?");
            pstm.setInt(1, region);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error listar ciudades");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[] data = new String[registros];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT nom_ciud FROM Ciudades INNER JOIN regiones ON ciudades.id_reg = regiones.id_reg "
                    + "where ciudades.id_reg = ? ORDER BY nom_ciud");
            pstm.setInt(1, region);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estnom = res.getString("nom_ciud");
                data[i] = estnom;
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println("Error listar ciudades");
            System.out.println(e);
        }
        return data;
    }
    
    public Object[] listaCiudades2() {
        int registros = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Ciudades");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error listar ciudades 2");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[] data = new String[registros];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT nom_ciud FROM Ciudades ORDER BY nom_ciud");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estnom = res.getString("nom_ciud");
                data[i] = estnom;
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println("Error listar ciudades 2");
            System.out.println(e);
        }
        return data;
    }
    
    public int getIdPorNombre(String nombre) {        
        int data = -1;
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT id_reg FROM Regiones WHERE nom_reg = ?");
            pstm.setString(1, nombre);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estnom = res.getString("id_reg");
            data = Integer.parseInt(estnom);
            res.close();
        }catch(SQLException e){
            System.out.println("Error id por nombre");
            System.out.println(e);
        }
        return data;
    }
}
