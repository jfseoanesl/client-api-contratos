/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.data;

import co.gov.deajvpar.gestcontractclient.fx.dtos.PersonaDto;
import co.gov.deajvpar.gestcontractclient.fx.utility.UsedApis;

/**
 *
 * @author Jairo F
 */
public class CrudApiPersona implements ICrudGeneric<PersonaDto>{
    
    private CrudApiImp<PersonaDto> api = new CrudApiImp();
    
    @Override
    public PersonaDto save(PersonaDto obj) {
        return this.api.post(obj, UsedApis.API_PERSONA_SAVE);
    }

    @Override
    public PersonaDto edit(PersonaDto obj) {
        return this.api.post(obj, UsedApis.API_PERSONA_EDIT);
    }

    @Override
    public PersonaDto delete(PersonaDto obj) {
        return this.api.post(obj, UsedApis.API_PERSONA_DELETE);
    }

    @Override
    public PersonaDto[] getAll() {
        return this.api.get(PersonaDto.class, UsedApis.API_PERSONA_GET_ALL);
    }
    
    public PersonaDto findByDocumento(PersonaDto dto){
        return this.api.post(dto, UsedApis.API_PERSONA_FIND);
    }
    
}
