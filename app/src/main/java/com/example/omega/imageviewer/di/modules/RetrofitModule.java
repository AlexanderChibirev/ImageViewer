package com.example.omega.imageviewer.di.modules;

import com.squareup.moshi.Moshi;

import java.util.Set;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static com.example.omega.imageviewer.di.modules.Interceptors.Type.NETWORK;


/**
 * Created by Alexander Chibirev on 4/15/2018.
 */

@Module
public class RetrofitModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Retrofit.Builder builder, String url) {
        return builder.baseUrl(url).build();
    }

    @Provides
    @Singleton
    public OkHttpClient provideClient(@Interceptors Set<Interceptor> interceptors,
                                      @Interceptors(NETWORK) Set<okhttp3.Interceptor> networkInterceptors) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().addAll(interceptors);
        builder.networkInterceptors().addAll(networkInterceptors);
        return builder.build();
    }

    @Provides
    @Singleton
    public Converter.Factory provideConverterFactory(Moshi moshi) {
        return MoshiConverterFactory.create(moshi);
    }

    @Provides
    @Singleton
    public Moshi provideMoshi() {
        return new Moshi.Builder().build();
    }
}
