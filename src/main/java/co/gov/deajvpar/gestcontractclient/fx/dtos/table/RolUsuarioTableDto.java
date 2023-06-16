/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.dtos.table;

import co.gov.deajvpar.gestcontractclient.fx.dtos.ModuloDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.PermisoUserDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.RolUsuarioDto;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Jairo F
 */
public class RolUsuarioTableDto {
    
    private SimpleLongProperty id;
    private SimpleStringProperty nombre;
    private SimpleStringProperty configuracion;
    private SimpleStringProperty usuarios;
    private SimpleStringProperty contratos;
    private SimpleStringProperty supervision;
    private SimpleStringProperty supervisor;
    private SimpleStringProperty dashboard;

    public RolUsuarioTableDto(RolUsuarioDto dto) {
        
        this.id = new SimpleLongProperty(dto.getId());
        this.nombre = new SimpleStringProperty(dto.getNombreRol());
        this.configuracion = new SimpleStringProperty(this.getPermisosModulo(ModuloDto.CONFIGURACION, dto));
        this.usuarios = new SimpleStringProperty(this.getPermisosModulo(ModuloDto.USUARIOS, dto));
        this.contratos = new SimpleStringProperty(this.getPermisosModulo(ModuloDto.CONTRATOS, dto));
        this.supervision = new SimpleStringProperty(this.getPermisosModulo(ModuloDto.SUPERVISION, dto));
        this.supervisor = new SimpleStringProperty(this.getPermisosModulo(ModuloDto.SUPERVISOR, dto));
        this.dashboard = new SimpleStringProperty(this.getPermisosModulo(ModuloDto.DASHBOARD, dto));
    }
    
    private String getPermisosModulo(ModuloDto modulo, RolUsuarioDto rol){
        
        for(PermisoUserDto p: rol.getListPermisosUsuario()){
           
            if(p.getModulo().equals(modulo.toString())){
                return p.getPrivilegiosModulo();
            }
           
        
        }
        return " ";
    
    }

    /**
     * @return the id
     */
    public Long getId() {
        return this.id.get();
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
     * @return the configuracion
     */
    public String getConfiguracion() {
        return configuracion.get();
    }

    /**
     * @param configuracion the configuracion to set
     */
    public void setConfiguracion(String configuracion) {
        this.configuracion.set(configuracion);
    }

    /**
     * @return the usuarios
     */
    public String getUsuarios() {
        return usuarios.get();
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(String usuarios) {
        this.usuarios.set(usuarios);
    }

    /**
     * @return the contratos
     */
    public String getContratos() {
        return contratos.get();
    }

    /**
     * @param contratos the contratos to set
     */
    public void setContratos(String contratos) {
        this.contratos.set(contratos);
    }

    /**
     * @return the supervision
     */
    public String getSupervision() {
        return supervision.get();
    }

    /**
     * @param supervision the supervision to set
     */
    public void setSupervision(String supervision) {
        this.supervision.set(supervision);
    }

    /**
     * @return the supervisor
     */
    public String getSupervisor() {
        return supervisor.get();
    }

    /**
     * @param supervisor the supervisor to set
     */
    public void setSupervisor(String supervisor) {
        this.supervisor.set(supervisor);
    }

    /**
     * @return the dashboard
     */
    public String getDashboard() {
        return dashboard.get();
    }

    /**
     * @param dashboard the dashboard to set
     */
    public void setDashboard(String dashboard) {
        this.dashboard.set(dashboard);
    }
    
    
    
}
