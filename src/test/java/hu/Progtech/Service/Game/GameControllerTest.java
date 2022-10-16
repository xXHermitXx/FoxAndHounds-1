package hu.Progtech.Service.Game;

import hu.Progtech.Model.GameState;
import hu.Progtech.Model.MapVO;
import hu.Progtech.Ui.PrintWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class GameControllerTest {

    private static final MapVO MAP_VO = new MapVO(0, null, null, null);

    @Mock
    private GameState gameState;

    @Mock
    private GameStepPerformer gameStepPerformer;

    @Mock
    private PrintWrapper printWrapper;

    private GameController underTest;

    @BeforeEach
    public void setUp() {
        underTest = new GameController(gameState, gameStepPerformer, printWrapper);
    }

    @Test
    public void testPlayGameShouldStopLoopWhenGameEnds() {

        given(gameState.isGameOver()).willReturn(true);

        underTest.playGame();

        verifyNoMoreInteractions(gameStepPerformer);
    }

    @Test
    public void testGameShouldContinueLoopWhenGameStepPerformerThrowsException() throws RuntimeException {

        doThrow(RuntimeException.class).when(gameStepPerformer).performGameStep();

        assertThrows(RuntimeException.class, () -> gameStepPerformer.performGameStep());

        verify(gameStepPerformer).performGameStep();
    }

}
