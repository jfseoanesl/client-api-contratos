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
public class SubModalidadDto {
    private Long id;
    private String descripcionSubModalidad;
    private String nombreSubModalidad;
    private List<ContratoDto> listContratosRegistrados;
    private UsuarioDto createByUser;
    private boolean eliminado;

    public SubModalidadDto() {
        this.eliminado=false;
    }

    public SubModalidadDto(String descripcionSubModalidad, String nombreSubModalidad, UsuarioDto createByUser) {
        this();
        this.descripcionSubModalidad = descripcionSubModalidad;
        this.nombreSubModalidad = nombreSubModalidad;
        this.createByUser = createByUser;
    }

    public SubModalidadDto(Long id, String descripcionSubModalidad, String nombreSubModalidad, List<ContratoDto> listContratosRegistrados, UsuarioDto createByUser) {
        this();
        this.id = id;
        this.descripcionSubModalidad = descripcionSubModalidad;
        this.nombreSubModalidad = nombreSubModalidad;
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
     * @return the descripcionSubModalidad
     */
    public String getDescripcionSubModalidad() {
        return descripcionSubModalidad;
    }

    /**
     * @param descripcionSubModalidad the descripcionSubModalidad to set
     */
    public void setDescripcionSubModalidad(String descripcionSubModalidad) {
        this.descripcionSubModalidad = descripcionSubModalidad;
    }

    /**
     * @return the nombreSubModalidad
     */
    public String getNombreSubModalidad() {
        return nombreSubModalidad;
    }

    /**
     * @param nombreSubModalidad the nombreSubModalidad to set
     */
    public void setNombreSubModalidad(String nombreSubModalidad) {
        this.nombreSubModalidad = nombreSubModalidad;
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

    @Override
    public String toString() {
        return "SubModalidadDto{" + "id=" + id + ", descripcionSubModalidad=" + descripcionSubModalidad + ", nombreSubModalidad=" + nombreSubModalidad + ", listContratosRegistrados=" + listContratosRegistrados + ", createByUser=" + createByUser + ", eliminado=" + eliminado + '}';
    }
    
    
}
