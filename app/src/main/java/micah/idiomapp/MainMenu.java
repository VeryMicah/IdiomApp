package micah.idiomapp;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainMenu extends AppCompatActivity {
    private static Random generator = new Random(); //used to select random statement, and select random options for replacement.
    private String origStatement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        prepBtn_NrmlStatement();
        prepBtn_SillyStatement();
    }

    private void prepBtn_NrmlStatement() {
        //allows access to strings.xml (& other res files)
        final Resources res = getResources();
        //get the array of statements
        final String[] idioms = res.getStringArray(R.array.statements);

        //instantiate the views we'll be using
        final Button btn_normal = (Button) findViewById(R.id.main_btn_normalIdiom);
        final TextView tv_normal = (TextView) findViewById(R.id.main_et_idiom);

        //set on click listener
        btn_normal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                origStatement = getRandom(idioms);
                origStatement = checkIfQuestion(origStatement);
                tv_normal.setText(origStatement);
            }
        });
    }

    private void prepBtn_SillyStatement() {
        final Button btn_silly = (Button) findViewById(R.id.main_btn_sillyIdiom);

        btn_silly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sillyStatement = origStatement;
                //todo: replace variables

            }
        });
    }

    private static String getRandom(String[] array) {

        int randomIndex = generator.nextInt(array.length);
        return array[randomIndex];
    }

    //add a question mark, if necessary //todo implement string array to scan for question openers
    private String checkIfQuestion(String s) {
        if (s.startsWith("Does")){
            s = s + "?";
        }
        return s;
    }

}