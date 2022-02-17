package net.iessochoa.alexandrorodriguez.practica6.ui.favoritos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import net.iessochoa.alexandrorodriguez.practica6.model.Pokemon;
import net.iessochoa.alexandrorodriguez.practica6.repository.PokemonRepository;

import java.util.List;

public class FavoritoViewModel extends AndroidViewModel {

    /**ATRIBUTOS**/
    private MutableLiveData<String> mText;
    private PokemonRepository mRepository;
    private LiveData<List<Pokemon>> mAllPokemons;

    /**CONSTRUCTOR**/
    public FavoritoViewModel(@NonNull Application application) {
        super(application);
        mRepository=PokemonRepository.getInstance(application);
        //Recuperamos el LiveData de todos los pokemons
        mAllPokemons=mRepository.getFavoritPokemons();
    }
    //OBTENER POKEMONS
    public LiveData<List<Pokemon>> getAllPokemons(){return mAllPokemons;}
    //INSERTAR POKEMON
    public void insert(Pokemon pokemon){mRepository.insert(pokemon);}
    //BORRAR POKEMON
    public void delete(Pokemon pokemon){mRepository.delete(pokemon);}

    //OBTENER TEXTO
    public LiveData<String> getText() {return mText;}

}