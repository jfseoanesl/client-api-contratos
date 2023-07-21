/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.data;

import co.gov.deajvpar.gestcontractclient.fx.Exceptions.HttpResponseException;
import co.gov.deajvpar.gestcontractclient.fx.dtos.ContratoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SupervisionContratoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SuscripcionContratoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.chart.ChartDto;
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
public class CrudApiContrato implements ICrudGeneric<ContratoDto> {

    private CrudApiImp<ContratoDto> api = new CrudApiImp();

    @Override
    public ContratoDto save(ContratoDto obj) {
        return this.api.post(obj, UsedApis.API_CONTRATO_SAVE);
    }

    @Override
    public ContratoDto edit(ContratoDto obj) {
        return this.api.post(obj, UsedApis.API_CONTRATO_EDIT);
    }

    @Override
    public ContratoDto delete(ContratoDto obj) {
        return this.api.post(obj, UsedApis.API_CONTRATO_DELETE);
    }

    public ContratoDto buscar(ContratoDto obj) {
        return this.api.post(obj, UsedApis.API_CONTRATO_BUSCAR);
    }

    @Override
    public ContratoDto[] getAll() {
        return this.api.get(ContratoDto.class, UsedApis.API_CONTRATO_GET_ALL);
    }

    public ContratoDto[] getAllByDeaj(ContratoDto dto) {

        return this.api.postList(ContratoDto.class, dto, UsedApis.API_CONTRATO_GET_ALL_BY_DEAJ);

    }

    public ContratoDto suscribirContrato(SuscripcionContratoDto data) {
        ContratoDto dto;
        try {
            StatusCode status = MyHttpApi.jsonPostRequest2(UsedApis.API_CONTRATO_SUSCRIBIR, data);

            if (!status.statusOk()) {
                throw new HttpResponseException(status);
            }
            dto = MyGsonMapper.get().fromJson(status.getResponse(), ContratoDto.class);
            MyScreen.setStatus(status);
        } catch (UnirestException ex) {
            throw new HttpResponseException(ex.getMessage());
        }

        return dto;
    }

    public ContratoDto supervisarContrato(SupervisionContratoDto data) {
        ContratoDto dto;
        try {
            StatusCode status = MyHttpApi.jsonPostRequest2(UsedApis.API_CONTRATO_SUPERVISAR, data);

            if (!status.statusOk()) {
                throw new HttpResponseException(status);
            }
            dto = MyGsonMapper.get().fromJson(status.getResponse(), ContratoDto.class);
            MyScreen.setStatus(status);
        } catch (UnirestException ex) {
            throw new HttpResponseException(ex.getMessage());
        }

        return dto;
    }
    
    public String[] report(String api){
        
        String[] data=null;
        try {
            StatusCode status = MyHttpApi.jsonGetRequest2(api);
            if (!status.statusOk()) {
                throw new HttpResponseException(status);
            }
            
            data = MyGsonMapper.get().fromJson(status.getResponse(), String[].class);

        } catch (UnirestException ex) {
            
            throw new HttpResponseException(ex.getMessage());
        }

        return data;
        
    
    }
    
    public ChartDto[] reportChartDto(String api){
        
        ChartDto[] data=null;
        try {
            StatusCode status = MyHttpApi.jsonGetRequest2(api);
            if (!status.statusOk()) {
                throw new HttpResponseException(status);
            }
            
            data = MyGsonMapper.get().fromJson(status.getResponse(), ChartDto[].class);

        } catch (UnirestException ex) {
            
            throw new HttpResponseException(ex.getMessage());
        }

        return data;
        
    
    }

}
