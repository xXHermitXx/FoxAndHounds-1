package hu.Progtech.Service.Game;

import hu.Progtech.Service.Command.CommandHandler;
import hu.Progtech.Service.Input.InputReader;
import hu.Progtech.Ui.PrintWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameStepPerformer {
    private final InputReader inputReader;
    private final CommandHandler commandHandler;
    private final PrintWrapper printWrapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(GameStepPerformer.class);

    public GameStepPerformer(InputReader inputReader, CommandHandler commandHandler, PrintWrapper printWrapper) {
        this.inputReader = inputReader;
        this.commandHandler = commandHandler;
        this.printWrapper = printWrapper;
    }

    public void performGameStep() {
        printWrapper.print("\nEnter a command: ");
        String input = inputReader.readInput();
        commandHandler.handleCommand(input);
    }
}