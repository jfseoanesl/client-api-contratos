/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalDate;

/**
 *
 * @author Jairo F
 */
public class MyGsonMapper {
    
    private static Gson gson;
    
    private MyGsonMapper(){
        gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                    .create();
    }
    
    public static Gson get(){
        if(gson==null){
            new MyGsonMapper();
        }
        return gson;
    }
    
}
