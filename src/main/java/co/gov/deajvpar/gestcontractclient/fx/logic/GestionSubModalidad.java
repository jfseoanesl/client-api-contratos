/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.logic;

import co.gov.deajvpar.gestcontractclient.fx.data.CrudApiSubModalidad;
import co.gov.deajvpar.gestcontractclient.fx.data.ICrudGeneric;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SubModalidadDto;

/**
 *
 * @author Jairo F
 */
public class GestionSubModalidad implements ICrudGeneric<SubModalidadDto> {
     
    private ICrudGeneric<SubModalidadDto> apiData ;

    public GestionSubModalidad() {
        this.apiData= new CrudApiSubModalidad();
    }
    
    
    @Override
    public SubModalidadDto save(SubModalidadDto obj) {
        return this.apiData.save(obj);
    }

    @Override
    public SubModalidadDto edit(SubModalidadDto obj) {
        return this.apiData.edit(obj);
    }

    @Override
    public SubModalidadDto delete(SubModalidadDto obj) {
       return this.apiData.delete(obj);
    }

    @Override
    public SubModalidadDto[] getAll() {
        return this.apiData.getAll();
    }
    
}
