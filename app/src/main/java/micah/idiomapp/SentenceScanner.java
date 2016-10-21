package micah.idiomapp;

import android.content.res.Resources;

import java.util.Random;

/**
 * Created by micah on 18/10/2016.
 */

public class SentenceScanner {

    public SentenceScanner() {
    }

    //Finds tagged words within a sentence.
    //Replaces any tagged words with a different word of the same type.
    public String scanSentence(String sentence) {

        //Split into an array of words
        String[] wordList = sentence.split("\\s+");

        //check each word in the array
        for (String word : wordList
                ) {
            String response = analyse(word);
            if (response.equals("no tag found")){
                //no tag in word, do nothing with it
            } else {
                //word has a tag: Replace the word with the new word
                word = response;
            }
        }
        //todo rebuild sentence
        return buildSentence(wordList);
    }

    //    Searches for a tag in the word.
//    If it finds one, a new word (from the appropriate collection) is requested and returned.
//    If not, it returns "no tag found."
    private String analyse(String word) {
        if (!word.contains("/")) {
            return "no tag found";
        } else {
            int tagEnd = word.indexOf("/"); // "/" indicates the END of a tag
            String typeTag = word.substring(0, tagEnd);
            return aWholeNewWord(typeTag);
        }
    }

    //Takes a tag and gets a word from the associated collection.
    //Should only be called if we know the word contains a tag.
    private String aWholeNewWord(String typeTag) {
        Resources res = Resources.getSystem();
        String newWord = "";

        switch (typeTag) {
            case "ns/":
                newWord = getRandom(res.getStringArray(R.array.nounSingular));
                break;
            case "vb/":
                newWord = getRandom(res.getStringArray(R.array.verbBase));
                break;
            case "aj/":
                newWord = getRandom(res.getStringArray(R.array.adjectives));
                break;
            case "lc/":
                newWord = getRandom(res.getStringArray(R.array.locations));
                break;
        }
        return newWord;
    }

    private String buildSentence(String[] array){
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
