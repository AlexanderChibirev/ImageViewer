package com.example.omega.imageviewer.tools.task;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Alexander Chibirev on 4/22/2018.
 */

public class Task<O> {

    @NonNull
    private final Worker<O> mWorker;

    @Nullable
    private FailListener mFailListener;
    @Nullable
    private ResultListener<O> mResultListener;
    @Nullable
    private PostHandler<O> mPostHandler;
    private boolean mCanceled;
    @Nullable
    private OnFinishRequestListener mFinishRequestListener;

    public Task(@NonNull Worker<O> workTask) {
        mWorker = workTask;
    }

    public Task<O> onResult(ResultListener<O> resultListener) {
        this.mResultListener = resultListener;
        return this;
    }

    public Task<O> onError(FailListener listener) {
        mFailListener = listener;
        return this;
    }

    public void onFinish(OnFinishRequestListener listener) {
        mFinishRequestListener = listener;
    }

    public void cancel() {
        mCanceled = true;
    }

    public boolean isCanceled() {
        return mCanceled;
    }

    O doTask() throws Exception {
        O result = mWorker.doTask();
        if (mPostHandler != null) {
            result = mPostHandler.post(result);
        }
        return result;
    }

    void finish(O result) {
        if (mResultListener != null) {
            mResultListener.onResult(result);
        }
        if (mFinishRequestListener != null) {
            mFinishRequestListener.onFinishRequest();
        }
    }

    void finish(@NonNull Exception e) {
        e.printStackTrace();
        if (mFailListener != null) {
            mFailListener.onFail(e);
        }
        if (mFinishRequestListener != null) {
            mFinishRequestListener.onFinishRequest();
        }
    }

    public interface Worker<O> {
        O doTask() throws Exception;
    }

    public interface ResultListener<O> {
        void onResult(O input);
    }

    public interface FailListener {
        void onFail(Exception e);
    }

    public interface PostHandler<O> {
        O post(O input);
    }

    public interface OnFinishRequestListener {
        void onFinishRequest();
    }

}