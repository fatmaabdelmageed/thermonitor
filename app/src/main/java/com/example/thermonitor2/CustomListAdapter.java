package com.example.thermonitor2;



import android.app.Activity;

import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

import android.widget.ArrayAdapter;

import android.widget.ImageView;

import android.widget.TextView;

import java.util.ArrayList;



public class CustomListAdapter extends ArrayAdapter {

    private final Activity context;

    private Integer[] imageIDarray={R.drawable.esp8622};

    private final ArrayList<String> nameArray;

   // Integer[] imageIDArrayParam;

    public CustomListAdapter(Activity context, ArrayList<String>nameArrayParam) {

        super(context, R.layout.row, nameArrayParam);

        this.context=context;

       // this.imageIDarray = imageIDArrayParam;

        this.nameArray = nameArrayParam;

    }

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.row, null, true);

        TextView nameTextField = (TextView) rowView.findViewById(R.id.textView);

        //ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView);

        nameTextField.setText(nameArray.get(position));

       // imageView.setImageResource(imageIDarray[0]);

        return rowView;

    }

}
