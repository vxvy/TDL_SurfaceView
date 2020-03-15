package com.example.city_clean.escenas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.example.city_clean.R;
import com.example.city_clean.codeUtils.AssetsPaths;
import com.example.city_clean.codeUtils.RecursosCodigo;
import com.example.city_clean.elementos.Boton;

import java.util.ArrayList;

import static com.example.city_clean.codeUtils.Constantes.*;

public class EscenaMenu extends EsquemaEscena {

    ArrayList<Boton> btnColection;

    String strTitulo;
    Paint paintTituloStroke;
    Paint paintTituloFill;

    //posiciones relativas de esta pantalla, se instanciar
    int auxV;
    int auxH;

    public Bitmap bmFondo;

    public EscenaMenu(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);

        this.auxV = altoPantalla/9;
        this.auxH = anchoPantalla/7;

        //Titulo
        strTitulo = context.getString(R.string.app_name);
        paintTituloFill = new Paint();
        paintTituloFill.setTextSize(auxV*2);
        paintTituloFill.setTypeface(Typeface.createFromAsset(context.getAssets(), AssetsPaths.FONT_MAIN_TITLE_PATH));
        paintTituloFill.setColor(Color.BLACK);
        paintTituloFill.setStyle(Paint.Style.FILL);
        paintTituloStroke = new Paint();
        paintTituloStroke.setTextSize(auxV*2);
        paintTituloStroke.setTypeface(Typeface.createFromAsset(context.getAssets(), AssetsPaths.FONT_MAIN_TITLE_PATH));
        paintTituloStroke.setColor(context.getColor(R.color.orangyhard));
        paintTituloStroke.setStyle(Paint.Style.STROKE);
        paintTituloStroke.setStrokeWidth(2);

        //Botones
        btnColection = new ArrayList<>();
        btnColection.add(
                new Boton(
                        auxH, auxV*3,
                        auxH*6, auxV*4,
                        Color.TRANSPARENT,
                        true,
                        context.getString(R.string.btn_play),
                        context.getColor(R.color.papiro1),
                        ESCENA_JUGAR_VALUE));
        btnColection.add(
                new Boton(
                        auxH,auxV *5,
                        auxH*3, auxV*6,
                        Color.TRANSPARENT,
                        true,
                        context.getString(R.string.btn_help),
                        context.getColor(R.color.papiro2),
                        ESCENA_AYUDA_VALUE));
        btnColection.add(
                new Boton(
                        auxH*4,auxV *5,
                        auxH*6, auxV*6,
                        Color.TRANSPARENT,
                        true,
                        context.getString(R.string.btn_scores),
                        context.getColor(R.color.papiro2),
                        ESCENA_RECORDS_VALUE));
        btnColection.add(
                new Boton(
                        auxH,auxV*7,
                        auxH*6,auxV*8,
                        Color.TRANSPARENT,
                        true,
                        context.getString(R.string.btn_options),
                        context.getColor(R.color.papiro1),
                        ESCENA_OPCIONES_VALUE));

        this.bmFondo = RecursosCodigo.getBitmapFromAssets(context,"background1.png");
        bmFondo = Bitmap.createScaledBitmap(
                bmFondo,
                anchoPantalla,altoPantalla,
                false);
    }

    @Override
    public void escenaDibuja(Canvas c) {
        c.drawBitmap(bmFondo,0,0,null);
        c.drawText(strTitulo, auxH, auxV*2, paintTituloFill);
        c.drawText(strTitulo, auxH, auxV*2, paintTituloStroke);
        for(Boton b : btnColection){
            b.dibujaBoton(c);
        }
    }

    @Override
    public int onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                for(Boton b : btnColection){
                    if(b.pulsaBoton(event)){
                        return b.btnValor;
                    }
                }
            break;
        }
        return idEscena; //si el método pudiese ser void no sería necesario esto, pero no puede ser :c
    }
}