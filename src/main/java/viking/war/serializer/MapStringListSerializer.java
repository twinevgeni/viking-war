package viking.war.serializer;

import viking.war.objects.IGameMap;

import java.util.List;

public class MapStringListSerializer
        implements IMapSerializer<List<String>> {

    /**
     * Serialize Map
     *
     * @param map
     * @return serialized map
     */
    @Override
    public List<String> serialize(IGameMap map) {
        return null;
    }

    /**
     * Deserialize Map
     *
     * @param strings
     * @return map
     */
    @Override
    public IGameMap deserialize(List<String> strings) {
        return null;
    }
}
