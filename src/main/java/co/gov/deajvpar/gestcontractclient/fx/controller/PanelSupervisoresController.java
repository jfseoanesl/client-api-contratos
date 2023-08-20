/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.controller;

import co.gov.deajvpar.gestcontractclient.fx.Exceptions.HttpResponseException;
import co.gov.deajvpar.gestcontractclient.fx.dtos.ContratoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.DireccionSeccionalDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.GeneroPersonaDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.ModuloDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.PersonaDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.RolUsuarioDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.TipoDocumentoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.TipoPersona;
import co.gov.deajvpar.gestcontractclient.fx.dtos.UserDto;
import co.gov.deajvpar.gestcontractclient.fx.logic.GestionFormPrincipal;
import co.gov.deajvpar.gestcontractclient.fx.logic.GestionSupervisor;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyScreen;
import co.gov.deajvpar.gestcontractclient.fx.utility.Utility;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Jairo F
 */
public class PanelSupervisoresController implements Initializable, IFormController {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox<DireccionSeccionalDto> cmbDeaj;

    @FXML
    private ComboBox cmbContratos;

    @FXML
    private Label lbNombreUsuario, lbRolUsuario, lbEstadoUsuario, lbNoContratos, lbDireccionSeccional;

    @FXML
    private Label lbErrorSupervisor, lbErrorContrato, lbErrorDesignacion, lbErrorEjecucion;

    @FXML
    private Button btnCrearSupervisor, btnEditarSupervisor, btnEliminarSupervisor, btnSupervisarContrato;

    @FXML
    private ListView<UserDto> listViewSupervisores;

    @FXML
    private DatePicker dpFechaDesignacion, dpFechaInicioEjecucion;

    @FXML
    private CheckBox checkSupervisar;

    @FXML
    private HBox panelSupervision, panelHbox;

    @FXML
    private VBox panelViewSupervisores, panelEditSupervisores, panelDetalle;

    @FXML
    private TextField txtBuscarUsuario, txtNoDocumento, txtIdUsuario, txtIdPersona;

    @FXML
    private TextField txtPNombre, txtSNombre, txtPApellido, txtSApellido, txtUsername;

    @FXML
    private ComboBox<TipoDocumentoDto> cmbTipoDocumento;

    @FXML
    private ComboBox cmbGenero;

    @FXML
    private ComboBox<RolUsuarioDto> cmbRolesUsuario;

    @FXML
    private ComboBox<DireccionSeccionalDto> cmbDireccionSeccional;

    @FXML
    private DatePicker txtFechaNacimiento;

    @FXML
    private Label lbErrorTipoDocumento, lbErrorNoDocumento, lbErrorPNombre, lbErrorSNombre;

    @FXML
    private Label lbErrorPApellido, lbErrorSApellido, lbErrorGenero, lbErrorFechaNacimiento;

    @FXML
    private Label lbErrorDeaj, lbErrorTipoUsuario, lbErrorRolUsuario, lbErrorUsername2;

    @FXML
    private Label lbTitle;

    @FXML
    private Button btnGuardarUsuario, btnActualizarUsuario, btnCancelarUsuario;

    @FXML
    private FontAwesomeIcon btnBuscarPersona;

    private GestionSupervisor logica = new GestionSupervisor();
    private int tipoValidacion; //0->supervisar , 1->crear

    @FXML
    private void actionOnBtnBuscarPersona(MouseEvent e) {
        TipoDocumentoDto tipo = this.cmbTipoDocumento.getSelectionModel().getSelectedItem();
        if (tipo != null && tipo.getId() != null) {

            String documento = this.txtNoDocumento.getText();
            PersonaDto dto = new PersonaDto();
            dto.setNoDocumento(documento);
            dto.setTipoDocumento(tipo);

            try {
                dto = this.logica.findPersona(dto);
                this.setDatosPersona(dto);
                this.habilitarComponentesRegistroUsuario(false);

            } catch (HttpResponseException ex) {
                MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
            }

        }
    }

    @FXML
    public void clickBtnEditar(ActionEvent e) {

        UserDto selected = this.listViewSupervisores.getItems().size() > 0 ? this.listViewSupervisores.getSelectionModel().getSelectedItem() : null;
        if (selected != null) {
            this.tipoValidacion = 1;
            this.activarDesactivarPaneles(false);
            this.lbTitle.setText("Actualizacion datos supervisor");
            this.activarDesactivarOpciones(false);
            this.limpiarFormulario();
            this.txtIdUsuario.setText(selected.getId().toString());
            this.txtNoDocumento.setText(selected.getDatosPersona().getNoDocumento());
            int index = 0;
            this.setDatosPersona(selected.getDatosPersona());

            if (selected.getDireccionSeccional() != null) {
                index = 0;
                for (DireccionSeccionalDto d : this.cmbDireccionSeccional.getItems()) {
                    if (d.getIdDireccion().equals(selected.getDireccionSeccional().getIdDireccion())) {
                        this.cmbDireccionSeccional.getSelectionModel().select(index);
                        break;
                    }
                    index++;
                }
            } else {
                this.cmbDireccionSeccional.getSelectionModel().select(-1);
            }

            this.txtUsername.setText(selected.getUserName());

            index = 0;
            for (RolUsuarioDto r : this.cmbRolesUsuario.getItems()) {
                if (r.getId().equals(selected.getRolUsuario().getId())) {
                    this.cmbRolesUsuario.getSelectionModel().select(index);
                    break;
                }
                index++;
            }
            this.habilitarComponentesRegistroUsuario(false);
            this.txtUsername.setDisable(true);
        }

    }

    @FXML
    public void clcikBtnCrear(ActionEvent e) {
        this.tipoValidacion = 1;
        this.activarDesactivarPaneles(false);
        this.lbTitle.setText("Registro de supervisor");
        this.limpiarFormulario();
        this.habilitarComponentesRegistroUsuario(true);
        this.activarDesactivarOpciones(true);

    }

    @FXML
    public void clickBtnVolver(ActionEvent e) {
        this.activarDesactivarPaneles(true);
        this.tipoValidacion = 0;
        try {
            this.logica.generateListViewSupervisores(listViewSupervisores, (DireccionSeccionalDto) this.cmbDeaj.getSelectionModel().getSelectedItem());
        } catch (HttpResponseException ex) {
            MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
        }
    }

    @FXML
    public void clickBotonSupervisar(ActionEvent e) {
        boolean validar = this.validarEnvioFormulario();

        if (validar) {
            LocalDate fechaDesignacion = this.dpFechaDesignacion.getValue();
            LocalDate fechaInicioEjecucion = this.dpFechaInicioEjecucion.getValue();
            try {
                this.logica.supervisarContrato(this.listViewSupervisores.getSelectionModel().getSelectedItem(), (ContratoDto) this.cmbContratos.getSelectionModel().getSelectedItem(), fechaDesignacion, fechaDesignacion);
                MyScreen.exitMessage();
                Utility.setModuloHBox(ModuloDto.CONTRATOS, this.panelHbox, GestionFormPrincipal.FXML_FORM_MODULE_CONTRATOS_GESTION_SUPERVISOR);

            } catch (HttpResponseException ex) {
                MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
            } catch (IOException ex) {
                MyScreen.errorMessage("Error al cargar modulo", ex.getMessage());
            }
        }

    }

    @FXML
    public void clickCheckSupervisar(ActionEvent e) {
        this.panelSupervision.setDisable(!this.checkSupervisar.isSelected());
    }

    @FXML
    public void clickBtnGuardar(ActionEvent e) {
        boolean validar = this.validarEnvioFormulario();
        if (validar) {

            try {
                this.logica.validarUsername(this.txtUsername.getText());
                this.lbErrorUsername2.setVisible(false);

            } catch (IllegalArgumentException ex) {
                this.lbErrorUsername2.setVisible(true);
                this.lbErrorUsername2.setText("(" + ex.getMessage() + ")");
                validar = false;
            }
            if (validar) {
                UserDto newUser = this.leerUsuarioFromPanel();
                try {
                    this.logica.guardarSupervisor(newUser);
                    MyScreen.exitMessage();
                    this.limpiarFormulario();
                    this.habilitarComponentesRegistroUsuario(true);
                    this.cmbTipoDocumento.requestFocus();

                } catch (HttpResponseException ex) {
                    MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
                }
            }
        }

    }

    @FXML
    public void clickBtnActualizar(ActionEvent e) {

        boolean validar = this.validarEnvioFormulario();
        if (validar) {
            UserDto newUser = this.leerUsuarioFromPanel();
            try {
                this.logica.actualizarSupervisor(newUser);
                MyScreen.exitMessage();
                this.limpiarFormulario();
                this.habilitarComponentesRegistroUsuario(true);
                this.cmbTipoDocumento.requestFocus();

            } catch (HttpResponseException ex) {
                MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
            }
        }

    }

    @FXML
    public void clickBtnEliminar(ActionEvent e) {

        UserDto selected = this.listViewSupervisores.getItems().size() > 0 ? this.listViewSupervisores.getSelectionModel().getSelectedItem() : null;
        if (selected != null) {

            Optional<ButtonType> result = MyScreen.confirmMessage(null, "Confirmacion", "Esta seguro de realizar la eliminacion?");
            if (result.get() == ButtonType.OK) {

                try {
                    this.logica.deleteSupervisor(selected);
                    MyScreen.exitMessage();
                    this.logica.generateListViewSupervisores(listViewSupervisores, (DireccionSeccionalDto) this.cmbDeaj.getSelectionModel().getSelectedItem());
                    //this.logica.loadContratos(this.cmbContratos, (DireccionSeccionalDto) this.cmbDeaj.getSelectionModel().getSelectedItem());

                } catch (HttpResponseException ex) {
                    MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
                }

            }

        }

    }

    @FXML
    public void clickComboDeaj(ActionEvent e) {
        try {
            this.logica.generateListViewSupervisores(listViewSupervisores, (DireccionSeccionalDto) this.cmbDeaj.getSelectionModel().getSelectedItem());
            this.logica.loadContratos(this.cmbContratos, (DireccionSeccionalDto) this.cmbDeaj.getSelectionModel().getSelectedItem());
        } catch (HttpResponseException ex) {
            MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.setDatosCmbGenero();
        try {
            this.logica.loadDeaj(this.cmbDeaj);
            this.logica.loadDeaj(this.cmbDireccionSeccional);
            this.logica.setComboTipoDocumento(this.cmbTipoDocumento);
            this.logica.setComboRolesSupervisor(this.cmbRolesUsuario);
            this.logica.generateListViewSupervisores(listViewSupervisores, (DireccionSeccionalDto) this.cmbDeaj.getSelectionModel().getSelectedItem());
            this.setDetallesSupervisor();
            this.logica.loadContratos(this.cmbContratos, (DireccionSeccionalDto) this.cmbDeaj.getSelectionModel().getSelectedItem());
        } catch (HttpResponseException ex) {
            MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
        }
        this.listViewSupervisores.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<UserDto>() {
            @Override
            public void changed(ObservableValue<? extends UserDto> ov, UserDto t, UserDto t1) {
                setDetallesSupervisor();
            }
        });
        this.tipoValidacion = 0;
        this.activarPrivilegiosModulo();
    }

    @Override
    public boolean validarEnvioFormulario() {
        boolean resultValidation = true;

        if (this.tipoValidacion == 0) {
            if (this.listViewSupervisores.getSelectionModel().getSelectedIndex() < 0) {
                resultValidation = false;
                this.lbErrorContrato.setVisible(false);
            } else {
                this.lbErrorContrato.setVisible(true);
            }

            boolean validate = Utility.validateEmptyComponentCombo(this.cmbContratos, this.lbErrorContrato);
            if (!validate) {
                resultValidation = false;
            }
            validate = Utility.validateEmptyComponentDatePicker(this.dpFechaDesignacion, this.lbErrorDesignacion);
            if (!validate) {
                resultValidation = false;
            }

            validate = Utility.validateEmptyComponentDatePicker(this.dpFechaInicioEjecucion, this.lbErrorEjecucion);
            if (!validate) {
                resultValidation = false;
            }
        } else {

            if (!Utility.validateEmptyComponentCombo(this.cmbTipoDocumento, this.lbErrorTipoDocumento)) {
                resultValidation = false;
            }
            if (!Utility.validateEmptyComponentTextField(this.txtNoDocumento, this.lbErrorNoDocumento)) {
                resultValidation = false;
            }
            if (!Utility.validateEmptyComponentTextField(this.txtPNombre, this.lbErrorPNombre)) {
                resultValidation = false;
            }
            if (!Utility.validateEmptyComponentTextField(this.txtPApellido, this.lbErrorPApellido)) {
                resultValidation = false;
            }
            if (!Utility.validateEmptyComponentCombo(this.cmbGenero, this.lbErrorGenero)) {
                resultValidation = false;
            }
            if (!Utility.validateEmptyComponentDatePicker(this.txtFechaNacimiento, this.lbErrorFechaNacimiento)) {
                resultValidation = false;
            }
            if (!Utility.validateEmptyComponentCombo(this.cmbDireccionSeccional, this.lbErrorDeaj)) {
                resultValidation = false;
            }
            if (!Utility.validateEmptyComponentCombo(this.cmbRolesUsuario, this.lbErrorRolUsuario)) {
                resultValidation = false;
            }
            if (!Utility.validateEmptyComponentTextField(this.txtUsername, this.lbErrorUsername2)) {
                resultValidation = false;
            }
        }

        return resultValidation;

    }

    @Override
    public void activarDesactivarOpciones(boolean opt) {

        this.btnGuardarUsuario.setDisable(!opt);
        this.btnActualizarUsuario.setDisable(opt);

        this.btnBuscarPersona.setDisable(!opt);
        this.txtUsername.setDisable(!opt);

    }

    @Override
    public void activarPrivilegiosModulo() {

        boolean create = this.logica.isCreate();
        boolean update = this.logica.isUpdate();
        boolean view = this.logica.isView();
        boolean delete = this.logica.isDelete();

        this.btnGuardarUsuario.setDisable(!create);
        this.btnCrearSupervisor.setDisable(!create);

        this.btnEditarSupervisor.setDisable(!update);
        this.btnActualizarUsuario.setDisable(!update);

        this.btnEliminarSupervisor.setDisable(!delete);

        this.listViewSupervisores.setDisable(!view);
        this.cmbDeaj.setDisable(!view);
        this.panelDetalle.setDisable(!view);
        
        this.checkSupervisar.setDisable(!view || !create);
        
        this.btnBuscarPersona.setDisable(!create);
    }

    @Override
    public void limpiarFormulario() {

        this.cmbTipoDocumento.getSelectionModel().select(-1);
        this.txtNoDocumento.setText(null);
        this.txtPNombre.setText(null);
        this.txtSNombre.setText(null);
        this.txtPApellido.setText(null);
        this.txtSApellido.setText(null);
        this.txtFechaNacimiento.setValue(null);
        this.cmbGenero.getSelectionModel().select(-1);
        this.cmbDireccionSeccional.getSelectionModel().select(-1);
        this.txtUsername.setText(null);
        this.txtIdPersona.setText(null);
        this.txtIdUsuario.setText(null);
        this.lbErrorUsername2.setVisible(false);

    }

    @Override
    public void activarDesactivarPaneles(boolean opt) {

        this.panelViewSupervisores.setVisible(opt);
        this.panelEditSupervisores.setVisible(!opt);

    }

    private void setDetallesSupervisor() {
        UserDto supervisor = null;
        supervisor = this.listViewSupervisores.getSelectionModel().getSelectedItem();
        if (supervisor == null) {
            this.lbNombreUsuario.setText(null);
            this.lbRolUsuario.setText(null);
            this.lbDireccionSeccional.setText(null);
            this.lbEstadoUsuario.setText(null);
            this.lbNoContratos.setText(null);
        } else {
            this.lbNombreUsuario.setText(supervisor.getUserName());
            this.lbRolUsuario.setText(supervisor.getRolUsuario().getNombreRol());
            this.lbDireccionSeccional.setText(supervisor.getDireccionSeccional().getDescripcionSeccional());
            this.lbEstadoUsuario.setText(supervisor.isEstado() ? "Inactivo" : "Activo");
            this.lbNoContratos.setText("" + supervisor.getListContratosSupervisados().size());
        }

    }

    private void setDatosPersona(PersonaDto persona) {

        if (persona.getIdPersona() != null) {
            this.txtIdPersona.setText(String.valueOf(persona.getIdPersona()));
        } else {
            this.txtIdPersona.setText(null);
        }
        int index = 0;
        for (TipoDocumentoDto t : this.cmbTipoDocumento.getItems()) {
            if (t.getId().equals(persona.getTipoDocumento().getId())) {
                this.cmbTipoDocumento.getSelectionModel().select(index);
                break;
            }
            index++;
        }

        this.txtPNombre.setText(persona.getpNombre());
        this.txtSNombre.setText(persona.getsNombre());
        this.txtPApellido.setText(persona.getpApellido());
        this.txtSApellido.setText(persona.getsApellido());
        this.txtFechaNacimiento.setValue(persona.getFechaNacimiento());
        index = -1;
        if (persona.getGenero() != null) {
            index = GeneroPersonaDto.getIndex(persona.getGenero());
        }
        this.cmbGenero.getSelectionModel().select(index);

    }

    private void setDatosCmbGenero() {

        GeneroPersonaDto[] generos = GeneroPersonaDto.values();
        this.cmbGenero.getItems().addAll(Arrays.asList(generos));
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
        dto.setTipoDocumento(this.cmbTipoDocumento.getSelectionModel().getSelectedItem());
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
        usuario.setCreatedByUser(this.logica.getSesionUser());
        usuario.setEstado(true);
        usuario.setPassword(this.txtNoDocumento.getText().trim());
        usuario.setRolUsuario(this.cmbRolesUsuario.getSelectionModel().getSelectedItem());
        usuario.setTipoUsuario(usuario.getRolUsuario().getTipoUsuario());
        usuario.setUserName(this.txtUsername.getText());
        usuario.setDireccionSeccional(this.cmbDireccionSeccional.getSelectionModel().getSelectedItem());
        usuario.setListContratosSupervisados(new ArrayList());
        usuario.setListInformesSupervision(new ArrayList());
        PersonaDto persona = this.leerPersonaFromPanel();
        usuario.setDatosPersona(persona);
        usuario.setEstado(false);
        return usuario;
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
        this.cmbRolesUsuario.setDisable(value);
        this.cmbDireccionSeccional.setDisable(value);
        this.txtUsername.setDisable(value);

    }
}
