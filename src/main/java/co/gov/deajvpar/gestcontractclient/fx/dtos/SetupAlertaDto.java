/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.dtos;

import java.awt.Color;

/**
 *
 * @author Jairo F
 */
public class SetupAlertaDto {
    private Long id;
    private int diasRojo;
    private int diasNaranja;
    private int diasVerde;
    private int prioridadRojo;
    private int prioridadNaranja;
    private int prioridadVerde;


    public SetupAlertaDto() {
        
         this.diasRojo=5;
        this.diasNaranja=10;
        this.diasVerde=1;
        
               this.prioridadRojo=2;
        this.prioridadNaranja=1;
        this.prioridadVerde=0;
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
     * @return the diasRojo
     */
    public int getDiasRojo() {
        return diasRojo;
    }

    /**
     * @param diasRojo the diasRojo to set
     */
    public void setDiasRojo(int diasRojo) {
        this.diasRojo = diasRojo;
    }

    /**
     * @return the diasNaranja
     */
    public int getDiasNaranja() {
        return diasNaranja;
    }

    /**
     * @param diasNaranja the diasNaranja to set
     */
    public void setDiasNaranja(int diasNaranja) {
        this.diasNaranja = diasNaranja;
    }

    /**
     * @return the diasVerde
     */
    public int getDiasVerde() {
        return diasVerde;
    }

    /**
     * @param diasVerde the diasVerde to set
     */
    public void setDiasVerde(int diasVerde) {
        this.diasVerde = diasVerde;
    }

    /**
     * @return the prioridadRojo
     */
    public int getPrioridadRojo() {
        return prioridadRojo;
    }

    /**
     * @param prioridadRojo the prioridadRojo to set
     */
    public void setPrioridadRojo(int prioridadRojo) {
        this.prioridadRojo = prioridadRojo;
    }

    /**
     * @return the prioridadNaranja
     */
    public int getPrioridadNaranja() {
        return prioridadNaranja;
    }

    /**
     * @param prioridadNaranja the prioridadNaranja to set
     */
    public void setPrioridadNaranja(int prioridadNaranja) {
        this.prioridadNaranja = prioridadNaranja;
    }

    /**
     * @return the prioridadVerde
     */
    public int getPrioridadVerde() {
        return prioridadVerde;
    }

    /**
     * @param prioridadVerde the prioridadVerde to set
     */
    public void setPrioridadVerde(int prioridadVerde) {
        this.prioridadVerde = prioridadVerde;
    }

    
    
}
