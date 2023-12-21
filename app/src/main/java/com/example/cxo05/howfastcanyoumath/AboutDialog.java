package com.example.cxo05.howfastcanyoumath;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;


public class AboutDialog extends Dialog implements View.OnClickListener{

    public AboutDialog(@NonNull Context context) {
        super(context);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.info_dialog);
        ImageButton button = (ImageButton) findViewById(R.id.close);
        button.setOnClickListener(this);

        //Fonts
        Fonts asd = new Fonts(getContext());

        TextView title = (TextView) findViewById(R.id.about_title);
        title.setTypeface(asd.getMercadoSansBold());

        TextView details = (TextView) findViewById(R.id.about_details);
        details.setTypeface(asd.getMercadoSansLight());
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
