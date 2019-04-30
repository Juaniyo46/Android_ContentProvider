package com.example.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView item;
    private Button btnConsultar;
    private Button btnInsertar;
    private Button btnEliminar;
    private Button btnLimpiar;
    private Button btnContactos;

    private ListView lista;

    //Codigo requerido para leer contactos cuando este sea mayor que 0.
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Enlazar a listView
        this.lista = findViewById(R.id.lista);

        //Enlazar botones
        item = findViewById(R.id.item);
        btnConsultar = findViewById(R.id.consultar);
        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalls();
            }
        });



    }

    private void showCalls(){
        //Checkeamos la version de sdk para pedir permisos o no.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_CALL_LOG}, PERMISSIONS_REQUEST_READ_CONTACTS);

        }else {
            //Si la version es menor de 6.0 o los permisos ya estan agragados
            List<String> calls = getCallName();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,calls);
        }
    }
}
