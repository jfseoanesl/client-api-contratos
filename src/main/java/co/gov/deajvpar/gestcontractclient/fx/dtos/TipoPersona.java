package co.gov.deajvpar.gestcontractclient.fx.dtos;

/**
 *
 * @author Jairo F
 */
public enum TipoPersona {
    NATURAL, JURIDICA;
    
    
    public static TipoPersona get(int value){
    
        if(value==0) return  NATURAL;
        else return JURIDICA;
    }
}
