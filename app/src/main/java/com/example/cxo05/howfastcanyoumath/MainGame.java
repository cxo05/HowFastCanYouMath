package com.example.cxo05.howfastcanyoumath;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainGame extends AppCompatActivity {

    Button eqn1;
    Button eqn2;
    Button eqn3;
    TextView operatorTextview;
    LinearLayout equationLayout;
    ProgressBar timing;
    TextView pointsText;

    int answer = 0;
    Button correctButton;

    Animation animation1;

    ArrayList<Button> buttonOptions;
    List<String> operators = Arrays.asList("\u002B", "\u2212", "\u00D7", "\u00F7"); //+,-,*,/

    int points = 0;

    CountDownTimer timer;

    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onWindowFocusChanged(true);
        setContentView(R.layout.activity_main_game);

        Bundle extras = getIntent().getExtras();
        type = extras.getString("Type");

        init();
        newEquation();

        animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shrink_expand);
    }

    private void init() {
        Fonts fonts = new Fonts(getApplicationContext());

        equationLayout = (LinearLayout) findViewById(R.id.equationLayout);
        eqn1 = (Button) findViewById(R.id.eqn1);
        eqn1.setTypeface(fonts.getMercadoSansBold());
        eqn2 = (Button) findViewById(R.id.eqn2);
        eqn2.setTypeface(fonts.getMercadoSansBold());
        eqn3 = (Button) findViewById(R.id.eqn3);
        eqn3.setTypeface(fonts.getMercadoSansBold());
        operatorTextview = (TextView) findViewById(R.id.operator);

        pointsText = (TextView) findViewById(R.id.points);

        buttonOptions = new ArrayList<>();

        TableLayout table = (TableLayout) findViewById(R.id.TABLE);
        for (int i = 0; i < 3; i++) {
            TableRow row = (TableRow) table.getChildAt(i);
            for (int a = 0; a < 3; a++) {
                final Button button = (Button) row.getChildAt(a);
                buttonOptions.add(button);
                button.setOnClickListener(optionsClick);
            }
        }

        //COUNTDOWN TIMER BAR
        timing = (ProgressBar) findViewById(R.id.time);

        int Duration = 0;

        if(type.equals("Normal")) {
            Duration = (4*1000);
        }else{
            Duration = (20*1000);
        }

        timing.setMax(Duration);

        timerReset(Duration);
        //Countdown timer END
    }

    private void timerReset (int newDuration){
        if(timer != null){
            timer.cancel();
        }
        timer = new CountDownTimer(newDuration, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                timing.setProgress((int)millisUntilFinished);
            }

            @Override
            public void onFinish() {
                Log.d("asd","Game End");
                gameEnd();
            }
        };
        timer.start();
    }

    private void newEquation(){
        //NUMBER GENERATION
        int first = randomInt(1,15);
        int second = randomInt(1,15);
        int operator = randomInt(0,1); //TODO Change to ALL OPERATORS when ready

        //SETTING EQUATION TEXT
        eqn1.setText(Integer.toString(first));
        eqn2.setText(Integer.toString(second));
        operatorTextview.setText(operators.get(operator));

        //COMPUTING RESULTS
        int result = 0;
        if(operator == 0){
            result = first + second;
            Log.d("asd", "ADD");
        }else if(operator == 1){
            result = first - second;
            Log.d("asd", "Minus");
        }
        answer = result;

        //FILLING UP OPTIONS
        ArrayList<Integer> tem = new ArrayList<>();
        for(int i = result-10;i < result+10;i++){
            if(i!=result){
                tem.add(i);
            }
        }
        Collections.shuffle(tem);

        for(int i = 0;i < 9; i++){
            buttonOptions.get(i).setText(Integer.toString(tem.get(i)));
        }

        //FINAL ANSWER POSITION SETTING
        int answerPosition = randomInt(0,8);
        buttonOptions.get(answerPosition).setText(Integer.toString(result));
        correctButton = buttonOptions.get(answerPosition);
    }

    private int randomInt(int lowest, int highest){
        Random rand = new Random();
        return rand.nextInt((highest - lowest) + 1) + lowest;
    }

    private boolean checkAnswer(int ans){
        return answer == ans;
    }

    private View.OnClickListener optionsClick =  new View.OnClickListener(){
        public void onClick(View v) {
            final Button button = (Button) findViewById(v.getId());
            int asd = Integer.parseInt(button.getText().toString());
            if(checkAnswer(asd)){
                Log.d("asd", "Correct");
                button.setBackgroundTintList(getColorStateList(R.color.green));
                points += 10;
                pointsText.setText(Integer.toString(points));
                change(true);
                if(!type.equals("Normal")){
                    Log.d("asd", "Timer Reset");
                    if(timing.getProgress() + (3*1000) > 20000){
                        timerReset(20*1000);
                    }else{
                        timerReset(timing.getProgress() + 3*1000);
                    }
                }
            }else{
                Log.d("asd","Wrong");
                button.setBackgroundTintList(getColorStateList(R.color.red));
                correctButton.setBackgroundTintList(getColorStateList(R.color.green));
                change(false);
            }
        }
    };

    boolean animated = false;
    boolean newEquation = false;

    private void change(final boolean correct){
        if(type.equals("Normal")){
            timer.cancel();
            Log.d("asd", "Normal mode Timer canceled for options change");
        }
        for(Button b: buttonOptions){
            b.setEnabled(false);
        }

        new CountDownTimer(1400, 20) {
            public void onFinish() {
                Log.d("asd", "Finished options change");
                animated = false;
                newEquation = false;
                if(type.equals("Normal")){
                    timerReset(5*1000);
                    Log.d("asd", "Normal reset to 5 seconds");
                }
                for(Button b: buttonOptions){
                    b.setEnabled(true);
                }
            }

            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished <= 1000 && !animated){
                    if(!correct && type.equals("Normal")){
                        gameEnd();
                        Log.d("asd", "Normal mode wrong option game end");
                        return;
                    }
                    for(Button b:buttonOptions){
                        b.startAnimation(animation1);
                        b.setBackgroundTintList(getColorStateList(R.color.darkorangestate));
                    }
                    eqn1.startAnimation(animation1);
                    eqn2.startAnimation(animation1);
                    animated = true;
                    Log.d("asd", "Animation start");
                }
                if(millisUntilFinished <= 800 && !newEquation){
                    newEquation();
                    Log.d("asd", "New equation");
                    newEquation = true;
                }
            }
        }.start();
    }

    boolean hasGameEnded = false;

    private void gameEnd(){
        if(!hasGameEnded) {
            hasGameEnded = true;
            Intent i = new Intent(getApplicationContext(), Game_End.class);
            i.putExtra("Score", points);
            i.putExtra("Type", type);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        }
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

    @Override
    public void onBackPressed() {
        gameEnd();
    }
}
