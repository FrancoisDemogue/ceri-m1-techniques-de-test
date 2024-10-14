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
    private Pokemon Bulbizarre;
    private Pokemon Aquali;

    @Before
    public void setup () throws PokedexException {
        pokedex = Mockito.mock(IPokedex.class);

        Bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0);
        Aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100.0);


        List<Pokemon> pokemon_list = new ArrayList<>();
        pokemon_list.add(Bulbizarre);
        pokemon_list.add(Aquali);

        // MOCKs des méthodes
        Mockito.when(pokedex.size()).thenReturn(pokemon_list.size());
        Mockito.when(pokedex.addPokemon(Bulbizarre)).thenReturn(0);
        Mockito.when(pokedex.addPokemon(Aquali)).thenReturn(133);
        Mockito.when(pokedex.getPokemon(0)).thenReturn(Bulbizarre);
        Mockito.when(pokedex.getPokemon(133)).thenReturn(Aquali);
        Mockito.when(pokedex.getPokemons()).thenReturn(pokemon_list);
    }

    @org.junit.Test
    public void testSize() {
        assertEquals(2, pokedex.size());
    }

    @Test
    public void testAddPokemon() {
        assertEquals(0, pokedex.addPokemon(Bulbizarre));
        assertEquals(133, pokedex.addPokemon(Aquali));
    }

    @Test
    public void testGetPokemon() throws PokedexException {
        Pokemon recup = pokedex.getPokemon(0);
        assertEquals("Bulbizarre", recup.getName());
        assertEquals(613, recup.getCp());

        recup = pokedex.getPokemon(133);
        assertEquals("Aquali", recup.getName());
        assertEquals(2729, recup.getCp());
    }

    @Test
    public void testGetPokemons() {
        Comparator<Pokemon> cpComparator = Comparator.comparingInt(Pokemon::getCp);
        List<Pokemon> recup = pokedex.getPokemons();
        assertEquals(2, recup.size());
        assertEquals("Bulbizarre", recup.get(0).getName());
        assertEquals("Aquali", recup.get(133).getName());
    }
}