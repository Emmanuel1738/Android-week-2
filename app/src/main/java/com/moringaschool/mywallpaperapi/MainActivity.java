package com.moringaschool.mywallpaperapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

@BindView(R.id.fetchWallpapersButton) Button fetchWallpapers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        fetchWallpapers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WallpaperActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Fetching Wallpapers", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

//7OabpYvsMCN5yM7-a-Dxo-cPMapIDLFRececgrqmvTE