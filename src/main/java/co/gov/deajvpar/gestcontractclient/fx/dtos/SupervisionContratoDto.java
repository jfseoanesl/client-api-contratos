/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.dtos;

import java.time.LocalDate;

/**
 *
 * @author Jairo F
 */
public class SupervisionContratoDto {
    
    private Long idContrato, idSupervisor;
    private LocalDate fechaAsignacion, fechaInicioEjecucion;

    public SupervisionContratoDto() {
    }

    
    public SupervisionContratoDto(Long idContrato, Long idSupervisor, LocalDate fechaAsignacion, LocalDate fechaInicioEjecucion) {
        this.idContrato = idContrato;
        this.idSupervisor = idSupervisor;
        this.fechaAsignacion = fechaAsignacion;
        this.fechaInicioEjecucion = fechaInicioEjecucion;
    }

    /**
     * @return the idContrato
     */
    public Long getIdContrato() {
        return idContrato;
    }

    /**
     * @param idContrato the idContrato to set
     */
    public void setIdContrato(Long idContrato) {
        this.idContrato = idContrato;
    }

    /**
     * @return the idSupervisor
     */
    public Long getIdSupervisor() {
        return idSupervisor;
    }

    /**
     * @param idSupervisor the idSupervisor to set
     */
    public void setIdSupervisor(Long idSupervisor) {
        this.idSupervisor = idSupervisor;
    }

    /**
     * @return the fechaAsignacion
     */
    public LocalDate getFechaAsignacion() {
        return fechaAsignacion;
    }

    /**
     * @param fechaAsignacion the fechaAsignacion to set
     */
    public void setFechaAsignacion(LocalDate fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    /**
     * @return the fechaInicioEjecucion
     */
    public LocalDate getFechaInicioEjecucion() {
        return fechaInicioEjecucion;
    }

    /**
     * @param fechaInicioEjecucion the fechaInicioEjecucion to set
     */
    public void setFechaInicioEjecucion(LocalDate fechaInicioEjecucion) {
        this.fechaInicioEjecucion = fechaInicioEjecucion;
    }
    
    
    
}
