package br.com.appdog.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.inject.Inject;

import br.com.appdog.model.Access;
import br.com.appdog.model.User;
import br.com.appdog.model.persistence.SharedPreference;
import br.com.appdog.service.IService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * class responsible for accessing the web service.
 */

public class LoginRepository {

    public IService mIservice;
    public SharedPreference sharedPreference;
    public Context mContext;

    @Inject
    public LoginRepository(final  IService service, final Application application, final SharedPreference
            preference){
        this.mIservice = service;
        this.mContext = application;
        this.sharedPreference = preference;

    }


    /**
     * method responsible for logging in to the server.
     * @param access
     * @return
     */
    public  LiveData<User> onLogin(final Access access) {
        final MutableLiveData<User> data = new MutableLiveData<>();


        final Call<JsonObject> call = this.mIservice.login("application/json", access);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(final Call<JsonObject> call, final Response<JsonObject> response) {
                Gson gson = new Gson();
                User user = gson.fromJson(response.body(), User.class);
                saveToken(user.getUser().getToken());
                data.setValue(user);
                Log.i("###", "userrr: " + user.getUser());

                }



            @Override
            public void onFailure(final Call<JsonObject> call, final Throwable t) {
                data.setValue(null);
            }
        });

        return  data;
    }

    public void saveToken(final String token){
        sharedPreference.isSaveTokenUser(mContext, token);
    }

    public String getToken(){
        return sharedPreference.getIsToken(mContext);
    }



}