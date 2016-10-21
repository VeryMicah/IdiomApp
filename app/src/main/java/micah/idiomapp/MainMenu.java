package micah.idiomapp;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainMenu extends AppCompatActivity {
    private static Random generator = new Random(); //used to select random statement, and select random options for replacement.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        prepBtn_NrmlStatement();
        prepBtn_SillyStatement();
    }

    private void prepBtn_NrmlStatement() {
        //instantiate the views we'll be using
        final Button btn_normal = (Button) findViewById(R.id.main_btn_normalIdiom);
        final TextView tv_normal = (TextView) findViewById(R.id.main_et_idiom);


        //set on click listener
        btn_normal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //build a random statement
                String statement = makeRandomStatement();

                //while the statement is equal to what's currently being displayed
                while(statement.equals( tv_normal.getText().toString() ) == true){
                    //get a new one
                    statement = makeRandomStatement();
                }

                tv_normal.setText(statement);
            }
        });
    } 

    private void prepBtn_SillyStatement() {
        final Button btn_silly = (Button) findViewById(R.id.main_btn_sillyIdiom);
        final TextView tv_statement = (TextView) findViewById(R.id.main_et_idiom);

        btn_silly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SentenceScanner scan = new SentenceScanner();
                String sillyStatement = makeSillyStatement();
//todo this might be broken
                //while the statement is equal to what's currently being displayed
                while(sillyStatement.equals( tv_statement.getText().toString() ) == true){
                    //get a new one
                    sillyStatement = makeRandomStatement();
                }
                scan.scanSentence(sillyStatement);
            }
        });
    }

    private String makeRandomStatement() {
        //get the array of statements from app resources file
        String[] statements = getResources().getStringArray(R.array.statements);
        //select a random num within appropriate scope
        int randomNum = generator.nextInt(statements.length);
        //get the statement at that position in the array
        String statement = statements[randomNum];
        //check if the statement is a question and if it is, add a question mark.
        statement = checkIfQuestion(statement);

        return statement;
    }

    private String makeSillyStatement() {
        //get the array of statements from app resources file
        String[] statements = getResources().getStringArray(R.array.statements_tagged);
        //select a random num within appropriate scope
        int randomNum = generator.nextInt(statements.length);
        //get the statement at that position in the array
        String statement = statements[randomNum];
        //check if the statement is a question and if it is, add a question mark.
        statement = checkIfQuestion(statement);

        return statement;
    }

    /*checks for question and displays question mark, if necessary.*/
    private String checkIfQuestion(String statement) {
        QuestionChecker check = new QuestionChecker();

        if (check.ifQuestion(statement) == true){
            statement = statement + "?";
        }
        return statement;
    }

}