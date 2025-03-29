package database.connector;


import database.respositories.ResidentRepository;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.LoginForm;
import util.Utils;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author asus
 */
public class Connector {    
    public static Connection Connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/barangayinformationsystem","root","");
            return con;
        } catch (Exception e) {
            System.out.println("Connection Error");
            return null;
        }
    }
 
    public static void main(String[] args) {
        File fileLoc = Paths.get(System.getProperty("user.dir"), "templates").toFile();

    String filename = fileLoc.toString() + "/brgyCertTemplate.pdf";
        System.out.println(filename);
//        new LoginForm().setVisible(true);

   
    }
    
}
