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
public class TipoContratoDto {
    private Long id;
    private String descripcion;
    private List<ContratoDto> listContratos;
    private UsuarioDto crateByuser;
    private boolean eliminado;

    public TipoContratoDto() {
        this.listContratos = new ArrayList();
        this.eliminado = false;
    }

    public TipoContratoDto(Long id, String descripcion) {
        this();
        this.id = id;
        this.descripcion = descripcion;
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

    /**
     * @return the crateByuser
     */
    public UsuarioDto getCrateByuser() {
        return crateByuser;
    }

    /**
     * @param crateByuser the crateByuser to set
     */
    public void setCrateByuser(UsuarioDto crateByuser) {
        this.crateByuser = crateByuser;
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

    @Override
    public String toString() {
        return "TipoContratoDto{" + "id=" + id + ", descripcion=" + descripcion + ", listContratos=" + listContratos + ", crateByuser=" + crateByuser + ", eliminado=" + eliminado + '}';
    }
}
