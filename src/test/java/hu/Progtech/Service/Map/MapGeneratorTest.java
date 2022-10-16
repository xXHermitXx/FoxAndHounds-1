package hu.Progtech.Service.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import hu.Progtech.Model.MapVO;
import hu.Progtech.Service.RandomGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MapGeneratorTest {
    private static final int MAP_LENGTH = 4;

    @Mock
    private RandomGenerator numberGenerator;

    private static final char[][] EXPECTED_MAP = {
            {'*', 'H', '*', 'H'},
            {'*', '*', '*', '*'},
            {'*', '*', '*', '*'},
            {'F', '*', '*', '*'},
    };

    private static final String EXPECTED_FOX_START = "30";
    private static final String[] EXPECTED_HOUNDS = { "01", "03" };

    private static final MapVO EXPECTED = new MapVO(MAP_LENGTH, EXPECTED_MAP, EXPECTED_FOX_START, EXPECTED_HOUNDS);
    private static final int RANDOM_BOUND = MAP_LENGTH/2;

    private MapGenerator underTest;

    @BeforeEach
    public void setUp() {
        underTest = new MapGenerator(numberGenerator);
    }

    @Test
    public void testGenerateMapShouldReturnCorrectMap() {

        given(numberGenerator.makeRandomNumber(RANDOM_BOUND)).willReturn(0);

        MapVO result = underTest.generateMap(MAP_LENGTH);

        assertEquals(result, EXPECTED);
    }

}
