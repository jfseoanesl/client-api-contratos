/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.logic;

import co.gov.deajvpar.gestcontractclient.fx.data.CrudApiDeaj;
import co.gov.deajvpar.gestcontractclient.fx.data.ICrudGeneric;
import co.gov.deajvpar.gestcontractclient.fx.dtos.DireccionSeccionalDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.DptoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUserDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUsuarioSingleton;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jairo F
 */
public class GestionDeaj implements ICrudGeneric<DireccionSeccionalDto> {

    private List<DptoDto> dptoList;
    private List<DptoDto> listDptoSeleccionados;
    private SesionUserDto sesion;
    private DireccionSeccionalDto[] deajs;
    private GestionDpto logicDpto;
    private CrudApiDeaj apiData;
   

    public GestionDeaj() {
        this.apiData = new CrudApiDeaj();
        this.logicDpto = new GestionDpto();
        this.sesion = SesionUsuarioSingleton.get();
        this.sesion.setPrivilegiosModulo();
        this.listDptoSeleccionados = new ArrayList();
        this.dptoList = List.of(this.logicDpto.getAll());
//        this.deajs = this.apiData.getAll();
    }

    @Override
    public DireccionSeccionalDto save(DireccionSeccionalDto obj) {
        return this.apiData.save(obj);
    }

    @Override
    public DireccionSeccionalDto edit(DireccionSeccionalDto obj) {
        return this.apiData.edit(obj);
    }

    @Override
    public DireccionSeccionalDto delete(DireccionSeccionalDto obj) {
        return this.apiData.delete(obj);
    }

    @Override
    public DireccionSeccionalDto[] getAll() {
        this.deajs = this.apiData.getAll();
        return this.deajs;
    }

    public void addDptoSelected(int index) {
        if (!this.listDptoSeleccionados.contains(this.dptoList.get(index))) {
            this.listDptoSeleccionados.add(this.dptoList.get(index));
        }
    }

    public void removeDtpoSelected(int index) {
        this.listDptoSeleccionados.remove(index);
    }

    public List<DptoDto> getDptoList() {
        return this.dptoList;
    }

    public List<DptoDto> getListDptoSelected() {
        return this.listDptoSeleccionados;
    }

    public void setListDptoSelected(List<DptoDto> list) {
        this.listDptoSeleccionados = list;
    }

    public DireccionSeccionalDto getDeaj(Long id) {

        for (DireccionSeccionalDto d : this.deajs) {
            if (d.getIdDireccion().equals(id)) {
                return d;
            }
        }
        return null;
    }

    public DireccionSeccionalDto getNewDeaj(String name) {
        DireccionSeccionalDto deaj = new DireccionSeccionalDto();
        deaj.setDescripcionSeccional(name);
        deaj.setEliminado(false);
        deaj.setListDptoCoordinados(this.listDptoSeleccionados);
        deaj.setCreatedByUser(this.sesion.getUser());
        return deaj;
    }
    
    public DireccionSeccionalDto getNewDeaj(String id, String name) {
        DireccionSeccionalDto deaj = this.getNewDeaj(name);
        deaj.setIdDireccion(Long.valueOf(id));
        return deaj;
    }
    
    public boolean isCreate(){
        return this.sesion.isCreate();
    }
    public boolean isDelete(){
        return this.sesion.isDelete();
    }
    public boolean isView(){
        return this.sesion.isView();
    }
    public boolean isUpdate(){
        return this.sesion.isUpdate();
    }

}
