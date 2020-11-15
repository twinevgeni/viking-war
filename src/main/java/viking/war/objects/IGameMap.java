package viking.war.objects;

import java.util.List;

/**
 * IGameMap
 */
public interface IGameMap extends IGameObject {

    /**
     * get islands
     * @return islands list
     */
    List<IIsland> getIslandList();

    /**
     * get Island by ID
     * @param id island id
     * @return Island
     */
    IIsland getIslandByID(int id);

    /**
     * Add way for two islands
     * Direction in second island sets as opposite to [directionFrom]
     * @param islandFrom first island
     * @param directionFrom direction from first island
     * @param islandTo second island
     * @throws Exception if way exists on islandFrom or islandTo
     */
    void addWay(IIsland islandFrom, Direction directionFrom, IIsland islandTo) throws Exception;

    /**
     * Remove way from islandFrom and connected island
     * @param islandFrom first island
     * @param directionFrom direction from first island
     */
    void removeWay(IIsland islandFrom, Direction directionFrom);

    /**
     * remove all ways and remove lighthouse
     * @param islandId island id
     */
    void destroyLighthouse(int islandId);

    /**
     * remove all ways and remove lighthouse
     * @param island island id
     */
    void destroyLighthouse(IIsland island);
}
