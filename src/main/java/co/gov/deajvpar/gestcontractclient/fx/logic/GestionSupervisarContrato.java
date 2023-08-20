/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.logic;

import co.gov.deajvpar.gestcontractclient.fx.controller.IFormController;
import co.gov.deajvpar.gestcontractclient.fx.data.CrudApiContrato;
import co.gov.deajvpar.gestcontractclient.fx.data.CrudApiSupervisor;
import co.gov.deajvpar.gestcontractclient.fx.dtos.ContratoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUserDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUsuarioSingleton;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SupervisionContratoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.UserDto;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Jairo F
 */
public class GestionSupervisarContrato {

    private final SesionUserDto sesion;
    private ContratoDto contrato;
    private final CrudApiContrato apiData;
    private List<UserDto> supervisorList;
    private UserDto supervisor;
    private final CrudApiSupervisor apiSupervisor;

    public GestionSupervisarContrato() {
        this.sesion = SesionUsuarioSingleton.get();
        this.apiData = new CrudApiContrato();
        this.apiSupervisor = new CrudApiSupervisor();
        this.sesion.setPrivilegiosModulo();
    }

    public void guardarSupervision(LocalDate fechaDesignacion, LocalDate fechaInicioEjecucion) {

        if (this.getContrato() != null && this.getContrato().getId() != null && this.getSupervisor() != null) {
            SupervisionContratoDto supervision = new SupervisionContratoDto(this.getContrato().getId(), this.getSupervisor().getId(), fechaDesignacion, fechaInicioEjecucion);
            supervision.setIdUserCreated(this.sesion.getUser().getId());
            this.setContrato(this.apiData.supervisarContrato(supervision));
        }

    }

    public String getTitleNoContrato() {
        String tag = "Contrato No.";
        if (this.getContrato() != null) {
            tag = tag + this.getContrato().getNoContrato();
        }
        return tag;

    }

    public List<UserDto> getListSupervisores() {
        UserDto dto = new UserDto();
        dto.setDireccionSeccional(this.contrato.getDireccion());
        this.supervisorList = this.apiSupervisor.getAll(dto);
        return this.supervisorList;
    }

    public void setSupervisor(int index) {
        if (this.supervisorList != null && this.supervisorList.size() > 0 && this.supervisorList.size() > index) {
            this.supervisor = this.supervisorList.get(index);
        } else {
            this.supervisor = null;
        }

    }

    /**
     * @return the contrato
     */
    public ContratoDto getContrato() {
        return contrato;
    }

    /**
     * @param contrato the contrato to set
     */
    public void setContrato(ContratoDto contrato) {
        this.contrato = contrato;
    }

    /**
     * @return the supervisor
     */
    public UserDto getSupervisor() {
        return supervisor;
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
