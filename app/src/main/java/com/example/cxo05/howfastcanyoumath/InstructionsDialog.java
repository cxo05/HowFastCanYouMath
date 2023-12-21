package com.example.cxo05.howfastcanyoumath;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

public class InstructionsDialog  extends Dialog implements View.OnClickListener {

    public InstructionsDialog(@NonNull Context context) {
        super(context);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.instructions_dialog);
        ImageButton button = (ImageButton) findViewById(R.id.close);
        button.setOnClickListener(this);

        //Fonts
        Fonts asd = new Fonts(getContext());

        TextView title = (TextView) findViewById(R.id.instructions_title);
        title.setTypeface(asd.getMercadoSansBold());

        TextView details = (TextView) findViewById(R.id.instructions_details);
        details.setTypeface(asd.getMercadoSansLight());
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
