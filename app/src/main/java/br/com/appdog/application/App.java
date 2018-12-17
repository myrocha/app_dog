package br.com.appdog.application;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.net.Uri;
import android.util.Log;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;

import javax.inject.Inject;

import br.com.appdog.di.DaggerAppComponent;
import br.com.appdog.model.persistence.AppDatabase;
import br.com.appdog.task.AppExecutors;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class App extends Application implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

    @Inject
    public AppExecutors appExecutors;

    @Inject
    public AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        Picasso.setSingletonInstance(getCustomPicasso());
       DaggerAppComponent.builder().application(this)
                .build().inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }


    private Picasso getCustomPicasso(){
        Picasso.Builder builder = new Picasso.Builder(getApplicationContext());
        //set 12% of available app memory for image cache
        builder.memoryCache(new LruCache(getBytesForMemCache(50)));
        //set request transformer
        Picasso.RequestTransformer requestTransformer =  new Picasso.RequestTransformer() {
            @Override
            public Request transformRequest(Request request) {
                Log.d("image request", request.toString());
                return request;
            }
        };
        builder.requestTransformer(requestTransformer);

        builder.listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri,
                                          Exception exception) {
                Log.d("image load error", uri.getPath());
            }
        });

        return builder.build();
    }

    private int getBytesForMemCache(int percent){
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager)
                getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);

        double availableMemory= mi.availMem;

        return (int)(percent*availableMemory/100);
    }
}
