package com.example.parcial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Pelicula> lista = new ArrayList<Pelicula>();
    private ListView listView;
    private FloatingActionButton btnAdd;
    private Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregar();
            }
        });

        try {
            Intent in = getIntent();
            lista = in.getParcelableArrayListExtra("lista");
            if (lista.size() != 0){
                adaptador = new Adaptador(this,lista);
                Toast.makeText(this," aux: "+ lista.size(),Toast.LENGTH_LONG).show();
                listView.setAdapter(adaptador);
            }
        } catch (Exception e){
            //Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.OrdPorGenero:
                if (lista.size() > 0){
                    Collections.sort(lista,new Comparator<Pelicula>() {
                        @Override
                        public int compare(Pelicula o1, Pelicula o2) {
                            return o1.getGenero().compareTo(o2.getGenero());
                        }
                    });
                    adaptador.notifyDataSetChanged();
                } else {
                    Toast.makeText(this,"Error: No hay elementos", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.OrdPorNombre:
                if (lista.size() > 0){
                    Collections.sort(lista,new Comparator<Pelicula>() {
                        @Override
                        public int compare(Pelicula o1, Pelicula o2) {
                            return o1.getNombre().compareTo(o2.getNombre());
                        }
                    });
                    adaptador.notifyDataSetChanged();
                } else {
                    Toast.makeText(this,"Error: No hay elementos", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.InvertirLista:
                if (lista.size() > 0){
                    Collections.reverse(lista);
                    adaptador.notifyDataSetChanged();
                } else {
                    Toast.makeText(this,"Error: No hay elementos", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.EliminarPrimero:
                if (lista.size() > 0){
                    lista.remove( 0);
                    adaptador.notifyDataSetChanged();
                } else {
                    Toast.makeText(this,"No hay elementos que eliminar", Toast.LENGTH_LONG).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void agregar(){
        Intent i = new Intent(this, SecondActivity.class);
        i.putParcelableArrayListExtra("lista2", lista);
        startActivity(i);
    }
}
