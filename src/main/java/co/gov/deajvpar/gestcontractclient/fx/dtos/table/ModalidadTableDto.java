/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.dtos.table;

import co.gov.deajvpar.gestcontractclient.fx.dtos.ModalidadDto;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Jairo F
 */
public class ModalidadTableDto {
    private SimpleLongProperty idModalidad;
    private SimpleStringProperty nombreModalidad;
    private SimpleStringProperty descripcionModalidad;
    private SimpleIntegerProperty submodalidades;

     
    public ModalidadTableDto(ModalidadDto dto) {
        
        this.idModalidad = new SimpleLongProperty(dto.getId());
        this.nombreModalidad = new SimpleStringProperty(dto.getNombreModalidad());
        this.descripcionModalidad = new SimpleStringProperty(dto.getDescripcionModalidad());
        this.submodalidades = new SimpleIntegerProperty(dto.getListSubModalidades().size());
        
    }

    /**
     * @return the id
     */
    public Long getIdModalidad() {
        return this.idModalidad.get();
    }

    /**
     * @param id the id to set
     */
    public void setIdModalidad(Long id) {
        this.idModalidad.set(id);
    }

    /**
     * @return the nombreModalidad
     */
    public String getNombreModalidad() {
        return nombreModalidad.get();
    }

    /**
     * @param nombreModalidad the nombreModalidad to set
     */
    public void setNombreModalidad(String nombreModalidad) {
        this.nombreModalidad.set( nombreModalidad);
    }

    /**
     * @return the descripcionModalidad
     */
    public String getDescripcionModalidad() {
        return descripcionModalidad.get();
    }

    /**
     * @param descripcionModalidad the descripcionModalidad to set
     */
    public void setDescripcionModalidad(String descripcionModalidad) {
        this.descripcionModalidad.set(descripcionModalidad);
    }

    /**
     * @return the submodalidades
     */
    public int getSubmodalidades() {
        return submodalidades.get();
    }

    /**
     * @param submodalidades the submodalidades to set
     */
    public void setSubmodalidades(int submodalidades) {
        this.submodalidades.set(submodalidades); 
    }

    
    
}
