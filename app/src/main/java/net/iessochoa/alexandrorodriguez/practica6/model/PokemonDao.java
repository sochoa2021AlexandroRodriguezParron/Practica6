package net.iessochoa.alexandrorodriguez.practica6.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PokemonDao {

    /**
     * INSERTAR
     * @param pokemon
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Pokemon pokemon);

    /**
     * BORRAR POKEMON
     * @param pokemon
     */
    @Delete
    void deleteByPokemon(Pokemon pokemon);

    /**
     * BORRAR TODO
     */
    @Query("DELETE FROM "+Pokemon.TABLE_NAME)
    void deleteAll();

    /**
     * MODIFICAR POKEMON
     * @param pokemon
     */
    @Update
    void update(Pokemon pokemon);

    /**
     * OBTENER TODOS LOS POKEMONS
     * @return
     */
    @Query("SELECT * FROM "+Pokemon.TABLE_NAME+" ORDER BY "+Pokemon.NOMBRE)
    LiveData<List<Pokemon>> allPokemon();
}
