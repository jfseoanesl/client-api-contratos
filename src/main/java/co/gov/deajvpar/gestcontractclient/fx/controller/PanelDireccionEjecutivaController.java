/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.controller;

import co.gov.deajvpar.gestcontractclient.fx.dtos.DireccionSeccionalDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.DptoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.ModuloDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.PrivilegioDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUserDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUsuarioSingleton;
import co.gov.deajvpar.gestcontractclient.fx.dtos.table.DeajTableDto;
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
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Jairo F
 */
public class PanelDireccionEjecutivaController implements Initializable {

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
    private Button btnCancelar;
    @FXML
    private Button btnGuardarDeaj;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnActualizar;

    @FXML
    private Label lbErrorDptos;
    @FXML
    private Label lbErrorNombreDeaj;

//    @FXML
//    private Button btnVer;
    @FXML
    private ComboBox cmbDptos;
    @FXML
    private ListView listViewDptos;

    @FXML
    private TableView<DeajTableDto> tablaDeaj;

    @FXML
    private TextField txtDeaj;
    @FXML
    private TextField txtBuscar;
    @FXML
    private TextField txtId;

    @FXML
    private TableColumn<DeajTableDto, String> tableColumnDeaj;
    @FXML
    private TableColumn<DeajTableDto, String> tableColumnDpto;

//    @FXML
//    private TableColumn columnStock;
    @FXML
    private TableColumn<DeajTableDto, String> tableColumnId;

    private ObservableList<DeajTableDto> data;
    private List<DptoDto> dptoList;
    private List<DptoDto> listDptoSeleccionados;
    private SesionUserDto sesion;
    private DireccionSeccionalDto[] deajs;
    private boolean create, view, delete, update;

    @FXML
    private void actionEventBotonCrear(ActionEvent e) {
        this.panelCrear.setVisible(true);
        this.panelVer.setVisible(false);
        this.limpiarPanelCrear();
        this.selectCrearOrActualizar(true);
    }

    @FXML
    private void actionEventBotonCancelar(ActionEvent e) {
        this.limpiarPanelCrear();
    }

    @FXML
    private void actionEventBotonEditar(ActionEvent e) {
        DeajTableDto selected = this.tablaDeaj.getSelectionModel().getSelectedItem();
        if (selected != null) {

            this.limpiarPanelCrear();
            this.panelCrear.setVisible(true);
            this.panelVer.setVisible(false);
            this.selectCrearOrActualizar(false);
            DireccionSeccionalDto dto = this.buscarDeajIntoArray(selected.getId());
            this.txtId.setText(dto.getIdDireccion().toString());
            this.txtDeaj.setText(dto.getDescripcionSeccional());
            this.listDptoSeleccionados = dto.getListDptoCoordinados();
            this.setListviewDptos();

        }

    }

    @FXML
    private void actionEventBotonActualizar(ActionEvent e) {
        if (this.validaFormCrear()) {
            DireccionSeccionalDto deaj = new DireccionSeccionalDto();
            deaj.setDescripcionSeccional(this.txtDeaj.getText());
            deaj.setEliminado(false);
            deaj.setIdDireccion(Long.valueOf(this.txtId.getText()));
            deaj.setListDptoCoordinados(this.listDptoSeleccionados);
            deaj.setCreatedByUser(sesion.getUser());

            try {
                MyHttpApi.jsonPostRequest(UsedApis.API_DEAJ_EDIT, deaj);
                String response = MyHttpApi.stringResponse();
                StatusCode status = HttpCodeResponse.get(MyHttpApi.responseStatusCode());
                if (MyHttpApi.statusOk()) {
                    MyScreen.showMessage(null, status.getStatus(), "Solicitud ejecutada con exito. La direcion seccional ha sido actualizada");
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
    private void actionEventBotonEliminar(ActionEvent e) {
        //this.limpiarPanelCrear();
        DeajTableDto selected = this.tablaDeaj.getSelectionModel().getSelectedItem();

        if (selected != null) {
            Optional<ButtonType> result = MyScreen.confirmMessage(null, "Confirmacion", "Esta seguro de realizar la eliminacion?");
            if (result.get() == ButtonType.OK) {
                Long id = selected.getId();
                try {
                    MyHttpApi.jsonPostRequest(UsedApis.API_DEAJ_DELETE, id);
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

    @FXML
    private void actionEventBotonGuardarDeaj(ActionEvent e) {
        if (this.validaFormCrear()) {
            DireccionSeccionalDto deaj = new DireccionSeccionalDto();
            deaj.setDescripcionSeccional(this.txtDeaj.getText());
            deaj.setEliminado(false);
            deaj.setListDptoCoordinados(this.listDptoSeleccionados);
            deaj.setCreatedByUser(sesion.getUser());

            try {
                MyHttpApi.jsonPostRequest(UsedApis.API_DEAJ_SAVE, deaj);
                String response = MyHttpApi.stringResponse();
                StatusCode status = HttpCodeResponse.get(MyHttpApi.responseStatusCode());
                if (MyHttpApi.statusOk()) {
                    MyScreen.showMessage(null, status.getStatus(), "Solicitud ejecutada con exito. La direcion seccional ha sido registrada");
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

    @FXML
    private void actionEventCmbDpto(ActionEvent e) {
        int index = this.cmbDptos.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            if (!this.listDptoSeleccionados.contains(this.dptoList.get(index))) {
                this.listDptoSeleccionados.add(this.dptoList.get(index));
            }
        }
        this.setListviewDptos();
    }

    @FXML
    private void clickListView(MouseEvent m) {
        int index = this.listViewDptos.getSelectionModel().getSelectedIndex();
        this.listDptoSeleccionados.remove(index);
        this.setListviewDptos();
    }

    @FXML
    public void eventKeyreleaseTxtBuscar(KeyEvent e) {
        String texto = this.txtBuscar.getText();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        try {
            this.lbErrorDptos.setVisible(false);
            this.lbErrorNombreDeaj.setVisible(false);
            this.selectCrearOrActualizar(true);
            this.sesion = SesionUsuarioSingleton.get();
            this.panelCrear.setVisible(false);
            this.panelVer.setVisible(true);
            this.listDptoSeleccionados = new ArrayList();
            this.loadDptos();
            this.initTable();
            this.txtDeaj.setText(null);
            this.setPrivilegiosModulo();
            this.activarPrivilegiosModulo();

        } catch (Exception e) {
            e.printStackTrace();
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
            MyHttpApi.jsonGetRequest(UsedApis.API_DEAJ_GET_ALL);
            String response = MyHttpApi.stringResponse();
//            System.out.println(response);
            StatusCode status = HttpCodeResponse.get(MyHttpApi.responseStatusCode());
            if (MyHttpApi.statusOk()) {
                this.deajs = MyGsonMapper.get().fromJson(response, DireccionSeccionalDto[].class);
                for (DireccionSeccionalDto dto : deajs) {
                    DeajTableDto deajTable = new DeajTableDto(dto);
                    this.data.add(deajTable);
                }
                this.tablaDeaj.setItems(data);
                if (this.delete) {

                    this.btnEliminar.setDisable(!(data.size() > 0));

                } else {
                    this.btnEliminar.setDisable(true);
                }
                this.setFiltroTable();
            } else {
                MyScreen.errorMessage(status.toString(), response);
            }
        } catch (UnirestException ex) {
            MyScreen.errorMessage(ex);
        }

    }

    private void loadDptos() {

        this.dptoList = new ArrayList();
        try {
            MyHttpApi.jsonGetRequest(UsedApis.API_DPTO_GET_ALL);
            String response = MyHttpApi.stringResponse();
            System.out.println(response);
            if (MyHttpApi.statusOk()) {
                DptoDto[] dptos = MyGsonMapper.get().fromJson(response, DptoDto[].class);
                this.dptoList = List.of(dptos);
            } else {
                StatusCode status = HttpCodeResponse.get(MyHttpApi.responseStatusCode());
                MyScreen.errorMessage(status.toString(), response);
            }
        } catch (UnirestException ex) {

        }

        for (DptoDto d : this.dptoList) {
            this.cmbDptos.getItems().add(d.getNombreDpto());
        }

    }

    private void setListviewDptos() {

        this.listViewDptos.getItems().clear();
        for (DptoDto dto : this.listDptoSeleccionados) {
            this.listViewDptos.getItems().add(dto.getNombreDpto());
        }
    }

    private void limpiarPanelCrear() {
        this.listDptoSeleccionados.clear();
        this.setListviewDptos();

        this.txtDeaj.setText(null);
        this.cmbDptos.getSelectionModel().select(-1);

        this.lbErrorDptos.setVisible(false);
        this.lbErrorNombreDeaj.setVisible(false);

        this.txtDeaj.requestFocus();
    }

    private boolean validaFormCrear() {

        boolean resultValidation = true;
        boolean empty;

        empty = this.txtDeaj.getText() == null || this.txtDeaj.getText().isBlank();
        if (empty) {
            this.txtDeaj.requestFocus();
            resultValidation = false;
        }
        this.lbErrorNombreDeaj.setVisible(empty);

        empty = this.listDptoSeleccionados.size() == 0;
        if (empty) {
            this.cmbDptos.requestFocus();
            resultValidation = false;
        }
        this.lbErrorDptos.setVisible(empty);

        return resultValidation;

    }

    private DireccionSeccionalDto buscarDeajIntoArray(Long id) {

        for (DireccionSeccionalDto d : this.deajs) {
            //DireccionSeccionalDto d = this.deajs[i];
            if (d.getIdDireccion().equals(id)) {
                return d;
            }
        }
        return null;
    }

    private void selectCrearOrActualizar(boolean opt) {
        // true - > crear     false->actualizar
        this.btnActualizar.setDisable(opt);
        this.btnGuardarDeaj.setDisable(!opt);

    }

    private void setPrivilegiosModulo() {

        this.create = this.sesion.checkPrivilegioModulo(ModuloDto.CONFIGURACION, PrivilegioDto.CREAR);
        this.update = this.sesion.checkPrivilegioModulo(ModuloDto.CONFIGURACION, PrivilegioDto.ACTUALIZAR);
        this.view = this.sesion.checkPrivilegioModulo(ModuloDto.CONFIGURACION, PrivilegioDto.CONSULTAR);
        this.delete = this.sesion.checkPrivilegioModulo(ModuloDto.CONFIGURACION, PrivilegioDto.ELIMINAR);

    }

    public void activarPrivilegiosModulo() {

        this.btnGuardarDeaj.setDisable(!this.create);
        this.btnCrear.setDisable(!this.create);

        this.btnEditar.setDisable(!this.update);
        this.btnActualizar.setDisable(!this.update);

        this.btnEliminar.setDisable(!this.delete);

        this.txtBuscar.setDisable(!this.view);

    }

}
