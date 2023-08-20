/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.controller;

import co.gov.deajvpar.gestcontractclient.fx.dtos.ContratoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.EstadoContratoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.ModuloDto;
import co.gov.deajvpar.gestcontractclient.fx.logic.GestionContrato;
import co.gov.deajvpar.gestcontractclient.fx.logic.GestionDashBoarContrato;
import co.gov.deajvpar.gestcontractclient.fx.logic.GestionFormPrincipal;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyScreen;
import co.gov.deajvpar.gestcontractclient.fx.utility.UsedApis;
import co.gov.deajvpar.gestcontractclient.fx.utility.Utility;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Jairo F
 */
public class DashBoardContratosController implements Initializable, IFormController {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ListView<ContratoDto> listContratos;
    @FXML
    private Label lbNoProceso;
    @FXML
    private Label lbNoContrato, lbEtapaContrato, lbSeccional;
    @FXML
    private Label lbTipoContrato, lbCuantia, lbDuracion, lbVigencia;
    @FXML
    private Label lbModalidad, lbSubmodalidad, lbObjeto, lbSupervisor;
    @FXML
    private Label lbLugarEjecucion;
    @FXML
    private Label lbNombreContratista;
    @FXML
    private Label  lbEstadoContrato;
    @FXML
    private Hyperlink linkSecop;
    @FXML
    private Button btnNuevoContrato, btnSuscripcion, btnSupervisor;
    @FXML
    private StackPane stackPane;
    @FXML
    private HBox panelHbox;
    @FXML
    private ComboBox cmpEtapaContrato;
    @FXML
    private PieChart chartTipoContratos, chartModalidad;
    @FXML
    private BarChart<String, Number> charEtapaContrato;
    @FXML
    private LineChart<String, Number> chartVigencia;

    private GestionDashBoarContrato logica = new GestionDashBoarContrato();

    @FXML
    private void clickLink(ActionEvent e){
        try {
            Desktop.getDesktop().browse(new URI(this.linkSecop.getText()));
        } catch (URISyntaxException | IOException ex) {
            MyScreen.errorMessage(ex);
        } 
 }
    @FXML
    public void clickComboEtapas(ActionEvent e) {
        if (this.cmpEtapaContrato.getSelectionModel().getSelectedIndex() > 0) {

            this.logica.setEstadoContrato(this.cmpEtapaContrato.getSelectionModel().getSelectedItem().toString());

        } else {
            this.logica.setEstadoContrato(null);
        }

        this.logica.generateListViewContratos(listContratos);
    }

    @FXML
    public void clickNuevoContrato(ActionEvent e) {
        try {

            Utility.setModuloHBox(ModuloDto.CONTRATOS, this.panelHbox, GestionFormPrincipal.FXML_FORM_MODULE_CONTRATOS_CREAR);

        } catch (IOException ex) {
            MyScreen.errorMessage("Error al cargar modulo", ex.getMessage());
        }
    }

    @FXML
    public void clickBtnSuscripcion(ActionEvent e) {

        if (this.listContratos.getSelectionModel().getSelectedIndex() >= 0) {
            ContratoDto contrato = this.listContratos.getSelectionModel().getSelectedItem();
            try {
                GestionContrato.getGestionSuscribir().setContrato(contrato);
                Utility.setModuloHBox(ModuloDto.CONTRATOS, this.panelHbox, GestionFormPrincipal.FXML_FORM_MODULE_CONTRATOS_SUSCRIBIR);

            } catch (IOException ex) {
                MyScreen.errorMessage("Error al cargar modulo", ex.getMessage());
            }
        }
    }

    @FXML
    public void clickBtnSupervisor(ActionEvent e) {

        if (this.listContratos.getSelectionModel().getSelectedIndex() >= 0) {
            ContratoDto contrato = this.listContratos.getSelectionModel().getSelectedItem();
            try {
                GestionContrato.getGestionSupervisar().setContrato(contrato);
                Utility.setModuloHBox(ModuloDto.CONTRATOS, this.panelHbox, GestionFormPrincipal.FXML_FORM_MODULE_CONTRATOS_SUPERVISOR);

            } catch (IOException ex) {
               MyScreen.errorMessage("Error al cargar modulo", ex.getMessage());
            }
        }
    }

    @FXML
    public void clickListView(MouseEvent e) {
        if (this.listContratos.getSelectionModel().getSelectedIndex() >= 0) {
            this.limpiarFormulario();
        }

    }

   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        this.limpiarFormulario();
        if (this.logica.isView()) {
            this.logica.generateListViewContratos(this.listContratos);
            this.logica.setComboEstados(this.cmpEtapaContrato);

            this.listContratos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ContratoDto>() {
                @Override
                public void changed(ObservableValue<? extends ContratoDto> ov, ContratoDto t, ContratoDto t1) {
                    limpiarFormulario();
                }
            });

            //this.logica.setGraficaPie(this.chartTipoContratos, UsedApis.API_CONTRATO_REPORTING_BYTIPO);
            this.logica.setGraficaPie(this.chartModalidad, UsedApis.API_CONTRATO_REPORTING_BYMODALIDAD);
            this.logica.setGraficaBar(this.charEtapaContrato, UsedApis.API_CONTRATO_REPORTING_BYESTADO);
            this.logica.setGraficaLine(this.chartVigencia, UsedApis.API_CONTRATO_REPORTING_BYVIGENCIA, "Inicio Vigencia");
            this.limpiarFormulario();
        }

        this.activarPrivilegiosModulo();
    }

    @Override
    public boolean validarEnvioFormulario() {
        return true;
    }

    @Override
    public void activarDesactivarOpciones(boolean opt) {

    }

    @Override
    public void activarPrivilegiosModulo() {
        this.btnNuevoContrato.setDisable(!this.logica.isCreate());
        this.btnSupervisor.setDisable(!this.logica.isUpdate());
        this.btnSuscripcion.setDisable(!this.logica.isDelete());
    }

    @Override
    public void limpiarFormulario() {

        if (this.listContratos.getItems().isEmpty()) {
            this.lbCuantia.setText(null);
            this.lbDuracion.setText(null);
            this.lbEtapaContrato.setText(null);
            this.lbLugarEjecucion.setText(null);
            this.lbModalidad.setText(null);
            this.lbSubmodalidad.setText(null);
            this.lbNoContrato.setText(null);
            this.lbNoProceso.setText(null);
            this.lbNombreContratista.setText(null);
            this.lbObjeto.setText(null);
            this.lbSeccional.setText(null);
            this.lbSubmodalidad.setText(null);
            this.lbSupervisor.setText(null);
            this.lbTipoContrato.setText(null);
            this.lbVigencia.setText(null);
            this.lbEstadoContrato.setText(null);
            this.btnSupervisor.setDisable(true);
            this.btnSuscripcion.setDisable(true);
            this.linkSecop.setText(null);

        } else {
            ContratoDto contrato = this.listContratos.getSelectionModel().getSelectedItem();
            if (contrato != null) {
                NumberFormat f = NumberFormat.getCurrencyInstance();
                String monto = f.format(contrato.getCuantiaInicialContrato());
                this.lbCuantia.setText(monto.toUpperCase());
                this.lbDuracion.setText(String.valueOf(contrato.getDuracionContrato()).toUpperCase() + " meses");
                this.lbEtapaContrato.setText(contrato.getEtapaContrato().toString().toUpperCase());
                this.lbLugarEjecucion.setText(contrato.getDataLugarEjecucion().toUpperCase());
                this.lbModalidad.setText(contrato.getModalidad().getNombreModalidad().toUpperCase());
                this.lbNoContrato.setText(contrato.getNoContrato().toUpperCase());
                this.lbNoProceso.setText(contrato.getNoProceso().toUpperCase());
                this.lbNombreContratista.setText(contrato.getContratistaAdjudicado() != null ? contrato.getContratistaAdjudicado().getNombrePersona().toUpperCase() : null);
                this.lbObjeto.setText(contrato.getObjetoContrato().substring(0, 1).toUpperCase()+contrato.getObjetoContrato().substring(1, contrato.getObjetoContrato().length()).toLowerCase());
                this.lbSeccional.setText(contrato.getDireccion().getDescripcionSeccional().toUpperCase());
                this.lbSubmodalidad.setText(contrato.getSubModalidad() != null ? contrato.getSubModalidad().getNombreSubModalidad().toUpperCase() : null);
                this.lbSupervisor.setText(contrato.getSupervisor() != null ? contrato.getSupervisor().getDataSupervisor().toUpperCase() : null);
                this.lbTipoContrato.setText(contrato.getTipoContrato().getDescripcion().toUpperCase());
                this.lbVigencia.setText(String.valueOf(contrato.getaInicioVigencia()).toUpperCase());
                this.lbEstadoContrato.setText(contrato.getEstadoContrato().toString().toUpperCase());
                this.linkSecop.setText(contrato.getEnlaceSecop());
                if (this.logica.isUpdate()) {
                    if (contrato.getEstadoContrato().compareTo(EstadoContratoDto.PLANEACION) == 0) {

                        this.btnSuscripcion.setDisable(false);
                        this.btnSupervisor.setDisable(true);

                    } else if (contrato.getEstadoContrato().compareTo(EstadoContratoDto.CELEBRADO) == 0) {

                        this.btnSuscripcion.setDisable(true);
                        this.btnSupervisor.setDisable(false);
                    } else {
                        this.btnSuscripcion.setDisable(true);
                        this.btnSupervisor.setDisable(true);
                    }
                }
            }
        }
    }

    @Override
    public void activarDesactivarPaneles(boolean opt) {

    }

}
