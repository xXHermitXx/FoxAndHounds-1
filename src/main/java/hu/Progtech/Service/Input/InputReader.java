package hu.Progtech.Service.Input;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(InputReader.class);

    private final BufferedReader reader;

    public InputReader(BufferedReader reader) {
        this.reader = reader;
    }

    public String readInput()  {
        String input = null;

        try {
            input = reader.readLine();
        } catch (IOException e) {
            LOGGER.error("Exception occured while reading input");
        }
        return input;
    }
}