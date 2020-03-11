package com.example.appomdb;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PeliculasAPI {

    @GET("procesarapi")
    Call<List<PeliculaJson>> getPeliculas(@Query("key") int key, @Query("a") String a);
    @GET("procesarapi")
    Call<String> guardarPelicula(@Query("key") String key, @Query("a") String act, @Query("id") String id);
    @GET("procesarapi")
    Call<List<PeliculaJson>> buscarPelicula(@Query("key") int key, @Query("a") String act, @Query("t") String titulo);
    @GET("procesarapi")
    Call<String> guardarPeliculaPuntuacion(@Query("key") String key, @Query("a") String action, @Query("id") String idp, @Query("p") String p);

}
