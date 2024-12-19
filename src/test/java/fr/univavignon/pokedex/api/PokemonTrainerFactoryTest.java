package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class PokemonTrainerFactoryTest {

    private PokemonTrainerFactory pokemonTrainerFactory;
    private IPokedexFactory pokedexFactory;
    private IPokedex pokedex;

    @BeforeEach
    public void setUp() {
        // Initialisation des mocks
        pokedexFactory = Mockito.mock(IPokedexFactory.class);
        pokedex = Mockito.mock(IPokedex.class);

        // Simuler la création d'un Pokédex
        Mockito.when(pokedexFactory.createPokedex(null, null)).thenReturn(pokedex);

        // Création de l'instance de la classe à tester
        pokemonTrainerFactory = new PokemonTrainerFactory();
    }

    @Test
    public void testCreateTrainer() {
        // Création d'un Pokémon Trainer avec des paramètres
        String name = "Ash";
        Team team = Team.MYSTIC;

        // Appel de la méthode createTrainer
        PokemonTrainer trainer = pokemonTrainerFactory.createTrainer(name, team, pokedexFactory);

        // Vérification que le trainer est créé correctement
        assertNotNull(trainer, "Le Pokémon Trainer ne doit pas être null.");
        assertEquals(name, trainer.getName(), "Le nom du Pokémon Trainer doit être Ash.");
        assertEquals(team, trainer.getTeam(), "L'équipe du Pokémon Trainer doit être Team.MYSTIC.");
        assertEquals(pokedex, trainer.getPokedex(), "Le Pokédex du Pokémon Trainer doit être correctement initialisé.");
    }

    @Test
    public void testCreatePokedexCalled() {
        // Création d'un Pokémon Trainer
        String name = "Ash";
        Team team = Team.MYSTIC;

        // Appel de la méthode createTrainer
        pokemonTrainerFactory.createTrainer(name, team, pokedexFactory);

        // Vérification que la méthode createPokedex de IPokedexFactory a été appelée une fois
        Mockito.verify(pokedexFactory, Mockito.times(1)).createPokedex(null, null);
    }
}
