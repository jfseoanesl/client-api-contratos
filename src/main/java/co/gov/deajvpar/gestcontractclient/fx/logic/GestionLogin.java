/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.logic;

import co.gov.deajvpar.gestcontractclient.fx.Exceptions.HttpResponseException;
import co.gov.deajvpar.gestcontractclient.fx.dtos.LoginDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUserDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUsuarioSingleton;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyGsonMapper;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyHttpApi;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyScreen;
import co.gov.deajvpar.gestcontractclient.fx.utility.StatusCode;
import co.gov.deajvpar.gestcontractclient.fx.utility.UsedApis;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 *
 * @author Jairo F
 */
public class GestionLogin {

    public GestionLogin() {
    }
    
    
    
    public void login(LoginDto dto){
    
        try {
            StatusCode status = MyHttpApi.jsonPostRequest2(UsedApis.API_LOGIN, dto);
            if (!status.statusOk()) {
                throw new HttpResponseException(status);
            }
            SesionUserDto sesion = MyGsonMapper.get().fromJson(status.getResponse(), SesionUserDto.class);
            SesionUsuarioSingleton.set(sesion);
            MyScreen.setStatus(status);
            
        } catch (UnirestException ex) {
            throw new HttpResponseException(ex.getMessage());
        }
    }
    
    public boolean logicSuccess(){
        return SesionUsuarioSingleton.get().isEstado();
    }
    
}
