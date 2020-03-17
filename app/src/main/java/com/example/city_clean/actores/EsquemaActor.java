package com.example.city_clean.actores;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.example.city_clean.MainActivity;
import com.example.city_clean.R;
import com.example.city_clean.codeUtils.RecursosCodigo;

public class EsquemaActor {

    public Bitmap[][] spritesArray;
    public Bitmap spriteContenedor;
    public Bitmap spriteMostrandose;

    public int spriteIndexI;
    public int spriteIndexJ;
    public int spriteTamanyoVisX;
    public int spriteTamanyoVisY;

    public float x, y;
    public boolean atacando;
    public int numVidas;
    public boolean colisiona;

    public RectF bloqueColision;
    public Paint bloqueColisionPaint;

    public float escalaSprite;

    public EsquemaActor(){}//Para actores concretos.

    public EsquemaActor(float x, float y, int numVidas, Bitmap spriteContenedor, float escalaSprite){
        this.x = x;
        this.y = y;
        this.numVidas = numVidas;
        this.atacando = false;
        this.spriteIndexI = 0;
        this.spriteIndexJ = 0;
        this.escalaSprite = escalaSprite;
        this.colisiona = false;

        this.spriteTamanyoVisX = (int)(spriteContenedor.getWidth()*escalaSprite);
        this.spriteTamanyoVisY = (int)(spriteContenedor.getHeight()*escalaSprite);

        this.spriteContenedor = spriteContenedor;

        this.spriteMostrandose = Bitmap.createScaledBitmap(
                spriteContenedor,
                spriteTamanyoVisX,
                spriteTamanyoVisY,
                false);

        this.bloqueColision = new RectF(
                x, y,
                x + spriteTamanyoVisX,
                y + spriteTamanyoVisY);

        this.bloqueColisionPaint = new Paint();
        this.bloqueColisionPaint.setColor(MainActivity.context.getColor(R.color.colisionNo));
        this.bloqueColisionPaint.setStyle(Paint.Style.STROKE);
        this.bloqueColisionPaint.setStrokeWidth(3);
    }

    /**
     *
     * @param x posición X donde empieza a dibujar
     * @param y posición Y donde empieza a dibujar
     * @param numVidas numvidas totales
     * @param escalaSprite tiene que ser en potencias de 2 para evitar errores de decimales
     * @param spriteContenedor BM ORIGINAL con los sprites
     * @param tamanyoXSpriteEnBM lo que mide la X del sprite en pixels reales dentro del bitmap
     * @param tamanyoYSpriteEnBM lo que mide la Y del sprite en pixels reales dentro del bitmap
     */

    public EsquemaActor(float x, float y, int numVidas, float escalaSprite,
                        Bitmap spriteContenedor, int tamanyoXSpriteEnBM, int tamanyoYSpriteEnBM){
        this(x, y, numVidas, spriteContenedor, escalaSprite);

        //estas variables tienen que ser redefinidas porque las anteriores se consideran para un sólo sprite
        this.spriteTamanyoVisX = (int)(tamanyoXSpriteEnBM * escalaSprite);
        this.spriteTamanyoVisY = (int)(tamanyoYSpriteEnBM * escalaSprite);
        //this.spriteMostrandose contiene el bitmap contenedor ya escalado

        this.spritesArray = RecursosCodigo.construyeArray(
            spriteMostrandose, //usamos este Bitmap en vez del contenedor porque se ha escalado previamente
            spriteContenedor.getWidth()/tamanyoXSpriteEnBM,  //calcula cuántos sprites totales hay,     CORRECTO
            spriteContenedor.getHeight()/tamanyoYSpriteEnBM,   // aunque se escale es proporcional        CORRECTO
            this.spriteTamanyoVisX,
            this.spriteTamanyoVisY
        );

        this.spriteMostrandose = this.spritesArray[spriteIndexI][spriteIndexJ];
        this.actualizarColision(); //Actualiza la posición del Rectánculo de colisión tras el recorte + escalado
    }


    public void dibujaActor(Canvas c){
        c.drawBitmap(
                spriteMostrandose,
                x, y, null);
        dibujaDebug(c);
    }

    public void dibujaDebug(Canvas c) {
        if (this.colisiona) {
            bloqueColisionPaint.setColor(MainActivity.context.getColor(R.color.colisionYes));
        }else {
            bloqueColisionPaint.setColor(MainActivity.context.getColor(R.color.colisionNo));
        }
        c.drawRect(
                bloqueColision,
                bloqueColisionPaint
        );
    }

    public void pulsaActor(MotionEvent event){
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_MOVE:
                this.actualizarPosicion(
                        (int)event.getX(),
                        (int)event.getY()
                );
                break;
        }
    }

    public void actualizarPosicion(int x, int y){
        this.x = x - (this.spriteTamanyoVisX/2);
        this.y = y - (this.spriteTamanyoVisY/2);
        actualizarColision();
    }

    public void actualizarColision(){
        bloqueColision.left = this.x;
        bloqueColision.top = this.y;
        bloqueColision.right = this.x + this.spriteTamanyoVisX;
        bloqueColision.bottom = this.y + this.spriteTamanyoVisY;

    }

    public void choca(EsquemaActor otroActor){
        if(this.bloqueColision.intersect(otroActor.bloqueColision)){
            this.colisiona = true;
            otroActor.colisiona = true;
        }else{
            this.colisiona = false;
            otroActor.colisiona = false;
        }
    }

    /**
     * Cambia de modo ataque (true) a pacífico (false)
     */
    public void atacar(boolean atacando){
        this.atacando = atacando;
    }
}