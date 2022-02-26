package net.iessochoa.alexandrorodriguez.practica6.network;



import net.iessochoa.alexandrorodriguez.practica6.model.ListaPokemon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface WebServicePokeApi {
    //llamada que devuelve el primer listado de pokemon
    @GET("pokemon")
    Call<ListaPokemon> getListaPokemon();
    //llamada que permite realizar una petición con un url completa. Utilizaremos esta opción para recibir los siguientes
    @GET
    Call<ListaPokemon> getListaPokemon(@Url String siguientes);
}
