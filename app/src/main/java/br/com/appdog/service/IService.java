package br.com.appdog.service;

import com.google.gson.JsonObject;

import br.com.appdog.model.pojo.Access;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IService {
    /**
     *service responsible for logging in to the web service.
     * @param contentType
     * @return
     */

    @POST("/signup")
    Call<JsonObject> login(@Header("Content-Type") String contentType, @Body Access access);

    /**
     * service responsible for searching the list of dogs in the web service.
     * @param authorization
     * @param category
     * @return
     */
    @GET("/feed")
    Call<JsonObject> getListDog(@Header("Authorization") String authorization, @Query("category") String category);
}
