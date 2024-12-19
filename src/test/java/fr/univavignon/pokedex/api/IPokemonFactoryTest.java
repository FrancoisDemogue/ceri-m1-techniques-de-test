package fr.univavignon.pokedex.api;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


/**
 * Factory interface for class that aims to create Pokemon instance.
 *
 * @author fv
 */

public class IPokemonFactoryTest {

    private IPokemonFactory pokemonFactory;

    @BeforeEach
    public void setUp() throws PokedexException {
        pokemonFactory = Mockito.mock(IPokemonFactory.class);
        Mockito.when(pokemonFactory.createPokemon(0, 613, 64, 4000, 4)).thenReturn(new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.0));
        Mockito.when(pokemonFactory.createPokemon(133, 2729, 202, 5000, 4)).thenReturn(new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100.0));
    }

    @Test
    public void testCreatePokemon() throws PokedexException {
        Pokemon bulbizarre = pokemonFactory.createPokemon(0, 613, 64, 4000, 4);
        assertEquals(0, bulbizarre.getIndex());
        assertEquals("Bulbizarre", bulbizarre.getName());
        assertEquals(613, bulbizarre.getCp());
        assertEquals(64, bulbizarre.getHp());
        assertEquals(4000, bulbizarre.getDust());
        assertEquals(4, bulbizarre.getCandy());
        assertEquals(126, bulbizarre.getAttack());
        assertEquals(126, bulbizarre.getDefense());
        assertEquals(90, bulbizarre.getStamina());
        assertEquals(56.0, bulbizarre.getIv(), 0.01);
    }

    @Test
    public void testCreateAutrePokemon() throws PokedexException {
        Pokemon aquali = pokemonFactory.createPokemon(133, 2729, 202, 5000, 4);
        assertEquals(133, aquali.getIndex());
        assertEquals("Aquali", aquali.getName());
        assertEquals(2729, aquali.getCp());
        assertEquals(202, aquali.getHp());
        assertEquals(5000, aquali.getDust());
        assertEquals(4, aquali.getCandy());
        assertEquals(186, aquali.getAttack());
        assertEquals(168, aquali.getDefense());
        assertEquals(260, aquali.getStamina());
        assertEquals(100.0, aquali.getIv(), 0.01);
    }

}
