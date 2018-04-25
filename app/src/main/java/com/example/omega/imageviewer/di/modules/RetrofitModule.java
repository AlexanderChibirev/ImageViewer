package com.example.omega.imageviewer.di.modules;

import android.arch.core.BuildConfig;

import com.example.omega.imageviewer.backend.TokenInterceptor;
import com.example.omega.imageviewer.backend.call.CallWrapper;
import com.example.omega.imageviewer.backend.call.TaskCallAdapterFactory;
import com.example.omega.imageviewer.mvp.models.Preferences;
import com.example.omega.imageviewer.mvp.models.Text;
import com.example.omega.imageviewer.tools.task.TaskExecutor;
import com.example.omega.imageviewer.tools.type_adapters.TextTypeAdapter;
import com.squareup.moshi.Moshi;

import java.util.Set;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static com.example.omega.imageviewer.di.modules.Interceptors.Type.BASE;
import static com.example.omega.imageviewer.di.modules.Interceptors.Type.NETWORK;


/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

@Module
public class RetrofitModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Retrofit.Builder builder) {
        return builder.baseUrl("https://api.quickblox.com").build();
    }

    @Provides
    @Singleton
    public Retrofit.Builder provideRetrofitBuilder(Converter.Factory converterFactory, OkHttpClient client, TaskExecutor taskExecutor) {
        return new Retrofit.Builder()
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(new TaskCallAdapterFactory(taskExecutor))
                .callFactory(new CallWrapper.Factory(client));
    }

    @Provides
    @Singleton
    public OkHttpClient provideClient(@Interceptors(BASE) Set<Interceptor> interceptors,
                                      @Interceptors(NETWORK) Set<okhttp3.Interceptor> networkInterceptors) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().addAll(interceptors);
        builder.networkInterceptors().addAll(networkInterceptors);
        return builder.build();
    }

    @Provides
    @IntoSet
    @Interceptors(NETWORK)
    public okhttp3.Interceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        return interceptor;
    }

    @Provides
    @IntoSet
    @Interceptors(BASE)
    public okhttp3.Interceptor provideTokenInterceptor(Preferences preferences) {
        return new TokenInterceptor(preferences);
    }

    @Provides
    @Singleton
    public Converter.Factory provideConverterFactory(Moshi moshi) {
        return MoshiConverterFactory.create(moshi);
    }

    @Provides
    @Singleton
    public Moshi provideMoshi() {
        return new Moshi.Builder()
                .add(Text.class, new TextTypeAdapter()) // registerTypeAdapter
                .build();
    }
}
