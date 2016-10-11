package micah.idiomapp;

import android.content.res.Resources;

import java.util.ArrayList;

/**
 * Created by micah on 10/10/2016.
 * This class currently only checks for questions that are the entirety of their sentence.
 * Embedded questions such as "I was wondering, what is the time?" will not be detected.
 * http://web.stanford.edu/~laurik/publications/archive/questions.pdf
 *
 * I'm using sub-methods a lot to allow easy expansion in the future.
 */

public class QuestionChecker {
    private boolean isThisAQuestion = false;
    private ArrayList<String> indicators = new ArrayList<>();

    public QuestionChecker(){
        buildIndicatorList();
    }

    private void buildIndicatorList(){
        indicators.add("Can");
        indicators.add("Could");
        indicators.add("Do");
        indicators.add("Does");
        indicators.add("How");
        indicators.add("If");
        indicators.add("Is");
        indicators.add("May");
        indicators.add("Should");
        indicators.add("What");
        indicators.add("Which");
        indicators.add("Who");
        indicators.add("Why");
        indicators.add("Would");
    }

    public boolean ifQuestion(String statement) {

        String word = getFirstWord(statement);
        if (indicators.contains(word)) isThisAQuestion = true; //Note that .contains() checks using Object.equals()
            // TODO: 11/10/2016 further validation goes here
        return isThisAQuestion;
    }

    private String getFirstWord(String sentence){
        //todo remove any sentence punctuation
//        sentence = sentence.replaceAll("\\p{P}", "");
//        sentence = sentence.replaceAll("[\\,\\?\\;\\.\\:\\!]", "");

        //grab the first word
        int i = sentence.indexOf(' ');
        return sentence.substring(0, i);
    }

    @Override
    //mostly useful for making toast
    public String toString() {
        return Boolean.toString(isThisAQuestion);
    }
}
