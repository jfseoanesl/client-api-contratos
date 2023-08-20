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
public class RolUsuarioDto {
   
    private Long id;
    private String nombreRol;
    private String tipoUsuario;
    private List<PermisoUserDto> listPermisosUsuario;
    private UsuarioDto cratedByUser;
    private boolean eliminado;

    public RolUsuarioDto() {
        this.listPermisosUsuario = new ArrayList();
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
     * @return the nombreRol
     */
    public String getNombreRol() {
        return nombreRol;
    }

    /**
     * @param nombreRol the nombreRol to set
     */
    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    /**
     * @return the tipoUsuario
     */
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * @param tipoUsuario the tipoUsuario to set
     */
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * @return the listPermisosUsuario
     */
    public List<PermisoUserDto> getListPermisosUsuario() {
        return listPermisosUsuario;
    }

    /**
     * @param listPermisosUsuario the listPermisosUsuario to set
     */
    public void setListPermisosUsuario(List<PermisoUserDto> listPermisosUsuario) {
        this.listPermisosUsuario = listPermisosUsuario;
    }

    /**
     * @return the cratedByUser
     */
    public UsuarioDto getCratedByUser() {
        return cratedByUser;
    }

    /**
     * @param cratedByUser the cratedByUser to set
     */
    public void setCratedByUser(UsuarioDto cratedByUser) {
        this.cratedByUser = cratedByUser;
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
        return this.nombreRol ;
    }

    /**
     * @return the idRol
     */
   
    
}
