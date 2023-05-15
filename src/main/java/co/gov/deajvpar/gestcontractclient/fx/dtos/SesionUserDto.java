/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.dtos;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Jairo F
 */
public class SesionUserDto {

    private UsuarioDto user;
    private String userType;
    private String userRol;
    private List<PermisoUserDto> userPermisos;
    private DireccionSeccionalDto userDeaj;
    private LocalDate fechaLogin;
    private boolean estado;

    public SesionUserDto(UsuarioDto user, String userType, String userRol, List<PermisoUserDto> userPermisos, DireccionSeccionalDto userDeaj, LocalDate fechaLogin, boolean estado) {
        this.user = user;
        this.userType = userType;
        this.userRol = userRol;
        this.userPermisos = userPermisos;
        this.userDeaj = userDeaj;
        this.fechaLogin = fechaLogin;
        this.estado = estado;
    }

    public SesionUserDto() {
    }

    /**
     * @return the user
     */
    public UsuarioDto getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UsuarioDto user) {
        this.user = user;
    }

    /**
     * @return the userType
     */
    public String getUserType() {
        return userType;
    }

    /**
     * @param userType the userType to set
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * @return the userRol
     */
    public String getUserRol() {
        return userRol;
    }

    /**
     * @param userRol the userRol to set
     */
    public void setUserRol(String userRol) {
        this.userRol = userRol;
    }

    /**
     * @return the userPermisos
     */
    public List<PermisoUserDto> getUserPermisos() {
        return userPermisos;
    }

    /**
     * @param userPermisos the userPermisos to set
     */
    public void setUserPermisos(List<PermisoUserDto> userPermisos) {
        this.userPermisos = userPermisos;
    }

    /**
     * @return the userDeaj
     */
    public DireccionSeccionalDto getUserDeaj() {
        return userDeaj;
    }

    /**
     * @param userDeaj the userDeaj to set
     */
    public void setUserDeaj(DireccionSeccionalDto userDeaj) {
        this.userDeaj = userDeaj;
    }

    /**
     * @return the fechaLogin
     */
    public LocalDate getFechaLogin() {
        return fechaLogin;
    }

    /**
     * @param fechaLogin the fechaLogin to set
     */
    public void setFechaLogin(LocalDate fechaLogin) {
        this.fechaLogin = fechaLogin;
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

    @Override
    public String toString() {
        return "SesionUserDto{" + "user=" + user + ", userType=" + userType + ", userRol=" + userRol + ", userPermisos=" + userPermisos + ", userDeaj=" + userDeaj + ", fechaLogin=" + fechaLogin + ", estado=" + estado + '}';
    }

    public boolean checkPermisoModulo(String modulo) {
        boolean success = false;
        for (PermisoUserDto p : this.userPermisos) {
            if (p.checkPermisoModulo(modulo)) {
                success = true;
                break;
            }
        }
        return success;
    }
    
    public boolean checkPrivilegioModulo(ModuloDto modulo, PrivilegioDto privilegio) {
        boolean success = false;
        for (PermisoUserDto p : this.userPermisos) {
            success=p.checkPrivilegioModulo(modulo, privilegio);
        }
        return success;
    }
    

}
