package br.com.appdog.viewmodel;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.ObservableBoolean;

import javax.inject.Inject;

import br.com.appdog.model.persistence.SharedPreference;

public class MainViewModel extends ViewModel {

    public final ObservableBoolean loading = new ObservableBoolean();
    @Inject
    SharedPreference sharedPreference;
    @Inject
    Context context;


    public MainViewModel(final Application application, final SharedPreference sharedPreference) {
        this.sharedPreference = sharedPreference;
        this.context = application;

    }

    public void clearToken(){
        sharedPreference.isSaveTokenUser(context, "");

    }




}
