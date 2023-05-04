/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.utility;

import co.gov.deajvpar.gestcontractclient.fx.Exceptions.HttpResponseException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jairo F
 */
public class MyHttpApi {

    private static HttpResponse<JsonNode> apiResponse;

    public MyHttpApi() {
    }

    public static void configureMapper() {
        Unirest.setObjectMapper(new ObjectMapper() {
            //private Gson gson = new Gson();
            private Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                    .create();

            public <T> T readValue(String s, Class<T> aClass) {
                try {
                    return gson.fromJson(s, aClass);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object o) {
                try {
                    return gson.toJson(o);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    
    

    public static void jsonGetRequest(String api) throws UnirestException {
        apiResponse = Unirest.get(api).asJson();
        //System.out.println(apiResponse.getStatus());

    }

    public static void jsonGetRequest(String api, Map<String, Object> field) throws UnirestException {
        apiResponse = Unirest.get(api).queryString(field).asJson();

    }

    public static void jsonPostRequest(String api, Object obj) throws UnirestException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        apiResponse = Unirest.post(api).headers(headers).body(obj).asJson();
    }

    public static void jsonPostRequest(String api, String obj) throws UnirestException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        apiResponse = Unirest.post(api).headers(headers).body(obj).asJson();
    }

    public static int responseStatusCode()throws HttpResponseException  {
        if (apiResponse == null) {
            throw new HttpResponseException();
        }
//        if(apiResponse.getStatus()==200){
        return apiResponse.getStatus();
    }

    public static String stringResponse() throws HttpResponseException {

        if (apiResponse == null) {
            throw new HttpResponseException();
        }
//        if(apiResponse.getStatus()==200){
        return apiResponse.getBody().toString();
//        }
//        else{
//           throw new HttpResponseException(HttpCodeResponse.NO_FOUND); 
//        
//        }

    }

    public static boolean statusOk() {

        return apiResponse.getStatus() == 200;

    }

}
