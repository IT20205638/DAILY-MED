package com.united.dailymed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HeartHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_history);
         ArrayList<HeartRateModel> HeartRateModelArrayList;
         HeartDBHandler HeartdbHandler;
        HeartRateRVAdapter heartRVAdapter;
         RecyclerView heartRV;

        // initializing our all variables.
        HeartRateModelArrayList = new ArrayList<>();
        HeartdbHandler = new HeartDBHandler(HeartHistory.this);

        // getting our course array
        // list from db handler class.
        HeartRateModelArrayList = HeartdbHandler.readHeartRates();

        // on below line passing our array lost to our adapter class.
        heartRVAdapter = new HeartRateRVAdapter(HeartRateModelArrayList, HeartHistory.this);
        heartRV = findViewById(R.id.idRVHeart);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HeartHistory.this, RecyclerView.VERTICAL, false);
        heartRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        heartRV.setAdapter(heartRVAdapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Heart Selected
        bottomNavigationView.setSelectedItemId(R.id.Heart);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch(menuItem.getItemId()) {
                case R.id.Heart:
                    startActivity(new Intent(getApplicationContext(),Heart.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(),Home.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.Fitness:
                    startActivity(new Intent(getApplicationContext(),Fitness.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.Pill:
                    startActivity(new Intent(getApplicationContext(),Pill.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.Water:
                    startActivity(new Intent(getApplicationContext(),Water.class));
                    overridePendingTransition(0,0);
                    return true;
            }

            return false;
        });
    }
}