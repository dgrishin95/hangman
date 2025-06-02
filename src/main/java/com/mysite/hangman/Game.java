package com.mysite.hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private static final List<String> WORDS;
    private static final List<String> STAGES;
    private static final int MAX_ATTEMPTS = 6;

    static {
        WORDS = Util.loadWords();
        STAGES = Util.loadStages();
    }

    public void start() {
        while (true) {
            System.out.println("Начать новую игру [Y] или выйти [N]?");
            Scanner scanner = new Scanner(System.in);
            char answer = Character.toLowerCase(scanner.next().charAt(0));

            if (answer == 'n')  {
                break;
            }

            if (answer == 'y') {
                newGame();
            }
        }
    }

    private void newGame() {
        Random random = new Random();
        String selectedWord = WORDS.get(random.nextInt(WORDS.size()));
        String guessedWord = selectedWord.replaceAll("[a-zA-Zа-яА-Я]", "*");

        int remainingAttempts = MAX_ATTEMPTS;

        List<Character> usingLetters = new ArrayList<>();
        char[] lettersOfGuessedWord = guessedWord.toCharArray();

        Scanner scanner = new Scanner(System.in);

        System.out.println(guessedWord);

        while (!selectedWord.equals(guessedWord)) {
            System.out.println("Введите букву:");
            char letter = Character.toLowerCase(scanner.next().charAt(0));

            if (selectedWord.contains(String.valueOf(letter))) {
                processCorrectGuess(selectedWord, letter, lettersOfGuessedWord);
            } else {
                if (usingLetters.contains(letter)) {
                    printUsingLetters(lettersOfGuessedWord, usingLetters);
                    continue;
                }

                remainingAttempts = calculateRemainingAttempts(remainingAttempts);

                if (remainingAttempts == 0) {
                    System.out.println("Вы проиграли!");
                    System.out.println("Загаданное слово: " + selectedWord);
                    System.out.println();
                    break;
                }

                System.out.println("Осталось попыток: " + remainingAttempts);
            }

            if (!usingLetters.contains(letter)) {
                usingLetters.add(letter);
            }

            guessedWord = new String(lettersOfGuessedWord);
            System.out.println(guessedWord);

            printUsingLetters(lettersOfGuessedWord, usingLetters);
        }
    }

    private void processCorrectGuess(String word, char letter, char[] lettersOfGuessedWord) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                lettersOfGuessedWord[i] = letter;
            }
        }
    }

    private void printUsingLetters(char[] lettersOfGuessedWord, List<Character> usingLetters) {
        System.out.print("Вы использовали буквы: ");
        usingLetters.forEach(usingLetter -> System.out.print(usingLetter + " "));

        System.out.println();
    }

    private int calculateRemainingAttempts(int remainingAttempts) {
        System.out.println("Вы ошиблись!");
        System.out.println(STAGES.get(STAGES.size() - remainingAttempts));

        return --remainingAttempts;
    }
}
