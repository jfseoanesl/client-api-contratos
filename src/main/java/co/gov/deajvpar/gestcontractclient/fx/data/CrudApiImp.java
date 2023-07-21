/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.gov.deajvpar.gestcontractclient.fx.data;

import co.gov.deajvpar.gestcontractclient.fx.Exceptions.HttpResponseException;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyGsonMapper;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyHttpApi;
import co.gov.deajvpar.gestcontractclient.fx.utility.MyScreen;
import co.gov.deajvpar.gestcontractclient.fx.utility.StatusCode;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.lang.reflect.Array;

/**
 *
 * @author Jairo F
 */
public class CrudApiImp<T> {
    
    private Class<T> dto;
    private T[] dtosArray;
      public T post(T obj, String api) {
        T dto;
        try {
            StatusCode status = MyHttpApi.jsonPostRequest2(api, obj);
            System.out.println(status.getResponse());
            if (!status.statusOk()) {
                throw new HttpResponseException(status);
            }
            dto = MyGsonMapper.get().fromJson(status.getResponse(), (Class<T>) obj.getClass());
            MyScreen.setStatus(status);
        } catch (UnirestException ex) {
            throw new HttpResponseException(ex.getMessage());
        }

        return dto;
    }

    public T[] get(Class<T> obj, String api) {
        this.dtosArray = (T[]) Array.newInstance(obj, 0); 
        try {
            StatusCode status = MyHttpApi.jsonGetRequest2(api);
            if (!status.statusOk()) {
                throw new HttpResponseException(status);
            }
            
            this.dtosArray = (T[]) MyGsonMapper.get().fromJson(status.getResponse(), (Class<T>) this.dtosArray.getClass());

        } catch (UnirestException ex) {
            
            throw new HttpResponseException(ex.getMessage());
        }

        return this.dtosArray;
    }
    
    public T[] postList(Class<T> obj, Object dto, String api) {
        this.dtosArray = (T[]) Array.newInstance(obj, 0); 
        try {
            StatusCode status = MyHttpApi.jsonPostRequest2(api, dto);
            if (!status.statusOk()) {
                throw new HttpResponseException(status);
            }
            this.dtosArray = (T[])MyGsonMapper.get().fromJson(status.getResponse(),  (Class<T>) this.dtosArray.getClass());
            MyScreen.setStatus(status);
        } catch (UnirestException ex) {
            throw new HttpResponseException(ex.getMessage());
        }

        return this.dtosArray;
    }

}
