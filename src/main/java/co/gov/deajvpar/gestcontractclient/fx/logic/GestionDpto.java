/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.logic;

import co.gov.deajvpar.gestcontractclient.fx.data.CrudApiDpto;
import co.gov.deajvpar.gestcontractclient.fx.data.ICrudGeneric;
import co.gov.deajvpar.gestcontractclient.fx.dtos.DptoDto;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 *
 * @author Jairo F
 */
public class GestionDpto implements ICrudGeneric<DptoDto> {
    private CrudApiDpto apiData;

    public GestionDpto() {
        this.apiData = new CrudApiDpto();
    }

    
    @Override
    public DptoDto save(DptoDto obj)  {
        return this.apiData.save(obj);
    }

    @Override
    public DptoDto edit(DptoDto obj)  {
        return this.apiData.edit(obj);
    }

    @Override
    public DptoDto delete(DptoDto obj) {
        return this.apiData.delete(obj);
    }

    @Override
    public DptoDto[] getAll() {
        return this.apiData.getAll();
    }
}
