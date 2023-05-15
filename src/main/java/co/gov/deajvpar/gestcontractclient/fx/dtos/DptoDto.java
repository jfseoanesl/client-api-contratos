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
public class DptoDto {
    private Long idDpto;
    private String codigoDpto;
    private String nombreDpto;
    private List<CiudadDto> listCiudades;

    public DptoDto() {
        this.listCiudades = new ArrayList();
    }

    public DptoDto(String codigoDpto, String nombreDpto) {
        this();
        this.codigoDpto = codigoDpto;
        this.nombreDpto = nombreDpto;
      
    }

    /**
     * @return the codigoDpto
     */
    public String getCodigoDpto() {
        return codigoDpto;
    }

    /**
     * @param codigoDpto the codigoDpto to set
     */
    public void setCodigoDpto(String codigoDpto) {
        this.codigoDpto = codigoDpto;
    }

    /**
     * @return the nombreDpto
     */
    public String getNombreDpto() {
        return nombreDpto;
    }

    /**
     * @param nombreDpto the nombreDpto to set
     */
    public void setNombreDpto(String nombreDpto) {
        this.nombreDpto = nombreDpto;
    }

    /**
     * @return the listCiudades
     */
    public List<CiudadDto> getListCiudades() {
        return listCiudades;
    }

    /**
     * @param listCiudades the listCiudades to set
     */
    public void setListCiudades(List<CiudadDto> listCiudades) {
        this.listCiudades = listCiudades;
    }

    /**
     * @return the idDpto
     */
    public Long getIdDpto() {
        return idDpto;
    }

    /**
     * @param idDpto the idDpto to set
     */
    public void setIdDpto(Long idDpto) {
        this.idDpto = idDpto;
    }

    

   

    
    
    
}
