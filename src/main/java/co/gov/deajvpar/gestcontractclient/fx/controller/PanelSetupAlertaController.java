/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.controller;

import co.gov.deajvpar.gestcontractclient.fx.Exceptions.HttpResponseException;
import co.gov.deajvpar.gestcontractclient.fx.dtos.DireccionSeccionalDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.ModuloDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.PrivilegioDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUserDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUsuarioSingleton;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SetupAlertaDto;
import co.gov.deajvpar.gestcontractclient.fx.utility.HttpCodeResponse;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyGsonMapper;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyHttpApi;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyScreen;
import co.gov.deajvpar.gestcontractclient.fx.utility.StatusCode;
import co.gov.deajvpar.gestcontractclient.fx.utility.UsedApis;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Jairo F
 */
public class PanelSetupAlertaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label lbErrorDeaj, lbRojo, lbNaranja, lbVerde;
    @FXML
    private Spinner<Integer> txtRoja, txtNaranja, txtVerde;
    @FXML
    private Button btnGuardar;
    @FXML
    private ComboBox cmbDeaj;

    private SesionUserDto sesion;
    private boolean create, update, view, delete;
    private DireccionSeccionalDto Deajs[], deajSelected;

    @FXML
    private void actionEventBotonGuardar(ActionEvent e) {
        if (this.deajSelected != null) {
            this.deajSelected.getSetupAlerta().setDiasRojo(this.txtRoja.getValue());
            this.deajSelected.getSetupAlerta().setDiasNaranja(this.txtNaranja.getValue());
            this.deajSelected.getSetupAlerta().setDiasVerde(this.txtVerde.getValue());
            try {
                MyHttpApi.jsonPostRequest(UsedApis.API_ALERTA_SAVE, this.deajSelected.getSetupAlerta());
                String response = MyHttpApi.stringResponse();
                StatusCode status = HttpCodeResponse.get(MyHttpApi.responseStatusCode());
                if (MyHttpApi.statusOk()) {
                    SetupAlertaDto dto = MyGsonMapper.get().fromJson(response, SetupAlertaDto.class);
                    this.deajSelected.setSetupAlerta(dto);
                    MyScreen.showMessage(null, status.getStatus(), "Solicitud ejecutada con exito. El Setup de alarmas de contrato ha sido actualizado");
                } else {
                    MyScreen.errorMessage(status.toString(), response);
                    throw new HttpResponseException();
                }
            } catch (UnirestException ex) {
                MyScreen.errorMessage(ex);
            }
        }
    }

    @FXML
    public void EventoClickRojo(MouseEvent e) {
        this.loadGraficoAlerta();
    }

    @FXML
    public void EventoClickNaranja(MouseEvent e) {
        this.loadGraficoAlerta();
    }
//    @FXML
//    public void EventoClickVerde(MouseEvent e){
//        this.loadGraficoAlerta();
//    }

    @FXML
    private void clickComboDeaj(ActionEvent a) {
        int index = this.cmbDeaj.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            this.deajSelected = this.Deajs[index];
            this.loadSetupSemaforo();
        }
        this.activarBotonGuardar();
//        this.loadGraficoAlerta();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SpinnerValueFactory valueFactoryRojo = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5);
        SpinnerValueFactory valueFactoryNaranja = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
        SpinnerValueFactory valueFactoryVerde = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
        this.txtRoja.setValueFactory(valueFactoryRojo);
        this.txtNaranja.setValueFactory(valueFactoryNaranja);
        this.txtVerde.setValueFactory(valueFactoryVerde);

        this.sesion = SesionUsuarioSingleton.get();
        this.setPrivilegiosModulo();
        this.loadComboDeaj();
        this.loadSetupSemaforo();
        this.activarPrivilegiosModulo();
        this.activarBotonGuardar();
        
    }

    private void setPrivilegiosModulo() {

        this.create = this.sesion.checkPrivilegioModulo(ModuloDto.CONFIGURACION, PrivilegioDto.CREAR);
        this.update = this.sesion.checkPrivilegioModulo(ModuloDto.CONFIGURACION, PrivilegioDto.ACTUALIZAR);
        this.view = this.sesion.checkPrivilegioModulo(ModuloDto.CONFIGURACION, PrivilegioDto.CONSULTAR);
        this.delete = this.sesion.checkPrivilegioModulo(ModuloDto.CONFIGURACION, PrivilegioDto.ELIMINAR);

    }

    public void activarPrivilegiosModulo() {

        this.btnGuardar.setDisable(!this.create && !this.update);
        this.txtRoja.setDisable(!this.create && !this.update);
        this.txtNaranja.setDisable(!this.create && !this.update);
        this.txtVerde.setDisable(true);

    }

    private void loadComboDeaj() {

        try {
            this.Deajs = this.loadDeaj();
            this.cmbDeaj.getItems().clear();
            if (this.sesion.getUserDeaj() == null) {
                if (this.Deajs != null) {
                    for (DireccionSeccionalDto dto : this.Deajs) {
                        this.cmbDeaj.getItems().add(dto.getDescripcionSeccional());
                    }

                }
                this.cmbDeaj.setDisable(false);

            } else {
                DireccionSeccionalDto deaj = this.sesion.getUserDeaj();
                this.cmbDeaj.getItems().add(deaj.getDescripcionSeccional());
                this.deajSelected = this.sesion.getUserDeaj();
                this.cmbDeaj.getSelectionModel().select(0);
                this.cmbDeaj.setDisable(true);

            }

        } catch (UnirestException ex) {
            MyScreen.errorMessage(ex);
        }
    }

    private void loadSetupSemaforo() {
        if (this.deajSelected != null) {
            int rojo = this.deajSelected.getSetupAlerta().getDiasRojo();
            int naranja = this.deajSelected.getSetupAlerta().getDiasNaranja();
            int verde = this.deajSelected.getSetupAlerta().getDiasVerde();
            this.txtRoja.getValueFactory().setValue(rojo);
            this.txtNaranja.getValueFactory().setValue(naranja);
            this.txtVerde.getValueFactory().setValue(verde);

        }
        this.loadGraficoAlerta();
    }

    private void loadGraficoAlerta() {
        if (this.deajSelected != null) {
            int rojo = this.txtRoja.getValue();
            int naranja = this.txtNaranja.getValue();
            int verde = this.txtVerde.getValue();
            this.lbRojo.setText(String.valueOf(rojo));
            this.lbNaranja.setText(String.valueOf(rojo + naranja));
            this.lbVerde.setText(String.valueOf(rojo + naranja + verde));
        } else {
            this.lbRojo.setText("");
            this.lbNaranja.setText("");
            this.lbVerde.setText("");
        }
    }

    private DireccionSeccionalDto[] loadDeaj() throws UnirestException {

        MyHttpApi.jsonGetRequest(UsedApis.API_DEAJ_GET_ALL);
        String response = MyHttpApi.stringResponse();
        StatusCode status = HttpCodeResponse.get(MyHttpApi.responseStatusCode());
        if (MyHttpApi.statusOk()) {
            DireccionSeccionalDto[] dtos = MyGsonMapper.get().fromJson(response, DireccionSeccionalDto[].class);
            return dtos;
        } else {
            MyScreen.errorMessage(status.toString(), response);
            throw new HttpResponseException();
        }

    }

    public void activarBotonGuardar() {
        if (this.create || this.update) {
            this.btnGuardar.setDisable(this.cmbDeaj.getSelectionModel().getSelectedIndex() == -1);
        }
    }

}
