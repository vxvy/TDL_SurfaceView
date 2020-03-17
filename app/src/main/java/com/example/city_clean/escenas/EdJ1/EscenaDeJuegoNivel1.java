package com.example.city_clean.escenas.EdJ1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

import com.example.city_clean.actores.EsquemaActor;
import com.example.city_clean.codeUtils.RecursosCodigo;
import com.example.city_clean.codeUtils.Constantes;
import com.example.city_clean.escenas.EscenaEligeJuego;

import java.util.ArrayList;

import static com.example.city_clean.codeUtils.AssetsPaths.ENEMY1_64x160_PATH;
import static com.example.city_clean.codeUtils.AssetsPaths.LOGO_PATH;
import static com.example.city_clean.codeUtils.AssetsPaths.SIEGFRIED_WALKING_PATH;

    public class EscenaDeJuegoNivel1 extends EscenaEligeJuego {

        public Context context;
        public float floorY = 0; //altura del suelo

        public EsquemaActor siegfried;
        public EsquemaActor[] arrActoresEnemigos;
        public EsquemaActor powerUp;
        public ArrayList<EsquemaActor> arlActor;

        public  EscenaDeJuegoNivel1(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
            super(context, idEscena, anchoPantalla, altoPantalla);
            this.context = context;

            //Creación del protagonista
            siegfried = new EsquemaActor(
                    anchoPantalla/2,
                    floorY,
                    5,
                    Constantes.SPRITES_ESCALA,
                    RecursosCodigo.getBitmapFromAssets(context, SIEGFRIED_WALKING_PATH),
                    64, 64
            );

            //Esto será un método que dado el nivel de dificultad creará una cantidad determinada de enemigos de distintos tipos
            this.arrActoresEnemigos = new EsquemaActor[5];
            for(int i = 0; i < arrActoresEnemigos.length; i++) {
                this.arrActoresEnemigos[i] = new EsquemaActor(
                        (int) (Math.random() * anchoPantalla) + 1, //posición aleatoria horizontal
                        (int) (Math.random() * altoPantalla) + 1,  //posción aleatoria vertical
                        3, Constantes.SPRITES_ESCALA,
                        RecursosCodigo.getBitmapFromAssets(context, ENEMY1_64x160_PATH),
                        160,
                        64
                );
            }

            powerUp = new EsquemaActor(
                    anchoPantalla/2,
                    altoPantalla/2,
                    1,
                    RecursosCodigo.getBitmapFromAssets(context, LOGO_PATH),
                    1/4f
            );

            //añadimos todos los elementos dibujados a la colección de cosas que se van a dibujar
            arlActor = new ArrayList<EsquemaActor>();
//        arlActor.add(siegfried);
            arlActor.add(powerUp);
            for (EsquemaActor ea: arrActoresEnemigos) {
                arlActor.add(ea);
            }

            Log.d("QAZ","Cargación");
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
            siegfried.dibujaActor(c);

            btnAtras.dibujaBoton(c);
        }

        @Override
        public int onTouchEvent(MotionEvent event) {
            siegfried.pulsaActor(event);

            return super.onTouchEvent(event);
        }

        @Override
        public void escenaActFisicas() {
//        for(EsquemaActor ea : arlActor){
//            siegfried.choca(ea);
//        }
            for(int i = 0; i < arlActor.size(); i++){
//            for(int j = 0; j<arlActor.size(); j++){
                siegfried.choca(arlActor.get(i));
//            }
            }
        }
    }
