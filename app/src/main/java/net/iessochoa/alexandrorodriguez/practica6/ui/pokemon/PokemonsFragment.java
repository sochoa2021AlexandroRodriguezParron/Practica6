package net.iessochoa.alexandrorodriguez.practica6.ui.pokemon;

import android.annotation.SuppressLint;
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

import net.iessochoa.alexandrorodriguez.practica6.R;
import net.iessochoa.alexandrorodriguez.practica6.databinding.FragmentPokemonsBinding;
import net.iessochoa.alexandrorodriguez.practica6.ui.adapters.PokemonAdapter;

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
        binding.rvPokemon.setBackgroundColor(getResources().getColor(R.color.verde_azul));



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}