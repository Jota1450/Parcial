package com.example.parcial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private EditText nombre;
    private EditText director;
    private Spinner genero;
    private RadioButton ingles;
    private RadioButton espanol;
    private Button btnCancelar;
    private Button btnGuardar;
    private ArrayList<Pelicula> lista = new ArrayList<Pelicula>();
    private ArrayList<Pelicula> auxlist = new ArrayList<Pelicula>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        nombre = findViewById(R.id.lbNombre);
        director = findViewById(R.id.lbDirector);
        ingles = findViewById(R.id.rbIngles);
        espanol = findViewById(R.id.rbEspanol);

        genero = findViewById(R.id.spinnerGenero);
        String g  [] = { "Accion","Suspenso","Drama","Comedia"};
        ArrayAdapter<String> g2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,g);
        genero.setAdapter(g2);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });
        btnCancelar = findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelar();
            }
        });

        try {
            Intent in = getIntent();
            if (in != null){
                auxlist = in.getParcelableArrayListExtra("lista2");
                Toast.makeText(this," aux: "+ auxlist.size(),Toast.LENGTH_LONG).show();
            }
            unificar();
        } catch (Exception e){
            //Toast.makeText(this,e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.Mayuscula:
                Toast.makeText(this,"Mayuscula", Toast.LENGTH_LONG).show();
                break;
            case R.id.Listado:
                sig();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void guardar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Seguro que desea guardar")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (ingles.isChecked() == true){
                            lista.add(new Pelicula(
                                    nombre.getText().toString(),
                                    director.getText().toString(),
                                    genero.getSelectedItem().toString(),
                                    "Ingles"));
                        } else {
                            lista.add(new Pelicula(
                                    nombre.getText().toString(),
                                    director.getText().toString(),
                                    genero.getSelectedItem().toString(),
                                    "Español"));
                        }
                        Toast.makeText(getApplicationContext(),"Guardado Exitoso", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alerta = builder.create();
        alerta.show();
    }
    public void cancelar(){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setMessage("Desea Cancelar")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(),"Cancelacion Exitosa", Toast.LENGTH_LONG).show();
                        sig();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog a = b.create();
        a.show();
    }
    public void sig(){
        Intent i = new Intent(this, MainActivity.class);
        i.putParcelableArrayListExtra("lista", lista);
        startActivity(i);
    }
    private void unificar(){
        for (int i=0;i<auxlist.size();i++){
            lista.add(auxlist.get(i));
        }
    }
}
