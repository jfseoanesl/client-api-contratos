/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.logic;

import co.gov.deajvpar.gestcontractclient.fx.data.CrudApiSetupAlerta;
import co.gov.deajvpar.gestcontractclient.fx.data.ICrudGeneric;
import co.gov.deajvpar.gestcontractclient.fx.dtos.DireccionSeccionalDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUserDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUsuarioSingleton;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SetupAlertaDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.UsuarioDto;

/**
 *
 * @author Jairo F
 */
public class GestionSetupAlerta implements ICrudGeneric <SetupAlertaDto>{
    
    private final ICrudGeneric<SetupAlertaDto> apiData;
    private final SesionUserDto sesion;
       
    public GestionSetupAlerta() {
        this.apiData = new CrudApiSetupAlerta();
        this.sesion = SesionUsuarioSingleton.get();
        this.sesion.setPrivilegiosModulo();
        
    }
    
    @Override
    public SetupAlertaDto save(SetupAlertaDto obj) {
        return this.apiData.save(obj);
    }

    @Override
    public SetupAlertaDto edit(SetupAlertaDto obj) {
        return this.apiData.save(obj);
    }

    @Override
    public SetupAlertaDto delete(SetupAlertaDto obj) {
        return this.apiData.delete(obj);
    }

    @Override
    public SetupAlertaDto[] getAll() {
        return this.apiData.getAll();
    }

    /**
     * @return the create
     */
    public boolean isCreate() {
        return this.sesion.isCreate();
    }

    /**
     * @return the update
     */
    public boolean isUpdate() {
        return this.sesion.isUpdate();
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

    public UsuarioDto getSesionUser(){
        return this.sesion.getUser();
    }
    
    public DireccionSeccionalDto getUserDeaj(){
        return this.sesion.getUserDeaj();
    }
    
}
