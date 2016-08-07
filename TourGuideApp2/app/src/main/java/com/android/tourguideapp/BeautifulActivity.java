package com.android.tourguideapp;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class BeautifulActivity extends AppCompatActivity {
    private ListView mListView;
    private ArrayList<Description> mArrayList;
    private PlacesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);
        mListView = (ListView) findViewById(R.id.rootListView);
        mArrayList = new ArrayList<>();
        mArrayList.add(new Description("Masjid-e-Tooba", "Mosque", "Karachi,Sindh Pakistan", R.drawable.masjid_e_tooba));
        mArrayList.add(new Description("Karachi Golf Club", "Club", "Karachi,Sindh Pakistan", R.drawable.golfclub_beautiful));
        mArrayList.add(new Description("Jheel Park", "Park, Garden, Beauty", "Karachi,Sindh Pakistan", R.drawable.jeheelpark_beautiful));
        mArrayList.add(new Description("Karachi Zoo", "Entertainment, Zoo, Park", "Karachi,Sindh Paistan", R.drawable.zoo_beautiful));
        mArrayList.add(new Description("Manora, Karachi", "Island, Port", "Karachi,Sindh Pakistan", R.drawable.manora_beautiful));


        adapter = new PlacesAdapter(mArrayList, this);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Description description = mArrayList.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(BeautifulActivity.this);
                builder.setTitle(description.getPlaceName());
                builder.setMessage(description.getDescription());
                View view1 = getLayoutInflater().inflate(R.layout.alert_builder_layout, null);
                ImageView imgView = (ImageView) view1.findViewById(R.id.alertbuilder_imageView);
                imgView.setImageResource(description.getImageResourceID());
                builder.setView(view1);

                builder.create().show();
            }
        });
    }
}
