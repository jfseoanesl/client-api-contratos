/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.utility;

import javafx.scene.control.Alert;
import javafx.stage.Window;
import javax.swing.JOptionPane;

/**
 *
 * @author Jairo F
 */
public class Screen {
    
    public static void errorMessage( String msg){
        //JOptionPane.showMessageDialog(null, msg, "Mensaje de error", JOptionPane.ERROR_MESSAGE);
        errorMessage("Mensaje de error", msg);
    }
    
    public static void errorMessage(String titulo, String msg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.initOwner(null);
        alert.showAndWait();
    }
    
    public static void errorMessage(Exception msg){
        errorMessage("Mensaje de error", msg);
    }
    
    public static void errorMessage(String titulo, Exception msg){
        errorMessage(titulo, msg.toString());
    }
    
    
    public static void confirmMessage(Window w, String msg){
        confirmMessage(w,"Mensaje de confirmacion", msg);
    }
    public static void confirmMessage(Window w, String titulo, String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.initOwner(w);
        alert.showAndWait();
    }
    
}
