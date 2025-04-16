package com.example.mascotas;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {

    public interface Estrella {
        public void contadorEstrella(Boolean like);

        public void agregarMascota(Mascota mascota);

        public void eliminarMascota(Mascota mascota);
    }

    private Estrella listener;
    ArrayList<Mascota> mascotas;
    Activity activity;

    Boolean favorita = false;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity, Estrella listener) {

        this.mascotas = mascotas;
        this.activity = activity;
        this.listener = listener;
    }

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity) {

        this.mascotas = mascotas;
        this.activity = activity;
        this.favorita = true;
    }

    //Inflar el layout y lo pasara al viewholder para que el obtenga los views
    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(v);
    }

    //asocia cada elemento de la lista con cada view
    @Override
    public void onBindViewHolder(@NonNull MascotaViewHolder holder, int position) { //el metodo se va ejecutando cada vez que se recorre la lista de mascotas

        Mascota mascota = mascotas.get(position);

        String numLikes = Integer.toString(mascota.getNumLikes());

        holder.imgFoto.setImageResource(mascota.getFoto());
        holder.tvNombreCV.setText(mascota.getNombre());
        holder.tvNumLikes.setText(numLikes);

        if (favorita == true) {

            holder.btnLike.setEnabled(false);

        }

        holder.btnLike.setOnClickListener(new View.OnClickListener() {
            Boolean like = true;

            @Override
            public void onClick(View view) {

                String numLikes;

                if (like == true) {
                    mascota.setNumLikes((mascota.getNumLikes() + 1));
                    numLikes = Integer.toString(mascota.getNumLikes());
                    /*Toast.makeText(activity,"Diste like a " + mascota.getNombre(),
                            Toast.LENGTH_SHORT).show();*/
                    holder.tvNumLikes.setText(numLikes);
                    like = false;
                    listener.contadorEstrella(true);
                    listener.agregarMascota(mascota);

                } else if (like == false) {
                    mascota.setNumLikes((mascota.getNumLikes() - 1));
                    numLikes = Integer.toString(mascota.getNumLikes());
                    /*Toast.makeText(activity,"Quitaste el like a " + mascota.getNombre(),
                            Toast.LENGTH_SHORT).show();*/
                    holder.tvNumLikes.setText(numLikes);
                    like = true;
                    listener.contadorEstrella(false);
                    listener.eliminarMascota(mascota);

                }


            }
        });
    }

    @Override
    public int getItemCount() { //Cantidad de elementos de mi lista contactos

        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFoto;
        private TextView tvNombreCV;
        private ImageButton btnLike;
        private TextView tvNumLikes;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvNombreCV = (TextView) itemView.findViewById(R.id.tvNombreCV);
            btnLike = (ImageButton) itemView.findViewById(R.id.btnLike);
            tvNumLikes = (TextView) itemView.findViewById(R.id.numLikes);

        }
    }
}

