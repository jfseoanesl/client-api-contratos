package co.gov.deajvpar.gestcontractclient.fx;

import co.gov.deajvpar.gestcontractclient.fx.Exceptions.HttpResponseException;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SistemaDto;
import co.gov.deajvpar.gestcontractclient.fx.logic.GestionApp;
import co.gov.deajvpar.gestcontractclient.fx.utility.*;

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

    public static Scene scene;
    public static SistemaDto sistema;
    private static final GestionApp logica = new GestionApp();

    @Override
    public void start(Stage stage) throws IOException, UnirestException {
        MyHttpApi.configureMapper();
        try {
            App.home(stage);
        } catch (UnirestException | IOException ex) {
            MyScreen.errorMessage(ex);
            Utility.salir();
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
        try {
            logica.initApp(stage);
        } catch (HttpResponseException ex) {
            MyScreen.errorMessage(ex);
            Utility.salir();
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
