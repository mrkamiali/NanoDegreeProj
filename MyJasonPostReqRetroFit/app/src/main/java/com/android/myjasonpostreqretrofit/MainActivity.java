package com.android.myjasonpostreqretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.android.myjasonpostreqretrofit.model.Place;
import com.android.myjasonpostreqretrofit.model.PlacesResponse;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {
    private ListView placesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit client = ApiClient.getClient();

        ApiInterface apiService = client.create(ApiInterface.class);

        HashMap<String, String> map = new HashMap<>();
        map.put("tourist_guide_key_mob", "yest_true");
        map.put("city_id", "64");
        map.put("id", "6");


        Call<PlacesResponse> call = apiService.mapRequest(map);
        call.enqueue(new Callback<PlacesResponse>() {
            @Override
            public void onResponse(Call<PlacesResponse> call, Response<PlacesResponse> response) {
                int code = response.code();
                Log.d("Status Code", "" + code);

                Place place = response.body().getPlaces().get(0);

                String s = place.getName().toString();

                Log.d("Name", "" + s);

                List<Place> places = response.body().getPlaces();
                Log.d("Length of List", "" + places.size());
            }

            @Override
            public void onFailure(Call<PlacesResponse> call, Throwable t) {
                Log.e("On FAilure", "" + t.getMessage());
                t.printStackTrace();
            }
        });

    }

    public interface ApiInterface {
        @FormUrlEncoded
        @POST("Place/getplacesbycatid")
        Call<PlacesResponse> apiResponseToken(@Field("tourist_guide_key_mob") String key,
                                              @Field("city_id") String city_id,
                                              @Field("id") String id
        );

        @FormUrlEncoded
        @POST("Place/getplacesbycatid")
        Call<PlacesResponse> mapRequest(@FieldMap HashMap<String, String> map);

    }
}
