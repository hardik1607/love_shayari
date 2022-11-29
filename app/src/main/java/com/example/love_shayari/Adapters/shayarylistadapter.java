package com.example.love_shayari.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.love_shayari.Activitys.shayarylist;
import com.example.love_shayari.R;
import com.example.love_shayari.config;

public class shayarylistadapter extends BaseAdapter {

    shayarylist s;
    String[] shayary;
    int image;
    public shayarylistadapter(shayarylist s,String[] shayary,int image) {
        this.s=s;
        this.shayary=shayary;
        this.image=image;
    }

    @Override
    public int getCount() {
        return shayary.length;
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

        view= LayoutInflater.from(s).inflate(R.layout.shayarylist_item,viewGroup,false);

        TextView textView=view.findViewById(R.id.shayarilist_shayary);
        ImageView imageView=view.findViewById(R.id.shayarilist_image);

        textView.setText(shayary[i]);
        imageView.setImageResource(image);
        return view;
    }
}
