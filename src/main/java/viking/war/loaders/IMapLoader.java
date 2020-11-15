package viking.war.loaders;

import viking.war.objects.IGameMap;

/**
 * map loader interface
 */
public interface IMapLoader {

    /**
     * load map
     * @return game map
     * @throws Exception if map load error
     */
    IGameMap Load() throws Exception;
}
