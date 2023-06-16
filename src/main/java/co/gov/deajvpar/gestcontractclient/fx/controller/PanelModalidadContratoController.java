/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.controller;

import co.gov.deajvpar.gestcontractclient.fx.Exceptions.HttpResponseException;
import co.gov.deajvpar.gestcontractclient.fx.dtos.*;
import co.gov.deajvpar.gestcontractclient.fx.dtos.table.*;
import co.gov.deajvpar.gestcontractclient.fx.logic.GestionModalidad;
import co.gov.deajvpar.gestcontractclient.fx.logic.GestionSubModalidad;
import co.gov.deajvpar.gestcontractclient.fx.utility.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.collections.transformation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Jairo F
 */
public class PanelModalidadContratoController implements Initializable, IFormController {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtBuscarModalidad, txtBuscarSubmodalidad, txtNombreModalidad, txtDescripcionSubmodalidad;
    @FXML
    private TextField txtNombreSubModalidaFichaSub, txtIdModalidad, txtIdSubmodalidad;
    @FXML
    private ComboBox cmbModalidadFichaSub;
    @FXML
    private TextArea txtDescripcionModalidad, txtDescripcionSubModalidaFichaSub;
    @FXML
    private Label lbErrorNombreModalidad, lbErrorDescripcionModalidad, lbErrorNombreSubmodalidad, lbErrorNombreSubModalidadFichaSub;
    @FXML
    private Label lbErrorModalidadFichaSub, lbErrorDescripcionSubModalidadFichaSub;
    @FXML
    private TableView<ModalidadTableDto> tablaModalidades;
    @FXML
    private TableView<SubModalidadTableDto> tablaSubmodalidades;
    @FXML
    private Button btnEliminarModalidad, btnCrearModalidad, btnEditarModalidad;
    @FXML
    private Button btnEliminarSubModalidad, btnEditarSubModalidad, btnCrearSubModalidad;
    @FXML
    private Button  btnActualizarModalidad, btnGuardarModalidad;
    @FXML
    private Button  btnActualizarSubModalidad, btnGuardarSubModalidad;
    @FXML
    private TableColumn<ModalidadTableDto, String> columnNoSubmodalidad, columnIdModalidad;
    @FXML
    private TableColumn<ModalidadTableDto, String> columnNombreModalidad, columnDescripcionModalidad;
    @FXML
    private TableColumn<SubModalidadTableDto, String> columnIdSub, columnNombreSub, columnDescripSub;
    @FXML
    private ListView listViewSubmodalidades;
    @FXML
    private Tab tabModalidad;
    @FXML
    private VBox panelCrearModalidad, panelListModalidad, panelCrearSubmodalidad, panelListSubmodalidad;

    private ObservableList<ModalidadTableDto> dataTableModalidad;
    private ObservableList<SubModalidadTableDto> dataTableSubModalidad;
    
    private final GestionModalidad logicModalidad=new GestionModalidad();
    private GestionSubModalidad logicSubModalidad;
    private int validarFormType;
    
    
    @FXML
    public void actionEventBotonEliminarModalidad(ActionEvent e) {

        ModalidadTableDto selected = this.tablaModalidades.getSelectionModel().getSelectedItem();

        if (selected != null) {
            Long id = selected.getIdModalidad();
            this.eliminarRegistro(id, true);
        }
    }

    @FXML
    public void actionEventBotonEditarModalidad(ActionEvent e) {

        ModalidadTableDto selected = this.tablaModalidades.getSelectionModel().getSelectedItem();
        if (selected != null) {

            this.limpiarFormulario();
            this.activarDesactivarPaneles(true);
            this.activarDesactivarOpciones(false);
            ModalidadDto dto = this.logicModalidad.getModalidadFromList(selected.getIdModalidad());
            this.cargarDatosModalidad(dto);
        }
    }

    @FXML
    public void actionEventBotonCrearModalidad(ActionEvent e) {
        this.activarDesactivarPaneles(true);
        this.activarDesactivarOpciones(true);
        this.logicModalidad.setNewSubmodalidadList(new ArrayList());
    }

    @FXML
    public void actionEventBotonAddSubmodalidad(ActionEvent e) {

        boolean validate = Utility.validateEmptyComponentTextField(this.txtDescripcionSubmodalidad, this.lbErrorNombreSubmodalidad);
        if (validate) {
            String submodalidad = this.txtDescripcionSubmodalidad.getText();
            SubModalidadDto sub = new SubModalidadDto(submodalidad, submodalidad, this.logicModalidad.getSesionUser());
            this.logicModalidad.getNewSubmodalidadList().add(sub);
            this.setListviewSubModalidad();
            this.txtDescripcionSubmodalidad.setText(null);
            this.txtDescripcionSubmodalidad.requestFocus();
        }
    }

    @FXML
    public void EventoClickListSubModalidades(MouseEvent e) {
        int index = this.listViewSubmodalidades.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            if (this.logicModalidad.getNewSubmodalidadList().get(index).getId() != null) {
                SubModalidadDto selected = this.logicModalidad.getNewSubmodalidadList().get(index);
                selected.setEliminado(true);
                this.logicModalidad.getSubmodalidadListForDelete().add(selected);
            }
            this.logicModalidad.deleteFromListSubmodalidad(this.logicModalidad.getNewSubmodalidadList(), index);
            this.setListviewSubModalidad();
        }
    }

    @FXML
    public void actionEventBtnVolverModalidad(ActionEvent e) {
        this.activarDesactivarPaneles(false);
    }

    @FXML
    public void actionEventBotonActualizarModalidad(ActionEvent e) {

        boolean validate = this.validarEnvioGuardarModalidad();
        if (validate) {
            ModalidadDto modalidad = this.leerModalidadOfForm();
            this.actualizarModalidad(modalidad);
        }

    }

    @FXML
    public void actionEventBotonGuardarModalidad(ActionEvent e) {

        this.validarFormType = 2;
        boolean validate = this.validarEnvioFormulario();
        if (validate) {

            ModalidadDto modalidad = this.leerModalidadOfForm();
            this.guardarModalidad(modalidad);
        }

    }

    @FXML
    public void actionEventBtnVolverSubModalidad(ActionEvent e) {
        this.activarDesactivarPaneles(false);
    }

    @FXML
    public void actionEventBotonActualizarSubModalidad(ActionEvent e) {

        this.validarFormType = 1;
        boolean validate = this.validarEnvioFormulario();
        if (validate) {

            SubModalidadDto sub = this.leerSubModalidadOfForm();
            this.actualizarSubModalidad(sub);

        }
    }

    @FXML
    public void actionEventBotonGuardarSubModalidad(ActionEvent e) {

        this.validarFormType = 3;
        boolean validate = this.validarEnvioFormulario();
        if (validate) {

            SubModalidadDto sub = this.leerSubModalidadOfForm();
            int index = this.cmbModalidadFichaSub.getSelectionModel().getSelectedIndex();
            ModalidadDto modalidad = this.logicModalidad.getModalidadList()[index];
            modalidad.getListSubModalidades().add(sub);
            this.actualizarModalidad(modalidad);

        }

    }

    @FXML
    public void actionEventBotonEliminarSubModalidad(ActionEvent e) {
        SubModalidadTableDto selected = this.tablaSubmodalidades.getSelectionModel().getSelectedItem();

        if (selected != null) {
            Long id = selected.getId();
            this.eliminarRegistro(id, false);
        }
    }

    @FXML
    public void actionEventBotonCrearSubModalidad(ActionEvent e) {
        this.activarDesactivarPaneles(true);
        this.activarDesactivarOpciones(true);
        this.cmbModalidadFichaSub.setDisable(false);
        this.loadComboModalidadSub();
    }

    @FXML
    public void actionEventBotonEditarSubModalidad(ActionEvent e) {

        SubModalidadTableDto selected = this.tablaSubmodalidades.getSelectionModel().getSelectedItem();
        if (selected != null) {

            this.limpiarFormulario();
            this.activarDesactivarPaneles(true);
            this.activarDesactivarOpciones(false);
            this.cmbModalidadFichaSub.setDisable(true);

            SubModalidadDto dto = this.logicModalidad.getSubModalidadFromArray(selected.getId());
            this.txtIdSubmodalidad.setText(dto.getId().toString());
            this.txtNombreSubModalidaFichaSub.setText(dto.getNombreSubModalidad());
            this.txtDescripcionSubModalidaFichaSub.setText(dto.getDescripcionSubModalidad());

        }
    }
    
    @FXML
    public void eventTabModalidad(){
          this.loadDataTables();
    }
    @FXML
    public void eventTabSubModalidad(){
          this.loadDataTables();  
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

//        this.logicModalidad = new GestionModalidad();
        this.logicSubModalidad = new GestionSubModalidad();

        this.activarDesactivarPaneles(false);
        this.loadDataTables();
        this.activarPrivilegiosModulo();

    }

    private void actualizarModalidad(ModalidadDto dto) {

        try {
            this.logicModalidad.edit(dto);
            MyScreen.exitMessage();
            this.loadDatatTableModalidades();
            this.limpiarFormulario();
        } catch (HttpResponseException ex) {
            MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
        }
    }

    private void guardarModalidad(ModalidadDto dto) {

        try {
            this.logicModalidad.save(dto);
            MyScreen.exitMessage();
            this.limpiarFormulario();
        } catch (HttpResponseException ex) {
            MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
        }
    }

    private void actualizarSubModalidad(SubModalidadDto dto) {

        try {
            this.logicSubModalidad.save(dto);
            MyScreen.exitMessage();
            this.limpiarFormulario();
        } catch (HttpResponseException ex) {
            MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
        }
    }

    private void setListviewSubModalidad() {

        this.listViewSubmodalidades.getItems().clear();
        if (this.logicModalidad.getNewSubmodalidadList() != null) {
            for (SubModalidadDto dto : this.logicModalidad.getNewSubmodalidadList()) {
                this.listViewSubmodalidades.getItems().add(dto.getNombreSubModalidad());
            }
        }
    }

    public boolean validarEnvioGuardarModalidad() {
        boolean resultValidation = true;
        boolean validate = Utility.validateEmptyComponentTextArea(this.txtDescripcionModalidad, this.lbErrorDescripcionModalidad);
        if (!validate) {
            resultValidation = false;
        }
        validate = Utility.validateEmptyComponentTextField(this.txtNombreModalidad, this.lbErrorNombreModalidad);
        if (!validate) {
            resultValidation = false;
        }
        return resultValidation;
    }

    public boolean validarEnvioGuardarSubModalidad() {
        boolean resultValidation = true;

        boolean validate = Utility.validateEmptyComponentTextArea(this.txtDescripcionSubModalidaFichaSub, this.lbErrorDescripcionSubModalidadFichaSub);
        if (!validate) {
            resultValidation = false;
        }
        validate = Utility.validateEmptyComponentTextField(this.txtNombreSubModalidaFichaSub, this.lbErrorNombreSubModalidadFichaSub);
        if (!validate) {
            resultValidation = false;
        }
        validate = Utility.validateEmptyComponentCombo(this.cmbModalidadFichaSub, this.lbErrorModalidadFichaSub);
        if (!validate) {
            resultValidation = false;
        }
        return resultValidation;
    }

    public boolean validarEnvioActualizarSubModalidad() {
        boolean resultValidation = true;

        boolean validate = Utility.validateEmptyComponentTextArea(this.txtDescripcionSubModalidaFichaSub, this.lbErrorDescripcionSubModalidadFichaSub);
        if (!validate) {
            resultValidation = false;
        }
        validate = Utility.validateEmptyComponentTextField(this.txtNombreSubModalidaFichaSub, this.lbErrorNombreSubModalidadFichaSub);
        if (!validate) {
            resultValidation = false;
        }
        return resultValidation;
    }

    @Override
    public void activarDesactivarPaneles(boolean select) {

        if (this.tabModalidad.isSelected()) {
            this.panelListModalidad.setVisible(!select);
            this.panelCrearModalidad.setVisible(select);
        } else {
            this.panelListSubmodalidad.setVisible(!select);
            this.panelCrearSubmodalidad.setVisible(select);
        }
    }

    private void loadDatatTableModalidades() {

        this.columnIdModalidad.setCellValueFactory(new PropertyValueFactory("idModalidad"));
        this.columnNombreModalidad.setCellValueFactory(new PropertyValueFactory("nombreModalidad"));
        this.columnDescripcionModalidad.setCellValueFactory(new PropertyValueFactory("descripcionModalidad"));
        this.columnNoSubmodalidad.setCellValueFactory(new PropertyValueFactory("submodalidades"));

        this.dataTableModalidad = FXCollections.observableArrayList();

        try {

            ModalidadDto[] modalidades = this.logicModalidad.getAll();

            for (ModalidadDto dto : modalidades) {
                ModalidadTableDto dtoTable = new ModalidadTableDto(dto);
                this.dataTableModalidad.add(dtoTable);
            }
            if (this.logicModalidad.isDelete()) {

                this.btnEliminarModalidad.setDisable(!(this.dataTableModalidad.size() > 0));

            } else {
                this.btnEliminarModalidad.setDisable(true);
            }
            if (this.logicModalidad.isView()) {
                this.setFiltroTableModalidades();
            }

        } catch (HttpResponseException ex) {
            MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
        }

    }

    @Override
    public void activarPrivilegiosModulo() {

        boolean create = this.logicModalidad.isCreate();
        boolean update = this.logicModalidad.isUpdate();
        boolean delete = this.logicModalidad.isDelete();
        boolean view = this.logicModalidad.isView();

        this.btnGuardarModalidad.setDisable(!create);
        this.btnGuardarSubModalidad.setDisable(!create);
        this.btnCrearModalidad.setDisable(!create);
        this.btnCrearSubModalidad.setDisable(!create);

        this.btnEditarModalidad.setDisable(!update);
        this.btnEditarSubModalidad.setDisable(!update);
        this.btnActualizarModalidad.setDisable(!update);
        this.btnActualizarSubModalidad.setDisable(!update);

        this.btnEliminarModalidad.setDisable(!delete);
        this.btnEliminarSubModalidad.setDisable(!delete);

        this.txtBuscarModalidad.setDisable(!view);
        this.txtBuscarSubmodalidad.setDisable(!view);
        this.tablaModalidades.setDisable(!view);
        this.tablaSubmodalidades.setDisable(!view);

    }

    public void loadDataTables() {
        if (this.tabModalidad.isSelected()) {
            this.loadDatatTableModalidades();
        } else {
            this.loadDataTableSubmodaldiades();
        }
    }

    private void setFiltroTableSubModalidades() {
        FilteredList<SubModalidadTableDto> filteredData = new FilteredList<>(this.dataTableSubModalidad, p -> true);
        this.txtBuscarSubmodalidad.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(dto -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (dto.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (dto.getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        SortedList<SubModalidadTableDto> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(this.tablaSubmodalidades.comparatorProperty());

        this.tablaSubmodalidades.setItems(sortedData);
    }

    private void setFiltroTableModalidades() {
        FilteredList<ModalidadTableDto> filteredData = new FilteredList<>(this.dataTableModalidad, p -> true);
        this.txtBuscarModalidad.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(dto -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (dto.getNombreModalidad().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (dto.getDescripcionModalidad().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        SortedList<ModalidadTableDto> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(this.tablaModalidades.comparatorProperty());

        this.tablaModalidades.setItems(sortedData);
    }

    private void loadDataTableSubmodaldiades() {

        this.columnIdSub.setCellValueFactory(new PropertyValueFactory("id"));
        this.columnNombreSub.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.columnDescripSub.setCellValueFactory(new PropertyValueFactory("descripcion"));

        this.dataTableSubModalidad = FXCollections.observableArrayList();
        try {
            this.logicModalidad.setSubModalidadList(this.logicSubModalidad.getAll());
            for (SubModalidadDto dto : this.logicModalidad.getSubModalidadList()) {
                SubModalidadTableDto dtoTable = new SubModalidadTableDto(dto);
                this.dataTableSubModalidad.add(dtoTable);
            }
            if (this.logicModalidad.isDelete()) {

                this.btnEliminarSubModalidad.setDisable(!(this.dataTableSubModalidad.size() > 0));

            } else {
                this.btnEliminarSubModalidad.setDisable(true);
            }
            if (this.logicModalidad.isView()) {
                this.setFiltroTableSubModalidades();
            }

        } catch (HttpResponseException ex) {
            MyScreen.errorMessage(ex);
        }
    }

    private void loadComboModalidadSub() {

        this.cmbModalidadFichaSub.getItems().clear();
        for (ModalidadDto m : this.logicModalidad.getModalidadList()) {
            this.cmbModalidadFichaSub.getItems().add(m.getNombreModalidad());
        }
    }

    public ModalidadDto leerModalidadOfForm() {
        String nombre = this.txtNombreModalidad.getText();
        String descripcion = this.txtDescripcionModalidad.getText();
        ModalidadDto modalidad = new ModalidadDto(descripcion, nombre, this.logicModalidad.getSesionUser());
        if (Utility.validateEmptyComponentTextField(this.txtIdModalidad)) {
            Long id = Long.valueOf(this.txtIdModalidad.getText());
            modalidad.setId(id);
        }
        this.logicModalidad.getNewSubmodalidadList().addAll(this.logicModalidad.getSubmodalidadListForDelete());
        modalidad.setListSubModalidades(this.logicModalidad.getNewSubmodalidadList());
        return modalidad;
    }

    public SubModalidadDto leerSubModalidadOfForm() {
        String nombre = this.txtNombreSubModalidaFichaSub.getText();
        String descripcion = this.txtDescripcionSubModalidaFichaSub.getText();
        SubModalidadDto sub = new SubModalidadDto(descripcion, nombre, this.logicModalidad.getSesionUser());
        if (Utility.validateEmptyComponentTextField(this.txtIdSubmodalidad)) {
            sub.setId(Long.valueOf(this.txtIdSubmodalidad.getText()));
        }
        return sub;
    }

    public void eliminarRegistro(Long id, boolean opc) {

        Optional<ButtonType> result = MyScreen.confirmMessage(null, "Confirmacion", "Esta seguro de realizar la eliminacion?");
        if (result.get() == ButtonType.OK) {

            try {
                if (opc) {
                    ModalidadDto dto = new ModalidadDto();
                    dto.setId(id);
                    this.logicModalidad.delete(dto);
                } else {
                    SubModalidadDto dto = new SubModalidadDto();
                    dto.setId(id);
                    this.logicSubModalidad.delete(dto);
                }
                this.loadDataTables();
                MyScreen.exitMessage();

            } catch (HttpResponseException ex) {
                MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
            }
        }

    }

    @Override
    public boolean validarEnvioFormulario() {
        switch (this.validarFormType) {
            case 1:
                return this.validarEnvioActualizarSubModalidad();
            case 2:
                return this.validarEnvioGuardarModalidad();
            default:
                return this.validarEnvioGuardarSubModalidad();
        }
    }
    
    @Override
    public void activarDesactivarOpciones(boolean opt) {
        if (this.tabModalidad.isSelected()) {
            this.btnActualizarModalidad.setDisable(opt);
            this.btnGuardarModalidad.setDisable(!opt);
        } else {
            this.btnActualizarSubModalidad.setDisable(opt);
            this.btnGuardarSubModalidad.setDisable(!opt);
        }
    }

    @Override
    public void limpiarFormulario() {
       
        if (this.tabModalidad.isSelected()) {
            this.txtNombreModalidad.setText(null);
            this.txtDescripcionModalidad.setText(null);
            this.txtDescripcionSubmodalidad.setText(null);
           
            if (this.logicModalidad.getNewSubmodalidadList() != null) {
                this.logicModalidad.getNewSubmodalidadList().clear();
            }
            if (this.logicModalidad.getSubmodalidadListForDelete() != null) {
                this.logicModalidad.getSubmodalidadListForDelete().clear();
            }
            this.listViewSubmodalidades.getItems().clear();
            this.lbErrorDescripcionModalidad.setVisible(false);
            this.lbErrorNombreModalidad.setVisible(false);
            this.lbErrorNombreSubmodalidad.setVisible(false);
            this.txtNombreModalidad.requestFocus();
        } else {

            this.txtNombreSubModalidaFichaSub.setText(null);
            this.txtDescripcionSubModalidaFichaSub.setText(null);
            this.cmbModalidadFichaSub.getSelectionModel().select(-1);
            this.txtNombreSubModalidaFichaSub.requestFocus();
            this.lbErrorDescripcionSubModalidadFichaSub.setVisible(false);
            this.lbErrorNombreSubModalidadFichaSub.setVisible(false);
            this.lbErrorModalidadFichaSub.setVisible(false);

        }

        this.loadDataTables();
    }

    public void cargarDatosModalidad(ModalidadDto dto) {
        this.txtIdModalidad.setText(dto.getId().toString());
        this.txtNombreModalidad.setText(dto.getNombreModalidad());
        this.txtDescripcionModalidad.setText(dto.getDescripcionModalidad());
        this.logicModalidad.setNewSubmodalidadList(dto.getListSubModalidades());
        this.logicModalidad.setSubmodalidadListForDelete(new ArrayList());
        this.setListviewSubModalidad();
    }

}
