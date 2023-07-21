/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jairo F
 */
public class ModalidadDto {
   
    private Long id;
    private String descripcionModalidad;
    private String nombreModalidad;
    private List<SubModalidadDto> listSubModalidades;
    private List<ContratoDto> listContratosRegistrados;
    private UsuarioDto createByUser;
    private boolean eliminado;

    public ModalidadDto() {
        this.eliminado=false;
        this.listSubModalidades=new ArrayList();
    }

    public ModalidadDto(String descripcionModalidad, String nombreModalidad, UsuarioDto createByUser) {
        this();
        this.descripcionModalidad = descripcionModalidad;
        this.nombreModalidad = nombreModalidad;
        this.createByUser = createByUser;
    }

    public ModalidadDto(Long id, String descripcionModalidad, String nombreModalidad, UsuarioDto createByUser) {
        this.id = id;
        this.descripcionModalidad = descripcionModalidad;
        this.nombreModalidad = nombreModalidad;
        this.createByUser = createByUser;
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
     * @return the descripcionModalidad
     */
    public String getDescripcionModalidad() {
        return descripcionModalidad;
    }

    /**
     * @param descripcionModalidad the descripcionModalidad to set
     */
    public void setDescripcionModalidad(String descripcionModalidad) {
        this.descripcionModalidad = descripcionModalidad;
    }

    /**
     * @return the nombreModalidad
     */
    public String getNombreModalidad() {
        return nombreModalidad;
    }

    /**
     * @param nombreModalidad the nombreModalidad to set
     */
    public void setNombreModalidad(String nombreModalidad) {
        this.nombreModalidad = nombreModalidad;
    }

    /**
     * @return the listSubModalidades
     */
    public List<SubModalidadDto> getListSubModalidades() {
        return listSubModalidades;
    }

    /**
     * @param listSubModalidades the listSubModalidades to set
     */
    public void setListSubModalidades(List<SubModalidadDto> listSubModalidades) {
        this.listSubModalidades = listSubModalidades;
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
     * @return the eliminado
     */
    public boolean isEliminado() {
        return eliminado;
    }

    /**
     * @param eliminado the eliminado to set
     */
    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    /**
     * @return the listContratosRegistrados
     */
    public List<ContratoDto> getListContratosRegistrados() {
        return listContratosRegistrados;
    }

    /**
     * @param listContratosRegistrados the listContratosRegistrados to set
     */
    public void setListContratosRegistrados(List<ContratoDto> listContratosRegistrados) {
        this.listContratosRegistrados = listContratosRegistrados;
    }
    
    
    
    
}
