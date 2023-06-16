/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.logic;

import co.gov.deajvpar.gestcontractclient.fx.data.CrudApiPersona;
import co.gov.deajvpar.gestcontractclient.fx.data.ICrudGeneric;
import co.gov.deajvpar.gestcontractclient.fx.dtos.PersonaDto;

/**
 *
 * @author Jairo F
 */
public class GestionPersona implements ICrudGeneric<PersonaDto> {
    
    private CrudApiPersona apiData = new CrudApiPersona();
    
    @Override
    public PersonaDto save(PersonaDto obj) {
        return this.apiData.save(obj);
    }

    @Override
    public PersonaDto edit(PersonaDto obj) {
        return this.apiData.edit(obj);
    }

    @Override
    public PersonaDto delete(PersonaDto obj) {
        return this.apiData.delete(obj);
    }

    @Override
    public PersonaDto[] getAll() {
       return this.apiData.getAll();
    }
    
    public PersonaDto findByDocument(PersonaDto dto){
        return this.apiData.findByDocumento(dto);
    }
    
}
