package br.com.appdog.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.ObservableBoolean;

import com.google.gson.Gson;

import javax.inject.Inject;

import br.com.appdog.R;
import br.com.appdog.databinding.LoginActivityBinding;
import br.com.appdog.model.pojo.Access;
import br.com.appdog.model.pojo.ResponseError;
import br.com.appdog.model.pojo.User;
import br.com.appdog.repository.LoginRepository;
import br.com.appdog.util.IntentActions;
import br.com.appdog.util.OpenScreenUtil;


/**
 * view screen login screen.
 */
public class LoginViewModel extends ViewModel {

    public final MutableLiveData<String> email = new MutableLiveData<>();
    public final MutableLiveData<String> emailFail = new MutableLiveData<>();

    public final ObservableBoolean loading = new ObservableBoolean();

    @Inject
    public LoginRepository loginRepository;

    public Context mContext;

    private LoginActivityBinding mBinding;

    public LoginViewModel(final LoginRepository repository, final Context context){
        this.loginRepository = repository;
        this.mContext = context;
    }

    /**
     * access the repository to login.
     * @return
     */
    public LiveData<String> onLogin() {
        Access access = new Access();
        access.setEmail(email.getValue());
        return loginRepository.onLogin(access);
    }

    public boolean checkLoginFields(final LoginActivityBinding binding, final Context context) {
        this.mBinding = binding;
         boolean field = true;
        if (email.getValue() == null || email.getValue().isEmpty()) {
            //mBinding.txtEmail.setError(context.getString(R.string.empty_field));
            setError(context.getString(R.string.empty_field));

            field = false;
        }


        return field;
    }

    public void onAcess(final String jsonLogin){
        //showLoading();
        final Gson gson = new Gson();
        final User user = gson.fromJson(jsonLogin, User.class);
        if (user.getUser() != null) {
            OpenScreenUtil.openScreen(mContext, IntentActions.MAIN_ACTIVITY.getAction(),
                    null, true);
        } else {

            final ResponseError responseError = gson.fromJson(jsonLogin, ResponseError.class);
            //mContext.getString(R.id.empty_state).
            setError(responseError.getMessage());
           // mBinding.txtEmail.setError(/*responseError.getMessage()*/"Email is not valid");

        }
        hideLoading();

    }

    public void setError(final String error){
        mBinding.txtEmail.setError(error);
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
