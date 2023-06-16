/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.logic;

import co.gov.deajvpar.gestcontractclient.fx.App;
import co.gov.deajvpar.gestcontractclient.fx.dtos.ModuloDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SesionUsuarioSingleton;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyScreen;
import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Jairo F
 */
public class GestionFormPrincipal {

    public final String PATH_IMAGES = "co/gov/deajvpar/gestcontractclient/images/";
    public final String PATH_IMAGES_ = "/src/main/resources/co/gov/deajvpar/gestcontractclient/images/";
    public final String PATH_MENU = PATH_IMAGES + "menu.png";

    public final String FXML_FORM_MODULE_DEAJ = "PanelDireccionEjecutiva";
    public final String FXML_FORM_MODULE_TIPO_CONTRATO = "PanelTipoDeContratos";
    public final String FXML_FORM_MODULE_MODALIDAD_CONTRATO = "PanelModalidadContrato";
    public final String FXML_FORM_MODULE_CONFIG_ALERTA = "PanelSetupAlerta";
    public final String FXML_FORM_MODULE_USUARIOS = "PanelUsuarios";
    public final String FXML_FORM_MODULE_ROLES = "PanelRoles";

    public final String menuTitlesAndIcon[][] = {
        {"Configuracion sistema", "configuracion.png", ModuloDto.CONFIGURACION.getName()},
        {"Gestion de usuarios y permisos", "usuario.png", ModuloDto.USUARIOS.getName()},
        {"Contratos", "contrato.png", ModuloDto.CONTRATOS.getName()},
        {"Supervision contratos", "supervision.png", ModuloDto.SUPERVISION.getName()},
        {"Supervisor", "supervisor.png", ModuloDto.SUPERVISOR.getName()},
        {"Dashboard", "eficiencia.png", ModuloDto.DASHBOARD.getName()}
    };

    private final String subMenuTitles[][] = {
        {"Gestion Direcciones seccionales", "Tipos de contrato", "Modalidades de contratacion", "Configuracion de alertas"},
        {"Registro de usuarios", "Roles y permisos", "Actualizacion personas", "Recuperacion de contraseñas"},
        {"Registro de contratos", "Gestion supervisores", "Registro de novedades", "Graficos", "Consultas"},
        {"Informes de supervision", "Verificacion de contratos", "Graficos"},
        {"Mis contratos asignados", "Mis informes", "Registro de informe"},
        {"Dashboard contratos", "Dashboard informes"}
    };
    private final int codigoSubMenu[][] = {
        {0, 1, 2, 3},
        {10, 11, 12, 13},
        {20, 21, 22, 23, 24},
        {30, 31, 32},
        {40, 41, 42},
        {50, 51}
    };

    public GestionFormPrincipal() {
    }

    public int getCodigoSubMenu(String title) {
        for (int i = 0; i < this.menuTitlesAndIcon.length; i++) {
            for (int j = 0; j < this.subMenuTitles[i].length; j++) {
                if (this.subMenuTitles[i][j].equalsIgnoreCase(title)) {
                    return this.codigoSubMenu[i][j];
                }
            }
        }
        return -1;
    }

    public String getNamePanelUsuario() {
        return SesionUsuarioSingleton.get().getAllNameUser();
    }

    public void setModulo(ModuloDto module, StackPane root, String children) throws IOException {
        root.getChildren().clear();
        if (module != null) {
            SesionUsuarioSingleton.get().setModuloActive(module);
            if (children != null) {
                StackPane childrenPanel = new StackPane(App.loadFXML(children));
                root.getChildren().add(childrenPanel);
                childrenPanel.setVisible(true);
            }
        }

    }

    public ImageView getImageView(String path) {

        return new ImageView(new Image(path));
    }

    public TreeItem generateTreeMenuUser() {

        TreeItem rootItem = new TreeItem("Menu de opciones");
        Node rootIcon = this.getImageView(this.PATH_MENU);
        rootItem.setGraphic(rootIcon);

        for (int i = 0; i < this.menuTitlesAndIcon.length; i++) {
            String title = this.menuTitlesAndIcon[i][0];
            String imageIcon = this.menuTitlesAndIcon[i][1];
            String modulo = this.menuTitlesAndIcon[i][2];
            if (SesionUsuarioSingleton.get().checkPermisoModulo(modulo)) {
                TreeItem item = new TreeItem(title);
                try {
                    Node icon = this.getImageView(PATH_IMAGES + imageIcon);
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
        return rootItem;
    }

    public void openMenuSelected(String value, StackPane root) throws IOException {
        if (value != null) {
            int codigoMenu = this.getCodigoSubMenu(value);
            switch (codigoMenu) {
                case 0:
                    this.setModulo(ModuloDto.CONFIGURACION, root, this.FXML_FORM_MODULE_DEAJ);
                    break;
                case 1:
                    this.setModulo(ModuloDto.CONFIGURACION, root, this.FXML_FORM_MODULE_TIPO_CONTRATO);
                    break;
                case 2:
                    this.setModulo(ModuloDto.CONFIGURACION, root, this.FXML_FORM_MODULE_MODALIDAD_CONTRATO);
                    break;
                case 3:
                    this.setModulo(ModuloDto.CONFIGURACION, root, this.FXML_FORM_MODULE_CONFIG_ALERTA);
                    break;
                case 10:
                    this.setModulo(ModuloDto.USUARIOS, root, this.FXML_FORM_MODULE_USUARIOS);
                    break;
                case 11:
                    this.setModulo(ModuloDto.USUARIOS, root, this.FXML_FORM_MODULE_ROLES);
                    break;
                case -1:
                    break;
                default:
                    // this.panelDirecciones.setVisible(false);
                    this.setModulo(null, root, null);
                    MyScreen.errorMessage(value);
            }
        }else{
            this.setModulo(null, root, null);
        }

    }
}
