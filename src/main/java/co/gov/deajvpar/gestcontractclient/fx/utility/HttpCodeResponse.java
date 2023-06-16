/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.utility;

import com.mashape.unirest.http.HttpResponse;

/**
 *
 * @author Jairo F
 */
public class HttpCodeResponse {

    private HttpCodeResponse() {

    }

    /**
     * @return the code
     */
    public static StatusCode get(int code) {

        StatusCode status;

       
        if(code>=500){
            status = new StatusCode(code, "Internal Server Error - 500", "El servidor encontró un problema y no puede procesar la solicitud HTTP");
        }
        else if(code>=400){
            status = new StatusCode(code, "Bad request - 400", "El servidor no pudo interpretar la solicitud HTTP, por un error en la solicitud");
        }
        else if(code>=300){
            status = new StatusCode(code, "Not found - 300", "La URi del recurso HTTP solicitado no esta disponible ");
        }
        else if (code >= 200) {
            status = new StatusCode(code, "Status OK - 200", "La solicitud Http se ha procesado con exito");
        } else {
            status = new StatusCode(code, "Status Not Found", "");
        }
        return status;
    }
    
    public static StatusCode get(HttpResponse response) {

        int code = response.getStatus();
        StatusCode status = get(code);
        status.setResponse(response.getBody().toString());
        return status;
    }

}
