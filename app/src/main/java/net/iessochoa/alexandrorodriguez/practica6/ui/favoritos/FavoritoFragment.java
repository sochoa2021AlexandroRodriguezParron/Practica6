package net.iessochoa.alexandrorodriguez.practica6.ui.favoritos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.iessochoa.alexandrorodriguez.practica6.databinding.FragmentFavoritosBinding;
import net.iessochoa.alexandrorodriguez.practica6.model.Pokemon;
import net.iessochoa.alexandrorodriguez.practica6.ui.adapters.PokemonAdapter;

public class FavoritoFragment extends Fragment {

    private FavoritoViewModel favoritoViewModel;
    private FragmentFavoritosBinding binding;
    private PokemonAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favoritoViewModel =
                new ViewModelProvider(this).get(FavoritoViewModel.class);

        binding = FragmentFavoritosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        adapter = new PokemonAdapter();
        binding.rvPokemons.setAdapter(adapter);
        binding.rvPokemons.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvPokemons.setBackgroundColor(Color.GREEN);

        favoritoViewModel.getAllPokemons().observe(getViewLifecycleOwner(), listaPokemon -> {
            adapter.setListaPokemon(listaPokemon);
        });

        adapter.setOnItemPokemonClickListener(new PokemonAdapter.OnItemPokemonClickListener() {
            @Override
            public void setOnItemPokemonClickListener(Pokemon pokemon) {
                Toast.makeText(getContext(), pokemon.getNombre(), Toast.LENGTH_SHORT).show();
            }
        });

        definirEventoSwiper();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void definirEventoSwiper() {
        //Creamos el Evento de Swiper
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT |
                        ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int
                            swipeDir) {
                        //realizamos un cast del viewHolder y obtenemos elpokemon a
                        // borrar
                        // PokemonListaPokemon
                        PokemonAdapter.PokemonViewHolder
                                vhPokemon=(PokemonAdapter.PokemonViewHolder) viewHolder;
                        Pokemon pokemon=vhPokemon.getPokemon();
                        borrarPokemon(pokemon, vhPokemon.getAdapterPosition());
                    }
                };
        //Creamos el objeto de ItemTouchHelper que se encargará del trabajo
        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(simpleItemTouchCallback);
        //lo asociamos a nuestro reciclerView
        itemTouchHelper.attachToRecyclerView(binding.rvPokemons);
    }

    private void borrarPokemon(Pokemon pokemon,int posicion) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(getContext());
        dialogo.setTitle("Aviso");
        dialogo.setMessage("Desea borrar a "+pokemon.getNombre());
        dialogo.setNegativeButton(android.R.string.cancel, new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapter.notifyItemChanged(posicion);//recuperamos la posición
                    }
                });
        dialogo.setPositiveButton(android.R.string.ok, new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Borramos
                        favoritoViewModel.delete(pokemon);
                    }
                });
        dialogo.show();
    }
}