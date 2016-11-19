package micah.idiomapp;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by micah on 18/10/2016.
 */

public class SentenceScanner {

    public SentenceScanner() {
    }

    //Finds tagged words within a sentence.
    //Replaces any tagged words with a different word of the same type.
    public String replaceWords(String sentence) {
        ArrayList<String> wordList = new ArrayList<>();

        //Break sentence down into words
        String[] demWords = sentence.split("\\s+");

        //add each word to the arraylist of words
        for (String word : demWords){
            wordList.add(word);
        }

        //check each word in the array
        for (String word : wordList) {
            //if word contains a tag...
            if (word.indexOf('/') != -1) {
                //...replace it with a new (appropriate) word.
                wordList.set(wordList.indexOf(word), findAndReplace(word));
            }
        }
        return buildSentence(wordList);
    }

//    Searches for a tag in the word.
//    If it finds one, a new word (from the appropriate collection) is requested and returned.
//    If not, it returns "no tag found."
    private String findAndReplace(String word) {
        String typeTag = word.substring(0, word.indexOf("/")+1); // "/" indicates the END of a tag
//        Toast.makeText(c, typeTag, Toast.LENGTH_SHORT).show();
        return aWholeNewWord(typeTag);
    }

    //Takes a tag and gets a word from the associated collection.
    //Should only be called if we know the word contains a tag.
    private String aWholeNewWord(String typeTag) {
        Resources res = Resources.getSystem();
        String newWord = "";

        switch (typeTag) { //todo using resource lookups causes crash
            case "ns/":
                newWord = "Pope"; //getRandom(res.getStringArray(R.array.nounSingular));
                break;
            case "vb/":
                newWord = "crap";//getRandom(res.getStringArray(R.array.verbBase));
                break;
            case "vbi/":
                newWord = "stabbing";//getRandom(res.getStringArray(R.array.verb_ing));
                break;
            case "aj/":
                newWord = "pointy";//getRandom(res.getStringArray(R.array.adjectives));
                break;
            case "lc/":
                newWord = "woods";//getRandom(res.getStringArray(R.array.locations));
                break;
        }
        return newWord;
    }

    private String buildSentence(ArrayList<String> array) {
        StringBuilder builder = new StringBuilder();

        for (String string : array) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(string);
        }
        return builder.toString();
    }

    //gets a random string from a String array
    private String getRandom(String[] list) {
        Random rand = new Random();

        int randomNum = rand.nextInt(list.length);
        return list[randomNum];
    }
}