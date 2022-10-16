package hu.Progtech.Service.Command;

import hu.Progtech.Model.GameState;
import hu.Progtech.Model.MapVO;
import hu.Progtech.Service.RandomGenerator;
import hu.Progtech.Service.Validator.MoveValidator;
import hu.Progtech.Ui.MapPrinter;
import hu.Progtech.Ui.PrintWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Move {
    private final MoveValidator moveValidator;
    private final PrintWrapper printWrapper;
    private final MapPrinter mapPrinter;
    private final RandomGenerator randomGenerator;
    private static final Logger LOGGER = LoggerFactory.getLogger(Move.class);

    public Move(MoveValidator moveValidator, PrintWrapper printWrapper, MapPrinter mapPrinter, RandomGenerator randomGenerator) {
        this.moveValidator = moveValidator;
        this.printWrapper = printWrapper;
        this.mapPrinter = mapPrinter;
        this.randomGenerator = randomGenerator;
    }

    public void foxMove(GameState gameState, String moveTo) throws RuntimeException {
        LOGGER.info("Performing player move");
        MapVO currentMap = gameState.getCurrentMap();
        String foxLocation = gameState.getCurrentMap().getFoxStart();

        moveValidator.validateCoordinate(moveTo);
        moveValidator.validateMove(moveTo, foxLocation);
        moveValidator.validateFreeSpace(currentMap, moveTo);

        int moveToRow = Character.getNumericValue(moveTo.charAt(0));
        int moveToColumn = Character.getNumericValue(moveTo.charAt(1));
        int foxRow = Character.getNumericValue(foxLocation.charAt(0));
        int foxColumn = Character.getNumericValue(foxLocation.charAt(1));

        printWrapper.printLine(gameState.getPlayer().getName() + " moves fox to: " + moveToRow + moveToColumn);
        char[][] map = gameState.getCurrentMap().getMap();
        map[foxRow][foxColumn] = '*';
        map[moveToRow][moveToColumn] = 'F';
        String[] houndsStart = gameState.getCurrentMap().getHoundsStart();
        if (moveToRow == 0) {
            gameState.setGameOver(true);
        }
        gameState.setCurrentMap(new MapVO(currentMap.getMapLength(), map, moveTo, houndsStart));

        mapPrinter.printMap(gameState.getCurrentMap());
    }

    public void enemyMove(GameState gameState) {
        printWrapper.printLine("Enemy's turn: ");
        LOGGER.info("Performing enemy move");

        MapVO currentMap = gameState.getCurrentMap();
        String[] hounds = gameState.getCurrentMap().getHoundsStart();
        char[][] map = currentMap.getMap();

        int selectedHound = -1;
        int houndRowIndex = -1;
        int houndColumnIndex = -1;
        String houndRow = "";
        String houndColumn = "";


        while (!moveValidator.isValidCoordinate(houndRow + houndColumn) ||
                !moveValidator.isFree(currentMap, houndRow + houndColumn)) {
            selectedHound = randomGenerator.makeRandomNumber(currentMap.getMapLength() / 2);
            houndRowIndex = Character.getNumericValue(hounds[selectedHound].charAt(0));
            houndColumnIndex = Character.getNumericValue(hounds[selectedHound].charAt(1));
            houndRow = String.valueOf(houndRowIndex + 1);
            houndColumn = String.valueOf(houndColumnIndex + (randomGenerator.makeRandomBool() ? 1 : -1));
        }
        printWrapper.printLine("Enemy moves Hound #" + (selectedHound + 1) + " to " + houndRow + houndColumn);
        map[houndRowIndex][houndColumnIndex] = '*';
        map[Integer.parseInt(houndRow)][Integer.parseInt(houndColumn)] = 'H';

        hounds[selectedHound] = houndRow + houndColumn;
        String foxLocation = gameState.getCurrentMap().getFoxStart();

        gameState.setCurrentMap(new MapVO(currentMap.getMapLength(), map, foxLocation, hounds));
        mapPrinter.printMap(gameState.getCurrentMap());
    }
}