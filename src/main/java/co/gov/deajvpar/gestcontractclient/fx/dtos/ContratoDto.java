/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jairo F
 */
public class ContratoDto {

    private Long id;
    private String noProceso;
    private int aInicioVigencia;
    private int aFinVigencia;
    private String noContrato;
    private EstadoContratoDto estadoContrato;
    private EtapaContractualDto etapaContrato;
    private String objetoContrato;
    private LocalDate fechaInicioEjecucion;
    private LocalDate fechaFinEjecucion;
    private int duracionContrato; // meses
    private double cuantiaInicialContrato;
    private double cuantiaFinalContrato;
    private double pEjecucionFinanciera;
    private double pEjecucionFisica;
    private LocalDate fechaInicioProceso;
    private String enlaceSecop;
    private String enlaceWeb;
    private LocalDate fechaPublicacionSecop;
    private LocalDate fechaAperturaProceso;
    private LocalDate fechaCierreProceso;
    private LocalDate fechaSuscripcionContrato;
    private LocalDate fechaAdjudicacionContrato;
    private LocalDate fechaTerminacionContrato;
    private LocalDate fechaLiquidacionContrato;
    private LocalDate fechaDesignacionInterventor;
    private LocalDate fechaCierreContrato;
    private List<LugarEjecucionDto> lugarEjecucion;
    private PersonaDto contratistaAdjudicado;
    private ModalidadDto modalidad;
    private SubModalidadDto subModalidad;
    private TipoContratoDto tipoContrato;
    private DireccionSeccionalDto direccion;
    private UserDto supervisor;
    private List<InformeSupervisorDto> listInformesSupervicion;
    private List<AnotacionContratoDto> listAnotacionesContrato;
    private boolean eliminado;
    private Long idUserCreated;

    public ContratoDto() {
        this.estadoContrato = EstadoContratoDto.PLANEACION;
        this.etapaContrato = EtapaContractualDto.PRECONTRACTUAL;
        this.listAnotacionesContrato = new ArrayList();
        this.listInformesSupervicion = new ArrayList();
        this.eliminado = false;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the noProceso
     */
    public String getNoProceso() {
        return noProceso;
    }

    /**
     * @param noProceso the noProceso to set
     */
    public void setNoProceso(String noProceso) {
        this.noProceso = noProceso;
    }

    /**
     * @return the aInicioVigencia
     */
    public int getaInicioVigencia() {
        return aInicioVigencia;
    }

    /**
     * @param aInicioVigencia the aInicioVigencia to set
     */
    public void setaInicioVigencia(int aInicioVigencia) {
        this.aInicioVigencia = aInicioVigencia;
    }

    /**
     * @return the aFinVigencia
     */
    public int getaFinVigencia() {
        return aFinVigencia;
    }

    /**
     * @param aFinVigencia the aFinVigencia to set
     */
    public void setaFinVigencia(int aFinVigencia) {
        this.aFinVigencia = aFinVigencia;
    }

    /**
     * @return the noContrato
     */
    public String getNoContrato() {
        return noContrato;
    }

    /**
     * @param noContrato the noContrato to set
     */
    public void setNoContrato(String noContrato) {
        this.noContrato = noContrato;
    }

    /**
     * @return the estadoContrato
     */
    public EstadoContratoDto getEstadoContrato() {
        return estadoContrato;
    }

    /**
     * @param estadoContrato the estadoContrato to set
     */
    public void setEstadoContrato(EstadoContratoDto estadoContrato) {
        this.estadoContrato = estadoContrato;
    }

    /**
     * @return the etapaContrato
     */
    public EtapaContractualDto getEtapaContrato() {
        return etapaContrato;
    }

    /**
     * @param etapaContrato the etapaContrato to set
     */
    public void setEtapaContrato(EtapaContractualDto etapaContrato) {
        this.etapaContrato = etapaContrato;
    }

    /**
     * @return the objetoContrato
     */
    public String getObjetoContrato() {
        return objetoContrato;
    }

    /**
     * @param objetoContrato the objetoContrato to set
     */
    public void setObjetoContrato(String objetoContrato) {
        this.objetoContrato = objetoContrato;
    }

    /**
     * @return the fechaInicioEjecucion
     */
    public LocalDate getFechaInicioEjecucion() {
        return fechaInicioEjecucion;
    }

    /**
     * @param fechaInicioEjecucion the fechaInicioEjecucion to set
     */
    public void setFechaInicioEjecucion(LocalDate fechaInicioEjecucion) {
        this.fechaInicioEjecucion = fechaInicioEjecucion;
    }

    /**
     * @return the fechaFinEjecucion
     */
    public LocalDate getFechaFinEjecucion() {
        return fechaFinEjecucion;
    }

    /**
     * @param fechaFinEjecucion the fechaFinEjecucion to set
     */
    public void setFechaFinEjecucion(LocalDate fechaFinEjecucion) {
        this.fechaFinEjecucion = fechaFinEjecucion;
    }

    /**
     * @return the duracionContrato
     */
    public int getDuracionContrato() {
        return duracionContrato;
    }

    /**
     * @param duracionContrato the duracionContrato to set
     */
    public void setDuracionContrato(int duracionContrato) {
        this.duracionContrato = duracionContrato;
    }

    /**
     * @return the cuantiaInicialContrato
     */
    public double getCuantiaInicialContrato() {
        return cuantiaInicialContrato;
    }

    /**
     * @param cuantiaInicialContrato the cuantiaInicialContrato to set
     */
    public void setCuantiaInicialContrato(double cuantiaInicialContrato) {
        this.cuantiaInicialContrato = cuantiaInicialContrato;
    }

    /**
     * @return the cuantiaFinalContrato
     */
    public double getCuantiaFinalContrato() {
        return cuantiaFinalContrato;
    }

    /**
     * @param cuantiaFinalContrato the cuantiaFinalContrato to set
     */
    public void setCuantiaFinalContrato(double cuantiaFinalContrato) {
        this.cuantiaFinalContrato = cuantiaFinalContrato;
    }

    /**
     * @return the pEjecucionFinanciera
     */
    public double getpEjecucionFinanciera() {
        return pEjecucionFinanciera;
    }

    /**
     * @param pEjecucionFinanciera the pEjecucionFinanciera to set
     */
    public void setpEjecucionFinanciera(double pEjecucionFinanciera) {
        this.pEjecucionFinanciera = pEjecucionFinanciera;
    }

    /**
     * @return the pEjecucionFisica
     */
    public double getpEjecucionFisica() {
        return pEjecucionFisica;
    }

    /**
     * @param pEjecucionFisica the pEjecucionFisica to set
     */
    public void setpEjecucionFisica(double pEjecucionFisica) {
        this.pEjecucionFisica = pEjecucionFisica;
    }

    /**
     * @return the fechaInicioProceso
     */
    public LocalDate getFechaInicioProceso() {
        return fechaInicioProceso;
    }

    /**
     * @param fechaInicioProceso the fechaInicioProceso to set
     */
    public void setFechaInicioProceso(LocalDate fechaInicioProceso) {
        this.fechaInicioProceso = fechaInicioProceso;
    }

    /**
     * @return the enlaceSecop
     */
    public String getEnlaceSecop() {
        return enlaceSecop;
    }

    /**
     * @param enlaceSecop the enlaceSecop to set
     */
    public void setEnlaceSecop(String enlaceSecop) {
        this.enlaceSecop = enlaceSecop;
    }

    /**
     * @return the enlaceWeb
     */
    public String getEnlaceWeb() {
        return enlaceWeb;
    }

    /**
     * @param enlaceWeb the enlaceWeb to set
     */
    public void setEnlaceWeb(String enlaceWeb) {
        this.enlaceWeb = enlaceWeb;
    }

    /**
     * @return the fechaPublicacionSecop
     */
    public LocalDate getFechaPublicacionSecop() {
        return fechaPublicacionSecop;
    }

    /**
     * @param fechaPublicacionSecop the fechaPublicacionSecop to set
     */
    public void setFechaPublicacionSecop(LocalDate fechaPublicacionSecop) {
        this.fechaPublicacionSecop = fechaPublicacionSecop;
    }

    /**
     * @return the fechaAperturaProceso
     */
    public LocalDate getFechaAperturaProceso() {
        return fechaAperturaProceso;
    }

    /**
     * @param fechaAperturaProceso the fechaAperturaProceso to set
     */
    public void setFechaAperturaProceso(LocalDate fechaAperturaProceso) {
        this.fechaAperturaProceso = fechaAperturaProceso;
    }

    /**
     * @return the fechaCierreProceso
     */
    public LocalDate getFechaCierreProceso() {
        return fechaCierreProceso;
    }

    /**
     * @param fechaCierreProceso the fechaCierreProceso to set
     */
    public void setFechaCierreProceso(LocalDate fechaCierreProceso) {
        this.fechaCierreProceso = fechaCierreProceso;
    }

    /**
     * @return the fechaSuscripcionContrato
     */
    public LocalDate getFechaSuscripcionContrato() {
        return fechaSuscripcionContrato;
    }

    /**
     * @param fechaSuscripcionContrato the fechaSuscripcionContrato to set
     */
    public void setFechaSuscripcionContrato(LocalDate fechaSuscripcionContrato) {
        this.fechaSuscripcionContrato = fechaSuscripcionContrato;
    }

    /**
     * @return the fechaAdjudicacionContrato
     */
    public LocalDate getFechaAdjudicacionContrato() {
        return fechaAdjudicacionContrato;
    }

    /**
     * @param fechaAdjudicacionContrato the fechaAdjudicacionContrato to set
     */
    public void setFechaAdjudicacionContrato(LocalDate fechaAdjudicacionContrato) {
        this.fechaAdjudicacionContrato = fechaAdjudicacionContrato;
    }

    /**
     * @return the fechaTerminacionContrato
     */
    public LocalDate getFechaTerminacionContrato() {
        return fechaTerminacionContrato;
    }

    /**
     * @param fechaTerminacionContrato the fechaTerminacionContrato to set
     */
    public void setFechaTerminacionContrato(LocalDate fechaTerminacionContrato) {
        this.fechaTerminacionContrato = fechaTerminacionContrato;
    }

    /**
     * @return the fechaLiquidacionContrato
     */
    public LocalDate getFechaLiquidacionContrato() {
        return fechaLiquidacionContrato;
    }

    /**
     * @param fechaLiquidacionContrato the fechaLiquidacionContrato to set
     */
    public void setFechaLiquidacionContrato(LocalDate fechaLiquidacionContrato) {
        this.fechaLiquidacionContrato = fechaLiquidacionContrato;
    }

    /**
     * @return the fechaDesignacionInterventor
     */
    public LocalDate getFechaDesignacionInterventor() {
        return fechaDesignacionInterventor;
    }

    /**
     * @param fechaDesignacionInterventor the fechaDesignacionInterventor to set
     */
    public void setFechaDesignacionInterventor(LocalDate fechaDesignacionInterventor) {
        this.fechaDesignacionInterventor = fechaDesignacionInterventor;
    }

    /**
     * @return the fechaCierreContrato
     */
    public LocalDate getFechaCierreContrato() {
        return fechaCierreContrato;
    }

    /**
     * @param fechaCierreContrato the fechaCierreContrato to set
     */
    public void setFechaCierreContrato(LocalDate fechaCierreContrato) {
        this.fechaCierreContrato = fechaCierreContrato;
    }

    /**
     * @return the lugarEjecucion
     */
    public List<LugarEjecucionDto> getLugarEjecucion() {
        return lugarEjecucion;
    }

    /**
     * @param lugarEjecucion the lugarEjecucion to set
     */
    public void setLugarEjecucion(List<LugarEjecucionDto> lugarEjecucion) {
        this.lugarEjecucion = lugarEjecucion;
    }

    /**
     * @return the contratistaAdjudicado
     */
    public PersonaDto getContratistaAdjudicado() {
        return contratistaAdjudicado;
    }

    /**
     * @param contratistaAdjudicado the contratistaAdjudicado to set
     */
    public void setContratistaAdjudicado(PersonaDto contratistaAdjudicado) {
        this.contratistaAdjudicado = contratistaAdjudicado;
    }

    /**
     * @return the modalidad
     */
    public ModalidadDto getModalidad() {
        return modalidad;
    }

    /**
     * @param modalidad the modalidad to set
     */
    public void setModalidad(ModalidadDto modalidad) {
        this.modalidad = modalidad;
    }

    /**
     * @return the subModalidad
     */
    public SubModalidadDto getSubModalidad() {
        return subModalidad;
    }

    /**
     * @param subModalidad the subModalidad to set
     */
    public void setSubModalidad(SubModalidadDto subModalidad) {
        this.subModalidad = subModalidad;
    }

    /**
     * @return the tipoContrato
     */
    public TipoContratoDto getTipoContrato() {
        return tipoContrato;
    }

    /**
     * @param tipoContrato the tipoContrato to set
     */
    public void setTipoContrato(TipoContratoDto tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    /**
     * @return the direccion
     */
    public DireccionSeccionalDto getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(DireccionSeccionalDto direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the supervisor
     */
    public UserDto getSupervisor() {
        return supervisor;
    }

    /**
     * @param supervisor the supervisor to set
     */
    public void setSupervisor(UserDto supervisor) {
        this.supervisor = supervisor;
    }

    /**
     * @return the listInformesSupervicion
     */
    public List<InformeSupervisorDto> getListInformesSupervicion() {
        return listInformesSupervicion;
    }

    /**
     * @param listInformesSupervicion the listInformesSupervicion to set
     */
    public void setListInformesSupervicion(List<InformeSupervisorDto> listInformesSupervicion) {
        this.listInformesSupervicion = listInformesSupervicion;
    }

    /**
     * @return the listAnotacionesContrato
     */
    public List<AnotacionContratoDto> getListAnotacionesContrato() {
        return listAnotacionesContrato;
    }

    /**
     * @param listAnotacionesContrato the listAnotacionesContrato to set
     */
    public void setListAnotacionesContrato(List<AnotacionContratoDto> listAnotacionesContrato) {
        this.listAnotacionesContrato = listAnotacionesContrato;
    }

    /**
     * @return the eliminado
     */
    public boolean isEliminado() {
        return eliminado;
    }

    /**
     * @param eliminado the eliminado to set
     */
    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public String getDataLugarEjecucion() {

        String lugar = "";
        if (this.lugarEjecucion != null) {
            int i = 0;
            for (LugarEjecucionDto l : this.lugarEjecucion) {
                if (i == 0) {
                    lugar = l.getDpto().getNombreDpto() + "-" + l.getCiudad().getNombreCiudad();
                    i++;
                } else {
                    lugar = lugar + ", " + l.toString();
                }
            }
        }
        return lugar;
    }

    @Override
    public String toString() {
        return "No contrato: " + noContrato + " - No Proceso: " + noProceso + " - Vigencia: " + aInicioVigencia ;
    }

    /**
     * @return the idUserCreated
     */
    public Long getIdUserCreated() {
        return idUserCreated;
    }

    /**
     * @param idUserCreated the idUserCreated to set
     */
    public void setIdUserCreated(Long idUserCreated) {
        this.idUserCreated = idUserCreated;
    }
    
    

}
