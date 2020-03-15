package com.example.city_clean.escenas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.widget.Button;

import com.example.city_clean.MainActivity;
import com.example.city_clean.R;
import com.example.city_clean.elementos.Boton;
import com.example.city_clean.elementos.MyTimerTask;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.city_clean.codeUtils.Constantes.ESCENA_OPCIONES_VALUE;
import static com.example.city_clean.codeUtils.Constantes.ESCENA_RECORDS_VALUE;


// Esta clase pasará a ser un lanzador para la partida,
// aquí se instanciará el timer que compartirán los distintos niveles.

//El timer se instanció en el main porque si no
// no podía ser accedido por el onResume y otros métodos fácilmente

//Va a haber dos botones, "Nueva partida" y "Continuar"

// Con más tiempo se podría plantear poner dibujitos representativos del estado de la partida aquí

//Este sub-menú tendrá un fondo de parallax

public class EscenaJuego extends EsquemaEscena {

    public Context context;
    public TimerTask ttDuracionPartida;
    public float duracionPartida;
    ArrayList<Boton> arlBotones;

    int auxH;
    int auxV;

    public EscenaJuego(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);

        this.auxV = altoPantalla/5;
        this.auxH = anchoPantalla/3;

//        this.duracionPartida = MainActivity.duracionPartida;
//                                        // *IMPORTANTE* Aquí es donde se le da valor al timer.
//                                        // Es posible que al reanudar la ejecución vuelva a 0
//                                        // Se podría crear una variable estática guardable
//                                        // si la partida ha sido iniciada antes o no
//
//
//
//        ttDuracionPartida = new MyTimerTask(duracionPartida);

        this.arlBotones = new ArrayList<Boton>();
        this.arlBotones.add(
                new Boton(
                auxH*0,auxV *1,
                auxH*2, auxV*2,
                Color.TRANSPARENT,
                true,
                context.getString(R.string.btn_continue),
                context.getColor(R.color.papiro2),
                ESCENA_RECORDS_VALUE));

        this.arlBotones.add(
                new Boton(
                auxH*0,auxV*3,
                auxH*2,auxV*4,
                Color.TRANSPARENT,
                true,
                context.getString(R.string.btn_newgame),
                context.getColor(R.color.papiro2),
                ESCENA_OPCIONES_VALUE));

        this.arlBotones.add(btnAtras);
    }

    @Override
    public void escenaDibuja(Canvas c) {
        c.drawRect(0,0,anchoPantalla, altoPantalla, new Paint());
        for (Boton b: arlBotones) {
            b.dibujaBoton(c);
        }
    }

    @Override
    public int onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                for(Boton b : arlBotones){
                    if(b.pulsaBoton(event)){
                        return b.btnValor;
                    }
                }
                break;
        }
        return this.idEscena;
    }
}