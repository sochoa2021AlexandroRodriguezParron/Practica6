package net.iessochoa.alexandrorodriguez.practica6.ui.favoritos;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import net.iessochoa.alexandrorodriguez.practica6.databinding.FragmentFavoritosBinding;
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

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}