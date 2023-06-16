/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.controller;

import co.gov.deajvpar.gestcontractclient.fx.Exceptions.HttpResponseException;
import co.gov.deajvpar.gestcontractclient.fx.dtos.*;
import co.gov.deajvpar.gestcontractclient.fx.dtos.table.TipoContratoDtoTable;
import co.gov.deajvpar.gestcontractclient.fx.logic.GestionTipoContrato;
import co.gov.deajvpar.gestcontractclient.fx.utility.*;
import java.net.URL;
import java.util.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.*;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Jairo F
 */
public class PanelTipoContratosController implements Initializable, IFormController {

    /**
     * Initializes the controller class.
     */
    @FXML
    private VBox panelCrear, panelVer;
    @FXML
    private Button btnCrear, btnGuardar, btnEditar, btnEliminar, btnActualizar;
    @FXML
    private Label lbErrorTipo;
    @FXML
    private TableView<TipoContratoDtoTable> tablaContratos;
    @FXML
    private TextField txtTipo, txtBuscar, txtId;
    @FXML

    private TableColumn<TipoContratoDtoTable, String> tableColumnTipo, tableColumnId;
    private ObservableList<TipoContratoDtoTable> data;

    private GestionTipoContrato logicContrato = new GestionTipoContrato();

    @FXML
    private void actionEventBotonCrear(ActionEvent e) {
        this.activarDesactivarPaneles(true);
        this.limpiarFormulario();
        this.activarDesactivarOpciones(true);
    }

    @FXML
    private void actionEventBotonEditar(ActionEvent e) {
        TipoContratoDtoTable selected = this.tablaContratos.getSelectionModel().getSelectedItem();
        if (selected != null) {

            this.activarDesactivarPaneles(true);
            this.limpiarFormulario();
            this.activarDesactivarOpciones(false);
            TipoContratoDto dto = this.logicContrato.getObjectTipoContract(selected.getId());
            this.txtId.setText(dto.getId().toString());
            this.txtTipo.setText(dto.getDescripcion());
        }

    }

    @FXML
    private void actionEventBotonActualizar(ActionEvent e) {
        if (this.validarEnvioFormulario()) {
            TipoContratoDto tipoContrato = this.leerDatosTipoContrato();
            this.actualizarTipoContrato(tipoContrato);
        }
    }

    @FXML
    private void actionEventBotonEliminar(ActionEvent e) {
        //this.limpiarPanelCrear();
        TipoContratoDtoTable selected = this.tablaContratos.getSelectionModel().getSelectedItem();

        if (selected != null) {
            Optional<ButtonType> result = MyScreen.confirmMessage(null, "Confirmacion", "Esta seguro de realizar la eliminacion?");
            if (result.get() == ButtonType.OK) {
                TipoContratoDto dto = new TipoContratoDto();
                dto.setId(selected.getId());
                this.eliminarTipoContrato(dto);
            }
        }
    }

    private TipoContratoDto leerDatosTipoContrato() {

        TipoContratoDto tipoContrato = new TipoContratoDto();
        tipoContrato.setDescripcion(this.txtTipo.getText());
        tipoContrato.setCrateByuser(this.logicContrato.getSesionUser());

        if (this.txtId.getText() == null) {
            tipoContrato.setId(null);
        } else {
            tipoContrato.setId(Long.valueOf(this.txtId.getText()));
        }
        return tipoContrato;
    }

    @FXML
    private void actionEventBotonGuardar(ActionEvent e) {
        if (this.validarEnvioFormulario()) {
            TipoContratoDto tipoContrato = this.leerDatosTipoContrato();
            this.actualizarTipoContrato(tipoContrato);
        }
    }

    @FXML
    private void actionEventBtnVolver(ActionEvent e) {
        this.activarDesactivarPaneles(false);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        try {

            this.lbErrorTipo.setVisible(false);
            this.activarDesactivarOpciones(true);
            this.activarDesactivarPaneles(false);

            this.initTable();
            this.txtTipo.setText(null);
            this.activarPrivilegiosModulo();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void actualizarTipoContrato(TipoContratoDto dto) {

        try {
            this.logicContrato.save(dto);
            MyScreen.exitMessage();
            this.initTable();
            this.limpiarFormulario();

        } catch (HttpResponseException ex) {
            MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
        }

    }

    private void eliminarTipoContrato(TipoContratoDto dto) {

        try {
            this.logicContrato.delete(dto);
            MyScreen.exitMessage();
            this.initTable();
            this.limpiarFormulario();

        } catch (HttpResponseException ex) {
            MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
        }

    }

    private void setFiltroTable() {
        FilteredList<TipoContratoDtoTable> filteredData = new FilteredList<>(this.data, p -> true);
        this.txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(dto -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (dto.getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (String.valueOf(dto.getId()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        SortedList<TipoContratoDtoTable> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(this.tablaContratos.comparatorProperty());
        this.tablaContratos.setItems(sortedData);
    }

    private void initTable() {

        this.tableColumnTipo.setCellValueFactory(new PropertyValueFactory("descripcion"));
        this.tableColumnId.setCellValueFactory(new PropertyValueFactory("id"));
        this.data = FXCollections.observableArrayList();

        try {
            TipoContratoDto[] list = this.logicContrato.getAll();
            for (TipoContratoDto dto : list) {
                TipoContratoDtoTable row = new TipoContratoDtoTable(dto);
                this.data.add(row);
            }
            if (this.logicContrato.isDelete()) {

                this.btnEliminar.setDisable(!(data.size() > 0));

            } else {
                this.btnEliminar.setDisable(true);
            }
            if (this.logicContrato.isView()) {
                this.setFiltroTable();
            }

        } catch (HttpResponseException ex) {
            MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
        }

    }



    @Override
    public void activarPrivilegiosModulo() {
        
        boolean create = this.logicContrato.isCreate();
        boolean update = this.logicContrato.isUpdate();
        boolean view = this.logicContrato.isView();
        boolean delete = this.logicContrato.isDelete();
        
        this.btnGuardar.setDisable(!create);
        this.btnCrear.setDisable(!create);

        this.btnEditar.setDisable(!update);
        this.btnActualizar.setDisable(!update);

        this.btnEliminar.setDisable(!delete);

        this.txtBuscar.setDisable(!view);
        this.tablaContratos.setDisable(!view);

    }

    @Override
    public boolean validarEnvioFormulario() {
        boolean resultValidation = true;
        if (!Utility.validateEmptyComponentTextField(this.txtTipo, this.lbErrorTipo)) {
            resultValidation = false;
        }
        return resultValidation;
    }

    @Override
    public void activarDesactivarOpciones(boolean opt) {
        this.btnActualizar.setDisable(opt);
        this.btnGuardar.setDisable(!opt);
    }

    @Override
    public void limpiarFormulario() {
        this.txtTipo.setText(null);
        this.txtId.setText(null);
        this.lbErrorTipo.setVisible(false);
        this.txtTipo.requestFocus();
    }

    @Override
    public void activarDesactivarPaneles(boolean opt) {
        this.panelCrear.setVisible(opt);
        this.panelVer.setVisible(!opt);
    }

}
