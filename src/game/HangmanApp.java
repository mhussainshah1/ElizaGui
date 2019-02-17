package game;

import java.util.Scanner;

public class HangmanApp extends Game {

    private String answer;

    //constructor
    public HangmanApp(String newline) {
        super(newline);
        answer = "";
    }

    public static void main(String[] args) {
        new HangmanApp("\n").process();
    }


    public String welcome() {
        String string = "Welcome, let's play hangman!"+newline
                + newline+"Here is the word I am thinking of: " + getBlanks() + newline
                + getChance();

        return string;
    }

    public String getGameStatus(String question) {
        if (isStart()) {
            setStart(false); // start run once
            setRun(true);
            setStop(false);
            return answer = welcome();//start need to run the hangmanApp
        }
        if (isRun()) {
            setGuess(question);
            answer = getResult();
        }
        if (isStop()) {
            setRun(false);
            answer += exit();
        }
        return answer;
    }

    public void process() {
        Scanner keyboard = new Scanner(System.in);
      //  System.out.println(getBooleanStatus());
        setStart(true);
        setStop(false);
        //System.out.println(getBooleanStatus());
        System.out.println(getGameStatus(""));
        //System.out.println(getBooleanStatus());
        while (isRun()) {
            String str = keyboard.nextLine();
            setGuess(str);
            System.out.println(getGameStatus(str));
        }
        //System.out.println(getBooleanStatus());
    }

    public String getBooleanStatus(){
        return ",Game = (start = " + isStart() + ", run = " + isRun() + ", stop = " +isStop() + ")" ;
    }

    public String exit() {
        return newline + "Thank you for playing! "+newline;
    }
}
