package com.example.linhp.phuonglinhpham_assignment5;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CollegesActivity extends AppCompatActivity {
    String chosenCollege="";
    List<String> chosenCollegeArray= new ArrayList<String>();
    List<String> chosenlocationsArray= new ArrayList<String>();
    ListView collegeListView;
    ArrayAdapter collegeAdapter;
    List<Address> geoCodes;
    Context thisContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colleges);
        this.thisContext = this;
        chosenCollege=getIntent().getStringExtra("college");
        switch (chosenCollege){
            case "Seneca":
                chosenCollegeArray= Arrays.asList(getResources().getStringArray(R.array.Seneca));
                chosenlocationsArray= Arrays.asList(getResources().getStringArray(R.array.SenecaLocation));
                break;
            case "Humber":
                chosenCollegeArray=Arrays.asList(getResources().getStringArray(R.array.Humber));
                chosenlocationsArray=Arrays.asList(getResources().getStringArray(R.array.HumberLocation));
                break;
            case "Sheridan":
                chosenCollegeArray=Arrays.asList(getResources().getStringArray(R.array.Sheridan));
                chosenlocationsArray=Arrays.asList(getResources().getStringArray(R.array.SheridanLocation));
                break;
            case "Centennial":
                chosenCollegeArray=Arrays.asList(getResources().getStringArray(R.array.Centennial));
                chosenlocationsArray=Arrays.asList(getResources().getStringArray(R.array.CentennialLocation));
                break;


        }


        ((TextView)findViewById(R.id.txtSelectedCollege)).setText("Chosen College: "+ chosenCollege);

        collegeListView = (ListView) findViewById(R.id.lvCollege);

        collegeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, chosenCollegeArray);

        collegeListView.setAdapter(collegeAdapter);


        collegeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent;
                String selectedSetting = (String) collegeListView.getItemAtPosition(position);
                selectedSetting+=" ,Toronto, ON";

                intent = new Intent(CollegesActivity.this, MapsActivity.class);
                String temp1 = (String)chosenlocationsArray.get(position);
                String[] coordinates = temp1.split(",");

                try {
                    geoCodes = new Geocoder(thisContext, Locale.getDefault()).getFromLocationName(selectedSetting, 1);

                    if(geoCodes.isEmpty()){
                        intent.putExtra("Lat", Double.parseDouble(coordinates[0]));
                        intent.putExtra("Lng", Double.parseDouble(coordinates[1]));

                    }
                    else {
                        intent.putExtra("Lat", geoCodes.get(0).getLatitude());
                        intent.putExtra("Lng", geoCodes.get(0).getLongitude());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


                intent.putExtra("Rst", selectedSetting);
                startActivity(intent);
            }
        });

    }
}

