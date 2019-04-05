package com.example.thermonitor2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.nfc.Tag;

import com.google.firebase.database.DataSnapshot;

import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.internal.PropertyReference0Impl;


public class DeviceDetailActivity extends AppCompatActivity {

    private static final String TAG = "DeviceDetailActivity";
    TextView temp;
    String datapath="temperature";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_detail);
Bundle bundle = getIntent().getExtras();
if(bundle.getString("ESPMAC")!=null){
    datapath= bundle.getString("ESPMAC");
}
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(datapath);

       // myRef.setValue("temp");

// Read from the database
       temp = findViewById(R.id.temperature);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //Long temperature=dataSnapshot.getValue(Long.class);
                String value = dataSnapshot.getValue().toString();
                temp.setText( "Temperature is: " +value);
               Log.d(TAG, "Value is: " + value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
                temp.setText(error.getMessage());
            }
        });

    }
}
