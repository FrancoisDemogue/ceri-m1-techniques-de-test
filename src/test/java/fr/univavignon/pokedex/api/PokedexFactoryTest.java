package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PokedexFactoryTest {

    private IPokedexFactory pokedexFactory;
    private IPokemonMetadataProvider metadataProvider;
    private IPokemonFactory pokemonFactory;

    @BeforeEach
    public void setUp() {
        // Mocking des dépendances
        metadataProvider = Mockito.mock(IPokemonMetadataProvider.class);
        pokemonFactory = Mockito.mock(IPokemonFactory.class);

        // Initialisation de PokedexFactory
        pokedexFactory = new PokedexFactory();
    }

    @Test
    public void testCreatePokedex() {
        // Création du pokedex via la méthode factory
        IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        // Vérifier que le Pokedex n'est pas null
        assertNotNull(pokedex, "Le Pokedex créé ne doit pas être nul.");

        // Vérifier que l'objet retourné est bien une instance de Pokedex
        assertTrue(pokedex instanceof Pokedex, "Le Pokedex créé doit être une instance de Pokedex.");
    }
}
