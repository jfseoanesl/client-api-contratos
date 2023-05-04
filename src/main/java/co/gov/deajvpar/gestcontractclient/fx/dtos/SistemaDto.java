/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.dtos;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Jairo F
 */
public class SistemaDto{
    private Long id;
    private String titulo;
    private String version;
    private String name;
    private boolean inicializado;
    private Map<String, DptoDto> dptos;

    public SistemaDto(Long id, String titulo, String version, String name, boolean inicializado) {
        this.id = id;
        this.titulo = titulo;
        this.version = version;
        this.name = name;
        this.inicializado = inicializado;
    }

    public SistemaDto() {
    }

    public SistemaDto(Long id, String titulo, String version, String name, boolean inicializado, Map<String, DptoDto> dptos) {
        this.id = id;
        this.titulo = titulo;
        this.version = version;
        this.name = name;
        this.inicializado = inicializado;
        this.dptos = dptos;
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
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the inicializado
     */
    public boolean isInicializado() {
        return inicializado;
    }

    /**
     * @param inicializado the inicializado to set
     */
    public void setInicializado(boolean inicializado) {
        this.inicializado = inicializado;
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
    
    
}
