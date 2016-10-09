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
                String statement = getRandomStatement();
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
        int randomNum = 0;
        int compareNum = 0;

        //get a snapshot of the resources as they are right now
        Resources res = getResources();
        //get the array of statements
        String[] statements = res.getStringArray(R.array.statements);

        //until randomNum != compareNum...
        while(randomNum == compareNum) {
            //get random number
            randomNum = generator.nextInt(statements.length);
        }
        compareNum = randomNum;

        //gets statement at random position of the array
        String statement = statements[randomNum];

        //check if it's a question
        statement = checkIfQuestion(statement);

        return statement;
    }

    //add a question mark, if necessary //todo implement string array to scan for question openers
    private String checkIfQuestion(String s) {
        if (s.startsWith("Does")){
            s = s + "?";
        }
        return s;
    }

}