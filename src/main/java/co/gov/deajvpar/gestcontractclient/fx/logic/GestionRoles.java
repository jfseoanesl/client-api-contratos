/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.logic;

import co.gov.deajvpar.gestcontractclient.fx.data.CrudApiRoles;
import co.gov.deajvpar.gestcontractclient.fx.data.CrudApiUsuarios;
import co.gov.deajvpar.gestcontractclient.fx.data.ICrudGeneric;
import co.gov.deajvpar.gestcontractclient.fx.dtos.RolUsuarioDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUserDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUsuarioSingleton;
import co.gov.deajvpar.gestcontractclient.fx.dtos.TipoUsuarioDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.UserDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.UsuarioDto;
import java.util.List;

/**
 *
 * @author Jairo F
 */
public class GestionRoles implements ICrudGeneric<RolUsuarioDto> {
    
    private CrudApiRoles apiData;
    private SesionUserDto sesion;
    private RolUsuarioDto[] listRoles;
    private CrudApiUsuarios logicUsuarios;

    public GestionRoles() {
        this.apiData = new CrudApiRoles();
        this.sesion=SesionUsuarioSingleton.get();
        this.sesion.setPrivilegiosModulo();
        this.logicUsuarios = new CrudApiUsuarios ();
    }
    
    
    @Override
    public RolUsuarioDto save(RolUsuarioDto obj) {
        return this.apiData.save(obj);
    }

    @Override
    public RolUsuarioDto edit(RolUsuarioDto obj) {
        return this.apiData.edit(obj);
    }

    @Override
    public RolUsuarioDto delete(RolUsuarioDto obj) {
        return this.apiData.delete(obj);
    }

    @Override
    public RolUsuarioDto[] getAll() {
        this.setListRoles(this.apiData.getAll());
        return this.getListRoles();
    }
    
    
    public RolUsuarioDto[] getRolTipoUsuario(TipoUsuarioDto dto) {
        this.setListRoles(this.apiData.getRolesTipoUsuario(dto));
        return this.getListRoles();
    }
    
    public UsuarioDto getSesionUser(){
        return this.sesion.getUser();
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
    
    public RolUsuarioDto GetRolUsuarioArray(Long id) {
        for (RolUsuarioDto rol : this.getListRoles()) {
            if (rol.getId().equals(id)) {
                return rol;
            }
        }
        return null;
    }

    /**
     * @return the listRoles
     */
    public RolUsuarioDto[] getListRoles() {
        return listRoles;
    }

    /**
     * @param listRoles the listRoles to set
     */
    public void setListRoles(RolUsuarioDto[] listRoles) {
        this.listRoles = listRoles;
    }
    
    
    public List<UserDto> listUserbyRol(RolUsuarioDto dto){
        return List.of(this.logicUsuarios.getAllByRol(dto));
    }
    
}
