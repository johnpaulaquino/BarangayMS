package util;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import java.util.HashMap;
import model.Users;

/**
 *
 * @author pj
 */
public class Utils {
    Argon2 passwordHasher = Argon2Factory.create();
    
    public Utils(){
        
    }
    
    public String hashPassword(String password){

            return passwordHasher.hash(10, 65536, 1, password);
    }
    
    public boolean verifyPassword(String hashedPass, String plainPass){
          return passwordHasher.verify(hashedPass,plainPass);
    }
    
    public void validateRegistrationForm(Users user) throws Exception{
        if(user.firstname.isEmpty()){
            throw new Exception("Firstname field should not be empty!");
        }
         if(user.lastname.isEmpty()){
            throw new Exception("Lastname field should not be empty!");
        }
          if(user.username.isEmpty()){
            throw new Exception("Username field should not be empty!");
        }
           if(user.password.isEmpty()){
            throw new Exception("Password field should not be empty!");
        }
            if(user.reTypePass.isEmpty()){
            throw new Exception("Re-type Password field should not be empty!");
        }
            if(!user.password.equals(user.reTypePass)){
                throw new Exception("Password doesn't match!");
        }
             if(user.bDate == null){
            throw new Exception("Birhtday field should not be empty!");
        }
             
            if(user.address.isEmpty()){
            throw new Exception("Address field should not be empty!");
        }
            
    }
    public void authenticateUser(String username,String password, HashMap<String, String> data) throws Exception{
        if(username.isEmpty()){
            throw new Exception("Username field should not be empty!");
        }
        if(password.isEmpty()){
         throw new Exception("Password field should not be empty!");

        }
        if(data.isEmpty()){
            throw new Exception("No user found!");
        }
        if(!this.verifyPassword(data.get("password"), password)){
            throw new Exception("Password is Incorrect!");
        }
    }
    
    
}
