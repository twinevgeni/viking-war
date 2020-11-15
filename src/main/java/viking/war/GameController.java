package viking.war;

import viking.war.objects.IGameMap;
import viking.war.objects.IIsland;

public class GameController {

    public static final int MAX_ITERATION_COUNT = 10000;

    private IGameMap gameMap;

    private int iteration;

    GameController()
    {
        this.iteration = -1;
    }

    void doIteration() throws Exception {
        iteration++;

        if (iteration >= MAX_ITERATION_COUNT)
        {
            throw new Exception("Прошло 10000 дней");
        }
    }

    void startGame()
    {
        try
        {
            while(true)
            {
                doIteration();
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
