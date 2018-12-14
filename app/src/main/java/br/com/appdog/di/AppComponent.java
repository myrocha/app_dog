package br.com.appdog.di;


import android.app.Application;

import javax.inject.Singleton;

import br.com.appdog.application.App;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AppModule.class,

     //  AppServiceModule.class,
        ActivityBindingsModule.class,
        FragmentBindingsModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<App> /*extends AndroidInjector<DaggerApplication>*/ {

    /*@Override
    void inject(DaggerApplication instance);*/

   // void inject(CdrApplication mvvmApplication);

   // @ForApplication
   // Context getApplicationContext();

 /*   OnlyParagraphFragment onlyParagraphFragment();*/




    @Component.Builder
    interface Buider {

        @BindsInstance
        AppComponent.Buider application(Application application);

        AppComponent build();
    }
}
