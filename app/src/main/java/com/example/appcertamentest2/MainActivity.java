package com.example.appcertamentest2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Creacion de variables principales
    EditText titulo, contenido, notaAEliminar;
    ListView listView;

    //Implementacion de listas a utilizar y adapter
    ArrayList<String> infoNotas;
    ArrayAdapter<String> adapter;
    ArrayList<Nota> notas = new ArrayList<Nota>();

    //Seleccion del usuario
    //String seleccion = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Encuento de variables con variables en archivo .xml
        titulo = findViewById(R.id.txtTituloNota);
        contenido = findViewById(R.id.txtContenidoNota);
        listView = findViewById(R.id.listVieww);
        notaAEliminar = findViewById(R.id.txtEliminarNota);

        //Inicializacion de listas
        infoNotas = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,infoNotas);
        //Setteamos el adapter
        listView.setAdapter(adapter);

        //Codigo necesario para obtener el objeto seleccionado por el usuario en el listView
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Nota notaSeleccionada = (Nota) adapterView.getItemAtPosition(i);

                seleccion = notaSeleccionada.getTitulo();
            }
        });*/

    }

    public void agregarNota(View view){
        String txtTitulo = titulo.getText().toString();
        String txtContenido = contenido.getText().toString();

        if(!txtTitulo.isEmpty() && !txtContenido.isEmpty()){
            Nota nota = new Nota(txtTitulo, txtContenido);
            notas.add(nota);

            Toast.makeText(this, "Nota agregada correctamente al listView!", Toast.LENGTH_SHORT).show();
            visualizarNotas();
        } else {
            Toast.makeText(this, "Favor rellenar campos correspondientes!", Toast.LENGTH_SHORT).show();
        }
    }

    public void visualizarNotas(){
        //Limpiamos la lista antes de visualizarla
        infoNotas.clear();
        for(int i = 0; i < notas.size(); i++){
            Nota nota1 = notas.get(i); //Obtenemos el indice de notas a utilizar

            String info = "Título: " + nota1.getTitulo().toString() + "\n" + "Contenido: " + nota1.getContenido();
            infoNotas.add(info);

        }
        //Notificamos al adaptador del cambio
        adapter.notifyDataSetChanged();

    }

    /*public void editarNota(View view){
        for(int i = 0; i < notas.size(); i++){
            if(notas.get(i).getTitulo().equals(seleccion)){
                titulo.setText(notas.get(i).getTitulo());
                contenido.setText(notas.get(i).getContenido());
            }
        }
    }*/


    public void eliminarNota(View view) {
        String notaEliminar = notaAEliminar.getText().toString();

        if(!notaEliminar.isEmpty()){
            boolean eliminado = false;

            for(int i = 0; i < notas.size(); i++){
                Nota notas2 = notas.get(i);

                if(notas2.getTitulo().equals(notaEliminar)){
                    notas.remove(i);
                    infoNotas.remove(i);
                    Toast.makeText(this, "Nota removida con éxito!", Toast.LENGTH_SHORT).show();
                    eliminado = true;
                    break;
                }
            }

            if(!eliminado) {
                Toast.makeText(this, "Favor intente con un título existente!", Toast.LENGTH_SHORT).show();
            }

            //Notificamos al adapter
            adapter.notifyDataSetChanged();

        } else {
            Toast.makeText(this, "Favor ingrese un título para eliminar!", Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarDatos(){

    }



}