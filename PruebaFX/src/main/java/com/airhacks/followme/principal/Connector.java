package com.airhacks.followme.principal;

/**
 *
 * @author diego
 */
public final class Connector {
    
    private Connector(){}
    public static Connector getInstance(){
        if(conInstance == null){
            conInstance = new Connector();
        }
        return conInstance;
    }
    
    private static Connector conInstance;
    
        String login = "root";
        String url= "jdbc:mysql://localhost:3306/fact_gruas?autoReconnect=true&useSSL=true";
        String password = "205243";
////    String password = "gruas_205243";
////    String url = "jdbc:mysql://10.20.224.100:3306/fact_gruas";
    public String getLogin() {
        return login;
    }

    public  String getPassword() {
        return password;
    }

    public  String getUrl() {
        return url;
    }
}
