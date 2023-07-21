/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.utility;

/**
 * 
 *
 * @author Jairo F
 */
public interface UsedApis {
     
     //String API_HOME = "http://34.29.79.246:8080";
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
     
     String API_ROL = API_HOME +   "/rolusuario";
     String API_ROL_GET_ALL = API_ROL + "/list";
     String API_ROL_GET_ALL_TIPO = API_ROL + "/listTipo";
     String API_ROL_SAVE = API_ROL + "/save";
     String API_ROL_EDIT = API_ROL + "/edit";
     String API_ROL_DELETE = API_ROL + "/delete";

     String API_USER = API_HOME +   "/users";
     String API_USER_GET_ALL = API_USER + "/list";
     String API_USER_GET_ALL_BY_ROL =API_USER + "/list/rol";
     String API_USER_SAVE = API_USER + "/save";
     String API_USER_EDIT = API_USER + "/edit";
     String API_USER_DELETE = API_USER + "/delete";
     String API_USER_FIND = API_USER + "/find";
     
     String API_SUPERVISOR = API_HOME +   "/supervisor";
     String API_SUPERVISOR_GET_ALL = API_SUPERVISOR + "/all";
     String API_SUPERVISOR_GET_ALL_DEAJ = API_SUPERVISOR + "/list";
     String API_SUPERVISOR_SAVE = API_SUPERVISOR + "/save";
     String API_SUPERVISOR_EDIT = API_SUPERVISOR + "/edit";
     String API_SUPERVISOR_DELETE = API_SUPERVISOR + "/delete";
     String API_SUPERVISOR_FIND = API_SUPERVISOR + "/buscar";
     
     String API_DPTO = API_HOME + "/Dpto";
     String API_DPTO_GET_ALL = API_DPTO + "/list";
     
     String API_TIPO_DOCUMENTO = API_HOME + "/tipoDocumento";
     String API_TIPO_DOCUMENTO_GET_ALL = API_TIPO_DOCUMENTO + "/list" ;
     
     String API_PERSONA = API_HOME + "/persona";
     String API_PERSONA_SAVE = API_PERSONA + "/save";
     String API_PERSONA_EDIT = API_PERSONA + "/edit";
     String API_PERSONA_FIND = API_PERSONA + "/buscar";
     String API_PERSONA_DELETE=API_PERSONA + "/delete";
     String API_PERSONA_GET_ALL=API_PERSONA + "/list";
     
     String API_CONTRATO = API_HOME + "/contrato";
     String API_CONTRATO_GET_ALL = API_CONTRATO + "/list";
     String API_CONTRATO_GET_ALL_BY_DEAJ = API_CONTRATO + "/list/deaj";
     String API_CONTRATO_SAVE = API_CONTRATO + "/save";
     String API_CONTRATO_EDIT = API_CONTRATO + "/edit";
     String API_CONTRATO_DELETE = API_CONTRATO + "/delete";
     String API_CONTRATO_SUSCRIBIR = API_CONTRATO + "/suscribir";
     String API_CONTRATO_SUPERVISAR = API_CONTRATO + "/supervisar";
     String API_CONTRATO_BUSCAR = API_CONTRATO + "/buscar";
     String API_CONTRATO_REPORTING_BYTIPO=API_CONTRATO +"/reporting/tipo";
     String API_CONTRATO_REPORTING_BYMODALIDAD=API_CONTRATO +"/reporting/modalidad";
     String API_CONTRATO_REPORTING_BYESTADO=API_CONTRATO +"/reporting/estado";
     String API_CONTRATO_REPORTING_BYVIGENCIA=API_CONTRATO +"/reporting/vigencia";
     
     String API_DOG_CEO="https://dog.ceo/api/breeds/image/random";
     String API_DPTO_CIUDAD = "https://www.datos.gov.co/resource/xdk5-pm3f.json";
     
}
