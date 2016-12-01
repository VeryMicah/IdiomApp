package micah.idiomapp;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedHashMap;
import java.util.Random;

public class MainMenu extends AppCompatActivity {
    private Resources res = null;
    QuestionChecker check = new QuestionChecker();
    //Used to select random statement, and select random options for replacement:
    private static Random generator = new Random();
    //Linked Hash Map to tell SentenceScanner what to look for.
    final LinkedHashMap<String, String[]> searchElements = new LinkedHashMap<>();
    private static SentenceScanner sentenceScan = new SentenceScanner();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        res = this.getResources();
        //-----------------|-----------------TAGS-----------------|--------------REPLACEMENT WORDS--------------|
        searchElements.put(res.getString(R.string.tag_nounSingular), res.getStringArray(R.array.ns_nounSingular));
        searchElements.put(res.getString(R.string.tag_nounMultiple), res.getStringArray(R.array.nm_nounMultiple));
        searchElements.put(res.getString(R.string.tag_actionVerbs), res.getStringArray(R.array.avb_actionVerbs));
        searchElements.put(res.getString(R.string.tag_verbIng), res.getStringArray(R.array.vbi_verbing));
        searchElements.put(res.getString(R.string.tag_adjective), res.getStringArray(R.array.aj_adjectives));
        searchElements.put(res.getString(R.string.tag_location), res.getStringArray(R.array.lc_locations));
        searchElements.put(res.getString(R.string.tag_doingVerbs), res.getStringArray(R.array.dvb_doingVerbs));
        sentenceScan = new SentenceScanner(searchElements);

        prepBtn_NrmlStatement();
        prepBtn_SillyStatement();
    }

    private void prepBtn_NrmlStatement() {
        //instantiate the views we'll be using
        final Button btn_normal = (Button) findViewById(R.id.main_btn_normalIdiom);
        final TextView tv_statement = (TextView) findViewById(R.id.main_et_idiom);


        //set on click listener
        btn_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //build a random statement
                String statement = makeNormalStatement();

                //while the statement is equal to what's currently being displayed
                while (statement.equals(tv_statement.getText().toString()) == true) {
                    //get a new one
                    statement = makeNormalStatement();
                }
                tv_statement.setText(statement);
            }
        });
    }

    private void prepBtn_SillyStatement() {
        final Button btn_silly = (Button) findViewById(R.id.main_btn_sillyIdiom);
        final TextView tv_statement = (TextView) findViewById(R.id.main_et_idiom);

        btn_silly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //select and prepare a tagged statement from res
                String sillyStatement = getRandomStatement();
                //while the statement is equal to what's currently being displayed
                while (sillyStatement.equals(tv_statement.getText().toString()) == true) {
                    //get a new one
                    sillyStatement = getRandomStatement();
                }
                tv_statement.setText(sentenceScan.scanAndSwap(sillyStatement));
            }
        });
    }

    private String makeNormalStatement() {
        //Build a random sentence and use SentenceScanner to remove any tags from it.
        String sentence = sentenceScan.removeTags(getRandomStatement());

        //Check if the statement is a question and if it is, add a question mark.
        sentence = checkIfQuestion(sentence);

        return sentence;
    }

    private String getRandomStatement() {
        //get the array of statements from app resources file
        String[] statements = getResources().getStringArray(R.array.statements);
        //select a random num within appropriate scope
        int randomNum = generator.nextInt(statements.length);
        //get the statement at that position in the array
        String statement = statements[randomNum];

        return statement;
    }

    /*checks for question and displays question mark, if necessary.*/
    private String checkIfQuestion(String statement) {

//todo the check always returns false
        if (check.ifQuestion(statement) == true) {
            statement = statement + "?";
        } else if (statement.charAt(statement.length()-1) != '.') {
            statement = statement + ".";
        }
        return statement;
    }

}