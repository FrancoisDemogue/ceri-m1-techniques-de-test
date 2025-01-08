package fr.univavignon.pokedex.imp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import fr.univavignon.pokedex.api.Pokemon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;

public class RocketPokemonFactoryTest {

    private RocketPokemonFactory factory;

    @BeforeEach
    public void setUp() {
        factory = new RocketPokemonFactory();
    }

    @Test
    public void testCreatePokemonWithValidIndex() {
        int index = 1; // Exemple : Bulbasaur
        int cp = 1500;
        int hp = 120;
        int dust = 2000;
        int candy = 20;

        Pokemon pokemon = factory.createPokemon(index, cp, hp, dust, candy);

        assertNotNull(pokemon);
        assertEquals("Bulbasaur", pokemon.getName());
        assertEquals(index, pokemon.getIndex());
        assertEquals(cp, pokemon.getCp());
        assertEquals(hp, pokemon.getHp());
        assertEquals(dust, pokemon.getDust());
        assertEquals(candy, pokemon.getCandy());
        assertTrue(pokemon.getAttack() >= 0 && pokemon.getAttack() <= 100);
        assertTrue(pokemon.getDefense() >= 0 && pokemon.getDefense() <= 100);
        assertTrue(pokemon.getStamina() >= 0 && pokemon.getStamina() <= 100);
        assertEquals(1, pokemon.getIv());
    }

    @Test
    public void testCreatePokemonWithInvalidIndex() {
        int index = -1;
        int cp = 1000;
        int hp = 100;
        int dust = 500;
        int candy = 10;

        Pokemon pokemon = factory.createPokemon(index, cp, hp, dust, candy);

        assertNotNull(pokemon);
        assertEquals("Ash's Pikachu", pokemon.getName());  // MISSINGNO est remplacé par Ash's Pikachu
        assertEquals(index, pokemon.getIndex());
        assertEquals(cp, pokemon.getCp());
        assertEquals(hp, pokemon.getHp());
        assertEquals(dust, pokemon.getDust());
        assertEquals(candy, pokemon.getCandy());
        assertEquals(1000, pokemon.getAttack());
        assertEquals(1000, pokemon.getDefense());
        assertEquals(1000, pokemon.getStamina());
        assertEquals(0, pokemon.getIv());
    }

    @Test
    public void testCreatePokemonWithRandomStats() {
        int index = 1; // Bulbasaur
        int cp = 2000;
        int hp = 150;
        int dust = 3000;
        int candy = 30;

        RocketPokemonFactory spyFactory = spy(factory);
        doReturn(50).when(spyFactory).generateRandomStat();
        
        Pokemon pokemon = spyFactory.createPokemon(index, cp, hp, dust, candy);

        assertNotNull(pokemon);
        assertEquals("Bulbasaur", pokemon.getName());
        assertEquals(index, pokemon.getIndex());
        assertEquals(cp, pokemon.getCp());
        assertEquals(hp, pokemon.getHp());
        assertEquals(dust, pokemon.getDust());
        assertEquals(candy, pokemon.getCandy());
        assertEquals(50, pokemon.getAttack()); // Valeur retournée par le mock
        assertEquals(50, pokemon.getDefense()); // Valeur retournée par le mock
        assertEquals(50, pokemon.getStamina()); // Valeur retournée par le mock
        assertEquals(1, pokemon.getIv());
    }

    @Test
    public void testCreatePokemonWithMissingNo() {
        int index = 0;
        int cp = 1000;
        int hp = 80;
        int dust = 1000;
        int candy = 5;

        Pokemon pokemon = factory.createPokemon(index, cp, hp, dust, candy);

        assertNotNull(pokemon);
        assertEquals("MISSINGNO", pokemon.getName());
        assertEquals(index, pokemon.getIndex());
        assertEquals(cp, pokemon.getCp());
        assertEquals(hp, pokemon.getHp());
        assertEquals(dust, pokemon.getDust());
        assertEquals(candy, pokemon.getCandy());
        assertTrue(pokemon.getAttack() >= 0 && pokemon.getAttack() <= 100);
        assertTrue(pokemon.getDefense() >= 0 && pokemon.getDefense() <= 100);
        assertTrue(pokemon.getStamina() >= 0 && pokemon.getStamina() <= 100);
        assertEquals(1, pokemon.getIv());
    }

    @Test
    public void testCreatePokemonWithNegativeIndex() {
        int index = -1;
        int cp = 1500;
        int hp = 120;
        int dust = 2000;
        int candy = 20;

        Pokemon pokemon = factory.createPokemon(index, cp, hp, dust, candy);

        assertNotNull(pokemon);
        assertEquals("Ash's Pikachu", pokemon.getName());
        assertEquals(index, pokemon.getIndex());
        assertEquals(cp, pokemon.getCp());
        assertEquals(hp, pokemon.getHp());
        assertEquals(dust, pokemon.getDust());
        assertEquals(candy, pokemon.getCandy());
        assertEquals(1000, pokemon.getAttack());
        assertEquals(1000, pokemon.getDefense());
        assertEquals(1000, pokemon.getStamina());
        assertEquals(0, pokemon.getIv());
    }
}
