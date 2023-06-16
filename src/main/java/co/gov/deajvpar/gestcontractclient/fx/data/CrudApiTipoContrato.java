/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.data;

import co.gov.deajvpar.gestcontractclient.fx.dtos.TipoContratoDto;
import co.gov.deajvpar.gestcontractclient.fx.utility.UsedApis;

/**
 *
 * @author Jairo F
 */
public class CrudApiTipoContrato implements ICrudGeneric<TipoContratoDto> {

    private CrudApiImp<TipoContratoDto> api = new CrudApiImp();
    
    @Override
    public TipoContratoDto save(TipoContratoDto obj) {
        return this.api.post(obj, UsedApis.API_TIPO_CONTRATO_SAVE);
    }

    @Override
    public TipoContratoDto edit(TipoContratoDto obj) {
        return this.api.post(obj, UsedApis.API_TIPO_CONTRATO_EDIT);
    }

    @Override
    public TipoContratoDto delete(TipoContratoDto obj) {
        return this.api.post(obj, UsedApis.API_TIPO_CONTRATO_DELETE);
    }

    @Override
    public TipoContratoDto[] getAll() {
        return this.api.get(TipoContratoDto.class, UsedApis.API_TIPO_CONTRATO_GET_ALL);
    }
    
}
