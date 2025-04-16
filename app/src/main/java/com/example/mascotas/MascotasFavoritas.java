package com.example.mascotas;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MascotasFavoritas extends AppCompatActivity {

    ArrayList<Mascota> listaRecibida;

    private RecyclerView listaMascotas;
    ImageButton btnEstrella;
    TextView tvContadorEstrella;
    TextView tvVerificadorMascotas;

    int contadorEstrella = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mascotas_favoritas);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        listaRecibida = (ArrayList<Mascota>) getIntent().getSerializableExtra("listaMascotas");

        Boolean verificadorMascotas = getIntent().getBooleanExtra("verificadorMascotas", false);

        tvVerificadorMascotas = (TextView) findViewById(R.id.tvVerificadorMascotas);

        if (verificadorMascotas == true) {

            tvVerificadorMascotas.setVisibility(View.VISIBLE);

        } else {
            tvVerificadorMascotas.setVisibility(View.INVISIBLE);
        }


        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);

        tvContadorEstrella = (TextView) findViewById(R.id.tvContadorEstrella);

        btnEstrella = (ImageButton) findViewById(R.id.btnEstrella);

        tvContadorEstrella.setVisibility(View.INVISIBLE);
        btnEstrella.setVisibility(View.INVISIBLE);

        inicializarAdaptador();


    }

    public void inicializarAdaptador() {
        MascotaAdaptador adaptador = new MascotaAdaptador(listaRecibida, this);
        listaMascotas.setAdapter(adaptador);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent resultIntent = new Intent(MascotasFavoritas.this, MainActivity.class);

            resultIntent.putExtra("listaRecibida", listaRecibida);

            setResult(RESULT_OK, resultIntent);

            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}