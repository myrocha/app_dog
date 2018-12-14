package br.com.appdog.di;


import br.com.appdog.view.fragments.DogFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = AndroidSupportInjectionModule.class)
public abstract class FragmentBindingsModule {



    @ContributesAndroidInjector(modules = DogFragmentModule.class)
    abstract DogFragment provideClosedResourcecFragment();


}

