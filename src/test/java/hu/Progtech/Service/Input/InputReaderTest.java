package hu.Progtech.Service.Input;

import hu.Progtech.Service.Input.InputReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.BufferedReader;

@ExtendWith({MockitoExtension.class})
public class InputReaderTest {

    private static final String INPUT = "input";

    @Mock
    private BufferedReader bufferedReader;

    private InputReader underTest;

    @BeforeEach
    public void setUp() { underTest = new InputReader(bufferedReader); }

    @Test
    public void testReadInputShouldReturnInputReadByReader() throws IOException {

        given(bufferedReader.readLine()).willReturn(INPUT);

        String result = underTest.readInput();

        assertEquals(INPUT, result);
    }

    @Test
    public void testReadInputShouldReturnNullWhenReaderThrowsException() throws IOException {

        doThrow(IOException.class).when(bufferedReader).readLine();

        String result = underTest.readInput();

        assertNull(result);
    }
}
