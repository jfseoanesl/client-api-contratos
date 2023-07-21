/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.controller;

import co.gov.deajvpar.gestcontractclient.fx.Exceptions.HttpResponseException;
import co.gov.deajvpar.gestcontractclient.fx.dtos.*;

import co.gov.deajvpar.gestcontractclient.fx.logic.*;
import co.gov.deajvpar.gestcontractclient.fx.utility.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Jairo F
 */
public class PanelContratosController implements Initializable, IFormController {

    /**
     * Initializes the controller class.
     */
    @FXML
    private StackPane stackPaneContrato;
    @FXML
    private HBox panelHbox;
    @FXML
    private ComboBox cmbTipoContrato, cmbModalidad, cmbSubmodalidad, cmbDeaj, cmbDpto, cmbCiudad;
    @FXML
    private ListView listLugarEjecucion;
    @FXML
    private TextField txtDireccion, txtNoProceso, txtVigencia, txtNoContrato, txtMontoContrato, txtEnlaceSecop;
    @FXML
    private TextArea txtObjetoContrato;
    @FXML
    private DatePicker dpFechaApertura, dpFechaInicio;
    @FXML
    private RadioButton rbMeses, rbAnios;
    @FXML
    private Spinner spinnerDuracion;
    @FXML
    private Label lbErrorNoProceso, lbErrorFechaInicio, lbErrorFechaApertura, lbErrorVigencia;
    @FXML
    private Label lbErrorNoContrato, lbErrorTipoContrato, lbErrorModalidad, lbErrorSubmodalidad;
    @FXML
    private Label lbErrorObjeto, lbErrorMonto, lbErrorDireccion, lbErrorLugar, lbErrorEnlace;
    @FXML
    private Button btnGuardar, btnLimpiar, btnCancelar;

    private final GestionCrearContrato logica = GestionContrato.getGestionCrear();

     @FXML
    public void clickBtnLimpiar(ActionEvent e){
        
        this.limpiarFormulario();
    
    }

    @FXML
    public void clickBtnCancelar() {
//        this.limpiarFormulario();
//        this.txtNoProceso.requestFocus();
        try {

            Utility.setModuloHBox(ModuloDto.CONTRATOS, this.panelHbox, GestionFormPrincipal.FXML_FORM_MODULE_CONTRATOS);

        } catch (IOException ex) {
            MyScreen.errorMessage("Error al cargar modulo", ex.getMessage());
        }
    }

    @FXML
    public void eventClickTipoContrato(ActionEvent e) {
        int index = this.cmbTipoContrato.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            this.logica.setTipoContrato(index);
        }

    }

    @FXML
    public void eventoClickSubmodaldiad(ActionEvent e) {
        int index = this.cmbSubmodalidad.getSelectionModel().getSelectedIndex();
        if(index!=-1){
            this.logica.setSubModalidad(index);
        }

    }

    @FXML
    public void clickAperturaProceso(ActionEvent e) {
        if (this.dpFechaApertura.getValue() != null) {
            this.txtVigencia.setText(String.valueOf(this.dpFechaApertura.getValue().getYear()));
        }
    }

    @FXML
    public void eventoClickListLugares(MouseEvent e) {
        int index = this.listLugarEjecucion.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            this.logica.removeLugarEjecucion(index);
            this.setDataListLugarEjecucion();
        }
    }

    @FXML
    public void eventClickAddLugarEjecucion(ActionEvent e) {
        int indexDpto = this.cmbDpto.getSelectionModel().getSelectedIndex();
        int indexCiudad = this.cmbCiudad.getSelectionModel().getSelectedIndex();
        String dir = this.txtDireccion.getText();
        if (indexDpto >= 0) {
            if (indexCiudad >= 0) {
                if (Utility.validateEmptyComponentTextField(this.txtDireccion)) {
                    this.logica.addLugarEjecucion(dir);
                    this.setDataListLugarEjecucion();
                    this.txtDireccion.setText(null);
                }

            } else {
                // mensaje validar
            }
        } else {
            // mensaje validar
        }

    }

    @FXML
    public void eventClickDpto(ActionEvent e) {
        int index = this.cmbDpto.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            this.logica.setDpto(index);
            this.setDataCiudades();
        }
    }

    @FXML
    public void eventoClickCiudad(ActionEvent e) {
        int index = this.cmbCiudad.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            this.logica.setCiudad(index);
//            this.setDataCiudades();
        }
    }

    @FXML
    public void eventoClickDeaj(ActionEvent e) {
        int index = this.cmbDeaj.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            this.logica.setDeaj(index);
            this.setDataDpto();

        }

    }

    @FXML
    public void eventoClickModalidad() {
        int index = this.cmbModalidad.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            this.logica.setModalidad(index);
            this.setDataCmbSubmodalidad();
        }

    }

    @FXML
    public void guardarGeneral(ActionEvent e) {

        boolean validar = this.validarEnvioFormulario();
        if (validar) {
            this.leerDatosContratoFromPanel();
            try {
                this.logica.guardarContrato();
                MyScreen.exitMessage();
                GestionContrato.getGestionSuscribir().setContrato(this.logica.getContrato());
                Utility.setModulo(ModuloDto.CONTRATOS, this.panelHbox, GestionFormPrincipal.FXML_FORM_MODULE_CONTRATOS_SUSCRIBIR);

            } catch (HttpResponseException ex) {
                MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
            } catch (IOException ex) {
                MyScreen.errorMessage("Error al cargar modulo", ex.getMessage());
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.logica.inicializar();
        this.setDataCmbTipoContrato();
        this.setDataCmbModalidad();
        this.setDataCmbDeajs();
        this.setDataListLugarEjecucion();
        this.setValueRadioButtonDuracion();
        this.setValueSpinnerDuracion();
        this.activarPrivilegiosModulo();
        //Utility.setMaskFormattedTextFieldNoProceso(this.txtNoProceso);
        Utility.setMaskFormattedCurrencyTextField(this.txtMontoContrato);
        //Utility.setMaskFormattedTextField(this.txtEnlaceSecop, Utility.URL);
    }

    @Override
    public boolean validarEnvioFormulario() {
        boolean resultValidation = true;

        boolean validate = Utility.validateEmptyComponentTextField(this.txtNoProceso, this.lbErrorNoProceso);
        if (!validate) {
            resultValidation = false;
        }
        validate = Utility.validateEmptyComponentDatePicker(this.dpFechaApertura, this.lbErrorFechaApertura);
        if (!validate) {
            resultValidation = false;
        }
        validate = Utility.validateEmptyComponentDatePicker(this.dpFechaInicio, this.lbErrorFechaInicio);
        if (!validate) {
            resultValidation = false;
        }
        validate = Utility.validateEmptyComponentTextField(this.txtVigencia, this.lbErrorVigencia);
        if (!validate) {
            resultValidation = false;
        }
        validate = Utility.validateEmptyComponentTextField(this.txtNoContrato, this.lbErrorNoContrato);
        if (!validate) {
            resultValidation = false;
        }
        validate = Utility.validateEmptyComponentCombo(this.cmbTipoContrato, this.lbErrorTipoContrato);
        if (!validate) {
            resultValidation = false;
        }
        validate = Utility.validateEmptyComponentCombo(this.cmbModalidad, this.lbErrorModalidad);
        if (!validate) {
            resultValidation = false;
        }
        if (this.logica.getListSubmodalidades() != null && this.logica.getListSubmodalidades().size() > 0) {
            validate = Utility.validateEmptyComponentCombo(this.cmbSubmodalidad, this.lbErrorSubmodalidad);
            if (!validate) {
                resultValidation = false;
            }
        }
        validate = Utility.validateEmptyComponentTextArea(this.txtObjetoContrato, this.lbErrorObjeto);
        if (!validate) {
            resultValidation = false;
        }
        validate = Utility.validateEmptyComponentTextField(this.txtMontoContrato, this.lbErrorMonto);
        if (!validate) {
            resultValidation = false;
        }
        validate = (double)this.txtMontoContrato.getTextFormatter().getValue()>0;
        this.lbErrorMonto.setVisible(!validate);
        if (!validate) {
            resultValidation = false;
            this.txtMontoContrato.requestFocus();
        }
        
        validate = Utility.validateEmptyComponentCombo(this.cmbDeaj, this.lbErrorDireccion);
        if (!validate) {
            resultValidation = false;
        }
        if (this.logica.getListLugarEjecucion().size() == 0) {
            resultValidation = false;
            this.lbErrorLugar.setVisible(true);
            this.txtDireccion.requestFocus();
        } else {
            this.lbErrorLugar.setVisible(false);
        }
        validate = Utility.validateEmptyComponentTextField(this.txtEnlaceSecop, this.lbErrorEnlace);
        if (!validate) {
            resultValidation = false;
        }

        return resultValidation;
    }

    @Override
    public void activarDesactivarOpciones(boolean opt) {

    }

    @Override
    public void activarPrivilegiosModulo() {

        this.btnGuardar.setDisable(!this.logica.isCreate());
       
    }

    @Override
    public void limpiarFormulario() {
        this.txtDireccion.setText(null);
        this.txtEnlaceSecop.setText(null);
        this.txtMontoContrato.setText("0");
        this.txtNoContrato.setText(null);
        this.txtNoProceso.setText(null);
        this.txtObjetoContrato.setText(null);
        this.txtVigencia.setText(null);

        this.cmbCiudad.getSelectionModel().select(-1);
        this.cmbCiudad.getItems().clear();

        this.cmbDeaj.getSelectionModel().select(-1);

        this.cmbDpto.getSelectionModel().select(-1);
        this.cmbDpto.getItems().clear();

        this.cmbModalidad.getSelectionModel().select(-1);
        this.cmbSubmodalidad.getSelectionModel().select(-1);
        this.cmbSubmodalidad.getItems().clear();

        this.cmbTipoContrato.getSelectionModel().select(-1);

        this.dpFechaApertura.setValue(null);
        this.dpFechaInicio.setValue(null);

        this.listLugarEjecucion.getItems().clear();

        this.setValueSpinnerDuracion();
        this.rbMeses.setSelected(true);
        this.logica.inicializar();

    }

    @Override
    public void activarDesactivarPaneles(boolean opt) {

    }

    private void setDataCmbTipoContrato() {
        this.logica.getListTipoContrato().forEach(t -> {
            this.cmbTipoContrato.getItems().add(t.getDescripcion());
        });
    }

    private void setDataCmbModalidad() {
        this.logica.getlistModalidades().forEach(m -> {
            this.cmbModalidad.getItems().add(m.getNombreModalidad());
        });
    }

    private void setDataCmbSubmodalidad() {

        this.cmbSubmodalidad.getItems().clear();
        for (SubModalidadDto s : this.logica.getListSubmodalidades()) {
            this.cmbSubmodalidad.getItems().add(s.getNombreSubModalidad());
        }
    }

    private void setDataCmbDeajs() {
        for (DireccionSeccionalDto d : this.logica.getListDeaj()) {
            this.cmbDeaj.getItems().add(d.getDescripcionSeccional());
        }
    }

    private void setDataDpto() {

        this.cmbDpto.getItems().clear();
        for (DptoDto d : this.logica.getListDpto()) {
            this.cmbDpto.getItems().add(d.getNombreDpto());
        }
        this.cmbCiudad.getItems().clear();

    }

    private void setDataCiudades() {
        this.cmbCiudad.getItems().clear();
        for (CiudadDto c : this.logica.getListCiudades()) {
            this.cmbCiudad.getItems().add(c.getNombreCiudad());
        }

    }

    private void setDataListLugarEjecucion() {

        this.listLugarEjecucion.getItems().clear();
        for (LugarEjecucionDto l : this.logica.getListLugarEjecucion()) {
            this.listLugarEjecucion.getItems().add(l);
        }

    }

    private void setValueRadioButtonDuracion() {
        ToggleGroup tg = new ToggleGroup();
        this.rbMeses.setToggleGroup(tg);
        this.rbAnios.setToggleGroup(tg);

    }

    private void setValueSpinnerDuracion() {
        SpinnerValueFactory valueFactoryDuracion = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000);
        this.spinnerDuracion.setValueFactory(valueFactoryDuracion);
    }

    private void leerDatosContratoFromPanel() {
        String noProceso = this.txtNoProceso.getText();
        String noContrato = this.txtNoContrato.getText();
        //double monto = Double.valueOf(this.txtMontoContrato.getText());
        double monto = (double) this.txtMontoContrato.getTextFormatter().getValue();
        String objeto = this.txtObjetoContrato.getText();
        String enlaceSecop = this.txtEnlaceSecop.getText();

        LocalDate fechaApertura = this.dpFechaApertura.getValue();
        LocalDate fechaInicio = this.dpFechaInicio.getValue();

        int duracion = (int) this.spinnerDuracion.getValue();
        if (this.rbAnios.isSelected()) {
            duracion = duracion * 12;
        }

        this.logica.crearContrato(noProceso, noContrato, objeto, monto, enlaceSecop, fechaApertura, fechaInicio, duracion);

    }

}
