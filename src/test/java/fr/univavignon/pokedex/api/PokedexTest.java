package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PokedexTest {

    private Pokedex pokedex;
    private IPokemonMetadataProvider metadataProvider;
    private IPokemonFactory pokemonFactory;

    @BeforeEach
    void setUp() throws PokedexException {
        // Mock des dépendances
        metadataProvider = Mockito.mock(IPokemonMetadataProvider.class);
        pokemonFactory = Mockito.mock(IPokemonFactory.class);

        // Initialisation du Pokedex
        pokedex = new Pokedex(metadataProvider, pokemonFactory);

        // Mock des comportements des dépendances
        Mockito.when(metadataProvider.getPokemonMetadata(0))
                .thenReturn(new PokemonMetadata(0, "Bulbizarre", 126, 126, 90));
        Mockito.when(metadataProvider.getPokemonMetadata(1))
                .thenReturn(new PokemonMetadata(1, "Herbizarre", 156, 158, 120));
        Mockito.when(pokemonFactory.createPokemon(0, 613, 64, 4000, 4))
                .thenReturn(new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0));
    }

    @Test
    void testSize() {
        // Vérifie que le pokedex est vide initialement
        assertEquals(0, pokedex.size());

        // Ajoute un Pokémon
        pokedex.addPokemon(new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0));

        // Vérifie la taille après ajout
        assertEquals(1, pokedex.size());
    }

    @Test
    void testAddPokemon() {
        // Ajoute un Pokémon et vérifie l'index retourné
        int index = pokedex.addPokemon(new Pokemon(1, "Herbizarre", 156, 158, 120, 613, 64, 4000, 4, 70.0));
        assertEquals(0, index); // L'index dans la liste commence à 0

        // Vérifie que le Pokémon est bien ajouté
        assertEquals(1, pokedex.size());
    }

    @Test
    void testGetPokemon() throws PokedexException {
        // Ajoute un Pokémon
        Pokemon bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0);
        pokedex.addPokemon(bulbizarre);

        // Récupère le Pokémon par son ID
        Pokemon fetchedPokemon = pokedex.getPokemon(0);
        assertEquals(bulbizarre, fetchedPokemon);

        // Vérifie qu'une exception est levée pour un ID invalide
        assertThrows(PokedexException.class, () -> pokedex.getPokemon(10));
    }

    @Test
    void testGetPokemons() {
        // Ajoute plusieurs Pokémons
        Pokemon bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0);
        Pokemon herbizarre = new Pokemon(1, "Herbizarre", 156, 158, 120, 613, 64, 4000, 4, 70.0);
        pokedex.addPokemon(bulbizarre);
        pokedex.addPokemon(herbizarre);

        // Vérifie que la liste des Pokémons contient bien les deux Pokémons ajoutés
        List<Pokemon> pokemons = pokedex.getPokemons();
        assertEquals(2, pokemons.size());
        assertTrue(pokemons.contains(bulbizarre));
        assertTrue(pokemons.contains(herbizarre));
    }

    @Test
    void testGetPokemonsSorted() {
        // Ajoute plusieurs Pokémons
        Pokemon bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0);
        Pokemon herbizarre = new Pokemon(1, "Herbizarre", 156, 158, 120, 613, 64, 4000, 4, 70.0);
        pokedex.addPokemon(herbizarre);
        pokedex.addPokemon(bulbizarre);

        // Trie les Pokémons par nom
        List<Pokemon> sortedByName = pokedex.getPokemons(Comparator.comparing(Pokemon::getName));
        assertEquals("Bulbizarre", sortedByName.get(0).getName());
        assertEquals("Herbizarre", sortedByName.get(1).getName());
    }

    @Test
    void testGetPokemonMetadata() throws PokedexException {
        // Récupère les métadonnées d'un Pokémon existant
        PokemonMetadata metadata = pokedex.getPokemonMetadata(0);
        assertEquals(0, metadata.getIndex());
        assertEquals("Bulbizarre", metadata.getName());
        assertEquals(126, metadata.getAttack());
        assertEquals(126, metadata.getDefense());
        assertEquals(90, metadata.getStamina());

        // Vérifie qu'une exception est levée pour un index invalide
        Mockito.when(metadataProvider.getPokemonMetadata(-1)).thenThrow(new PokedexException("Invalid index"));
        assertThrows(PokedexException.class, () -> pokedex.getPokemonMetadata(-1));
    }

    @Test
    void testCreatePokemon() throws PokedexException {
        // Création d'un Pokémon via la factory
        Pokemon createdPokemon = pokedex.createPokemon(0, 613, 64, 4000, 4);

        // Vérifie les propriétés du Pokémon créé
        assertEquals(0, createdPokemon.getIndex());
        assertEquals("Bulbizarre", createdPokemon.getName());
        assertEquals(613, createdPokemon.getCp());
        assertEquals(64, createdPokemon.getHp());
        assertEquals(4000, createdPokemon.getDust());
        assertEquals(4, createdPokemon.getCandy());
        assertEquals(56.0, createdPokemon.getIv(), 0.01);
    }
}
