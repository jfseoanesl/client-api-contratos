/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.controller;

import co.gov.deajvpar.gestcontractclient.fx.App;
import co.gov.deajvpar.gestcontractclient.fx.Exceptions.HttpResponseException;
import co.gov.deajvpar.gestcontractclient.fx.dtos.*;
import co.gov.deajvpar.gestcontractclient.fx.logic.*;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyScreen;
import co.gov.deajvpar.gestcontractclient.fx.utility.Utility;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Jairo F
 */
public class PanelSuscribirContratoController implements Initializable, IFormController {

    @FXML
    private StackPane stackPaneContrato;
    @FXML
    private HBox panelHbox;
    @FXML
    private ComboBox cmbTipoDocumento, cmbGenero;
    @FXML
    private TextField txtIdPersona, txtNoDocumento, txtPNombre, txtSNombre, txtPApellido, txtSApellido, txtRazonSocial;
    @FXML
    private DatePicker dpFechaNacimiento;
    @FXML
    private DatePicker dpFechaAdjudicacion, dpFechaSuscripcion;
    @FXML
    private Label lbTitleContratoNo;
    @FXML
    private Label lbErrorTipo, lbErrorDocumento, lbErrorPNombre, lbErrorSNombre, lbErrorPApellido;
    @FXML
    private Label lbErrorSApellido, lbErrorGenero, lbErrorFechaNacimiento, lbErrorRazonSocial;
    @FXML
    private Label lbErrorAdjudicacion, lbErrorSuscripcion;
    @FXML
    private Button btnGuardar, btnLimpiar, btnCancelar;
    @FXML
    private Label lbNoProceso;
    @FXML
    private Label lbTipoContrato, lbCuantia, lbDuracion, lbVigencia;
    @FXML
    private Label lbModalidad, lbSubmodalidad, lbObjeto;

    private final GestionPersona logicPersona = new GestionPersona();
    private final GestionTipoDocumento logicDocumento = new GestionTipoDocumento();
    private final GestionSuscribirContrato logica = GestionContrato.getGestionSuscribir();

    @FXML
    public void clickBtnCancelar() {
//        this.limpiarFormulario();
//        this.txtNoDocumento.requestFocus();
        try {

            Utility.setModuloHBox(ModuloDto.CONTRATOS, this.panelHbox, GestionFormPrincipal.FXML_FORM_MODULE_CONTRATOS);

        } catch (IOException ex) {
            MyScreen.errorMessage("Error al cargar modulo", ex.getMessage());
        }
    }

    @FXML
    public void clickBtnLimpiar(ActionEvent e){
        
        this.limpiarFormulario();
    
    }
    
    @FXML
    public void clickBuscarPersona(ActionEvent e) {

        if (this.validarEnvioBusqueda()) {

            int index = this.cmbTipoDocumento.getSelectionModel().getSelectedIndex();
            TipoDocumentoDto tipo = this.logicDocumento.getAll()[index];
            String documento = this.txtNoDocumento.getText();
            PersonaDto dto = new PersonaDto();
            dto.setNoDocumento(documento);
            dto.setTipoDocumento(tipo);

            try {
                dto = this.logicPersona.findByDocument(dto);
                this.logica.setPersona(dto);
                this.setDatosPersona(dto);
                this.activarDesactivarOpciones(true);
            } catch (HttpResponseException ex) {
                MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
            }

        }
    }

    @FXML
    public void guardarSuscripcion(ActionEvent e) {
        boolean validar = this.validarEnvioFormulario();
        if (validar) {
            String pNombre = this.txtPNombre.getText();
            String sNombre = this.txtSNombre.getText();
            String pApellido = this.txtPApellido.getText();
            String sApellido = this.txtSApellido.getText();
            LocalDate fechaNacimiento = this.dpFechaNacimiento.getValue();
            LocalDate fechaAdjudicacion = this.dpFechaAdjudicacion.getValue();
            LocalDate fechaSuscripcion = this.dpFechaSuscripcion.getValue();
            String razonSocial = this.txtRazonSocial.getText();
            int index = this.cmbGenero.getSelectionModel().getSelectedIndex();
            String genero = null;
            if (index >= 0) {
                genero = this.cmbGenero.getSelectionModel().getSelectedItem().toString();
            }

            try {
                this.logica.guardarSuscripcion(pNombre, sNombre, pApellido, sApellido, razonSocial, fechaNacimiento, fechaSuscripcion, fechaAdjudicacion, genero);
                MyScreen.exitMessage();
                GestionContrato.getGestionSupervisar().setContrato(this.logica.getContrato());
                Utility.setModulo(ModuloDto.CONTRATOS, this.panelHbox, GestionFormPrincipal.FXML_FORM_MODULE_CONTRATOS_SUPERVISOR);

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

        this.setDataCmbGenero();
        this.setDatosComboTipoDocumento();
        this.lbTitleContratoNo.setText(this.logica.getTitleNoContrato());
        this.setDatosContrato();
        this.limpiarFormulario();
        this.txtNoDocumento.requestFocus();

        //Utility.setMaskFormattedTextFieldNoProceso(this.txtNoProceso);
    }

    public boolean validarEnvioBusqueda() {
        this.desmascarErrores();
        boolean resultValidation = true;
        boolean validate = Utility.validateEmptyComponentCombo(this.cmbTipoDocumento, this.lbErrorDocumento);
        if (!validate) {
            resultValidation = false;
        }
        validate = Utility.validateEmptyComponentTextField(this.txtNoDocumento, this.lbErrorDocumento);
        if (!validate) {
            resultValidation = false;
        }
        return resultValidation;
    }

    public void desmascarErrores() {
        this.lbErrorAdjudicacion.setVisible(false);
        this.lbErrorDocumento.setVisible(false);
        this.lbErrorFechaNacimiento.setVisible(false);
        this.lbErrorGenero.setVisible(false);
        this.lbErrorPApellido.setVisible(false);
        this.lbErrorPNombre.setVisible(false);
        this.lbErrorRazonSocial.setVisible(false);
        this.lbErrorSuscripcion.setVisible(false);
        //this.lbErrorTipo.setVisible(false);

    }

    @Override
    public boolean validarEnvioFormulario() {
        this.desmascarErrores();
        if (this.logica.getPersona() != null) {
            boolean resultValidation = true;
            boolean validate = Utility.validateEmptyComponentCombo(this.cmbTipoDocumento, this.lbErrorDocumento);
            if (!validate) {
                resultValidation = false;
            }

            validate = Utility.validateEmptyComponentTextField(this.txtNoDocumento, this.lbErrorDocumento);
            if (!validate) {
                resultValidation = false;
            }
            if (this.logica.getPersona().getTipoDocumento().getTipoPersona() == TipoPersona.NATURAL) {

                validate = Utility.validateEmptyComponentTextField(this.txtPNombre, this.lbErrorPNombre);
                if (!validate) {
                    resultValidation = false;
                }
                validate = Utility.validateEmptyComponentTextField(this.txtPApellido, this.lbErrorPApellido);
                if (!validate) {
                    resultValidation = false;
                }
                validate = Utility.validateEmptyComponentCombo(this.cmbGenero, this.lbErrorGenero);
                if (!validate) {
                    resultValidation = false;
                }
                validate = Utility.validateEmptyComponentDatePicker(this.dpFechaNacimiento, this.lbErrorFechaNacimiento);
                if (!validate) {
                    resultValidation = false;
                }
            } else {
                validate = Utility.validateEmptyComponentTextField(this.txtRazonSocial, this.lbErrorRazonSocial);
                if (!validate) {
                    resultValidation = false;
                }
            }

            validate = Utility.validateEmptyComponentDatePicker(this.dpFechaAdjudicacion, this.lbErrorAdjudicacion);
            if (!validate) {
                resultValidation = false;
            }
            validate = Utility.validateEmptyComponentDatePicker(this.dpFechaSuscripcion, this.lbErrorSuscripcion);
            if (!validate) {
                resultValidation = false;
            }

            return resultValidation;
        }
        return false;
    }

    @Override
    public void activarDesactivarOpciones(boolean opt) {

        PersonaDto dto = this.logica.getPersona();
        if (dto.getTipoDocumento().getTipoPersona() == TipoPersona.NATURAL) {
            opt = false;
        }
        this.activarDesactivarPaneles(opt);

    }

    @Override
    public void activarPrivilegiosModulo() {
        this.btnGuardar.setDisable(!this.logica.isCreate());       
    }

    @Override
    public void limpiarFormulario() {
        this.txtNoDocumento.setText(null);
        this.txtPNombre.setText(null);
        this.txtSNombre.setText(null);
        this.txtPApellido.setText(null);
        this.txtSApellido.setText(null);
        this.cmbGenero.getSelectionModel().select(-1);
        this.dpFechaNacimiento.setValue(null);
        this.txtRazonSocial.setText(null);
        this.logica.setPersona(null);

    }

    @Override
    public void activarDesactivarPaneles(boolean opt) {
        this.txtPNombre.setDisable(opt);
        this.txtSNombre.setDisable(opt);
        this.txtPApellido.setDisable(opt);
        this.txtSApellido.setDisable(opt);
        this.cmbGenero.setDisable(opt);
        this.dpFechaNacimiento.setDisable(opt);
        this.txtRazonSocial.setDisable(!opt);
    }

    private void setDataCmbGenero() {

        GeneroPersonaDto[] generos = GeneroPersonaDto.values();
        this.cmbGenero.getItems().addAll(Arrays.asList(generos));
    }

    public void setDatosPersona(PersonaDto dto) {

        this.txtIdPersona.setText(dto.getIdPersona() == null ? null : String.valueOf(dto.getIdPersona()));
        this.txtNoDocumento.setText(dto.getNoDocumento());
        this.txtPNombre.setText(dto.getpNombre());
        this.txtSNombre.setText(dto.getsNombre());
        this.txtPApellido.setText(dto.getpApellido());
        this.txtSApellido.setText(dto.getsApellido());
        this.cmbGenero.getSelectionModel().select(dto.getGenero());
        this.dpFechaNacimiento.setValue(dto.getFechaNacimiento());
        this.txtRazonSocial.setText(dto.getNombreEmpresa());

    }

    private void setDatosComboTipoDocumento() {

        try {
            List<TipoDocumentoDto> list = List.of(this.logicDocumento.getAll());
            for (TipoDocumentoDto dto : list) {
                this.cmbTipoDocumento.getItems().add(dto.getDescripcion());
            }

        } catch (HttpResponseException ex) {
            MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
        }

    }

    public void setDatosContrato() {

        NumberFormat format = NumberFormat.getCurrencyInstance();
        this.lbCuantia.setText(format.format(this.logica.getContrato().getCuantiaInicialContrato()));
        this.lbDuracion.setText(this.logica.getContrato().getDuracionContrato() + " meses");
        this.lbModalidad.setText(this.logica.getContrato().getModalidad().getNombreModalidad());
//        this.lbNoContrato.setText(this.logica.getContrato().getNoContrato());
        this.lbNoProceso.setText(this.logica.getContrato().getNoProceso());
        this.lbObjeto.setText(this.logica.getContrato().getObjetoContrato().substring(0, 1).toUpperCase() + this.logica.getContrato().getObjetoContrato().substring(1, this.logica.getContrato().getObjetoContrato().length()).toLowerCase());
        this.lbSubmodalidad.setText(this.logica.getContrato().getSubModalidad() != null ? this.logica.getContrato().getSubModalidad().getNombreSubModalidad() : null);
        this.lbTipoContrato.setText(this.logica.getContrato().getTipoContrato().getDescripcion());
        this.lbVigencia.setText(String.valueOf(this.logica.getContrato().getaInicioVigencia()));

    }

}
