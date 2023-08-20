/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.controller;

import co.gov.deajvpar.gestcontractclient.fx.Exceptions.HttpResponseException;
import co.gov.deajvpar.gestcontractclient.fx.dtos.AnotacionContratoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.ContratoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.ModuloDto;
import co.gov.deajvpar.gestcontractclient.fx.logic.GestionFormPrincipal;
import co.gov.deajvpar.gestcontractclient.fx.logic.GestionNovedades;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyScreen;
import co.gov.deajvpar.gestcontractclient.fx.utility.Utility;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Jairo F
 */
public class PanelNovedadesController implements Initializable, IFormController {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtBusqueda;

    @FXML
    private TextArea txtNovedad;

    @FXML
    private ComboBox<ContratoDto> cmbContratos;
    @FXML
    private ListView<AnotacionContratoDto> listViewHistorico;
    @FXML
    private Label lbNoProceso, lbEtapa, lbEstado, lbContratista, lbSupervisor;
    @FXML
    private Label lbTipoContrato, lbCuantia, lbDuracion, lbVigencia;
    @FXML
    private Label lbModalidad, lbSubmodalidad, lbObjeto, lbErrorNovedad;
    @FXML
    private VBox panelRegistroNovedad;
    @FXML
    private CheckBox checkRegistro;
    @FXML
    private Button btnCrear, btnEditar, btnEliminar;
    @FXML
    private FontAwesomeIcon btnBuscar;

    private GestionNovedades logica = new GestionNovedades();

    @FXML
    public void clickBtnBuscar(MouseEvent e) {

        this.logica.selectedContrato(this.txtBusqueda.getText());
        this.loadDataSelectedContrato();
        this.viewNovedadSelected();

    }

    @FXML
    public void clickAnotaciones(MouseEvent e) {
        this.viewNovedadSelected();
    }

    @FXML
    public void clickBtnCrear(ActionEvent e) {

        boolean validar = this.validarEnvioFormulario();
        if (validar) {

            try {
                String description = this.txtNovedad.getText();
                this.logica.guardarNovedad(description);
                MyScreen.exitMessage();
                this.logica.setAutoCompletText(this.txtBusqueda);
                this.logica.selectedContrato(this.txtBusqueda.getText());
                this.loadDataSelectedContrato();
                this.checkRegistro.setSelected(false);
                this.activarDesactivarOpciones(true);

            } catch (HttpResponseException ex) {
                MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
            }

        }
    }

    @FXML
    public void clickBtnEliminar(ActionEvent e) {

        AnotacionContratoDto anotacion = this.listViewHistorico.getSelectionModel().getSelectedItem();
        if (anotacion != null) {

            try {

                this.logica.eliminarNovedad(anotacion);
                MyScreen.exitMessage();
                this.logica.setAutoCompletText(this.txtBusqueda);
                this.logica.selectedContrato(this.txtBusqueda.getText());
                this.loadDataSelectedContrato();

            } catch (HttpResponseException ex) {
                MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
            }
        }

    }

    @FXML
    public void clickBtnEditar(ActionEvent e) {

        boolean validar = this.validarEnvioFormulario();
        if (validar) {
            AnotacionContratoDto anotacion = this.listViewHistorico.getSelectionModel().getSelectedItem();
            if (anotacion != null) {

                try {
                    String description = this.txtNovedad.getText();
                    anotacion.setDescripcion(description);
                    this.logica.editarNovedad(anotacion);
                    MyScreen.exitMessage();
                    this.logica.setAutoCompletText(this.txtBusqueda);
                    this.logica.selectedContrato(this.txtBusqueda.getText());
                    this.loadDataSelectedContrato();

                } catch (HttpResponseException ex) {
                    MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
                }
            }
        }
    }

    @FXML
    public void clickCheckRegistro(ActionEvent e) {
        this.lbErrorNovedad.setVisible(false);
        if (this.checkRegistro.isSelected()) {
            this.txtNovedad.setDisable(false);
            this.limpiarFormulario();
        } else {

            this.viewNovedadSelected();
        }
        this.activarDesactivarOpciones(!this.checkRegistro.isSelected());

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.activarPrivilegiosModulo();
        this.logica.setAutoCompletText(this.txtBusqueda);
        this.loadDataSelectedContrato();

        this.txtBusqueda.setOnKeyReleased((KeyEvent e) -> {
            switch (e.getCode()) {
                case ENTER:
                    this.logica.selectedContrato(this.txtBusqueda.getText());
                    this.loadDataSelectedContrato();
                    this.viewNovedadSelected();
                    break;

                default:
                    break;
            }
        });

        this.listViewHistorico.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AnotacionContratoDto>() {
            @Override
            public void changed(ObservableValue<? extends AnotacionContratoDto> ov, AnotacionContratoDto t, AnotacionContratoDto t1) {
                viewNovedadSelected();
            }
        });
        this.txtBusqueda.requestFocus();

    }

    public void loadDataSelectedContrato() {

        ContratoDto contrato = this.logica.getSelectedContrato();
        if (contrato != null) {
            NumberFormat f = NumberFormat.getCurrencyInstance();
            String monto = f.format(contrato.getCuantiaInicialContrato());
            this.lbCuantia.setText(monto.toUpperCase());
            this.lbDuracion.setText(String.valueOf(contrato.getDuracionContrato()).toUpperCase() + " meses");
            this.lbEtapa.setText(contrato.getEtapaContrato().toString().toUpperCase());
            this.lbModalidad.setText(contrato.getModalidad().getNombreModalidad().toUpperCase());
            this.lbNoProceso.setText(contrato.getNoProceso().toUpperCase());
            this.lbContratista.setText(contrato.getContratistaAdjudicado() != null ? contrato.getContratistaAdjudicado().getNombrePersona().toUpperCase() : null);
            this.lbObjeto.setText(contrato.getObjetoContrato().substring(0, 1).toUpperCase() + contrato.getObjetoContrato().substring(1, contrato.getObjetoContrato().length()).toLowerCase());
            this.lbSubmodalidad.setText(contrato.getSubModalidad() != null ? contrato.getSubModalidad().getNombreSubModalidad().toUpperCase() : null);
            this.lbSupervisor.setText(contrato.getSupervisor() != null ? contrato.getSupervisor().getDataSupervisor().toUpperCase() : null);
            this.lbTipoContrato.setText(contrato.getTipoContrato().getDescripcion().toUpperCase());
            this.lbVigencia.setText(String.valueOf(contrato.getaInicioVigencia()).toUpperCase());
            this.lbEstado.setText(contrato.getEstadoContrato().toString().toUpperCase());
            this.logica.generateListViewNovedades(this.listViewHistorico);
            this.activarDesactivarPaneles(true);
        } else {
            this.activarDesactivarPaneles(false);
            this.listViewHistorico.getItems().clear();
            this.limpiarFormulario();
            this.btnCrear.setDisable(true);
            this.btnEditar.setDisable(true);
            this.btnEliminar.setDisable(true);
            this.txtBusqueda.requestFocus();
        }

    }

    @Override
    public boolean validarEnvioFormulario() {
        boolean resultValidation = true;
        boolean validate = Utility.validateEmptyComponentTextArea(this.txtNovedad, this.lbErrorNovedad);
        if (!validate) {
            resultValidation = false;
            this.lbErrorNovedad.setText("( required )");
            this.txtNovedad.requestFocus();
        }
        validate = this.logica.getSelectedContrato() != null;
        if (!validate) {
            resultValidation = false;
            this.lbErrorNovedad.setVisible(true);
            this.lbErrorNovedad.setText("( No hay contrato seleccionado )");
            this.txtBusqueda.requestFocus();
        }

        return resultValidation;
    }

    @Override
    public void activarDesactivarOpciones(boolean opt) {
        if (opt) {
            this.btnCrear.setDisable(opt);
            if (this.logica.isUpdate()) {
                this.btnEditar.setDisable(!opt);
            }
            if (this.logica.isDelete()) {
                this.btnEliminar.setDisable(!opt);
            }
        } else {
            
            if (this.logica.isCreate()) {
                this.btnCrear.setDisable(opt);
            }
             this.btnEditar.setDisable(!opt);
             this.btnEliminar.setDisable(!opt);

        }

    }

    @Override
    public void activarPrivilegiosModulo() {

        boolean create = this.logica.isCreate();
        boolean edit = this.logica.isUpdate();
        boolean read = this.logica.isView();
        boolean delete = this.logica.isDelete();

        this.txtBusqueda.setDisable(!read);
        this.txtNovedad.setDisable(!read);
        this.btnBuscar.setDisable(!read);

        this.checkRegistro.setDisable(!create);
        this.btnCrear.setDisable(!create);

        this.btnEditar.setDisable(!edit);

        this.btnEliminar.setDisable(!delete);

    }

    @Override
    public void limpiarFormulario() {
        this.txtNovedad.setText(null);

    }

    @Override
    public void activarDesactivarPaneles(boolean opt) {
        this.lbContratista.setVisible(opt);
        this.lbCuantia.setVisible(opt);
        this.lbDuracion.setVisible(opt);
        this.lbEstado.setVisible(opt);
        this.lbEtapa.setVisible(opt);
        this.lbModalidad.setVisible(opt);
        this.lbNoProceso.setVisible(opt);
        this.lbObjeto.setVisible(opt);
        this.lbSubmodalidad.setVisible(opt);
        this.lbSupervisor.setVisible(opt);
        this.lbTipoContrato.setVisible(opt);
        this.lbVigencia.setVisible(opt);
    }

    public void viewNovedadSelected() {
        AnotacionContratoDto anotacion = this.listViewHistorico.getSelectionModel().getSelectedItem();
        if (anotacion != null) {
            this.txtNovedad.setText(anotacion.getDescripcion());
            this.activarDesactivarOpciones(true);
        }
    }

}
