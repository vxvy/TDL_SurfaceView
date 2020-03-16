package com.example.city_clean.escenas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.example.city_clean.MainActivity;
import com.example.city_clean.R;
import com.example.city_clean.codeUtils.AssetsPaths;
import com.example.city_clean.codeUtils.Constantes;
import com.example.city_clean.codeUtils.RecursosCodigo;
import com.example.city_clean.elementos.Boton;
import com.example.city_clean.elementos.GestionDatos;

import java.util.ArrayList;

public class EscenaOpciones extends EsquemaEscena {

    public Paint fontPaint;
    public Bitmap bmFondo;

    public ArrayList<Boton> arlBotonnes;

    public int auxH;
    public int auxV;

    public EscenaOpciones(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);

        this.auxH =anchoPantalla/5;
        this.auxV = altoPantalla/11;

        arlBotonnes = new ArrayList<Boton>();
        arlBotonnes.add(
                new Boton(
                        this.auxH,
                        auxV * 5,
                        this.auxH *2,
                        auxV * 6,
                        Color.WHITE,
                        false,
                        Constantes.OPCIONES_MUSICA,
                        Color.BLACK,
                        Constantes.OPCIONES_MUSICA_ID)
        );
        arlBotonnes.add(
                new Boton(
                        this.auxH,
                        auxV * 7,
                        this.auxH *2,
                        auxV * 8,
                        Color.WHITE,
                        false,
                        Constantes.OPCIONES_SONIDOS,
                        Color.BLACK,
                        Constantes.OPCIONES_SONIDOS_ID)
        );
        arlBotonnes.add(
                new Boton(
                        this.auxH,
                        auxV * 9,
                        this.auxH *2,
                        auxV * 10,
                        Color.WHITE,
                        false,
                        Constantes.OPCIONES_VIBRACION,
                        Color.BLACK,
                        Constantes.OPCIONES_VIBRACION_ID)
        );


        fontPaint = new Paint();
        fontPaint.setTypeface(Typeface.createFromAsset(MainActivity.context.getAssets(), AssetsPaths.FONT_AWESOME_PATH));
        fontPaint.setColor(this.context.getColor(R.color.papiro1));
        fontPaint.setTextSize(auxV);

        bmFondo = RecursosCodigo.getBitmapFromAssets(this.context, AssetsPaths.CUT_TITLE_PATH);
        bmFondo = Bitmap.createBitmap(bmFondo,0,0,bmFondo.getWidth()/2,bmFondo.getHeight());
    }

    @Override
    public void escenaDibuja(Canvas c) {
        c.drawRect(0,0,anchoPantalla, altoPantalla, new Paint());
        c.drawBitmap(
                bmFondo,
                new Rect(0,0,bmFondo.getWidth(),bmFondo.getHeight()),
                new Rect(0,0, anchoPantalla,altoPantalla),
                null
        );

        //music
        c.drawText(
                (MainActivity.musica ?
                                        context.getText(R.string.opt_music_on_ico) :
                                        context.getText(R.string.opt_music_off_ico))
                ,
                this.auxH*3,
                auxV * 5,
                fontPaint);
        //sound effs
        c.drawText(
                (String) (MainActivity.efectos ?
                        context.getText(R.string.opt_soundeffects_on_ico) :
                        context.getText(R.string.opt_soundeffects_off_ico))
                ,
                this.auxH*3,
                auxV * 5,
                fontPaint);
        //vib
        c.drawText(
                (String) (MainActivity.vibracion ?
                        context.getText(R.string.opt_vibration_on_ico) :
                        context.getText(R.string.opt_vibration_off_ico))
                ,
                this.auxH*3,
                auxV * 5,
                fontPaint);

        for (Boton b:arlBotonnes) {
            b.dibujaBoton(c);
        }
        super.escenaDibuja(c);
    }

    @Override
    public int onTouchEvent(MotionEvent event) {
        ArrayList<Boolean> auxGameOptions = new ArrayList<Boolean>();

        for(Boton b:arlBotonnes){
            //music
            if (b.pulsaBoton(event) &&
                b.btnValor == Constantes.OPCIONES_MUSICA_ID){
                auxGameOptions.add(!MainActivity.musica);
            }else{
                auxGameOptions.add(MainActivity.musica);
            }

            //sound effs
            if (b.pulsaBoton(event) &&
                b.btnValor == Constantes.OPCIONES_SONIDOS_ID){
                auxGameOptions.add(!MainActivity.efectos);
            }else{
                auxGameOptions.add(MainActivity.efectos);
            }

            //vib
            if (b.pulsaBoton(event) &&
                b.btnValor == Constantes.OPCIONES_VIBRACION_ID){
                auxGameOptions.add(!MainActivity.vibracion);
            }else{
                auxGameOptions.add(MainActivity.vibracion);
            }
        }

        //guarda los datos
        GestionDatos gd = new GestionDatos();
        gd.borrarOpciones();
        gd.guardarOpciones(auxGameOptions);

        //gestiona el btnAtras
        return super.onTouchEvent(event);
    }
}
