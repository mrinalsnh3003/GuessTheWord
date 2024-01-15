package com.example.guesstheword;

import java.io.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GuessTheWord {
    public static void main(String[] args) {
        String predefinedList[] = {"book", "shelf", "cardboard", "promo", "pos"};
        Random random = new Random();
        String wordToGuess = predefinedList[random.nextInt(predefinedList.length)];
        processTheWordGame(wordToGuess, System.in, System.out);
    }

    private static String getRegex(Set<String> userInputs) {
        String maskedText = "";
        for (String s : userInputs) {
            maskedText = maskedText + s;
        }
        String regex = "[^" + maskedText + "]";
        return regex;
    }

    public static void processTheWordGame(String wordToGuess, InputStream in, PrintStream out) {
        int totalLife = 4;
        int count = 1;
        String maskedText = wordToGuess.replaceAll(".", "*");
        Set<String> allCharacters = new HashSet<>();
        while (totalLife > 0) {
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(in));
                out.println("Guess the word:" + maskedText);
                String userInput = reader.readLine();
                if (userInput == null) {
                    out.println("Input should not be null. Exiting the game...");
                    break;
                } else if (userInput.length() > 1) {
                    out.println("Input should not be more than one letter. Please try again...");
                } else {
                    if (allCharacters.add(userInput)) {
                        if (wordToGuess.contains(userInput)) {
                            maskedText = wordToGuess.replaceAll(getRegex(allCharacters), "*");
                            if (maskedText.equalsIgnoreCase(wordToGuess)) {
                                out.println("You have won the game. The word was  ->" + wordToGuess);
                                break;
                            }
                            out.println("Correct");
                        } else {
                            totalLife--;
                            out.println("Incorrect " + count + " life lost. " + totalLife + " remaining. The current word is " + maskedText);
                            count++;
                        }
                    } else {
                        out.println("You have already tried this letter!");
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        if (totalLife == 0)
            out.println("Sorry you lost the game,all lives consumed!");

    }


}
