package micah.idiomapp;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by micah on 18/10/2016.
 * READ-ME
 * SentenceScanner is designed to read sentences in which certain words have been tagged for replacement, using tags
 * specified by you.  It will then replace tagged words with alternatives from a list, also specified by you.
 *
 * This class demands a LinkedHashMap.  Each LinkedHashMap entry should contain:
 * (1) A tag, which has been used to identify individual words in your sentence that the sentence scanner will replace.
 * (2) An array of words that can be used as replacements for words with the associated tag.
 */

public class SentenceScanner {
    private ArrayList<String> tags = new ArrayList<>();
    private ArrayList<String[]> wordGroups = new ArrayList<>();
    private boolean repeatTwice = false;
    private int randomNum_repeat = 0;

    //Blank Constructor todo fix the issues this causes
    public SentenceScanner(){}

    //Constructor
    public SentenceScanner(LinkedHashMap<String, String[]> tagsAndWords) {
        //For each entry in the provided LinkedHashMap...
        for (Map.Entry<String, String[]> entry : tagsAndWords.entrySet()) {
            //Add the key (tag) and value (wordGroup) to an ArrayList (which can then be queried based on index)
            tags.add(entry.getKey());
            wordGroups.add(entry.getValue());
        }
    }

    public boolean getRepeatTwice() {
        return repeatTwice;
    }

    public void setRepeatTwice(boolean repeatTwice) {
        this.repeatTwice = repeatTwice;
    }

    //Splits sentence into an ArrayList and processes each word, then reassembles.
    public String scanAndSwap(String sentence) {
        ArrayList<String> split = splitIntoWords(sentence);
        checkForTags(split);
        return buildSentence(split);
    }

    //This is public as sometimes you may wish to remove tags from a sentence and display it as normal.
    public String removeTags(String sentence) {
        ArrayList<String> split = splitIntoWords(sentence);

        //Check each word in the array...
        for (String word : split) {
            //...for each tag in the collection...
            for (String tag : tags) {
                //...if the word contains the tag...
                if (word.contains(tag)) {
                    //...update the word to have no tags.
                    split.set(split.indexOf(word), word.replaceAll(tag,""));
                }
            }
        }
        return buildSentence(split);
    }

    private ArrayList<String> splitIntoWords(String sentence) {
        //Break sentence down into words (splits on space character)
        String[] demWords = sentence.split("\\s+");

        //add each word to an arraylist of words
        ArrayList<String> sentenceSplit = new ArrayList<>();
        for (String word : demWords) {
            sentenceSplit.add(word);
        }
        return sentenceSplit;
    }



    //    Searches a sentence for tagged words.
//    If it finds one, the word is replaced with an alternative word (from the appropriate collection)
    private ArrayList<String> checkForTags(ArrayList<String> words) {
        //Check each word in the array...
        for (String word : words) {
            //...for each tag in the collection...
            for (String tag : tags) {
                //...if the word contains the tag...
                if (word.contains(tag)) {
                    //...replace the word with a new word from the appropriate collection.
                    words.set(words.indexOf(word), getNewWord(tag));
                }
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
                //if it does, get a new word from the associated list.
                newWord = getRandom(wordGroups.get(index));
            }
            index++;
        }
        return newWord;
    }

    private String buildSentence(ArrayList<String> sentenceSplit) {
        StringBuilder builder = new StringBuilder();

        for (String string : sentenceSplit) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(string);
        }
        String sentence = builder.toString();

        //Capitalise first word in sentence
        String firstLetter = sentence.substring(0, 1).toUpperCase();

        return firstLetter + sentence.substring(1);
    }

    //gets a random string from a String array
    private String getRandom(String[] list) {
        Random rand = new Random();
        int randomNum = rand.nextInt(list.length);

        //if repeatTwice is false, avoid repeating a random selection twice in a row.
        //todo in reality I should be recording tags used, and avoid repeating the same tag multiple times per sentence.
        if (!repeatTwice) {
            while (randomNum == randomNum_repeat){
                randomNum = rand.nextInt(list.length);
            }
            return list[randomNum];
        } else randomNum = rand.nextInt(list.length);


        return list[randomNum];
    }
}