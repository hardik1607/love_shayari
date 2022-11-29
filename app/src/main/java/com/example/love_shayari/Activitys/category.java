package com.example.love_shayari.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ListView;
import android.widget.Toast;

import com.example.love_shayari.R;
import com.example.love_shayari.Adapters.categoryadapter;
import com.example.love_shayari.config;

import java.io.File;

public class category extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);

        listView=findViewById(R.id.category_list1);

        categoryadapter ca = new categoryadapter(this);
        listView.setAdapter(ca);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(category.this,shayarylist.class);
            intent.putExtra("position",i);
            startActivity(intent);
        });

        ActivityCompat.requestPermissions(category.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {


                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    config.file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+"/shayari");
                    if (config.file.exists())
                    {
                        System.out.println("folder available");
                    }
                    else
                    {
                        System.out.println("folder not available");
                        if (config.file.mkdir())
                        {
                            System.out.println("folder created");
                        }
                        else
                        {
                            System.out.println("folder not created");
                        }
                    }
                } else {

                    ActivityCompat.requestPermissions(category.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);
                    Toast.makeText(category.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}