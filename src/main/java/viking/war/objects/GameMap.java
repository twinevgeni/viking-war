package viking.war.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * GameMap
 */
public class GameMap extends GameObject implements IGameMap {

    private List<IIsland> islands;

    public GameMap()
    {
        super();
        this.islands = new ArrayList<>();
    }

    /**
     * get islands
     * @return islands list
     */
    @Override
    public List<IIsland> getIslandList() {
        return this.islands;
    }

    /**
     * get Island by ID
     *
     * @param id island id
     * @return Island
     */
    @Override
    public IIsland getIslandByID(int id) {
        IIsland foundIsland = islands.stream()
                .filter(island -> island.getID() == id)
                .findFirst().orElse(null);

        return foundIsland;
    }

    /**
     * Connect two islands by way
     * Direction in second island sets as opposite to [directionFrom]
     *
     * @param islandFrom    first island
     * @param directionFrom direction from first island
     * @param islandTo      second island
     *                      @throws Exception
     */
    @Override
    public void addWay(IIsland islandFrom, Direction directionFrom, IIsland islandTo) throws Exception {
        if (islandFrom.wayExists(directionFrom) || islandTo.wayExists(directionFrom.getOpposite()))
        {
            throw new Exception("way already exists");
        }

        islandFrom.setWay(directionFrom, islandTo);
        islandTo.setWay(directionFrom.getOpposite(), islandFrom);
    }

    /**
     * Remove way from islandFrom and connected island
     *
     * @param islandFrom    first island
     * @param directionFrom direction from first island
     */
    @Override
    public void removeWay(IIsland islandFrom, Direction directionFrom) {
        if (islandFrom.wayExists(directionFrom)) {
            IIsland islandTo = islandFrom.getWay(directionFrom);
            islandFrom.removeWay(directionFrom);
            islandTo.removeWay(directionFrom.getOpposite());
        }
    }

    /**
     * remove all ways and remove lighthouse
     * @param islandId island id
     */
    @Override
    public void destroyLighthouse(int islandId) {
        IIsland foundIsland = this.getIslandByID(islandId);
        if (foundIsland != null)
        {
            this.destroyLighthouse(foundIsland);
        }
    }

    /**
     * remove all ways and remove lighthouse
     * @param island island id
     */
    @Override
    public void destroyLighthouse(IIsland island) {

        for (Direction direction : Direction.values()) {
            removeWay(island, direction);
        }

        island.setLighthouse(false);
    }
}
