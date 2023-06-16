/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.data;

/**
 *
 * @author Jairo F
 */
public interface ICrudGeneric <T> {
    
    T save (T obj) ;
    T edit (T obj);
    T delete (T obj);
    T[] getAll();
    
}
