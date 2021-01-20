package com.moringaschool.mywallpaperapi;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.util.ArrayList;

public class WallpaperActivity extends AppCompatActivity {
private final String API_URL = "https://manuapi.herokuapp.com/wallpapers";
    private EditText searchEt;
    private RecyclerView wallpaperRv;
    private Adapter myAdapter;
    private ArrayList<WallpaperModel> wallpaperModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);

        wallpaperRv = findViewById(R.id.recyclerView);
        wallpaperRv.setHasFixedSize(true);
        wallpaperRv.setLayoutManager(new LinearLayoutManager(this));

        loadWallpaperData();

    }

    private void loadWallpaperData() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                wallpaperModelArrayList = new ArrayList<>();
                wallpaperModelArrayList.clear();

                try {
                    //converting json object
                    JSONArray array = new JSONArray(response);

//            JSONArray jsonArray = new JSONArray(response);
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.create();

                    //actual fetching data

                    for (int i = 0; i < array.length(); i++) {
                        WallpaperModel wallpaperModel = gson.fromJson(array.getJSONObject(i).toString(), WallpaperModel.class);
                        wallpaperModelArrayList.add(wallpaperModel);
                    }

                    //setup adapter
                    myAdapter = new MyAdapter(getApplicationContext(), wallpaperModelArrayList);
                    wallpaperRv.setAdapter(myAdapter);
                    progressDialog.dismiss();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Check that you have an active internet connection", Toast.LENGTH_SHORT).show();
                Log.i("TRUE","No internet");
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


}
