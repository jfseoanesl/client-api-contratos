/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.dtos;

/**
 *
 * @author Jairo F
 */
public class CiudadDptoDto {
   
    private String region;
    private String c_digo_dane_del_municipio;
    private String municipio;
    private String c_digo_dane_del_departamento;
    private String departamento;

    public CiudadDptoDto(String region, String c_digo_dane_del_municipio, String municipio, String c_digo_dane_del_departamento, String departamento) {
        this.region = region;
        this.c_digo_dane_del_municipio = c_digo_dane_del_municipio;
        this.municipio = municipio;
        this.c_digo_dane_del_departamento = c_digo_dane_del_departamento;
        this.departamento = departamento;
    }

    public CiudadDptoDto() {
    }

    /**
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region the region to set
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @return the c_digo_dane_del_municipio
     */
    public String getC_digo_dane_del_municipio() {
        return c_digo_dane_del_municipio;
    }

    /**
     * @param c_digo_dane_del_municipio the c_digo_dane_del_municipio to set
     */
    public void setC_digo_dane_del_municipio(String c_digo_dane_del_municipio) {
        this.c_digo_dane_del_municipio = c_digo_dane_del_municipio;
    }

    /**
     * @return the municipio
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    /**
     * @return the c_digo_dane_del_departamento
     */
    public String getC_digo_dane_del_departamento() {
        return c_digo_dane_del_departamento;
    }

    /**
     * @param c_digo_dane_del_departamento the c_digo_dane_del_departamento to set
     */
    public void setC_digo_dane_del_departamento(String c_digo_dane_del_departamento) {
        this.c_digo_dane_del_departamento = c_digo_dane_del_departamento;
    }

    /**
     * @return the departamento
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * @param departamento the departamento to set
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    
    
}
