package viking.war.objects;

import java.util.Map;

public interface IIsland extends IGameObject {

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

    boolean wayExists(Direction direction);
    IIsland getWay(Direction direction);
    void setWay(Direction direction, IIsland island);
    void removeWay(Direction direction);

    void removeAllWays();
}
