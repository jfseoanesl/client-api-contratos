/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.controller;

import co.gov.deajvpar.gestcontractclient.fx.*;
import co.gov.deajvpar.gestcontractclient.fx.Exceptions.HttpResponseException;
import co.gov.deajvpar.gestcontractclient.fx.dtos.*;
import co.gov.deajvpar.gestcontractclient.fx.logic.GestionInicializar;
import co.gov.deajvpar.gestcontractclient.fx.utility.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;


/**
 * FXML Controller class
 *
 * @author Jairo F
 */
public class InicializarController implements Initializable {

    @FXML
    private TextField txtTitulo, txtNombre,txtNoDocumento,txtPNombre,txtSNombre,txtPApellido,txtSApellido,txtUsuario;
    @FXML
    private ComboBox cmbTipo, cmbGenero;
    @FXML
    private DatePicker dpFechaNacimiento;
    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lbTitulo, lbNombre,lbTipo,lbDocumento,lbPNombre,lbSNombre,lbPApellido,lbSApellido,lbGenero,lbFecha;
    @FXML
    private Label lbUser,lbPassword;
   
    private GestionInicializar logica = new GestionInicializar();

    /**
     * Initializes the controller class.
     */
    @FXML
    public void actionButtonInicializar(ActionEvent e) {
        if (this.validarForm()) {
            try {
                this.inicializarSistema();
            } catch (IOException ex) {
                MyScreen.errorMessage(ex);
            }
        }
    }

    @FXML
    public void actionButtonCancelar(ActionEvent e) {
       System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        this.txtTitulo.setText(App.sistema.getTitulo());
        this.txtNombre.setText(App.sistema.getName());

        this.cmbGenero.getItems().addAll(this.logica.getListGenero());
        this.cmbTipo.getItems().addAll(this.logica.getListTipoDocumento());

    }

    private void inicializarSistema() throws IOException {

        App.sistema.setName(this.txtNombre.getText());
        App.sistema.setTitulo(this.txtTitulo.getText());
        
        try{
            InicializarDto dto = new InicializarDto(App.sistema, this.logica.getDptosAndCiudades(), this.getDataAdmon());
            this.logica.inicializar(dto);
            //MyScreen.showMessage(App.getScene().getWindow(), "Peticion de inicializacion realizada con exito");
            MyScreen.exitMessage();
            this.logica.lanzarLogin();
        }catch(HttpResponseException ex){
            MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
        
        }

    }

    private UsuarioDto getDataAdmon() {
        
        UsuarioDto userDto = new UsuarioDto();
        TipoDocumentoDto tipoDto= new TipoDocumentoDto();
        tipoDto.setDescripcion(this.cmbTipo.getSelectionModel().getSelectedItem().toString());
        //userDto.setTipoDocumento(this.cmbTipo.getSelectionModel().getSelectedItem().toString());
        userDto.setTipoDocumento(tipoDto);
        userDto.setNoDocumento(this.txtNoDocumento.getText());
        userDto.setpNombre(this.txtPNombre.getText());
        userDto.setsNombre(this.txtSNombre.getText());
        userDto.setpApellido(this.txtPApellido.getText());
        userDto.setsApellido(this.txtSApellido.getText());
        userDto.setFechaNacimiento(this.dpFechaNacimiento.getValue());
        userDto.setGenero(this.cmbGenero.getSelectionModel().getSelectedItem().toString());
        userDto.setUserName(this.txtUsuario.getText());
        
        String clave = Utility.encryptPassword(this.txtPassword.getText());
        userDto.setPassword(clave);
        userDto.setUserType("ADMINISTRADOR");
        return userDto;

    }

    private boolean validarForm() {

        boolean resultValidation = true;

        if (!Utility.validateEmptyComponentTextField(this.txtTitulo,  this.lbTitulo)) {

            resultValidation = false;
        }

        if (!Utility.validateEmptyComponentTextField(this.txtNombre,  this.lbNombre)) {
            resultValidation = false;
        }

        if (!Utility.validateEmptyComponentCombo(this.cmbTipo, this.lbTipo)) {
            resultValidation = false;
        }

        if (!Utility.validateEmptyComponentTextField(this.txtNoDocumento, this.lbDocumento)) {
            resultValidation = false;
        }

        if (!Utility.validateEmptyComponentTextField(this.txtPNombre, this.lbPNombre)) {
            resultValidation = false;
        }

        if (!Utility.validateEmptyComponentTextField(this.txtPApellido, this.lbPApellido)) {
         
            resultValidation = false;
        }
     
        if (!Utility.validateEmptyComponentCombo(this.cmbGenero, this.lbGenero)) {
            resultValidation = false;
        }

        if (!Utility.validateEmptyComponentDatePicker(this.dpFechaNacimiento, this.lbFecha)) {
           
            resultValidation = false;
        }
      
        if (!Utility.validateEmptyComponentTextField(this.txtUsuario, this.lbUser)) {
          
            resultValidation = false;
        }
      
        if (!Utility.validateEmptyComponentPasswordField(this.txtPassword, this.lbPassword)) {
        
            resultValidation = false;
        }
      
        return resultValidation;
    }
}
