package game;

import util.Randomize;

class Game {
    private static final int MAX_LIMIT = 6;
    private int gameCounter;
    private boolean start;
    private boolean run;
    private boolean stop;
    private String guess;
    private String word;
    protected String newline;
    private StringBuilder builder;
    private DatabaseWord databaseWord;
    private Randomize random;

    //constructor
    Game(String newline) {
        gameCounter = 1;
        start = false;
        run = false;
        stop = true;
        databaseWord = new DatabaseWord();
        builder = new StringBuilder();
        random = new Randomize();
        int index = random.getRandomNumber(0, databaseWord.getWords().size());
        word = databaseWord.getWordAt(index);
        this.newline = newline;
    }

    //getter and setters
    public void setGuess(String guess) {
        this.guess = guess;
    }

    public int getGameCounter() {
        return gameCounter;
    }

    //value between 1 and 6
    public void setGameCounter(int gameCounter) {
        if (gameCounter >= 1 && gameCounter <= MAX_LIMIT) {
            this.gameCounter = gameCounter;
        }
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    //methods
    public StringBuilder getBlanks() {
        for (int i = 0; i < word.length(); i++) {
            builder.append("-");
        }
        return builder;//.toString();
    }

    public String getResult() {
        int i = getGameCounter();
        String string = "";
        if (i <= MAX_LIMIT) {
            if (word.contains(guess) || guess.equals("$")) {
                string = playGame();
                if (builder.indexOf("-") == -1) {
                    string += wonGame();
                    stop = true;
                    return string;
                } else {
                    string +=  getChance();
                }
            } else {
                string = "You have guessed incorrectly " + i + "/" + MAX_LIMIT+ " times. " + newline +
                        getStatus();

                if (i == MAX_LIMIT) {
                    string += looseGame();
                    stop = true;
                } else {
                    string += getChance();
                }
                setGameCounter(++i);
            }
        }
        return string;
    }


    protected String playGame() {
        if (guess.equals("$")) {
            int index = builder.indexOf("-");
            guess = Character.toString(word.charAt(index));
        }

        int start = 0, end = 0;
        String string = "";
        while (true) {
            start = word.indexOf(guess, end); //inclusive
            if (start == -1) {
                break;
            }
            end = start + guess.length(); //exclusive
            builder.replace(start, end, guess);
            string = getStatus();
        }
        return string;
    }

    public String getStatus() {
        return "Your guess so far:" + builder + newline;
    }

    public String getChance(){
        return newline +"Enter Letter, word guess or $ for Lifeline: ";
    }

    public String looseGame() {
        return newline + "Sorry, you have no more guesses left. The word was " + word +newline;
    }

    public String wonGame() {
        return newline+ "You've won! The word was " + builder + newline;
    }


}