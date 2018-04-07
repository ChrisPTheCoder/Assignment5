package com.example.linhp.phuonglinhpham_assignment5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String[] collegesArray= {"Seneca","Humber","Sheridan","Centennial"};
    ListView collegeView;
    ArrayAdapter collegeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        collegeView = (ListView) findViewById(R.id.collegeView);

        collegeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, collegesArray);

        // Attach the array adapter to the spinner
        collegeView.setAdapter(collegeAdapter);

        collegeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent;
                String selectedSetting = (String) collegeView.getItemAtPosition(position);
                intent = new Intent(MainActivity.this, CollegesActivity.class);
                intent.putExtra("cuisine", selectedSetting);
                startActivity(intent);
            }
        });
    }
}

