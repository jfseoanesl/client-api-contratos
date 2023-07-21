/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.data;

import co.gov.deajvpar.gestcontractclient.fx.Exceptions.HttpResponseException;
import co.gov.deajvpar.gestcontractclient.fx.dtos.UserDto;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyGsonMapper;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyHttpApi;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyScreen;
import co.gov.deajvpar.gestcontractclient.fx.utility.StatusCode;
import co.gov.deajvpar.gestcontractclient.fx.utility.UsedApis;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.List;

/**
 *
 * @author Jairo F
 */
public class CrudApiSupervisor implements ICrudGeneric<UserDto> {
    
    private CrudApiImp<UserDto> api = new CrudApiImp();

    @Override
    public UserDto save(UserDto obj) {
        return this.api.post(obj, UsedApis.API_SUPERVISOR_SAVE);
    }

    @Override
    public UserDto edit(UserDto obj) {
        return this.api.post(obj, UsedApis.API_SUPERVISOR_EDIT);
    }

    @Override
    public UserDto delete(UserDto obj) {
        return this.api.post(obj, UsedApis.API_SUPERVISOR_DELETE);
    }

    @Override
    public UserDto[] getAll() {
        return this.api.get(UserDto.class, UsedApis.API_SUPERVISOR_GET_ALL);
    }
    
    public List<UserDto> getAll(UserDto dto) {
        
        List<UserDto> dtos;
        try {
            StatusCode status = MyHttpApi.jsonPostRequest2(UsedApis.API_SUPERVISOR_GET_ALL_DEAJ, dto);
            
            if (!status.statusOk()) {
                throw new HttpResponseException(status);
            }
            dtos = List.of(MyGsonMapper.get().fromJson(status.getResponse(), UserDto[].class));
            MyScreen.setStatus(status);
        } catch (UnirestException ex) {
            throw new HttpResponseException(ex.getMessage());
        }

        return dtos;
    }
    
}
