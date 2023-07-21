/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.dtos;

/**
 *
 * @author Jairo F
 */
public class LugarEjecucionDto {
    private Long id;
    private DptoDto dpto;
    private CiudadDto ciudad;
    private String direccion;
//    private ContratoDto contrato;

    public LugarEjecucionDto() {
    }

    public LugarEjecucionDto(DptoDto dpto, CiudadDto ciudad, String direccion) {
        this.dpto = dpto;
        this.ciudad = ciudad;
        this.direccion = direccion;
        
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
     * @return the dpto
     */
    public DptoDto getDpto() {
        return dpto;
    }

    /**
     * @param dpto the dpto to set
     */
    public void setDpto(DptoDto dpto) {
        this.dpto = dpto;
    }

    /**
     * @return the ciudad
     */
    public CiudadDto getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(CiudadDto ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    

    @Override
    public String toString() {
        return dpto.getNombreDpto() + " - " + ciudad.getNombreCiudad();
    }
    
    
}
