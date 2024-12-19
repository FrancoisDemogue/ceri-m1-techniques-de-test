package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Pokedex implements IPokedex {

    // Liste pour stocker les Pokémon
    private final List<Pokemon> pokemons;
    private final IPokemonMetadataProvider metadataProvider;
    private final IPokemonFactory pokemonFactory;
	
    // Constructeur
    public Pokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        this.metadataProvider = metadataProvider;
        this.pokemonFactory = pokemonFactory;
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
        return pokemon.getIndex() - 1; // L'index est basé sur la position dans la liste
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

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) throws PokedexException {
        // Déléguer l'appel à l'instance de IPokemonFactory
        return pokemonFactory.createPokemon(index, cp, hp, dust, candy);
    }


    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        return metadataProvider.getPokemonMetadata(index);
    }
}
