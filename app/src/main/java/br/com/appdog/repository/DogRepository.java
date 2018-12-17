package br.com.appdog.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.appdog.model.ListDog;
import br.com.appdog.model.Url;
import br.com.appdog.model.persistence.AppDatabase;
import br.com.appdog.model.persistence.SharedPreference;
import br.com.appdog.service.IService;
import br.com.appdog.task.AppExecutors;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogRepository {

    public IService mIservice;
    public SharedPreference sharedPreferences;
    public Context mContext;
    public AppDatabase mAppDatabase;
    public AppExecutors mAppExecutors;
    private List<String> mListUrl= new ArrayList<>();


    @Inject
    public DogRepository(final  IService service,
                         final SharedPreference preference,
                         final Application application,
                         final AppExecutors appExecutors,
                         final AppDatabase appDatabase){
        this.mIservice = service;
        this.sharedPreferences = preference;
        this.mContext = application;
        this.mAppExecutors = appExecutors;
        this.mAppDatabase = appDatabase;

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
             //   save(listDog.getList(), category);
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

    public void save(final List<String> listUrl, final String category) {

        final Runnable runnable = () -> {
            for (String urls : listUrl ) {
                Url url = new Url();
                url.setCategory(category);
                url.setUrl(urls);
                mAppDatabase.urlDAO().insertAll(url);
            }


        };

        mAppExecutors.diskIO().execute(runnable);

    }

    public LiveData<List<String>> getListUrl(final String category){
        final MutableLiveData<List<String>> data = new MutableLiveData<>();
        final Runnable runnable = () -> {

            List<Url> urlList = mAppDatabase.urlDAO().getUrlByCategory(category);
           // List<String> list = new ArrayList<>();
            for (Url url : urlList              ) {
                mListUrl.add(url.getUrl());

            }

            //data.setValue(list);

            updateUi(data);


        };
        mAppExecutors.diskIO().execute(runnable);
        return  data;
    }

    public  void updateUi(final MutableLiveData<List<String>> data) {
        //final MutableLiveData<List<String>> date = (MutableLiveData<List<String>>) urlList;
        mAppExecutors.mainThread().execute(() -> data.setValue(mListUrl));
    }


}
