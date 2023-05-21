/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.controller;

import co.gov.deajvpar.gestcontractclient.fx.dtos.ModalidadDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.ModuloDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.PrivilegioDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUserDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUsuarioSingleton;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SubModalidadDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.table.ModalidadTableDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.table.SubModalidadTableDto;
import co.gov.deajvpar.gestcontractclient.fx.utility.HttpCodeResponse;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyGsonMapper;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyHttpApi;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyScreen;
import co.gov.deajvpar.gestcontractclient.fx.utility.StatusCode;
import co.gov.deajvpar.gestcontractclient.fx.utility.UsedApis;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Jairo F
 */
public class PanelModalidadContratoController implements Initializable {

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
    private Button btnEliminarModalidad, btnCrearModalidad, btnEditarModalidad, btnAddSubmodalidad;
    @FXML
    private Button btnEliminarSubModalidad, btnEditarSubModalidad, btnCrearSubModalidad;
    @FXML
    private Button btnVolverModalidad, btnActualizarModalidad, btnGuardarModalidad;
    @FXML
    private Button btnVolverSubModalidad, btnActualizarSubModalidad, btnGuardarSubModalidad;
    @FXML
    private TableColumn<ModalidadTableDto, String> columnNoSubmodalidad, columnIdModalidad;
    @FXML
    private TableColumn<ModalidadTableDto, String> columnNombreModalidad, columnDescripcionModalidad;
    @FXML
    private TableColumn<SubModalidadTableDto, String> columnIdSub, columnNombreSub, columnDescripSub;
    @FXML
    private StackPane stackPaneModalidad, stackPaneSubmodalidad;
    @FXML
    private ListView listViewSubmodalidades;
    @FXML
    private Tab tabModalidad, tabSubmodalidad;

//    @FXML
//    private TabPane tabPane;
//    
    @FXML
    private VBox panelCrearModalidad, panelListModalidad, panelCrearSubmodalidad, panelListSubmodalidad;

    private ObservableList<ModalidadTableDto> dataTableModalidad;
    private ObservableList<SubModalidadTableDto> dataTableSubModalidad;
    private ModalidadDto[] modalidadList;
    private SubModalidadDto[] subModalidadList;
    private SesionUserDto sesion;
    private boolean create, update, delete, view;
    private List<SubModalidadDto> submodalidadListOfNewModalidad;
    private List<SubModalidadDto> submodalidadListDeleteOfModalidad;

    @FXML
    public void actionEventBotonEliminarModalidad(ActionEvent e) {

        ModalidadTableDto selected = this.tablaModalidades.getSelectionModel().getSelectedItem();

        if (selected != null) {
            Long id = selected.getIdModalidad();
            this.eliminarRegistro(id, UsedApis.API_MODALIDAD_DELETE);
        }
    }

    @FXML
    public void actionEventBotonEditarModalidad(ActionEvent e) {

        ModalidadTableDto selected = this.tablaModalidades.getSelectionModel().getSelectedItem();
        if (selected != null) {

            this.limpiarPanelCrear();
            this.activarDesactivarPaneles(true);
            this.selectCrearOrActualizar(false);
            ModalidadDto dto = this.buscarModalidadIntoArray(selected.getIdModalidad());
            this.txtIdModalidad.setText(dto.getId().toString());
            this.txtNombreModalidad.setText(dto.getNombreModalidad());
            this.txtDescripcionModalidad.setText(dto.getDescripcionModalidad());
            this.submodalidadListOfNewModalidad = dto.getListSubModalidades();
            this.submodalidadListDeleteOfModalidad = new ArrayList();
            this.setListviewSubModalidad();
        }
    }

    @FXML
    public void actionEventBotonCrearModalidad(ActionEvent e) {
        this.activarDesactivarPaneles(true);
        this.selectCrearOrActualizar(true);
        this.submodalidadListOfNewModalidad = new ArrayList();
    }

    @FXML
    public void actionEventBotonAddSubmodalidad(ActionEvent e) {

        boolean validate = this.validateComponentTextField(this.txtDescripcionSubmodalidad, this.lbErrorNombreSubmodalidad);
        if (validate) {
            String submodalidad = this.txtDescripcionSubmodalidad.getText();
            SubModalidadDto sub = new SubModalidadDto(submodalidad, submodalidad, this.sesion.getUser());
            this.submodalidadListOfNewModalidad.add(sub);
            this.setListviewSubModalidad();
            this.txtDescripcionSubmodalidad.setText(null);
            this.txtDescripcionSubmodalidad.requestFocus();
        }
    }

    @FXML
    public void EventoClickListSubModalidades(MouseEvent e) {
        int index = this.listViewSubmodalidades.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            if (this.submodalidadListOfNewModalidad.get(index).getId() != null) {
                SubModalidadDto selected = this.submodalidadListOfNewModalidad.get(index);
                selected.setEliminado(true);
                this.submodalidadListDeleteOfModalidad.add(selected);
            }
            this.submodalidadListOfNewModalidad.remove(index);
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
            Long id = Long.valueOf(this.txtIdModalidad.getText());
            modalidad.setId(id);
            this.submodalidadListOfNewModalidad.addAll(this.submodalidadListDeleteOfModalidad);
            modalidad.setListSubModalidades(this.submodalidadListOfNewModalidad);
            this.actualizarModalidad(modalidad);
            this.limpiarPanelCrear();
        }

    }

    @FXML
    public void actionEventBotonGuardarModalidad(ActionEvent e) {

        boolean validate = this.validarEnvioGuardarModalidad();
        if (validate) {

            ModalidadDto modalidad = this.leerModalidadOfForm();
            modalidad.setListSubModalidades(this.submodalidadListOfNewModalidad);
            try {
                MyHttpApi.jsonPostRequest(UsedApis.API_MODALIDAD_SAVE, modalidad);
                String response = MyHttpApi.stringResponse();
                StatusCode status = HttpCodeResponse.get(MyHttpApi.responseStatusCode());
                if (MyHttpApi.statusOk()) {
                    MyScreen.showMessage(null, status.getStatus(), "Solicitud ejecutada con exito. La modalidad de contrato ha sido registrada");
                } else {
                    MyScreen.errorMessage(status.toString(), response);
                }
            } catch (UnirestException ex) {
                MyScreen.errorMessage(ex);
            }
            this.limpiarPanelCrear();
        }

    }

    @FXML
    public void actionEventBtnVolverSubModalidad(ActionEvent e) {
        this.activarDesactivarPaneles(false);
    }

    @FXML
    public void actionEventBotonActualizarSubModalidad(ActionEvent e) {

        boolean validate = this.validarEnvioActualizarSubModalidad();
        if (validate) {

            SubModalidadDto sub = this.leerSubModalidadOfForm();
            Long id = Long.valueOf(this.txtIdSubmodalidad.getText());
            sub.setId(id);
            this.actualizarSubModalidad(sub);
            this.limpiarPanelCrear();

        }
    }

    @FXML
    public void actionEventBotonGuardarSubModalidad(ActionEvent e) {

        boolean validate = this.validarEnvioGuardarSubModalidad();
        if (validate) {

            SubModalidadDto sub = this.leerSubModalidadOfForm();
            int index = this.cmbModalidadFichaSub.getSelectionModel().getSelectedIndex();
            ModalidadDto modalidad = this.modalidadList[index];
            modalidad.getListSubModalidades().add(sub);
            this.actualizarModalidad(modalidad);
            this.limpiarPanelCrear();

        }

    }

    @FXML
    public void actionEventBotonEliminarSubModalidad(ActionEvent e) {
        SubModalidadTableDto selected = this.tablaSubmodalidades.getSelectionModel().getSelectedItem();

        if (selected != null) {
            Long id = selected.getId();
            this.eliminarRegistro(id, UsedApis.API_SUBMODALIDAD_DELETE);
        }
    }

    @FXML
    public void actionEventBotonCrearSubModalidad(ActionEvent e) {
        this.activarDesactivarPaneles(true);
        this.selectCrearOrActualizar(true);
        this.cmbModalidadFichaSub.setDisable(false);
        this.loadComboModalidadSub();
    }

    @FXML
    public void actionEventBotonEditarSubModalidad(ActionEvent e) {

        SubModalidadTableDto selected = this.tablaSubmodalidades.getSelectionModel().getSelectedItem();
        if (selected != null) {

            this.limpiarPanelCrear();
            this.activarDesactivarPaneles(true);
            this.selectCrearOrActualizar(false);
            this.cmbModalidadFichaSub.setDisable(true);

            SubModalidadDto dto = this.buscarSubModalidadIntoArray(selected.getId());
            this.txtIdSubmodalidad.setText(dto.getId().toString());
            this.txtNombreSubModalidaFichaSub.setText(dto.getNombreSubModalidad());
            this.txtDescripcionSubModalidaFichaSub.setText(dto.getDescripcionSubModalidad());

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        this.sesion = SesionUsuarioSingleton.get();
        this.setPrivilegiosModulo();

        this.activarDesactivarPaneles(false);

        this.loadDataTables();
        this.activarPrivilegiosModulo();

    }

    private void selectCrearOrActualizar(boolean opt) {
        // true - > crear     false->actualizar
        if (this.tabModalidad.isSelected()) {
            this.btnActualizarModalidad.setDisable(opt);
            this.btnGuardarModalidad.setDisable(!opt);
        } else {
            this.btnActualizarSubModalidad.setDisable(opt);
            this.btnGuardarSubModalidad.setDisable(!opt);
        }

    }

    private void actualizarModalidad(ModalidadDto dto) {
        try {
            MyHttpApi.jsonPostRequest(UsedApis.API_MODALIDAD_EDIT, dto);
            String response = MyHttpApi.stringResponse();
            StatusCode status = HttpCodeResponse.get(MyHttpApi.responseStatusCode());
            if (MyHttpApi.statusOk()) {
                MyScreen.showMessage(null, status.getStatus(), "Solicitud ejecutada con exito. La modalidad de contrato ha sido actualizada");
            } else {
                MyScreen.errorMessage(status.toString(), response);
            }
        } catch (UnirestException ex) {
            MyScreen.errorMessage(ex);
        }
    }

    private void actualizarSubModalidad(SubModalidadDto dto) {
        try {
            MyHttpApi.jsonPostRequest(UsedApis.API_SUBMODALIDAD_EDIT, dto);
            String response = MyHttpApi.stringResponse();
            StatusCode status = HttpCodeResponse.get(MyHttpApi.responseStatusCode());
            if (MyHttpApi.statusOk()) {
                MyScreen.showMessage(null, status.getStatus(), "Solicitud ejecutada con exito. La submodalidad de contrato ha sido actualizada");
            } else {
                MyScreen.errorMessage(status.toString(), response);
            }
        } catch (UnirestException ex) {
            MyScreen.errorMessage(ex);
        }
    }

    public ModalidadDto buscarModalidadIntoArray(Long id) {
        for (ModalidadDto m : this.modalidadList) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        return null;
    }

    public SubModalidadDto buscarSubModalidadIntoArray(Long id) {
        for (SubModalidadDto s : this.subModalidadList) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    private void limpiarPanelCrear() {
        if (this.tabModalidad.isSelected()) {
            this.txtNombreModalidad.setText(null);
            this.txtDescripcionModalidad.setText(null);
            this.txtDescripcionSubmodalidad.setText(null);
            if (this.submodalidadListOfNewModalidad != null) {
                this.submodalidadListOfNewModalidad.clear();
            }
            if (this.submodalidadListDeleteOfModalidad != null) {
                this.submodalidadListDeleteOfModalidad.clear();
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

    private void setListviewSubModalidad() {

        this.listViewSubmodalidades.getItems().clear();
        if (this.submodalidadListOfNewModalidad != null) {
            for (SubModalidadDto dto : this.submodalidadListOfNewModalidad) {
                this.listViewSubmodalidades.getItems().add(dto.getNombreSubModalidad());
            }
        }
    }

    public boolean validarEnvioGuardarModalidad() {
        boolean resultValidation = true;
        boolean validate = this.validateComponentTextArea(this.txtDescripcionModalidad, this.lbErrorDescripcionModalidad);
        if (!validate) {
            resultValidation = false;
        }
        validate = this.validateComponentTextField(this.txtNombreModalidad, this.lbErrorNombreModalidad);
        if (!validate) {
            resultValidation = false;
        }
        return resultValidation;
    }

    public boolean validarEnvioGuardarSubModalidad() {
        boolean resultValidation = true;

        boolean validate = this.validateComponentTextArea(this.txtDescripcionSubModalidaFichaSub, this.lbErrorDescripcionSubModalidadFichaSub);
        if (!validate) {
            resultValidation = false;
        }
        validate = this.validateComponentTextField(this.txtNombreSubModalidaFichaSub, this.lbErrorNombreSubModalidadFichaSub);
        if (!validate) {
            resultValidation = false;
        }
        validate = this.validateComponentCombo(this.cmbModalidadFichaSub, this.lbErrorModalidadFichaSub);
        if (!validate) {
            resultValidation = false;
        }
        return resultValidation;
    }

    public boolean validarEnvioActualizarSubModalidad() {
        boolean resultValidation = true;

        boolean validate = this.validateComponentTextArea(this.txtDescripcionSubModalidaFichaSub, this.lbErrorDescripcionSubModalidadFichaSub);
        if (!validate) {
            resultValidation = false;
        }
        validate = this.validateComponentTextField(this.txtNombreSubModalidaFichaSub, this.lbErrorNombreSubModalidadFichaSub);
        if (!validate) {
            resultValidation = false;
        }
        return resultValidation;
    }

    public boolean validateComponentTextField(TextField text, Label error) {

        boolean empty = text.getText() == null || text.getText().isBlank();

        if (empty) {
            text.requestFocus();
        }
        error.setVisible(empty);
        return !empty;

    }

    public boolean validateComponentTextArea(TextArea text, Label error) {

        boolean empty = text.getText() == null || text.getText().isBlank();
        if (empty) {
            text.requestFocus();
        }
        error.setVisible(empty);
        return !empty;

    }

    public boolean validateComponentCombo(ComboBox list, Label error) {

        int index = list.getSelectionModel().getSelectedIndex();
        boolean empty = list.getSelectionModel().getSelectedIndex() == -1;
        if (empty) {
            list.requestFocus();
        }
        error.setVisible(empty);
        return !empty;

    }

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
            MyHttpApi.jsonGetRequest(UsedApis.API_MODALIDAD_GET_ALL);
            String response = MyHttpApi.stringResponse();
            StatusCode status = HttpCodeResponse.get(MyHttpApi.responseStatusCode());
            if (MyHttpApi.statusOk()) {
                this.modalidadList = MyGsonMapper.get().fromJson(response, ModalidadDto[].class);
                for (ModalidadDto dto : this.modalidadList) {
                    ModalidadTableDto dtoTable = new ModalidadTableDto(dto);
                    this.dataTableModalidad.add(dtoTable);
                }
//                this.tablaModalidades.setItems(this.dataTableModalidad);
                if (this.delete) {

                    this.btnEliminarModalidad.setDisable(!(this.dataTableModalidad.size() > 0));

                } else {
                    this.btnEliminarModalidad.setDisable(true);
                }
                if (this.view) {
                    this.setFiltroTableModalidades();
                }
            } else {
                MyScreen.errorMessage(status.toString(), response);
            }
        } catch (UnirestException ex) {
            MyScreen.errorMessage(ex);
        }

    }

    private void setPrivilegiosModulo() {

        this.create = this.sesion.checkPrivilegioModulo(ModuloDto.CONFIGURACION, PrivilegioDto.CREAR);
        this.update = this.sesion.checkPrivilegioModulo(ModuloDto.CONFIGURACION, PrivilegioDto.ACTUALIZAR);
        this.view = this.sesion.checkPrivilegioModulo(ModuloDto.CONFIGURACION, PrivilegioDto.CONSULTAR);
        this.delete = this.sesion.checkPrivilegioModulo(ModuloDto.CONFIGURACION, PrivilegioDto.ELIMINAR);

    }

    public void activarPrivilegiosModulo() {

        this.btnGuardarModalidad.setDisable(!this.create);
        this.btnGuardarSubModalidad.setDisable(!this.create);
        this.btnCrearModalidad.setDisable(!this.create);
        this.btnCrearSubModalidad.setDisable(!this.create);

        this.btnEditarModalidad.setDisable(!this.update);
        this.btnEditarSubModalidad.setDisable(!this.update);
        this.btnActualizarModalidad.setDisable(!this.update);
        this.btnActualizarSubModalidad.setDisable(!this.update);

        this.btnEliminarModalidad.setDisable(!this.delete);
        this.btnEliminarSubModalidad.setDisable(!this.delete);

        this.txtBuscarModalidad.setDisable(!this.view);
        this.txtBuscarSubmodalidad.setDisable(!this.view);
        this.tablaModalidades.setDisable(!this.view);
        this.tablaSubmodalidades.setDisable(!this.view);

    }

    public void loadDataTables() {

        this.loadDatatTableModalidades();;
        this.loadDataTableSubmodaldiades();

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
            MyHttpApi.jsonGetRequest(UsedApis.API_SUBMODALIDAD_GET_ALL);
            String response = MyHttpApi.stringResponse();
            StatusCode status = HttpCodeResponse.get(MyHttpApi.responseStatusCode());
            if (MyHttpApi.statusOk()) {
                this.subModalidadList = MyGsonMapper.get().fromJson(response, SubModalidadDto[].class);
                for (SubModalidadDto dto : this.subModalidadList) {
                    SubModalidadTableDto dtoTable = new SubModalidadTableDto(dto);
                    this.dataTableSubModalidad.add(dtoTable);
                }
//                this.tablaSubmodalidades.setItems(this.dataTableSubModalidad);
                if (this.delete) {

                    this.btnEliminarSubModalidad.setDisable(!(this.dataTableSubModalidad.size() > 0));

                } else {
                    this.btnEliminarSubModalidad.setDisable(true);
                }
                if (this.view) {
                    this.setFiltroTableSubModalidades();
                }
            } else {
                MyScreen.errorMessage(status.toString(), response);
            }
        } catch (UnirestException ex) {
            MyScreen.errorMessage(ex);
        }
    }

    private void loadComboModalidadSub() {

        this.cmbModalidadFichaSub.getItems().clear();
        for (ModalidadDto m : this.modalidadList) {
            this.cmbModalidadFichaSub.getItems().add(m.getNombreModalidad());
        }
    }

    public ModalidadDto leerModalidadOfForm() {
        String nombre = this.txtNombreModalidad.getText();
        String descripcion = this.txtDescripcionModalidad.getText();
        ModalidadDto modalidad = new ModalidadDto(descripcion, nombre, this.sesion.getUser());

        return modalidad;
    }

    public SubModalidadDto leerSubModalidadOfForm() {
        String nombre = this.txtNombreSubModalidaFichaSub.getText();
        String descripcion = this.txtDescripcionSubModalidaFichaSub.getText();
        SubModalidadDto sub = new SubModalidadDto(descripcion, nombre, this.sesion.getUser());
        return sub;
    }

    public void eliminarRegistro(Long id, String api) {

        Optional<ButtonType> result = MyScreen.confirmMessage(null, "Confirmacion", "Esta seguro de realizar la eliminacion?");
        if (result.get() == ButtonType.OK) {

            try {
                MyHttpApi.jsonPostRequest(api, id);
                String response = MyHttpApi.stringResponse();
                StatusCode status = HttpCodeResponse.get(MyHttpApi.responseStatusCode());
                if (MyHttpApi.statusOk()) {
                    this.loadDataTables();
                    MyScreen.showMessage(null, status.getStatus(), "Solicitud ejecutada con exito. Registro eliminado correctamente");

                } else {
                    MyScreen.errorMessage(status.toString(), response);
                }
            } catch (UnirestException ex) {
                MyScreen.errorMessage(ex);
            }
        }

    }

}
