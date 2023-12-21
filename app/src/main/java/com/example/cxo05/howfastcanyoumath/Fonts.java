package com.example.cxo05.howfastcanyoumath;


import android.content.Context;
import android.graphics.Typeface;

public class Fonts {
    private Typeface MercadoSansBold;
    private Typeface MercadoSansLight;
    private Typeface MercadoSans;

    public Fonts (Context context){
        MercadoSansBold = Typeface.createFromAsset(context.getAssets(),  "fonts/Mercado Sans Bold.ttf");
        MercadoSansLight = Typeface.createFromAsset(context.getAssets(),  "fonts/Mercado Sans Light.ttf");
        MercadoSans = Typeface.createFromAsset(context.getAssets(),  "fonts/Mercado Sans.ttf");
    }

    public Typeface getMercadoSans() {
        return MercadoSans;
    }

    public Typeface getMercadoSansLight() {
        return MercadoSansLight;
    }

    public Typeface getMercadoSansBold() {
        return MercadoSansBold;
    }
}
