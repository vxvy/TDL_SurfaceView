package com.example.city_clean;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    public static Context context;
    public static boolean musica;
    public static boolean efectos;
    public static boolean vibracion;
    public static float duracionPartida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }catch(Exception ignore){}

        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        int opciones = View.SYSTEM_UI_FLAG_FULLSCREEN        // pone la pantalla en modo pantalla completa ocultando elementos no criticos como la barra de estado.
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION  // oculta la barra de navegación
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(opciones);

        this.context = this;

        //Datos partida
        this.duracionPartida = 0f;

        //Opciones
        this.musica = true;
        this.efectos = true;
        this.vibracion = true;

        EscenaEnPantalla contenedorSurfaceView = new EscenaEnPantalla(this);
        contenedorSurfaceView.setKeepScreenOn(true);
        this.setContentView(contenedorSurfaceView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Se quita el getWindow porque no hace falta en el onResume
        try{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }catch(Exception ignore){}

        View decorView = getWindow().getDecorView();
        int opciones = View.SYSTEM_UI_FLAG_FULLSCREEN       // pone la pantalla en modo pantalla completa ocultando elementos no criticos como la barra de estado.
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION       // oculta la barra de navegación
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(opciones);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
