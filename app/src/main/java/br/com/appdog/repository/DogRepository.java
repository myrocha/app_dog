package br.com.appdog.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.inject.Inject;

import br.com.appdog.model.ListDog;
import br.com.appdog.model.persistence.SharedPreference;
import br.com.appdog.service.IService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogRepository {

    public IService mIservice;
    public SharedPreference sharedPreferences;
    public Context mContext;

    @Inject
    public DogRepository(final  IService service, final SharedPreference preference, final Application application){
        this.mIservice = service;
        this.sharedPreferences = preference;
        this.mContext = application;

    }


    /**
     * method responsible for logging in to the server.
     * @param category
     * @return
     */
    public LiveData<ListDog> getListDog(final String category) {
        final MutableLiveData<ListDog> data = new MutableLiveData<>();

        final String token = sharedPreferences.getIsToken(mContext);
        final Call<JsonObject> call = this.mIservice.getListDog(token, category);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(final Call<JsonObject> call, final Response<JsonObject> response) {
                Gson gson = new Gson();
                ListDog listDog = gson.fromJson(response.body(), ListDog.class);
                data.setValue(listDog);
                //  Log.i("###", "userrr: " + user.getUser());

            }



            @Override
            public void onFailure(final Call<JsonObject> call, final Throwable t) {
                data.setValue(null);
            }
        });

        return  data;
    }

}
