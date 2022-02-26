package net.iessochoa.alexandrorodriguez.practica6.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListaPokemon {
    //Atributos
    //le cambiamos el nombre original del JSON
    @SerializedName("next")
    private String uriSiguientes;
    @SerializedName("results")
    private ArrayList<Pokemon> listaPokemon;
    //Contructores
    public ListaPokemon(String uriSiguientes, ArrayList<Pokemon> listaPokemon) {
        this.uriSiguientes = uriSiguientes;
        this.listaPokemon = listaPokemon;
    }
    public ListaPokemon() {
    }
    //GETTERS & SETTERS
    public String getUriSiguientes() {
        return uriSiguientes;
    }
    public void setUriSiguientes(String uriSiguientes) {
        this.uriSiguientes = uriSiguientes;
    }
    public ArrayList<Pokemon> getListaPokemon() {
        return listaPokemon;
    }
    public void setListaPokemon(ArrayList<Pokemon> listaPokemon) {
        this.listaPokemon = listaPokemon;
    }
}
