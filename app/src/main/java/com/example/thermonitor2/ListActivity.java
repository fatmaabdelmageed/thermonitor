package com.example.thermonitor2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        String items[]= new String [] {"Android”, “iPhone”, “Windows”, “Blackberry”, “Linux"};
        listView = (ListView) findViewById(R.id.list_item);

        ArrayAdapter<String> adapter = new ArrayAdapter <String>(this,android.R.layout.simple_list_item_1 ,items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent (ListActivity.this , DeviceDetailActivity.class );
                startActivity(intent);
            }
        });
    }
    }

