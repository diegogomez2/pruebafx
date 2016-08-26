package com.airhacks.followme.principal.compras.proveedores;

import com.airhacks.followme.principal.Connector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author diego
 */
public class ProveedoresModel {
    private ProveedoresModel(){}
    public static ProveedoresModel getInstance(){
        if(pmInstance == null){
            pmInstance = new ProveedoresModel();
        }
        return pmInstance;
    }
    
    private static ProveedoresModel pmInstance;
    Connector conector = Connector.getInstance();
    String login = conector.getLogin();
    String password = conector.getPassword();
    String url = conector.getUrl();
    
    Connection conn = null;
    
    public List<List<String>> listarProveedores(){
        
        List<List<String>> data = new ArrayList<>();
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT rut_pro, dig_pro, raz_pro, dir_pro,"
                    + " tel_pro, con_pro FROM Proveedores ORDER BY rut_pro");
            ResultSet res = pstm.executeQuery();
            while(res.next()){
                String estrut = res.getString("rut_pro");
                String estdig = res.getString("dig_pro");
                String estraz = res.getString("raz_pro");
                String estdir = res.getString("dir_pro");
                String esttel = res.getString("tel_pro");
                String estcon = res.getString("con_pro");
                data.add(Arrays.asList(estrut + "-" + estdig, estraz, esttel, estdir, estcon));
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProveedoresModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
    public String ingresarProveedor(List<String> data){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("INSERT into Proveedores (rut_pro, dig_pro,"
                    + "con_pro, raz_pro, gir_pro, cor_pro, tel_pro, dir_pro, reg_pro, ciu_pro,"
                    + "com_pro, obs_pro, for_pro, med_pro) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstm.setInt(1, Integer.parseInt(data.get(0)));
            pstm.setString(2, data.get(1));
            pstm.setString(3, data.get(2));
            pstm.setString(4, data.get(3));
            pstm.setString(5, data.get(4));
            pstm.setString(6, data.get(5));
            pstm.setString(7, data.get(6));
            pstm.setString(8, data.get(7));
            pstm.setString(9, data.get(8));
            pstm.setString(10, data.get(9));
            pstm.setString(11, data.get(10));
            pstm.setString(12, data.get(11));
            pstm.setString(13, data.get(12));
            pstm.setString(14, data.get(13));
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error al ingresar proveedor");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
    
    public String eliminarProveedor(String data){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM Proveedores WHERE rut_pro = ?");
            pstm.setString(1, data);
            pstm.execute();
            pstm.close();
            return "correcto";
        }catch(SQLException e){
            System.out.println("Error al eliminar proveedor");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
    }
    
    public List<String> obtenerProveedorPorRut(String rut){
        List<String>data = new ArrayList();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM Proveedores WHERE rut_pro = ?");
            pstm.setString(1, rut);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estrut = res.getString("rut_pro");
            String estdig = res.getString("dig_pro");
            String estcon = res.getString("con_pro");
            String estraz = res.getString("raz_pro");
            String estgir = res.getString("gir_pro");
            String estcor = res.getString("cor_pro");
            String esttel = res.getString("tel_pro");
            String estdir = res.getString("dir_pro");
            String estreg = res.getString("reg_pro");
            String estciu = res.getString("ciu_pro");
            String estcom = res.getString("com_pro");
            String estobs = res.getString("obs_pro");
            String estfor = res.getString("for_pro");
            String estmed = res.getString("med_pro");
            data = Arrays.asList(estrut + "-" + estdig , estcon, estraz, estgir, estcor,
                esttel, estdir, estreg, estciu, estcom, estobs, estfor, estmed);
        }catch(SQLException e){
            System.out.println("Error al obtener proveedor por rut");
            System.out.println(e);
            return data;
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
    
    public String modificarProveedor(String[] data, int rut){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("UPDATE Proveedores set rut_pro=?, dig_pro=?, "
                    + "con_pro = ?, raz_pro=?, gir_pro=?, cor_pro=?, tel_pro=?, dir_pro=?, reg_pro=?,"
                    + "ciu_pro=?, com_pro=?, obs_pro=?, for_pro=?, med_pro=? WHERE rut_pro=?");
            pstm.setInt(1, Integer.parseInt(data[0]));
            pstm.setString(2, data[1]);
            pstm.setString(3, data[2]);
            pstm.setString(4, data[3]);
            pstm.setString(5, data[4]);
            pstm.setString(6, data[5]);
            pstm.setString(7, data[6]);
            pstm.setString(8, data[7]);
            pstm.setString(9, data[8]);
            pstm.setString(10, data[9]);
            pstm.setString(11, data[10]);
            pstm.setString(12, data[11]);
            pstm.setString(13, data[12]);
            pstm.setString(14, data[13]);
            pstm.setInt(15, rut);
            pstm.executeUpdate();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error al modificar proveedor");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }

    public ObservableList<String> obtenerRutProveedores() {
       
        ObservableList<String> data = FXCollections.observableArrayList();
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT rut_pro, dig_pro FROM Proveedores"
                    + " ORDER BY rut_pro");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estrut = res.getString("rut_pro");
                String estdig = res.getString("dig_pro");
                data.add(estrut + "-" + estdig);
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return data;  
    }

    public String obtenerClientePorRazon(String razon) {
        String data = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT rut_cli FROM clientes WHERE raz_cli = ?");
            pstm.setString(1, razon);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estrut = res.getString("rut_cli");
            data = estrut;
        }catch(SQLException e){
            System.out.println("Error al obtener cliente por razon");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
    
    public String obtenerObsPorRazon(String razon){
        String data = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT obs_cli FROM clientes WHERE raz_cli = ?");
            pstm.setString(1, razon);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estobs = res.getString("obs_cli");
            data = estobs;
        }catch(SQLException e){
            System.out.println("Error al obtener obs por razon");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
}
