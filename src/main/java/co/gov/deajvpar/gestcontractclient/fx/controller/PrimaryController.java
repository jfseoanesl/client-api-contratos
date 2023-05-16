package co.gov.deajvpar.gestcontractclient.fx.controller;

import co.gov.deajvpar.gestcontractclient.fx.App;
import co.gov.deajvpar.gestcontractclient.fx.Exceptions.HttpResponseException;
import co.gov.deajvpar.gestcontractclient.fx.dtos.DptoDto;
import co.gov.deajvpar.gestcontractclient.fx.dtos.SistemaDto;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyGsonMapper;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyHttpApi;
import co.gov.deajvpar.gestcontractclient.fx.utility.UsedApis;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {

        
        try {
            MyHttpApi.jsonGetRequest(UsedApis.API_HOME);
            String responseJsonAsString = MyHttpApi.stringResponse();
            System.out.println(responseJsonAsString);


        SistemaDto sistema = MyGsonMapper.get().fromJson(responseJsonAsString, SistemaDto.class);

        if (sistema.isInicializado()) {
            App.setRoot("secondary");
        } else {
            sistema.setInicializado(true);

            MyHttpApi.jsonPostRequest(UsedApis.API_HOME + "/initialize", sistema);
            responseJsonAsString = MyHttpApi.stringResponse();
            System.out.println("Response initialize: " + responseJsonAsString);
        }

//        DptoDto[] cesar=gson.fromJson(responseJsonAsString, DptoDto[].class);
//        for(DptoDto d: cesar){
//            System.out.println("Nombre: " + d.getDepartamento());
//        
//        }
//        DptoDto dpto = new DptoDto("La paz cesar");
//        String json = gson.toJson(dpto).toString();
//        System.out.println("---json---");
//        System.out.println(json);
        } catch (HttpResponseException | UnirestException ex) {
            System.out.println(ex);
        }
    }
}
