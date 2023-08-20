/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.logic;

import co.gov.deajvpar.gestcontractclient.fx.Exceptions.HttpResponseException;
import co.gov.deajvpar.gestcontractclient.fx.data.CrudApiAnotacion;
import co.gov.deajvpar.gestcontractclient.fx.data.CrudApiContrato;
import co.gov.deajvpar.gestcontractclient.fx.dtos.AnotacionContratoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.ContratoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.DireccionSeccionalDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.GeneroPersonaDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUserDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUsuarioSingleton;
import co.gov.deajvpar.gestcontractclient.fx.dtos.UserDto;
import co.gov.deajvpar.gestcontractclient.fx.utility.ComboBoxAutoComplete;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import org.controlsfx.control.textfield.TextFields;

/**
 *
 * @author Jairo F
 */
public class GestionNovedades {
    
    private SesionUserDto sesion;
    private final CrudApiContrato apiContrato;
    private final CrudApiAnotacion apiAnotacion;
    private Map<String, ContratoDto> mapContratos;
    private ContratoDto selectedContrato;
   

    public GestionNovedades() {
        this.sesion = SesionUsuarioSingleton.get();
        this.apiContrato = new CrudApiContrato();
        this.sesion.setPrivilegiosModulo();
        this.mapContratos = new HashMap();
        this.apiAnotacion=new CrudApiAnotacion();
        this.sesion.setPrivilegiosModulo();
    }
    
    public void setAutoCompletText(TextField text){
        
        ContratoDto contratoDto = new ContratoDto();
        contratoDto.setDireccion(this.sesion.getUserDeaj());
        contratoDto.setEstadoContrato(null);
        ContratoDto[] listContratos = this.apiContrato.getAllByDeaj(contratoDto);
        for(ContratoDto c: listContratos){
            this.mapContratos.put(c.toString(), c);
        }
        TextFields.bindAutoCompletion(text, listContratos).setPrefWidth(500);
     
    }
    
     public void setAutoCompletCombo(ComboBox<ContratoDto> combo){
        
         
        ContratoDto contratoDto = new ContratoDto();
        contratoDto.setDireccion(this.sesion.getUserDeaj());
        contratoDto.setEstadoContrato(null);
        ContratoDto[] listContratos = this.apiContrato.getAllByDeaj(contratoDto);
       combo.getItems().clear();
       combo.getItems().addAll(listContratos);
       ComboBoxAutoComplete<ContratoDto> comboBoxAutoComplete = new ComboBoxAutoComplete<>(combo);
    
    }
     
     public void selectedContrato(String key){
         this.selectedContrato = this.mapContratos.get(key);
     }
     
     public ContratoDto getSelectedContrato(){
         return this.selectedContrato;
     }
    
      public void generateListViewNovedades(ListView<AnotacionContratoDto> listView) throws HttpResponseException {

        String PATH_IMAGES = "co/gov/deajvpar/gestcontractclient/images/";
        String PATH_ANOTACION = PATH_IMAGES + "anotacion.png";
        Image ANOTACION = new Image(PATH_ANOTACION);
        
        Image[] listOfImages = {ANOTACION};
        List<AnotacionContratoDto> list = this.selectedContrato.getListAnotacionesContrato();
        ObservableList<AnotacionContratoDto> items = FXCollections.observableArrayList(list);
        listView.setItems(items);
//        System.out.println(items.size());
        if (listView.getItems().size() > 0) {
            listView.setCellFactory(param -> {
                return new ListCell<AnotacionContratoDto>() {
                    private final ImageView imageView = new ImageView();

                    @Override
                    public void updateItem(AnotacionContratoDto anotacion, boolean empty) {
                        super.updateItem(anotacion, empty);
                        if (empty) {
                            setText(null);
                            setGraphic(null);
                        } else {

                            imageView.setImage(listOfImages[0]);
                            setText("  " + anotacion.getFecha().toString() +"\n" +
                                    "  " +anotacion.getCreateByUser().getUserName()+" - "+anotacion.getCreateByUser().getpNombre()+" "+anotacion.getCreateByUser().getpApellido()+"\n" +
                                    "  " +anotacion.getDescripcion()+"\n\n");
                            
                            setGraphic(imageView);         // setGraphic HBox
                        }
                    }
                };
            });
        }
        if (listView.getItems().size() > 0) {
            listView.getSelectionModel().select(listView.getItems().size()-1);
        }
        //listView.setBackground(Background.EMPTY);

    }
      
    public void guardarNovedad(String description){
        
       AnotacionContratoDto novedad = new AnotacionContratoDto(LocalDate.now(),
                                                                description, 
                                                                this.sesion.getUser(), this.selectedContrato);
       
       this.apiAnotacion.save(novedad);
       
        
        
    }
    
    public void editarNovedad(AnotacionContratoDto anotacion){
       
       
       this.apiAnotacion.edit(anotacion);
       
        
    }
    
    public void eliminarNovedad(AnotacionContratoDto anotacion){
       
       
       this.apiAnotacion.delete(anotacion);
       
        
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
