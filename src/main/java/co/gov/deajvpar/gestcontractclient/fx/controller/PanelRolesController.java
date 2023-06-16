/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.controller;

import co.gov.deajvpar.gestcontractclient.fx.Exceptions.HttpResponseException;
import co.gov.deajvpar.gestcontractclient.fx.dtos.*;
import co.gov.deajvpar.gestcontractclient.fx.dtos.table.*;
import co.gov.deajvpar.gestcontractclient.fx.logic.GestionRoles;
import co.gov.deajvpar.gestcontractclient.fx.utility.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Jairo F
 */
public class PanelRolesController implements Initializable, IFormController {

    @FXML
    private TextField txtNombreRol, txtIdRol;
    @FXML
    private TextField txtIdDashboard, txtIdSupervisor, txtIdSupervision;
    @FXML
    private TextField txtIdUsuarios, txtIdContratos, txtIdConfiguracion;

    @FXML
    private ComboBox cmbTipoUsuario, cmbFiltroTipoUsuario;

    @FXML
    private ToggleButton onConfig, offConfig, onUsuarios, offUsuarios;
    @FXML
    private ToggleButton onContratos, offContratos, onSupervision, offSupervision;
    @FXML
    private ToggleButton onSupervisor, offSupervisor, onDashboard, offDashboard;

    @FXML
    private Label lbConfiguracion, lbUsuarios, lbContratos, lbSupervision, lbSupervisor, lbDashboard;

    @FXML
    private Label lbErrorNombreRol, lbErrorTipoUsuario;

    @FXML
    private Button btnGuardarRol, btnActualizarRol, btnCancelarRol;
    @FXML
    private Button btnCrearRol, btnEditarRol, btnEliminarRol;

    @FXML
    private CheckBox rConfig, cConfig, uConfig, dConfig;
    @FXML
    private CheckBox rUsuarios, cUsuarios, uUsuarios, dUsuarios;
    @FXML
    private CheckBox rContratos, cContratos, uContratos, dContratos;
    @FXML
    private CheckBox rSupervision, cSupervision, uSupervision, dSupervision;
    @FXML
    private CheckBox rSupervisor, cSupervisor, uSupervisor, dSupervisor;
    @FXML
    private CheckBox rDashboard;

    @FXML
    private VBox panelListRoles, panelCrearRol;

    @FXML
    private Tab tabRoles;
    @FXML
    private StackPane stackPaneRoles;
    @FXML
    private TableView<RolUsuarioTableDto> tablaRoles;
    @FXML
    private TableColumn<RolUsuarioTableDto, String> columnIdRol, columnNombreRol, columnConfiguracion, columnUsuarios;
    @FXML
    private TableColumn<RolUsuarioTableDto, String> columnContratos, columnSupervision, columnSupervisor, columnDashboard;
    @FXML

    private ObservableList<RolUsuarioTableDto> dataTableRoles;
    private GestionRoles logicaRolaes = new GestionRoles();

    @FXML
    private void actionEventBtnGuardarRol(ActionEvent e) {

        boolean validate = this.validarEnvioFormulario();
        if (validate) {
            RolUsuarioDto rol = this.leerDatosRolUsuario();
            this.guardarRolusuario(rol);
            this.limpiarFormulario();
        }

    }

    @FXML
    private void actionEventBtnActualizarRol(ActionEvent e) {

        boolean validate = this.validarEnvioFormulario();
        if (validate) {

            RolUsuarioDto rol = this.leerDatosRolUsuario();
            this.actualizarRolusuario(rol);
        }

    }

    @FXML
    private void actionEventBtnCancelarRol(ActionEvent e) {
        this.activarDesactivarPaneles(true);
    }

    @FXML
    private void actionEventCmbFiltroTipoUsuario(ActionEvent e) {
        this.loadDatatTableRoles();
    }

    @FXML
    private void actionEventBotonCrearRol(ActionEvent e) {
        this.activarDesactivarPaneles(false);
        this.activarDesactivarOpciones(true);
        this.limpiarFormulario();
    }

    @FXML
    private void actionEventBotonEditarRol(ActionEvent e) {

        RolUsuarioTableDto selected = this.tablaRoles.getSelectionModel().getSelectedItem();
        if (selected != null) {

            this.activarDesactivarPaneles(false);
            this.activarDesactivarOpciones(false);
            this.limpiarFormulario();
            RolUsuarioDto dto = this.logicaRolaes.GetRolUsuarioArray(selected.getId());
            this.txtIdRol.setText(dto.getId().toString());
            this.txtNombreRol.setText(dto.getNombreRol());
            this.cmbTipoUsuario.getSelectionModel().select(dto.getTipoUsuario());
            for (PermisoUserDto p : dto.getListPermisosUsuario()) {
                boolean r = p.ispConsultar();
                boolean c = p.ispCrear();
                boolean u = p.ispEditar();
                boolean d = p.ispEliminar();
                String modulo = p.getModulo();
                this.activarDesactivarModulo(ModuloDto.valueOf(modulo), true, r, c, u, d, p.getId().toString());
            }

        }
    }

    @FXML
    private void actionEventBotonEliminarRol(ActionEvent e) {

        RolUsuarioTableDto selected = this.tablaRoles.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Long id = selected.getId();
            this.eliminarRegistro(id);
        }
    }

    @FXML
    private void onConfiguracion(ActionEvent e) {
        String id = this.txtIdConfiguracion.getText();
        this.activarDesactivarModulo(ModuloDto.CONFIGURACION, true, this.rConfig.isSelected(),
                this.cConfig.isSelected(),
                this.uConfig.isSelected(),
                this.dConfig.isSelected(), id);
    }

    @FXML
    private void offConfiguracion(ActionEvent e) {
        String id = this.txtIdConfiguracion.getText();
        this.activarDesactivarModulo(ModuloDto.CONFIGURACION, false, this.rConfig.isSelected(),
                this.cConfig.isSelected(),
                this.uConfig.isSelected(),
                this.dConfig.isSelected(), id);
    }

    @FXML
    private void onUsuarios(ActionEvent e) {
        String id = this.txtIdUsuarios.getText();
        this.activarDesactivarModulo(ModuloDto.USUARIOS, true, this.rUsuarios.isSelected(),
                this.cUsuarios.isSelected(),
                this.uUsuarios.isSelected(),
                this.dUsuarios.isSelected(), id);
    }

    @FXML
    private void offUsuarios(ActionEvent e) {
        String id = this.txtIdUsuarios.getText();
        this.activarDesactivarModulo(ModuloDto.USUARIOS, false, this.rUsuarios.isSelected(),
                this.cUsuarios.isSelected(),
                this.uUsuarios.isSelected(),
                this.dUsuarios.isSelected(), id);
    }

    @FXML
    private void onContratos(ActionEvent e) {
        String id = this.txtIdContratos.getText();
        this.activarDesactivarModulo(ModuloDto.CONTRATOS, true, this.rContratos.isSelected(),
                this.cContratos.isSelected(),
                this.uContratos.isSelected(),
                this.dContratos.isSelected(), id);
    }

    @FXML
    private void offContratos(ActionEvent e) {
        String id = this.txtIdContratos.getText();
        this.activarDesactivarModulo(ModuloDto.CONTRATOS, false, this.rContratos.isSelected(),
                this.cContratos.isSelected(),
                this.uContratos.isSelected(),
                this.dContratos.isSelected(), id);
    }

    @FXML
    private void onSupervision(ActionEvent e) {
        String id = this.txtIdSupervision.getText();
        this.activarDesactivarModulo(ModuloDto.SUPERVISION, true, this.rSupervision.isSelected(),
                this.cSupervision.isSelected(),
                this.uSupervision.isSelected(),
                this.dSupervision.isSelected(), id);
    }

    @FXML
    private void offSupervision(ActionEvent e) {
        String id = this.txtIdSupervision.getText();
        this.activarDesactivarModulo(ModuloDto.SUPERVISION, false, this.rSupervision.isSelected(),
                this.cSupervision.isSelected(),
                this.uSupervision.isSelected(),
                this.dSupervision.isSelected(), id);
    }

    @FXML
    private void onSupervisor(ActionEvent e) {
        String id = this.txtIdSupervisor.getText();
        this.activarDesactivarModulo(ModuloDto.SUPERVISOR, true, this.rSupervisor.isSelected(),
                this.cSupervisor.isSelected(),
                this.uSupervisor.isSelected(),
                this.dSupervisor.isSelected(), id);
    }

    @FXML
    private void offSupervisor(ActionEvent e) {
        String id = this.txtIdSupervisor.getText();
        this.activarDesactivarModulo(ModuloDto.SUPERVISOR, false, this.rSupervisor.isSelected(),
                this.cSupervisor.isSelected(),
                this.uSupervisor.isSelected(),
                this.dSupervisor.isSelected(), id);
    }

    @FXML
    private void onDashboard(ActionEvent e) {
        String id = this.txtIdDashboard.getText();
        this.activarDesactivarModulo(ModuloDto.DASHBOARD, true, this.rDashboard.isSelected(), false, false, false, id);;
    }

    @FXML
    private void offDashboard(ActionEvent e) {
        String id = this.txtIdDashboard.getText();
        this.activarDesactivarModulo(ModuloDto.DASHBOARD, false, this.rDashboard.isSelected(), false, false, false, id);
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        this.activarDesactivarPaneles(true);
        this.loadTipoUserCmb(this.cmbFiltroTipoUsuario);
        this.loadTipoUserCmb(this.cmbTipoUsuario);
        this.loadDatatTableRoles();
        this.loadSetPemisosModulo();
        this.activarPrivilegiosModulo();
    }

    @Override
    public void activarDesactivarPaneles(boolean select) {

        this.panelListRoles.setVisible(select);
        this.panelCrearRol.setVisible(!select);

    }

    @Override
    public void activarPrivilegiosModulo() {

        boolean create = this.logicaRolaes.isCreate();
        boolean update = this.logicaRolaes.isUpdate();
        boolean delete = this.logicaRolaes.isDelete();
        boolean view = this.logicaRolaes.isView();

        this.btnGuardarRol.setDisable(!create);
        this.btnCrearRol.setDisable(!create);

        this.btnEditarRol.setDisable(!update);
        this.btnActualizarRol.setDisable(!update);

        this.btnEliminarRol.setDisable(!delete);

        this.cmbFiltroTipoUsuario.setDisable(!view);
        this.tablaRoles.setDisable(!view);

    }

    private void loadTipoUserCmb(ComboBox cmb) {

        cmb.getItems().addAll(TipoUsuarioDto.getList());
    }

    private void loadRoles(ComboBox cmb) throws HttpResponseException {
        this.logicaRolaes.setListRoles(new RolUsuarioDto[0]);

        int index = cmb.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            this.logicaRolaes.getAll();
        } else {
            TipoUsuarioDto tipo = TipoUsuarioDto.get(TipoUsuarioDto.getList()[index]);
            this.logicaRolaes.getRolTipoUsuario(tipo);
        }

    }

    private void loadDatatTableRoles() {
        try {
            this.columnIdRol.setCellValueFactory(new PropertyValueFactory("id"));
            this.columnNombreRol.setCellValueFactory(new PropertyValueFactory("nombre"));
            this.columnConfiguracion.setCellValueFactory(new PropertyValueFactory("configuracion"));
            this.columnUsuarios.setCellValueFactory(new PropertyValueFactory("usuarios"));
            this.columnContratos.setCellValueFactory(new PropertyValueFactory("contratos"));
            this.columnSupervision.setCellValueFactory(new PropertyValueFactory("supervision"));
            this.columnSupervisor.setCellValueFactory(new PropertyValueFactory("supervisor"));
            this.columnDashboard.setCellValueFactory(new PropertyValueFactory("dashboard"));

            this.dataTableRoles = FXCollections.observableArrayList();

            this.loadRoles(this.cmbFiltroTipoUsuario);

            for (RolUsuarioDto dto : this.logicaRolaes.getListRoles()) {
                RolUsuarioTableDto dtoTable = new RolUsuarioTableDto(dto);
                this.dataTableRoles.add(dtoTable);
            }

            if (this.logicaRolaes.isDelete()) {
                this.btnEliminarRol.setDisable(!(this.dataTableRoles.size() > 0));
            } else {
                this.btnEliminarRol.setDisable(true);
            }
            if (this.logicaRolaes.isView()) {
                this.tablaRoles.setItems(this.dataTableRoles);
            }
        } catch (HttpResponseException ex) {
            MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
        }

    }

    private void loadSetPemisosModulo() {
        this.activarDesactivarModulo(ModuloDto.CONFIGURACION, false, false, false, false, false, null);
        this.activarDesactivarModulo(ModuloDto.USUARIOS, false, false, false, false, false, null);
        this.activarDesactivarModulo(ModuloDto.CONTRATOS, false, false, false, false, false, null);
        this.activarDesactivarModulo(ModuloDto.SUPERVISION, false, false, false, false, false, null);
        this.activarDesactivarModulo(ModuloDto.SUPERVISOR, false, false, false, false, false, null);
        this.activarDesactivarModulo(ModuloDto.DASHBOARD, false, false, false, false, false, null);
    }

    private void activarDesactivarModulo(ModuloDto modulo, boolean activo, boolean r, boolean c, boolean u, boolean d, String id) {

        switch (modulo) {

            case CONFIGURACION:
                this.onConfig.setSelected(activo);
                this.offConfig.setSelected(!activo);
                this.lbConfiguracion.setDisable(!activo);
                this.cConfig.setDisable(!activo);
                this.rConfig.setDisable(!activo);
                this.dConfig.setDisable(!activo);
                this.uConfig.setDisable(!activo);
                this.cConfig.setSelected(c);
                this.rConfig.setSelected(r);
                this.dConfig.setSelected(d);
                this.uConfig.setSelected(u);
                this.txtIdConfiguracion.setText(id);

                break;
            case USUARIOS:
                this.onUsuarios.setSelected(activo);
                this.offUsuarios.setSelected(!activo);
                this.lbUsuarios.setDisable(!activo);
                this.cUsuarios.setDisable(!activo);
                this.uUsuarios.setDisable(!activo);
                this.dUsuarios.setDisable(!activo);
                this.rUsuarios.setDisable(!activo);
                this.cUsuarios.setSelected(c);
                this.uUsuarios.setSelected(u);
                this.dUsuarios.setSelected(d);
                this.rUsuarios.setSelected(r);
                this.txtIdUsuarios.setText(id);
                break;
            case CONTRATOS:
                this.onContratos.setSelected(activo);
                this.offContratos.setSelected(!activo);
                this.lbContratos.setDisable(!activo);
                this.cContratos.setDisable(!activo);
                this.rContratos.setDisable(!activo);
                this.uContratos.setDisable(!activo);
                this.dContratos.setDisable(!activo);
                this.cContratos.setSelected(c);
                this.rContratos.setSelected(r);
                this.uContratos.setSelected(u);
                this.dContratos.setSelected(d);
                this.txtIdContratos.setText(id);
                break;
            case SUPERVISION:
                this.onSupervision.setSelected(activo);
                this.offSupervision.setSelected(!activo);
                this.lbSupervision.setDisable(!activo);
                this.cSupervision.setDisable(!activo);
                this.dSupervision.setDisable(!activo);
                this.uSupervision.setDisable(!activo);
                this.rSupervision.setDisable(!activo);
                this.cSupervision.setSelected(c);
                this.dSupervision.setSelected(d);
                this.uSupervision.setSelected(u);
                this.rSupervision.setSelected(r);
                this.txtIdSupervision.setText(id);
                break;
            case SUPERVISOR:
                this.onSupervisor.setSelected(activo);
                this.offSupervisor.setSelected(!activo);
                this.lbSupervisor.setDisable(!activo);
                this.cSupervisor.setDisable(!activo);
                this.dSupervisor.setDisable(!activo);
                this.uSupervisor.setDisable(!activo);
                this.rSupervisor.setDisable(!activo);
                this.cSupervisor.setSelected(c);
                this.dSupervisor.setSelected(d);
                this.uSupervisor.setSelected(u);
                this.rSupervisor.setSelected(r);
                this.txtIdSupervisor.setText(id);
                break;
            case DASHBOARD:
                this.onDashboard.setSelected(activo);
                this.offDashboard.setSelected(!activo);
                this.lbDashboard.setDisable(!activo);
                this.rDashboard.setDisable(!activo);
                this.rDashboard.setSelected(r);
                this.txtIdDashboard.setText(id);

        }

    }

    public RolUsuarioDto leerDatosRolUsuario() {

        RolUsuarioDto rol = new RolUsuarioDto();
        rol.setNombreRol(this.txtNombreRol.getText());
        rol.setTipoUsuario(this.cmbTipoUsuario.getSelectionModel().getSelectedItem().toString());
        rol.setEliminado(false);
        rol.setCratedByUser(this.logicaRolaes.getSesionUser());

        if (Utility.validateEmptyComponentTextField(this.txtIdRol)) {
            rol.setId(Long.valueOf(this.txtIdRol.getText()));
        }

        if (this.onConfig.isSelected()) {
            PermisoUserDto config = new PermisoUserDto();
            config.setModulo(ModuloDto.CONFIGURACION.toString());
            config.setpConsultar(this.rConfig.isSelected());
            config.setpCrear(this.cConfig.isSelected());
            config.setpEditar(this.uConfig.isSelected());
            config.setpEliminar(this.dConfig.isSelected());
            rol.getListPermisosUsuario().add(config);
            if (Utility.validateEmptyComponentTextField(this.txtIdConfiguracion)) {
                config.setId(Long.valueOf(this.txtIdConfiguracion.getText()));
            }
        }
        if (this.onUsuarios.isSelected()) {
            PermisoUserDto usuario = new PermisoUserDto();
            usuario.setModulo(ModuloDto.USUARIOS.toString());
            usuario.setpConsultar(this.rUsuarios.isSelected());
            usuario.setpCrear(this.cUsuarios.isSelected());
            usuario.setpEditar(this.uUsuarios.isSelected());
            usuario.setpEliminar(this.dUsuarios.isSelected());
            rol.getListPermisosUsuario().add(usuario);
            if (Utility.validateEmptyComponentTextField(this.txtIdUsuarios)) {
                usuario.setId(Long.valueOf(this.txtIdUsuarios.getText()));
            }
        }
        if (this.onContratos.isSelected()) {
            PermisoUserDto contrato = new PermisoUserDto();
            contrato.setModulo(ModuloDto.CONTRATOS.toString());
            contrato.setpConsultar(this.rContratos.isSelected());
            contrato.setpCrear(this.cContratos.isSelected());
            contrato.setpEditar(this.uContratos.isSelected());
            contrato.setpEliminar(this.dContratos.isSelected());
            rol.getListPermisosUsuario().add(contrato);
            if (Utility.validateEmptyComponentTextField(this.txtIdContratos)) {
                contrato.setId(Long.valueOf(this.txtIdContratos.getText()));
            }
        }
        if (this.onSupervision.isSelected()) {
            PermisoUserDto supervision = new PermisoUserDto();
            supervision.setModulo(ModuloDto.SUPERVISION.toString());
            supervision.setpConsultar(this.rSupervision.isSelected());
            supervision.setpCrear(this.cSupervision.isSelected());
            supervision.setpEditar(this.uSupervision.isSelected());
            supervision.setpEliminar(this.dSupervision.isSelected());
            rol.getListPermisosUsuario().add(supervision);
            if (Utility.validateEmptyComponentTextField(this.txtIdSupervision)) {
                supervision.setId(Long.valueOf(this.txtIdSupervision.getText()));
            }
        }
        if (this.onSupervisor.isSelected()) {
            PermisoUserDto supervisor = new PermisoUserDto();
            supervisor.setModulo(ModuloDto.SUPERVISOR.toString());
            supervisor.setpConsultar(this.rSupervisor.isSelected());
            supervisor.setpCrear(this.cSupervisor.isSelected());
            supervisor.setpEditar(this.uSupervisor.isSelected());
            supervisor.setpEliminar(this.dSupervisor.isSelected());
            rol.getListPermisosUsuario().add(supervisor);
            if (Utility.validateEmptyComponentTextField(this.txtIdSupervisor)) {
                supervisor.setId(Long.valueOf(this.txtIdSupervisor.getText()));
            }
        }
        if (this.onDashboard.isSelected()) {
            PermisoUserDto dashboard = new PermisoUserDto();
            dashboard.setModulo(ModuloDto.DASHBOARD.toString());
            dashboard.setpConsultar(this.rDashboard.isSelected());
            dashboard.setpCrear(false);
            dashboard.setpEditar(false);
            dashboard.setpEliminar(false);
            rol.getListPermisosUsuario().add(dashboard);
            if (Utility.validateEmptyComponentTextField(this.txtIdDashboard)) {
                dashboard.setId(Long.valueOf(this.txtIdDashboard.getText()));
            }
        }
        return rol;
    }

    public void eliminarRegistro(Long id) {
        RolUsuarioDto dto = new RolUsuarioDto();
        dto.setId(id);
        try {
            List<UserDto> listUser = this.logicaRolaes.listUserbyRol(dto);
            if (listUser.isEmpty()) {
                Optional<ButtonType> result = MyScreen.confirmMessage(null, "Confirmacion", "Esta seguro de realizar la eliminacion?");
                if (result.get() == ButtonType.OK) {

                        this.logicaRolaes.delete(dto);
                        MyScreen.exitMessage();
                        this.loadDatatTableRoles();
   
                }
            }
            else{
                MyScreen.errorMessage("No es posible eliminar el rol, porque tiene usuarios activos");
            }
        } catch (HttpResponseException ex) {
            MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
        }

    }

    public void actualizarRolusuario(RolUsuarioDto rol) {
        try {
            this.logicaRolaes.edit(rol);
            MyScreen.exitMessage();
            this.loadDatatTableRoles();

        } catch (HttpResponseException ex) {
            MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
        }
    }

    public void guardarRolusuario(RolUsuarioDto rol) {
        try {
            this.logicaRolaes.save(rol);
            MyScreen.exitMessage();
            this.loadDatatTableRoles();

        } catch (HttpResponseException ex) {
            MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
        }
    }

    @Override
    public boolean validarEnvioFormulario() {
        boolean resultValidation = true;

        if (!Utility.validateEmptyComponentTextField(this.txtNombreRol, this.lbErrorNombreRol)) {
            resultValidation = false;
        }

        if (!Utility.validateEmptyComponentCombo(this.cmbTipoUsuario, this.lbErrorTipoUsuario)) {
            resultValidation = false;
        }

        return resultValidation;
    }

    @Override
    public void activarDesactivarOpciones(boolean opt) {
        this.btnActualizarRol.setDisable(opt);
        this.btnGuardarRol.setDisable(!opt);
    }

    @Override
    public void limpiarFormulario() {
        this.txtIdRol.setText(null);
        this.txtNombreRol.setText(null);
        this.cmbTipoUsuario.getSelectionModel().select(-1);
        this.loadSetPemisosModulo();
    }

}
