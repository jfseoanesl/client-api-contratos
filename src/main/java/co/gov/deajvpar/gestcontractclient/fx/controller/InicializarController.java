/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.controller;

import co.gov.deajvpar.gestcontractclient.fx.*;
import co.gov.deajvpar.gestcontractclient.fx.Exceptions.HttpResponseException;
import co.gov.deajvpar.gestcontractclient.fx.dtos.CiudadDptoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.UsuarioDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.DptoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.InicializarDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SistemaDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.TipoDocumentoDto;
import co.gov.deajvpar.gestcontractclient.fx.utility.HttpCodeResponse;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyGsonMapper;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyHttpApi;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyScreen;
import co.gov.deajvpar.gestcontractclient.fx.utility.StatusCode;
import co.gov.deajvpar.gestcontractclient.fx.utility.UsedApis;
import co.gov.deajvpar.gestcontractclient.fx.utility.Utility;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jairo F
 */
public class InicializarController implements Initializable {

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextField txtNombre;
    @FXML
    private ComboBox cmbTipo;
    @FXML
    private TextField txtNoDocumento;
    @FXML
    private TextField txtPNombre;
    @FXML
    private TextField txtSNombre;
    @FXML
    private TextField txtPApellido;
    @FXML
    private TextField txtSApellido;
    @FXML
    private ComboBox cmbGenero;
    @FXML
    private DatePicker dpFechaNacimiento;
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbTipo;
    @FXML
    private Label lbDocumento;
    @FXML
    private Label lbPNombre;
    @FXML
    private Label lbSNombre;
    @FXML
    private Label lbPApellido;
    @FXML
    private Label lbSApellido;
    @FXML
    private Label lbGenero;
    @FXML
    private Label lbFecha;
    @FXML
    private Label lbUser;
    @FXML
    private Label lbPassword;
    @FXML
    private HBox panelPrincipal;

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

        this.cmbGenero.getItems().addAll(List.of("Masculino", "Femenino", "Otro"));
        this.cmbTipo.getItems().addAll(List.of("Cedula ciudadania", "Registro civil",
                "Tarjeta identidad", "Cedula extranjeria", "Pasaporte", "Nit", "Otro"));

    }

    private void inicializarSistema() throws IOException {

        App.sistema.setName(this.txtNombre.getText());
        App.sistema.setTitulo(this.txtTitulo.getText());

        try {
            InicializarDto dto = new InicializarDto(App.sistema, this.getDptosAndCiudades(), this.getDataAdmon());
            MyHttpApi.jsonPostRequest(UsedApis.API_HOME_INITIALIZE, dto);
            StatusCode status = HttpCodeResponse.get(MyHttpApi.responseStatusCode());
            String response = MyHttpApi.stringResponse();
//            System.out.println(response);
            if (MyHttpApi.statusOk()) {
                App.sistema = MyGsonMapper.get().fromJson(response, SistemaDto.class);
                MyScreen.showMessage(App.getScene().getWindow(), "Peticion de inicializacion realizada con exito");
                String titulo = App.sistema.getNameAndTitle();
                App.newStage("Login", false, titulo, MyScreen.getMaxWidth(), MyScreen.getMaxHeight());
                                
            } else {

                MyScreen.errorMessage(status.toString(), response);
//                  System.out.println(MyHttpApi.stringResponse());
            }
        } catch (UnirestException ex) {
            MyScreen.errorMessage(ex);
           //ex.printStackTrace();
        }
    }

    private Map<String, DptoDto> getDptosAndCiudades() throws UnirestException {

        MyHttpApi.jsonGetRequest(UsedApis.API_DPTO_CIUDAD);
        Map<String, DptoDto> map;
        if (MyHttpApi.statusOk()) {
            String response = MyHttpApi.stringResponse();
            CiudadDptoDto[] ciudades = MyGsonMapper.get().fromJson(response, CiudadDptoDto[].class);
            map = Utility.listCiudadesToMapDpto(ciudades);
            return map;

        } else {

            StatusCode status = HttpCodeResponse.get(MyHttpApi.responseStatusCode());
            throw new HttpResponseException(status);
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
        
        userDto.setPassword(this.txtPassword.getText());
        userDto.setUserType("ADMINISTRADOR");
        return userDto;

    }

    private boolean validarForm() {

        boolean resultValidation = true;
        boolean empty;

        empty = this.txtTitulo.getText()==null || this.txtTitulo.getText().isBlank();
        if (empty) {
            this.txtTitulo.requestFocus();
            resultValidation = false;
        }
        this.lbTitulo.setVisible(empty);

        empty = this.txtNombre.getText()==null || this.txtNombre.getText().isBlank();
        if (empty) {
            this.txtNombre.requestFocus();
            resultValidation = false;
        }
        this.lbNombre.setVisible(empty);

        empty = this.cmbTipo.getSelectionModel().getSelectedIndex() == -1;
        if (empty) {
            this.cmbTipo.requestFocus();
            resultValidation = false;
        }
        this.lbTipo.setVisible(empty);

        empty = this.txtNoDocumento.getText()==null || this.txtNoDocumento.getText().isBlank();
        if (empty) {
            this.txtNoDocumento.requestFocus();
            resultValidation = false;
        }
        this.lbDocumento.setVisible(empty);

        empty = this.txtPNombre.getText()==null || this.txtPNombre.getText().isBlank();
        if (empty) {
            this.txtPNombre.requestFocus();
            resultValidation = false;
        }
        this.lbPNombre.setVisible(empty);

//        empty=this.txtSNombre.getText()==null || this.txtSNombre.getText().isBlank();
//        if(empty) {this.txtSNombre.requestFocus();
//                resultValidation = false;
//        }
//        this.lbSNombre.setVisible(empty);
//        resultValidation=!empty;

        empty = this.txtPApellido.getText()==null || this.txtPApellido.getText().isBlank();
        if (empty) {
            this.txtPApellido.requestFocus();
            resultValidation = false;
        }
        this.lbPApellido.setVisible(empty);

//        empty=this.txtSApellido.getText().isEmpty();
//        if(empty) {this.txtSApellido.requestFocus();
//            resultValidation = false;
//        }
//        this.lbSApellido.setVisible(empty);

        empty = this.cmbGenero.getSelectionModel().getSelectedIndex() == -1;
        if (empty) {
            this.cmbGenero.requestFocus();
            resultValidation = false;
        }
        this.lbGenero.setVisible(empty);

        empty = this.dpFechaNacimiento.getValue() == null;
        if (empty) {
            this.dpFechaNacimiento.requestFocus();
            resultValidation = false;
        }
        this.lbFecha.setVisible(empty);

        empty = this.txtUsuario.getText()==null || this.txtUsuario.getText().isBlank();
        if (empty) {
            this.txtUsuario.requestFocus();
            resultValidation = false;
        }
        this.lbUser.setVisible(empty);

        empty = this.txtPassword.getText()==null || this.txtPassword.getText().isBlank();
        if (empty) {
            this.txtPassword.requestFocus();
            resultValidation = false;
        }
        this.lbPassword.setVisible(empty);

        return resultValidation;
    }
}
