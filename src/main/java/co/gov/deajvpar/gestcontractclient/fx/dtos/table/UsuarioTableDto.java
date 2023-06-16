/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.dtos.table;

import co.gov.deajvpar.gestcontractclient.fx.dtos.UserDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.UsuarioDto;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Jairo F
 */
public class UsuarioTableDto {

    private SimpleLongProperty id;
    private SimpleStringProperty nombre;
    private SimpleStringProperty username;
    private SimpleStringProperty tipo;
    private SimpleStringProperty rol;
    private SimpleStringProperty seccional;

    public UsuarioTableDto(UserDto dto) {

        this.id = new SimpleLongProperty(dto.getId());
        String nombre = dto.getDatosPersona().getpNombre() + " "
                + dto.getDatosPersona().getsNombre() + " "
                + dto.getDatosPersona().getpApellido() + " "
                + dto.getDatosPersona().getsApellido() + " ";
        this.nombre = new SimpleStringProperty(nombre);
        this.username = new SimpleStringProperty(dto.getUserName());
        this.rol = new SimpleStringProperty(dto.getRolUsuario().getNombreRol());
        this.tipo = new SimpleStringProperty(dto.getTipoUsuario());
        if (dto.getDireccionSeccional() != null) {
            this.seccional = new SimpleStringProperty(dto.getDireccionSeccional().getDescripcionSeccional());
        } else {
            this.seccional =new SimpleStringProperty(null);
        }
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id.get();
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id.set(id);
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre.get();
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username.get();
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username.set(username);
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo.get();
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo.set(tipo);
    }

    /**
     * @return the rol
     */
    public String getRol() {
        return rol.get();
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(String rol) {
        this.rol.set(rol);
    }

    /**
     * @return the seccional
     */
    public String getSeccional() {
        return seccional.get();
    }

    /**
     * @param seccional the seccional to set
     */
    public void setSeccional(String seccional) {
        this.seccional.set(seccional);
    }

}
