package util;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import model.Users;

/**
 *
 * @author pj
 */
public class Utils {

    Argon2 passwordHasher = Argon2Factory.create();
    File filePath = Paths.get(System.getProperty("user.dir"), ".cached").toFile();

    public Utils() {

    }

    public String hashPassword(String password) {

        return passwordHasher.hash(10, 65536, 1, password);
    }

    public boolean verifyPassword(String hashedPass, String plainPass) {
        return passwordHasher.verify(hashedPass, plainPass);
    }

    public void validateRegistrationForm(Users user) throws Exception {
        if (user.firstname.isEmpty()) {
            throw new Exception("Firstname field should not be empty!");
        }
        if (user.lastname.isEmpty()) {
            throw new Exception("Lastname field should not be empty!");
        }
        if (user.username.isEmpty()) {
            throw new Exception("Username field should not be empty!");
        }
        if (user.password.isEmpty()) {
            throw new Exception("Password field should not be empty!");
        }
        if (user.reTypePass.isEmpty()) {
            throw new Exception("Re-type Password field should not be empty!");
        }
        if (!user.password.equals(user.reTypePass)) {
            throw new Exception("Password doesn't match!");
        }
        if (user.bDate == null) {
            throw new Exception("Birhtday field should not be empty!");
        }

        if (user.address.isEmpty()) {
            throw new Exception("Address field should not be empty!");
        }

    }

    public void authenticateUser(String username, String password, HashMap<String, String> data) throws Exception {
        if (username.isEmpty()) {
            throw new Exception("Username field should not be empty!");
        }
        if (password.isEmpty()) {
            throw new Exception("Password field should not be empty!");

        }
        if (data.isEmpty()) {
            throw new Exception("No user found!");
        }
        if (!this.verifyPassword(data.get("password"), password)) {
            throw new Exception("Password is Incorrect!");
        }
    }

    public void createTempFiles(String userId) {
        File fileDir = Paths.get(System.getProperty("user.dir"), ".cached").toFile();
        try {

            if (!fileDir.exists()) {
                fileDir.mkdir(); // if folder is not exist, then create
            }

            File file = File.createTempFile("cachedInformation", ".temp", fileDir);
            this.writeFile(file.getAbsolutePath(), userId);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    private void writeFile(String filePath, String userId) {
        try (var fileWriter = new BufferedWriter(new FileWriter(filePath))) {
            fileWriter.write(userId);
        } catch (Exception e) {
        }
    }

    public void deleteCachedFile() {
        try {

            File listFile[] = filePath.listFiles();

            for (File file : listFile) {
                file.delete();
            }
        } catch (Exception e) {
        }
    }

    public String readUserIdInFile() {
        String userId = "";
        String filePath1 = "";
        File listFiles[] = filePath.listFiles();
        for (File listFile : listFiles) {
            filePath1 = listFile.getAbsolutePath();
        }
        try (Scanner reader = new Scanner(new FileReader(filePath1))) {
            while (reader.hasNext()) {
                userId = reader.next();
            }

            return userId;
        } catch (Exception e) {
        }

        return userId;

    }

    public int calculateAge(Date bday) {
        int age = 0;
        LocalDate today = LocalDate.now();

        String slicedDate[] = today.toString().split("-");
        int todayYear = Integer.parseInt(slicedDate[0]);
        int todayMonth = Integer.parseInt(slicedDate[1]);
        int todayDay = Integer.parseInt(slicedDate[2]);


        SimpleDateFormat sdp = new SimpleDateFormat("yyyy-MM-dd");
        String formattedBday = sdp.format(bday);
        String slicedBday[] = formattedBday.toString().split("-");
        
        int bdayYear = Integer.parseInt(slicedBday[0]);
        int bdayMonth = Integer.parseInt(slicedBday[1]);
        int bdayDay = Integer.parseInt(slicedBday[2]);

        age = todayYear - bdayYear;

        if (todayMonth < bdayMonth) {
            age -= 1;

        } else if (todayMonth == bdayMonth) {
            if (todayDay < bdayDay) {
                age -= 1;
            }
        }

        return age;
    }

    public void setImage(JLabel lblImage, String filePath) {
        try {
            ImageIcon origImage = new ImageIcon(filePath);
            Image scaledImage = origImage.getImage().
                    getScaledInstance(lblImage.getWidth(),
                    lblImage.getHeight(), Image.SCALE_SMOOTH);
            lblImage.setIcon(new ImageIcon(scaledImage));

        } catch (Exception e) {
        }

    }

}
