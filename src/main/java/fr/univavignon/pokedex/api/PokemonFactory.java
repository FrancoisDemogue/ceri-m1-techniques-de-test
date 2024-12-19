package fr.univavignon.pokedex.api;

public class PokemonFactory implements IPokemonFactory {

    private IPokemonMetadataProvider metadataProvider;

    public PokemonFactory(IPokemonMetadataProvider metadataProvider) {
        this.metadataProvider = metadataProvider;
    }

    // Création du Pokémon désigné par l'index
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        if (index < 0 || index > 151) {
            throw new PokedexException("Pokemon avec l'id " + index + " n'existe pas.");
        }

        // Récupérer les métadonnées du Pokémon
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(index);

        return new Pokemon(index, metadata.getName(), metadata.getAttack(), metadata.getDefense(), metadata.getStamina(), 
                           cp, hp, dust, candy, calculateIv(metadata));
    }

    // Calcul de l'IV basé sur les statistiques du Pokémon
    private double calculateIv(PokemonMetadata metadata) {
        int totalStats = metadata.getAttack() + metadata.getDefense() + metadata.getStamina();
        int maxStats = 450;
        return (totalStats / (double) maxStats) * 100;
    }
}
