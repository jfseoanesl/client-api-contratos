/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.logic;

import at.favre.lib.crypto.bcrypt.BCrypt;
import co.gov.deajvpar.gestcontractclient.fx.data.CrudApiDeaj;
import co.gov.deajvpar.gestcontractclient.fx.data.CrudApiPersona;
import co.gov.deajvpar.gestcontractclient.fx.data.CrudApiUsuarios;
import co.gov.deajvpar.gestcontractclient.fx.data.ICrudGeneric;
import co.gov.deajvpar.gestcontractclient.fx.dtos.ContratoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.DireccionSeccionalDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.PersonaDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.RolUsuarioDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUserDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUsuarioSingleton;
import co.gov.deajvpar.gestcontractclient.fx.dtos.TipoDocumentoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.TipoUsuarioDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.UserDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.UsuarioDto;
import co.gov.deajvpar.gestcontractclient.fx.utility.Utility;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Jairo F
 */
public class GestionUsuarios implements ICrudGeneric<UserDto> {

    private final CrudApiUsuarios apiData;
    private final GestionTipoDocumento logicTipoDocumento;
    private final GestionRoles logicRolesUsuario;
    private RolUsuarioDto[] listRoles;
    private UserDto[] listUsuarios;
    private DireccionSeccionalDto[] listDeaj;
    private final TipoDocumentoDto[] listTipoDocumento;
    private final CrudApiDeaj apiDeaj = new CrudApiDeaj();
    private final CrudApiPersona apiPersona = new CrudApiPersona();
    private final SesionUserDto sesion;

    public GestionUsuarios() {
        this.apiData = new CrudApiUsuarios();
        this.logicTipoDocumento = new GestionTipoDocumento();
        this.listTipoDocumento = this.logicTipoDocumento.getAll();
        this.logicRolesUsuario = new GestionRoles();
        this.sesion = SesionUsuarioSingleton.get();
        this.sesion.setPrivilegiosModulo();

    }

    @Override
    public UserDto save(UserDto obj) {
        Utility.encryptPassword(obj);
        return this.apiData.save(obj);
    }

    @Override
    public UserDto edit(UserDto obj) {
        UserDto dto = this.getUsuario(obj.getId());
        if(!dto.getPassword().equals(obj.getPassword())){
            Utility.encryptPassword(obj);
        }
        
        if(TipoUsuarioDto.get(obj.getTipoUsuario())==TipoUsuarioDto.SUPERVISOR){
            if(!Objects.equals(dto.getDireccionSeccional().getIdDireccion(), obj.getDireccionSeccional().getIdDireccion())){
               
                if(obj.getListContratosSupervisados().size()>0){
                    throw new IllegalArgumentException("No es posible hacer el cambio de Direccion Seccional, el usuario tiene contratos asociados");
                }
                else if(obj.getListInformesSupervision().size()>0){
                    throw new IllegalArgumentException("No es posible hacer el cambio de Direccion Seccional, el usuario tiene informes de supervision asociados\"");
                }
            }
        }
        return this.apiData.edit(obj);
    }

    @Override
    public UserDto delete(UserDto obj) {
        return this.apiData.delete(obj);
    }

    @Override
    public UserDto[] getAll() {
        return this.apiData.getAll();
    }

    public UserDto[] getAllByRol(RolUsuarioDto rol) {
        return this.apiData.getAllByRol(rol);
    }

    public List<TipoDocumentoDto> getListTipoDocumento() {

        return List.of(this.listTipoDocumento);
    }

    public List<RolUsuarioDto> getListRolesByTipoUsuario(TipoUsuarioDto dto) {

        this.listRoles = this.logicRolesUsuario.getRolTipoUsuario(dto);
        return List.of(this.listRoles);

    }

    public List<RolUsuarioDto> getRoles() {

        return List.of(this.listRoles);

    }

    public List<DireccionSeccionalDto> getDeajs() {
        this.listDeaj = this.apiDeaj.getAll();
        return List.of(this.listDeaj);
    }

    public DireccionSeccionalDto getDeaj(int index) {
        return this.listDeaj[index];
    }

    public List<UserDto> getListUsuarios() {

        this.listUsuarios = this.apiData.getAll();
        return List.of(this.listUsuarios);
    }

    public UserDto getUsuario(Long id) {

        for (UserDto user : this.getListUsuarios()) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public PersonaDto findPersona(PersonaDto persona) {
        return this.apiPersona.findByDocumento(persona);
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
    
    public UsuarioDto getSesionUser(){
        return this.sesion.getUser();
    }
    
    
    public void validarPasswordsUsuario(String p1, String p2){
        
        if(!p1.equals(p2)){
            throw new IllegalArgumentException("Los password no coinciden");
        }
    
    }
    
    public void validarUsername(String username){
        
        if(this.apiData.validateUsername(username)){
            throw new IllegalArgumentException("El Username no esta disponible");
        }
    
    }
    
    
    

}
