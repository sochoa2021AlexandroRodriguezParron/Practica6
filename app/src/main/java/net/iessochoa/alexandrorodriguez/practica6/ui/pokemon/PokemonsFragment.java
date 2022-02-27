package net.iessochoa.alexandrorodriguez.practica6.ui.pokemon;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.iessochoa.alexandrorodriguez.practica6.R;
import net.iessochoa.alexandrorodriguez.practica6.databinding.FragmentPokemonsBinding;
import net.iessochoa.alexandrorodriguez.practica6.model.Pokemon;
import net.iessochoa.alexandrorodriguez.practica6.ui.VerPokemonFragment;
import net.iessochoa.alexandrorodriguez.practica6.ui.adapters.PokemonAdapter;

import java.util.Date;

public class PokemonsFragment extends Fragment {

    private PokemonsViewModel pokemonsViewModel;
    private FragmentPokemonsBinding binding;
    private PokemonAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pokemonsViewModel =
                new ViewModelProvider(this).get(PokemonsViewModel.class);

        binding = FragmentPokemonsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        adapter = new PokemonAdapter();
        binding.rvPokemon.setAdapter(adapter);
        binding.rvPokemon.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvPokemon.setBackgroundColor(getResources().getColor(R.color.verde_claro));
        defineDetectarFinRecycler();

        adapter.setOnItemPokemonClickListener(new PokemonAdapter.OnItemPokemonClickListener() {
            @Override
            public void setOnItemPokemonClickListener(Pokemon pokemon) {
                //creamos bundle para pasar el pokemon al fragment ver_pokemon
                Bundle argumentosBundle=new Bundle();
                argumentosBundle.putParcelable(VerPokemonFragment.ARG_POKEMON,pokemon);
                //llamamos a la acción con el id del Navigation y el bundle
                NavHostFragment.findNavController(PokemonsFragment.this).navigate(R.id.action_nav_home_to_verPokemonFragment,argumentosBundle);
            }
        });

        binding.pbCarga.setVisibility(View.GONE);//oculta

        pokemonsViewModel.getAllPokemons().observe(getViewLifecycleOwner(), listaPokemon -> {
            adapter.setListaPokemon(listaPokemon);
            binding.pbCarga.setVisibility(View.INVISIBLE);
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
                        insertaDia(pokemon, vhPokemon.getAdapterPosition());
                    }
                };
        //Creamos el objeto de ItemTouchHelper que se encargará del trabajo
        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(simpleItemTouchCallback);
        //lo asociamos a nuestro reciclerView
        itemTouchHelper.attachToRecyclerView(binding.rvPokemon);
    }


    private void insertaDia(Pokemon pokemon, int posicion) {
        pokemon.setFechaCompra(new Date());
        pokemonsViewModel.insert(pokemon);
        adapter.notifyItemChanged(posicion); //para que lo vuelva a mostrar
    }

    /**
     * Detecta cuando llega al final
     */
    private void defineDetectarFinRecycler() {
        binding.rvPokemon.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //de esta forma sabemos si llega al final
                if ((!recyclerView.canScrollVertically(1)) && (newState ==
                        RecyclerView.SCROLL_STATE_IDLE)) {
                    Log.v("scroll", "fin recyclerview");
                    binding.pbCarga.setVisibility(View.VISIBLE);
                    //recuperamos los 20 siguientes pokemon eso hara que se
                    //active el observador
                    pokemonsViewModel.getNextPokemon();
                }
            }
        });
    }
}

