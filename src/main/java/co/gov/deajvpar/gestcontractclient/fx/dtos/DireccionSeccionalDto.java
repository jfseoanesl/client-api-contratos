/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.dtos;

import java.util.List;

/**
 *
 * @author Jairo F
 */
public class DireccionSeccionalDto {

    private Long id;
    private String descripcionSeccional;
    private List<DptoDto> listDptoCoordinados;
    private UsuarioDto createdByUser;
    private boolean eliminado;

    public DireccionSeccionalDto() {
    }

    public DireccionSeccionalDto(String descripcionSeccional, List<DptoDto> listDptoCoordinados, UsuarioDto createdByUser) {
        this.descripcionSeccional = descripcionSeccional;
        this.listDptoCoordinados = listDptoCoordinados;
        this.createdByUser = createdByUser;
        this.eliminado = false;
    }

    public String dptosToString() {
        String dptos = "";
        boolean first = true;
        for (DptoDto d : this.listDptoCoordinados) {
            if (first) {
                dptos = dptos.concat(d.getNombreDpto());
                first = false;
            } else {
                dptos = dptos.concat(" - ").concat(d.getNombreDpto());
            }

        }
        return dptos;
    }

    /**
     * @return the idDireccion
     */
    public Long getIdDireccion() {
        return id;
    }

    /**
     * @param idDireccion the idDireccion to set
     */
    public void setIdDireccion(Long idDireccion) {
        this.id = idDireccion;
    }

    /**
     * @return the descripcionSeccional
     */
    public String getDescripcionSeccional() {
        return descripcionSeccional;
    }

    /**
     * @param descripcionSeccional the descripcionSeccional to set
     */
    public void setDescripcionSeccional(String descripcionSeccional) {
        this.descripcionSeccional = descripcionSeccional;
    }

    /**
     * @return the listDptoCoordinados
     */
    public List<DptoDto> getListDptoCoordinados() {
        return listDptoCoordinados;
    }

    /**
     * @param listDptoCoordinados the listDptoCoordinados to set
     */
    public void setListDptoCoordinados(List<DptoDto> listDptoCoordinados) {
        this.listDptoCoordinados = listDptoCoordinados;
    }

    /**
     * @return the createdByUser
     */
    public UsuarioDto getCreatedByUser() {
        return createdByUser;
    }

    /**
     * @param createdByUser the createdByUser to set
     */
    public void setCreatedByUser(UsuarioDto createdByUser) {
        this.createdByUser = createdByUser;
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
}
