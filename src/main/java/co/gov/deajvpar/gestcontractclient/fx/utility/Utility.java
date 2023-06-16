/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.utility;

import at.favre.lib.crypto.bcrypt.BCrypt;
import co.gov.deajvpar.gestcontractclient.fx.dtos.CiudadDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.CiudadDptoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.DptoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.UserDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Jairo F
 */
public class Utility {
    
    
    
    public static Map<String, DptoDto> listCiudadesToMapDpto(CiudadDptoDto[] list){
        
       Map<String, DptoDto> dptos = new HashMap();
       for(CiudadDptoDto c: list){
           
           DptoDto dpto = dptos.get(c.getC_digo_dane_del_departamento());
           if(dpto == null){
               dpto = new DptoDto(c.getC_digo_dane_del_departamento(), c.getDepartamento());
           }
           CiudadDto ciudad = new CiudadDto(c.getC_digo_dane_del_municipio(), c.getMunicipio());
           dpto.getListCiudades().add(ciudad);
           dptos.put(c.getC_digo_dane_del_departamento(), dpto);
       
       }
       return dptos;
    }
    
    
    public static boolean validateEmptyComponentCombo(ComboBox list, Label error) {

        int index = list.getSelectionModel().getSelectedIndex();
        boolean empty = list.getSelectionModel().getSelectedIndex() == -1;
        if (empty) {
            list.requestFocus();
        }
        error.setVisible(empty);
        return !empty;

    }
    
    public static boolean validateEmptyComponentTextArea(TextArea text, Label error) {

        boolean empty = text.getText() == null || text.getText().isBlank();
        if (empty) {
            text.requestFocus();
        }
        error.setVisible(empty);
        return !empty;

    }
    
    public static boolean validateEmptyComponentTextField(TextField text, Label error) {

        boolean empty = text.getText() == null || text.getText().isBlank();

        if (empty) {
            text.requestFocus();
        }
        error.setVisible(empty);
        return !empty;

    }
    
    public static boolean validateEmptyComponentPasswordField(PasswordField text, Label error) {

        boolean empty = text.getText() == null || text.getText().isBlank();

        if (empty) {
            text.requestFocus();
        }
        error.setVisible(empty);
        return !empty;

    }
    
    public static boolean validateEmptyComponentDatePicker(DatePicker dp, Label error) {

        boolean empty = dp.getValue()==null;

        if (empty) {
            dp.requestFocus();
        }
        error.setVisible(empty);
        return !empty;

    }
    
    public static boolean validateEmptyComponentTextField(TextField text) {

        boolean empty = text.getText() == null || text.getText().isBlank();

        if (empty) {
            text.requestFocus();
        }
        return !empty;

    }
    
    public static void encryptPassword(UserDto dto){
        dto.setPassword(BCrypt.withDefaults().hashToString(12, dto.getPassword().toCharArray()));
    }
    
    public static String encryptPassword(String clave){
       return BCrypt.withDefaults().hashToString(12, clave.toCharArray());
      // return clave;
    }
    
    
}
