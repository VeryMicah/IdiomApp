package micah.idiomapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by micah on 18/10/2016.
 * READ-ME
 * This class demands a LinkedHashMap.  Each LinkedHashMap entry should contain:
 * (1) A tag, which has been used to identify individual words in your sentence that the sentence scanner will replace.
 * (2) An array of words that can be used as replacements for words with the associated tag.
 *
 * IDENTIFYING WORDS:
 * Tags should precede the word they are identifying, and finish with the '/' character.  E.g. "Hello world" tagged might
 * be, "Hello noun/world."
 */

public class SentenceScanner {
    private ArrayList<String> tags = new ArrayList<>();
    private ArrayList<String[]> wordGroups = new ArrayList<>();
    private ArrayList<String> sentenceSplit = new ArrayList<>();

    //Constructor
    public SentenceScanner(LinkedHashMap<String, String[]> tagsAndWords) {
        //For each entry in the provided LinkedHashMap...
        for (Map.Entry<String, String[]> entry : tagsAndWords.entrySet()) {
            //Add the key (tag) and value (wordGroup) to an ArrayList (which can then be queried based on index)
            tags.add(entry.getKey());
            wordGroups.add(entry.getValue());
        }
    }

    //Splits sentence into an ArrayList and processes each word, then reassembles.
    public String scanAndSwap(String sentence) {

        //Break sentence down into words (splits on space character)
        String[] demWords = sentence.split("\\s+");

        //add each word to the arraylist of words
        for (String word : demWords) {
            sentenceSplit.add(word);
        }
        checkForTags(sentenceSplit);
        return buildSentence(sentenceSplit);
    }

    //    Searches a sentence for tagged words.
//    If it finds one, the word is replaced with an alternative word (from the appropriate collection)
    private ArrayList<String> checkForTags(ArrayList<String> words) {
        //check each word in the array
        for (String word : words) {
            //if word contains a tag...
            if (word.indexOf('/') != -1) { //todo search for the specific tags sent by the user instead - makes this more widely useable
                //identify the tag...
                String typeTag = word.substring(0, word.indexOf("/") + 1); // "/" indicates the END of a tag
                //...and replace the word with a new word from the appropriate collection.
                words.set(words.indexOf(word), getNewWord(typeTag));
            }
        }
        return words;
    }

    //Takes a tag and gets a word from the associated collection.
    //Is only called if we know the word contains a tag.
    private String getNewWord(String typeTag) {
        String newWord = "oops";
        int index = 0;

        //Iterate the list of provided tags, to see if any match the tag we found.
        while (index < tags.size()) {
            if (typeTag.equals(tags.get(index))) {
                //if it does, get a new word from the associated list and return it.
                newWord = getRandom(wordGroups.get(index));
            }
            index++;
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