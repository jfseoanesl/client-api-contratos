/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.dtos.table;

import co.gov.deajvpar.gestcontractclient.fx.dtos.TipoContratoDto;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Jairo F
 */
public class TipoContratoDtoTable {
    private SimpleStringProperty descripcion;
    private SimpleLongProperty id;

    public TipoContratoDtoTable(TipoContratoDto dto) {
        
        this.descripcion= new SimpleStringProperty(dto.getDescripcion());
        this.id= new SimpleLongProperty(dto.getId());
    }

    public String getDescripcion() {
        return this.descripcion.get();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public Long getId() {
        return id.get();
    }

    public void setId(Long id) {
        this.id.set(id);
    }
    
    
    
    
}
