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
    private List<ContratoDto> listContratos;
    private UsuarioDto createdByUser;
    private boolean eliminado;
    private SetupAlertaDto setupAlerta;

    public DireccionSeccionalDto(Long id) {
        this.id = id;
    }

    public DireccionSeccionalDto() {
        this.setupAlerta = new SetupAlertaDto();
    }

    public DireccionSeccionalDto(String descripcionSeccional, List<DptoDto> listDptoCoordinados, UsuarioDto createdByUser, SetupAlertaDto setupAlerta) {
        this.descripcionSeccional = descripcionSeccional;
        this.listDptoCoordinados = listDptoCoordinados;
        this.createdByUser = createdByUser;
        this.setupAlerta = setupAlerta;
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
        return getId();
    }

    /**
     * @param idDireccion the idDireccion to set
     */
    public void setIdDireccion(Long idDireccion) {
        this.setId(idDireccion);
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

    /**
     * @return the setupAlerta
     */
    public SetupAlertaDto getSetupAlerta() {
        return setupAlerta;
    }

    /**
     * @param setupAlerta the setupAlerta to set
     */
    public void setSetupAlerta(SetupAlertaDto setupAlerta) {
        this.setupAlerta = setupAlerta;
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
     * @return the listContratos
     */
    public List<ContratoDto> getListContratos() {
        return listContratos;
    }

    /**
     * @param listContratos the listContratos to set
     */
    public void setListContratos(List<ContratoDto> listContratos) {
        this.listContratos = listContratos;
    }

    @Override
    public String toString() {
        return descripcionSeccional + " ("+this.id+")";
    }
    
    
    
    
}
