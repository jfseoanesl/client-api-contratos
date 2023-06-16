/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.data;

import co.gov.deajvpar.gestcontractclient.fx.dtos.RolUsuarioDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.UserDto;
import co.gov.deajvpar.gestcontractclient.fx.utility.UsedApis;

/**
 *
 * @author Jairo F
 */
public class CrudApiUsuarios implements ICrudGeneric<UserDto> {

    private final CrudApiImp<UserDto> api = new CrudApiImp();
    
    
    @Override
    public UserDto save(UserDto obj) {
        return this.api.post(obj, UsedApis.API_USER_SAVE);
    }

    @Override
    public UserDto edit(UserDto obj) {
       return this.api.post(obj, UsedApis.API_USER_EDIT);
    }

    @Override
    public UserDto delete(UserDto obj) {
        return this.api.post(obj, UsedApis.API_USER_DELETE);
    }

    @Override
    public UserDto[] getAll() {
        return this.api.get(UserDto.class, UsedApis.API_USER_GET_ALL);
    }
    
    public UserDto[] getAllByRol(RolUsuarioDto rol) {
        return this.api.postList(UserDto.class, rol, UsedApis.API_USER_GET_ALL_BY_ROL);
    }
    
    public boolean validateUsername(String username){
        UserDto user = new UserDto();
        user.setUserName(username);
        user = this.api.post(user, UsedApis.API_USER_FIND);
        return user.getId()!=null;
    }
    
}
