/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.logic;

import co.gov.deajvpar.gestcontractclient.fx.data.CrudApiContrato;
import co.gov.deajvpar.gestcontractclient.fx.data.CrudApiSupervisor;
import co.gov.deajvpar.gestcontractclient.fx.dtos.ContratoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.PersonaDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUserDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUsuarioSingleton;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SuscripcionContratoDto;
import java.time.LocalDate;

/**
 *
 * @author Jairo F
 */
public class GestionSuscribirContrato {
    
   
    private SesionUserDto sesion;
    private ContratoDto contrato;
    private CrudApiContrato apiData;
    private CrudApiSupervisor apiSupervisor;
    private PersonaDto persona;

    public GestionSuscribirContrato() {
        this.sesion = SesionUsuarioSingleton.get();
        this.apiSupervisor = new CrudApiSupervisor();
        this.apiData = new CrudApiContrato();
        this.sesion.setPrivilegiosModulo();
    }
    
    
    
    public void guardarSuscripcion(String pNombre, String sNombre, String pApellido, String sApellido,
            String razonSocial, LocalDate nacimiento, LocalDate suscripcion, LocalDate adjudicacion, String genero) {

        if (this.contrato != null && this.contrato.getId() != null) {
            this.getPersona().setGenero(genero);
            this.getPersona().setpNombre(pNombre);
            this.getPersona().setsNombre(sNombre);
            this.getPersona().setpApellido(pApellido);
            this.getPersona().setsApellido(sApellido);
            this.getPersona().setFechaNacimiento(nacimiento);
            this.getPersona().setNombreEmpresa(razonSocial);
            SuscripcionContratoDto suscripcionDto = new SuscripcionContratoDto(this.contrato.getId(), this.getPersona(), adjudicacion, suscripcion);
            suscripcionDto.setUserCreateId(this.sesion.getUser().getId());
            this.contrato = this.apiData.suscribirContrato(suscripcionDto);
        }

    }
    
    public String getTitleNoContrato() {
        String tag = "Contrato No.";
        if (this.contrato != null) {
            tag = tag + this.contrato.getNoContrato();
        }
        return tag;

    }

    public ContratoDto getContrato() {
        return contrato;
    }

    public void setContrato(ContratoDto contrato) {
        this.contrato = contrato;
    }

    /**
     * @return the persona
     */
    public PersonaDto getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(PersonaDto persona) {
        this.persona = persona;
    }
    
    public boolean isCreate(){
        return this.sesion.isCreate();
    }
    
    public boolean isUpdate(){
        return this.sesion.isUpdate();
    }
    
    public boolean isView(){
        return this.sesion.isView();
    }
    
    public boolean isDelete(){
        return this.sesion.isDelete();
    }
    
}
