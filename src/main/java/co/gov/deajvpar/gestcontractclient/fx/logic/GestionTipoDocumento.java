/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.logic;

import co.gov.deajvpar.gestcontractclient.fx.data.CrudApiTipoDocumento;
import co.gov.deajvpar.gestcontractclient.fx.data.ICrudGeneric;
import co.gov.deajvpar.gestcontractclient.fx.dtos.TipoDocumentoDto;

/**
 *
 * @author Jairo F
 */
public class GestionTipoDocumento implements ICrudGeneric<TipoDocumentoDto> {
    
    private CrudApiTipoDocumento apiData = new CrudApiTipoDocumento();

    @Override
    public TipoDocumentoDto save(TipoDocumentoDto obj) {
        return this.apiData.save(obj);
    }

    @Override
    public TipoDocumentoDto edit(TipoDocumentoDto obj) {
       return this.apiData.edit(obj);
    }

    @Override
    public TipoDocumentoDto delete(TipoDocumentoDto obj) {
       return this.apiData.delete(obj);
    }

    @Override
    public TipoDocumentoDto[] getAll() {
       return this.apiData.getAll();
    }

    
}
