package br.com.appdog.repository;

import android.app.Application;
import android.content.Context;

import javax.inject.Inject;

import br.com.appdog.model.persistence.SharedPreference;
import br.com.appdog.service.IService;


public class MainRepository {

    public IService mIservice;
    public SharedPreference sharedPreferences;
    public Context mContext;

    @Inject
    public MainRepository(final  IService service, final SharedPreference preference, final Application application){
        this.mIservice = service;
        this.sharedPreferences = preference;
        this.mContext = application;

    }


}
