package com.example.love_shayari.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.love_shayari.Activitys.category;
import com.example.love_shayari.R;
import com.example.love_shayari.config;

public class categoryadapter extends BaseAdapter {
    category m;
    public categoryadapter(category m) {
        this.m=m;
    }

    @Override
    public int getCount() {
        return config.category_shayary.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(m).inflate(R.layout.category_item,viewGroup,false);

        TextView textView=view.findViewById(R.id.category_shayary);
        ImageView imageView=view.findViewById(R.id.category_image);

        textView.setText(config.category_shayary[i]);
        imageView.setImageResource(config.category_images[i]);
        return view;
    }
}
