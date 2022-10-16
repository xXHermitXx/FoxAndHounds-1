package hu.Progtech;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import hu.Progtech.Model.GameState;
import hu.Progtech.Model.MapVO;
import hu.Progtech.Model.Player;
import hu.Progtech.Service.RandomGenerator;
import hu.Progtech.Service.Command.CommandHandler;
import hu.Progtech.Service.Command.Move;
import hu.Progtech.Service.Game.GameController;
import hu.Progtech.Service.Game.GameStepPerformer;
import hu.Progtech.Service.Input.InputReader;
import hu.Progtech.Service.Map.MapGenerator;
import hu.Progtech.Service.Validator.MoveValidator;
import hu.Progtech.Ui.MapPrinter;
import hu.Progtech.Ui.PrintWrapper;

public class Main {
    public static void main(String[] args) {
        RandomGenerator randomGenerator = new RandomGenerator();
        PrintWrapper printWrapper = new PrintWrapper();
        MapGenerator mapGenerator = new MapGenerator(randomGenerator);
        MapVO mapVO = mapGenerator.generateMap(8);
        Player player = new Player("Player");
        GameState gameState = new GameState(mapVO, false, player);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        InputReader inputReader = new InputReader(bufferedReader);
        MapPrinter mapPrinter = new MapPrinter(printWrapper);
        MoveValidator moveValidator = new MoveValidator(gameState, printWrapper);
        Move move = new Move(moveValidator, printWrapper, mapPrinter, randomGenerator);
        CommandHandler commandHandler = new CommandHandler(mapPrinter, move, gameState, printWrapper);
        GameStepPerformer gameStepPerformer = new GameStepPerformer(inputReader, commandHandler, printWrapper);
        GameController gameController = new GameController(gameState, gameStepPerformer, printWrapper);

        gameController.playGame();
    }
}
