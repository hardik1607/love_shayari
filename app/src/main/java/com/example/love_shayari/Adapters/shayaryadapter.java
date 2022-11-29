package com.example.love_shayari.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.love_shayari.Activitys.shayary;
import com.example.love_shayari.R;

public class shayaryadapter extends BaseAdapter {

    shayary s;
    String[] shayary;
    public shayaryadapter(shayary s, String[] shayary) {
        this.s=s;
        this.shayary=shayary;
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
        view= LayoutInflater.from(s).inflate(R.layout.shayary_item,viewGroup,false);

        TextView textView=view.findViewById(R.id.shayary_text);

        textView.setText(shayary[i]);
        return view;
    }
}
