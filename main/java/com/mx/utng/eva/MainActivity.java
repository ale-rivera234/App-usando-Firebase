package com.mx.utng.eva;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText etTema;
    Spinner spiArea;
    Spinner spiSec;
    Button btnRegistrar;

    private DatabaseReference dbDiario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTema = (EditText)findViewById(R.id.etTema);
        spiArea = (Spinner)findViewById(R.id.spiArea);
        spiSec = (Spinner)findViewById(R.id.spiSeccion);
        btnRegistrar = (Button)findViewById(R.id.btnRegistrar);

        dbDiario = FirebaseDatabase.getInstance().getReference("Clase");

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarClase();
            }
        });
    }//fin de onCreate

    public void registrarClase(){
        String seccion = spiSec.getSelectedItem().toString();
        String area = spiArea.getSelectedItem().toString();
        String tema = etTema.getText().toString();

        if(!TextUtils.isEmpty(tema)){
            String id = dbDiario.push().getKey();
            Clase leccion = new Clase(id, seccion, area, tema);
            dbDiario.child("Lecciones").child(id).setValue(leccion);

            Toast.makeText(this, "Clase Registrada", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Debe introducir un tema", Toast.LENGTH_LONG).show();
        }
    }




}