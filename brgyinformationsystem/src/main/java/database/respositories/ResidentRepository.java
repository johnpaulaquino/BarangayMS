/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.respositories;
import java.sql.Connection;
import java.sql.SQLException;
import model.Users;
import database.connector.Connector;
import java.sql.Date;
import java.util.HashMap;
import util.Utils;

/**
 *
 * @author pj
 */
public class ResidentRepository {
    private Connection conn;
    private Utils utility;
    public ResidentRepository(){
        utility = new Utils();
        conn = new Connector().connection();
        
    }
   public void createUser(Users user) throws Exception{
      String stmt = "INSERT INTO users (id, firstname, lastname, username, password, birth_date, addres) "
      + "VALUES (?,?,?,?,?,?,?)";
      HashMap<String, String> data = this.findResident(user.username);
       try (var ps1 = conn.prepareStatement(stmt)){
           ps1.setString(1, user.id);
           ps1.setString(2, user.firstname);
           ps1.setString(3, user.lastname);
           ps1.setString(4, user.username);
           ps1.setString(5, utility.hashPassword(user.password));
           ps1.setDate(6, new Date(user.bDate.getTime()));
           ps1.setString(7, user.address);
           
           if(data.containsKey("id")){
               throw new Exception("Username is already exist!");
           }
           
           ps1.executeUpdate();
           System.out.println("Successfully created!");
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }
   }// End of create Users
   
   public HashMap<String, String> findResident(String username){
       var data = new HashMap<String, String>();
       String stmt = "Select id from users "
               + " Where username = ?";
       
       try (var ps1 = conn.prepareStatement(stmt)){
           
           ps1.setString(1, username);
           
           try (var rs = ps1.executeQuery()){
               
               if(rs.next()){
                   data.put("id", rs.getString("id"));
               }
           } catch (Exception e) {
           }
       } catch (Exception e) {
       }
       return data;
   }
}
