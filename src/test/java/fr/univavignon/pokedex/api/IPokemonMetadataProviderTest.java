package fr.univavignon.pokedex.api;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


public class IPokemonMetadataProviderTest {
    private IPokemonMetadataProvider pokemonMetadataProvider;

    @BeforeEach
    public void setUp() throws PokedexException {
        pokemonMetadataProvider = Mockito.mock(IPokemonMetadataProvider.class);
        Mockito.when(pokemonMetadataProvider.getPokemonMetadata(0)).thenReturn(new PokemonMetadata(0, "Bulbizarre", 126, 126, 90));
        Mockito.when(pokemonMetadataProvider.getPokemonMetadata(-1)).thenThrow(new PokedexException("Invalid index"));
    }

    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        PokemonMetadata metadata = pokemonMetadataProvider.getPokemonMetadata(0);

        assertEquals(0, metadata.getIndex());
        assertEquals("Bulbizarre", metadata.getName());
        assertEquals(126, metadata.getAttack());
        assertEquals(126, metadata.getDefense());
        assertEquals(90, metadata.getStamina());
    }
}
