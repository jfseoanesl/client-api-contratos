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
public class AnotacionContratoDto {

    private Long id;
    private LocalDate fecha;
    private String descripcion;
    private UsuarioDto createByUser;
    private ContratoDto contrato;

    public AnotacionContratoDto() {
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
     * @return the fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the createByUser
     */
    public UsuarioDto getCreateByUser() {
        return createByUser;
    }

    /**
     * @param createByUser the createByUser to set
     */
    public void setCreateByUser(UsuarioDto createByUser) {
        this.createByUser = createByUser;
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
