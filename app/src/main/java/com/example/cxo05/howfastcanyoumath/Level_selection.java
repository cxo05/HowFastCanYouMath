package com.example.cxo05.howfastcanyoumath;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Level_selection extends AppCompatActivity {

    private boolean normal = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onWindowFocusChanged(true);
        setContentView(R.layout.activity_level_selection);

        //Font stuff
        Fonts fonts = new Fonts(getApplicationContext());

        //Setting views
        TextView title = (TextView) findViewById(R.id.diff_title);
        title.setTypeface(fonts.getMercadoSans());

        final TextView NormalButton = (TextView) findViewById(R.id.Normal);
        NormalButton.setTypeface(fonts.getMercadoSansLight());
        final TextView EnduranceButton = (TextView) findViewById(R.id.Endurance);
        EnduranceButton.setTextColor(getColor(R.color.lightgray));
        EnduranceButton.setTypeface(fonts.getMercadoSansLight());

        Button goButton = (Button) findViewById(R.id.go_button);
        goButton.setTypeface(fonts.getMercadoSansBold());

        ImageButton backButton = (ImageButton) findViewById(R.id.back_button);

        ImageButton instructions = (ImageButton) findViewById(R.id.instructions);
        //Setting view END

        //Allows user to specify number range
        //Setting spinner
        /*Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Number_range, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*/
        //Setting spinner END

        NormalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(EnduranceButton.getCurrentTextColor() == getColor(R.color.white)){
                    NormalButton.setTextColor(getColor(R.color.white));
                    EnduranceButton.setTextColor(getColor(R.color.lightgray));
                    normal = true;
                }
            }
        });

        EnduranceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NormalButton.getCurrentTextColor() == getColor(R.color.white)){
                    EnduranceButton.setTextColor(getColor(R.color.white));
                    NormalButton.setTextColor(getColor(R.color.lightgray));
                    normal = false;
                }
            }
        });

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartGame(normal);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InstructionsDialog asd = new InstructionsDialog(Level_selection.this);
                asd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                asd.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
                asd.show();
                asd.getWindow().getDecorView().setSystemUiVisibility(getWindow().getDecorView().getSystemUiVisibility());
                asd.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
            }
        });
    }

    private void StartGame(boolean type){
        //True : Normal
        //False : Endurance
        Intent i = new Intent(getApplicationContext(), MainGame.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if(type){
            //Normal
            i.putExtra("Type", "Normal");
            startActivity(i);
        }else{
            //Endurance
            i.putExtra("Type", "Endurance");
            startActivity(i);
        }
        finish();
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
        finish();
    }
}
