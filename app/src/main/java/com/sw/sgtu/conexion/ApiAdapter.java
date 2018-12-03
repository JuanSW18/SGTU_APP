package com.sw.sgtu.conexion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiAdapter {

    private static ApiAdapter API_SERVICE;

    private final static String BASE_API_URL = "https://ptransporte.herokuapp.com/";
    private final static String BASE_API_URL_TWO = "https://apipatrones.herokuapp.com/";
    private static Retrofit retrofit = null;
    private static Retrofit retrofit_two = null;
    private static Gson gson = new GsonBuilder().create();

    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private static OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor);

    private static OkHttpClient okHttpClient = okHttpClientBuilder.build();

    public static <T> T createService(Class<T> serviceClass){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_API_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit.create(serviceClass);
    }

    public static <T> T createServiceSecondAPI(Class<T> serviceClass){
        if(retrofit_two == null){
            retrofit_two = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_API_URL_TWO)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit_two.create(serviceClass);
    }
}
