/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.dtos;

/**
 *
 * @author Jairo F
 */
public class TipoDocumentoDto {
    
    private Long id;
    private String tipo;
    private String descripcion;
    private TipoPersona tipoPersona;
    private boolean eliminado;

    public TipoDocumentoDto() {
    }

    public TipoDocumentoDto(String tipo, String descripcion, TipoPersona tipoPersona) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.tipoPersona = tipoPersona;
        this.eliminado = false;
    }

    public TipoDocumentoDto(Long idTipoDocumento, String tipo, String descripcion, TipoPersona tipoPersona, boolean eliminado) {
        this.id = idTipoDocumento;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.tipoPersona = tipoPersona;
        this.eliminado = eliminado;
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
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
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
     * @return the tipoPersona
     */
    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    /**
     * @param tipoPersona the tipoPersona to set
     */
    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
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
        return  descripcion ;
    }

    
    
}
