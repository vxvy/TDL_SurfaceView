package com.example.city_clean.escenas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.example.city_clean.actores.EsquemaActor;
import com.example.city_clean.codeUtils.CodeSnippets;
import com.example.city_clean.codeUtils.Constantes;

import java.util.ArrayList;
import java.util.Random;

import static com.example.city_clean.codeUtils.AssetsPaths.*;

public class EscenaJuego extends EsquemaEscena {

    public Context context;
    public float floorY = 0; //altura del suelo

    public EsquemaActor siegfried;
    public EsquemaActor[] arrActoresEnemigos;
    public EsquemaActor powerUp;

    public ArrayList<EsquemaActor> arlActor;

    public EscenaJuego(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);
        this.context = context;

        //Creación del protagonista
        siegfried = new EsquemaActor(
                anchoPantalla/2,
                floorY,
                5,
//                Constantes.SPRITES_ESCALA,
                1,
                CodeSnippets.getBitmapFromAssets(context, SIEGFRIED_WALKING_PATH),
                64, 64
        );

//        Bitmap aux = CodeSnippets.getBitmapFromAssets(context, ENEMY1_64x160_PATH);
//
//        //Esto será un método que dado el nivel de dificultad creará una cantidad determinada de enemigos de distintos tipos
//        this.arrActoresEnemigos = new EsquemaActor[5];
//        for(int i = 0; i < arrActoresEnemigos.length; i++){
//            this.arrActoresEnemigos[i] = new EsquemaActor(
//                    (int)(Math.random() * anchoPantalla) + 1, //posición aleatoria horizontal
//                    (int)(Math.random() * altoPantalla) + 1,  //posción aleatoria vertical
//                    3, Constantes.SPRITES_ESCALA,
//                    aux,
//                    aux.getWidth(), aux.getWidth()
//            );
////            float x, float y, int numVidas, float escalaSprite,
////            Bitmap spriteContenedor, int tamanyoXSpriteEnBM, int tamanyoYSpriteEnBM
//        }
//
//
//        powerUp = new EsquemaActor(
//                anchoPantalla/2,
//                altoPantalla/2,
//                1,
//                CodeSnippets.getBitmapFromAssets(context, LOGO_PATH),
//                1/4
//        );

        //añadimos todos los elementos dibujados a la colección de cosas que se van a dibujar
        arlActor = new ArrayList<EsquemaActor>();
        arlActor.add(siegfried);
//        arlActor.add(powerUp);
//        for (EsquemaActor ea: arrActoresEnemigos) {
//            arlActor.add(ea);
//        }
    }

    @Override
    public void escenaDibuja(Canvas c) {
        c.drawRect(0, 0,
                anchoPantalla, altoPantalla,
                new Paint()
        ); //Con esto pinto toda la pantalla de negro para evitar solapamientos raros

        for (EsquemaActor ea: arlActor) {
            ea.dibujaActor(c);
        }
        super.escenaDibuja(c); //dibuja botón para retroceder
    }

    @Override
    public int onTouchEvent(MotionEvent event) {
        siegfried.pulsaActor(event);

        return super.onTouchEvent(event);
    }

    @Override
    public void escenaActFisicas() {
//        siegfried.choca();
    }
}