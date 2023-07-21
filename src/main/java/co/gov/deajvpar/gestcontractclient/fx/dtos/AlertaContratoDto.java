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
public class AlertaContratoDto {
    
    private Long id;
    private LocalDate fechaRegistro;
    private boolean estado;  // true: actual   false: vieja
    private AvisoAlertaDto aviso;
    private ContratoDto contrato;

    public AlertaContratoDto() {
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the fechaRegistro
     */
    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @return the estado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * @return the aviso
     */
    public AvisoAlertaDto getAviso() {
        return aviso;
    }

    /**
     * @param aviso the aviso to set
     */
    public void setAviso(AvisoAlertaDto aviso) {
        this.aviso = aviso;
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

    
    
    
}
