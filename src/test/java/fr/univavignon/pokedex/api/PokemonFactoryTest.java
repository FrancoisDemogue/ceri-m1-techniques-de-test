package fr.univavignon.pokedex.api;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PokemonFactoryTest {

    private IPokemonFactory pokemonFactory;
    private IPokemonMetadataProvider metadataProvider;

    @BeforeEach
    public void setUp() {
        // Création du mock pour IPokemonMetadataProvider
        metadataProvider = Mockito.mock(IPokemonMetadataProvider.class);

        // Initialisation de PokemonFactory avec le mock
        pokemonFactory = new PokemonFactory(metadataProvider);
    }

    @Test
    public void testCreatePokemon() throws PokedexException {
        // Simulation des métadonnées pour le Pokémon avec l'index 0
        PokemonMetadata metadata = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        Mockito.when(metadataProvider.getPokemonMetadata(0)).thenReturn(metadata);

        // Appel de la méthode pour créer le Pokémon
        Pokemon pokemon = pokemonFactory.createPokemon(0, 500, 150, 3000, 10);

        // Vérification des valeurs
        assertNotNull(pokemon, "Le Pokémon créé ne doit pas être nul.");
        assertEquals(0, pokemon.getIndex(), "L'index du Pokémon doit être 0.");
        assertEquals("Bulbizarre", pokemon.getName(), "Le nom du Pokémon doit être Bulbizarre.");
        assertEquals(500, pokemon.getCp(), "Le CP du Pokémon doit être 500.");
        assertEquals(150, pokemon.getHp(), "Le HP du Pokémon doit être 150.");
        assertEquals(3000, pokemon.getDust(), "Le nombre de poussière du Pokémon doit être 3000.");
        assertEquals(10, pokemon.getCandy(), "Le nombre de bonbons du Pokémon doit être 10.");
        assertEquals(126, pokemon.getAttack(), "L'attaque du Pokémon doit être 126.");
        assertEquals(126, pokemon.getDefense(), "La défense du Pokémon doit être 126.");
        assertEquals(90, pokemon.getStamina(), "L'endurance du Pokémon doit être 90.");

        // Vérification que le calcul de l'IV est effectué correctement
        double expectedIv = (126 + 126 + 90) / 450.0 * 100;
        assertEquals(expectedIv, pokemon.getIv(), 0.01, "L'IV calculé du Pokémon est incorrect.");
    }

    @Test
    public void testCreatePokemonInvalidIndex() throws PokedexException {
        // Simulation de l'exception pour un index invalide
        Mockito.when(metadataProvider.getPokemonMetadata(Mockito.anyInt())).thenThrow(new PokedexException("Index invalide"));

        // Vérification que l'exception est bien levée pour un index invalide
        PokedexException exception = assertThrows(PokedexException.class, () -> {
            pokemonFactory.createPokemon(999, 500, 150, 3000, 10);
        });

        assertEquals("Pokemon avec l'id 999 n'existe pas.", exception.getMessage(), "Le message d'exception est incorrect.");
    }

    @Test
    public void testCreatePokemonIndexOutOfRangeLow() {
        // Vérification que l'exception est levée pour un index trop bas
        PokedexException exception = assertThrows(PokedexException.class, () -> {
            pokemonFactory.createPokemon(-1, 500, 150, 3000, 10);
        });

        assertEquals("Pokemon avec l'id -1 n'existe pas.", exception.getMessage(), "Le message d'exception est incorrect.");
    }

    @Test
    public void testCreatePokemonIndexOutOfRangeHigh() {
        // Vérification que l'exception est levée pour un index trop élevé
        PokedexException exception = assertThrows(PokedexException.class, () -> {
            pokemonFactory.createPokemon(152, 500, 150, 3000, 10);
        });

        assertEquals("Pokemon avec l'id 152 n'existe pas.", exception.getMessage(), "Le message d'exception est incorrect.");
    }
}
