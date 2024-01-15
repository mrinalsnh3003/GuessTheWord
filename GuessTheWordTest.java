package com.example.guesstheword;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class GuessTheWordTest {

    @Test
    void processTheWordGame() {
        String simulatedUserInput = "b";
        InputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out);
        GuessTheWord.processTheWordGame("book", inputStream, ps);
        String output = out.toString();
    }

    @Test
    void getRegex() {
    }
}