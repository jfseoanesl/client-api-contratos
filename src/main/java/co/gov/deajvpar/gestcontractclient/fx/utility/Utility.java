/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.utility;

import at.favre.lib.crypto.bcrypt.BCrypt;
import co.gov.deajvpar.gestcontractclient.fx.App;
import co.gov.deajvpar.gestcontractclient.fx.dtos.CiudadDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.CiudadDptoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.DptoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.ModuloDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUsuarioSingleton;
import co.gov.deajvpar.gestcontractclient.fx.dtos.UserDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.chart.ChartDto;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;

/**
 *
 * @author Jairo F
 */
public class Utility {

    public static final String TEXT_ONLY = "[a-zA-Z]+";
    public static final String INTEGERS_ONLY = "\\d+";
    public static final String DOUBLES = "\\d+\\.\\d+";
    public static final String LARGE_NUMBER = "\\d*|\\d+\\,\\d*";
    public static final String DATE_YYYYMMDD = "((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])";
    public static final String URL = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    public static final String EMAIL = "^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@"
            + "[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$";

    public static Map<String, DptoDto> listCiudadesToMapDpto(CiudadDptoDto[] list) {

        Map<String, DptoDto> dptos = new HashMap();
        for (CiudadDptoDto c : list) {

            DptoDto dpto = dptos.get(c.getC_digo_dane_del_departamento());
            if (dpto == null) {
                dpto = new DptoDto(c.getC_digo_dane_del_departamento(), c.getDepartamento());
            }
            CiudadDto ciudad = new CiudadDto(c.getC_digo_dane_del_municipio(), c.getMunicipio());
            dpto.getListCiudades().add(ciudad);
            dptos.put(c.getC_digo_dane_del_departamento(), dpto);

        }
        return dptos;
    }

    public static boolean validateEmptyComponentCombo(ComboBox list, Label error) {

        int index = list.getSelectionModel().getSelectedIndex();
        boolean empty = list.getSelectionModel().getSelectedIndex() == -1;
        if (empty) {
            list.requestFocus();
        }
        error.setVisible(empty);
        return !empty;

    }

    public static boolean validateEmptyComponentTextArea(TextArea text, Label error) {

        boolean empty = text.getText() == null || text.getText().isBlank();
        if (empty) {
            text.requestFocus();
        }
        error.setVisible(empty);
        return !empty;

    }

    public static boolean validateEmptyComponentTextField(TextField text, Label error) {

        boolean empty = text.getText() == null || text.getText().isBlank();

        if (empty) {
            text.requestFocus();
        }
        error.setVisible(empty);
        return !empty;

    }

    public static boolean validateEmptyComponentPasswordField(PasswordField text, Label error) {

        boolean empty = text.getText() == null || text.getText().isBlank();

        if (empty) {
            text.requestFocus();
        }
        error.setVisible(empty);
        return !empty;

    }

    public static boolean validateEmptyComponentDatePicker(DatePicker dp, Label error) {

        boolean empty = dp.getValue() == null;

        if (empty) {
            dp.requestFocus();
        }
        error.setVisible(empty);
        return !empty;

    }

    public static boolean validateEmptyComponentTextField(TextField text) {

        boolean empty = text.getText() == null || text.getText().isBlank();

        if (empty) {
            text.requestFocus();
        }
        return !empty;

    }

    public static void encryptPassword(UserDto dto) {
        dto.setPassword(BCrypt.withDefaults().hashToString(12, dto.getPassword().toCharArray()));
    }

    public static String encryptPassword(String clave) {
        return BCrypt.withDefaults().hashToString(12, clave.toCharArray());
        // return clave;
    }

    public static void salir() {
        System.exit(0);
    }

    //no funciona hay que validarlo
    public static void setMaskFormattedTextFieldNoProceso(TextField tf) {

        UnaryOperator<TextFormatter.Change> noProceso = change -> {
            if (change.getText().matches("[a-zA-Z]-[0-9]{3}-((19|2[0-9])[0-9]{2})")) {
                return change; //if change is a number
            } else {
                change.setText(""); //else make no change
                change.setRange( //don't remove any selected text either.
                        change.getRangeStart(),
                        change.getRangeStart()
                );
                return change;
            }
        };
        tf.setTextFormatter(new TextFormatter<>(noProceso));
        tf.setText("A-000-2023");
    }

    public static void setMaskFormattedCurrencyTextField(TextField tf) {
        NumberFormat f = NumberFormat.getCurrencyInstance();

        StringConverter<Double> currencyStringConverter = new StringConverter<>() {
            @Override
            public String toString(Double object) {

                return f.format(object);
            }

            @Override
            public Double fromString(String data) {

                Double valor = Double.parseDouble(data);
                return valor;
            }
        };

        TextFormatter<Double> formatter = new TextFormatter<>(
                currencyStringConverter, //converter
                0.00
        );

        tf.setTextFormatter(formatter);
    }

    public static void setMaskFormattedTextField(TextField tf, String match) {

        UnaryOperator<TextFormatter.Change> textFormat = change -> {
            if (change.getText().matches(match)) {
                return change;
            } else {
                change.setText(""); //else make no change
                change.setRange( //don't remove any selected text either.
                        change.getRangeStart(),
                        change.getRangeStart()
                );
                return change;
            }
        };
        tf.setTextFormatter(new TextFormatter<>(textFormat));
        tf.setText("");
    }

    public static void setModulo(ModuloDto module, HBox root, String children) throws IOException {

        if (module != null) {
            SesionUsuarioSingleton.get().setModuloActive(module);
            if (children != null) {
                Node compo = (Node) root.getParent().getParent();
                StackPane pane = (StackPane) compo;
                pane.getChildren().clear();
                HBox childrenPanel = new HBox(App.loadFXML(children));
                pane.getChildren().add(childrenPanel);
                childrenPanel.setVisible(true);
            }
        }

    }

    public static void setModuloHBox(ModuloDto module, HBox root, String children) throws IOException {

        if (module != null) {
            SesionUsuarioSingleton.get().setModuloActive(module);
            if (children != null) {
                Node compo = (Node) root.getParent().getParent();
                StackPane pane = (StackPane) compo;
                HBox hbox = (HBox) (pane.getParent());
                VBox vbox = (VBox) hbox.getParent();
                
                pane.getChildren().clear();
                HBox node = new HBox(App.loadFXML(children));

                node.setMaxSize(vbox.getWidth(), vbox.getHeight());
                node.setPrefSize(vbox.getWidth(), vbox.getHeight());
                pane.getChildren().add(node);

            }
        }

    }

    public static void setGraficaPie(PieChart chart, List<ChartDto> datos) {
        chart.setLabelsVisible(false);
        ObservableList<PieChart.Data> dataList = FXCollections.observableArrayList();
        for (ChartDto c : datos) {
            PieChart.Data d = new PieChart.Data(c.getDescripcion(), c.getValor());
            dataList.add(d);
        }
        chart.setData(dataList);

        final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 10 arial;");

        for (final PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());
                    caption.setText(String.valueOf(data.getPieValue()) + "%");
                }
            });
        }

    }
    
    public static void setGraficaBar(BarChart chart, List<ChartDto> datos){
        chart.setLegendVisible(false);
        List<XYChart.Series> series = new ArrayList();
        for(ChartDto d: datos){
            XYChart.Series<String, Number> serie = new XYChart.Series<>();
            serie.getData().add(new XYChart.Data<>(d.getDescripcion(), d.getValor()));
            series.add(serie);
        }
        chart.getData().addAll(series);
    
    }
    
    public static void setGraficaLine(LineChart chart, List<ChartDto> datos, String titleSerie){
        
        XYChart.Series<String,Number> series = new XYChart.Series();
        series.setName(titleSerie);
        
        for(ChartDto d: datos){
            series.getData().add(new XYChart.Data(d.getDescripcion(), d.getValor()));
        }
        chart.getData().addAll(series);
    }
    
    public static List<ChartDto> getDataChart(String data[]){
        
            List<ChartDto> report = new ArrayList();
            for (String s : data) {
                ChartDto dto = new ChartDto();
                String values[] = s.split(",");
                dto.setDescripcion(values[0]);
                dto.setValor(Integer.valueOf(values[1]));
                report.add(dto);
            }
            return report;
    }

}
