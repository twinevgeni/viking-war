package viking.war.serializer;

import viking.war.objects.IGameMap;

/**
 * Map Serializer interface
 * @param <TData> type of serialized data
 */
public interface IMapSerializer<TData> {

    /**
     * Serialize Map
     * @param map
     * @return serialized map
     */
    TData serialize(IGameMap map);

    /**
     * Deserialize Map
     * @param data
     * @return map
     */
    IGameMap deserialize(TData data);
}
