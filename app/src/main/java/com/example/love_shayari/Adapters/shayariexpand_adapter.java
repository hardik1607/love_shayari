package com.example.love_shayari.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.love_shayari.Activitys.shayary;
import com.example.love_shayari.R;
import com.example.love_shayari.config;

public class shayariexpand_adapter extends BaseAdapter {

    shayary shayary;
    public shayariexpand_adapter(shayary shayary) {
        this.shayary=shayary;
    }

    @Override
    public int getCount() {
        return config.grediant.length;
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
        view= LayoutInflater.from(shayary).inflate(R.layout.shayariexpand_item,viewGroup,false);
        TextView textView=view.findViewById(R.id.shayariexpand_text);
        textView.setBackgroundResource(config.grediant[i]);
        return view;
    }
}
