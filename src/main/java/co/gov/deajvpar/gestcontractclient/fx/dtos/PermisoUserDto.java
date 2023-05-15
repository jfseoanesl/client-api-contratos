/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.dtos;

/**
 *
 * @author Jairo F
 */
public class PermisoUserDto {

    private String modulo;
    private boolean c;
    private boolean u;
    private boolean d;
    private boolean r;

    public PermisoUserDto() {
    }

    public PermisoUserDto(String modulo, boolean pCrear, boolean pEditar, boolean pEliminar, boolean pConsultar) {
        this.modulo = modulo;
        this.c = pCrear;
        this.u = pEditar;
        this.d = pEliminar;
        this.r = pConsultar;
    }

    /**
     * @return the modulo
     */
    public String getModulo() {
        return modulo;
    }

    /**
     * @param modulo the modulo to set
     */
    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    /**
     * @return the pCrear
     */
    public boolean ispCrear() {
        return c;
    }

    /**
     * @param pCrear the pCrear to set
     */
    public void setpCrear(boolean pCrear) {
        this.c = pCrear;
    }

    /**
     * @return the pEditar
     */
    public boolean ispEditar() {
        return u;
    }

    /**
     * @param pEditar the pEditar to set
     */
    public void setpEditar(boolean pEditar) {
        this.u = pEditar;
    }

    /**
     * @return the pEliminar
     */
    public boolean ispEliminar() {
        return d;
    }

    /**
     * @param pEliminar the pEliminar to set
     */
    public void setpEliminar(boolean pEliminar) {
        this.d = pEliminar;
    }

    /**
     * @return the pConsultar
     */
    public boolean ispConsultar() {
        return r;
    }

    /**
     * @param pConsultar the pConsultar to set
     */
    public void setpConsultar(boolean pConsultar) {
        this.r = pConsultar;
    }

    public boolean checkPermisoModulo(String modulo) {
        return this.modulo.equalsIgnoreCase("ALL") || this.modulo.equalsIgnoreCase(modulo);

    }
    
    public boolean checkPrivilegioModulo(ModuloDto modulo, PrivilegioDto privilegio){
        boolean succes = false;
        if(this.checkPermisoModulo(modulo.getName())){
            switch(privilegio){
                
                case CREAR: succes = this.c; break;
                case ACTUALIZAR: succes = this.u;break;
                case CONSULTAR: succes = this.r;break;
                case ELIMINAR: succes = this.d;break;
            
            }
        }
        return succes;
    }

    @Override
    public String toString() {
        return "PermisoUserDto{" + "modulo=" + modulo + ", c=" + c + ", u=" + u + ", d=" + d + ", r=" + r + '}';
    }

    

}
