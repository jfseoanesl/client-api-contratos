/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.utility;

import co.gov.deajvpar.gestcontractclient.fx.dtos.CiudadDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.CiudadDptoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.DptoDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jairo F
 */
public class Utility {
    
    
    
    public static Map<String, DptoDto> listCiudadesToMapDpto(CiudadDptoDto[] list){
        
       Map<String, DptoDto> dptos = new HashMap();
       for(CiudadDptoDto c: list){
           
           DptoDto dpto = dptos.get(c.getC_digo_dane_del_departamento());
           if(dpto == null){
               dpto = new DptoDto(c.getC_digo_dane_del_departamento(), c.getDepartamento());
           }
           CiudadDto ciudad = new CiudadDto(c.getC_digo_dane_del_municipio(), c.getMunicipio());
           dpto.getListCiudades().add(ciudad);
           dptos.put(c.getC_digo_dane_del_departamento(), dpto);
       
       }
       return dptos;
    }
    
    
    
}
