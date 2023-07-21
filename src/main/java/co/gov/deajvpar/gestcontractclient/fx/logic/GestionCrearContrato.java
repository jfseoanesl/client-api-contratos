/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.logic;

import co.gov.deajvpar.gestcontractclient.fx.data.CrudApiContrato;
import co.gov.deajvpar.gestcontractclient.fx.data.CrudApiSupervisor;
import co.gov.deajvpar.gestcontractclient.fx.data.ICrudGeneric;
import co.gov.deajvpar.gestcontractclient.fx.dtos.CiudadDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.ContratoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.DireccionSeccionalDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.DptoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.LugarEjecucionDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.ModalidadDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUserDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUsuarioSingleton;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SubModalidadDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.TipoContratoDto;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jairo F
 */
public class GestionCrearContrato implements ICrudGeneric<ContratoDto> {

    private final GestionTipoContrato gestionTipo;
    private final GestionModalidad gestionModalidad;
    private final GestionDeaj gestionDeaj;
    private final SesionUserDto sesion;
    private List<DireccionSeccionalDto> deajList;
    private DireccionSeccionalDto deaj;
    private ModalidadDto modalidad;
    private SubModalidadDto subModalidad;
    private DptoDto dpto;
    private CiudadDto ciudad;
    private TipoContratoDto tipoContrato;
    private List<LugarEjecucionDto> lugarEjecucionList;
    private ContratoDto contrato;
    private final CrudApiContrato apiData;

    public GestionCrearContrato() {
        this.apiData = new CrudApiContrato();
        this.gestionTipo = new GestionTipoContrato();
        this.gestionModalidad = new GestionModalidad();
        this.gestionDeaj = new GestionDeaj();
        this.sesion = SesionUsuarioSingleton.get();
        this.sesion.setPrivilegiosModulo();
        this.lugarEjecucionList = new ArrayList();
    }

    @Override
    public ContratoDto save(ContratoDto obj) {
        return this.apiData.save(obj);
    }

    @Override
    public ContratoDto edit(ContratoDto obj) {
        return null;
    }

    @Override
    public ContratoDto delete(ContratoDto obj) {
        return null;
    }

    @Override
    public ContratoDto[] getAll() {
        return this.apiData.getAll();
    }

    public List<TipoContratoDto> getListTipoContrato() {
        return List.of(this.gestionTipo.getAll());
    }

    public void setTipoContrato(int index) {
        if (this.getListTipoContrato().size() > 0 && this.getListTipoContrato().size() > index) {
            this.tipoContrato = this.getListTipoContrato().get(index);
        } else {
            this.tipoContrato = null;
        }

    }

    public List<ModalidadDto> getlistModalidades() {
        return List.of(this.gestionModalidad.getAll());
    }

    public void setModalidad(int index) {
        List<ModalidadDto> list = List.of(this.gestionModalidad.getModalidadList());
        if (list.size() > 0 && list.size() > index) {
            this.modalidad = list.get(index);
        } else {
            this.modalidad = null;
        }
        this.subModalidad = null;
        
    }

    public List<SubModalidadDto> getListSubmodalidades() {
        if (this.modalidad != null) {
            return this.modalidad.getListSubModalidades();
        }
        return null;

    }

    public void setSubModalidad(int index) {
        if (this.modalidad != null) {
            if (this.modalidad.getListSubModalidades().size() > 0 && this.modalidad.getListSubModalidades().size() > index) {
                this.subModalidad = this.modalidad.getListSubModalidades().get(index);
            } else {
                this.subModalidad = null;
            }

        } else {
            this.subModalidad = null;
        }
       
    }

    public List<DireccionSeccionalDto> getListDeaj() {

        if (this.sesion.getUserDeaj() == null) {
            this.deajList = List.of(this.gestionDeaj.getAll());
        } else {
            this.deajList = new ArrayList();
            this.deajList.add(this.sesion.getUserDeaj());
        }

        return this.deajList;

    }

    public DireccionSeccionalDto getDeaj() {
        return this.deaj;
    }

    public void setDeaj(int index) {
        if (this.deajList.size() > 0 && this.deajList.size() > index) {
            this.deaj = this.deajList.get(index);
        } else {
            this.deaj = null;
        }

    }

    public void setDpto(int index) {
        if (this.deaj != null) {
            if (this.deaj.getListDptoCoordinados().size() > 0 && this.deaj.getListDptoCoordinados().size() > index) {
                this.dpto = this.deaj.getListDptoCoordinados().get(index);
            } else {
                this.dpto = null;
            }
        } else {
            this.dpto = null;
        }

    }

    public List<DptoDto> getListDpto() {
        if (this.deaj != null) {
            return this.deaj.getListDptoCoordinados();
        }
        return null;

    }

    public List<CiudadDto> getListCiudades() {
        if (this.dpto != null) {
            return this.dpto.getListCiudades();
        }
        return null;
    }

    public void addLugarEjecucion(String dir) {

        if (this.dpto != null) {
            if (this.ciudad != null) {
                LugarEjecucionDto lugar = new LugarEjecucionDto(this.dpto, this.ciudad, dir);
                this.lugarEjecucionList.add(lugar);
            }
        }
    }

    public void removeLugarEjecucion(int index) {
        this.lugarEjecucionList.remove(index);
    }

    public List<LugarEjecucionDto> getListLugarEjecucion() {
        return this.lugarEjecucionList;
    }

    public void setCiudad(int indexCiudad) {

        if (this.dpto != null) {
            if (this.dpto.getListCiudades().size() > 0 && this.dpto.getListCiudades().size() >= indexCiudad) {
                this.ciudad = dpto.getListCiudades().get(indexCiudad);
            } else {
                this.ciudad = null;
            }

        } else {
            this.ciudad = null;
        }
    }

    public void crearContrato(String noProceso, String noContrato, String objeto, double monto,
            String enlaceSecop, LocalDate fechaApertura, LocalDate fechaInicio, int duracion) {

        this.contrato = new ContratoDto();
        this.getContrato().setNoProceso(noProceso);
        this.getContrato().setNoContrato(noContrato);
        this.getContrato().setFechaAperturaProceso(fechaApertura);
        this.getContrato().setFechaInicioProceso(fechaInicio);
        this.getContrato().setObjetoContrato(objeto);
        this.getContrato().setCuantiaInicialContrato(monto);
        this.getContrato().setCuantiaFinalContrato(monto);
        this.getContrato().setEnlaceWeb(enlaceSecop);
        this.getContrato().setDuracionContrato(duracion);
        this.getContrato().setaInicioVigencia(fechaApertura.getYear());
        this.getContrato().setaFinVigencia(fechaApertura.getYear());
        this.getContrato().setEnlaceWeb(enlaceSecop);
        this.getContrato().setEnlaceSecop(enlaceSecop);
        this.getContrato().setFechaPublicacionSecop(fechaInicio);

        this.getContrato().setTipoContrato(this.tipoContrato);
        this.getContrato().setDireccion(this.deaj);
        this.getContrato().setModalidad(this.modalidad);
        this.getContrato().setSubModalidad(this.subModalidad);
        this.getContrato().setLugarEjecucion(this.lugarEjecucionList);

    }

    public void guardarContrato() {
        if (this.getContrato() != null) {
            this.contrato = this.save(this.getContrato());
        }

    }

    /**
     * @return the contrato
     */
    public ContratoDto getContrato() {
        return contrato;
    }

    public boolean isCreate() {
        return this.sesion.isCreate();
    }

    public boolean isUpdate() {
        return this.sesion.isUpdate();
    }

    public boolean isView() {
        return this.sesion.isView();
    }

    public boolean isDelete() {
        return this.sesion.isDelete();
    }

    public void inicializar() {

        this.ciudad = null;
        this.contrato = null;
        this.deaj = null;
        this.deajList = new ArrayList();
        this.dpto = null;
        this.lugarEjecucionList = new ArrayList();
        this.modalidad = null;
        this.subModalidad = null;
        this.tipoContrato = null;

    }
}
