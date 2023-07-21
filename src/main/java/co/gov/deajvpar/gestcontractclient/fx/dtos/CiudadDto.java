/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.dtos;

/**
 *
 * @author Jairo F
 */
public class CiudadDto {
    private Long id;
    private String codigoCiudad;
    private String nombreCiudad;
  

    public CiudadDto() {
    }

    public CiudadDto(String codigoCiudad, String nombreCiudad) {
        this.codigoCiudad = codigoCiudad;
        this.nombreCiudad = nombreCiudad;
        
    }

    /**
     * @return the codigoCiudad
     */
    public String getCodigoCiudad() {
        return codigoCiudad;
    }

    /**
     * @param codigoCiudad the codigoCiudad to set
     */
    public void setCodigoCiudad(String codigoCiudad) {
        this.codigoCiudad = codigoCiudad;
    }

    /**
     * @return the nombreCiudad
     */
    public String getNombreCiudad() {
        return nombreCiudad;
    }

    /**
     * @param nombreCiudad the nombreCiudad to set
     */
    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
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
   
}
