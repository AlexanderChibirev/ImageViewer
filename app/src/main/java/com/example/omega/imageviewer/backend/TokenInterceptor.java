package com.example.omega.imageviewer.backend;

import android.support.annotation.NonNull;

import com.example.omega.imageviewer.BuildConfig;
import com.example.omega.imageviewer.models.Preferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Alexander Chibirev on 4/25/2018.
 */

public class TokenInterceptor implements Interceptor {

    @NonNull
    private final Preferences mPreferences;

    public TokenInterceptor(@NonNull Preferences preferences) {
        mPreferences = preferences;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();

        if (!original.headers().values(Constants.X_TOKEN).isEmpty()) {
            Request.Builder builder = original.newBuilder()
                    .removeHeader(Constants.X_TOKEN);

            String token = mPreferences.getToken();

            if (token != null) {
                builder.header(BuildConfig.HEADER_NAME_AUTHORIZATION,
                        BuildConfig.HEADER_VALUE_AUTHORIZATION + token);
            }

            Request request = builder.build();
            return chain.proceed(request);
        }

        return chain.proceed(chain.request());
    }

}
