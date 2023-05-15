/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.dtos;

/**
 *
 * @author Jairo F
 */
public enum ModuloDto {
    ALL("ALL",0),
    CONFIGURACION("CONFIGURACION",1),
    USUARIOS("USUARIOS",2),
    CONTRATOS("CONTRATOS",3),
    SUPERVISION("SUPERVISION",4),
    SUPERVISOR("SUPERVISOR",5),
    DASHBOARD("DASHBOARD",6);
    
    private String name;
    private int value;

    private ModuloDto(String name, int value) {
        this.name = name;
        this.value = value;
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
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }
    
    public ModuloDto getValueModule(String name){
        
        switch(name){
            case "CONFIGURACION": return ModuloDto.CONFIGURACION;
            case "ALL": return ModuloDto.ALL;
            case "CONTRATOS": return ModuloDto.CONTRATOS;
            case "DASHBOARD": return ModuloDto.DASHBOARD;
            case "SUPERVISION": return ModuloDto.SUPERVISION;
            case "SUPERVISOR": return ModuloDto.SUPERVISOR;
            default: return null;
        }
    }
    
    
}
