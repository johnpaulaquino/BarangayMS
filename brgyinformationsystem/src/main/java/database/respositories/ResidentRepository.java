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
import java.sql.ResultSet;
import java.util.HashMap;
import model.ResidentInfo;
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
      String stmt = "INSERT INTO users (id, username, password, birth_date, addres) "
      + "VALUES (?,?,?,?,?)";
      HashMap<String, String> data = this.findResident(user.username);
       try (var ps1 = conn.prepareStatement(stmt)){
           user.id = this.generateId();
           
           ps1.setString(1, user.id);
           ps1.setString(2, user.username);
           ps1.setString(3, utility.hashPassword(user.password));
           ps1.setDate(4, new Date(user.bDate.getTime()));
           ps1.setString(5, user.address);
           
           if(data.containsKey("id")){
               throw new Exception("Username is already exist!");
           }
           
           ps1.executeUpdate();
           this.createResidentInfo(user.id,
                  user.firstname , 
                  user.lastname);
           System.out.println("Successfully created!");
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }
   }// End of create Users
   

   public void createResidentInfo(String userId, 
           String firstname, 
           String lastname){
           
       String stmt = "Insert into residents (user_id, lastname, firstname) "
               + "Values (?,?,?)";
       try (var ps1 = conn.prepareStatement(stmt)){
           ps1.setString(1, userId);
           ps1.setString(2, lastname);
           ps1.setString(3, firstname);
           
           ps1.executeUpdate();
           
       } catch (Exception e) {
           System.out.println(e);
       }
       
       
   }// End of Create Resident Information
   
   
   
   public void updateResidentInfo(ResidentInfo residentInfo) {
       String stmt = "Update residents lastname = ?, "
               + "firstname = ?, middlename = ?, "
               + "profile_img = ?, civil_status = ?, "
               + "poblacion = ?,"
               + "sex = ? "
               + "Where user_id = ?";
       try(var ps1 = conn.prepareStatement(stmt)) {
           ps1.setString(1, residentInfo.lastname);
           ps1.setString(2, residentInfo.firstname);
           ps1.setString(3, residentInfo.middlename);
           ps1.setBlob(4, residentInfo.profileImage);
           ps1.setString(5, residentInfo.civilStatus);
           ps1.setString(6, residentInfo.poblacion);
           ps1.setString(7, residentInfo.sex);
           ps1.setString(8, residentInfo.userId);
           
           
           ps1.executeUpdate();
          
       } catch (Exception e) {
           System.out.println(e);
       }
   }// End of UpdateResident
   
     public HashMap<String, String> findResident(String username){
       var data = new HashMap<String, String>();
       String stmt = "Select id, password from users "
               + " Where username = ?";
       
       try (var ps1 = conn.prepareStatement(stmt)){
           
           ps1.setString(1, username);
           
           try (var rs = ps1.executeQuery()){
               
               if(rs.next()){
                   data.put("id", rs.getString("id"));
                   data.put("password", rs.getString("password"));
                   
               }
           } catch (Exception e) {
           }
       } catch (Exception e) {
       }
       return data;
   } // end of Find Resident 
   
     public String generateId(){
         String generatedId = "";
         String stmt = "Select  LPAD(IFNULL(MAX(id), 0) + 1, 3, '0') as generated_id FROM users";
         
         try(var ps1 = conn.prepareStatement(stmt)) {
             try(var reader = ps1.executeQuery()) {
                 if(reader.next()){
                     generatedId = reader.getString("generated_id");
                 }
             } catch (Exception e) {
             }
         } catch (Exception e) {
         }
         
         return generatedId;
     }
     
     public ResultSet getResidentInfo(){

         String stmt = "Select u.address, r.lastname, r.firstname, r.middlename "
                 + "from users u "
                 + "Inner join residents "
                 + "On u.id = r.user_id";
         try(var ps1 = conn.prepareStatement(stmt)) {
             try(var rs = ps1.executeQuery()) {
                 return rs;
             } catch (Exception e) {
                 System.out.println(e);
             }
         } catch (Exception e) {
             System.out.println(e);
         }
         return null;
     }
}
