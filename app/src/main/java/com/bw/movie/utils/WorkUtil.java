package com.bw.movie.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WorkUtil {
    private static final WorkUtil ourInstance = new WorkUtil();
    private final Retrofit retrofit;

    public static WorkUtil getInstance() {
        return ourInstance;
    }

    private WorkUtil() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.MILLISECONDS)
                .addInterceptor(interceptor)
                .build();
        retrofit = new Retrofit.Builder().client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Api.BASE_MOBILE_URL)
                .build();



    }

    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }
}
