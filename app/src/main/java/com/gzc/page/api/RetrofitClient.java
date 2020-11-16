package com.gzc.page.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * author：gzc
 * date：2020/11/16
 * describe：
 */
public class RetrofitClient {

    private static final String BASE_URL = "http://weiq.ppe.appinside.com/app/index.php/";

    private static RetrofitClient instance ;

    private Retrofit mRetrofit ;

    private RetrofitClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        mRetrofit = new Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(ScalarsConverterFactory.create()).
                addConverterFactory(GsonConverterFactory.create()).
                client(okHttpClient).build();
    }

    public synchronized static RetrofitClient getInstance() {
        if(null == instance) {
            instance = new RetrofitClient();
        }

        return instance;
    }

    public Api getApi() {
        return mRetrofit.create(Api.class);
    }
}


