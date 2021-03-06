package com.example.appomdb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Callback;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.ViewHolder> {


    private List<PeliculaJson> peliculaList;
    private Context contexto;

    public PeliculaAdapter(Context contexto, List<PeliculaJson> peliculaList, Callback<List<PeliculaJson>> listCallback) {
        this.contexto = contexto;
        this.peliculaList = peliculaList;
    }

    public void setPeliculaList(List<PeliculaJson> peliculaList) {
        this.peliculaList = peliculaList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView namep;
        private ImageView imp;
        private TextView yearp;
        CardView cardview;
        public ViewHolder(View v) {
            super(v);
            namep = (TextView) v.findViewById(R.id.nombrep);
            imp = (ImageView) v.findViewById(R.id.imagenp);
            yearp = (TextView) v.findViewById(R.id.aniop);
            cardview = (CardView) v.findViewById(R.id.card);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pelicula_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PeliculaJson pelicula = peliculaList.get(position);
        ViewHolder Holder = (ViewHolder) holder;
        Holder.namep.setText(pelicula.getTitulo());
        Holder.yearp.setText(pelicula.getAnio());





    }

    @Override
    public int getItemCount() {
        return peliculaList.size();
    }
}