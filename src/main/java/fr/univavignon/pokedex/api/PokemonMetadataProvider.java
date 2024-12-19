package fr.univavignon.pokedex.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    final Map<Integer, PokemonMetadata> metadataCache;

    public PokemonMetadataProvider() {
        metadataCache = new HashMap<>();
        loadMetadataFromFile();
    }

    //récupère les données dans le fichier concerné
    private void loadMetadataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(Objects.requireNonNull(getClass().getClassLoader().getResource("pokemon_151.txt")).getPath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int index = Integer.parseInt(parts[0]);
                String name = parts[1];
                int attack = Integer.parseInt(parts[2]);
                int defense = Integer.parseInt(parts[3]);
                int stamina = Integer.parseInt(parts[4]);
                PokemonMetadata metadata = new PokemonMetadata(index, name, attack, defense, stamina);
                this.metadataCache.put(index, metadata);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //obtiens les metadata d'un pokemon en fonction de son index
    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        PokemonMetadata metadata = metadataCache.get(index);
        if (metadata == null) {
            throw new PokedexException("Invalid index");
        }
        return metadata;
    }
}
