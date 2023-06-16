/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.dtos;

import java.util.List;

/**
 *
 * @author Jairo F
 */
public enum TipoUsuarioDto {
    ADMINISTRADOR, DIRECTOR, JURIDICA, SUPERVISOR;
    
    public static TipoUsuarioDto get(String tipo){
        
        switch(tipo){
            case "ADMINISTRADOR": return ADMINISTRADOR;
            case "DIRECTOR": return DIRECTOR;
            case "JURIDICA":return JURIDICA;
            case "SUPERVISOR": return SUPERVISOR;
            default: return null;
        }
    }
    
    public static String[] getList(){
        String list[]={"ADMINISTRADOR","DIRECTOR", "JURIDICA","SUPERVISOR"};
        return list;
    }
}
