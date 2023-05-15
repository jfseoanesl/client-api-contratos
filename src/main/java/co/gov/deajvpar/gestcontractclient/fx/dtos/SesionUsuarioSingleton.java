/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.dtos;

/**
 *
 * @author Jairo F
 */
public class SesionUsuarioSingleton {
    private static SesionUserDto sesion;

    public static SesionUserDto get(){
        
        if(sesion==null){
            sesion = new SesionUserDto();
        }
        
        return sesion;
        
    }
    
    public static void set(SesionUserDto sesion){
        SesionUsuarioSingleton.sesion = sesion;
    }
    
}
