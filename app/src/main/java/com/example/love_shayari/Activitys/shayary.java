package com.example.love_shayari.Activitys;

import static com.example.love_shayari.Activitys.pencil.getBitmapFromView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.love_shayari.Adapters.expand_adapter;
import com.example.love_shayari.Adapters.shayariexpand_adapter;
import com.example.love_shayari.Adapters.shayaryadapter;
import com.example.love_shayari.R;
import com.example.love_shayari.config;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class shayary extends AppCompatActivity {


    TextView title,shayary_text;
    ImageView next,prev,copy,share,pencil,expand,reload;
    int shayaripos;
    String[] arr;
    GridView gridView;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shayary);

        shayary_text=findViewById(R.id.shayary_text);
        title=findViewById(R.id.shayary_title);
        next=findViewById(R.id.shayary_next);
        prev=findViewById(R.id.shayary_prev);
        copy=findViewById(R.id.shayary_copy);
        share=findViewById(R.id.shayary_share);
        pencil=findViewById(R.id.shayary_pencil2);
        expand=findViewById(R.id.shayary_expand);
        reload=findViewById(R.id.shayary_reload);
        gridView=findViewById(R.id.expand_grid);
        linearLayout=findViewById(R.id.shayari_linear);

        shayaripos=getIntent().getIntExtra("position",0);
        arr=getIntent().getStringArrayExtra("arr");


        shayary_text.setText(arr[shayaripos]);
        title.setText((shayaripos+1)+"/"+ arr.length);

        copy.setOnClickListener(view -> {
            ClipboardManager clipboardManager= (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clipData=ClipData.newPlainText("text",arr[shayaripos]);
            clipboardManager.setPrimaryClip(clipData);

            Toast.makeText(shayary.this,"copied",Toast.LENGTH_SHORT).show();
        });

        share.setOnClickListener(view -> {
            Bitmap icon = getBitmapFromView(linearLayout);
            System.out.println("bitmap======>"+icon);
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("image/jpeg");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            int num=new Random().nextInt(2000);
            File f = new File(config.file.getAbsolutePath()  + "/temporary_file"+num+".jpg");
            try {
                f.createNewFile();
                FileOutputStream fo = new FileOutputStream(f);
                fo.write(bytes.toByteArray());
                Toast.makeText(shayary.this, "file downloaded", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                share.putExtra(Intent.EXTRA_STREAM, Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), f.getAbsolutePath(),"img","Identified image")));
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            startActivity(Intent.createChooser(share, "Share Image"));
        });

        prev.setOnClickListener(view -> {
            if (shayaripos>0)
            {
                shayaripos--;
                shayary_text.setText(arr[shayaripos]);
                title.setText((shayaripos+1)+"/"+arr.length);
            }
        });

        next.setOnClickListener(view -> {
            if (shayaripos<arr.length-1)
            {
                shayaripos++;
                shayary_text.setText(arr[shayaripos]);
                title.setText((shayaripos+1)+"/"+arr.length);
            }
        });


        pencil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shayary.this,pencil.class);
                intent.putExtra("shayari",shayary_text.getText().toString());
                startActivity(intent);
            }
        });
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int min=0;
                int max=config.grediant.length;
                int random=new Random().nextInt(max-min)+min;
                linearLayout.setBackgroundResource(config.grediant[random]);
            }
        });

        expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(shayary.this);
                bottomSheetDialog.setCancelable(false);
                view= LayoutInflater.from(shayary.this).inflate(R.layout.shayariexpand_layout,null);

                gridView=view.findViewById(R.id.shayariexpand_grid);
                shayariexpand_adapter adapter = new shayariexpand_adapter(shayary.this);
                gridView.setAdapter(adapter);

                bottomSheetDialog.setContentView(view);
                bottomSheetDialog.show();
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        linearLayout.setBackgroundResource(config.grediant[i]);
                        bottomSheetDialog.dismiss();
                    }
                });
            }
        });
    }
}