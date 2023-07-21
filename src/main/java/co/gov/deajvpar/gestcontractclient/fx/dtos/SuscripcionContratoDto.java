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
public class SuscripcionContratoDto {
   
    private Long contratoId;
    private PersonaDto contratista;
    private LocalDate fechaAdjudicacion;
    private LocalDate fechaSuscripcion;

    public SuscripcionContratoDto() {
    }

    public SuscripcionContratoDto(Long contratoId, PersonaDto contratista, LocalDate fechaAdjudicacion, LocalDate fechaSuscripcion) {
        this.contratoId = contratoId;
        this.contratista = contratista;
        this.fechaAdjudicacion = fechaAdjudicacion;
        this.fechaSuscripcion = fechaSuscripcion;
    }
    
    

    /**
     * @return the contratista
     */
    public PersonaDto getContratista() {
        return contratista;
    }

    /**
     * @param contratista the contratista to set
     */
    public void setContratista(PersonaDto contratista) {
        this.contratista = contratista;
    }

    /**
     * @return the fechaAdjudicacion
     */
    public LocalDate getFechaAdjudicacion() {
        return fechaAdjudicacion;
    }

    /**
     * @param fechaAdjudicacion the fechaAdjudicacion to set
     */
    public void setFechaAdjudicacion(LocalDate fechaAdjudicacion) {
        this.fechaAdjudicacion = fechaAdjudicacion;
    }

    /**
     * @return the fechaSuscripcion
     */
    public LocalDate getFechaSuscripcion() {
        return fechaSuscripcion;
    }

    /**
     * @param fechaSuscripcion the fechaSuscripcion to set
     */
    public void setFechaSuscripcion(LocalDate fechaSuscripcion) {
        this.fechaSuscripcion = fechaSuscripcion;
    }

    /**
     * @return the contratoId
     */
    public Long getContratoId() {
        return contratoId;
    }

    /**
     * @param contratoId the contratoId to set
     */
    public void setContratoId(Long contratoId) {
        this.contratoId = contratoId;
    }

    @Override
    public String toString() {
        return "SuscripcionContratoDto{" + "contratoId=" + contratoId + ", contratista=" + contratista + ", fechaAdjudicacion=" + fechaAdjudicacion + ", fechaSuscripcion=" + fechaSuscripcion + '}';
    }
    
    
    
}
