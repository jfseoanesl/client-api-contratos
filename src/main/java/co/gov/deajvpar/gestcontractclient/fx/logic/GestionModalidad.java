/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.logic;

import co.gov.deajvpar.gestcontractclient.fx.data.CrudApiModalidad;
import co.gov.deajvpar.gestcontractclient.fx.data.ICrudGeneric;
import co.gov.deajvpar.gestcontractclient.fx.dtos.ModalidadDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUserDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUsuarioSingleton;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SubModalidadDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.UsuarioDto;
import java.util.List;


/**
 *
 * @author Jairo F
 */
public class GestionModalidad implements ICrudGeneric<ModalidadDto> {
    
    private ICrudGeneric<ModalidadDto> apiData;
    
    private ModalidadDto[] modalidadList;
    private SubModalidadDto[] subModalidadList;
    private SesionUserDto sesion;
    private List<SubModalidadDto> submodalidadListOfNewModalidad;
    private List<SubModalidadDto> submodalidadListDeleteOfModalidad;
    private boolean create, view, delete, update;
    
    public GestionModalidad() {
        this.apiData = new CrudApiModalidad();
        this.sesion = SesionUsuarioSingleton.get();
        this.sesion.setPrivilegiosModulo();
    }

    @Override
    public ModalidadDto save(ModalidadDto obj) {
        return this.apiData.save(obj);
    }

    @Override
    public ModalidadDto edit(ModalidadDto obj) {
        return this.apiData.edit(obj);
    }

    @Override
    public ModalidadDto delete(ModalidadDto obj) {
        return this.apiData.save(obj);
    }

    @Override
    public ModalidadDto[] getAll() {
        this.modalidadList=this.apiData.getAll();
        return this.modalidadList;          
    }
    
    public UsuarioDto getSesionUser(){
            return this.sesion.getUser();
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
    
    public ModalidadDto getModalidadFromList(Long id) {
        for (ModalidadDto m : this.getModalidadList()) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        return null;
    }
    
    public SubModalidadDto getSubModalidadFromArray(Long id) {
        for (SubModalidadDto s : this.subModalidadList) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    /**
     * @return the modalidadList
     */
    public ModalidadDto[] getModalidadList() {
        return modalidadList;
    }

    /**
     * @param modalidadList the modalidadList to set
     */
    public void setModalidadList(ModalidadDto[] modalidadList) {
        this.modalidadList = modalidadList;
    }

    /**
     * @return the subModalidadList
     */
    public SubModalidadDto[] getSubModalidadList() {
        return subModalidadList;
    }

    /**
     * @param subModalidadList the subModalidadList to set
     */
    public void setSubModalidadList(SubModalidadDto[] subModalidadList) {
        this.subModalidadList = subModalidadList;
    }

    /**
     * @return the submodalidadListOfNewModalidad
     */
    public List<SubModalidadDto> getNewSubmodalidadList() {
        return submodalidadListOfNewModalidad;
    }

    /**
     * @param submodalidadListOfNewModalidad the submodalidadListOfNewModalidad to set
     */
    public void setNewSubmodalidadList(List<SubModalidadDto> submodalidadListOfNewModalidad) {
        this.submodalidadListOfNewModalidad = submodalidadListOfNewModalidad;
    }

    /**
     * @return the submodalidadListDeleteOfModalidad
     */
    public List<SubModalidadDto> getSubmodalidadListForDelete() {
        return submodalidadListDeleteOfModalidad;
    }

    /**
     * @param submodalidadListDeleteOfModalidad the submodalidadListDeleteOfModalidad to set
     */
    public void setSubmodalidadListForDelete(List<SubModalidadDto> submodalidadListDeleteOfModalidad) {
        this.submodalidadListDeleteOfModalidad = submodalidadListDeleteOfModalidad;
    }
    
    public void deleteFromListSubmodalidad(List<SubModalidadDto> list, int index){
        list.remove(index);
    }

   
}
