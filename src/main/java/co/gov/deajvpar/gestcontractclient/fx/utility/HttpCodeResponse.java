/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.utility;

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
    
    
    public static StatusCode get(int code){
        
        StatusCode status;
        switch(code){
            
            case 200: status = new StatusCode(200, "Status OK");
                      break;
            case 404: status = new StatusCode(404, "Status Not Found");
                      break;    
            default:  status = new StatusCode(404, "Status Not Found");         
        
        }
        return status;
    }
    
    
}
