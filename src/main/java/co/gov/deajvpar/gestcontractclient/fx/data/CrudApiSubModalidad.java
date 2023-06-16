/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.data;

import co.gov.deajvpar.gestcontractclient.fx.dtos.SubModalidadDto;
import co.gov.deajvpar.gestcontractclient.fx.utility.UsedApis;

/**
 *
 * @author Jairo F
 */
public class CrudApiSubModalidad implements ICrudGeneric<SubModalidadDto> {
    
    private final CrudApiImp<SubModalidadDto> api = new CrudApiImp();
     
    @Override
    public SubModalidadDto save(SubModalidadDto obj) {
        return this.api.post(obj, UsedApis.API_SUBMODALIDAD_SAVE);
    }

    @Override
    public SubModalidadDto edit(SubModalidadDto obj) {
        return this.api.post(obj, UsedApis.API_SUBMODALIDAD_EDIT);
    }

    @Override
    public SubModalidadDto delete(SubModalidadDto obj) {
        return this.api.post(obj, UsedApis.API_SUBMODALIDAD_DELETE);
    }

    @Override
    public SubModalidadDto[] getAll() {
        return this.api.get(SubModalidadDto.class, UsedApis.API_SUBMODALIDAD_GET_ALL);
    }
    
}
