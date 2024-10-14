package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class IPokedexTest {

    private IPokedex pokedex;
    private Pokemon bulbasaur;
    private Pokemon charmander;

    @Before
    public void setup () throws PokedexException {
        pokedex = Mockito.mock(IPokedex.class);

        bulbasaur = new Pokemon(1, "Bulbasaur", 613, 64, 4000, 4, 126, 126, 90, 56.0);
        charmander = new Pokemon(4, "Charmander", 700, 55, 5000, 5, 130, 110, 85, 60.0);


        List<Pokemon> pokemon_list = new ArrayList<>();
        pokemon_list.add(bulbasaur);
        pokemon_list.add(charmander);

        // MOCKs des méthodes
        Mockito.when(pokedex.size()).thenReturn(pokemon_list.size());
        Mockito.when(pokedex.addPokemon(bulbasaur)).thenReturn(0);
        Mockito.when(pokedex.addPokemon(charmander)).thenReturn(1);
        Mockito.when(pokedex.getPokemon(0)).thenReturn(bulbasaur);
        Mockito.when(pokedex.getPokemon(1)).thenReturn(charmander);
        Mockito.when(pokedex.getPokemons()).thenReturn(pokemon_list);
    }

    @org.junit.Test
    public void testSize() {
        assertEquals(2, pokedex.size());
    }

    @Test
    public void testAddPokemon() {
        assertEquals(0, pokedex.addPokemon(bulbasaur));
        assertEquals(1, pokedex.addPokemon(charmander));
    }

    @Test
    public void testGetPokemon() throws PokedexException {
        Pokemon recup = pokedex.getPokemon(0);
        assertEquals("Bulbasaur", recup.getName());
        assertEquals(613, recup.getCp());

        recup = pokedex.getPokemon(1);
        assertEquals("Charmander", recup.getName());
        assertEquals(700, recup.getCp());
    }

    @Test
    public void testGetPokemons() {
        Comparator<Pokemon> cpComparator = Comparator.comparingInt(Pokemon::getCp);
        List<Pokemon> recup = pokedex.getPokemons();
        assertEquals(2, recup.size());
        assertEquals("bulbasaur", recup.get(0).getName());
        assertEquals("Charmander", recup.get(1).getName());
    }
}