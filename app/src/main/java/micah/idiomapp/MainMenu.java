package micah.idiomapp;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainMenu extends AppCompatActivity {
    private static Random generator = new Random(); //used to select random statement, and select random options for replacement.
    private String origStatement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        prepBtnNormalIdiom();
        //// TODO: 9/10/2016 add method for prepBtnRandomIdiom()
    }

    public void prepBtnNormalIdiom() {
        //allows access to strings.xml (& other res files)
        Resources res = getResources();
        //get the array of statements
        final String[] idioms = res.getStringArray(R.array.statements);

        //instantiate the views we'll be using
        final Button btn_normalIdiom = (Button) findViewById(R.id.main_btn_normalIdiom);
        final TextView tv_idiom = (TextView) findViewById(R.id.main_et_idiom);

        btn_normalIdiom.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                origStatement = getRandom(idioms);
                origStatement = checkIfQuestion(origStatement);
                tv_idiom.setText(origStatement);
            }
        });
    }

    public static String getRandom(String[] array) {

        int randomIndex = generator.nextInt(array.length);
        return array[randomIndex];
    }

    //add a question mark, if necessary //todo implement string array to scan for question openers
    public String checkIfQuestion(String s) {
        if (s.startsWith("Does")){
            s = s + "?";
        }
        return s;
    }

}