/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.controller;

import co.gov.deajvpar.gestcontractclient.fx.App;
import co.gov.deajvpar.gestcontractclient.fx.dtos.LoginDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUserDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUsuarioSingleton;
import co.gov.deajvpar.gestcontractclient.fx.utility.HttpCodeResponse;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyGsonMapper;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyHttpApi;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyScreen;
import co.gov.deajvpar.gestcontractclient.fx.utility.StatusCode;
import co.gov.deajvpar.gestcontractclient.fx.utility.UsedApis;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Jairo F
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lbError;
    @FXML
    private Button btnIngresar;

    @FXML
    private void actionButtonIngresar(ActionEvent e) {

        try {
            String usuario = this.txtUsername.getText();
            String clave = this.txtPassword.getText();
            LoginDto dto = new LoginDto(usuario, clave);

            MyHttpApi.jsonPostRequest(UsedApis.API_LOGIN, dto);
            StatusCode status = HttpCodeResponse.get(MyHttpApi.responseStatusCode());
            String response = MyHttpApi.stringResponse();
//            System.out.println(response);
            if (MyHttpApi.statusOk()) {
                SesionUserDto sesion = MyGsonMapper.get().fromJson(response, SesionUserDto.class);
                SesionUsuarioSingleton.set(sesion);
                if (sesion.isEstado()) {
                    String titulo = App.sistema.getNameAndTitle();
//                    App.newStage("FormPrincipal", false, titulo, MyScreen.getMaxWidth(), MyScreen.getMaxHeight());
                    App.setRoot("FormPrincipal");
                } else {
                    this.lbError.setVisible(true);
                    this.txtUsername.requestFocus();
                }

            } else {

                MyScreen.errorMessage(status.toString(), response);
//                  System.out.println(MyHttpApi.stringResponse());
            }
        } catch (UnirestException | IOException  ex) {
            ex.printStackTrace();
            MyScreen.errorMessage(ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.lbError.setVisible(false);

    }

}
