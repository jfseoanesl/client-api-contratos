/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.data;

import co.gov.deajvpar.gestcontractclient.fx.dtos.ModalidadDto;
import co.gov.deajvpar.gestcontractclient.fx.utility.UsedApis;

/**
 *
 * @author Jairo F
 */
public class CrudApiModalidad implements ICrudGeneric<ModalidadDto> {
    
    private CrudApiImp<ModalidadDto> api = new CrudApiImp();
    
    @Override
    public ModalidadDto save(ModalidadDto obj) {
        return this.api.post(obj, UsedApis.API_MODALIDAD_SAVE);
    }

    @Override
    public ModalidadDto edit(ModalidadDto obj) {
        return this.api.post(obj, UsedApis.API_MODALIDAD_EDIT);
    }

    @Override
    public ModalidadDto delete(ModalidadDto obj) {
        return this.api.post(obj, UsedApis.API_MODALIDAD_DELETE);
    }

    @Override
    public ModalidadDto[] getAll() {
        return this.api.get(ModalidadDto.class, UsedApis.API_MODALIDAD_GET_ALL);
    }
    
}
