/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.dtos;

import java.util.Map;

/**
 *
 * @author Jairo F
 */
public class InicializarDto {
    private SistemaDto sistema;
    private Map<String, DptoDto> dptos;
    private UsuarioDto userData;

    public InicializarDto(SistemaDto sistema, Map<String, DptoDto> dptos, UsuarioDto admon) {
        this.sistema = sistema;
        this.dptos = dptos;
        this.userData = admon;
    }

    

    public InicializarDto() {
    }

    /**
     * @return the sistema
     */
    public SistemaDto getSistema() {
        return sistema;
    }

    /**
     * @param sistema the sistema to set
     */
    public void setSistema(SistemaDto sistema) {
        this.sistema = sistema;
    }

    /**
     * @return the dptos
     */
    public Map<String, DptoDto> getDptos() {
        return dptos;
    }

    /**
     * @param dptos the dptos to set
     */
    public void setDptos(Map<String, DptoDto> dptos) {
        this.dptos = dptos;
    }

    /**
     * @return the admon
     */
    public UsuarioDto getAdmon() {
        return userData;
    }

    /**
     * @param admon the admon to set
     */
    public void setAdmon(UsuarioDto admon) {
        this.userData = admon;
    }

    @Override
    public String toString() {
        return "InicializarDto{" + "sistema=" + sistema + ", dptos=" + dptos + ", userAdmon=" + userData + '}';
    }
    
    
}
