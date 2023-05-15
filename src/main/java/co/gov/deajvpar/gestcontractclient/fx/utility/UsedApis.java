/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.utility;

/**
 *
 * @author Jairo F
 */
public interface UsedApis {
     
     String API_HOME = "http://localhost:8080";
     String API_HOME_INITIALIZE = API_HOME+"/initialize";
     
     String API_LOGIN = API_HOME + "/initLogin";
     
     String API_DEAJ = API_HOME + "/Deaj";
     String API_DEAJ_GET_ALL = API_DEAJ + "/list";
     String API_DEAJ_SAVE = API_DEAJ + "/save";
     String API_DEAJ_EDIT = API_DEAJ + "/edit";
     String API_DEAJ_DELETE = API_DEAJ + "/delete";
     
     String API_DPTO = API_HOME + "/Dpto";
     String API_DPTO_GET_ALL = API_DPTO + "/list";
     
     
     
     String API_DOG_CEO="https://dog.ceo/api/breeds/image/random";
     String API_DPTO_CIUDAD = "https://www.datos.gov.co/resource/xdk5-pm3f.json";
     
}
