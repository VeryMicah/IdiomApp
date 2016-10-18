package micah.idiomapp;

import android.content.res.Resources;

import java.util.Random;

/**
 * Created by micah on 18/10/2016.
 */

public class SentenceScanner {

    public SentenceScanner() {
    }

    public void findTags(String sentence) {
        String trueSentence;

        //Split into an array of words
        String[] split = sentence.split("\\s+");

        //check each word in the array
        for (String word:split
             ) {
            //check each character in the word
            for (int i = 0 ; i<word.length() ; i++) {
                if (word.charAt(i) == '/') { // "/" indicates the END of a tag
                    String tag = word.substring(0, i);
                    String trueWord = word.substring(i+1);
                    String newWord = replaceWord(tag);
                }
            }
        }

    }

    private String replaceWord(String tag){
        Resources res = Resources.getSystem();

        switch(tag) {
            case "ns/": getRandom(res.getStringArray(R.array.nounSingular));
            case "vb/": getRandom(res.getStringArray(R.array.verbBase));
            case "aj/": getRandom(res.getStringArray(R.array.adjectives));
            case "lc/": getRandom(res.getStringArray(R.array.locations));
        }


        //get new word based on tag
        return " ";
    }

    private String getRandom(String[] list){
        Random rand = new Random();

        int randomNum = rand.nextInt(list.length);
        return list[randomNum];
    }
}
