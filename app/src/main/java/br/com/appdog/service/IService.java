package br.com.appdog.service;

import com.google.gson.JsonObject;

import br.com.appdog.model.Access;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IService {
    /**
     *
     * @param contentType
     * @return
     */

    @POST("/signup")
    Call<JsonObject> login(@Header("Content-Type") String contentType, @Body Access access);

    @GET("/feed")
    Call<JsonObject> getListDog(@Header("Authorization") String authorization/*,@Header("Content-Type") String contentType*/, @Query("category") String category);
}
