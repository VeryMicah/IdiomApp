package micah.idiomapp;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by micah on 18/10/2016.
 */

public class SentenceScanner {
//    private LinkedHashMap<String, String[]> indicators = new LinkedHashMap<>();
    private ArrayList<String> tags = new ArrayList<>();
    private String tag1, tag2, tag3, tag4, tag5;
    private String[] tag1Words, tag2Words, tag3Words, tag4Words, tag5Words;

    //Constructor
    //Must be passed a LinkedHashMap containing String tags, each associated with a String array of words.
    public SentenceScanner(LinkedHashMap<String, String []> indicators) {
//        this.indicators = indicators;

        //For each entry in the provided HashMap...
        for (Map.Entry<String, String[]> entry : indicators.entrySet()){
            //Add the key to an ArrayList (which can then be queried based on index)
            tags.add(entry.getKey());
        }

        //Extract and assign tags and words.
        this.tag1 = tags.get(0);
        this.tag2 = tags.get(1);
        this.tag3 = tags.get(2);
        this.tag4 = tags.get(3);
        this.tag5 = tags.get(4);
        this.tag1Words = indicators.get(tag1);
        this.tag2Words = indicators.get(tag2);
        this.tag3Words = indicators.get(tag3);
        this.tag4Words = indicators.get(tag4);
        this.tag5Words = indicators.get(tag5);
    }

    //Splits sentence into an ArrayList and processes each word, then reassembles
    public String scanAndSwap(String sentence) {
        ArrayList<String> wordList = new ArrayList<>();

        //Break sentence down into words (splits on space character)
        String[] demWords = sentence.split("\\s+");

        //add each word to the arraylist of words
        for (String word : demWords) {
            wordList.add(word);
        }
        checkForTags(wordList);
        return buildSentence(wordList);
    }

//    Searches a sentence for tagged words.
//    If it finds one, the word is replaced with an alternative word (from the appropriate collection)
    private ArrayList<String> checkForTags(ArrayList<String> words) {
        //check each word in the array
        for (String word : words) {
            //if word contains a tag...
            if (word.indexOf('/') != -1) {
                //identify the tag...
                String typeTag = word.substring(0, word.indexOf("/") + 1); // "/" indicates the END of a tag
                //...and replace the word with a new word from the appropriate collection.
                words.set(words.indexOf(word), aWholeNewWord(typeTag));
            }
        }
        return words;
    }

    //Takes a tag and gets a word from the associated collection.
    //Is only called if we know the word contains a tag.
    private String aWholeNewWord(String typeTag) {

        if (typeTag.equals(tag1)) {
            return getRandom(tag1Words);
        } else if (typeTag.equals(tag2)) {
            return getRandom(tag2Words);
        } else if (typeTag.equals(tag3)) {
            return getRandom(tag3Words);
        } else if (typeTag.equals(tag4)) {
            return getRandom(tag4Words);
        } else if (typeTag.equals(tag5)) {
            return getRandom(tag5Words);
        } else return "no tag here matey potatey";
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