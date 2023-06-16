/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.controller;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUsuarioSingleton;
import co.gov.deajvpar.gestcontractclient.fx.logic.GestionFormPrincipal;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyScreen;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Jairo F
 */
public class FormPrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private StackPane panel;

    @FXML
    private TreeView<String> menuRoot;
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbTipo;
    @FXML
    private Label lbRol;

    private GestionFormPrincipal logicaForm = new GestionFormPrincipal();

    @FXML
    public void eventClickMenu(MouseEvent e) throws IOException {

        if (e.getClickCount() == 2) {
            TreeItem<String> selected = this.menuRoot.getSelectionModel().getSelectedItem();
            if (selected != null) {
                String value = selected.getValue();
                this.logicaForm.openMenuSelected(value, this.panel);
            }

        }

    }

    private void panelUsuario() {
        String nombre = this.logicaForm.getNamePanelUsuario();
        this.lbNombre.setText(nombre);
        this.lbRol.setText(SesionUsuarioSingleton.get().getUserRol());
        this.lbTipo.setText(SesionUsuarioSingleton.get().getUserType());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        try {

            this.logicaForm.openMenuSelected(null, this.panel);

            this.panelUsuario();
            this.menuRoot.setRoot(this.logicaForm.generateTreeMenuUser());

        } catch (IOException ie) {
            MyScreen.errorMessage(ie);
        }

    }

}
