package br.com.appdog.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.ObservableBoolean;

import javax.inject.Inject;

import br.com.appdog.databinding.LoginActivityBinding;
import br.com.appdog.model.Access;
import br.com.appdog.model.User;
import br.com.appdog.repository.LoginRepository;



/**
 * view screen login screen.
 */
public class LoginViewModel extends ViewModel {

    public final MutableLiveData<String> email = new MutableLiveData<>();
    public final MutableLiveData<String> emailFail = new MutableLiveData<>();

    public final ObservableBoolean loading = new ObservableBoolean();

    @Inject
    public LoginRepository loginRepository;

    public LoginViewModel(LoginRepository repository){
        this.loginRepository = repository;
    }

    /**
     * access the repository to login.
     * @return
     */
    public LiveData<User> onLogin() {
        Access access = new Access();
        access.setEmail(email.getValue());
        return loginRepository.onLogin(access);


    }

    public boolean checkLoginFields(final LoginActivityBinding binding, final Context context) {
         boolean field = true;
        if (email.getValue() == null || email.getValue().isEmpty()) {
           // binding.txtEmail.setError(context.getString(R.string.empty_field));

            field = false;
        }


        return field;
    }

    public String getToken(){
        return loginRepository.getToken();
    }


    public void showLoading() {
        loading.set(true);
    }

    public void hideLoading() {
        loading.set(false);


    }



}
