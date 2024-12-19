package fr.univavignon.pokedex.impl;

import fr.univavignon.pokedex.api.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Pokedex implements IPokedex {

    // Liste pour stocker les Pokémon
    private final List<Pokemon> pokemons;
	
    // Constructeur
    public Pokedex() {
        this.pokemons = new ArrayList<>();
    }

    // Nombre de Pokémon dans le Pokédex
    @Override
    public int size() {
        return this.pokemons.size();
    }

    // Ajouter un Pokémon et retourner son index unique
    @Override
    public int addPokemon(Pokemon pokemon) {
        this.pokemons.add(pokemon);
        return this.pokemons.size() - 1; // L'index est basé sur la position dans la liste
    }

    // Vérifie qu'un pokemon se trouve dans la liste en cherchant l'ID
    @Override
    public Pokemon getPokemon(int id) throws PokedexException {
        if ( id < 0 || id > 150 ) {
            throw new PokedexException("Pokemon avec l'id " + id + " n'existe pas.");
        }
        return this.pokemons.get(id);
    }

    // Retourne la liste des Pokémons
    @Override
    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    // Retourner la liste triée des Pokémons
    @Override
    public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
        List<Pokemon> sortedPokemons = new ArrayList<>(this.pokemons);
        sortedPokemons.sort(order); // Tri de la liste
        return sortedPokemons;
    }
}
