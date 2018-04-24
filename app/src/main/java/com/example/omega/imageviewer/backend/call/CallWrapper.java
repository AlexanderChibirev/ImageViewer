package com.example.omega.imageviewer.backend.call;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.omega.imageviewer.R;
import com.example.omega.imageviewer.backend.Error;
import com.example.omega.imageviewer.backend.ErrorException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Alexander Chibirev on 4/22/2018.
 */

public class CallWrapper implements Call {

    private final Call mCall;

    public CallWrapper(Call call) {
        mCall = call;
    }

    @Override
    public Request request() {
        return mCall.request();
    }

    @Override
    public Response execute() throws IOException {
        Response response;
        try {
            response = mCall.execute();

        } catch (IOException e) {
            throw convertToErrorException(e);
        }

        handleError(response);
        return response;
    }

    private IOException convertToErrorException(IOException e) {
        if (e instanceof ConnectException || e instanceof SocketTimeoutException) {
            return new ErrorException(new Error(R.string.error_connection));
        } else {
            return new ErrorException(new Error(e.getMessage()));
        }
    }

    private void handleError(Response response) throws IOException {
        if (!response.isSuccessful()) {
            ResponseBody body = response.body();
            Error error = null;
            if (body != null) {
                error = getErrorFromErrorBody(body);
            }
            throw new ErrorException(error);
        }
    }

    private Error getErrorFromErrorBody(ResponseBody errorBody) {
        try {
            Error error = Error.createWithJson(errorBody.string());
            if (error != null) {
                return error;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Error(R.string.error_unknown);
    }

    @Override
    public void enqueue(@Nullable Callback responseCallback) {
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if (responseCallback != null) {
                    responseCallback.onFailure(call, convertToErrorException(e));
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (responseCallback == null) {
                    return;
                }
                try {
                    handleError(response);
                    responseCallback.onResponse(call, response);

                } catch (ErrorException e) {
                    responseCallback.onFailure(call, e);
                }
            }
        });
    }

    @Override
    public void cancel() {
        mCall.cancel();
    }

    @Override
    public boolean isExecuted() {
        return mCall.isExecuted();
    }

    @Override
    public boolean isCanceled() {
        return mCall.isCanceled();
    }

    @SuppressWarnings("CloneDoesntCallSuperClone")
    @Override
    public Call clone() {
        return new CallWrapper(mCall);
    }

    public static class Factory implements Call.Factory {

        private final Call.Factory factory;

        public Factory(Call.Factory factory) {
            this.factory = factory;
        }

        @Override
        public Call newCall(@NonNull Request request) {
            return new CallWrapper(factory.newCall(request));
        }

    }

}