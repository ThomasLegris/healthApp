package com.example.tlegris.healthapp.network.models;

import com.example.tlegris.healthapp.network.HealthAppAPI;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Create our own http client
 * Called in fragment
 * using okhttp and retrofit
 */
public class ApiClient {

    private static final String API_URL = "http://www.";

    public static final String API_KEY = "";

    private static HealthAppAPI healthAppClient;

    private HealthAppAPI getInstance() {
        if (healthAppClient == null) {

            HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
            logger.setLevel(HttpLoggingInterceptor.Level.BODY);

            //setup the client for retrofit
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build();

            //using retrofit to make Api call
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            healthAppClient = retrofit.create(HealthAppAPI.class);
        }
        return healthAppClient;
    }

}
