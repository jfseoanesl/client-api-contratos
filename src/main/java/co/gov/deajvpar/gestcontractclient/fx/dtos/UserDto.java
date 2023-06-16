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
public class UserDto {
    private Long id;
    private PersonaDto datosPersona;
    private String userName;
    private String password;
    private RolUsuarioDto rolUsuario;
    private DireccionSeccionalDto direccionSeccional;
    private UsuarioDto createdByUser;
    private boolean estado;
    private String tipoUsuario;
    private List<ContratoDto> listContratosSupervisados;
    private List<InformeSupervisorDto> listInformesSupervision;

    public UserDto() {
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
     * @return the datosPersona
     */
    public PersonaDto getDatosPersona() {
        return datosPersona;
    }

    /**
     * @param datosPersona the datosPersona to set
     */
    public void setDatosPersona(PersonaDto datosPersona) {
        this.datosPersona = datosPersona;
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
     * @return the rolUsuario
     */
    public RolUsuarioDto getRolUsuario() {
        return rolUsuario;
    }

    /**
     * @param rolUsuario the rolUsuario to set
     */
    public void setRolUsuario(RolUsuarioDto rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    /**
     * @return the direccionSeccional
     */
    public DireccionSeccionalDto getDireccionSeccional() {
        return direccionSeccional;
    }

    /**
     * @param direccionSeccional the direccionSeccional to set
     */
    public void setDireccionSeccional(DireccionSeccionalDto direccionSeccional) {
        this.direccionSeccional = direccionSeccional;
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
     * @return the listContratosSupervisados
     */
    public List<ContratoDto> getListContratosSupervisados() {
        return listContratosSupervisados;
    }

    /**
     * @param listContratosSupervisados the listContratosSupervisados to set
     */
    public void setListContratosSupervisados(List<ContratoDto> listContratosSupervisados) {
        this.listContratosSupervisados = listContratosSupervisados;
    }

    /**
     * @return the listInformesSupervision
     */
    public List<InformeSupervisorDto> getListInformesSupervision() {
        return listInformesSupervision;
    }

    /**
     * @param listInformesSupervision the listInformesSupervision to set
     */
    public void setListInformesSupervision(List<InformeSupervisorDto> listInformesSupervision) {
        this.listInformesSupervision = listInformesSupervision;
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
    
    
}
