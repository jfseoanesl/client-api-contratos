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
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Jairo F
 */
public class PanelRegistroSupervisorContratoController implements Initializable, IFormController {

    /**
     * Initializes the controller class.
     */
    @FXML
    private StackPane stackPaneContrato;
    @FXML
    private ListView listViewSupervisores;
    @FXML
    private TextField txtFiltroSupervisor;
    @FXML
    private DatePicker dpFechaEjecucion, dpFechaDesignacion;
    @FXML
    private Label lbTitleContrato, lbErrorSupervisor, lbErrorEjecucion, lbErrorDesignacion;
    @FXML
    private Button btnGuardar, btnLimpiar, btnCancelar;
    @FXML
    private Label lbNoProceso;
    @FXML
    private Label lbTipoContrato, lbCuantia, lbDuracion, lbVigencia;
    @FXML
    private Label lbModalidad, lbSubmodalidad, lbObjeto;
    
    @FXML
    private HBox panelHbox;
    
    private GestionSupervisarContrato logica = GestionContrato.getGestionSupervisar();
    
    @FXML
    public void clickBtnCancelar(ActionEvent e) {
//        this.limpiarFormulario();
//        this.txtFiltroSupervisor.requestFocus();
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
    public void clickListSupervisores(MouseEvent e) {
        int index = this.listViewSupervisores.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            this.logica.setSupervisor(index);
        }
    }
    
    @FXML
    public void guardarSupervision(ActionEvent e) {
        
        boolean validar = this.validarEnvioFormulario();
        if (validar) {
            LocalDate fechaDesignacion = this.dpFechaDesignacion.getValue();
            LocalDate fechaInicioEjecucion = this.dpFechaEjecucion.getValue();
            try {
                this.logica.guardarSupervision(fechaDesignacion, fechaInicioEjecucion);
                MyScreen.exitMessage();
                Utility.setModuloHBox(ModuloDto.CONTRATOS, this.panelHbox, GestionFormPrincipal.FXML_FORM_MODULE_CONTRATOS);
                
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
        this.limpiarFormulario();
        this.lbTitleContrato.setText(this.logica.getTitleNoContrato());
        this.setDatosContrato();
        this.txtFiltroSupervisor.requestFocus();
        this.activarPrivilegiosModulo();

        //Utility.setMaskFormattedTextFieldNoProceso(this.txtNoProceso);
    }
    
    @Override
    public boolean validarEnvioFormulario() {
        boolean resultValidation = true;
        boolean validate = Utility.validateEmptyComponentDatePicker(this.dpFechaDesignacion, this.lbErrorDesignacion);
        if (!validate) {
            resultValidation = false;
        }
        validate = Utility.validateEmptyComponentDatePicker(this.dpFechaEjecucion, this.lbErrorEjecucion);
        if (!validate) {
            resultValidation = false;
        }
        if (this.logica.getSupervisor() == null) {
            this.lbErrorSupervisor.setVisible(true);
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
        //this.btnLimpiar.setDisable(!this.logica.isUpdate());
    }
    
    @Override
    public void limpiarFormulario() {
        
        this.txtFiltroSupervisor.setText(null);
        this.setDatosListSupervisores();
        this.dpFechaDesignacion.setValue(null);
        this.dpFechaEjecucion.setValue(null);
        
    }
    
    @Override
    public void activarDesactivarPaneles(boolean opt) {
        
    }
    
    private void setDatosListSupervisores() {
        
        try {
            List<UserDto> list = this.logica.getListSupervisores();
            this.listViewSupervisores.getItems().clear();
            for (UserDto dto : list) {
                this.listViewSupervisores.getItems().add(dto.getDataSupervisor());
            }
            if (list.size() > 0) {
                this.listViewSupervisores.getSelectionModel().select(0);
                this.logica.setSupervisor(0);
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
        this.lbObjeto.setText(this.logica.getContrato().getObjetoContrato().substring(0, 1).toUpperCase()+this.logica.getContrato().getObjetoContrato().substring(1, this.logica.getContrato().getObjetoContrato().length()).toLowerCase());
        //this.lbObjeto.setTooltip(new Tooltip(this.lbObjeto.getText()));
        this.lbSubmodalidad.setText(this.logica.getContrato().getSubModalidad() != null ? this.logica.getContrato().getSubModalidad().getNombreSubModalidad() : null);
        this.lbTipoContrato.setText(this.logica.getContrato().getTipoContrato().getDescripcion());
        this.lbVigencia.setText(String.valueOf(this.logica.getContrato().getaInicioVigencia()));
        
    }
    
}
