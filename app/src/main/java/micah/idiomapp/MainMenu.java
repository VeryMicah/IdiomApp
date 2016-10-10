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
                //get a random statement
                String statement = getRandomStatement();

                //while the random statement is equal to the current statement
                while(statement.equals( tv_normal.getText().toString() ) == true){
                    //get a new one
                    statement = getRandomStatement();
                }

//                check if it's a question
                //todo this causes random selection to fail, questions are selected multiple times
                statement = formatAsQuestion(statement);

                //update the TextView
                tv_normal.setText(statement);
            }
        });
    }

    private void prepBtn_SillyStatement() {
        final Button btn_silly = (Button) findViewById(R.id.main_btn_sillyIdiom);

        btn_silly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sillyStatement = getRandomStatement();
                Toast.makeText(MainMenu.this, "silly old bear", Toast.LENGTH_SHORT).show();
                //todo: replace variables
            }
        });
    }

    private String getRandomStatement() {
        //get a snapshot of the resources as they are right now
        Resources res = getResources();
        //get the array of statements
        String[] statements = res.getStringArray(R.array.statements);
        //select a random num within appropriate scope
        int randomNum = generator.nextInt(statements.length);

        //gets statement at random position of the array
        String statement = statements[randomNum];

        return statement;
    }

    //checks for question and adds a question mark, if necessary
    private String formatAsQuestion(String statement) {
        QuestionChecker checker = new QuestionChecker();

        if (checker.isQuestion(statement) == true){
            statement = statement + "?";
        }
        return statement;
    }

}