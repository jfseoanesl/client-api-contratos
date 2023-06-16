/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.controller;

import co.gov.deajvpar.gestcontractclient.fx.Exceptions.HttpResponseException;
import co.gov.deajvpar.gestcontractclient.fx.dtos.*;
import co.gov.deajvpar.gestcontractclient.fx.dtos.table.DeajTableDto;
import co.gov.deajvpar.gestcontractclient.fx.logic.GestionDeaj;
import co.gov.deajvpar.gestcontractclient.fx.utility.*;
import java.net.URL;
import java.util.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.*;
import javafx.collections.transformation.*;
import javafx.event.ActionEvent;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Jairo F
 */
public class PanelDireccionEjecutivaController implements Initializable, IFormController {

    /**
     * Initializes the controller class.
     */
    @FXML
    private VBox panelCrear, panelVer;
    @FXML
    private Button btnCrear, btnGuardarDeaj, btnEditar, btnEliminar, btnActualizar;
    @FXML
    private Label lbErrorDptos, lbErrorNombreDeaj;
    @FXML
    private ComboBox cmbDptos;
    @FXML
    private ListView listViewDptos;
    @FXML
    private TableView<DeajTableDto> tablaDeaj;
    @FXML
    private TextField txtDeaj, txtBuscar, txtId;
    @FXML
    private TableColumn<DeajTableDto, String> tableColumnDeaj, tableColumnDpto, tableColumnId;

    private ObservableList<DeajTableDto> data;
   
    private GestionDeaj logica;

    @FXML
    private void actionEventBotonCrear(ActionEvent e) {
        this.activarDesactivarPaneles(true);
        this.limpiarFormulario();
        this.activarDesactivarOpciones(true);
    }

    @FXML
    private void actionEventBotonEditar(ActionEvent e) {
        DeajTableDto selected = this.tablaDeaj.getSelectionModel().getSelectedItem();
        if (selected != null) {

            this.limpiarFormulario();
            this.activarDesactivarPaneles(true);
           this.activarDesactivarOpciones(false);
            DireccionSeccionalDto dto = this.logica.getDeaj(selected.getId());
            this.txtId.setText(dto.getIdDireccion().toString());
            this.txtDeaj.setText(dto.getDescripcionSeccional());
            this.logica.setListDptoSelected(dto.getListDptoCoordinados());
            this.setListviewDptos();

        }

    }

    @FXML
    private void actionEventBotonActualizar(ActionEvent e) {
        if (this.validarEnvioFormulario()) {
            DireccionSeccionalDto deaj = this.logica.getNewDeaj(this.txtId.getText(), this.txtDeaj.getText());
            try {
                this.logica.edit(deaj);
                MyScreen.exitMessage();
                this.initTable();

            } catch (HttpResponseException ex) {
                MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
            }

        }
    }

    @FXML
    private void actionEventBotonEliminar(ActionEvent e) {
        DeajTableDto selected = this.tablaDeaj.getSelectionModel().getSelectedItem();

        if (selected != null) {
            Optional<ButtonType> result = MyScreen.confirmMessage(null, "Confirmacion", "Esta seguro de realizar la eliminacion?");
            if (result.get() == ButtonType.OK) {
                Long id = selected.getId();
                DireccionSeccionalDto dto = new DireccionSeccionalDto(id);
                try {
                    this.logica.delete(dto);
                    MyScreen.exitMessage();

                } catch (HttpResponseException ex) {
                    MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
                }
            }
        }
    }

    @FXML
    private void actionEventBotonGuardarDeaj(ActionEvent e) {
        if (this.validarEnvioFormulario()) {
            DireccionSeccionalDto deaj = this.logica.getNewDeaj(this.txtDeaj.getText());
            try {
                this.logica.save(deaj);
                MyScreen.exitMessage();
                this.initTable();
                this.limpiarFormulario();
            } catch (HttpResponseException ex) {
                MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
            }
        }
    }

    @FXML
    private void actionEventBtnVolver(ActionEvent e) {

        this.activarDesactivarPaneles(false);
    }

    @FXML
    private void actionEventCmbDpto(ActionEvent e) {
        int index = this.cmbDptos.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            this.logica.addDptoSelected(index);
        }
        this.setListviewDptos();
    }

    @FXML
    private void clickListView(MouseEvent m) {
        int index = this.listViewDptos.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            this.logica.removeDtpoSelected(index);
            this.setListviewDptos();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        try {
            this.logica = new GestionDeaj();
            this.lbErrorDptos.setVisible(false);
            this.lbErrorNombreDeaj.setVisible(false);
            this.activarDesactivarOpciones(true);
            this.activarDesactivarPaneles(false);
            this.loadComboDptos();
            this.initTable();
            this.txtDeaj.setText(null);
            this.activarPrivilegiosModulo();

        } catch (Exception e) {
            e.printStackTrace();
            MyScreen.errorMessage(e);
        }

    }

    private void setFiltroTable() {
        FilteredList<DeajTableDto> filteredData = new FilteredList<>(this.data, p -> true);
        this.txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(dto -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (dto.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (dto.getDptos().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        SortedList<DeajTableDto> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(this.tablaDeaj.comparatorProperty());

        this.tablaDeaj.setItems(sortedData);
    }

    private void initTable() {

        this.tableColumnDeaj.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.tableColumnDpto.setCellValueFactory(new PropertyValueFactory("dptos"));
        this.tableColumnId.setCellValueFactory(new PropertyValueFactory("id"));
        
        this.data = FXCollections.observableArrayList();
        try {
            DireccionSeccionalDto[] deajs = this.logica.getAll();

            for (DireccionSeccionalDto dto : deajs) {
                DeajTableDto deajTable = new DeajTableDto(dto);
                this.data.add(deajTable);
            }
            
            if (this.logica.isDelete()) {

                this.btnEliminar.setDisable(!(data.size() > 0));

            } else {
                this.btnEliminar.setDisable(true);
            }
            if (this.logica.isView()) {
                this.setFiltroTable();
            }

        } catch (HttpResponseException ex) {
            MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
        }

    }

    private void loadComboDptos() {

        List<DptoDto> dptoList = this.logica.getDptoList();
        for (DptoDto d : dptoList) {
            this.cmbDptos.getItems().add(d.getNombreDpto());
        }

    }

    private void setListviewDptos() {

        this.listViewDptos.getItems().clear();
        for (DptoDto dto : this.logica.getListDptoSelected()) {
            this.listViewDptos.getItems().add(dto.getNombreDpto());
        }
    }

   

    

    @Override
    public void activarPrivilegiosModulo() {

        this.btnGuardarDeaj.setDisable(!this.logica.isCreate());
        this.btnCrear.setDisable(!this.logica.isCreate());

        this.btnEditar.setDisable(!this.logica.isUpdate());
        this.btnActualizar.setDisable(!this.logica.isUpdate());

        this.btnEliminar.setDisable(!this.logica.isDelete());

        this.txtBuscar.setDisable(!this.logica.isView());
        this.tablaDeaj.setDisable(!this.logica.isView());

    }

    @Override
    public boolean validarEnvioFormulario() {
        boolean resultValidation = true;
        boolean empty;

        empty = this.txtDeaj.getText() == null || this.txtDeaj.getText().isBlank();
        if (empty) {
            this.txtDeaj.requestFocus();
            resultValidation = false;
        }
        this.lbErrorNombreDeaj.setVisible(empty);

        empty = this.logica.getListDptoSelected().size() == 0;
        if (empty) {
            this.cmbDptos.requestFocus();
            resultValidation = false;
        }
        this.lbErrorDptos.setVisible(empty);

        return resultValidation;
    }

    @Override
    public void activarDesactivarOpciones(boolean opt) {
        this.btnActualizar.setDisable(opt);
        this.btnGuardarDeaj.setDisable(!opt);
    }

    @Override
    public void limpiarFormulario() {
       this.logica.getListDptoSelected().clear();
        this.setListviewDptos();

        this.txtDeaj.setText(null);
        this.cmbDptos.getSelectionModel().select(-1);

        this.lbErrorDptos.setVisible(false);
        this.lbErrorNombreDeaj.setVisible(false);

        this.txtDeaj.requestFocus();
    }

    @Override
    public void activarDesactivarPaneles(boolean opt) {
        this.panelCrear.setVisible(opt);
        this.panelVer.setVisible(!opt);
    }

}
