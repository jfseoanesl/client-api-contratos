/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.dtos;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Jairo F
 */
public class PersonaDto {

    private Long idPersona;
    private TipoDocumentoDto tipoDocumento;
    private String noDocumento;
    private List<ContratoDto> listContratosSuscritos;
    private String pNombre;
    private String sNombre;
    private String pApellido;
    private String sApellido;
    private String tipoPersona;
    private String genero;
    private LocalDate fechaNacimiento;
    private String nombreEmpresa;

    public PersonaDto() {
    }

    /**
     * @return the idPersona
     */
    public Long getIdPersona() {
        return idPersona;
    }

    /**
     * @param idPersona the idPersona to set
     */
    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    /**
     * @return the tipoDocumento
     */
    public TipoDocumentoDto getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * @param tipoDocumento the tipoDocumento to set
     */
    public void setTipoDocumento(TipoDocumentoDto tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * @return the noDocumento
     */
    public String getNoDocumento() {
        return noDocumento;
    }

    /**
     * @param noDocumento the noDocumento to set
     */
    public void setNoDocumento(String noDocumento) {
        this.noDocumento = noDocumento;
    }

    /**
     * @return the listContratosSuscritos
     */
    public List<ContratoDto> getListContratosSuscritos() {
        return listContratosSuscritos;
    }

    /**
     * @param listContratosSuscritos the listContratosSuscritos to set
     */
    public void setListContratosSuscritos(List<ContratoDto> listContratosSuscritos) {
        this.listContratosSuscritos = listContratosSuscritos;
    }

    /**
     * @return the pNombre
     */
    public String getpNombre() {
        return pNombre;
    }

    /**
     * @param pNombre the pNombre to set
     */
    public void setpNombre(String pNombre) {
        this.pNombre = pNombre;
    }

    /**
     * @return the sNombre
     */
    public String getsNombre() {
        return sNombre;
    }

    /**
     * @param sNombre the sNombre to set
     */
    public void setsNombre(String sNombre) {
        this.sNombre = sNombre;
    }

    /**
     * @return the pApellido
     */
    public String getpApellido() {
        return pApellido;
    }

    /**
     * @param pApellido the pApellido to set
     */
    public void setpApellido(String pApellido) {
        this.pApellido = pApellido;
    }

    /**
     * @return the sApellido
     */
    public String getsApellido() {
        return sApellido;
    }

    /**
     * @param sApellido the sApellido to set
     */
    public void setsApellido(String sApellido) {
        this.sApellido = sApellido;
    }

    /**
     * @return the tipoPersona
     */
    public String getTipoPersona() {
        return tipoPersona;
    }

    /**
     * @param tipoPersona the tipoPersona to set
     */
    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * @return the fechaNacimiento
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the nombreEmpresa
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * @param nombreEmpresa the nombreEmpresa to set
     */
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    @Override
    public String toString() {
        return "PersonaDto{" + "idPersona=" + idPersona + ", tipoDocumento=" + tipoDocumento + ", noDocumento=" + noDocumento + ", listContratosSuscritos=" + listContratosSuscritos + ", pNombre=" + pNombre + ", sNombre=" + sNombre + ", pApellido=" + pApellido + ", sApellido=" + sApellido + ", tipoPersona=" + tipoPersona + ", genero=" + genero + ", fechaNacimiento=" + fechaNacimiento + ", nombreEmpresa=" + nombreEmpresa + '}';
    }
    
    
}
