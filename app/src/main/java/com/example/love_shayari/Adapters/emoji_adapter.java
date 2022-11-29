package com.example.love_shayari.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.love_shayari.Activitys.pencil;
import com.example.love_shayari.R;
import com.example.love_shayari.config;

public class emoji_adapter extends BaseAdapter {

    pencil pencil;
    public emoji_adapter(pencil pencil) {
        this.pencil=pencil;
    }

    @Override
    public int getCount() {
        return config.emoji.length;
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
        view= LayoutInflater.from(pencil).inflate(R.layout.emoji_item,viewGroup,false);

        TextView textView=view.findViewById(R.id.emoji_text);
        textView.setText(config.emoji[i]);
        return view;
    }
}
