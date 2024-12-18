package fr.univavignon.pokedex.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

class IPokedexTest {

    private IPokedex pokedex;
    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private ArrayList<Pokemon> liste = new ArrayList<>();


    @BeforeEach
    public void setup () throws PokedexException, IOException {
        pokedex = Mockito.mock(IPokedex.class);

        // recuperer la liste des pokemons dans un fichier avec un before each
        FileReader fileReader = new FileReader(Objects.requireNonNull(getClass().getClassLoader().getResource("pokemon_151.txt")).getPath());
        BufferedReader reader = new BufferedReader(fileReader);
        String line = reader.readLine();
        String[] elements = line.split(",");
        Pokemon elem = new Pokemon((Integer.parseInt(elements[0]))-1, elements[1], Integer.parseInt(elements[2]), Integer.parseInt(elements[3]), Integer.parseInt(elements[4]), Integer.parseInt(elements[5]), Integer.parseInt(elements[6]), Integer.parseInt(elements[7]), Integer.parseInt(elements[8]), Double.parseDouble(elements[9]));
        liste.add(elem);

        while (line != null){
            line = reader.readLine();
            if (line != null) {
                elements = line.split(",");
                elem = new Pokemon((Integer.parseInt(elements[0])) - 1, elements[1], Integer.parseInt(elements[2]), Integer.parseInt(elements[3]), Integer.parseInt(elements[4]), Integer.parseInt(elements[5]), Integer.parseInt(elements[6]), Integer.parseInt(elements[7]), Integer.parseInt(elements[8]), Double.parseDouble(elements[9]));
                liste.add(elem);
            }
        }

        pokemon1 = liste.get(0);
        pokemon2 = liste.get(133);
        List<Pokemon> pokemon_list = new ArrayList<>();
        pokemon_list.add(pokemon1);
        pokemon_list.add(pokemon2);

        Mockito.when(pokedex.size()).thenReturn(pokemon_list.size());
        Mockito.when(pokedex.addPokemon(pokemon1)).thenReturn(0);
        Mockito.when(pokedex.addPokemon(pokemon2)).thenReturn(133);
        Mockito.when(pokedex.getPokemon(0)).thenReturn(pokemon1);
        Mockito.when(pokedex.getPokemon(133)).thenReturn(pokemon2);
        Mockito.when(pokedex.getPokemons()).thenReturn(pokemon_list);
    }

    @Test
    void testSize() {
        Assertions.assertEquals(2, pokedex.size());
    }

    @Test
    public void testAddPokemon() {
        Assertions.assertEquals(0, pokedex.addPokemon(pokemon1));
        Assertions.assertEquals(133, pokedex.addPokemon(pokemon2));
    }

    @Test
    public void testGetPokemon() throws PokedexException {
        Pokemon recup = pokedex.getPokemon(0);
        Assertions.assertEquals("Bulbizarre", recup.getName());
        Assertions.assertEquals(1280, recup.getCp());

        recup = pokedex.getPokemon(133);
        Assertions.assertEquals("Aquali", recup.getName());
        Assertions.assertEquals(1643, recup.getCp());
    }

    @Test
    public void testGetPokemons() {
        Comparator<Pokemon> cpComparator = Comparator.comparingInt(Pokemon::getCp);
        List<Pokemon> recup = pokedex.getPokemons();
        Assertions.assertEquals(2, recup.size());
        Assertions.assertEquals(pokemon1.getName(), recup.get(0).getName());
        Assertions.assertEquals(pokemon2.getName(), recup.get(1).getName());
    }
}