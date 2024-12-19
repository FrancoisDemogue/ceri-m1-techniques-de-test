package fr.univavignon.pokedex.api;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class IPokedexFactoryTest {

    private IPokedexFactory pokedexFactory;
    private IPokemonMetadataProvider metadataProvider;
    private IPokemonFactory pokemonFactory;
    private IPokedex pokedex;

    @BeforeEach
    public void setUp() {
        pokedexFactory = Mockito.mock(IPokedexFactory.class);
        metadataProvider = Mockito.mock(IPokemonMetadataProvider.class);
        pokemonFactory = Mockito.mock(IPokemonFactory.class);
        pokedex = Mockito.mock(IPokedex.class);

        Mockito.when(pokedexFactory.createPokedex(metadataProvider, pokemonFactory)).thenReturn(pokedex);
    }

    @Test
    public void testCreatePokedex() {

        IPokedex createdPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
        assertEquals(pokedex, createdPokedex);
        Mockito.verify(pokedexFactory).createPokedex(metadataProvider, pokemonFactory);
    }


}
