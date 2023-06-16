/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.data;

import co.gov.deajvpar.gestcontractclient.fx.dtos.TipoDocumentoDto;
import co.gov.deajvpar.gestcontractclient.fx.utility.UsedApis;

/**
 *
 * @author Jairo F
 */
public class CrudApiTipoDocumento implements ICrudGeneric<TipoDocumentoDto>{

    private CrudApiImp<TipoDocumentoDto> api = new CrudApiImp();
    
    @Override
    public TipoDocumentoDto save(TipoDocumentoDto obj) {
        return null;
    }

    @Override
    public TipoDocumentoDto edit(TipoDocumentoDto obj) {
        return null;
    }

    @Override
    public TipoDocumentoDto delete(TipoDocumentoDto obj) {
        return null;
    }

    @Override
    public TipoDocumentoDto[] getAll() {
        return this.api.get(TipoDocumentoDto.class, UsedApis.API_TIPO_DOCUMENTO_GET_ALL);
    }
    
}
