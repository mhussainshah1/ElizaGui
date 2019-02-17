package util;

public class Randomize {
    private int randomNumber; //readable property

    public int getRandomNumber(int min, int max) {
        randomNumber = min + (int) (Math.random() * max);
        return randomNumber;
    }
}