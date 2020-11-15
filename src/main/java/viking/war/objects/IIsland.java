package viking.war.objects;

import java.util.List;
import java.util.Map;

public interface IIsland extends IGameObject {

    /**
     * Available ways
     * @return ways
     */
    Map<Direction, IIsland> getWays();

    /**
     * @return true if lighthouse is exists | false if not
     */
    boolean isLighthouse();

    /**
     * set lighthouse existence
     * @param status lighthouse existence status
     */
    void setLighthouse(boolean status);

    /**
     * Check if way exists
     * @param direction direction
     * @return true if way exists | false if not
     */
    boolean wayExists(Direction direction);

    /**
     * get way
     * @param direction direction
     * @return next island
     */
    IIsland getWay(Direction direction);

    /**
     * add or replace new way to island
     * @param direction direction
     * @param island next island
     */
    void setWay(Direction direction, IIsland island);

    /**
     * remove way from island
     * @param direction direction
     */
    void removeWay(Direction direction);

    /**
     * remove all ways from island
     */
    void removeAllWays();

    /**
     * get vikings on island
     * @return vikings
     */
    List<IViking> getVikings();

    /**
     * add viking to island
     * @param viking viking
     */
    void addViking(IViking viking);

    /**
     * get viking by id
     * @param vikingId viking id
     * @return viking
     */
    IViking getVikingByID(int vikingId);

    /**
     * remove viking by id
     * @param vikingId viking id
     */
    void removeViking(int vikingId);

    /**
     * remove viking
     * @param viking viking
     */
    void removeViking(IViking viking);

    /**
     * remove all vikings
     */
    void removeAllVikings();
}
