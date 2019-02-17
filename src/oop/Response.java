package oop;

import util.Randomize;

import java.util.HashMap;
import java.util.Map;

public class Response {
    private boolean pig, cap,  red;
    private String replacedString;
    private String answer;
    private String[] hedges;
    private String[] qualifiers;
    private Map<String, String> replace;
    private Randomize random;
    private PigLatin pigLatin;

    //Constructor
    public Response() {
        hedges = new String[]{
                "Please tell me more ",
                "Many of my patients tell me the same thing ",
                "It is getting late, maybe we had better quit "
        };

        qualifiers = new String[]{
                "Why do you say that ",
                "You seem to think that ",
                "So, you are concerned that "
        };

        replace = new HashMap<>();
        replace.put("i", "you");
        replace.put("me", "you");
        replace.put("my", "your");
        replace.put("am", "are");

        random = new Randomize();
        pigLatin = new PigLatin();
    }

    //getter and setter
    public String getReplacedString(Map replace, String question) {
        replacedString = "";
        for (String retval : question.split(" ")) {
            if (replace.containsKey(retval)) {
                replacedString += replace.get(retval) + " ";
            } else {
                replacedString += retval + " ";
            }
        }
        return replacedString;
    }

    public String getAnswer(String question) {
        answer = getRandomResponse(question);
        if (pig) {
            answer = pigLatin.getPigString(answer);
        }
        if (cap) {
            answer = answer.toUpperCase();
        }
        if (red) {
            answer = "<font color = \"red\">" + answer + "</font>";
            //IColorCode.ANSI_RED_BACKGROUND + answer + IColorCode.ANSI_RESET;
        }
        return answer;
    }

    //methods
    public String getBooleanStatus() {
        return "Pig = " + isPig() + ",Capital = " + isCap() + ",Red = " + isRed();
    }

    public String getRandomResponse(String question) {
        int choice = random.getRandomNumber(1, 2);
        switch (choice) {
            case 1:
                int i = random.getRandomNumber(0, hedges.length);
                answer = hedges[i];
                break;
            case 2:
                int j = random.getRandomNumber(0, qualifiers.length);
                answer = qualifiers[j] + getReplacedString(replace, question);
                break;
            default:
                answer = "Invalid Choice";
                break;
        }
        return answer;
    }

    public boolean isOptions(String option) {
        switch (option) {
            case "pig":
                pig = !pig;
                break;
            case "caps":
                cap = !cap;
                break;
            case "red":
                red = !red;
                break;
            default:
                return false;
        }
        return true;
    }

    public boolean isPig() {
        return pig;
    }

    public void setPig(boolean pig) {
        this.pig = pig;
    }

    public boolean isCap() {
        return cap;
    }

    public void setCap(boolean cap) {
        this.cap = cap;
    }

    public boolean isRed() {
        return red;
    }

    public void setRed(boolean red) {
        this.red = red;
    }
}