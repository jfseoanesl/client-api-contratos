/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.logic;

import co.gov.deajvpar.gestcontractclient.fx.data.CrudApiTipoContrato;
import co.gov.deajvpar.gestcontractclient.fx.data.ICrudGeneric;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUserDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUsuarioSingleton;
import co.gov.deajvpar.gestcontractclient.fx.dtos.TipoContratoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.UsuarioDto;

/**
 *
 * @author Jairo F
 */
public class GestionTipoContrato implements ICrudGeneric<TipoContratoDto> {
    private ICrudGeneric<TipoContratoDto> apiData;
    private SesionUserDto sesion;
    private TipoContratoDto[] tipoContratoList;

    public GestionTipoContrato() {
        this.apiData = new CrudApiTipoContrato();
        this.sesion = SesionUsuarioSingleton.get();
        this.sesion.setPrivilegiosModulo();
    }
    
    @Override
    public TipoContratoDto save(TipoContratoDto obj) {
        return  this.apiData.save(obj);
    }

    @Override
    public TipoContratoDto edit(TipoContratoDto obj) {
        return  this.apiData.edit(obj);
    }

    @Override
    public TipoContratoDto delete(TipoContratoDto obj) {
        return  this.apiData.delete(obj);
    }

    @Override
    public TipoContratoDto[] getAll() {
        this.tipoContratoList=this.apiData.getAll();
        return  this.tipoContratoList;
    }

    /**
     * @return the create
     */
    public boolean isCreate() {
        return this.sesion.isCreate();
    }

    /**
     * @return the view
     */
    public boolean isView() {
        return this.sesion.isView();
    }

    /**
     * @return the delete
     */
    public boolean isDelete() {
        return this.sesion.isDelete();
    }

    /**
     * @return the update
     */
    public boolean isUpdate() {
        return this.sesion.isUpdate();
    }
    
    public TipoContratoDto getObjectTipoContract(Long id) {

        for (TipoContratoDto t : this.tipoContratoList) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }
    
    public UsuarioDto getSesionUser(){
      return this.sesion.getUser();
    }
    
}
