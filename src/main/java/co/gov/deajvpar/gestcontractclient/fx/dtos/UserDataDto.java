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
public class UserDataDto {

    private String tipoDocumento;
    private String noDocumento;
    private String pNombre;
    private String sNombre;
    private String pApellido;
    private String sApellido;
    private String genero;
    private LocalDate fechaNacimiento;
    private String userName;
    private String password;

    public UserDataDto() {
    }

    public UserDataDto(String tipoDocumento, String noDocumento, String pNombre, String sNombre, String pApellido, String sApellido, String genero, LocalDate fechaNacimiento, String userName, String password) {
        this.tipoDocumento = tipoDocumento;
        this.noDocumento = noDocumento;
        this.pNombre = pNombre;
        this.sNombre = sNombre;
        this.pApellido = pApellido;
        this.sApellido = sApellido;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.userName = userName;
        this.password = password;
    }

    

    /**
     * @return the tipoDocumento
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * @param tipoDocumento the tipoDocumento to set
     */
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * @return the noDocumento
     */
    public String getNoDocumento() {
        return noDocumento;
    }

    /**
     * @param noDocumento the noDocumento to set
     */
    public void setNoDocumento(String noDocumento) {
        this.noDocumento = noDocumento;
    }

    /**
     * @return the pNombre
     */
    public String getpNombre() {
        return pNombre;
    }

    /**
     * @param pNombre the pNombre to set
     */
    public void setpNombre(String pNombre) {
        this.pNombre = pNombre;
    }

    /**
     * @return the sNombre
     */
    public String getsNombre() {
        return sNombre;
    }

    /**
     * @param sNombre the sNombre to set
     */
    public void setsNombre(String sNombre) {
        this.sNombre = sNombre;
    }

    /**
     * @return the pApellido
     */
    public String getpApellido() {
        return pApellido;
    }

    /**
     * @param pApellido the pApellido to set
     */
    public void setpApellido(String pApellido) {
        this.pApellido = pApellido;
    }

    /**
     * @return the sApellido
     */
    public String getsApellido() {
        return sApellido;
    }

    /**
     * @param sApellido the sApellido to set
     */
    public void setsApellido(String sApellido) {
        this.sApellido = sApellido;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * @return the fechaNacimiento
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDataDto{" + "tipoDocumento=" + tipoDocumento + ", noDocumento=" + noDocumento + ", pNombre=" + pNombre + ", sNombre=" + sNombre + ", pApellido=" + pApellido + ", sApellido=" + sApellido + ", genero=" + genero + ", userName=" + userName + ", password=" + password + '}';
    }
    
    
}
