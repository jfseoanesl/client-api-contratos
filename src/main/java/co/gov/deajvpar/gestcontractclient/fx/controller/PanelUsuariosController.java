/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.controller;

import co.gov.deajvpar.gestcontractclient.fx.Exceptions.*;
import co.gov.deajvpar.gestcontractclient.fx.dtos.*;
import co.gov.deajvpar.gestcontractclient.fx.dtos.table.*;
import co.gov.deajvpar.gestcontractclient.fx.logic.GestionUsuarios;
import co.gov.deajvpar.gestcontractclient.fx.utility.*;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.util.*;
import javafx.collections.*;
import javafx.collections.transformation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Jairo F
 */
public class PanelUsuariosController implements Initializable, IFormController {

    @FXML
    private TextField txtBuscarUsuario, txtNoDocumento, txtIdUsuario, txtIdPersona;
    @FXML
    private TextField txtPNombre, txtSNombre, txtPApellido, txtSApellido, txtUsername;
    @FXML
    private PasswordField txtPassword1, txtPassword2;
    @FXML
    private ComboBox cmbTipoUsuarioRegistro, cmbTipoDocumento, cmbGenero, cmbRolesUsuario, cmbDireccionSeccional;
    @FXML
    private DatePicker txtFechaNacimiento;
    @FXML
    private Label lbErrorTipoDocumento, lbErrorNoDocumento, lbErrorPNombre, lbErrorSNombre;
    @FXML
    private Label lbErrorPApellido, lbErrorSApellido, lbErrorGenero, lbErrorFechaNacimiento;
    @FXML
    private Label lbErrorDeaj, lbErrorTipoUsuario, lbErrorRolUsuario, lbErrorUsername, lbErrorUsername2;
    @FXML
    private Label lbErrorPassword1, lbErrorPassword2, lbErrorPasswords;
    @FXML
    private Button btnGuardarUsuario, btnActualizarUsuario, btnCancelarUsuario;
    @FXML
    private Button btnCrearUsuario, btnEditarUsuario, btnEliminarUsuario;
    @FXML
    private VBox panelListUsuario, panelCrearUsuario;
    @FXML
    private Tab tabOtros, tabUsuarios;
    @FXML
    private TableView<UsuarioTableDto> tablaUsuarios;
    @FXML
    private TableColumn<UsuarioTableDto, String> columnIdUsuario, columnNombreUsuario, columnUsername, columnTipoUsuario;
    @FXML
    private TableColumn<UsuarioTableDto, String> columnRolUsuario, columnSeccionalUsuario;
    @FXML
    private FontAwesomeIcon btnBuscarPersona;

    private ObservableList<UsuarioTableDto> dataTableUsuarios;
    private GestionUsuarios logicUsuarios = new GestionUsuarios();

    @FXML
    private void actionEventCmbTipoUsuario(ActionEvent e) {
    }

    @FXML
    private void actionEventBotonGuardarUsuario(ActionEvent e) {

        boolean validate = this.validarEnvioFormulario();

        if (validate) {

            UserDto usuario = this.leerUsuarioFromPanel();
            try {
                this.logicUsuarios.validarPasswordsUsuario(this.txtPassword1.getText(), this.txtPassword2.getText());
                this.lbErrorPasswords.setVisible(false);

            } catch (IllegalArgumentException ex) {
                this.lbErrorPasswords.setVisible(true);
                this.lbErrorPasswords.setText("(" + ex.getMessage() + ")");
                validate = false;
            }

            try {
                this.logicUsuarios.validarUsername(usuario.getUserName());
                this.lbErrorUsername2.setVisible(false);

            } catch (IllegalArgumentException ex) {
                this.lbErrorUsername2.setVisible(true);
                this.lbErrorUsername2.setText("(" + ex.getMessage() + ")");
                validate = false;
            }
            if (validate) {
                this.guardarDatosUsuario(usuario);
            }
        }

    }

    @FXML
    private void actionEventBotonActualizarUsuario(ActionEvent e) {

        boolean validate = this.validarEnvioFormulario();
        if (validate) {

            UserDto usuario = this.leerUsuarioFromPanel();
            this.actualizarDatosUsuario(usuario);
        }

    }

    @FXML
    private void actionEventBtnVolverUsuario(ActionEvent e) {
        this.activarDesactivarPaneles(true);
    }

    @FXML
    private void actionEventBotonCrearUsuario(ActionEvent e) {
        //this.activarDesactivarPaneles(false);
        this.panelCrearUsuario.setVisible(true);
        this.setDatosComboRoles();
        this.limpiarFormulario();
        this.habilitarComponentesRegistroUsuario(true);
    }

    @FXML
    private void actionEventBotonEditarUsuario(ActionEvent e) {
        UsuarioTableDto selected = this.tablaUsuarios.getSelectionModel().getSelectedItem();
        if (selected != null) {

            this.activarDesactivarPaneles(false);
            this.activarDesactivarOpciones(false);
            this.limpiarFormulario();

            UserDto dto = this.logicUsuarios.getUsuario(selected.getId());
            this.txtIdUsuario.setText(dto.getId().toString());
            this.txtNoDocumento.setText(dto.getDatosPersona().getNoDocumento());
            int index = 0;

            for (TipoDocumentoDto t : this.logicUsuarios.getListTipoDocumento()) {
                if (t.getId().equals(dto.getDatosPersona().getTipoDocumento().getId())) {
                    break;
                }
                index++;
            }
            this.cmbTipoDocumento.getSelectionModel().select(index);
            this.setDatosPersona(dto.getDatosPersona());

            
            if (dto.getDireccionSeccional() != null) {
                index = 0;
                for (DireccionSeccionalDto d : this.logicUsuarios.getDeajs()) {
                    if (d.getIdDireccion().equals(dto.getDireccionSeccional().getIdDireccion())) {
                        break;
                    }
                    index++;
                }
                this.cmbDireccionSeccional.getSelectionModel().select(index);
            }
            else{
                this.cmbDireccionSeccional.getSelectionModel().select(-1);
            }

            this.txtUsername.setText(dto.getUserName());
            this.txtPassword1.setText(dto.getPassword());
            this.txtPassword2.setText(dto.getPassword());
            this.cmbTipoUsuarioRegistro.getSelectionModel().select(dto.getTipoUsuario());
            this.setDatosComboRoles();
            index = 0;
            for (RolUsuarioDto r : this.logicUsuarios.getRoles()) {
                if (r.getId().equals(dto.getRolUsuario().getId())) {
                    break;
                }
                index++;
            }
            this.cmbRolesUsuario.getSelectionModel().select(index);
            this.cmbTipoUsuarioRegistro.setDisable(true);
            this.txtUsername.setDisable(true);
        }
    }

    @FXML
    private void actionEventBotonEliminarUsuario(ActionEvent e) {

    }

    @FXML
    private void actionOnBtnBuscarPersona(MouseEvent e) {
        int selectedIndex = this.cmbTipoDocumento.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {

            TipoDocumentoDto tipo = this.logicUsuarios.getListTipoDocumento().get(selectedIndex);
            String documento = this.txtNoDocumento.getText();
            PersonaDto dto = new PersonaDto();
            dto.setNoDocumento(documento);
            dto.setTipoDocumento(tipo);

            try {
                dto = this.logicUsuarios.findPersona(dto);
                this.setDatosPersona(dto);

            } catch (HttpResponseException ex) {
                MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
            }

        }
    }

    @FXML
    private void actionOnComboTTipoUsuarioRegistro(ActionEvent e) {

        this.setDatosComboRoles();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        this.activarDesactivarPaneles(true);
        this.setDatosCmbTipoUser(this.cmbTipoUsuarioRegistro);
        this.setDatosComboTipoDocumento();
        this.setDatosCmbGenero();
        this.setDatosComboDeaj();
        this.loadDatatTableUsuarios();
        this.habilitarComponentesRegistroUsuario(true);
        this.activarPrivilegiosModulo();
    }

    private void setDatosPersona(PersonaDto persona) {

        if (persona.getIdPersona() != null) {
            this.txtIdPersona.setText(String.valueOf(persona.getIdPersona()));
        } else {
            this.txtIdPersona.setText(null);
        }
        this.txtPNombre.setText(persona.getpNombre());
        this.txtSNombre.setText(persona.getsNombre());
        this.txtPApellido.setText(persona.getpApellido());
        this.txtSApellido.setText(persona.getsApellido());
        this.txtFechaNacimiento.setValue(persona.getFechaNacimiento());
        int index = -1;
        if (persona.getGenero() != null) {
            index = GeneroPersonaDto.getIndex(persona.getGenero());
        }
        this.cmbGenero.getSelectionModel().select(index);
        this.habilitarComponentesRegistroUsuario(false);

    }

    private void setDatosCmbGenero() {

        GeneroPersonaDto[] generos = GeneroPersonaDto.values();
        this.cmbGenero.getItems().addAll(Arrays.asList(generos));
    }

    @Override
    public void activarDesactivarPaneles(boolean select) {

        this.panelListUsuario.setVisible(select);
        this.panelCrearUsuario.setVisible(!select);

    }

    public void habilitarComponentesRegistroUsuario(boolean value) {

//            this.cmbTipoDocumento.setDisable(value);
        this.txtIdPersona.setDisable(value);
        this.txtPNombre.setDisable(value);
        this.txtSNombre.setDisable(value);
        this.txtPApellido.setDisable(value);
        this.txtSApellido.setDisable(value);
        this.txtFechaNacimiento.setDisable(value);
        this.cmbGenero.setDisable(value);
        this.cmbTipoUsuarioRegistro.setDisable(value);
        this.cmbRolesUsuario.setDisable(value);
        this.cmbDireccionSeccional.setDisable(value);
        this.txtUsername.setDisable(value);
        this.txtPassword1.setDisable(value);
        this.txtPassword2.setDisable(value);
    }

    private void guardarDatosUsuario(UserDto dto) {

        try {

            this.logicUsuarios.save(dto);
            MyScreen.exitMessage();
            this.loadDatatTableUsuarios();
            this.limpiarFormulario();

        } catch (HttpResponseException ex) {
            MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
        }
    }

    private void actualizarDatosUsuario(UserDto dto) {

        try {

            this.logicUsuarios.edit(dto);
            MyScreen.exitMessage();
            this.loadDatatTableUsuarios();

        } catch (HttpResponseException ex) {
            MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
        } catch (IllegalArgumentException iae) {
            MyScreen.errorMessage(iae.getMessage());
        }
    }

    private void setDatosComboDeaj() {

        try {
            List<DireccionSeccionalDto> list = this.logicUsuarios.getDeajs();
            this.cmbDireccionSeccional.getItems().clear();
            list.forEach(dto -> {
                this.cmbDireccionSeccional.getItems().add(dto.getDescripcionSeccional());
            });

        } catch (HttpResponseException ex) {
            MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
        }
    }

    @Override
    public void activarPrivilegiosModulo() {

        boolean create = this.logicUsuarios.isCreate();
        boolean update = this.logicUsuarios.isUpdate();
        boolean view = this.logicUsuarios.isView();
        boolean delete = this.logicUsuarios.isDelete();

        this.btnGuardarUsuario.setDisable(!create);
        this.btnCrearUsuario.setDisable(!create);

        this.btnEditarUsuario.setDisable(!update);
        this.btnActualizarUsuario.setDisable(!update);

        this.btnEliminarUsuario.setDisable(!delete);

        this.txtBuscarUsuario.setDisable(!view);
        this.tablaUsuarios.setDisable(!view);
    }

    private void setDatosCmbTipoUser(ComboBox cmb) {

        cmb.getItems().addAll(TipoUsuarioDto.getList());
    }

    private void setDatosComboRoles() {

        int index = this.cmbTipoUsuarioRegistro.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            TipoUsuarioDto tipo = TipoUsuarioDto.get(TipoUsuarioDto.getList()[index]);

            try {
                List<RolUsuarioDto> list = this.logicUsuarios.getListRolesByTipoUsuario(tipo);
                this.cmbRolesUsuario.getItems().clear();
                for (RolUsuarioDto rol : list) {
                    this.cmbRolesUsuario.getItems().add(rol.getNombreRol());
                }
            } catch (HttpResponseException ex) {
                MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
            }
        }

    }

    private void loadDatatTableUsuarios() {

        this.columnIdUsuario.setCellValueFactory(new PropertyValueFactory("id"));
        this.columnNombreUsuario.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.columnUsername.setCellValueFactory(new PropertyValueFactory("username"));
        this.columnTipoUsuario.setCellValueFactory(new PropertyValueFactory("tipo"));
        this.columnRolUsuario.setCellValueFactory(new PropertyValueFactory("rol"));
        this.columnSeccionalUsuario.setCellValueFactory(new PropertyValueFactory("seccional"));

        this.dataTableUsuarios = FXCollections.observableArrayList();

        try {

            List<UserDto> list = this.logicUsuarios.getListUsuarios();
            for (UserDto dto : list) {
                UsuarioTableDto dtoTable = new UsuarioTableDto(dto);
                this.dataTableUsuarios.add(dtoTable);
            }

            if (this.logicUsuarios.isDelete()) {

                this.btnEliminarUsuario.setDisable(!(this.dataTableUsuarios.size() > 0));

            } else {
                this.btnEliminarUsuario.setDisable(true);
            }
            if (this.logicUsuarios.isView()) {
                this.setFiltroTableUsuarios();
            }

        } catch (HttpResponseException ex) {
            MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
        }

    }

    private void setFiltroTableUsuarios() {
        FilteredList<UsuarioTableDto> filteredData = new FilteredList<>(this.dataTableUsuarios, p -> true);
        this.txtBuscarUsuario.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(dto -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (dto.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (dto.getUsername().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        SortedList<UsuarioTableDto> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(this.tablaUsuarios.comparatorProperty());
        this.tablaUsuarios.setItems(sortedData);
    }

    public PersonaDto leerPersonaFromPanel() {

        PersonaDto dto = new PersonaDto();
        dto.setFechaNacimiento(this.txtFechaNacimiento.getValue());
        dto.setGenero(this.cmbGenero.getSelectionModel().getSelectedItem().toString());
        String id = this.txtIdPersona.getText();
        if (id != null && !id.isBlank()) {
            dto.setIdPersona(Long.valueOf(this.txtIdPersona.getText()));
        }
        dto.setListContratosSuscritos(new ArrayList());
        dto.setNoDocumento(this.txtNoDocumento.getText());
        dto.setNombreEmpresa(null);
        int indexTipoDocumento = this.cmbTipoDocumento.getSelectionModel().getSelectedIndex();
        dto.setTipoDocumento(this.logicUsuarios.getListTipoDocumento().get(indexTipoDocumento));
        dto.setTipoPersona(TipoPersona.NATURAL.toString());
        dto.setpApellido(this.txtPApellido.getText());
        dto.setpNombre(this.txtPNombre.getText());
        dto.setsApellido(this.txtSApellido.getText());
        dto.setsNombre(this.txtSNombre.getText());
        return dto;

    }

    public UserDto leerUsuarioFromPanel() {

        UserDto usuario = new UserDto();
        String id = this.txtIdUsuario.getText();
        if (id != null && !id.isBlank()) {
            usuario.setId(Long.valueOf(this.txtIdUsuario.getText()));
        }
        usuario.setCreatedByUser(this.logicUsuarios.getSesionUser());
        usuario.setEstado(true);
        usuario.setPassword(this.txtPassword1.getText());
        int indexRol = this.cmbRolesUsuario.getSelectionModel().getSelectedIndex();
        usuario.setRolUsuario(this.logicUsuarios.getRoles().get(indexRol));
        usuario.setTipoUsuario(this.cmbTipoUsuarioRegistro.getSelectionModel().getSelectedItem().toString());
        usuario.setUserName(this.txtUsername.getText());
        int indexSeccional = this.cmbDireccionSeccional.getSelectionModel().getSelectedIndex();
        usuario.setDireccionSeccional(this.logicUsuarios.getDeaj(indexSeccional));
        usuario.setListContratosSupervisados(new ArrayList());
        usuario.setListInformesSupervision(new ArrayList());
        PersonaDto persona = this.leerPersonaFromPanel();
        usuario.setDatosPersona(persona);
        usuario.setEstado(false);
        return usuario;
    }

    private void setDatosComboTipoDocumento() {

        try {
            List<TipoDocumentoDto> list = this.logicUsuarios.getListTipoDocumento();
            for (TipoDocumentoDto dto : list) {
                this.cmbTipoDocumento.getItems().add(dto.getDescripcion());
            }

        } catch (HttpResponseException ex) {
            MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
        }

    }

    @Override
    public boolean validarEnvioFormulario() {
        boolean result = true;

        this.lbErrorPasswords.setText("");
        this.lbErrorPasswords.setVisible(false);

        if (!Utility.validateEmptyComponentCombo(this.cmbTipoDocumento, this.lbErrorTipoDocumento)) {
            result = false;
        }
        if (!Utility.validateEmptyComponentTextField(this.txtNoDocumento, this.lbErrorNoDocumento)) {
            result = false;
        }
        if (!Utility.validateEmptyComponentTextField(this.txtPNombre, this.lbErrorPNombre)) {
            result = false;
        }
        if (!Utility.validateEmptyComponentTextField(this.txtPApellido, this.lbErrorPApellido)) {
            result = false;
        }
        if (!Utility.validateEmptyComponentCombo(this.cmbGenero, this.lbErrorGenero)) {
            result = false;
        }
        if (!Utility.validateEmptyComponentDatePicker(this.txtFechaNacimiento, this.lbErrorFechaNacimiento)) {
            result = false;
        }
        if (!Utility.validateEmptyComponentCombo(this.cmbDireccionSeccional, this.lbErrorDeaj)) {
            result = false;
        }
        if (!Utility.validateEmptyComponentCombo(this.cmbTipoUsuarioRegistro, this.lbErrorTipoUsuario)) {
            result = false;
        }
        if (!Utility.validateEmptyComponentCombo(this.cmbRolesUsuario, this.lbErrorRolUsuario)) {
            result = false;
        }
        if (!Utility.validateEmptyComponentTextField(this.txtUsername, this.lbErrorUsername)) {
            result = false;
        }
        if (!Utility.validateEmptyComponentPasswordField(this.txtPassword1, this.lbErrorPassword1)) {
            result = false;
        }
        if (!Utility.validateEmptyComponentPasswordField(this.txtPassword2, this.lbErrorPassword2)) {
            result = false;
        }

        return result;
    }

    @Override
    public void activarDesactivarOpciones(boolean opt) {
        this.btnActualizarUsuario.setDisable(opt);
        this.btnGuardarUsuario.setDisable(!opt);
    }

    @Override
    public void limpiarFormulario() {
        this.cmbTipoDocumento.getSelectionModel().select(-1);
        this.txtIdPersona.setText(null);
        this.txtPNombre.setText(null);
        this.txtSNombre.setText(null);
        this.txtPApellido.setText(null);
        this.txtSApellido.setText(null);
        this.txtFechaNacimiento.setValue(null);
        this.cmbGenero.getSelectionModel().select(-1);
        this.cmbTipoUsuarioRegistro.getSelectionModel().select(-1);
        this.cmbRolesUsuario.getSelectionModel().select(-1);
        this.txtUsername.setText(null);
        this.txtPassword1.setText(null);
        this.txtPassword2.setText(null);
        this.txtIdUsuario.setText(null);
        this.txtNoDocumento.setText(null);
        this.lbErrorUsername2.setVisible(false);
        this.lbErrorPasswords.setVisible(false);

    }

}
