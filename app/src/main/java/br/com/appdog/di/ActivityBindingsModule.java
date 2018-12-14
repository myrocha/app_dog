package br.com.appdog.di;


import br.com.appdog.view.activity.LoginActivity;
import br.com.appdog.view.activity.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = AndroidSupportInjectionModule.class)
public abstract class ActivityBindingsModule {


    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity loginActivityInjector();

    @ContributesAndroidInjector(modules = {MainActivityModule.class/*, FragmentBindingsModule.class*/})
    abstract MainActivity mainActivityInjector();


}
