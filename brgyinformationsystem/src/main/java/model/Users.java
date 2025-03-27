/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author pj
 */
public class Users {
    public String id;
    public String firstname;
    public String lastname;
    public String username; 
    public String password;
    public String reTypePass;
    public Date bDate;
    public String address;
    
    public Users(String firstname, String lastname,
        String username, String password, String reType,
        Date bDate, String address){
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.reTypePass = reType;
        this.bDate = bDate;
        this.address = address;
    }
}
