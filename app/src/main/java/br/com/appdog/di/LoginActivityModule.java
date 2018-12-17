package br.com.appdog.di;

import android.app.Application;

import br.com.appdog.model.persistence.SharedPreference;
import br.com.appdog.repository.LoginRepository;
import br.com.appdog.service.IService;
import br.com.appdog.viewmodel.LoginViewModel;
import dagger.Module;
import dagger.Provides;

@Module
public class LoginActivityModule {
    @Provides
    static LoginViewModel provideViewModel(final Application application, final IService iService, final SharedPreference sharedPreference/*, final AppExecutors appExecutors, final AppDatabase appDatabase, final SharedPreferencesUtil sharedPreferencesUtil*/) {
        return new LoginViewModel(new LoginRepository(iService, application, sharedPreference), application);/*application*//*, new LoginRepository(application, iService, appExecutors, appDatabase, sharedPreferencesUtil*/
    }
}
