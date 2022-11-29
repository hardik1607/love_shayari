package com.example.love_shayari.Activitys;

import static com.example.love_shayari.R.id.textsize_seek;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.love_shayari.Adapters.background_adapter;
import com.example.love_shayari.Adapters.emoji_adapter;
import com.example.love_shayari.Adapters.expand_adapter;
import com.example.love_shayari.Adapters.font_adapter;
import com.example.love_shayari.R;
import com.example.love_shayari.config;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class pencil extends AppCompatActivity {

    TextView textView;
    ImageView reload,expand,download;
    Button background,textcolor,share,font,emoji,textsize;
    LinearLayout linearLayout;
    String text;
    GridView gridView;
    ListView listView;
    SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencil);

        textView=findViewById(R.id.pencil_text);
        reload=findViewById(R.id.pencil_reload);
        expand=findViewById(R.id.pencil_expand);
        background=findViewById(R.id.pencil_background);
        textcolor=findViewById(R.id.pencil_textcolor);
        share=findViewById(R.id.pencil_share);
        font=findViewById(R.id.pencil_font);
        emoji=findViewById(R.id.pencil_emoji);
        textsize=findViewById(R.id.pencil_textsize);
        linearLayout = findViewById(R.id.pencil_linear);
        seekBar = findViewById(textsize_seek);
        download=findViewById(R.id.pencil_download);


        text=getIntent().getStringExtra("shayari");
        textView.setText(text);


        background.setOnClickListener(view -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
            bottomSheetDialog.setCancelable(false);
            View view1= LayoutInflater.from(this).inflate(R.layout.background_layout,null);

            gridView=view1.findViewById(R.id.background_view);
            background_adapter adapter = new background_adapter(this);
            gridView.setAdapter(adapter);

            bottomSheetDialog.setContentView(view1);
            bottomSheetDialog.show();

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    linearLayout.setBackgroundColor(getResources().getColor(config.colors[i]));
                    bottomSheetDialog.show();
                    bottomSheetDialog.setCancelable(true);
                }
            });
        });

        textcolor.setOnClickListener(view -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
            bottomSheetDialog.setCancelable(false);
            View view1=LayoutInflater.from(this).inflate(R.layout.background_layout,null);

            gridView=view1.findViewById(R.id.background_view);
            background_adapter adapter = new background_adapter(this);
            gridView.setAdapter(adapter);
            bottomSheetDialog.setContentView(view1);
            bottomSheetDialog.show();

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    textView.setTextColor(getResources().getColor(config.colors[i]));
                    bottomSheetDialog.show();
                    bottomSheetDialog.setCancelable(true);
                }
            });
        });

        textsize.setOnClickListener(view -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
            bottomSheetDialog.setCancelable(false);
            View view1=LayoutInflater.from(this).inflate(R.layout.textsize_layout,null);

            seekBar=view1.findViewById(textsize_seek);

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    textView.setTextSize(seekBar.getProgress());
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            bottomSheetDialog.setContentView(view1);
            bottomSheetDialog.show();
            bottomSheetDialog.setCancelable(true);
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap icon = getBitmapFromView(linearLayout);
                System.out.println("bitmap======>"+icon);

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                int num=new Random().nextInt(2000);
                File f = new File(config.file.getAbsolutePath()  + "/temporary_file"+num+".jpg");
                try {
                    f.createNewFile();
                    FileOutputStream fo = new FileOutputStream(f);
                    fo.write(bytes.toByteArray());
                    Toast.makeText(pencil.this, "file downloaded", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                    Toast.makeText(pencil.this, "file downloaded", Toast.LENGTH_SHORT).show();
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
            }
        });

        font.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(pencil.this);
                bottomSheetDialog.setCancelable(false);
                view=LayoutInflater.from(pencil.this).inflate(R.layout.font_layout,null);

                gridView=view.findViewById(R.id.font_grid);
                font_adapter adapter = new font_adapter(pencil.this);
                gridView.setAdapter(adapter);
                gridView.setNumColumns(config.font.length);

                bottomSheetDialog.setContentView(view);
                bottomSheetDialog.show();

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Typeface typeface=Typeface.createFromAsset(getAssets(),config.font[i]);
                        textView.setTypeface(typeface);
                        bottomSheetDialog.setCancelable(true);
                    }
                });
            }
        });

        emoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(pencil.this);
                bottomSheetDialog.setCancelable(false);
                view=LayoutInflater.from(pencil.this).inflate(R.layout.emoji_layout,null);

                listView=view.findViewById(R.id.emoji_list);
                emoji_adapter adapter = new emoji_adapter(pencil.this);
                listView.setAdapter(adapter);
                bottomSheetDialog.setContentView(view);
                bottomSheetDialog.show();

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        textView.setText(config.emoji[i]+"\n"+text+"\n"+config.emoji[i]);
                        bottomSheetDialog.setCancelable(true);
                    }
                });
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

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(pencil.this);
                bottomSheetDialog.setCancelable(false);
                view= LayoutInflater.from(pencil.this).inflate(R.layout.expand_layout,null);

                gridView=view.findViewById(R.id.expand_grid);
                expand_adapter adapter = new expand_adapter(pencil.this);
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
    public static Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return returnedBitmap;
    }
}