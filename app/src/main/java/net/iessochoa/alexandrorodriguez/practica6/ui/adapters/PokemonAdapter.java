package net.iessochoa.alexandrorodriguez.practica6.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import net.iessochoa.alexandrorodriguez.practica6.R;
import net.iessochoa.alexandrorodriguez.practica6.model.Pokemon;
import net.iessochoa.alexandrorodriguez.practica6.utils.Utils;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>{

    private List<Pokemon> listaPokemon;
    private OnItemPokemonClickListener listenerclick;


    //cuando se modifique la base de datos, actualizamos el recyclerview
    public void setListaPokemon(List<Pokemon> pokemons){
        listaPokemon=pokemons;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PokemonAdapter.PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon,
                        parent, false);
        return new PokemonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonAdapter.PokemonViewHolder holder, int position) {
        if (listaPokemon != null) {
            //mostramos los valores en el cardview
            final Pokemon pokemon = listaPokemon.get(position);
            holder.tvNombre.setText(pokemon.getNombre().toUpperCase());
            //comprobamos si tenemos fecha por si no es de base de datos. Ya que utilizamos la misma clase
            holder.tvFechaCompra.setText(pokemon.getFechaCompraFormatoLocal());
            //utilizamos Glide para mostrar la imagen
            Utils.cargaImagen(holder.ivImagenPokemon,pokemon.getUrlImagen());
            //guardamos el pokemon actual
            holder.pokemon=pokemon;
        }
    }

    @Override
    public int getItemCount() {
        if (listaPokemon != null)
            return listaPokemon.size();
        else return 0;
    }

    public class PokemonViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNombre;
        private ImageView ivImagenPokemon;
        private TextView tvFechaCompra;
        //private ImageView ivBorrar;
        //guardamos el pokemon para simplificar
        private Pokemon pokemon;
        private CardView cv_fondo;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvFechaCompra = itemView.findViewById(R.id.tvFechaCompra);
            ivImagenPokemon = itemView.findViewById(R.id.ivImagenPokemon);
            cv_fondo = itemView.findViewById(R.id.cv_fondo);

            cv_fondo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerclick.setOnItemPokemonClickListener(listaPokemon.get(PokemonViewHolder.this.getBindingAdapterPosition()));
                }
            });
        }

        public Pokemon getPokemon() {
            return pokemon;
        }
    }



    //Interfaz para poder ejecutar el click del recyclerView
    public interface OnItemPokemonClickListener {
        void setOnItemPokemonClickListener(Pokemon pokemon);
    }
    public void setOnItemPokemonClickListener(OnItemPokemonClickListener listener) {
        this.listenerclick = listener;
    }
}
