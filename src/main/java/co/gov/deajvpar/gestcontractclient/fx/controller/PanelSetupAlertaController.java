/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.controller;

import co.gov.deajvpar.gestcontractclient.fx.Exceptions.HttpResponseException;
import co.gov.deajvpar.gestcontractclient.fx.dtos.*;
import co.gov.deajvpar.gestcontractclient.fx.logic.GestionDeaj;
import co.gov.deajvpar.gestcontractclient.fx.logic.GestionSetupAlerta;
import co.gov.deajvpar.gestcontractclient.fx.utility.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Jairo F
 */
public class PanelSetupAlertaController implements Initializable, IFormController {

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

    private final GestionSetupAlerta logicAlerta = new GestionSetupAlerta();
    private final GestionDeaj logicDeaj = new GestionDeaj();
    private DireccionSeccionalDto deajSelected;

    @FXML
    private void actionEventBotonGuardar(ActionEvent e) {
        if (this.deajSelected != null) {
            this.deajSelected.getSetupAlerta().setDiasRojo(this.txtRoja.getValue());
            this.deajSelected.getSetupAlerta().setDiasNaranja(this.txtNaranja.getValue());
            this.deajSelected.getSetupAlerta().setDiasVerde(this.txtVerde.getValue());
            try {
                this.logicAlerta.save(this.deajSelected.getSetupAlerta());
                MyScreen.exitMessage();
            } catch (HttpResponseException ex) {
                MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
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

    @FXML
    private void clickComboDeaj(ActionEvent a) {
        int index = this.cmbDeaj.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            this.deajSelected = this.logicDeaj.getAll()[index];
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

        this.loadComboDeaj();
        this.loadSetupSemaforo();
        this.activarBotonGuardar();

    }

    private void loadComboDeaj() {

        try {
            DireccionSeccionalDto[] deajs = this.logicDeaj.getAll();
            this.cmbDeaj.getItems().clear();
            if (this.logicAlerta.getUserDeaj() == null) {
                if (deajs != null) {
                    for (DireccionSeccionalDto dto : deajs) {
                        this.cmbDeaj.getItems().add(dto.getDescripcionSeccional());
                    }

                }
                this.cmbDeaj.setDisable(false);

            } else {
                DireccionSeccionalDto deaj = this.logicAlerta.getUserDeaj();
                this.cmbDeaj.getItems().add(deaj.getDescripcionSeccional());
                this.deajSelected = deaj;
                this.cmbDeaj.getSelectionModel().select(0);
                this.cmbDeaj.setDisable(true);

            }

        } catch (HttpResponseException ex) {
            MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
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

    public void activarBotonGuardar() {
        if (this.logicAlerta.isCreate() || this.logicAlerta.isUpdate()) {
            this.btnGuardar.setDisable(this.cmbDeaj.getSelectionModel().getSelectedIndex() == -1);
        }
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
        boolean create, update;
        create = this.logicAlerta.isCreate();
        update = this.logicAlerta.isUpdate();
        this.btnGuardar.setDisable(!create && !update);
        this.txtRoja.setDisable(!create && !update);
        this.txtNaranja.setDisable(!create && !update);
        this.txtVerde.setDisable(true);
    }

    @Override
    public void limpiarFormulario() {

    }

    @Override
    public void activarDesactivarPaneles(boolean opt) {

    }

}
