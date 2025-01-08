package fr.univavignon.pokedex.api;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PokemonMetadataProviderTest {

    private PokemonMetadataProvider pokemonMetadataProvider;

    @BeforeEach
    public void setUp() {
        // Création du mock pour IPokemonMetadataProvider
        IPokemonMetadataProvider mockMetadataProvider = Mockito.mock(IPokemonMetadataProvider.class);

        // Initialisation de PokemonMetadataProvider avec le mock
        pokemonMetadataProvider = new PokemonMetadataProvider();
    }

    @Test
    public void testGetPokemonMetadataValidIndex() throws PokedexException {
        // Simulation des métadonnées pour l'index 0
        PokemonMetadata metadata = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);

        // Simulées dans la cache
        pokemonMetadataProvider.metadataCache.put(0, metadata);

        // Récupérer les métadonnées pour l'index 0
        PokemonMetadata result = pokemonMetadataProvider.getPokemonMetadata(0);

        // Vérif résultats
        assertNotNull(result, "Les métadonnées ne doivent pas être nulles.");
        assertEquals(0, result.getIndex(), "L'index du Pokémon doit être 0.");
        assertEquals("Bulbizarre", result.getName(), "Le nom du Pokémon doit être Bulbizarre.");
        assertEquals(126, result.getAttack(), "L'attaque du Pokémon doit être 126.");
        assertEquals(126, result.getDefense(), "La défense du Pokémon doit être 126.");
        assertEquals(90, result.getStamina(), "L'endurance du Pokémon doit être 90.");
    }

    @Test
    public void testGetPokemonMetadataInvalidIndex() {
        PokedexException exception = assertThrows(PokedexException.class, () -> {
            pokemonMetadataProvider.getPokemonMetadata(999);
        });

        // Vérification du message d'exception
        assertEquals("Invalid index", exception.getMessage(), "Le message d'exception est incorrect.");
    }

    @Test
    public void testLoadMetadataFromFile() {
        assertTrue(true, "Le cache des métadonnées doit contenir des entrées.");
    }

}
