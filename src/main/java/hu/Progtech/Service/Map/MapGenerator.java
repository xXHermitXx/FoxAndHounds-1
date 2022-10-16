package hu.Progtech.Service.Map;

import hu.Progtech.Model.MapVO;
import hu.Progtech.Service.RandomGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapGenerator {

    private final RandomGenerator randomGenerator;

    private static final Logger LOGGER = LoggerFactory.getLogger(MapGenerator.class);

    public MapGenerator(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    public MapVO generateMap(int mapLength) {

        LOGGER.info("Generating map");
        char[][] map = new char[mapLength][mapLength];
        for (int i = 0; i < mapLength; i++) {
            for (int j = 0; j < mapLength; j++) {
                map[i][j] = '*';
            }
        }

        int foxIndex = randomGenerator.makeRandomNumber(mapLength / 2);
        map[mapLength - 1][foxIndex * 2] = 'F';

        String[] houndsStart = new String[mapLength / 2];

        for (int i = 0; i < mapLength / 2; i++) {
            houndsStart[i] = "0" + (i * 2 + 1);
        }

        String foxStart = mapLength - 1 + String.valueOf(foxIndex * 2);
        for (int i = 0; i < mapLength / 2; i++) {
            map[0][i * 2 + 1] = 'H';
        }
        return new MapVO(mapLength, map, foxStart, houndsStart);
    }
}