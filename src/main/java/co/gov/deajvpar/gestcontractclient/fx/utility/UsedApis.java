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
     
     String API_TIPO_CONTRATO = API_HOME + "/tipoContrato";
     String API_TIPO_CONTRATO_GET_ALL = API_TIPO_CONTRATO + "/list";
     String API_TIPO_CONTRATO_SAVE = API_TIPO_CONTRATO + "/save";
     String API_TIPO_CONTRATO_EDIT = API_TIPO_CONTRATO + "/edit";
     String API_TIPO_CONTRATO_DELETE = API_TIPO_CONTRATO + "/delete";
     
     String API_MODALIDAD = API_HOME + "/modalidad";
     String API_MODALIDAD_GET_ALL = API_MODALIDAD + "/list";
     String API_MODALIDAD_SAVE = API_MODALIDAD + "/save";
     String API_MODALIDAD_EDIT = API_MODALIDAD + "/edit";
     String API_MODALIDAD_DELETE = API_MODALIDAD + "/delete";
     
     String API_SUBMODALIDAD = API_HOME + "/submodalidad";
     String API_SUBMODALIDAD_GET_ALL = API_SUBMODALIDAD + "/list";
     String API_SUBMODALIDAD_SAVE = API_SUBMODALIDAD + "/save";
     String API_SUBMODALIDAD_EDIT = API_SUBMODALIDAD + "/edit";
     String API_SUBMODALIDAD_DELETE = API_SUBMODALIDAD + "/delete";
     
     String API_ALERTA = API_HOME + "/alerta";
     String API_ALERTA_SAVE = API_ALERTA + "/save";
     
     String API_DPTO = API_HOME + "/Dpto";
     String API_DPTO_GET_ALL = API_DPTO + "/list";
     
     
     
     String API_DOG_CEO="https://dog.ceo/api/breeds/image/random";
     String API_DPTO_CIUDAD = "https://www.datos.gov.co/resource/xdk5-pm3f.json";
     
}
