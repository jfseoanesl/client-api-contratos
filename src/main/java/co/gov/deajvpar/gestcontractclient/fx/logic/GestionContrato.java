/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.logic;


/**
 *
 * @author Jairo F
 */
public class GestionContrato {
    
    private static GestionCrearContrato crear;
    private static GestionSuscribirContrato suscribir;
    private static GestionSupervisarContrato supervisar;
    
    
    public static GestionCrearContrato getGestionCrear(){
        
        if(crear==null){
            crear = new GestionCrearContrato();
        }
        
        return crear;
        
    }
    
    public static void setGestionCrear(GestionCrearContrato gestionCrear){
        GestionContrato.crear = gestionCrear;
    }
    
    public static GestionSuscribirContrato getGestionSuscribir(){
        
        if(suscribir==null){
            suscribir = new GestionSuscribirContrato();
        }
        
        return suscribir;
        
    }
    
    public static void setGestionSuscribir(GestionSuscribirContrato gestionSuscribir){
        GestionContrato.suscribir = gestionSuscribir;
    }
    
     public static GestionSupervisarContrato getGestionSupervisar(){
        
        if(supervisar==null){
            supervisar = new GestionSupervisarContrato();
        }
        
        return supervisar;
        
    }
    
    public static void setGestionSupervisar(GestionSupervisarContrato gestionSupervisar){
        GestionContrato.supervisar = gestionSupervisar;
    }
    
    
}
