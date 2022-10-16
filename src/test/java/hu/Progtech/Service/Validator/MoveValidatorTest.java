package hu.Progtech.Service.Validator;

import static org.junit.jupiter.api.Assertions.*;

import hu.Progtech.Model.GameState;
import hu.Progtech.Model.MapVO;
import hu.Progtech.Service.Exception.InvalidCoordinateException;
import hu.Progtech.Service.Exception.InvalidMoveException;
import hu.Progtech.Service.Exception.OccupiedSpaceException;
import hu.Progtech.Ui.PrintWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MoveValidatorTest {

    private static final String VAlID_COORDINATE = "00";
    private static final String INVALID_COORDINATE = "55";
    private static final String FOX_LOCATION = "12";
    private static final String VALID_DESTINATION_COORDINATE = "21";
    private static final String INVALID_DESTINATION_COORDINATE = "22";
    private static final String OCCUPIED_SPACE = "01";

    private static final char[][] MAP = {
            {'*', 'H', '*', 'H'},
            {'*', '*', 'F', '*'},
            {'*', '*', '*', '*'},
            {'*', '*', '*', '*'},
    };

    private static final MapVO MAP_VO = new MapVO(4, MAP, null, null);

    @Mock
    private GameState gameState;

    @Mock
    private PrintWrapper printWrapper;

    private MoveValidator underTest;

    @BeforeEach
    public void setUp() {
        gameState = new GameState(MAP_VO, false, null);
        underTest = new MoveValidator(gameState, printWrapper);
    }

    @Test
    public void testIsValidCoordinateShouldReturnTrueWhenCoordinateIsNotOutOfBounds() {

        boolean result = underTest.isValidCoordinate(VAlID_COORDINATE);

        assertTrue(result);
    }

    @Test
    public void testIsValidCoordinateShouldReturnFalseWhenCoordinateIsOutOfBounds() {

        boolean result = underTest.isValidCoordinate(INVALID_COORDINATE);

        assertFalse(result);
    }

    @Test
    public void testIsValidMoveShouldReturnTrueWhenDestinationCoordinateIsDiagonallyOneSpaceAway() {

        boolean result = underTest.isValidMove(VALID_DESTINATION_COORDINATE, FOX_LOCATION);

        assertTrue(result);
    }

    @Test
    public void testIsValidMoveShouldReturnFalseWhenDestinationCoordinateIsNotDiagonallyOneSpaceAway() {

        boolean result = underTest.isValidMove(INVALID_DESTINATION_COORDINATE, FOX_LOCATION);

        assertFalse(result);
    }

    @Test
    public void testIsFreeShouldReturnTrueWhenDestinationCoordinateSpaceIsFree() {

        boolean result = underTest.isFree(MAP_VO, VALID_DESTINATION_COORDINATE);

        assertTrue(result);
    }

    @Test
    public void testIsFreeShouldReturnFalseWhenDestinationCoordinateSpaceIsNotFree() {

        boolean result = underTest.isFree(MAP_VO, OCCUPIED_SPACE);

        assertFalse(result);
    }

    @Test
    public void testValidateCoordinateShouldThrowInvalidCoordinateExceptionIfGivenCoordinateIsInvalid() {

        assertThrows(InvalidCoordinateException.class, () -> underTest.validateCoordinate(INVALID_COORDINATE));
    }

    @Test
    public void testValidateMoveShouldThrowInvalidMoveExceptionIfGivenMoveIsInvalid() {

        assertThrows(InvalidMoveException.class, () -> underTest.validateMove(INVALID_DESTINATION_COORDINATE, FOX_LOCATION));
    }

    @Test
    public void testValidateFreeSpaceShouldThrowOccupiedSpaceExceptionIfGivenCoordinateSpaceIsNotFree() {

        assertThrows(OccupiedSpaceException.class, () -> underTest.validateFreeSpace(MAP_VO, OCCUPIED_SPACE));
    }
    @Test
    public void testValidateCoordinateShouldNotThrowInvalidCoordinateExceptionIfGivenCoordinateIsValid() {

        underTest.validateCoordinate(VAlID_COORDINATE);
    }

    @Test
    public void testValidateMoveShouldNotThrowInvalidMoveExceptionIfGivenMoveIsValid() {

        underTest.validateMove(VALID_DESTINATION_COORDINATE, FOX_LOCATION);
    }

    @Test
    public void testValidateFreeSpaceShouldNotThrowOccupiedSpaceExceptionIfGivenCoordinateSpaceIsFree() {

        underTest.validateFreeSpace(MAP_VO, VALID_DESTINATION_COORDINATE);

    }
}
