package com.example.cxo05.howfastcanyoumath;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Game_End extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onWindowFocusChanged(true);
        setContentView(R.layout.activity_game__end);

        //Fonts
        Fonts asd = new Fonts(getApplicationContext());

        //EXTRAS
        Bundle extras = getIntent().getExtras();
        int currentScore = extras.getInt("Score");
        String type = extras.getString("Type");
        //EXTRAS END

        //Setting views
        TextView gameOverTitle = (TextView) findViewById(R.id.game_overTitle);
        gameOverTitle.setTypeface(asd.getMercadoSansBold());
        TextView scoreText = (TextView) findViewById(R.id.Score);
        scoreText.setTypeface(asd.getMercadoSans());
        TextView highscoreText = (TextView) findViewById(R.id.Highscore);
        highscoreText.setTypeface(asd.getMercadoSans());
        TextView typeText = (TextView) findViewById(R.id.type_title);
        typeText.setTypeface(asd.getMercadoSansBold());

        typeText.setText(type);

        scoreText.setText("Score\n" + Integer.toString(currentScore));

        SharedPreferences scores = getPreferences(Context.MODE_APPEND);
        int highscore = 0;
        if(type.equals("Normal")){
            highscore = scores.getInt(getString(R.string.Normal_Highscore), 0);
        }else{
            highscore = scores.getInt(getString(R.string.Endurance_Highscore), 0);
        }

        if(highscore > currentScore){
            highscoreText.setText("Highscore\n" + Integer.toString(highscore));
        }else{
            SharedPreferences.Editor editor = scores.edit();
            if(type.equals("Normal")){
                editor.putInt(getString(R.string.Normal_Highscore), currentScore);
            }else{
                editor.putInt(getString(R.string.Endurance_Highscore), currentScore);
            }
            editor.apply();
            highscoreText.setText("Highscore\n" + Integer.toString(currentScore));
        }
        //Setting views END

        ImageButton restart = (ImageButton) findViewById(R.id.restartButton);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Level_selection.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
