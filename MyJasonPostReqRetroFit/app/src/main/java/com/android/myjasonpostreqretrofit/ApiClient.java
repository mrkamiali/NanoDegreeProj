package com.android.myjasonpostreqretrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kamran ALi on 8/10/2016.
 */
public class ApiClient {


    public static final String BASE_URL = "http://alisonstech.com/tourist/index.php/";//Place/getplacesbycatid";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                     .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
