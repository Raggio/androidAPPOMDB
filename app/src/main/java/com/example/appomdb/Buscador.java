package com.example.appomdb;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Buscador extends AppCompatActivity {

    private PeliculaAdapter mAdapter;
    private RecyclerView recyclerViewPelicula;
    private static final int RC_SIGN_IN = 123;
    private final String URL_BASE = "http://10.0.2.2:8080/peliculados/";
    private PeliculasAPI peliculasApi;
    private Button search;
    private EditText titulo;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador);

        search = (Button) findViewById(R.id.buscar);
        titulo = (EditText) findViewById(R.id.titp);
        context = this;

        recyclerViewPelicula = findViewById(R.id.recycler);
        recyclerViewPelicula.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPelicula.setHasFixedSize(true);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        peliculasApi = retrofit.create(PeliculasAPI.class);

    }


    public void buscarPeliculas(View v) {
        Call<List<PeliculaJson>> call = peliculasApi.buscarPelicula(1854030476, "b", titulo.getText().toString());
        call.enqueue(new Callback<List<PeliculaJson>>() {
            @Override
            public void onResponse(Call<List<PeliculaJson>> call, Response<List<PeliculaJson>> response) {
                if (response.isSuccessful()) {
                    mAdapter = new PeliculaAdapter(context, response.body(), this);
                    recyclerViewPelicula.setAdapter(mAdapter);

                } else {
                    Log.e("je", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<List<PeliculaJson>> call, Throwable t) {
                Log.e("Nop", t.getMessage());

            }
        });

    }

    public void puntuadas(View view) {
        List<PeliculaJson> found = new ArrayList<>();
        Call<List<PeliculaJson>> call = peliculasApi.getPeliculas(1854030476, "l");
        call.enqueue(new Callback<List<PeliculaJson>>() {
            @Override
            public void onResponse(Call<List<PeliculaJson>> call, Response<List<PeliculaJson>> response) {
                if (response.isSuccessful()) {
                    mAdapter = new PeliculaAdapter(context, response.body(), this);
                    recyclerViewPelicula.setAdapter(mAdapter);
                    Log.e("bien", response.body().toString());
                } else {
                    Log.e("nop", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<List<PeliculaJson>> call, Throwable t) {
                Log.e("fail", t.getMessage());
            }
        });
    }

    public void guardadas(View view) {

        Call<List<PeliculaJson>> call = peliculasApi.getPeliculas(1854030476, "g");
        call.enqueue(new Callback<List<PeliculaJson>>() {
            @Override
            public void onResponse(Call<List<PeliculaJson>> call, Response<List<PeliculaJson>> response) {
                if (response.isSuccessful()) {
                    mAdapter = new PeliculaAdapter(context, response.body(), this);
                    recyclerViewPelicula.setAdapter(mAdapter);
                    Log.e("bien", response.body().toString());
                } else {
                    Log.e("NOP", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<List<PeliculaJson>> call, Throwable t) {
                Log.e("NO", t.getMessage());
            }
        });
    }
}
