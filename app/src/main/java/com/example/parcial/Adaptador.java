package com.example.parcial;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Adaptador extends ArrayAdapter {
    Activity context;
    private ArrayList<Pelicula> lista;


    public Adaptador(@NonNull Activity context, ArrayList<Pelicula> lista) {
        super(context,R.layout.list_item ,lista);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.list_item,null);
        TextView txtNombre = view.findViewById(R.id.lbNombre);
        TextView txtDirector = view.findViewById(R.id.lbDirector);
        TextView txtGenero = view.findViewById(R.id.lbGenero);
        TextView txtIdioma = view.findViewById(R.id.lbIdioma);
        txtNombre.setText(lista.get(position).getNombre());
        txtDirector.setText(lista.get(position).getDirector());
        txtGenero.setText(lista.get(position).getGenero());
        txtIdioma.setText(lista.get(position).getIdioma());
        return view;
    }
    //adaptador.notifyDataSetChanged();

    @Override
    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
    }
}
