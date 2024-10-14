package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

/**
 * Factory interface for class that aims to create Pokemon instance.
 *
 * @author fv
 */

public class IPokemonFactoryTest {

    private IPokemonFactory pokemonFactory;

    @Before
    public void setUp() {
        pokemonFactory = Mockito.mock(IPokemonFactory.class);
        Mockito.when(pokemonFactory.createPokemon(1, 613, 64, 4000, 4)).thenReturn(new Pokemon(1, "Bulbasaur", 613, 64, 4000, 4, 126, 126, 90, 56.0));
        Mockito.when(pokemonFactory.createPokemon(4, 700, 55, 5000, 5)).thenReturn(new Pokemon(4, "Charmander", 700, 55, 5000, 5, 130, 110, 85, 60.0));
    }

    @Test
    public void testCreatePokemon() {
        Pokemon bulbasaur = pokemonFactory.createPokemon(1, 613, 64, 4000, 4);
        assertEquals(1, bulbasaur.getIndex());
        assertEquals("Bulbasaur", bulbasaur.getName());
        assertEquals(613, bulbasaur.getCp());
        assertEquals(64, bulbasaur.getHp());
        assertEquals(4000, bulbasaur.getDust());
        assertEquals(4, bulbasaur.getCandy());
        assertEquals(126, bulbasaur.getAttack());
        assertEquals(126, bulbasaur.getDefense());
        assertEquals(90, bulbasaur.getStamina());
        assertEquals(56.0, bulbasaur.getIv(), 0.01);
    }

    @Test
    public void testCreateAutrePokemon() {
        Pokemon charmander = pokemonFactory.createPokemon(4, 700, 55, 5000, 5);
        assertEquals(4, charmander.getIndex());
        assertEquals("Charmander", charmander.getName());
        assertEquals(700, charmander.getCp());
        assertEquals(55, charmander.getHp());
        assertEquals(5000, charmander.getDust());
        assertEquals(5, charmander.getCandy());
        assertEquals(130, charmander.getAttack());
        assertEquals(110, charmander.getDefense());
        assertEquals(85, charmander.getStamina());
        assertEquals(60.0, charmander.getIv(), 0.01);
    }

}
