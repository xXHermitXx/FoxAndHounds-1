package hu.Progtech.Service.Game;

import hu.Progtech.Service.Command.CommandHandler;
import hu.Progtech.Service.Input.InputReader;
import hu.Progtech.Ui.PrintWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameStepPerformerTest {

    private static final String INPUT = "input";
    private static final String PROMPT = "\nEnter a command: ";
    @Mock
    private InputReader inputReader;

    @Mock
    private CommandHandler commandHandler;

    @Mock
    private PrintWrapper printWrapper;

    private GameStepPerformer underTest;

    @BeforeEach
    public void setUp() {
        underTest = new GameStepPerformer(inputReader, commandHandler, printWrapper);
    }

    @Test
    public void testPerformGameStepShouldReadUserInputAndCallCommandHandler() {
        // given
        given(inputReader.readInput()).willReturn(INPUT);

        // when
        underTest.performGameStep();

        // then
        verify(printWrapper).print(PROMPT);
        verify(inputReader).readInput();
        verify(commandHandler).handleCommand(INPUT);
    }
}