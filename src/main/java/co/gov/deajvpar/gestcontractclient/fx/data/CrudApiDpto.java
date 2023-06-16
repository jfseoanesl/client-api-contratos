/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.data;

import co.gov.deajvpar.gestcontractclient.fx.Exceptions.HttpResponseException;
import co.gov.deajvpar.gestcontractclient.fx.dtos.DptoDto;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyGsonMapper;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyHttpApi;
import co.gov.deajvpar.gestcontractclient.fx.utility.StatusCode;
import co.gov.deajvpar.gestcontractclient.fx.utility.UsedApis;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 *
 * @author Jairo F
 */
public class CrudApiDpto implements ICrudGeneric<DptoDto> {

    private CrudApiImp<DptoDto> api = new CrudApiImp();
    
    @Override
    public DptoDto save(DptoDto obj)  {
        return null;
    }

    @Override
    public DptoDto edit(DptoDto obj) {
        return null;
    }

    @Override
    public DptoDto delete(DptoDto obj)  {
        return null;
    }

    @Override
    public DptoDto[] getAll()  {
            return this.api.get(DptoDto.class, UsedApis.API_DPTO_GET_ALL);
    }
    
}
