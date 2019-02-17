import java.util.*;
//import org.junit.contrib.java.lang.system.SystemOutRule;

/**
 * Now that your personal digital therapist, Eliza, is up and running, it's time to step it up with some Easter Eggs.
 * <p>
 * <p>
 * If you type "pig" Eliza should begin speaking in pig latin (Source: https://en.wikipedia.org/wiki/Pig_Latin)
 * <p>
 * Pig Latin Rules:
 * ----------------
 * If the first letter is a consonant, add "ay" to the end
 * If the first letter is a vowel, add "way" or "tay" to the end
 * Don't worry about the "multiple-letters-that-sounds-like one" rule (ex. str-, ch-, th-, etc.)
 * <p>
 * Additional Features:
 * <p>
 * If you type "caps" Eliza should begin speaking in all caps.
 * If you type "play game" Eliza should allow you to play a game, such as your choose your own adventure game.
 * If you type in "red" Eliza 's text should be displayed in red.
 * <p>
 * At the end of the chat, print out the chat history.
 */

public class Main {
    public static final String ANSI_RED_BACKGROUND = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    //public static final SystemOutRule log = new SystemOutRule().enableLog();
    public static List<String> history = new ArrayList<String>();

    public static void main(String[] args) {
        String[] hedges = {
                "Please tell me more ",
                "Many of my patients tell me the same thing ",
                "It is getting late, maybe we had better quit "
        };

        String[] qualifiers = {
                "Why do you say that ",
                "You seem to think that ",
                "So, you are concerned that "
        };

        Map<String, String> replace = new HashMap<>();
        replace.put("i", "you");
        replace.put("me", "you");
        replace.put("my", "your");
        replace.put("am", "are");

        Scanner keyboard = new Scanner(System.in);
        String question, answer = "";
        boolean isPig = false, isCap = false, isPlayGame = false, isRed = false;

        print("Good day. What is your problem? ");

        while (true) {
            print("Enter your response here or Q to quit: ");
            question = keyboard.nextLine().toLowerCase();
            input(question);

            if (question.equalsIgnoreCase("pig")) {
                isPig = onOff(isPig);
                continue;
            }

            if (question.equalsIgnoreCase("caps")) {
                isCap = onOff(isCap);
                continue;
            }

            if (question.equalsIgnoreCase("play game")) {
                playGame();
                continue;
            }
            if (question.equalsIgnoreCase("red")) {
                isRed = onOff(isRed);
                continue;
            }

            if (question.equalsIgnoreCase("I am feeling great") | question.equals("q")) {
                //print out history
                //System.out.println(log.getLog());
                for(String str: history){
                    System.out.print(str);
                }
                break;
            } else {
                int choice = 1 + (int) (Math.random() * 2);
                switch (choice) {
                    case 1:
                        int i = (int) (Math.random() * hedges.length);
                        answer = hedges[i];
                        break;
                    case 2:
                        int j = (int) (Math.random() * qualifiers.length);
                        answer = qualifiers[j] + getReplacedString(replace, question);
                        break;
                    default:
                        println("Invalid Choice");
                }
                if (isPig) {
                    answer = getPigString(answer);
                }
                if (isCap) {
                    answer = answer.toUpperCase();
                }
                if(isRed){
                    answer = ANSI_RED_BACKGROUND + answer + ANSI_RESET;
                }
                println(answer);
            }
        }
    }

    public static boolean onOff(boolean check){
        return !check;
    }

    public static String getReplacedString(Map replace, String question) {
        String replacedString = "";
        for (String retval : question.split(" ")) {
            if (replace.containsKey(retval)) {
                replacedString += replace.get(retval) + " ";
            } else {
                replacedString += retval + " ";
            }
        }
        return replacedString;
    }

    public static String getPigString(String str) {
        String pigString = "";
        for (String retval : str.split(" ")) {
            char v = retval.charAt(0);
            if (v == 'a' || v == 'e' || v == 'i' || v == 'o' || v == 'u') {
                int choice = 1+ (int) (Math.random() * 2) ;
                switch (choice){
                    case 1:
                        pigString += retval + "way ";
                        break;
                    case 2:
                        pigString += retval + "tay ";
                        break;
                }
            } else {
                pigString += retval + "ay ";
            }
        }
        return pigString;
    }

    //play games

    public static void playGame(){
        while (true) {
            Scanner keyboard = new Scanner(System.in);
            print("Enter 1st number: ");
            String a = keyboard.next();
            input(a);
            int n = getNumber(a);

            print("Enter 2nd number: ");
            String b = keyboard.next();
            input(b);
            int m = getNumber(b);

            if (m == 0 && n == 0) {
                break;
            } else {
                int sum = m + n;
                if (sum < 21) {
                    println(Integer.toString(sum));
                } else {
                    println(sum + "*");
                }
            }
        }
    }

    public static int getNumber(String str) {
        if (str.equalsIgnoreCase("J") || str.equalsIgnoreCase("Q") || str.equalsIgnoreCase("K")) {
            return 10;
        } else if (str.equalsIgnoreCase("A")) {
            Scanner keyboard = new Scanner(System.in);
            print("Enter 1 or 11 number: ");
            int a = keyboard.nextInt();
            input(Integer.toString(a));
            return a;
        }
        return Integer.parseInt(str);
    }

    public static void print(String str){
        System.out.print(str);
        history.add(str);
    }

    public static void println(String str){
        System.out.println(str);
        history.add(str + "\n");
    }

    public static void input(String str){
        history.add(str+ "\n");
    }
}