package br.com.appdog.view.activity;

import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * activity base, responsible for injecting the activity.
 */
public class BaseActivity extends DaggerAppCompatActivity {



    @Inject
    public DispatchingAndroidInjector<Fragment> dispatchingFragmentInjector;
    BroadcastReceiver receiver;


    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidInjection.inject(this);




    }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingFragmentInjector;
    }




    protected void unregister() {
        try {
            unregisterReceiver(receiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }





}

