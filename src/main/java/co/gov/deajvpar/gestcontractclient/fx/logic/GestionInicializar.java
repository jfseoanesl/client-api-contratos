/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.logic;

import co.gov.deajvpar.gestcontractclient.fx.App;
import co.gov.deajvpar.gestcontractclient.fx.Exceptions.HttpResponseException;
import co.gov.deajvpar.gestcontractclient.fx.data.CrudApiImp;
import co.gov.deajvpar.gestcontractclient.fx.dtos.CiudadDptoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.DptoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.InicializarDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SistemaDto;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyGsonMapper;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyHttpApi;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyScreen;
import co.gov.deajvpar.gestcontractclient.fx.utility.StatusCode;
import co.gov.deajvpar.gestcontractclient.fx.utility.UsedApis;
import co.gov.deajvpar.gestcontractclient.fx.utility.Utility;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jairo F
 */
public class GestionInicializar {

    private String[] generoList = {"Masculino", "Femenino", "Otro"};
    private String[] documentosList = {"Cedula ciudadania", "Registro civil",
        "Tarjeta identidad", "Cedula extranjeria",
        "Pasaporte", "Nit", "Otro"};

    private CrudApiImp<CiudadDptoDto> api;
    private Map<String, DptoDto> map;

    public GestionInicializar() {
        this.api = new CrudApiImp();
    }

    public List<String> getListGenero() {
        return List.of(this.generoList);
    }

    public List<String> getListTipoDocumento() {
        return List.of(this.documentosList);
    }

    public Map<String, DptoDto> getDptosAndCiudades() {

        CiudadDptoDto[] list = this.api.get(CiudadDptoDto.class, UsedApis.API_DPTO_CIUDAD);
        this.map = Utility.listCiudadesToMapDpto(list);
        return this.map;

    }

    public void inicializar(InicializarDto dto) throws IOException {
        try {
            StatusCode status = MyHttpApi.jsonPostRequest2(UsedApis.API_HOME_INITIALIZE, dto);
            if (!status.statusOk()) {
                throw new HttpResponseException(status);
            }

            App.sistema = MyGsonMapper.get().fromJson(status.getResponse(), SistemaDto.class);
            MyScreen.setStatus(status);

        } catch (UnirestException ex) {
            throw new HttpResponseException(ex.getMessage());
        }

    }

    public void lanzarLogin() throws IOException {
        String titulo = App.sistema.getNameAndTitle();
        App.newStage("Login", false, titulo, MyScreen.getMaxWidth(), MyScreen.getMaxHeight());
    }
}
