/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import com.itextpdf.kernel.pdf.PdfWriter;
import java.io.File;
import java.nio.file.Paths;
import java.util.Date;

/**
 *
 * @author pj
 */
public class ResidentsDocuments {
    String filename ;
    private File fileLoc = Paths.get(System.getProperty("user.dir"), "templates").toFile();
    
    public ResidentsDocuments(){
        
    }
    
    public void generateBrgyCertificate(
            String name, 
            Date date, 
            String purpose){
        try {
            String filename = fileLoc.toString() + "/";
            System.out.println();
            Pdf pdfWriter = new PdfWriter(filename);
        } catch (Exception e) {
        }
    }
    
}
