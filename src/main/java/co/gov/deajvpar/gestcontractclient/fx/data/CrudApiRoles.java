/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.data;

import co.gov.deajvpar.gestcontractclient.fx.dtos.RolUsuarioDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.TipoUsuarioDto;
import co.gov.deajvpar.gestcontractclient.fx.utility.UsedApis;

/**
 *
 * @author Jairo F
 */
public class CrudApiRoles implements ICrudGeneric<RolUsuarioDto>{
    
    private CrudApiImp<RolUsuarioDto> api = new CrudApiImp();
    @Override
    public RolUsuarioDto save(RolUsuarioDto obj) {
        return this.api.post(obj, UsedApis.API_ROL_SAVE);
    }

    @Override
    public RolUsuarioDto edit(RolUsuarioDto obj) {
        return this.api.post(obj, UsedApis.API_ROL_EDIT);
    }

    @Override
    public RolUsuarioDto delete(RolUsuarioDto obj) {
        return this.api.post(obj, UsedApis.API_ROL_DELETE);
    }

    @Override
    public RolUsuarioDto[] getAll() {
        return this.api.get(RolUsuarioDto.class,UsedApis.API_ROL_GET_ALL);
    }
    
    public RolUsuarioDto[] getRolesTipoUsuario(TipoUsuarioDto dto) {
        return this.api.postList(RolUsuarioDto.class,dto, UsedApis.API_ROL_GET_ALL_TIPO);
    }
    
}
