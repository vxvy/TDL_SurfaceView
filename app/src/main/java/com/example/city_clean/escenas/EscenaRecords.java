package com.example.city_clean.escenas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;

import com.example.city_clean.R;
import com.example.city_clean.codeUtils.AssetsPaths;
import com.example.city_clean.elementos.Boton;
import com.example.city_clean.elementos.GestionDatos;

import java.util.Map;

public class EscenaRecords extends EsquemaEscena {

    public GestionDatos gd;

    public Paint paintTexto;
    public Paint paintRecords;
    public Paint paintFondo;
    public Boton btnBorrar;

    public int auxH;
    public int auxV;

    public String strRecords;
    public String[] arrRecords;

    public EscenaRecords(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);
        this.gd = new GestionDatos();

        this.auxH = anchoPantalla/15;
        this.auxV = altoPantalla/15;

        btnBorrar = new Boton(
                auxH*13,
                auxV*12,
                auxH*14,
                auxV*14,
                context.getColor(R.color.orangyhard),
                true,
                666
                );

        paintTexto = new Paint();
        paintTexto.setTypeface(Typeface.createFromAsset(context.getAssets(), AssetsPaths.FONT_TEXT_PATH));
        paintTexto.setTextSize(auxV);
        paintTexto.setColor(Color.BLACK);

        paintFondo = new Paint();
        paintFondo.setColor(context.getColor(R.color.papiro2));

        strRecords = "";
        arrRecords = this.actualizarRecords();
     }

    @Override
    public void escenaDibuja(Canvas c) {
        c.drawRect(0,0,anchoPantalla,altoPantalla,paintFondo);
        btnBorrar.dibujaBoton(c);

        for(int i = 0; i < arrRecords.length; i++){
            c.drawText(
                    arrRecords[i],
                    auxV,auxV*(i+1),
                    paintTexto
                    );
//            Log.d("ASDF", i+"");
        }

        super.escenaDibuja(c);
    }

    @Override
    public int onTouchEvent(MotionEvent event) {
        if(event.getAction() == event.ACTION_DOWN){
//            Log.d("ASDF","BORRAR REcOrDs");
            gd.borrarRecords();
            this.actualizarRecords();
        }
        return super.onTouchEvent(event);
    }

    public String[] actualizarRecords(){
        try{
            Map<String,String> aux = (Map<String, String>) gd.cargarRecords();
            if(aux.isEmpty()){ //TODO quitar cuando termine las pruebas
                throw new NullPointerException();
            }else {

                for (Map.Entry<String,String> entry : aux.entrySet()){
                    strRecords = entry.getKey() + "\t" + entry.getValue().toString()+"\n";
                }
                return strRecords.split("\n");
            }
        }catch (NullPointerException npe){  //TODO quitar cuando termine las pruebas
            String []aux = new String[]{"PACO \t 999", "PATO \t 1"};
            return aux;
        }
    }

}