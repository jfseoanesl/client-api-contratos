/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.logic;

import co.gov.deajvpar.gestcontractclient.fx.Exceptions.HttpResponseException;
import co.gov.deajvpar.gestcontractclient.fx.data.CrudApiContrato;
import co.gov.deajvpar.gestcontractclient.fx.dtos.ContratoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.EstadoContratoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUserDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUsuarioSingleton;
import co.gov.deajvpar.gestcontractclient.fx.dtos.chart.ChartDto;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyScreen;
import co.gov.deajvpar.gestcontractclient.fx.utility.Utility;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;

/**
 *
 * @author Jairo F
 */
public class GestionDashBoarContrato {

    private SesionUserDto sesion;
    private final CrudApiContrato apiData;
    private EstadoContratoDto estado;

    public GestionDashBoarContrato() {
        this.sesion = SesionUsuarioSingleton.get();
        this.apiData = new CrudApiContrato();
        this.sesion.setPrivilegiosModulo();
    }

    public void generateListViewContratos(ListView<ContratoDto> listView) {

        String PATH_IMAGES = "co/gov/deajvpar/gestcontractclient/images/";
        String PATH = PATH_IMAGES + "proceso.png";
        Image PROCESO = new Image(PATH);

        Image[] listOfImages = {PROCESO};
        ContratoDto[] list = this.getListContrato();
        ObservableList<ContratoDto> items = FXCollections.observableArrayList(list);
        listView.setItems(items);
//        System.out.println(items.size());
        if (listView.getItems().size() > 0) {
            listView.setCellFactory(param -> {
                return new ListCell<ContratoDto>() {
                    private final ImageView imageView = new ImageView();

                    @Override
                    public void updateItem(ContratoDto contrato, boolean empty) {
                        super.updateItem(contrato, empty);
                        if (empty) {
                            setText(null);
                            setGraphic(null);
                        } else {

                            imageView.setImage(listOfImages[0]);
                            setText(" " + contrato.getNoProceso());
                            setGraphic(imageView);         // setGraphic HBox
                        }
                    }
                };
            });
        }
        if (listView.getItems().size() > 0) {
            listView.getSelectionModel().select(0);
        }
        listView.setBackground(Background.EMPTY);

    }

    public ContratoDto[] getListContrato() {
        ContratoDto dto = new ContratoDto();
        dto.setDireccion(this.sesion.getUserDeaj());
        dto.setEstadoContrato(estado);
        ContratoDto list[] = this.apiData.getAllByDeaj(dto);
        return list;
    }

    public void setComboEstados(ComboBox combo) {

        combo.getItems().add("TODAS");
        for (EstadoContratoDto e : EstadoContratoDto.values()) {
            combo.getItems().add(e.toString());
        }
        combo.getSelectionModel().select(0);
    }

    public void setEstadoContrato(String value) {
        if (value != null) {
            this.estado = EstadoContratoDto.valueOf(value);
        } else {
            this.estado = null;
        }
    }

    public void setGraficaPie(PieChart chart, String api) {
        try {
            String[] data = this.apiData.report(api);
            List<ChartDto> report = new ArrayList();
            for (String s : data) {
                ChartDto dto = new ChartDto();
                String values[] = s.split(",");
                dto.setDescripcion(values[0]);
                dto.setValor(Integer.valueOf(values[1]));
                report.add(dto);
            }
            Utility.setGraficaPie(chart, report);
        } catch (HttpResponseException ex) {
            MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
        }
    }
    
    public void setGraficaBar(BarChart chart, String api) {
        try {
          
            List<ChartDto> report = List.of(this.apiData.reportChartDto(api));
            Utility.setGraficaBar(chart, report);
        } catch (HttpResponseException ex) {
            MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
        }
    }

    public void setGraficaLine(LineChart chart, String api, String titleSerie) {
        try {
          
            List<ChartDto> report = List.of(this.apiData.reportChartDto(api));
            Utility.setGraficaLine(chart, report, titleSerie);
        } catch (HttpResponseException ex) {
            MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
        }
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
}
