/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.controller;

import co.gov.deajvpar.gestcontractclient.fx.App;
import co.gov.deajvpar.gestcontractclient.fx.Exceptions.HttpResponseException;
import co.gov.deajvpar.gestcontractclient.fx.dtos.LoginDto;
import co.gov.deajvpar.gestcontractclient.fx.logic.GestionLogin;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyScreen;
import co.gov.deajvpar.gestcontractclient.fx.utility.Utility;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

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
    private Label lbError, lbSalir;
    @FXML
    private Button btnIngresar;
    @FXML
    private Pane imageViewPane;
    @FXML
    private ImageView imageView, imgSalir;

    private GestionLogin logiclogin = new GestionLogin();
    
    @FXML
    public void evenSalirImg(MouseEvent e){
        Utility.salir();
    }
    
    @FXML
    public void evenSalirLabel(MouseEvent e){
        Utility.salir();
    }

    @FXML
    private void actionButtonIngresar(ActionEvent e) {
        String usuario = this.txtUsername.getText();
        String clave = this.txtPassword.getText();
        LoginDto dto = new LoginDto(usuario, clave);

        try {
            this.logiclogin.login(dto);
            if (this.logiclogin.logicSuccess()) {
                String titulo = App.sistema.getNameAndTitle();
//                    App.newStage("FormPrincipal", false, titulo, MyScreen.getMaxWidth(), MyScreen.getMaxHeight());
                App.setRoot("FormPrincipal");
            } else {
                this.lbError.setVisible(true);
                this.txtUsername.requestFocus();
            }

        } catch (HttpResponseException ex) {
            MyScreen.errorMessage(ex.getCausa(), ex.getMessage());
        }catch(IOException ex){
            MyScreen.errorMessage(ex);
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.imageView.fitWidthProperty().bind(this.imageViewPane.widthProperty());
        this.imageView.fitHeightProperty().bind(this.imageViewPane.heightProperty());
        this.lbError.setVisible(false);

    }

}
