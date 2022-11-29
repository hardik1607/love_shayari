package com.example.love_shayari.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.love_shayari.R;
import com.example.love_shayari.Adapters.shayarylistadapter;
import com.example.love_shayari.config;

public class shayarylist extends AppCompatActivity {

    ListView listView;
    int pos;
    String[] temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shayarylist);

        listView=findViewById(R.id.shayarilist_list2);


        pos=getIntent().getIntExtra("position",0);

        if(pos==0)
        {
            temp= config.attitude_shayary;
        }
        if(pos==1)
        {
            temp= config.love_shayary;
        }
        if(pos==2)
        {
            temp= config.birthday_shayary;
        }
        if(pos==3)
        {
            temp= config.child_shayary;
        }
        if(pos==4)
        {
            temp= config.bewfa_shayary;
        }

        shayarylistadapter shayarylistadapter = new shayarylistadapter(this,temp,config.category_images[pos]);
        listView.setAdapter(shayarylistadapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {

            Intent intent = new Intent(shayarylist.this,shayary.class);
            intent.putExtra("position",i);
            intent.putExtra("arr",temp);
            startActivity(intent);
        });
    }
}