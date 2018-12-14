package br.com.appdog.di;

import android.app.Application;

import br.com.appdog.model.persistence.SharedPreference;
import br.com.appdog.service.IService;
import br.com.appdog.viewmodel.MainViewModel;
import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {
    @Provides
    static MainViewModel provideMainViewModel(final Application application, final IService iService, final SharedPreference sharedPreference/*, final AppExecutors appExecutors, final AppDatabase appDatabase, final SharedPreferencesUtil sharedPreferencesUtil*/) {
        return new MainViewModel(/*new MainRepository(iService, sharedPreference, application)*//*new LoginRepository(iService)*/);/*application*//*, new LoginRepository(application, iService, appExecutors, appDatabase, sharedPreferencesUtil*/
    }
}
