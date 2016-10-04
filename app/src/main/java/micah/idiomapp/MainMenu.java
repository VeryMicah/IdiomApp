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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        prepBtnNormalIdiom();
    }

    public void prepBtnNormalIdiom() {
        Resources res = getResources(); //allows access to strings.xml (& other res files)
        final Button btn_normalIdiom = (Button) findViewById(R.id.main_btn_normalIdiom);
        final TextView tv_idiom = (TextView) findViewById(R.id.main_et_idiom);
        final String[] idioms = res.getStringArray(R.array.idioms);

        btn_normalIdiom.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                tv_idiom.setText(getRandom(idioms));
            }


        });
    }


    public static String getRandom(String[] array) {
        Random generator = new Random();
        int randomIndex = generator.nextInt(array.length);
        return array[randomIndex];
    }
}
