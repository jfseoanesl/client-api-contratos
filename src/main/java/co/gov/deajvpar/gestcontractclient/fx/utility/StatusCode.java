/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.utility;

/**
 *
 * @author Jairo F
 */
public class StatusCode {
     private int code;
     private String status;
     private String descripcion;
     private String response;

    public StatusCode() {
    }

    public StatusCode(int code, String status) {
        this.code = code;
        this.status = status;
    }

    public StatusCode(int code, String status, String descripcion) {
        this.code = code;
        this.status = status;
        this.descripcion = descripcion;
    }

    public StatusCode(int code, String status, String descripcion, String response) {
        this.code = code;
        this.status = status;
        this.descripcion = descripcion;
        this.response = response;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Status Code( "+code+" ) - " + status;
    }
    
    public boolean statusOk(){
        return this.code==200;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the response
     */
    public String getResponse() {
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(String response) {
        this.response = response;
    }
}
