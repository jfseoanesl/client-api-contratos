/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.logic;

import co.gov.deajvpar.gestcontractclient.fx.Exceptions.HttpResponseException;
import co.gov.deajvpar.gestcontractclient.fx.data.CrudApiContrato;
import co.gov.deajvpar.gestcontractclient.fx.data.CrudApiDeaj;
import co.gov.deajvpar.gestcontractclient.fx.data.CrudApiPersona;
import co.gov.deajvpar.gestcontractclient.fx.data.CrudApiSupervisor;
import co.gov.deajvpar.gestcontractclient.fx.data.CrudApiUsuarios;
import co.gov.deajvpar.gestcontractclient.fx.dtos.ContratoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.DireccionSeccionalDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.EstadoContratoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.GeneroPersonaDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.PersonaDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.RolUsuarioDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUserDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUsuarioSingleton;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SupervisionContratoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.TipoDocumentoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.TipoUsuarioDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.UserDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.UsuarioDto;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyScreen;
import co.gov.deajvpar.gestcontractclient.fx.utility.Utility;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.util.StringConverter;

/**
 *
 * @author Jairo F
 */
public class GestionSupervisor {

    private SesionUserDto sesion;
    private CrudApiDeaj apideaj;
    private final CrudApiSupervisor apiSupervisor;
    private final CrudApiContrato apiContrato;
    private final GestionTipoDocumento logicTipoDocumento;
    private final GestionRoles logicRolesUsuario;
    private final CrudApiPersona apiPersona;
    private final CrudApiUsuarios apiUsuario;

    public GestionSupervisor() {

        this.sesion = SesionUsuarioSingleton.get();
        this.apideaj = new CrudApiDeaj();
        this.apiSupervisor = new CrudApiSupervisor();
        this.apiContrato = new CrudApiContrato();
        this.logicTipoDocumento = new GestionTipoDocumento();
        this.logicRolesUsuario = new GestionRoles();
        this.apiPersona = new CrudApiPersona();
        this.apiUsuario = new CrudApiUsuarios();

    }

    public void loadDeaj(ComboBox<DireccionSeccionalDto> comboControl) {
        try {
            ObservableList<DireccionSeccionalDto> items = FXCollections.observableArrayList();
            if (this.sesion.getUserDeaj() == null) {
                items.addAll(this.apideaj.getAll());
            } else {

                items.add(this.sesion.getUserDeaj());
            }
            comboControl.getItems().clear();
            comboControl.getItems().addAll(items);
            if (items.size() > 0) {
                comboControl.getSelectionModel().select(0);
            }
        } catch (HttpResponseException ex) {
            MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
        }
    }

    public void loadContratos(ComboBox<ContratoDto> comboControl, DireccionSeccionalDto deaj) {

        ObservableList<ContratoDto> items = FXCollections.observableArrayList();
        items.addAll(this.getListContrato(deaj, EstadoContratoDto.CELEBRADO));
        comboControl.getItems().clear();
        comboControl.getItems().addAll(items);

    }

    public List<UserDto> getListSupervisores(DireccionSeccionalDto deaj) throws HttpResponseException {
        UserDto dto = new UserDto();
        dto.setDireccionSeccional(deaj);
        List<UserDto> list;
        list = this.apiSupervisor.getAll(dto);

        return list;
    }

    public List<ContratoDto> getListContrato(DireccionSeccionalDto deaj, EstadoContratoDto estado) throws HttpResponseException {

        List<ContratoDto> list;

        ContratoDto dto = new ContratoDto();
        dto.setDireccion(deaj);
        dto.setEstadoContrato(estado);
        list = List.of(this.apiContrato.getAllByDeaj(dto));

        return list;
    }

    public void supervisarContrato(UserDto supervisor, ContratoDto contrato, LocalDate designacion, LocalDate ejecucion) throws HttpResponseException {
        SupervisionContratoDto supervision = new SupervisionContratoDto(contrato.getId(), supervisor.getId(), designacion, ejecucion);
        supervision.setIdUserCreated(this.sesion.getUser().getId());
        this.apiContrato.supervisarContrato(supervision);
    }

    public void generateListViewSupervisores(ListView<UserDto> listView, DireccionSeccionalDto deaj) throws HttpResponseException {

        String PATH_IMAGES = "co/gov/deajvpar/gestcontractclient/images/";
        String PATH_SUPERVISOR = PATH_IMAGES + "supervisor2.png";
        String PATH_SUPERVISORA = PATH_IMAGES + "supervisora.png";
        Image SUPERVISOR = new Image(PATH_SUPERVISOR);
        Image SUPERVISORA = new Image(PATH_SUPERVISORA);

        Image[] listOfImages = {SUPERVISOR, SUPERVISORA};
        List<UserDto> list = this.getListSupervisores(deaj);
        ObservableList<UserDto> items = FXCollections.observableArrayList(list);
        listView.setItems(items);
//        System.out.println(items.size());
        if (listView.getItems().size() > 0) {
            listView.setCellFactory(param -> {
                return new ListCell<UserDto>() {
                    private final ImageView imageView = new ImageView();

                    @Override
                    public void updateItem(UserDto supervisor, boolean empty) {
                        super.updateItem(supervisor, empty);
                        if (empty) {
                            setText(null);
                            setGraphic(null);
                        } else {

                            if (supervisor.getDatosPersona().getGenero().trim().equals(GeneroPersonaDto.MASCULINO.toString())) {
                                imageView.setImage(listOfImages[0]);
                            } else {
                                imageView.setImage(listOfImages[1]);
                            }

                            setText(" " + supervisor.getDataSupervisor());
                            setGraphic(imageView);         // setGraphic HBox
                        }
                    }
                };
            });
        }
        if (listView.getItems().size() > 0) {
            listView.getSelectionModel().select(0);
        }
        listView.setBackground(Background.EMPTY);

    }

    public void setComboTipoDocumento(ComboBox<TipoDocumentoDto> combo) throws HttpResponseException {

        TipoDocumentoDto array[] = this.logicTipoDocumento.getAll();
        ObservableList<TipoDocumentoDto> items = FXCollections.observableArrayList();
        items.addAll(array);
        combo.getItems().clear();
        combo.getItems().addAll(items);

    }

    public void setComboRolesSupervisor(ComboBox<RolUsuarioDto> combo) throws HttpResponseException {

        RolUsuarioDto array[] = this.logicRolesUsuario.getRolTipoUsuario(TipoUsuarioDto.SUPERVISOR);
        ObservableList<RolUsuarioDto> items = FXCollections.observableArrayList();
        items.addAll(array);
        combo.getItems().clear();
        combo.getItems().addAll(items);

    }

    public void guardarSupervisor(UserDto dto) throws HttpResponseException {

        Utility.encryptPassword(dto);
        this.apiSupervisor.save(dto);

    }

    public void actualizarSupervisor(UserDto dto) throws HttpResponseException {

        this.apiSupervisor.edit(dto);

    }
    
    public void deleteSupervisor(UserDto dto) throws HttpResponseException {

        this.apiSupervisor.delete(dto);

    }

    public UsuarioDto getSesionUser() {
        return this.sesion.getUser();
    }

    public PersonaDto findPersona(PersonaDto persona) {

        return this.apiPersona.findByDocumento(persona);

    }

    public void validarUsername(String username) {

        if (this.apiUsuario.validateUsername(username)) {
            throw new IllegalArgumentException("El Username no esta disponible");
        }

    }

     /**
     * @return the create
     */
    public boolean isCreate() {
        return this.sesion.isCreate();
    }

    /**
     * @return the update
     */
    public boolean isUpdate() {
        return this.sesion.isUpdate();
    }

    /**
     * @return the delete
     */
    public boolean isDelete() {
        return this.sesion.isDelete();
    }

    /**
     * @return the view
     */
    public boolean isView() {
        return this.sesion.isView();
    }
}
