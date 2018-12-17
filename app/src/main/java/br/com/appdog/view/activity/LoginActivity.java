package br.com.appdog.view.activity;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;

import br.com.appdog.R;
import br.com.appdog.databinding.LoginActivityBinding;
import br.com.appdog.util.IntentActions;
import br.com.appdog.util.OpenScreenUtil;
import br.com.appdog.viewmodel.LoginViewModel;


/**
 * login screen activity.
 */
public class LoginActivity extends BaseActivity {

    @Inject
    public LoginViewModel loginViewModel;
    LoginActivityBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        binding = DataBindingUtil.setContentView(this, R.layout.login_activity);
        binding.setViewModel(loginViewModel);


    }

    public void onLogin(final View view) {
        loginViewModel.showLoading();

        if (loginViewModel.getToken().isEmpty()) {
            final boolean checkFields = loginViewModel.checkLoginFields(binding, this);
            if (checkFields) {
                loginViewModel.onLogin().observe(this, response -> {
                    if (response != null) {
                        loginViewModel.onAcess(response);
                    } else {
                        OpenScreenUtil.openScreen(this, IntentActions.EMPTY_STATE_ACTIVITY.getAction(),
                                null, false);
                    }

                });
            }

        }
        loginViewModel.hideLoading();

    }

    @Override
    protected void onResume() {
        super.onResume();
        String token = loginViewModel.getToken();
        if (token != null && !token.isEmpty()) {
            OpenScreenUtil.openScreen(this, IntentActions.MAIN_ACTIVITY.getAction(),
                    null, true);
        }
    }

}



