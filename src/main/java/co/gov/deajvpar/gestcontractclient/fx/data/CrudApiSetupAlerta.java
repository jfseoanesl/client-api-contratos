/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.data;

import co.gov.deajvpar.gestcontractclient.fx.dtos.SetupAlertaDto;
import co.gov.deajvpar.gestcontractclient.fx.utility.UsedApis;

/**
 *
 * @author Jairo F
 */
public class CrudApiSetupAlerta implements ICrudGeneric<SetupAlertaDto> {
    
    private CrudApiImp<SetupAlertaDto> api = new CrudApiImp();
    
    @Override
    public SetupAlertaDto save(SetupAlertaDto obj) {
        return this.api.post(obj, UsedApis.API_ALERTA_SAVE);
    }

    @Override
    public SetupAlertaDto edit(SetupAlertaDto obj) {
        return this.api.post(obj, UsedApis.API_ALERTA_SAVE);
    }

    @Override
    public SetupAlertaDto delete(SetupAlertaDto obj) {
        return null;
    }

    @Override
    public SetupAlertaDto[] getAll() {
        return null;
    }
    
}
