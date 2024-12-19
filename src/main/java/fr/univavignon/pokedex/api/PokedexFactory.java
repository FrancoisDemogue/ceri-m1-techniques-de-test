package fr.univavignon.pokedex.api;


public class PokedexFactory implements IPokedexFactory {

    // Créer et retourner un Pokedex
    @Override
    // on défini les types avec les interface pour plus de flexibilité en cas de modification des implémentations
    public IPokedex createPokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        return new Pokedex(metadataProvider, pokemonFactory);
    }
}
