/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.data;

import co.gov.deajvpar.gestcontractclient.fx.dtos.AnotacionContratoDto;
import co.gov.deajvpar.gestcontractclient.fx.utility.UsedApis;

/**
 *
 * @author Jairo F
 */
public class CrudApiAnotacion implements ICrudGeneric<AnotacionContratoDto> {
    
    private CrudApiImp<AnotacionContratoDto> api = new CrudApiImp();

    @Override
    public AnotacionContratoDto save(AnotacionContratoDto obj) {
        return this.api.post(obj, UsedApis.API_ANOTACION_SAVE);
    }

    @Override
    public AnotacionContratoDto edit(AnotacionContratoDto obj) {
        return this.api.post(obj, UsedApis.API_ANOTACION_EDIT);
    }

    @Override
    public AnotacionContratoDto delete(AnotacionContratoDto obj) {
        return this.api.post(obj, UsedApis.API_ANOTACION_DELETE);
    }

    public AnotacionContratoDto[] getAll(AnotacionContratoDto dto) {

        return this.api.postList(AnotacionContratoDto.class, dto, UsedApis.API_ANOTACION_LIST);

    }

    @Override
    public AnotacionContratoDto[] getAll() {
        return null;
    }

}