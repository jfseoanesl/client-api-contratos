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
public class InformeSupervisorDto {
    
    private Long id;
    private LocalDate fechaReferencia;
    private LocalDate fechaPresentacion;
    private LocalDate fechaRegistro;
    private String resumenInforme;
    private boolean estado; // false: no verificado   true: verificado
    private UserDto verificatebyUser;
    private LocalDate fechaVerificacion;
    private UserDto supervisor;
//    private ContratoDto contrato;

    public InformeSupervisorDto() {
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
     * @return the fechaReferencia
     */
    public LocalDate getFechaReferencia() {
        return fechaReferencia;
    }

    /**
     * @param fechaReferencia the fechaReferencia to set
     */
    public void setFechaReferencia(LocalDate fechaReferencia) {
        this.fechaReferencia = fechaReferencia;
    }

    /**
     * @return the fechaPresentacion
     */
    public LocalDate getFechaPresentacion() {
        return fechaPresentacion;
    }

    /**
     * @param fechaPresentacion the fechaPresentacion to set
     */
    public void setFechaPresentacion(LocalDate fechaPresentacion) {
        this.fechaPresentacion = fechaPresentacion;
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
     * @return the resumenInforme
     */
    public String getResumenInforme() {
        return resumenInforme;
    }

    /**
     * @param resumenInforme the resumenInforme to set
     */
    public void setResumenInforme(String resumenInforme) {
        this.resumenInforme = resumenInforme;
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
     * @return the verificatebyUser
     */
    public UserDto getVerificatebyUser() {
        return verificatebyUser;
    }

    /**
     * @param verificatebyUser the verificatebyUser to set
     */
    public void setVerificatebyUser(UserDto verificatebyUser) {
        this.verificatebyUser = verificatebyUser;
    }

    /**
     * @return the fechaVerificacion
     */
    public LocalDate getFechaVerificacion() {
        return fechaVerificacion;
    }

    /**
     * @param fechaVerificacion the fechaVerificacion to set
     */
    public void setFechaVerificacion(LocalDate fechaVerificacion) {
        this.fechaVerificacion = fechaVerificacion;
    }

    /**
     * @return the supervisor
     */
    public UserDto getSupervisor() {
        return supervisor;
    }

    /**
     * @param supervisor the supervisor to set
     */
    public void setSupervisor(UserDto supervisor) {
        this.supervisor = supervisor;
    }

    
    
    
}
