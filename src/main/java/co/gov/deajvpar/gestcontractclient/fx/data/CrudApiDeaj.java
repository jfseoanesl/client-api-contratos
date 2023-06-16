/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.data;
import co.gov.deajvpar.gestcontractclient.fx.dtos.DireccionSeccionalDto;
import co.gov.deajvpar.gestcontractclient.fx.utility.UsedApis;

/**
 *
 * @author Jairo F
 */
public class CrudApiDeaj implements ICrudGeneric<DireccionSeccionalDto> {
    private final CrudApiImp<DireccionSeccionalDto> api = new CrudApiImp();
       
    @Override
    public DireccionSeccionalDto save(DireccionSeccionalDto obj) {
        return this.api.post(obj, UsedApis.API_DEAJ_SAVE);
    }

    @Override
    public DireccionSeccionalDto edit(DireccionSeccionalDto obj) {
        return this.api.post(obj,UsedApis.API_DEAJ_EDIT);
    }

    @Override
    public DireccionSeccionalDto delete(DireccionSeccionalDto obj) {
       return this.api.post(obj, UsedApis.API_DEAJ_DELETE);
    }

    @Override
    public DireccionSeccionalDto[] getAll() {
        return this.api.get(DireccionSeccionalDto.class, UsedApis.API_DEAJ_GET_ALL);
    }
}
