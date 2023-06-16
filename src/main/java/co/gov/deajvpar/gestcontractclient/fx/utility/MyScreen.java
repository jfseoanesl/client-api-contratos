/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.utility;

import java.util.Optional;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Screen;
import javafx.stage.Window;
import javax.swing.JOptionPane;

/**
 *
 * @author Jairo F
 */
public class MyScreen {
    
    private static Rectangle2D bounds;
    private static StatusCode status;
    
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
    
    
    public static void showMessage(Window w, String msg){
        showMessage(w,"Mensaje de confirmacion", msg);
    }
    public static void showMessage(Window w, String titulo, String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.initOwner(w);
        alert.showAndWait();
    }
    
    public static void exitMessage(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(status.getStatus());
        alert.setHeaderText(null);
        alert.setContentText(status.getDescripcion());
        alert.initOwner(null);
        alert.showAndWait();
    }
    
    public static Optional<ButtonType> confirmMessage(Window w, String titulo, String msg){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.initOwner(w);
        Optional<ButtonType> result = alert.showAndWait();
        return result;
    }
    
    
    
    public static Rectangle2D getBound(){
        if(getBounds()==null){
            setBounds(Screen.getPrimary().getVisualBounds());
        }
        return getBounds();
    }
    
    public static double getMaxWidth(){
        return getBound().getWidth();
    }
    
    public static double getMaxHeight(){
        return getBound().getHeight();
    }
    
    /**
     * @return the bounds
     */
    public static Rectangle2D getBounds() {
        return bounds;
    }

    /**
     * @param aBounds the bounds to set
     */
    public static void setBounds(Rectangle2D aBounds) {
        bounds = aBounds;
    }

    /**
     * @return the status
     */
    public static StatusCode getStatus() {
        return status;
    }

    /**
     * @param aStatus the status to set
     */
    public static void setStatus(StatusCode aStatus) {
        status = aStatus;
    }
    
    
    
}
