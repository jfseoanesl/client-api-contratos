package co.gov.deajvpar.gestcontractclient.fx;

import co.gov.deajvpar.gestcontractclient.fx.dtos.SistemaDto;
import co.gov.deajvpar.gestcontractclient.fx.utility.HttpCodeResponse;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyGsonMapper;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyHttpApi;
import co.gov.deajvpar.gestcontractclient.fx.utility.Screen;
import co.gov.deajvpar.gestcontractclient.fx.utility.StatusCode;
import co.gov.deajvpar.gestcontractclient.fx.utility.UsedApis;

import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
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
            Screen.errorMessage(ex);
            System.exit(0);
        }
        
    }
    
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    static Scene getScene() {
        return scene;
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public static void main(String[] args) {
        launch();
    }
    
    private static void login(Stage stage) throws UnirestException, IOException{
        MyHttpApi.jsonGetRequest(UsedApis.API_LOGIN);
        String response = MyHttpApi.stringResponse();
        StatusCode status = HttpCodeResponse.get(MyHttpApi.responseStatusCode());
        if (MyHttpApi.statusOk()) {
            sistema = MyGsonMapper.get().fromJson(response, SistemaDto.class);
            String titulo;
            if (!sistema.isInicializado()) {
                scene = new Scene(loadFXML("Inicializar"), 1200, 640);
                titulo = "Inicializar sistema - Creacion usuario administrador";
            } else {
                
                scene = new Scene(loadFXML("primary"), 640, 480);
                titulo = "Control de acceso - Login";
            }
            stage.setScene(scene);
            stage.setTitle(titulo);
            stage.show();
        } else {
            
            Screen.errorMessage(status.toString(), response);
            System.exit(0);
        }
    }
    
    private static void home(Stage stage) throws UnirestException, IOException {
        MyHttpApi.jsonGetRequest(UsedApis.API_HOME);
        String response = MyHttpApi.stringResponse();
        StatusCode status = HttpCodeResponse.get(MyHttpApi.responseStatusCode());
        if (MyHttpApi.statusOk()) {
            sistema = MyGsonMapper.get().fromJson(response, SistemaDto.class);
            String titulo;
            if (!sistema.isInicializado()) {
                scene = new Scene(loadFXML("Inicializar"), 1200, 640);
                titulo = "Inicializar sistema - Creacion usuario administrador";
            } else {
                
                scene = new Scene(loadFXML("primary"), 640, 480);
                titulo = "Control de acceso - Login";
            }
            stage.setScene(scene);
            stage.setTitle(titulo);
            stage.show();
        } else {
            
            Screen.errorMessage(status.toString(), response);
            System.exit(0);
        }
    }
    
    public static Stage newStage(String fxml, boolean modo) throws IOException {
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
        //stage.show();

        /**
         * Para mostrar la ventana prinipal como nueva stage
         */
        Stage newStage = new Stage();
        newStage.setScene(scene);
//        newStage.setTitle(stage.getTitle());
//        newStage.show();
        return newStage;
        
    }
    
}
