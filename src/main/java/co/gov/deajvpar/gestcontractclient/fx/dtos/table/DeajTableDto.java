/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.dtos.table;

import co.gov.deajvpar.gestcontractclient.fx.dtos.DireccionSeccionalDto;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Jairo F
 */
public class DeajTableDto {
    private SimpleStringProperty nombre;
    private SimpleStringProperty dptos;
    private SimpleLongProperty id;

    public DeajTableDto(DireccionSeccionalDto dto) {
        this.nombre = new SimpleStringProperty(dto.getDescripcionSeccional());
        this.dptos = new SimpleStringProperty(dto.dptosToString());
        this.id = new SimpleLongProperty(dto.getIdDireccion());
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre.get();
    }


    /**
     * @return the dptos
     */
    public String getDptos() {
        return dptos.get();
    }

   

    /**
     * @return the id
     */
    public Long getId() {
        return id.getValue();
    }

  
    
    
    
}
