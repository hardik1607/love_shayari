package com.example.love_shayari.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.example.love_shayari.Activitys.pencil;
import com.example.love_shayari.R;
import com.example.love_shayari.config;

public class background_adapter extends BaseAdapter {

    pencil pencil;
    public background_adapter(pencil pencil) {
        this.pencil=pencil;
    }

    @Override
    public int getCount() {
        return config.colors.length;
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
        view= LayoutInflater.from(pencil).inflate(R.layout.background_item,viewGroup,false);

        LinearLayout linearLayout=view.findViewById(R.id.background_grid);
        linearLayout.setBackgroundColor(pencil.getResources().getColor(config.colors[i]));
        return view;
    }
}
