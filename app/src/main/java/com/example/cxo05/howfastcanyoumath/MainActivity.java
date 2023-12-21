package com.example.cxo05.howfastcanyoumath;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean volumeButton = false;

    MediaPlayer mediaPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onWindowFocusChanged(true);
        setContentView(R.layout.activity_main);

        //Creating font class
        Fonts fonts = new Fonts(getBaseContext());

        //Setting views
        TextView title = (TextView) findViewById(R.id.title);
        title.setTypeface(fonts.getMercadoSansBold());

        ImageButton play = (ImageButton) findViewById(R.id.play_button);

        final ImageButton volume = (ImageButton) findViewById(R.id.volume);

        ImageButton info = (ImageButton) findViewById(R.id.info);
        //Setting views END

        //ANIMATIONS
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.moveleft);
        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.moveup);
        Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.moveup_short);

        title.startAnimation(animation1);
        play.startAnimation(animation2);
        volume.startAnimation(animation3);
        info.startAnimation(animation3);
        //ANIMATIONS END

        //Play button
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Level_selection.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        //Volume/Music
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.bgm);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!volumeButton){
                    volume.setImageDrawable(getDrawable(R.drawable.ic_volume_off));
                    volumeButton = true;
                    Log.d("asd", "Volume Click");
                    mediaPlayer.setVolume(0,0);
                }else{
                    volume.setImageDrawable(getDrawable(R.drawable.ic_volume_up));
                    volumeButton = false;
                    Log.d("asd", "Volume Click");
                    mediaPlayer.setVolume(1,1);
                }
            }
        });
        //Volume/Music start

        //About button
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutDialog asd = new AboutDialog(MainActivity.this);
                asd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                asd.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
                asd.show();
                asd.getWindow().getDecorView().setSystemUiVisibility(getWindow().getDecorView().getSystemUiVisibility());
                asd.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
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
