package com.example.city_clean;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.city_clean.codeUtils.Constantes;
import com.example.city_clean.escenas.EdJ1.EscenaDeJuegoNivel1;
import com.example.city_clean.escenas.EdJ1.EscenaDeJuegoNivelBoss;
import com.example.city_clean.escenas.EscenaAyuda;
import com.example.city_clean.escenas.EscenaCreditos;
import com.example.city_clean.escenas.EscenaEligeJuego;
import com.example.city_clean.escenas.EscenaGameOver;
import com.example.city_clean.escenas.EscenaMenu;
import com.example.city_clean.escenas.EscenaOpciones;
import com.example.city_clean.escenas.EscenaRecords;
import com.example.city_clean.escenas.EsquemaEscena;

public class EscenaEnPantalla extends SurfaceView implements SurfaceHolder.Callback {
    public Context context;
    private Hilo hilo;
    private SurfaceHolder surfaceHolder;

    public EsquemaEscena escena;
    public boolean funcionando = false;

    private int anchoPantalla = 1;
    private int altoPantalla = 1;

    public EscenaEnPantalla(Context context) {
        super(context);
        this.setFocusable(true);
        this.context = context;

        this.surfaceHolder = this.getHolder();
        this.surfaceHolder.addCallback(this);

        hilo = new Hilo();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        hilo.setFuncionando(true); // Se le indica al hilo que puede arrancar
        if (hilo.getState() == Thread.State.NEW) hilo.start(); // si el hilo no ha sido creado se crea;
        if (hilo.getState() == Thread.State.TERMINATED) {      // si el hilo ha sido finalizado se crea de nuevo;
            hilo=new Hilo();
            hilo.start(); // se arranca el hilo
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        anchoPantalla = width;
        altoPantalla = height;

        escena = new EscenaMenu(context, Constantes.ESCENA_MENU_VALUE, width, height);

        hilo.setSurfaceSize(width,height);   // se establece el nuevo ancho y alto de pantalla en el hilo
        hilo. setFuncionando(true);
        if(hilo.getState() == Thread.State.NEW){
            hilo.start();
        }
        if(hilo.getState() == Thread.State.TERMINATED){
            Hilo h = new Hilo();
            h.start();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hilo.setFuncionando(false);  // Se para el hilo
        try {
            hilo.join();   // Se espera a que finalize
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        synchronized (surfaceHolder) {
            int nuevaEscena=escena.onTouchEvent(event);
            if(nuevaEscena!=escena.idEscena){
                switch (nuevaEscena){
                    case Constantes.ESCENA_JUGAR_VALUE:
                        escena = new EscenaEligeJuego(context, nuevaEscena, anchoPantalla, altoPantalla);
                        break;
                    case Constantes.ESCENA_AYUDA_VALUE:
                        escena = new EscenaAyuda(context, nuevaEscena, anchoPantalla, altoPantalla);
                        break;
                    case Constantes.ESCENA_RECORDS_VALUE:
                        escena = new EscenaRecords(context, nuevaEscena, anchoPantalla, altoPantalla);
                        break;
                    case Constantes.ESCENA_OPCIONES_VALUE:
                        escena = new EscenaOpciones(context, nuevaEscena, anchoPantalla, altoPantalla);
                        break;
                    case Constantes.ESCENA_CREDITOS_VALUE:
                        escena = new EscenaCreditos(context, nuevaEscena, anchoPantalla, altoPantalla);
                        break;
                    case Constantes.ESCENA_JUEGO_NIVEL_1_VALUE:
                        escena = new EscenaDeJuegoNivel1(context, nuevaEscena, anchoPantalla, altoPantalla);
                        break;
                    case Constantes.ESCENA_JUEGO_NIVEL_JEFE_VALUE:
                        escena = new EscenaDeJuegoNivelBoss(context, nuevaEscena, anchoPantalla, altoPantalla);
                        break;
                    case Constantes.ESCENA_CARGAR_JUEGO:
                        //Cargar parámetros de shared preferences aquí
                        escena = new EscenaEligeJuego(context, nuevaEscena, anchoPantalla, altoPantalla);
                        break;
                    case Constantes.ESCENA_GAME_OVER:
                        escena = new EscenaGameOver(context, nuevaEscena, anchoPantalla, altoPantalla);
                        break;
                    default:
                    case Constantes.ESCENA_MENU_VALUE:
                        escena=new EscenaMenu(context,nuevaEscena,anchoPantalla,altoPantalla);
                        break;
                }
            }
//            else{
//                escena.onTouchEvent(event);
//            }
        }
        return true; //true si ha sido gestionado el evento
    }

    class Hilo extends Thread {
        public Hilo(){}
        @Override
        public void run() {
            while (funcionando) {
                Canvas c = null; //Necesario repintar la totalidad del lienzo
                try {
                    if (!surfaceHolder.getSurface().isValid()) continue; // si la superficie no está preparada repetimos
                    c = surfaceHolder.lockCanvas(); // Obtenemos el lienzo.  La sincronización es necesaria por ser recurso común
                    synchronized (surfaceHolder) {
                        escena.escenaActFisicas();  // Movimiento de los elementos
                        escena.escenaDibuja(c);              // Dibujamos los elementos
                    }
                } finally {  // Haya o no excepción, hay que liberar el lienzo
                    if (c != null) {
                        surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }

        // Activa o desactiva el funcionamiento del hilo
        void setFuncionando(boolean flag) {
            funcionando = flag;
        }

        // Función es llamada si cambia el tamaño de la pantall o la orientación
        public void setSurfaceSize(int width, int height) {
            synchronized (surfaceHolder) {  // Se recomienda realizarlo de forma atómica
            }
        }
    }
}