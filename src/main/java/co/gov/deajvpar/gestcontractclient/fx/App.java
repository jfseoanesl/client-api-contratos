package co.gov.deajvpar.gestcontractclient.fx;

import co.gov.deajvpar.gestcontractclient.fx.dtos.SistemaDto;
import co.gov.deajvpar.gestcontractclient.fx.utility.HttpCodeResponse;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyGsonMapper;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyHttpApi;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyScreen;
import co.gov.deajvpar.gestcontractclient.fx.utility.StatusCode;
import co.gov.deajvpar.gestcontractclient.fx.utility.UsedApis;

import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Window;

/**
 * JavaFX App
 */
public class App extends Application {
    
    private static Scene scene;
    public static SistemaDto sistema;
    
    @Override
    public void start(Stage stage) throws IOException, UnirestException {
        MyHttpApi.configureMapper();
        try {
            App.home(stage);
        } catch (UnirestException | IOException ex) {
            MyScreen.errorMessage(ex);
            System.exit(0);
        }

    }
    
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    public static Scene getScene() {
        return scene;
    }
    
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
       
    public static void main(String[] args) {
        launch();
    }
    
    
    
    private static void home(Stage stage) throws UnirestException, IOException {
        MyHttpApi.jsonGetRequest(UsedApis.API_HOME+"/");
        String response = MyHttpApi.stringResponse();
        StatusCode status = HttpCodeResponse.get(MyHttpApi.responseStatusCode());
        System.out.println(response);
        if (MyHttpApi.statusOk()) {
            sistema = MyGsonMapper.get().fromJson(response, SistemaDto.class);
            String titulo;
            if (!sistema.isInicializado()) {
                scene = new Scene(loadFXML("Inicializar"), 1200, 640);
                titulo = "Inicializar sistema - Creacion usuario administrador";
            } else {
                titulo = App.sistema.getNameAndTitle();
                scene = new Scene(loadFXML("Login"),MyScreen.getMaxWidth(), MyScreen.getMaxHeight());
            }
            stage.setScene(scene);
            stage.setTitle(titulo);
            //stage.setFullScreen(true);
            stage.show();
        } else {
            
            MyScreen.errorMessage(status.toString(), response);
            System.exit(0);
        }
    }
    
    public static void newStage(String fxml, boolean modo, String titulo, double w, double h) throws IOException {
        //Node node = (Node) (component);
        //Scene scene = node.getScene();
        Window window = scene.getWindow();
        Stage stage = (Stage) window;
        
        if (!modo) {
            stage.hide();
        }
        
        Parent newNode = App.loadFXML(fxml);

        /**
         * Para mostrar la ventana prinipal como nueva escene
         */
        scene = new Scene(newNode);
        //stage.setScene(scene);
        //stage.showMessage();

        /**
         * Para mostrar la ventana prinipal como nueva stage
         */
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.setTitle(titulo);
        newStage.setWidth(w);
        newStage.setHeight(h);
//        stage.setFullScreen(true);
//        newStage.setResizable(false);
        newStage.show();
        
        
    }
    
}
