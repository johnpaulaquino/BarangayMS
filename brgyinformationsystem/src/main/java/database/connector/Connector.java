package database.connector;


import java.sql.Connection;
import java.sql.DriverManager;
import src.RegisterForm;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author asus
 */
public class Connector {
    public static Connection connection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/barangayinformationsystem","root","");
                System.out.println("COnnected");
            return con;
        } catch (Exception e) {
            System.out.println("Connection Error");
            return null;
        }
    }
    public static void main(String[] args) {
        new RegisterForm().setVisible(true);
   
    }
    
}
