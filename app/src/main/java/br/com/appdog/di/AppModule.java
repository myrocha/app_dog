package br.com.appdog.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import br.com.appdog.constants.Constants;
import br.com.appdog.service.IService;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Model class that provides objects that are used throughout the application.
 */
@Module
public abstract class AppModule {


    @Binds
    abstract Context bindContext(Application application);


    /**
     * retorna o objeto do servico de requisicao
     *
     * @return
     */
    @Singleton
    @Provides
    static IService provideService() {

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(final Chain chain) throws IOException {
                        Request original = chain.request();
                        Request request = original.newBuilder()
                                .method(original.method(), original.body())
                                .build();
                        return chain.proceed(request);
                    }
                })
                .readTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
                .build();
   

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Constants.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(IService.class);
    }




    @Provides
    static SharedPreferences prividePreferences(final Context context) {
        return context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
    }




}
