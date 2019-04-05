package com.example.thermonitor2;



import android.Manifest;

import android.content.BroadcastReceiver;

import android.content.Context;

import android.content.Intent;

import android.content.IntentFilter;

import android.content.pm.PackageManager;

import android.graphics.Color;

import android.net.wifi.ScanResult;

import android.net.wifi.WifiConfiguration;

import android.net.wifi.WifiInfo;

import android.net.wifi.WifiManager;

import android.os.Build;

import android.support.v4.content.ContextCompat;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;

import android.view.View;

import android.widget.AdapterView;

import android.widget.ArrayAdapter;

import android.widget.Button;

import android.widget.ListView;

import android.widget.TextView;

import android.widget.Toast;



import com.google.firebase.FirebaseApp;

import com.google.firebase.FirebaseOptions;

import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;



import java.io.FileInputStream;

import java.util.ArrayList;

import java.util.List;


public class ListActivity extends AppCompatActivity {

    ListView simpleList;

    Integer[] imageArray= {R.drawable.esp8622} ;

    private WifiManager wifiManager;

    private Button buttonScan;

    private int size = 0;

    private List<ScanResult> results;

    private ArrayList<String> arrayList = new ArrayList<>();

    CustomListAdapter whatever;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){

            System.out.print("LOP");

            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){

                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},87);

            }

            else{

                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},87);

            }

        }

        whatever = new CustomListAdapter(this, arrayList);

        simpleList = (ListView) findViewById(R.id.list_view);

        simpleList.setAdapter(whatever);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ListActivity.this, DeviceDetailActivity.class);

                startActivity(intent);

            }

        });

        if (!wifiManager.isWifiEnabled()) {

            Toast.makeText(this, "WiFi is disabled ... We need to enable it", Toast.LENGTH_LONG).show();

            wifiManager.setWifiEnabled(true);

        }



        scanWifi();



    }



    private void scanWifi() {



        arrayList.clear();



        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

        wifiManager.startScan();

        Toast.makeText(this, "Scanning WiFi ...", Toast.LENGTH_SHORT).show();

    }

    BroadcastReceiver wifiReceiver = new BroadcastReceiver() {

        @Override

        public void onReceive(Context context, Intent intent) {

            results = wifiManager.getScanResults();

            unregisterReceiver(this);

            for (ScanResult scanResult : results) {

                if(scanResult.SSID.equals("MyESP8266AP")){

                arrayList.add(scanResult.SSID + " - " + scanResult.capabilities);

                whatever.notifyDataSetChanged();

                }

            }



        }



    };



}