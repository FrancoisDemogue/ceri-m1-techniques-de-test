package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class IPokemonTrainerFactoryTest {
    private IPokemonTrainerFactory pokemonTrainerFactory;
    private IPokedexFactory pokedexFactory;
    private IPokedex pokedex;
    private PokemonTrainer trainer;

    @Before
    public void setUp() {
        pokemonTrainerFactory = Mockito.mock(IPokemonTrainerFactory.class);
        pokedexFactory = Mockito.mock(IPokedexFactory.class);
        pokedex = Mockito.mock(IPokedex.class);

        Mockito.when(pokedexFactory.createPokedex(Mockito.any(), Mockito.any())).thenReturn(pokedex);

        trainer = new PokemonTrainer("Ash", Team.MYSTIC, pokedex);
        Mockito.when(pokemonTrainerFactory.createTrainer("Ash", Team.MYSTIC, pokedexFactory)).thenReturn(trainer);
    }

    @Test
    public void testCreateTrainer() {
        PokemonTrainer createdTrainer = pokemonTrainerFactory.createTrainer("Ash", Team.MYSTIC, pokedexFactory);

        assertEquals("Ash", createdTrainer.getName());
        assertEquals(Team.MYSTIC, createdTrainer.getTeam());
        assertEquals(pokedex, createdTrainer.getPokedex());
    }
}
