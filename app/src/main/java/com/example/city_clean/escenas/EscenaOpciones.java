package com.example.city_clean.escenas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.example.city_clean.MainActivity;
import com.example.city_clean.codeUtils.AssetsPaths;

public class EscenaOpciones extends EsquemaEscena {

    public Paint fontPaint;
    public int idEscena;

    public EscenaOpciones(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);

        fontPaint = new Paint();
        fontPaint.setTypeface(Typeface.createFromAsset(MainActivity.context.getAssets(), AssetsPaths.FONT_AWESOME_PATH));

    }

    @Override
    public void escenaDibuja(Canvas c) {

    }

    @Override
    public void escenaActFisicas() {

    }

    @Override
    public int onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
