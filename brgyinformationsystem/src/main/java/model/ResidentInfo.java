/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.FileInputStream;

/**
 *
 * @author pj
 */
public class ResidentInfo {
    public String id; 
    public String userId;
    public String firstname;
    public String middlename;
    public String lastname;
    public int age; 
    public FileInputStream profileImage;
    public String civilStatus;
    public String poblacion;
    public String sex;
    
    public ResidentInfo(
            String userId,
            String firstname, 
            String middlename,
            String lastname,
            int age,
            FileInputStream profileImage,
            String civilStatus,
            String poblacion,
            String sex){
        this.userId = userId;
        this.firstname = firstname;
        this.middlename= middlename;
        this.lastname  = lastname;
        this.age = age;
        this. profileImage = profileImage;
        this.civilStatus = civilStatus;
        this.sex = sex;
        this.poblacion = poblacion;
    }
}
