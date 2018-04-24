package com.example.omega.imageviewer.backend.call;

import android.support.annotation.NonNull;

import com.example.omega.imageviewer.tools.task.Task;
import com.example.omega.imageviewer.tools.task.TaskExecutor;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Alexander Chibirev on 4/22/2018.
 */

public class TaskCallAdapterFactory extends CallAdapter.Factory {

    private final TaskExecutor mExecutor;

    public TaskCallAdapterFactory(TaskExecutor executor) {
        mExecutor = executor;
    }

    @Override
    public CallAdapter<?, ?> get(@NonNull Type returnType, @NonNull Annotation[] annotations, @NonNull Retrofit retrofit) {
        if (getRawType(returnType) != Task.class) {
            return null;
        }

        if (!(returnType instanceof ParameterizedType)) {
            throw new IllegalArgumentException("Task return type must be parameterized"
                    + " as Task<Foo> or Task<? extends Foo>");
        }
        Type innerType = getParameterUpperBound(0, (ParameterizedType) returnType);

        if (getRawType(innerType) != Response.class) {
            return createAdapter(innerType, annotations);
        }

        if (!(innerType instanceof ParameterizedType)) {
            throw new IllegalArgumentException("Response must be parameterized"
                    + " as Response<Foo> or Response<? extends Foo>");
        }
        Type responseType = getParameterUpperBound(0, (ParameterizedType) innerType);
        return createAdapter(responseType, annotations);
    }

    private CallAdapter<Task<?>, Task<?>> createAdapter(Type responseType, Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation instanceof PeriodicTask) {
                return new PeriodicTaskAdapter(responseType, mExecutor, (PeriodicTask) annotation);
            }
        }
        return new TaskAdapter(responseType, mExecutor);
    }

    public static class TaskAdapter implements CallAdapter<Task<?>, Task<?>> {

        private final Type responseType;
        private TaskExecutor executor;

        public TaskAdapter(Type responseType, TaskExecutor taskExecutor) {
            this.responseType = responseType;
            this.executor = taskExecutor;
        }


        @Override
        public Type responseType() {
            return responseType;
        }

        @Override
        public Task<?> adapt(@NonNull Call<Task<?>> call) {
            return executor.runTask(() -> call.execute().body());
        }
    }

    public static class PeriodicTaskAdapter implements CallAdapter<Task<?>, Task<?>> {

        private final Type responseType;
        private final PeriodicTask periodicTaskParams;
        private TaskExecutor executor;

        public PeriodicTaskAdapter(Type responseType, TaskExecutor taskExecutor, PeriodicTask taskParams) {
            this.responseType = responseType;
            this.executor = taskExecutor;
            this.periodicTaskParams = taskParams;
        }


        @Override
        public Type responseType() {
            return responseType;
        }

        @Override
        public Task<?> adapt(@NonNull Call<Task<?>> call) {
            return executor.runPeriodicTask(
                    periodicTaskParams.delayMills(),
                    periodicTaskParams.timeoutMills(),
                    () -> call.clone().execute().body()
            );
        }
    }

}