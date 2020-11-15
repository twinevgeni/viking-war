package viking.war.serializer;

import viking.war.objects.IGameMap;

import java.util.Arrays;

/**
 * string serializer
 */
public class MapStringSerializer implements IMapSerializer<String> {

    /**
     * Serialize Map
     *
     * @param map game map
     * @return serialized map
     */
    @Override
    public String serialize(IGameMap map) {
        return String.join(System.lineSeparator(), new MapStringListSerializer().serialize(map));
    }

    /**
     * Deserialize Map
     *
     * @param s string data
     * @return map
     */
    @Override
    public IGameMap deserialize(String s) {

        s = s.replace('\r', '\n').replace("\n\n", "\n");
        String[] strings = s.split("\n");

        return new MapStringListSerializer().deserialize(Arrays.asList(strings));
    }
}
