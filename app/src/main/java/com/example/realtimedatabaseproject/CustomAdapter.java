package com.example.realtimedatabaseproject;

import android.app.Activity;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Model> {

        private Activity context;
        private List<Model> modelList;

    public CustomAdapter(Activity context, List<Model> modelList) {
        super(context, R.layout.layout_of_list,modelList);
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {

        LayoutInflater layoutInflater=context.getLayoutInflater();
       View view= layoutInflater.inflate(R.layout.layout_of_list,null,true);


        Model model=modelList.get(position);

        TextView textView1=view.findViewById(R.id.name_id);
        TextView textView2=view.findViewById(R.id.age_id);

        textView1.setText("Name :"+model.getName());
        textView2.setText("Age :"+model.getAge());

        return view;
    }
}
