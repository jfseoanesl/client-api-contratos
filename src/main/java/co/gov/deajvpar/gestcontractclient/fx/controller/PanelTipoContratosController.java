/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.controller;

import co.gov.deajvpar.gestcontractclient.fx.dtos.ModuloDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.PrivilegioDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUserDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUsuarioSingleton;
import co.gov.deajvpar.gestcontractclient.fx.dtos.TipoContratoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.table.TipoContratoDtoTable;
import co.gov.deajvpar.gestcontractclient.fx.utility.HttpCodeResponse;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyGsonMapper;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyHttpApi;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyScreen;
import co.gov.deajvpar.gestcontractclient.fx.utility.StatusCode;
import co.gov.deajvpar.gestcontractclient.fx.utility.UsedApis;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Jairo F
 */
public class PanelTipoContratosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private VBox panelCrear;
    @FXML
    private VBox panelVer;
    @FXML
    private Button btnCrear;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnActualizar;

    @FXML
    private Label lbErrorTipo;

    @FXML
    private TableView<TipoContratoDtoTable> tablaContratos;

    @FXML
    private TextField txtTipo;
    @FXML
    private TextField txtBuscar;
    @FXML
    private TextField txtId;

    @FXML
    private TableColumn<TipoContratoDtoTable, String> tableColumnTipo;
    @FXML
    private TableColumn<TipoContratoDtoTable, String> tableColumnId;

    private ObservableList<TipoContratoDtoTable> data;
    private SesionUserDto sesion;
    private TipoContratoDto[] tipoContratoList;
    private boolean create, view, delete, update;

    @FXML
    private void actionEventBotonCrear(ActionEvent e) {
        this.panelCrear.setVisible(true);
        this.panelVer.setVisible(false);
        this.limpiarPanelCrear();
        this.selectCrearOrActualizar(true);
    }
//

//
    @FXML
    private void actionEventBotonEditar(ActionEvent e) {
        TipoContratoDtoTable selected = this.tablaContratos.getSelectionModel().getSelectedItem();
        if (selected != null) {

            this.limpiarPanelCrear();
            this.panelCrear.setVisible(true);
            this.panelVer.setVisible(false);
            this.selectCrearOrActualizar(false);
            TipoContratoDto dto = this.buscarIntoArray(selected.getId());
            this.txtId.setText(dto.getId().toString());
            this.txtTipo.setText(dto.getDescripcion());
        }

    }
//

    @FXML
    private void actionEventBotonActualizar(ActionEvent e) {
        if (this.validaFormCrear()) {
            TipoContratoDto tipoContrato = this.loadTipoContrato();

            try {
                MyHttpApi.jsonPostRequest(UsedApis.API_TIPO_CONTRATO_EDIT, tipoContrato);
                String response = MyHttpApi.stringResponse();
                StatusCode status = HttpCodeResponse.get(MyHttpApi.responseStatusCode());
                if (MyHttpApi.statusOk()) {
                    MyScreen.showMessage(null, status.getStatus(), "Solicitud ejecutada con exito. El tipo de contrato ha sido actualizado");
                    this.initTable();
                } else {
                    MyScreen.errorMessage(status.toString(), response);
                }
            } catch (UnirestException ex) {
                MyScreen.errorMessage(ex);
            }
            this.limpiarPanelCrear();
        }
    }
//

    @FXML
    private void actionEventBotonEliminar(ActionEvent e) {
        //this.limpiarPanelCrear();
        TipoContratoDtoTable selected = this.tablaContratos.getSelectionModel().getSelectedItem();

        if (selected != null) {
            Optional<ButtonType> result = MyScreen.confirmMessage(null, "Confirmacion", "Esta seguro de realizar la eliminacion?");
            if (result.get() == ButtonType.OK) {
                Long id = selected.getId();
                try {
                    MyHttpApi.jsonPostRequest(UsedApis.API_TIPO_CONTRATO_DELETE, id);
                    String response = MyHttpApi.stringResponse();
                    StatusCode status = HttpCodeResponse.get(MyHttpApi.responseStatusCode());
                    if (MyHttpApi.statusOk()) {
                        this.initTable();
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

    private TipoContratoDto loadTipoContrato() {

        TipoContratoDto tipoContrato = new TipoContratoDto();
        tipoContrato.setDescripcion(this.txtTipo.getText());
        tipoContrato.setCrateByuser(sesion.getUser());

        String id = this.txtId.getText();
        if (id == null) {
            tipoContrato.setId(null);
        } else {
            tipoContrato.setId(Long.valueOf(id));
        }
        return tipoContrato;
    }

    @FXML
    private void actionEventBotonGuardar(ActionEvent e) {
        if (this.validaFormCrear()) {
            TipoContratoDto tipoContrato = this.loadTipoContrato();
            try {
                MyHttpApi.jsonPostRequest(UsedApis.API_TIPO_CONTRATO_SAVE, tipoContrato);
                String response = MyHttpApi.stringResponse();
                StatusCode status = HttpCodeResponse.get(MyHttpApi.responseStatusCode());
                if (MyHttpApi.statusOk()) {
                    MyScreen.showMessage(null, status.getStatus(), "Solicitud ejecutada con exito. El tipo de contrato ha sido registrado");
                    this.initTable();
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
    private void actionEventBtnVolver(ActionEvent e) {
        this.panelCrear.setVisible(false);
        this.panelVer.setVisible(true);
    }
//
//   

    @FXML
    public void eventKeyreleaseTxtBuscar(KeyEvent e) {
        String texto = this.txtBuscar.getText();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        try {

            this.sesion = SesionUsuarioSingleton.get();
            this.setPrivilegiosModulo();

            this.lbErrorTipo.setVisible(false);
            this.selectCrearOrActualizar(true);

            this.panelCrear.setVisible(false);
            this.panelVer.setVisible(true);
            this.initTable();
            this.txtTipo.setText(null);
            this.activarPrivilegiosModulo();

        } catch (Exception e) {
            e.printStackTrace();
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
            MyHttpApi.jsonGetRequest(UsedApis.API_TIPO_CONTRATO_GET_ALL);
            String response = MyHttpApi.stringResponse();
            StatusCode status = HttpCodeResponse.get(MyHttpApi.responseStatusCode());
            if (MyHttpApi.statusOk()) {
                this.tipoContratoList = MyGsonMapper.get().fromJson(response, TipoContratoDto[].class);
                for (TipoContratoDto dto : this.tipoContratoList) {
                    TipoContratoDtoTable row = new TipoContratoDtoTable(dto);
                    this.data.add(row);
                }
//                this.tablaContratos.setItems(data);
                if (this.delete) {

                    this.btnEliminar.setDisable(!(data.size() > 0));

                } else {
                    this.btnEliminar.setDisable(true);
                }
                if (this.view) {
                    this.setFiltroTable();
                }
            } else {
                MyScreen.errorMessage(status.toString(), response);
            }
        } catch (UnirestException ex) {
            MyScreen.errorMessage(ex);
        }

    }

    private void limpiarPanelCrear() {

        this.txtTipo.setText(null);
        this.txtId.setText(null);

        this.lbErrorTipo.setVisible(false);

        this.txtTipo.requestFocus();
    }

    private boolean validaFormCrear() {

        boolean resultValidation = true;
        boolean empty;

        empty = this.txtTipo.getText() == null || this.txtTipo.getText().isBlank();
        if (empty) {
            this.txtTipo.requestFocus();
            resultValidation = false;
        }
        this.lbErrorTipo.setVisible(empty);

        return resultValidation;

    }

    private TipoContratoDto buscarIntoArray(Long id) {

        for (TipoContratoDto t : this.tipoContratoList) {
            //DireccionSeccionalDto d = this.deajs[i];
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }

    private void selectCrearOrActualizar(boolean opt) {
        // true - > crear     false->actualizar
        this.btnActualizar.setDisable(opt);
        this.btnGuardar.setDisable(!opt);

    }

    private void setPrivilegiosModulo() {

        this.create = this.sesion.checkPrivilegioModulo(ModuloDto.CONFIGURACION, PrivilegioDto.CREAR);
        this.update = this.sesion.checkPrivilegioModulo(ModuloDto.CONFIGURACION, PrivilegioDto.ACTUALIZAR);
        this.view = this.sesion.checkPrivilegioModulo(ModuloDto.CONFIGURACION, PrivilegioDto.CONSULTAR);
        this.delete = this.sesion.checkPrivilegioModulo(ModuloDto.CONFIGURACION, PrivilegioDto.ELIMINAR);

    }

    public void activarPrivilegiosModulo() {

        this.btnGuardar.setDisable(!this.create);
        this.btnCrear.setDisable(!this.create);

        this.btnEditar.setDisable(!this.update);
        this.btnActualizar.setDisable(!this.update);

        this.btnEliminar.setDisable(!this.delete);

        this.txtBuscar.setDisable(!this.view);
        this.tablaContratos.setDisable(!this.view);

    }

}
