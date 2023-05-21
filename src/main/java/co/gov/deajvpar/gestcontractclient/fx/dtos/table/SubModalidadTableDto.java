/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.dtos.table;

import co.gov.deajvpar.gestcontractclient.fx.dtos.SubModalidadDto;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Jairo F
 */
public class SubModalidadTableDto {

    private SimpleLongProperty id;
    private SimpleStringProperty nombre;
    private SimpleStringProperty descripcion;

    public SubModalidadTableDto(SubModalidadDto dto) {
        
        this.id = new SimpleLongProperty(dto.getId());
        this.nombre = new SimpleStringProperty(dto.getNombreSubModalidad());
        this.descripcion = new SimpleStringProperty(dto.getDescripcionSubModalidad());
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id.get();
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id.set(id);
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre.get();
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion.get();
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }
    
    

}
