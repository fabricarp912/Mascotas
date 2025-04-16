package com.example.mascotas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements MascotaAdaptador.Estrella {

    ArrayList<Mascota> mascotas;
    ArrayList<Mascota> mascotas2 = new ArrayList<Mascota>();

    private RecyclerView listaMascotas;
    ImageButton btnEstrella;
    TextView tvContadorEstrella;

    int contadorEstrella = 0;

    ArrayList<Mascota> listaRecibida;

    MascotaAdaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);

        tvContadorEstrella = (TextView) findViewById(R.id.tvContadorEstrella);

        btnEstrella = (ImageButton) findViewById(R.id.btnEstrella);


        inicializarListaMascotas();
        inicializarAdaptador();


    }

    public void inicializarAdaptador() {
        if (Static.receptor2 == false) {
            adaptador = new MascotaAdaptador(mascotas, this, this);
            listaMascotas.setAdapter(adaptador);
            Static.receptor2 = true;
        } else {


            adaptador = new MascotaAdaptador(listaRecibida, this, this);
            listaMascotas.setAdapter(adaptador);
        }
    }

    public void inicializarListaMascotas() {
        if (Static.receptor == false) {
            mascotas = new ArrayList<Mascota>();
            agregarMascotas();
            Static.receptor = true;
        } else {

            listaRecibida = (ArrayList<Mascota>) getIntent().getSerializableExtra("listaRecibida");
        }
    }

    public void contadorEstrella(Boolean like) {

        String numLikes = Integer.toString(contadorEstrella);

        if (like == true) {

            contadorEstrella++;
            numLikes = Integer.toString(contadorEstrella);
            tvContadorEstrella.setText(numLikes);

        } else if (like == false) {

            contadorEstrella--;
            numLikes = Integer.toString(contadorEstrella);
            tvContadorEstrella.setText(numLikes);

        }
    }

    public void agregarMascota(Mascota mascota) {
        mascotas2.add(mascota);
    }

    public void eliminarMascota(Mascota mascota) {
        mascotas2.remove(mascota);
    }


    public void onClickEstrella(View v) {

        //para ordenar por el mas reciente clickeado
        Collections.reverse(mascotas2);

        Intent intent = new Intent(MainActivity.this, MascotasFavoritas.class);

        intent.putExtra("listaMascotas", mascotas2);

        if (contadorEstrella == 0) {
            intent.putExtra("verificadorMascotas", true);
        }

        startActivity(intent);


    }

    public void agregarMascotas() {
        mascotas.add(new Mascota(R.drawable.perro1, "Mico", 8));
        mascotas.add(new Mascota(R.drawable.gato1, "Chispa", 3));
        mascotas.add(new Mascota(R.drawable.perro2, "Rayo", 2));
        mascotas.add(new Mascota(R.drawable.caballo1, "Chiqui", 4));
        mascotas.add(new Mascota(R.drawable.perro3, "Plutón", 9));
        mascotas.add(new Mascota(R.drawable.caballo2, "Chico", 8));
        mascotas.add(new Mascota(R.drawable.perro4, "Luna", 2));
        mascotas.add(new Mascota(R.drawable.caballo3, "Lola", 1));
        mascotas.add(new Mascota(R.drawable.perro5, "Coco", 0));
        mascotas.add(new Mascota(R.drawable.burro1, "Linda", 6));
    }
}

