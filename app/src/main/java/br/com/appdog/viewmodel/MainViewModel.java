package br.com.appdog.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

public class MainViewModel extends ViewModel {

    public final ObservableBoolean loading = new ObservableBoolean();
/*    @Inject
    public MainRepository mainRepository;*/

    public MainViewModel(/*final MainRepository repository*/) {
      //  this.mainRepository = repository;

    }
/*
    *//**
     * access the repository to login.
     * @return
     *//*
    public LiveData<ListDog> onLogin() {
        *//*Access access = new Access();
        access.setEmail(email.getValue());*//*
        return mainRepository.getListDog("husky");


    }*/

    public void showLoading() {
        loading.set(true);
    }

    public void hideLoading() {
        loading.set(false);


    }
}
