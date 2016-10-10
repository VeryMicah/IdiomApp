package micah.idiomapp;

import android.content.res.Resources;

import java.util.ArrayList;

/**
 * Created by micah on 10/10/2016.
 * This class currently only checks for questions that are the entirety of their sentence.
 * Embedded questions such as "I was wondering, what is the time?" will not be detected.
 * http://web.stanford.edu/~laurik/publications/archive/questions.pdf
 */

public class QuestionChecker {
    private boolean isQuestion = false;
    private ArrayList<String> indicators = new ArrayList<>();

    public QuestionChecker(){
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

    public boolean isQuestion(String statement) {
        //I'm using sub-methods to allow easy expansion in the future.

        String word = getFirstWord(statement);
        if (indicators.contains(word)) isQuestion = true; //Note that .contains() checks using .equals()

        return isQuestion;
    }

    private String getFirstWord(String sentence){
        //todo remove any sentence puctuation
//        sentence = sentence.replaceAll("\\p{P}", "");
//        sentence = sentence.replaceAll("[\\,\\?\\;\\.\\:\\!]", "");

        //grab the first word
        int i = sentence.indexOf(' ');
        String word = sentence.substring(0, i);
        return word;
    }
}
