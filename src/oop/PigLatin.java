package oop;

import util.Randomize;

public class PigLatin {

    private String pigString;
    private Randomize random;

    public PigLatin(){
        pigString = "";
        random = new Randomize();
    }

    public String getPigString(String str) {
        pigString = "";
        for (String retval : str.split(" ")) {
            char v = retval.charAt(0);
            if (v == 'a' || v == 'e' || v == 'i' || v == 'o' || v == 'u') {
                int choice = random.getRandomNumber(1,2) ;
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
}
