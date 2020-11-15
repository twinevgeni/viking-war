package viking.war.loaders;

import com.google.common.io.Resources;
import viking.war.objects.IGameMap;
import viking.war.serializer.MapStringSerializer;

import java.io.PrintStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Map loader from resources
 */
public class ResourceMapLoader implements IMapLoader {

    private String resName;
    private PrintStream out;

    public ResourceMapLoader(String resName, PrintStream out)
    {
        this.resName = resName;
        this.out = out;
    }

    /**
     * load map
     *
     * @return game map
     * @throws Exception if map load error
     */
    @Override
    public IGameMap Load() throws Exception {

        if (this.resName.equals(""))
        {
            throw new Exception("Карта не выбрана");
        }

        this.out.println("Выбрана карта: " + this.resName);

        URL url = Resources.getResource(this.resName);
        String resMapData = Resources.toString(url, StandardCharsets.UTF_8);

        return new MapStringSerializer().deserialize(resMapData);
    }
}
