package hu.Progtech.Service.Validator;

import java.util.regex.Pattern;
import hu.Progtech.Model.GameState;
import hu.Progtech.Model.MapVO;
import hu.Progtech.Service.Exception.InvalidCoordinateException;
import hu.Progtech.Service.Exception.InvalidMoveException;
import hu.Progtech.Service.Exception.OccupiedSpaceException;
import hu.Progtech.Ui.PrintWrapper;

public class MoveValidator {
    private final GameState gameState;
    private final PrintWrapper printWrapper;

    public MoveValidator(GameState gameState, PrintWrapper printWrapper) {
        this.gameState = gameState;
        this.printWrapper = printWrapper;
    }

    public void validateCoordinate(String coordinate) {
        if (!isValidCoordinate(coordinate)) {
            printWrapper.printLine("Out of map");
            throw new InvalidCoordinateException("Out of map");
        }
    }

    public void validateMove(String coordinate, String foxLocation) {
        if (!isValidMove(coordinate, foxLocation)) {
            printWrapper.printLine("Invalid move");
            throw new InvalidMoveException("Invalid move");
        }
    }

    public void validateFreeSpace(MapVO currentMap, String coordinate) {
        if (!isFree(currentMap, coordinate)) {
            printWrapper.printLine("Space is occupied");
            throw new OccupiedSpaceException("Space is occupied");
        }
    }

    public Boolean isValidCoordinate(String coordinate) {
        int mapBound = gameState.getCurrentMap().getMapLength() - 1;
        String coordinateRegex = "^[0-" + mapBound + "][0-" + mapBound + "]$";
        return Pattern.matches(coordinateRegex, coordinate);
    }

    public Boolean isValidMove(String coordinate, String foxLocation) {
        int moveToRow = Character.getNumericValue(coordinate.charAt(0));
        int moveToColumn = Character.getNumericValue(coordinate.charAt(1));
        int foxRow = Character.getNumericValue(foxLocation.charAt(0));
        int foxColumn = Character.getNumericValue(foxLocation.charAt(1));
        return ((moveToRow == foxRow + 1 || moveToRow == foxRow - 1) &&
                (moveToColumn == foxColumn + 1 || moveToColumn == foxColumn - 1));
    }

    public Boolean isFree(MapVO mapVO, String coordinate) {
        boolean result = true;
        int row = Character.getNumericValue(coordinate.charAt(0));
        int column = Character.getNumericValue(coordinate.charAt(1));
        if (mapVO.getMap()[row][column] != '*') {
            result = false;
        }
        return result;
    }
}