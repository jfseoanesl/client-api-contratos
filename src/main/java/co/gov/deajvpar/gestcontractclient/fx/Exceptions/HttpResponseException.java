/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.Exceptions;

import co.gov.deajvpar.gestcontractclient.fx.utility.HttpCodeResponse;
import co.gov.deajvpar.gestcontractclient.fx.utility.StatusCode;

/**
 *
 * @author Jairo F
 */
public class HttpResponseException extends RuntimeException {
    private String causa;
    
    public HttpResponseException() {
        this("Excepcion: Respuesta a solicitud HTTP es Null");
    }

    public HttpResponseException(String message) {
        super(message);
    }
    
    public HttpResponseException(StatusCode code) {
        super(code.getDescripcion());
        this.causa = code.getStatus();
    }

    /**
     * @return the causa
     */
    public String getCausa() {
        return causa;
    }

    /**
     * @param causa the causa to set
     */
    public void setCausa(String causa) {
        this.causa = causa;
    }
    

    
    
    
}
