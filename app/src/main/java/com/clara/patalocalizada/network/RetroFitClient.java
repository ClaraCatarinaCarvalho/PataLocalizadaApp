package com.clara.patalocalizada.network;

import android.content.Context;
import android.content.SharedPreferences;
import com.clara.patalocalizada.MyApp;

import java.io.IOException;
import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitClient {

    private static Retrofit retrofit;

    private static final String BASE_URL = "http://192.168.1.23/PataLocalizadaAdmin/";


    public static Retrofit getInstance() {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(chain -> {
                        Request original = chain.request();

                        // Recuperar token JWT
                        SharedPreferences prefs = MyApp.getContext().getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE);
                        String token = prefs.getString("JWT_TOKEN", null);

                        Request.Builder requestBuilder = original.newBuilder()
                                .method(original.method(), original.body());

                        if (token != null) {
                            requestBuilder.addHeader("Authorization", "Bearer " + token);
                        }

                        return chain.proceed(requestBuilder.build());
                    })
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}