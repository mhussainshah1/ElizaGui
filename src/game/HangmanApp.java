package game;

import java.util.Scanner;

public class HangmanApp extends Game {

    //constructor
    public HangmanApp(String newline) {
        super(newline);
    }

    public static void main(String[] args) {
        new HangmanApp("\n").process();
    }

    public String welcome() {
        String string = "Welcome, let's play hangman!" + newline
                + newline + "Here is the word I am thinking of: " + getBlanks() + newline
                + getChance();
        return string;
    }

    public void process() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println(welcome());
        while (isRun()) {
            String str = keyboard.nextLine();
            setGuess(str);
            System.out.println(getResult());
        }
        System.out.println(exit());
    }

    public String getBooleanStatus() {
        return ",Game =  run = " + isRun();
    }

    public String exit() {
        return "Thank you for playing! " + newline;
    }
}