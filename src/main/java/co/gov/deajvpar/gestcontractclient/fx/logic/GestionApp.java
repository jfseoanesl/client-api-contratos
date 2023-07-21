/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.logic;

import co.gov.deajvpar.gestcontractclient.fx.App;
import static co.gov.deajvpar.gestcontractclient.fx.App.loadFXML;
import static co.gov.deajvpar.gestcontractclient.fx.App.sistema;
import co.gov.deajvpar.gestcontractclient.fx.Exceptions.HttpResponseException;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SistemaDto;
import co.gov.deajvpar.gestcontractclient.fx.utility.HttpCodeResponse;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyGsonMapper;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyHttpApi;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyScreen;
import co.gov.deajvpar.gestcontractclient.fx.utility.StatusCode;
import co.gov.deajvpar.gestcontractclient.fx.utility.UsedApis;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Jairo F
 */
public class GestionApp {

    public GestionApp() {
    }

    public void initApp(Stage stage) throws IOException {

        try {
            StatusCode status = MyHttpApi.jsonGetRequest2(UsedApis.API_HOME + "/");
            if (!status.statusOk()) {
                throw new HttpResponseException(status);
            }
            sistema = MyGsonMapper.get().fromJson(status.getResponse(), SistemaDto.class);
            String titulo;
            if (!sistema.isInicializado()) {
                App.scene = new Scene(App.loadFXML("Inicializar"), 1200, 640);
                titulo = "Inicializar sistema - Creacion usuario administrador";
                stage.setMaximized(false);

            } else {
                titulo = App.sistema.getNameAndTitle();
                App.scene = new Scene(App.loadFXML("Login"), MyScreen.getMaxWidth(), MyScreen.getMaxHeight());
                stage.setMaximized(true);

            }
            stage.setScene(App.scene);
            stage.setTitle(titulo);
            stage.show();

        } catch (UnirestException ex) {
            throw new HttpResponseException(ex.getMessage());
        }

    }

}
