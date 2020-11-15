package viking.war.loaders;

import viking.war.objects.IGameMap;
import viking.war.serializer.MapStringSerializer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * Map loader from resources
 */
public class ResourceMapLoader implements IMapLoader {

    private String resName;
    private PrintStream out;

    public ResourceMapLoader(String resName, PrintStream out) {
        this.resName = resName;
        this.out = out;
    }

    /**
     * Reads given resource file as a string.
     *
     * @param fileName path to the resource file
     * @return the file's contents
     * @throws IOException if read fails for any reason
     */
    static String getResourceFileAsString(String fileName) throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(fileName)) {
            if (is == null) return null;
            try (InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                 BufferedReader reader = new BufferedReader(isr)) {

                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }
    }

    /**
     * load map
     *
     * @return game map
     * @throws Exception if map load error
     */
    @Override
    public IGameMap Load() throws Exception {

        if (this.resName.equals("")) {
            throw new Exception("Карта не выбрана");
        }

        this.out.println("Выбрана карта: " + this.resName);
        String resMapData = getResourceFileAsString(this.resName);

        return new MapStringSerializer().deserialize(resMapData);
    }
}
