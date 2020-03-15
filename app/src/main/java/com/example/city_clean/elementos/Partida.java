package com.example.city_clean.elementos;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.city_clean.MainActivity;
import com.example.city_clean.codeUtils.Constantes;

import java.util.ArrayList;
import java.util.Hashtable;

public class Partida {

    public SharedPreferences sp;

    public Partida(){
        this.sp = MainActivity.context.getSharedPreferences(
                Constantes.FICHERO_SP, Context.MODE_PRIVATE);
    }

    public nuevaPartida(){

    }

    public void guardarPartida(){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString()
    }

//    datos que quiero guardar en el shared preferences
//
//    número de vidas
//    posición de pnj en la pantalla
//    número de enemigos derrotados //lo necesario para pasar a la siguiente sala
//    tiempo de juego transcurrido
//    estado del ataque especial
//    id de la pantalla en que está
//    id del power up que lleve si es que lleva (estano normal powerup = none)

    public void cargarPartida(Hashtable datosPartida){
//        for (Object o: datos) {
//            if ()
//        }
    }

}
