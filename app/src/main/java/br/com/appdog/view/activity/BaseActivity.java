package br.com.appdog.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity;

public class BaseActivity extends DaggerAppCompatActivity {

    @Inject
    public DispatchingAndroidInjector<Fragment> dispatchingFragmentInjector;


    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidInjection.inject(this);

/*        if (this instanceof FragmentActivity) {
            ((FragmentActivity) this).getSupportFragmentManager()
                    .registerFragmentLifecycleCallbacks(
                            new FragmentManager.FragmentLifecycleCallbacks() {
                                @Override
                                public void onFragmentCreated(FragmentManager fm, final Fragment fragment,
                                                              final Bundle savedInstanceState) {
                                   // if (fragment instanceof Injectable) {
                                        AndroidSupportInjection.inject(fragment);
                                   // }
                                }
                            }, true);
        }*/

    }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingFragmentInjector;
    }
}
