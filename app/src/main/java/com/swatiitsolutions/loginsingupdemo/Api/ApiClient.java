package com.swatiitsolutions.loginsingupdemo.Api;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit;
    private static final String BASE_URL = "";

    public static Retrofit getRetrofitInstance() {
       /* if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getRequestHeader())
                    .build();
        }
        return retrofit;*/
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    private static OkHttpClient getRequestHeader() {

        return new OkHttpClient.Builder()
                .callTimeout(2, TimeUnit.MINUTES)
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(new MyInterceptor())
                .retryOnConnectionFailure(true)
//                .connectionPool(new ConnectionPool(8, 120,
//                        TimeUnit.SECONDS))
                .build();
    }
}

class MyInterceptor  implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request().newBuilder()
                .addHeader("Connection",
                        "Keep-Alive").build();

        return chain.proceed(request);
    }
}
