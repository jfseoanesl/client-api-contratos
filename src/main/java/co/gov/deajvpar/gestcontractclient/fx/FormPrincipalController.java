/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx;

import co.gov.deajvpar.gestcontractclient.fx.dtos.ModuloDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUserDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUsuarioSingleton;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyScreen;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Jairo F
 */
public class FormPrincipalController implements Initializable {

    private final String PATH_IMAGES = "../images/";
    private final String menuTitlesAndIcon[][] = {
        {"Configuracion sistema", "configuracion.png", ModuloDto.CONFIGURACION.getName()},
        {"Gestion de usuarios y permisos", "usuario.png", ModuloDto.USUARIOS.getName()},
        {"Contratos", "contrato.png", ModuloDto.CONTRATOS.getName()},
        {"Supervision contratos", "supervision.png", ModuloDto.SUPERVISION.getName()},
        {"Supervisor", "supervisor.png", ModuloDto.SUPERVISOR.getName()},
        {"Dashboard", "eficiencia.png", ModuloDto.DASHBOARD.getName()}
    };

    private final String subMenuTitles[][] = {
        {"Gestion Direcciones seccionales", "Tipos de contrato",
            "Modalidades de contratacion", "Configuracion de alertas"},
        {"Registro de usuarios", "Roles y permisos",
            "Actualizacion personas", "Recuperacion de contraseñas"},
        {"Registro de contratos", "Gestion supervisores",
            "Registro de novedades", "Graficos", "Consultas"},
        {"Informes de supervision", "Verificacion de contratos", "Graficos"},
        {"Mis contratos asignados", "Mis informes", "Registro de informe"},
        {"Dashboard contratos", "Dashboard informes"}
    };
    private final int codigoSubMenu[][] = {
        {0, 1, 2, 3},
        {10, 11, 12, 13},
        {20, 21, 22, 23, 24},
        {30, 31, 32},
        {40, 41}
    };

    private SesionUserDto sesionUsuario;

    private StackPane panelDirecciones;
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

    @FXML
    public void eventClickMenu(MouseEvent e) throws IOException {

        if (e.getClickCount() == 2) {
            String value = this.menuRoot.getSelectionModel().getSelectedItem().getValue();
            int codigoMenu = this.getCodigoSubMenu(value);
            switch (codigoMenu) {
                case 0:
                    this.panel.getChildren().remove(this.panelDirecciones);
                    this.panelDirecciones = new StackPane(App.loadFXML("panelDireccionEjecutiva"));
                    this.panel.getChildren().add(this.panelDirecciones);
                    this.panelDirecciones.setVisible(true);
                    break;
                case -1:
                    break;
                default:
                    this.panelDirecciones.setVisible(false);
                    MyScreen.errorMessage(value);
            }

        }

    }

    private void panelUsuario() {
        String nombre = SesionUsuarioSingleton.get().getUser().getpNombre() + " "
                + SesionUsuarioSingleton.get().getUser().getpApellido() + " "
                + SesionUsuarioSingleton.get().getUser().getsApellido();

        this.lbNombre.setText(nombre);
        //System.out.println(SesionUsuarioSingleton.get());
        this.lbRol.setText(SesionUsuarioSingleton.get().getUserRol());
        this.lbTipo.setText(SesionUsuarioSingleton.get().getUserType());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        try {
            
            this.panelDirecciones = new StackPane(App.loadFXML("panelDireccionEjecutiva"));
            this.panel.getChildren().add(this.panelDirecciones);
            
            this.sesionUsuario = SesionUsuarioSingleton.get();
            this.panelUsuario();
            this.generateMenuUser();
            

        } catch (IOException ie) {
            MyScreen.errorMessage(ie);
        }

    }

    private void generateMenuUser() {

        TreeItem rootItem = new TreeItem("Menu de opciones");
        Node rootIcon = new ImageView(new Image(getClass().getResourceAsStream(PATH_IMAGES + "menu.png")));
        rootItem.setGraphic(rootIcon);
        
        for (int i = 0; i < this.menuTitlesAndIcon.length; i++) {
            String title = this.menuTitlesAndIcon[i][0];
            String imageIcon = this.menuTitlesAndIcon[i][1];
            String modulo = this.menuTitlesAndIcon[i][2];
            if (this.sesionUsuario.checkPermisoModulo(modulo)) {
                TreeItem item = new TreeItem(title);
//            System.out.println(PATH_IMAGES + imageIcon);
                try {
                    Node icon = new ImageView(new Image(getClass().getResourceAsStream(PATH_IMAGES + imageIcon)));
                    item.setGraphic(icon);
                } catch (Exception e) {
                }

                for (String subMenuTitle : this.subMenuTitles[i]) {
                    item.getChildren().add(new TreeItem(subMenuTitle));
                }
                rootItem.getChildren().add(item);

            }
        }
        rootItem.setExpanded(true);
        this.menuRoot.setRoot(rootItem);
    }

    public int getCodigoSubMenu(String title) {
        for (int i = 0; i < this.subMenuTitles.length; i++) {
            for (int j = 0; j < this.subMenuTitles[i].length; j++) {
                if (this.subMenuTitles[i][j].equalsIgnoreCase(title)) {
                    return this.codigoSubMenu[i][j];
                }
            }
        }
        return -1;
    }

}
