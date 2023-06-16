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
   // ALL("ALL",0),
    CONFIGURACION("CONFIGURACION",0),
    USUARIOS("USUARIOS",1),
    CONTRATOS("CONTRATOS",2),
    SUPERVISION("SUPERVISION",3),
    SUPERVISOR("SUPERVISOR",4),
    DASHBOARD("DASHBOARD",5);
    
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
//            case "ALL": return ModuloDto.ALL;
            case "CONTRATOS": return ModuloDto.CONTRATOS;
            case "DASHBOARD": return ModuloDto.DASHBOARD;
            case "SUPERVISION": return ModuloDto.SUPERVISION;
            case "SUPERVISOR": return ModuloDto.SUPERVISOR;
            case "USUARIOS": return ModuloDto.USUARIOS;
            default: return null;
        }
    }
    
    
}
